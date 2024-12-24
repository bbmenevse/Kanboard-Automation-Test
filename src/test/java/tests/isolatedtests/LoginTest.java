package tests.isolatedtests;
import driver.BaseDriver;
import helper.HeaderHelper;
import pages.LoginPage;
import org.testng.annotations.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;

public class LoginTest {

    BaseDriver baseDriver = new BaseDriver();
    LoginPage loginPage = new LoginPage();
    HeaderHelper headerHelper = new HeaderHelper();

    @BeforeClass
    public void setUp()
    {
        baseDriver.setUp();
    }

    @AfterClass
    public void tearDown()
    {
        baseDriver.tearDown();
    }

    @BeforeMethod
    public void openLoginPage()
    {
        open("http://localhost:88/login");
    }

    @Test
    public void UIVisibilityTest()
    {
        open("http://localhost:88/login");
        assertTrue(loginPage.userNameLabel.isDisplayed());
        assertTrue(loginPage.passWordLabel.isDisplayed());
    }
    @Test
    public void testInvalidLoginTest() {
        loginPage.invalidLoginAttempt();
        $(".alert-error").shouldBe(visible);
    }
    @Test
    public void testValidLoginTest() {
        loginPage.loginAsManager();
        assertTrue($x("//a[contains(@href, '/dashboard') and contains(@href, 'projects')]").isDisplayed());
        headerHelper.clickGivenElementOnDropdown(headerHelper.logoutOnDropdown);
    }
    @Test
    public void sqlInjectionTest(){
        loginPage.login("\" or \"\"=\"","\" or \"\"=\"");
        assertTrue(loginPage.alertMessage.isDisplayed());
    }




}
