package tests.isolatedtests.userdetailstests;

import helper.HeaderHelper;
import helper.NavigationHelper;
import tests.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.userdetails.UserSummaryPage;

import static org.testng.Assert.assertTrue;

public class UserSummaryTest extends BaseTest {

    LoginPage loginPage=new LoginPage();
    HeaderHelper headerHelper = new HeaderHelper();

    UserSummaryPage userSummaryPage = new UserSummaryPage();

    @BeforeClass
    public void login() {
        loginPage.loginAsAdmin();
        headerHelper.clickGivenElementOnDropdown(headerHelper.myProfileOnDropdown);
        userSummaryPage.clickLinkUsingText("Summary"); // Not really necessary, It directs to Summary page anyway.
    }
    @Test
    public void informationTest(){
        assertTrue(userSummaryPage.isTextInFrontOfLabelVisible("Login:","admin"));
        assertTrue(userSummaryPage.isTextInFrontOfLabelVisible("Status:","Active"));
        assertTrue(userSummaryPage.isTextInFrontOfLabelVisible("Email:","None"));
        assertTrue(userSummaryPage.isTextInFrontOfLabelVisible("Role:","Administrator"));
        assertTrue(userSummaryPage.isTextInFrontOfLabelVisible("Theme:","Dark theme"));
    }
}
