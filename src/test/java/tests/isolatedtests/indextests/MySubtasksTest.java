package tests.isolatedtests.indextests;

import com.codeborne.selenide.SelenideElement;
import helper.HeaderHelper;
import helper.NavigationHelper;
import org.apache.http.Header;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.index.MySubtasksPage;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MySubtasksTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    HeaderHelper headerHelper = new HeaderHelper();
    MySubtasksPage mySubtasksPage = new MySubtasksPage();

    @BeforeMethod
    public void setUp(){
        String userName = "MySubtaskTestsAdmin";
        String password = "123456";
        loginPage.login(userName,password);
        mySubtasksPage.mySubtasksButtonOnSideBar.click();
    }

    @AfterMethod
    public void logOut(){
        headerHelper.clickGivenElementOnDropdown(headerHelper.logoutOnDropdown);
    }

    @Test
    public void doesIconChangeOnSubtaskClickTest(){
       assertTrue(mySubtasksPage.doesIconsChangeAsExpected("Third Subtask"));
    }
    @Test
    public void sortMySubtasksTest(){

        SelenideElement firstSubtask = mySubtasksPage.subTasksCollection.first();
        SelenideElement lastSubtask = mySubtasksPage.subTasksCollection.last();

        // Click priority first, then id to make sure it is in correct(ascending) order.
        mySubtasksPage.sortButton.click();
        mySubtasksPage.priority.click();
        // TaskId ascending
        mySubtasksPage.sortButton.click();
        mySubtasksPage.taskId.click(); // This sort is better done in tasks sort but there is not much choice for subtasks.

        assertTrue(firstSubtask.getText().contains( "Random Subtask"));
        assertTrue(lastSubtask.getText().contains( "Just a subtask with Doesn't in it."));
        // TaskId Descending
        mySubtasksPage.sortButton.click();
        mySubtasksPage.taskId.click();

        assertTrue(firstSubtask.getText().contains( "Second Subtask"));
        assertTrue(lastSubtask.getText().contains( "Second Subtask"));
        // Title Ascending
        mySubtasksPage.sortButton.click();
        mySubtasksPage.title.click();

        assertTrue(firstSubtask.getText().contains( "Random Subtask"));
        assertTrue(lastSubtask.getText().contains( "Just a subtask with Doesn't in it."));
        // Title Descending
        mySubtasksPage.sortButton.click();
        mySubtasksPage.title.click();

        assertTrue(firstSubtask.getText().contains( "Second Subtask"));
        assertTrue(lastSubtask.getText().contains( "Second Subtask"));
    }
}
