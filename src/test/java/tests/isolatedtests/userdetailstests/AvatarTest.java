package tests.isolatedtests.userdetailstests;

import helper.HeaderHelper;
import helper.NavigationHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.userdetails.AvatarPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AvatarTest {

    LoginPage loginPage=new LoginPage();
    HeaderHelper headerHelper = new HeaderHelper();
    AvatarPage avatarPage = new AvatarPage();
    @BeforeMethod
    public void login() {
        loginPage.loginAsAdmin();
        headerHelper.clickGivenElementOnDropdown(headerHelper.myProfileOnDropdown);
        avatarPage.clickLinkUsingText("Avatar");
    }

    @AfterMethod
    public void logout() {
        headerHelper.clickGivenElementOnDropdown(headerHelper.logoutOnDropdown);
    }

    /**
     * 30 mb image is not accepted by server. A file seems like created on serverside but is actually empty.
     */
    @Test()
    public void uploadTooBigImageTest(){
        avatarPage.uploadImage(AvatarPage.TestFile.BIG_IMAGE.getFile());
        assertFalse(avatarPage.doesAvatarImageExist());
    }

    @Test(priority = 2)
    public void removeImageTest()
    {
        avatarPage.clickRemoveImageButton();
        assertFalse(avatarPage.doesAvatarImageExist());
    }

    @Test(priority = 3)
    public void uploadSmallImageTest()
    {
        avatarPage.uploadImage(AvatarPage.TestFile.PROFILE_IMAGE.getFile());
        assertTrue(avatarPage.doesAvatarImageExist());
    }
    @Test
    public void uploadWrongFileTest()
    {
        avatarPage.uploadImage(AvatarPage.TestFile.EMPTY_TEXT_FILE.getFile());
        assertTrue(avatarPage.alertOnPageTopCssSelector.shouldBe(visible).exists());

    }
}
