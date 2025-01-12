package tests.isolatedtests;


import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import helper.HeaderHelper;
import helper.NavigationHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.index.DashboardPage;
import pages.LoginPage;
import org.testng.annotations.Test;
import tests.BaseTest;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
// This one was for learning purposes, Is not used anymore.

public class IndexTest extends BaseTest {

    SelenideElement boardIdInput = $("input[name='boardId']");

    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();
    HeaderHelper headerHelper = new HeaderHelper();
    @BeforeMethod
    public void login() {
        loginPage.loginAsAdmin();
    }

    @AfterMethod
    public void logOut(){
        headerHelper.clickGivenElementOnDropdown(headerHelper.logoutOnDropdown);
    }

    @Test(enabled = false)
    public void myProjectsButtonTest() {
        assertTrue(dashboardPage.isTaskComplexityNumberCorrectForProject("A task to see how this looks","2"));
        assertFalse(dashboardPage.isTaskComplexityNumberCorrectForProject("A task to see how this looks","3"));
    }

    // ALL MENU ITEMS
    @Test(enabled = false)
    public void AllMenuItemsVisibilityTest()
    {
        assertTrue(dashboardPage.areDropdownFiltersOnSearchBarVisible());
        $("body").click();
        assertTrue(dashboardPage.areSortButtonMenuOnProjectsElementsVisible());
        $("body").click();
        assertTrue(dashboardPage.isTaskSortMenuElementsVisible("First Example Project"));
        $("body").click();
        assertTrue(dashboardPage.isTaskWithGivenNameMenuElementsVisible(2));
        $("body").click();
    }

    @Test(enabled = false)
    public void changeIconWhenSubtaskStatusIsToggled() {
        assertTrue(dashboardPage.doesIconChangeAfterClick("Random Subtask"));
        assertFalse(dashboardPage.doesIconChangeAfterClick("A Subtask Name That Doesn't Exist"));

        assertTrue(dashboardPage.isSubtaskWithGivenNameVisible("Random Subtask"));
        assertFalse(dashboardPage.isSubtaskWithGivenNameVisible("A Subtask Name That Doesn't Exist"));
    }

    @Test(enabled = false)
        public void isProjectPersonal(){
        assertTrue(dashboardPage.isProjectPersonal("Project Moon"));
        assertFalse(dashboardPage.isProjectPersonal("Flying'' Cow"));

        assertTrue(dashboardPage.isTaskPriorityCorrect("Randomless task",0));
        assertFalse(dashboardPage.isTaskPriorityCorrect("Randomless task",1));
        assertFalse(dashboardPage.isTaskPriorityCorrect("nonexistent task",2));

        assertTrue(dashboardPage.isTimeSpentOnGivenTaskCorrect("Randomless task","0/52h"));
        assertFalse(dashboardPage.isTimeSpentOnGivenTaskCorrect("Randomless task","5/52h"));
        assertFalse(dashboardPage.isTimeSpentOnGivenTaskCorrect("nonexistent task","0/52h"));

        assertTrue(dashboardPage.isStartDateTooltipOnTasksCorrect("Randomless task","10/03/2024"));
        assertFalse(dashboardPage.isStartDateTooltipOnTasksCorrect("Randomless task","2/03/2025"));
        assertFalse(dashboardPage.isStartDateTooltipOnTasksCorrect("nonexistent task","10/03/2024"));

        assertTrue(dashboardPage.isDueDateTooltipOnTasksCorrect("Randomless task","03/07/2025 00:00"));
        assertFalse(dashboardPage.isDueDateTooltipOnTasksCorrect("Randomless task","04/07/2029 40:40"));
        assertFalse(dashboardPage.isDueDateTooltipOnTasksCorrect("nonexistent task","03/07/2025 00:00"));

        //$("div.menus-container").should(Condition.visible);
        System.out.println("Webdriver: "+WebDriverRunner.url());
        assertTrue(dashboardPage.isTaskCountCorrectForBacklog("Flying Cow",0));
        assertFalse(dashboardPage.isTaskCountCorrectForBacklog("Flying Cow",99));

        assertTrue(dashboardPage.isTaskCountCorrectForBacklog("First Example Project",2));
        assertFalse(dashboardPage.isTaskCountCorrectForBacklog("First Example Project",200));
        /*
        assertTrue(indexPage.isTaskWithGivenIdOnIndividualProjectsVisible(4));
        assertFalse(indexPage.isTaskWithGivenIdOnIndividualProjectsVisible(5));
        */
    }

    @Test(enabled = false)
    public void testNumbersOnProjectsForTaskBacklog() {
        System.out.println("Webdriver: "+WebDriverRunner.url());

        assertTrue(dashboardPage.isTaskCountCorrectForBacklog("Flying Cow",0));
        assertFalse(dashboardPage.isTaskCountCorrectForBacklog("Flying Cow",99));

        assertTrue(dashboardPage.isTaskCountCorrectForBacklog("First Example Project",2));
        assertFalse(dashboardPage.isTaskCountCorrectForBacklog("First Example Project",200));
    }
    @Test(enabled = false)
    public void newProjectTest(){
        //indexPage.cli

    }



}
