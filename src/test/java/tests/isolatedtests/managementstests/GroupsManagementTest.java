package tests.isolatedtests.managementstests;

import helper.HeaderHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Modals.EditGroupModal;
import pages.managements.GroupManagementPage;
import pages.LoginPage;
import pages.Modals.AddGroupMemberModal;
import pages.Modals.ConfirmationModal;
import pages.Modals.NewGroupModal;
import pages.managements.GroupMembersPage;
import tests.BaseTest;

import static com.codeborne.selenide.Condition.visible;
import static org.testng.Assert.*;

public class GroupsManagementTest extends BaseTest {

    HeaderHelper headerHelper = new HeaderHelper();
    LoginPage loginPage = new LoginPage();
    GroupManagementPage groupManagementPage = new GroupManagementPage();
    NewGroupModal newGroupModal = new NewGroupModal();
    AddGroupMemberModal addGroupMemberModal = new AddGroupMemberModal();
    EditGroupModal editGroupModal = new EditGroupModal();
    GroupMembersPage groupMembersPage = new GroupMembersPage();

    String groupName = "RandomGroup"; // used in multiple method
    String newGroupName = "RandomGroup3366";

    @BeforeMethod
    public void setUp(){
        String userName = "GroupManagementTestAdmin";
        String password = "123456";
        loginPage.login(userName,password);
        headerHelper.clickGivenElementOnDropdown(headerHelper.groupsOnDropdown);
    }

    @AfterMethod
    public void logout(){
        headerHelper.logOut();
    }
    @Test
    public void jsInjectionTest()
    {
        String injectGroupName ="Something" + "<script>" +
                "alert(\"This is a warning message!\");" +
                "</script>" + "Something";
        groupManagementPage.newGroup.click();
        newGroupModal.input.setValue(injectGroupName);
        newGroupModal.submitButton.click();
        assertEquals(injectGroupName,groupManagementPage.getGroupNameElement(injectGroupName).getText());
        groupManagementPage.getIdDropdownOnGivenGroup(injectGroupName).click();
        groupManagementPage.remove.click();
        ConfirmationModal.confirmButton.click();
    }
    @Test(priority = 1)
    public void createNewGroupTest()
    {
        groupManagementPage.newGroup.click();
        newGroupModal.input.setValue(groupName);
        newGroupModal.submitButton.click();
        assertTrue(groupManagementPage.getGroupNameElement(groupName).shouldBe(visible).exists());
    }

    @Test(priority = 2)
    public void addMembersToGroupTest() {
        String user_one = "Tarık Akan";
        groupManagementPage.getIdDropdownOnGivenGroup(groupName).click();
        groupManagementPage.addGroupMembers.click();
        addGroupMemberModal.clickMatchingOption("Tar",user_one);
        addGroupMemberModal.submitButton.click();

        String user_two = "salih";
        groupManagementPage.getIdDropdownOnGivenGroup(groupName).click();
        groupManagementPage.addGroupMembers.click();
        addGroupMemberModal.clickMatchingOption("salih",user_two);
        addGroupMemberModal.submitButton.click();
    }

    @Test(priority = 3)
    public void editGroupTest(){
        groupManagementPage.getIdDropdownOnGivenGroup(groupName).click();
        groupManagementPage.edit.shouldBe(visible).click();
        assertEquals(editGroupModal.groupName.shouldBe(visible).getValue(),groupName);
        editGroupModal.groupName.clear();
        editGroupModal.groupName.setValue(newGroupName);
        editGroupModal.submitButton.click();
        assertTrue(groupManagementPage.getGroupNameElement(newGroupName).shouldBe(visible).exists());
    }

    /**
     * Check if the added group members are visible in group members page.
     */
    @Test(priority = 4)
    public void membersTest()
    {
        groupManagementPage.getIdDropdownOnGivenGroup(newGroupName).click();
        groupManagementPage.members.shouldBe(visible).click();
        groupMembersPage.getMember("Tarık Akan").shouldBe(visible);
    }
    @Test(priority = 5)
    public void deleteTest()
    {
        groupManagementPage.getIdDropdownOnGivenGroup(newGroupName).click();
        groupManagementPage.remove.shouldBe(visible).click();
        ConfirmationModal.clickConfirmButton();
        assertTrue(groupManagementPage.doesGroupExist("Healers"));
        assertFalse(groupManagementPage.doesGroupExist(newGroupName));
    }

}
