package tests.scenariotests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import helper.HeaderHelper;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.project.BoardPage;

import static com.codeborne.selenide.Selenide.$;

public class AuthorizationTests {
    LoginPage loginPage = new LoginPage();
    HeaderHelper headerHelper = new HeaderHelper();
    BoardPage boardPage = new BoardPage();

    /**
     * Directly go to a page without login.
     * Some pages show error on the screen. Example:  //Selenide.open("http://localhost:88/user/14/avatar");
     * Also confirm page redirects after login.
     */
    @Test
    public void authorizationTest(){

        Selenide.open("http://localhost:88/?controller=DashboardController&action=subtasks&user_id=1&page=1&order=tasks.title&direction=DESC");
        loginPage.userNameInput.setValue("Salih");
        loginPage.passwordInput.setValue("123456");
        loginPage.loginButton.click();
        $(".alert.alert-error").shouldBe(Condition.visible);
        headerHelper.logOut();

        Selenide.open("http://localhost:88/board/1");
        loginPage.userNameInput.setValue("admin");
        loginPage.passwordInput.setValue("123456");
        loginPage.loginButton.click();
        boardPage.getTaskAnchorElementByGivenName("Randomless Task").should(Condition.exist);


    }
}
