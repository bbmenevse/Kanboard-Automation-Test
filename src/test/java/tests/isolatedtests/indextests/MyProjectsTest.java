package tests.isolatedtests.indextests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import helper.HeaderHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.Modals.ConfirmationModal;
import pages.Modals.NewProjectModal;
import pages.index.DashboardPage;
import pages.index.MyProjectsPage;
import pages.project.projectconfiguration.SummaryPage;
import tests.BaseTest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.*;

public class MyProjectsTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    HeaderHelper headerHelper = new HeaderHelper();
    DashboardPage dashboardPage = new DashboardPage();
    MyProjectsPage myProjectsPage = new MyProjectsPage();
    NewProjectModal newProjectModal = new NewProjectModal();

    String identifier = "MPNPT";
    String projectName = "MyProjects New Project Test";

    @BeforeMethod
    public void setUp(){
        String userName = "MyProjectTestsAdmin";
        String password = "123456";
        loginPage.login(userName,password);
        dashboardPage.myProjectsButtonOnSideBar.shouldBe(visible).click();
    }

    @AfterMethod
    public void logOut(){
        headerHelper.logOut();
    }
    @Test(priority = 0)
    public void sortTest(){
        SelenideElement firstElement = $$("div.table-list-row").first();
        SelenideElement lastElement = $$("div.table-list-row").last();

        // Project Personal clicked first to reset ascending/descending.
        myProjectsPage.sortButton.click();
        myProjectsPage.projectPersonal.shouldBe(visible).click();
        myProjectsPage.sortButton.click();
        myProjectsPage.projectId.shouldBe(visible).click();
        // ProjectId Ascending
        assertTrue(firstElement.getText().contains("First Example Project"));
        assertTrue(lastElement.getText().contains("A Project For Project Tests"));

        myProjectsPage.sortButton.click();
        myProjectsPage.projectId.shouldBe(visible).click();
        // ProjectId Descending
        assertTrue(firstElement.getText().contains("A Project For Project Tests"));
        assertTrue(lastElement.getText().contains("First Example Project"));

        myProjectsPage.sortButton.click();
        myProjectsPage.projectName.shouldBe(visible).click();
        // ProjectName Ascending
        assertTrue(firstElement.getText().contains("A Project For Project Tests"));
        assertTrue(lastElement.getText().contains("Hole to another universe"));

        myProjectsPage.sortButton.click();
        myProjectsPage.projectName.shouldBe(visible).click();
        // ProjectName Descending
        assertTrue(firstElement.getText().contains("Hole to another universe"));
        assertTrue(lastElement.getText().contains("A Project For Project Tests"));
    }

    @Test(priority = 1)
    public void createNewProjectTest(){
        myProjectsPage.newProjectButton.click();
        newProjectModal.projectNameInput.setValue(projectName);
        newProjectModal.identifierInput.setValue(identifier);
        newProjectModal.submitButton.click();
        headerHelper.dashboardTitle.should(Condition.text(projectName));

    }
    @Test(priority = 2)
    public void createNewProjectFormErrorsTest(){
        myProjectsPage.newProjectButton.click();
        newProjectModal.submitButton.shouldBe(visible).click();
        assertEquals(newProjectModal.formError.shouldBe(visible).getText(), "The project name is required");
        newProjectModal.projectNameInput.setValue("Mytest##£>#½{{[]×¶|¸ä┼;°");
        newProjectModal.identifierInput.setValue(identifier);
        newProjectModal.submitButton.click();
        newProjectModal.formError.shouldNotHave(Condition.text("The project name is required"));
        assertEquals(newProjectModal.formError.shouldBe(visible).getText(), "The identifier must be unique");
        newProjectModal.cancelButton.click();
        // Make sure new project modal opens with empty input fields.
        myProjectsPage.newProjectButton.shouldBe(visible).click();
        assertTrue(newProjectModal.identifierInput.shouldBe(visible).getValue().isEmpty());
        assertTrue(newProjectModal.projectNameInput.shouldBe(visible).getValue().isEmpty());
        newProjectModal.cancelButton.click();
    }
    @Test(priority = 3)
    public void deleteProject(){
        myProjectsPage.clickIdDropdownByProjectName(projectName);
        myProjectsPage.configureThisProject.click();
        SummaryPage summaryPage = new SummaryPage();
        summaryPage.clickOnMenuLinks("Remove");
        ConfirmationModal.confirmButton.click();
        assertFalse(myProjectsPage.doesProjectExist(projectName));
    }




}
