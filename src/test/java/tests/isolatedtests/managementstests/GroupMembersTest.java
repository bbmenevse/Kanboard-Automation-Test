package tests.isolatedtests.managementstests;

import helper.HeaderHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.Modals.AddGroupMemberModal;
import pages.Modals.ConfirmationModal;
import pages.managements.GroupManagementPage;
import pages.managements.GroupMembersPage;
import pages.userdetails.UserSummaryPage;
import tests.BaseTest;

import static com.codeborne.selenide.Condition.visible;
import static org.testng.Assert.*;
public class GroupMembersTest extends BaseTest {

    HeaderHelper headerHelper = new HeaderHelper();
    LoginPage loginPage = new LoginPage();
    GroupManagementPage management = new GroupManagementPage();
    GroupMembersPage members = new GroupMembersPage();
    AddGroupMemberModal addGroupMemberModal = new AddGroupMemberModal();
    UserSummaryPage userSummaryPage = new UserSummaryPage();

    @BeforeMethod
    public void setUp(){

        String userName = "GroupMembersTestAdmin";
        String password = "123456";
        loginPage.login(userName,password);
        headerHelper.clickGivenElementOnDropdown(headerHelper.groupsOnDropdown);
        management.getGroupNameElement("Empty Group").shouldBe(visible).click();
    }

    @AfterMethod
    public void logout(){
        headerHelper.logOut();
    }

    @Test(priority = 1)
    public void addUsersToGroupTest()
    {
        members.addGroupMember.shouldBe(visible).click();
        addGroupMemberModal.clickMatchingOption("Ahm","Ahm");
        addGroupMemberModal.submitButton.click();

        members.addGroupMember.shouldBe(visible).click();
        addGroupMemberModal.clickMatchingOption("berKay","ber");
        addGroupMemberModal.submitButton.click();

        members.addGroupMember.shouldBe(visible).click();
        addGroupMemberModal.clickMatchingOption("Cem","Cemal");
        addGroupMemberModal.submitButton.click();

        assertTrue(members.getMember("Ahmet").shouldBe(visible).exists());
        assertTrue(members.getMember("Berkay").exists());
        assertTrue(members.getMember("Cemal").exists());
    }

    /**
     * First ordered by Status, then Name to make sure it is Descending first.
     * Asserts name and Role options
     * Something a little wierd happens on Role sort:
     * First (Descending) sort is: Cemal Ahmet Berkay
     * Second (Ascending) sort is: Ahmet Berkay Cemal
     * It is not exactly wrong as Ahmet and Berkay are both users but
     * Ahmet and Berkay change places for some reason.
     */
    @Test(priority = 2)
    public void sortMembersTest()
    {
        members.openSortAndClickOption("Status"); // To make sure
        members.openSortAndClickOption("Name");
        assertTrue(members.isSortOrderedCorrectly("Ahmet","Cemal"));
        members.openSortAndClickOption("Name");
        assertTrue(members.isSortOrderedCorrectly("Cemal","Ahmet"));
        members.openSortAndClickOption("Role");
        assertTrue(members.isSortOrderedCorrectly("Cemal","Berkay"));
        members.openSortAndClickOption("Role");
        assertTrue(members.isSortOrderedCorrectly("Ahmet","Cemal"));
    }

    /**
     * Assert that clicking view member button takes the user to Summary page
     */
    @Test(priority = 3)
    public void viewMemberButtonTest() {
        members.clickIdDropdownElement("Ahmet");
        members.viewUser.click();
        assertEquals(userSummaryPage.titleElement.getText(),"Summary");
        assertTrue(userSummaryPage.isTextInFrontOfLabelVisible("Full Name:","Ahmet"));
    }

    /**
     * Performs the same action as viewMemberButton(),
     * but username is clicked instead of the user ID followed by the view member button.
     */
    @Test(priority = 3)
    public void clickMemberNameTest() {
        members.getMember("Berkay").click();
        assertEquals(userSummaryPage.titleElement.getText(),"Summary");
        assertTrue(userSummaryPage.isTextInFrontOfLabelVisible("Full Name:","Berkay"));
    }

    /**
     * Deletes the members that were created in sortMembersTest(),
     * and confirms they no longer exist.
     */
    @Test(priority = 4)
    public void deleteMembersTest()
    {
        members.clickIdDropdownElement("Ahmet");
        members.deleteUser.shouldBe(visible).click();
        ConfirmationModal.confirmButton.click();

        members.clickIdDropdownElement("Berkay");
        members.deleteUser.shouldBe(visible).click();
        ConfirmationModal.confirmButton.click();

        members.clickIdDropdownElement("Cemal");
        members.deleteUser.shouldBe(visible).click();
        ConfirmationModal.confirmButton.click();

        assertFalse(members.getMember("Ahmet").exists());
        assertFalse(members.getMember("Berkay").exists());
        assertFalse(members.getMember("Cemal").exists());
    }


}
