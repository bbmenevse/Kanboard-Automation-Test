package tests.isolatedtests.userdetailstests;

import com.codeborne.selenide.Condition;
import helper.HeaderHelper;
import helper.NavigationHelper;
import tests.BaseTest;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.userdetails.ChangePasswordPage;
import util.ConfigLoader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends BaseTest {
    ChangePasswordPage changePasswordPage = new ChangePasswordPage();
    LoginPage loginPage = new LoginPage();
    HeaderHelper headerHelper = new HeaderHelper();

    @BeforeMethod
    public void login(){
        loginPage.loginAsAdmin();
        NavigationHelper.checkUserDetails("2");
        changePasswordPage.clickLinkUsingText("Change password");
    }
    @AfterMethod
    public void logout(){
        headerHelper.clickGivenElementOnDropdown(headerHelper.logoutOnDropdown);
    }

    @Test
    public void tooShortPasswordTest(){
        changePasswordPage.changePassword(ConfigLoader.getProperty("admin.password"),"123","123");
        assertEquals(changePasswordPage.getErrorMessage(),"The minimum length is 6 characters");
    }
    @Test
    public void tooLongPasswordTest(){

        // Max Length for password is 72. After that, it doesn't take it into account
        assertTrue(changePasswordPage.isFirstHeaderInSidebarVisible("Password modification"));
        changePasswordPage.changePassword(ConfigLoader.getProperty("admin.password"),"999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999","999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        assertTrue(changePasswordPage.isFirstHeaderInSidebarVisible("Summary"));
    }

    @Test
    public void wrongAdminPassword()
    {
        changePasswordPage.changePassword("12345","123456","123456");
        assertEquals(changePasswordPage.getErrorMessage(),"Wrong password");
    }

    @Test
    public void TooShortAndPasswordConfirmationMismatchTest()
    {
        changePasswordPage.changePassword(ConfigLoader.getProperty("admin.password"),"123","12345");
        assertEquals(changePasswordPage.getErrorMessage(),"The minimum length is 6 characters\n" + "Passwords don't match");
    }
    @Test
    public void passwordAndConfirmationMismatchTest() {
        changePasswordPage.changePassword(ConfigLoader.getProperty("admin.password"), "1234567", "123456");
        assertEquals(changePasswordPage.getErrorMessage(),"Passwords don't match");
    }
    @Test(priority = 1)
    public void changePasswordAndRevertTest()
    {
        changePasswordPage.changePassword(ConfigLoader.getProperty("admin.password"),"888777","888777");
        headerHelper.logOut();
        loginPage.login("Salih","888777");
        headerHelper.dashboardTitle.shouldBe(Condition.visible);
        headerHelper.logOut();
        loginPage.loginAsAdmin();
        NavigationHelper.checkUserDetails("2");
        changePasswordPage.clickLinkUsingText("Change password");
        changePasswordPage.changePassword(ConfigLoader.getProperty("admin.password"),"123456","123456");
    }



}
