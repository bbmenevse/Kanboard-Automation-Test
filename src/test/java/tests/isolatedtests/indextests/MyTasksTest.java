package tests.isolatedtests.indextests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import helper.HeaderHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.Modals.CommentsOnTaskModal;
import pages.Modals.ConfirmationModal;
import pages.Modals.NewTaskModal;
import pages.alerts.ConfirmationAlert;
import pages.index.MyProjectsPage;
import pages.index.MyTasksPage;
import pages.project.BoardPage;
import tests.BaseTest;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.*;


// If we add a comment, the page doesn't refresh and comment modal is open.
// When user clicks cancel or close button, page still doesn't refresh.
// This causes the number of comments to not update. If the comment is edited or removed,
// the page dom updates and a confirmation alert shows at bottom. The number is updated.
// Just an observation.

// Duplicating a task does not duplicate the comments. Just an observation.

// I use a global variable to check comment number of a given task.
// This means, I can't use Singleton method for this one If I decide to also
// use similar checks for other tasks. This could be fixed if I made the tests into One Big Test
// Or just don't make this single class Singleton.
public class MyTasksTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    HeaderHelper headerHelper = new HeaderHelper();
    MyTasksPage myTasksPage = new MyTasksPage();
    CommentsOnTaskModal commentsOnTaskModal = new CommentsOnTaskModal();
    ConfirmationAlert confirmationAlert = new ConfirmationAlert();
    NewTaskModal newTaskModal = new NewTaskModal();
    MyProjectsPage myProjectsPage = new MyProjectsPage();
    BoardPage boardpage = new BoardPage();

    private String initialTaskName = "Mytasks Test Task";
    private String duplicateTaskName = "Duplicated Mytasks Test Task"; // Task which I will use for comment tests
    private String initialComment = "Second comment before edit.";
    private String updatedComment = "Second comment after edit.";
    private String commentNumberOnTask;

    @BeforeMethod
    public void setUp(){
        String userName = "MyTasksTestsAdmin";
        String password = "123456";
        loginPage.login(userName,password);
        myTasksPage.myTasksButtonOnSideBar.shouldBe(visible).click();
    }
    @AfterMethod
    public void logOut(){
        headerHelper.clickGivenElementOnDropdown(headerHelper.logoutOnDropdown);
    }

    @Test
    public void sortTasksTest()
    {
        SelenideElement firstRow = $("div.table-list").$$("div.table-list-row").first();
        SelenideElement lastRow = $("div.table-list").$$("div.table-list-row").last();

        // Sort task title first, then back to task Id to make sure it is in correct order(ascending/descending)
        myTasksPage.sortButton.click();
        myTasksPage.taskPriority.click();
        myTasksPage.sortButton.click();
        myTasksPage.taskId.click();

        assertTrue(firstRow.getText().contains("A task for the unworthy"));
        assertTrue(lastRow.getText().contains("Mytasks Test Task"));

        myTasksPage.sortButton.click();
        myTasksPage.taskId.click();

        assertTrue(firstRow.getText().contains("Mytasks Test Task"));
        assertTrue(lastRow.getText().contains("A task for the unworthy"));

        myTasksPage.sortButton.click();
        myTasksPage.taskTitle.click();

        assertTrue(firstRow.getText().contains("A task for the unworthy"));
        assertTrue(lastRow.getText().contains("Randomless task"));

        myTasksPage.sortButton.click();
        myTasksPage.taskTitle.click();

        assertTrue(firstRow.getText().contains("Randomless task"));
        assertTrue(lastRow.getText().contains("A task for the unworthy"));

    }

    @Test(priority = 1)
    public void duplicateTaskTest()
    {
        myTasksPage.clickIdDropdownByTaskName(initialTaskName);
        myTasksPage.duplicateTask.shouldBe(visible).click();
        ConfirmationModal.confirmButton.shouldBe(visible).click();
        assertEquals(confirmationAlert.getConfirmationMessage(),"Task created successfully.");
    }

    @Test(priority = 2)
    public void editTaskTest(){
        myTasksPage.clickIdDropdownByTaskName("[DUPLICATE] " + initialTaskName);
        myTasksPage.editTask.shouldBe(visible).click();
        newTaskModal.taskTitle.shouldBe(visible).clear();
        newTaskModal.taskTitle.setValue(duplicateTaskName);
        newTaskModal.textarea.clear();
        newTaskModal.textarea.setValue("A duplicate task's text area");
        newTaskModal.submitButton.click();
        assertEquals(confirmationAlert.getConfirmationMessage(),"Task updated successfully.");
    }

    /**
     * Adding a comment directly on comments modal does not refresh page, which results in comments number not updating.
     * Which is why I added page refresh.
     */
    @Test(priority = 3)
    public void addCommentTest(){
        // No comment yet.
        commentNumberOnTask = myTasksPage.getCommentNumberByTaskName(duplicateTaskName);
        assertEquals(commentNumberOnTask, "0");

        myTasksPage.clickIdDropdownByTaskName(duplicateTaskName);
        myTasksPage.addComment.click();
        commentsOnTaskModal.textArea.setValue("First Comment");
        commentsOnTaskModal.submitButton.click();
        commentNumberOnTask = myTasksPage.getCommentNumberByTaskName(duplicateTaskName);
        assertEquals(commentNumberOnTask, "1");

        myTasksPage.clickCommentsByTaskName(duplicateTaskName);
        commentsOnTaskModal.textArea.setValue(initialComment);
        commentsOnTaskModal.submitButton.click();
        commentsOnTaskModal.cancelButton.shouldBe(visible).click();

        Selenide.refresh(); // Dom doesn't update after adding comment
        commentNumberOnTask = myTasksPage.getCommentNumberByTaskName(duplicateTaskName);
        assertEquals(commentNumberOnTask, "2");

        myTasksPage.clickCommentsByTaskName(duplicateTaskName);
        commentsOnTaskModal.textArea.setValue("A comment for Administrators only");
        commentsOnTaskModal.submitButton.click();
        commentsOnTaskModal.cancelButton.shouldBe(visible).click();
        Selenide.refresh(); // Dom doesn't update after adding comment
        commentNumberOnTask = myTasksPage.getCommentNumberByTaskName(duplicateTaskName);
        assertEquals(commentNumberOnTask, "3");
    }
    /**
     *
     */
    @Test(priority = 4)
    public void editCommentTest()
    {
        myTasksPage.clickCommentsByTaskName(duplicateTaskName);
        assertTrue(commentsOnTaskModal.doesCommentExistByCommentText(initialComment));
        commentsOnTaskModal.clickCommentDropdownByComment(initialComment);
        commentsOnTaskModal.edit.shouldBe(visible).click();
        assertEquals(initialComment, commentsOnTaskModal.textArea.shouldBe(visible).getValue());
        commentsOnTaskModal.textArea.clear();
        commentsOnTaskModal.textArea.setValue(updatedComment);
        commentsOnTaskModal.submitButton.click();
        assertEquals("Comment updated successfully.",confirmationAlert.getConfirmationMessage());
        commentNumberOnTask = myTasksPage.getCommentNumberByTaskName(duplicateTaskName);
        assertEquals(myTasksPage.getCommentNumberByTaskName(duplicateTaskName),commentNumberOnTask);

        myTasksPage.clickCommentsByTaskName(duplicateTaskName);
        assertTrue(commentsOnTaskModal.doesCommentExistByCommentText(updatedComment));
        commentsOnTaskModal.closeModalButton.click();
    }

    @Test(priority = 5)
    public void deleteCommentsTest(){
        while(!myTasksPage.getCommentNumberByTaskName(duplicateTaskName).equals("0")){
            myTasksPage.clickCommentsByTaskName(duplicateTaskName);
            commentsOnTaskModal.commentsCollection.get(0).$("a").click();
            commentsOnTaskModal.remove.click();
            ConfirmationModal.confirmButton.click();
        }
        assertEquals(myTasksPage.getCommentNumberByTaskName(duplicateTaskName), "0");
    }
    /**
     * Authorization for viewing comments is based on user roles, not project roles.
     * For example, a user with the "Manager" role can view "Application managers or more" comments
     * even if they are added to a project as a "Viewer." The role assigned to the user
     * determines their access to comments, regardless of their project-specific role.
     *
     * I am unsure if it's necessary to include an additional step to verify that editing a comment's visibility works correctly.
     */
    @Test(priority = 6)
    public void commentVisibilityTest(){

        String userComment = "A comment meant for users or those with higher authorization.";
        String managerComment = "A comment meant for managers or those with higher authorization.";
        String administratorComment = "A comment meant for administrators.";

        myTasksPage.clickIdDropdownByTaskName(duplicateTaskName);
        myTasksPage.addComment.shouldBe(visible).click();
        // Add first comment
        commentsOnTaskModal.textArea.shouldBe(visible).setValue(userComment);
        commentsOnTaskModal.visibilitySelectElement.selectOption("Standard users");
        commentsOnTaskModal.submitButton.click();

        myTasksPage.clickCommentsByTaskName(duplicateTaskName);
        // Add second comment
        commentsOnTaskModal.textArea.shouldBe(visible).setValue(managerComment);
        commentsOnTaskModal.visibilitySelectElement.selectOption("Application managers or more");
        commentsOnTaskModal.submitButton.click();
        // Add third comment
        commentsOnTaskModal.textArea.shouldBe(visible).setValue(administratorComment);
        commentsOnTaskModal.visibilitySelectElement.selectOption("Administrators");
        commentsOnTaskModal.submitButton.click();
        commentsOnTaskModal.cancelButton.click(); // to close the modal
        // Login into user and confirm comment views

        headerHelper.logOut();
        loginPage.login("MytasksTestUser","123456");
        myTasksPage.myProjectsButtonOnSideBar.click();
        myProjectsPage.clickProject("My Tasks Test Project");
        boardpage.clickCommentsIconByTaskName(duplicateTaskName);
        //myTasksPage.clickCommentsByTaskName(duplicateTaskName);
        commentsOnTaskModal.closeModalButton.shouldBe(visible); // Make sure modal is open.
        assertTrue(commentsOnTaskModal.doesCommentExistByCommentText(userComment));
        assertFalse(commentsOnTaskModal.doesCommentExistByCommentText(managerComment));
        assertFalse(commentsOnTaskModal.doesCommentExistByCommentText(administratorComment));
        commentsOnTaskModal.cancelButton.click();
        // Login into manager and confirm comment views
        headerHelper.logOut();
        loginPage.login("MytasksTestManager","123456");
        myProjectsPage.myProjectsButtonOnSideBar.click();
        myProjectsPage.clickProject("My Tasks Test Project");
        boardpage.clickCommentsIconByTaskName(duplicateTaskName);
        commentsOnTaskModal.closeModalButton.shouldBe(visible); // Make sure modal is open.
        assertTrue(commentsOnTaskModal.doesCommentExistByCommentText(userComment));
        assertTrue(commentsOnTaskModal.doesCommentExistByCommentText(managerComment));
        assertFalse(commentsOnTaskModal.doesCommentExistByCommentText(administratorComment));
        commentsOnTaskModal.closeModalButton.click(); // Close the modal so logout can happen.
    }

    /**
     * Delete the duplicated task
     */
    @Test(priority = 7)
    public void deleteDuplicateTaskTest(){
        myTasksPage.clickIdDropdownByTaskName(duplicateTaskName);
        myTasksPage.removeTask.click();
        ConfirmationModal.confirmButton.click();

        assertFalse(myTasksPage.doesTaskExist(duplicateTaskName));
    }


}
