package tests.isolatedtests.managementstests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import helper.HeaderHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.Modals.ConfirmationModal;
import pages.Modals.ImportUsersModal;
import pages.Modals.NewUserModal;
import pages.alerts.ConfirmationAlert;
import pages.managements.UserManagementPage;
import tests.BaseTest;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.*;


public class UserManagementTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    HeaderHelper headerHelper = new HeaderHelper();
    UserManagementPage userManagementPage = new UserManagementPage();
    NewUserModal newUserModal = new NewUserModal();
    ConfirmationAlert confirmationAlert = new ConfirmationAlert();

    @BeforeMethod
    public void setUp(){
        String userName = "UserManagementTestAdmin";
        String password = "123456";
        loginPage.login(userName,password);
        headerHelper.clickGivenElementOnDropdown(headerHelper.usersButtonOnDropdown);
    }

    @AfterMethod
    public void logout(){
        headerHelper.clickGivenElementOnDropdown(headerHelper.logoutOnDropdown);
    }

    /**
     *
     */
    @Test(priority = 1)
    public void formAlertsTest(){
        String userName = "errorUser";
        // Verify the alerts displayed when the form is submitted empty.
        userManagementPage.newUserButton.shouldBe(visible).click();
        newUserModal.submitButton.click();
        assertTrue(newUserModal.allErrors.findBy(Condition.text("The username is required")).shouldBe(visible).exists());
        assertTrue(newUserModal.allErrors.findBy(Condition.text("The password is required")).shouldBe(visible).exists());
        assertTrue(newUserModal.allErrors.findBy(Condition.text("The confirmation is required")).shouldBe(visible).exists());
        // Verify the alerts displayed when a username that already is set as value, and the form is submitted.
        newUserModal.username.shouldBe(visible).setValue("admin");
        newUserModal.submitButton.click();
        assertTrue(newUserModal.allErrors.findBy(Condition.text("This username is already taken")).shouldBe(visible).exists());
        newUserModal.username.clear();
        // Verify the alerts displayed when only the username is set, and the form is submitted.
        newUserModal.username.shouldBe(visible).setValue(userName);
        newUserModal.submitButton.click();
        assertFalse(newUserModal.allErrors.findBy(Condition.text("The username is required")).should(disappear).exists());
        assertTrue(newUserModal.allErrors.findBy(Condition.text("The password is required")).shouldBe(visible).exists());
        assertTrue(newUserModal.allErrors.findBy(Condition.text("The confirmation is required")).shouldBe(visible).exists());
        // Verify the alerts displayed when password is set below minimum length(6) and the form is submitted.
        newUserModal.password.setValue("123");
        newUserModal.submitButton.click();
        assertTrue(newUserModal.allErrors.findBy(Condition.text("The minimum length is 6 characters Passwords don't match")).shouldBe(visible).exists());
        newUserModal.password.clear();
        // Verify the alerts displayed when password is set but confirmation is not, and the form is submitted.
        newUserModal.password.setValue("123456");
        newUserModal.submitButton.click();
        assertFalse(newUserModal.allErrors.findBy(Condition.text("The password is required")).should(disappear).exists());
        assertTrue(newUserModal.allErrors.findBy(Condition.text("The confirmation is required")).shouldBe(visible).exists());
        // Verify the alerts displayed when password and confirmation are both set but don't match, and the form is submitted
        newUserModal.confirmation.setValue("12345");
        newUserModal.submitButton.click();
        assertFalse(newUserModal.allErrors.findBy(Condition.text("The confirmation is required")).should(disappear).exists());
        assertTrue(newUserModal.allErrors.findBy(Condition.text("Passwords don't match")).shouldBe(visible).exists());
        // Verify that submit works when everything is done by the books.
        newUserModal.confirmation.clear();
        newUserModal.confirmation.setValue("123456");
        newUserModal.submitButton.click();
        headerHelper.dashboardTitle.shouldBe(visible).shouldHave(Condition.text(userName));
        // Verify that user is shown on user management page and remove it.
        headerHelper.clickGivenElementOnDropdown(headerHelper.usersButtonOnDropdown);
        userManagementPage.clickUserIdDropdownByName(userName);
        userManagementPage.remove.shouldBe(visible).click();
        ConfirmationModal.clickConfirmButton();
        confirmationAlert.confirmationAlert.shouldBe(visible);
    }

    @Test(priority = 2)
    public void createUser(){
        userManagementPage.newUserButton.click();
        newUserModal = new NewUserModal.Builder("JustinTimberlake","123456","123456")
                .setName("Justin" +
                        "")
                .setEmail("JustinTimberlake@Justin" +
                        ".com")
                .build();
        newUserModal.submitButton.click();

        headerHelper.dashboardTitle.shouldHave(Condition.text("Justin" +
                ""));

    }

    @Test(priority = 3)
    public void sortUserListTest(){
        assertTrue(userManagementPage.isUserInState("Justin" +
                "",UserManagementPage.UserState.EXISTS));
        userManagementPage.sortButton.click();
        SelenideElement element = $("ul.dropdown-submenu-open li:first-child");
        while(!element.getText().contains("▲")) //▲▼

        {
            element.click();
            userManagementPage.sortButton.click();
            element = $("ul.dropdown-submenu-open li:first-child");
        }
        $("body").click();

        // USER ID DECREASING
        SelenideElement firstIdElement = $("div.table-list > div.table-list-row:nth-child(2)"); // first one is header
        SelenideElement lastIdElement = $("div.table-list > div.table-list-row:last-of-type");

        //System.out.println(firstIdElement.getText());

        assertTrue(firstIdElement.getText().contains("admin"));
        assertTrue(lastIdElement.getText().contains("JustinTimberlake@Justin" +
                ".com"));
        // USER ID INCREASING
        String formattedString = String.format("//div[@id='dropdown']//a[contains(text(),'%s')]","User ID");
        userManagementPage.clickElementOnSortMenuByGivenText("User ID");

        firstIdElement = $("div.table-list > div.table-list-row:nth-child(2)"); // first one is header
        lastIdElement = $("div.table-list > div.table-list-row:last-of-type");

        assertTrue(lastIdElement.getText().contains("admin"));
        assertTrue(firstIdElement.getText().contains("JustinTimberlake@Justin" +
                ".com"));
    }

    @Test(priority = 4)
    public void editUserTest()
    {
        userManagementPage.clickUserIdDropdownByName("Justin" +
                "");
        userManagementPage.editButton.shouldBe(visible).click();

        // Verify that the user's existing information is pre-filled in the edit modal.
        newUserModal.username.shouldHave(Condition.value("JustinTimberlake"));
        newUserModal.name.shouldHave(Condition.value("Justin" +
                ""));
        newUserModal.email.shouldHave(Condition.value("JustinTimberlake@Justin" +
                ".com"));
        newUserModal.role.shouldHave(Condition.value("User"));

        // Edit the name but cancel
        newUserModal.username.clear();
        newUserModal.username.setValue("Something Justin" +
                "");
        newUserModal.cancelButton.click();
        // Confirm that "JustinTimberlake" is kept instead of "Something Justin
        // "
        userManagementPage.clickUserIdDropdownByName("Justin" +
                "");
        userManagementPage.editButton.shouldBe(visible).click();
        newUserModal.username.shouldHave(Condition.value("JustinTimberlake"));
        // Edit
        newUserModal.username.clear();
        newUserModal.username.setValue("blue");
        newUserModal.name.clear();
        newUserModal.name.setValue("Blue");
        newUserModal.email.clear();
        newUserModal.email.setValue("blue@red.com");
        newUserModal.role.selectOption("Manager");
        newUserModal.submitButton.click();
        assertEquals(confirmationAlert.getConfirmationMessage(),"User updated successfully.");
        // Confirm new values are on edit Page
        userManagementPage.clickUserIdDropdownByName("Blue");
        userManagementPage.editButton.shouldBe(visible).click();
        newUserModal.username.shouldHave(Condition.value("blue"));
        newUserModal.name.shouldHave(Condition.value("Blue"));
        newUserModal.email.shouldHave(Condition.value("blue@red.com"));
        newUserModal.role.shouldHave(Condition.value("Manager"));
        // Revert the user to old values
        newUserModal.username.clear();
        newUserModal.username.setValue("JustinTimberlake");
        newUserModal.name.clear();
        newUserModal.name.setValue("Justin" +
                "");
        newUserModal.email.clear();
        newUserModal.email.setValue("JustinTimberlake@Justin" +
                ".com");
        newUserModal.role.selectOption("User");
        newUserModal.submitButton.click();
        assertTrue(userManagementPage.isUserInState("Justin" +
                "",UserManagementPage.UserState.EXISTS));
    }

    @Test(priority = 5)
    public void deleteUserTest()
    {
        assertTrue(userManagementPage.isUserInState("Justin" +
                "",UserManagementPage.UserState.EXISTS));
        userManagementPage.clickUserIdDropdownByName("Justin" +
                "");
        userManagementPage.remove.shouldBe(visible).click();
        ConfirmationModal.confirmButton.click();
        assertTrue(userManagementPage.isUserInState("admin",UserManagementPage.UserState.EXISTS));
        assertTrue(userManagementPage.isUserInState("Justin" +
                "",UserManagementPage.UserState.DOES_NOT_EXIST));
    }

    @Test(priority = 6)
    public void importValidUsersTest()
    {
        userManagementPage.importButton.click();
        ImportUsersModal importUsersModal = new ImportUsersModal();
        importUsersModal.importFileInput.uploadFromClasspath("valid_users.csv");
        importUsersModal.importButton.click();

        userManagementPage.isUserInState("john_doe", UserManagementPage.UserState.EXISTS);
        userManagementPage.isUserInState("jane_smith", UserManagementPage.UserState.EXISTS);
        userManagementPage.isUserInState("james_butler", UserManagementPage.UserState.EXISTS);
    }

    /**
     * A user with dual role in csv is still taken with its highest role that has true value.
     * Not sure if that counts as bug or is intended.
     */
    @Test(priority = 6)
    public void importInvalidUsersTest()
    {
        userManagementPage.importButton.click();
        ImportUsersModal importUsersModal = new ImportUsersModal();
        importUsersModal.importFileInput.uploadFromClasspath("invalid_users.csv");
        importUsersModal.importButton.click();

        assertTrue(userManagementPage.isUserInState("valid_user", UserManagementPage.UserState.DOES_NOT_EXIST));
        //userManagementPage.isUserInState("dual_roles_user", UserManagementPage.UserState.DOES_NOT_EXIST);
        assertTrue(userManagementPage.isUserInState("emily_clark", UserManagementPage.UserState.DOES_NOT_EXIST));
        assertFalse(userManagementPage.isAdminDuplicate());
    }
    @Test(priority = 7)
    public void deleteImportedUsersTest()
    {
        userManagementPage.clickUserIdDropdownByName("john_doe");
        userManagementPage.remove.click();
        ConfirmationModal.confirmButton.shouldBe(visible).click();
        userManagementPage.clickUserIdDropdownByName("jane_smith");
        userManagementPage.remove.click();
        ConfirmationModal.confirmButton.shouldBe(visible).click();
        userManagementPage.clickUserIdDropdownByName("james_butler");
        userManagementPage.remove.click();
        ConfirmationModal.confirmButton.shouldBe(visible).click();

        userManagementPage.isUserInState("john_doe", UserManagementPage.UserState.DOES_NOT_EXIST);
        userManagementPage.isUserInState("jane_smith", UserManagementPage.UserState.DOES_NOT_EXIST);
        userManagementPage.isUserInState("james_butler", UserManagementPage.UserState.DOES_NOT_EXIST);
    }

    /**
     * Not sure if this test is required but it just checks if mailto has correct addresses
     */
    @Test
    public void mailToLinkTest()
    {
        assertTrue(userManagementPage.isMailToLinkCorrect("ahmet@ahmet.com"));
    }

    @Test
    public void createLoginAndDeleteUserTest()
    {
        String userName = "createUserTest";
        String password = "123456";
        userManagementPage.newUserButton.click();
        newUserModal = new NewUserModal.Builder("createUserTest","123456","123456")
                .setName("Ferit")
                .setRole("User")
                .setEmail("something@something.com")
                .build();
        newUserModal.submitButton.click();
        headerHelper.logOut();
        loginPage.login(userName,password);
        headerHelper.dashboardButton.shouldBe(visible);
        headerHelper.logOut();

        loginPage.loginAsAdmin();
        headerHelper.dropdownMenu.click();
        headerHelper.usersButtonOnDropdown.click();
        assertTrue(userManagementPage.isUserInState(userName, UserManagementPage.UserState.EXISTS));
        userManagementPage.clickUserIdDropdownByName(userName);
        userManagementPage.remove.click();
        ConfirmationModal.confirmButton.click();
        assertTrue(userManagementPage.isUserInState(userName, UserManagementPage.UserState.DOES_NOT_EXIST));
    }

}
