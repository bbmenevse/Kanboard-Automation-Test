package tests.isolatedtests.projecttests;

import com.codeborne.selenide.*;
import com.codeborne.selenide.testng.SoftAsserts;
import helper.HeaderHelper;
import helper.NavigationHelper;
import tests.BaseTest;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.Modals.NewTaskModal;
import pages.project.BoardPage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.*;

@Listeners({ SoftAsserts.class})
public class BoardTest extends BaseTest {

    BoardPage boardPage = new BoardPage();
    LoginPage loginPage = new LoginPage();
    HeaderHelper headerHelper = new HeaderHelper();
    NewTaskModal newTaskModal = new NewTaskModal();

    @BeforeMethod
    public void setUp(){
        String userName = "BoardAdmin";
        String password = "123456";
        loginPage.login(userName,password);
        NavigationHelper.openProjectBoard("1");
    }

    @BeforeClass
    public void softAssert(){
        Configuration.assertionMode = AssertionMode.STRICT;
    }

    @AfterMethod
    public void logout(){
        headerHelper.clickGivenElementOnDropdown(headerHelper.logoutOnDropdown);
    }

    @Test
    // A test to see if dragging a task works
    public void dragTest(){

        boardPage.dragTaskToCategory("3", "2");
        boardPage.getTaskById("3").shouldHave(attribute("data-column-id", "2").because("Random Message"));

        boardPage.dragTaskToCategory("3", "1");
        boardPage.getTaskById("3").shouldHave(attribute("data-column-id", "1"));

        boardPage.dragTaskToCategory("3", "2");
        boardPage.getTaskById("3").shouldHave(attribute("data-column-id", "2").because("Random Message"));

        boardPage.dragTaskToCategory("3", "1");
        boardPage.getTaskById("3").shouldHave(attribute("data-column-id", "1"));
    }

    /**
     *
     */
    @Test
    public void filtersTest()
    {
        // CHECK IF OTHER'S TASK DISAPPEAR ON FILTER: Mytasks
        boardPage.defaultFilters.click();
        boardPage.myTasks.click();
        assertFalse(boardPage.getTaskAnchorElementByGivenName("Random Task").isDisplayed());

        // CHECK IF THE DUE TOMORROW FILTER WORKS
        // Create a task with tomorrow as duedate
        $x(String.format(boardPage.plusSignOnColumn,"Done")).click();
        newTaskModal.taskTitle.shouldBe(visible);

        // Creating A date variable that is set to today + 1 day, which will be used in making the task being due tomorrow.
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dueDateString = tomorrow.format(formatter);

        // Create task and set its due date
        newTaskModal = new NewTaskModal.Builder("Task with due date tomorrow!")
                .setTextarea("Random stuff here")
                .setColorPicker("Blue")
                .setComplexity("1")
                .setColumn("Done")
                .setDueDate(dueDateString)
                .setOriginalEstimate("10")
                .build();
        newTaskModal.submitButton.click();

        boardPage.defaultFilters.shouldBe(visible).click();
        boardPage.tasksDueTomorrow.shouldBe(visible).click();

        /*
        Very interesting, the due tomorrow fails at late at night. This is probably due to some local and application timezone
        conflict. The time this bug occurred on my computer: 02:39 am, on application task is shown to be created at
        23:39. I will need to check this again tomorrow.

        Yup, the problem only exists during close night. It is morning now and the task shows up when tasks are filtered
        with due tomorrow tag.
         */
        assertTrue(boardPage.getTaskAnchorElementByGivenName("Task with due date tomorrow!").shouldBe(visible).isDisplayed());
        boardPage.deleteTaskByName("Task with due date tomorrow!");
    }

    @Test(priority = 1)
    // A test to make sure tasks can be created and deleted
    // I tried to check for a limit on how many tasks can be created
    // but it is probably high. I tried 300 max.
    public void createAndDeleteTasksTest()
    {
        //NewTaskModal newTaskModal = new NewTaskModal();
        for(int k=0;k<10;k++)
        {
            newTaskModal.taskTitle.shouldNotBe(visible);
            $x(String.format(boardPage.plusSignOnColumn, "Done")).shouldBe(visible,enabled).click();
            newTaskModal.taskTitle.shouldBe(visible);
            newTaskModal = new NewTaskModal.Builder("Task number: " +(k+1))
                    .setTextarea("Bla bla bla")
                    .setColorPicker("Red")
                    .setComplexity("1")
                    .setColumn("Done")
                    .setOriginalEstimate("10")
                    .build();
            newTaskModal.submitButton.click();
        }
        newTaskModal.taskTitle.shouldNotBe(visible);
        boardPage.deleteAllTasksOnColumn("4"); // 4TH COLUMN: Done

    }

    @Test(priority = 2)
    public void taskSortTest(){
        // Note:
        // AFTER SORTING A COLUMN, NEW ADDED TASKS DO NOT GET SORTED BUT INSTEAD END UP AT THE BOTTOM.
        boardPage.clickGivenLocator(boardPage.sortButtonOnGivenColumn,"Backlog");
        boardPage.sortTasksByIdAscending.click();
        assertTrue(boardPage.isColumnOrderCorrect("1","A task to see how this looks","Randomless task"));

        boardPage.clickGivenLocator(boardPage.sortButtonOnGivenColumn,"Backlog");
        boardPage.sortTasksByIdDescending.click();
        assertTrue(boardPage.isColumnOrderCorrect("1","Randomless task","A task to see how this looks"));

        // CREATE NEW TASK TO SEE WHERE IT POSITIONS
        // WHILE SORTING ORDER IS ID DESCENDING, IT SHOULD PLACE LAST
        boardPage.clickGivenLocator(boardPage.plusSignOnColumn,"Backlog");
        NewTaskModal firstNewTask = new NewTaskModal.Builder("Kanboard BoardPage Test Task")
                .setTextarea("Bla bla bla")
                .setColorPicker("Green")
                .setComplexity("2")
                .setColumn("Backlog")
                .setOriginalEstimate("20")
                .build();
        firstNewTask.submitButton.click();
        boardPage.getTaskAnchorElementByGivenName("Kanboard BoardPage Test Task").shouldBe(visible);
        assertTrue(boardPage.isColumnOrderCorrect("1","Randomless task","Kanboard BoardPage Test Task"));
        // REORDER AND ASSERT THE ORDER
        boardPage.clickGivenLocator(boardPage.sortButtonOnGivenColumn,"Backlog");
        boardPage.sortTasksByAssigneeAscending.click();
        assertTrue(boardPage.isColumnOrderCorrect("1","Kanboard BoardPage Test Task","Just a task"));

        // CREATE ANOTHER TASK
        boardPage.clickGivenLocator(boardPage.plusSignOnColumn,"Backlog");
        NewTaskModal secondNewTask = new NewTaskModal.Builder("A BoardPage Test Task")
                .setTextarea("Bla bla bla")
                .setColorPicker("Red")
                .setComplexity("1")
                .setColumn("Backlog")
                .setOriginalEstimate("10")
                .build();
        secondNewTask.submitButton.click();
        boardPage.getTaskAnchorElementByGivenName("A BoardPage Test Task").shouldBe(visible);
        // 10 should not be at the bottom, but Kanboard adds it to the bottom without reordering.
        // Maybe it is intended this way by design.
        assertTrue(boardPage.isColumnOrderCorrect("1","Kanboard BoardPage Test Task","A BoardPage Test Task"));

        boardPage.deleteTaskByName("Kanboard BoardPage Test Task");
        boardPage.deleteTaskByName("A BoardPage Test Task");
    }

    /**
     * If the number of tasks exceed the task limit, background color of the table
     * changes to red. This test confirms the change happens.
     */
    @Test(priority = 3)
    public void tableColorChangeOnLimitExceededTest()
    {
        int currentTaskNumber = Integer.parseInt(boardPage.currentTaskNumberOnHeader.getText());
        int maximumTaskNumber = Integer.parseInt(boardPage.maximumTaskNumberOnHeader.getOwnText());

        for(int k=0;k<maximumTaskNumber-currentTaskNumber+1;k++)
        {
            newTaskModal.taskTitle.should(disappear);
            $x(String.format(boardPage.plusSignOnColumn, "Done")).shouldBe(visible,enabled).click();
            newTaskModal.taskTitle.shouldBe(visible);

            newTaskModal = new NewTaskModal.Builder("Task number: " +(k+1))
                    .setTextarea("Bla bla bla")
                    .setColorPicker("Red")
                    .setComplexity("1")
                    .setColumn("Done")
                    .setOriginalEstimate("10")
                    .build();
            newTaskModal.submitButton.click();
        }
        newTaskModal.taskTitle.shouldNotBe(visible);
        // WHEN DOM DOESN'T UPDATE FAST ENOUGH, SOME STUFF FAILS.
        // FOR EXAMPLE, DELETING A TASK RESULTS IN PAGE UPDATE AND DOM UPDATES
        // THERE SHOULD BE MECHANISMS TO CONFIRM THAT DOM IS REFRESHED FOR STUFF THAT UPDATES DOM
        assertTrue(boardPage.isTaskLimitExceeded());
        boardPage.deleteAllTasksOnColumn("4");
        assertFalse(boardPage.isTaskLimitExceeded());
    }

    @Test(priority = 4)
    public void columnShapesTest()
    {
        // TODO
        // NEED TO CONFIRM THAT TASKS ARE IN CORRECT ORDER IN THE BEGINNING


        // Collapse Tasks
        boardPage.configureProjectButton.click();
        if(boardPage.expandTasks.isDisplayed())
        {
            boardPage.expandTasks.click();
            boardPage.configureProjectButton.shouldBe(visible).click();
        }
        boardPage.collapseTasks.shouldBe(visible).click();
        $("div.task-board-collapsed").should(exist);

        boardPage.clickGivenLocator(boardPage.sortButtonOnGivenColumn,"Backlog");
        boardPage.sortTasksByIdAscending.click();

        SelenideElement firstElement = boardPage.getCollapsedTaskByName("Randomless task");
        SelenideElement secondElement = boardPage.getCollapsedTaskByName("A task to see how this looks");
        boardPage.dragTaskAboveAnotherTask("Randomless task","A task to see how this looks"); // Drag task 7 over Task 2
        int taskOneLocation = firstElement.getLocation().getY();
        //System.out.println("first: " + taskSevenLocation);
        int taskTwoLocation = secondElement.getLocation().getY();
        //System.out.println("second: " + taskTwoLocation);
        // Drag Task 7 over 2 and assert that task 7 is above 2
        assertTrue(taskOneLocation < taskTwoLocation);
        boardPage.dragTaskAboveAnotherTask("Randomless task","A task to see how this looks"); // Drag task 2 over task 7
        taskOneLocation = firstElement.getLocation().getY();
        taskTwoLocation = secondElement.getLocation().getY();
        // Drag Task 2 over 7 and assert that task 2 is above 7
        assertTrue(taskTwoLocation < taskOneLocation);

        // Expand Tasks
        boardPage.configureProjectButton.click();
        boardPage.expandTasks.shouldBe(visible).click();
    }
    @Test(priority = 5)
    public void hideColumnTest(){

        // BUG REPORT
        // There is actually a small problem here, the icon for Hide this column
        // is not inside the <a> element so clicking it has no effect!

        // Collapse Column
        $x(String.format(boardPage.columnTitle,"Done")).click();
        boardPage.hideColumnButton.click();
        SelenideElement column = $("div.board-task-list[data-column-id='4']"); // 4th colum is chosen
        assertFalse(column.isDisplayed());

        // ADD TASK AND CONFIRM TASK NUMBER CHANGES
        assertEquals(boardPage.getTaskCountOnColumn("4"),0);
        boardPage.configureProjectButton.click();
        boardPage.addNewTask.click();
        newTaskModal.taskTitle.shouldBe(visible, Duration.ofSeconds(10));
        newTaskModal = new NewTaskModal.Builder("random task")
                .setTextarea("Bla bla bla")
                .setColorPicker("Green")
                .setComplexity("2")
                .setColumn("Done")
                .setOriginalEstimate("10")
                .build();
        newTaskModal.submitButton.click();
        boardPage.getTaskAnchorElementByGivenName("random task").shouldBe(visible);
        Selenide.Wait().until(webDriver -> boardPage.getTaskCountOnColumn("4") == 1); // Dom does not update fast enough for refresh()
        // Expand Column
        boardPage.clickPlusIconForColumn("Done");
        boardPage.deleteAllTasksOnColumn("4");
        assertTrue(column.isDisplayed());
    }
}
