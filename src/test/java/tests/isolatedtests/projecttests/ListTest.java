package tests.isolatedtests.projecttests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helper.HeaderHelper;
import helper.NavigationHelper;
import tests.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.project.ListPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ListTest extends BaseTest {

    ListPage listPage = new ListPage();
    LoginPage loginPage = new LoginPage();
    HeaderHelper headerHelper = new HeaderHelper();

    @BeforeMethod
    public void setUp(){
        String userName ="ListAdmin";
        String userPassword = "123456";
        loginPage.login(userName,userPassword);
        NavigationHelper.openProjectBoard("5");
        listPage.listButton.shouldBe(visible).click();
    }

    @AfterMethod
    public void logOut(){
        headerHelper.clickGivenElementOnDropdown(headerHelper.logoutOnDropdown);
    }

    @Test
    public void clickTask(){
        String taskTitle = "Something Random";
        SelenideElement element = listPage.getTaskTitle(taskTitle);
        element.click();
        $("section#task-summary h2").should(text(taskTitle)).shouldBe(visible);

    }

    @Test
    public void selectUnselectTest(){
        listPage.selectAll.shouldBe(visible).click();
        ElementsCollection allCheckBoxs = $$("div.table-list-row input[type=\"checkbox\"]");
        for(SelenideElement checkboxElement : allCheckBoxs){
            assertTrue(checkboxElement.isSelected());
        }
        listPage.unselectAll.click();
        for(SelenideElement checkboxElement : allCheckBoxs){
            assertFalse(checkboxElement.isSelected());
        }
    }

    @Test
    public void showSubtasksTest(){
        listPage.showAndHideSubtasks.shouldBe(visible).click();
        assertTrue($("div.task-list-subtasks").should(Condition.exist).exists()); // Confirm at least 1 subtask Exists.
        listPage.showAndHideSubtasks.click();
        assertFalse($("div.task-list-subtasks").shouldNot(Condition.exist).exists()); // Confirm at least 1 subtask Exists.
    }
}
