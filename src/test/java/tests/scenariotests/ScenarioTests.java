package tests.scenariotests;

import com.codeborne.selenide.Condition;
import helper.HeaderHelper;
import org.testng.annotations.Test;
import pages.Modals.*;
import pages.index.MyProjectsPage;
import pages.managements.GroupManagementPage;
import pages.index.DashboardPage;
import pages.LoginPage;
import pages.managements.GroupMembersPage;
import pages.managements.UserManagementPage;
import pages.project.BoardPage;
import pages.project.projectconfiguration.EditProjectPage;
import pages.project.projectconfiguration.PermissionsPage;
import pages.project.projectconfiguration.SummaryPage;
import tests.BaseTest;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.*;

public class ScenarioTests extends BaseTest {
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();
    SummaryPage summaryPage = new SummaryPage();
    NewProjectModal newProjectModal;
    NewGroupModal newGroupModal = new NewGroupModal();
    GroupMembersPage groupMembersPage = new GroupMembersPage();
    NewUserModal newUserModal;
    AddGroupMemberModal addGroupMemberModal = new AddGroupMemberModal();
    NewTaskModal newTaskModal;
    HeaderHelper headerHelper = new HeaderHelper();
    EditProjectPage editProjectPage = new EditProjectPage();
    PermissionsPage permissionsPage = new PermissionsPage();
    UserManagementPage userManagementPage = new UserManagementPage();
    GroupManagementPage groupManagementPage = new GroupManagementPage();
    BoardPage boardPage = new BoardPage();

    /*
    /** Create a Project.
     * Create Group
     * Create Users
     * Assign the users to the group.
     * Assign the group to project.
     * Check that users can create tasks.
     * Delete users, create another user and add it to the group, confirm new user can access the project.
     * Delete users, group and project.
     *
     */
    /*
    @Test
    public void CreateUsersAndGroupAndProject(){
        String userName_one = "Shushei";
        String userName_two = "Mellisan";
        String managerName = "Xpeke";
        String projectName = "Fly on the wall";
        String groupName = "DPS";
        loginPage.loginAsAdmin();

        // Add new project: Fly on the wall
        dashboardPage.newProjectButton.click();
        newProjectModal = new NewProjectModal.Builder(projectName)
                .setIdentifier("Miley")
                .setTaskLimit("20")
                .build();
        newProjectModal.submitButton.click();
        summaryPage.clickOnMenuLinks("edit project");
        assertEquals(editProjectPage.identifierInput.getValue(), "MILEY"); // Automatically gets changed to ALL UPPERCASE
        editProjectPage.descriptionTextarea.setValue("What should I write here?");
        editProjectPage.emailInput.setValue("SomethingRandom3@gmail.com");
        editProjectPage.priorityDefaultInput.setValue("3");
        editProjectPage.saveButton.click();

        // Create user 1
        headerHelper.clickGivenElementOnDropdown(headerHelper.usersButtonOnDropdown);
        userManagementPage.newUserButton.shouldBe(visible).click();
        newUserModal = new NewUserModal.Builder(userName_one,"123456","123456")
                .setName(userName_one)
                .setRole("User")
                .build();
        newUserModal.submitButton.shouldBe(visible).click();
        // Create user 2
        userManagementPage.newUserButton.shouldBe(visible).click();
        newUserModal = new NewUserModal.Builder(userName_two,"123456","123456")
                .setName(userName_two)
                .setRole("User")
                .build();
        newUserModal.submitButton.shouldBe(visible).click();

        // Create Manager
        userManagementPage.newUserButton.shouldBe(visible).click();
        newUserModal = new NewUserModal.Builder(managerName,"123456","123456")
                .setName(managerName)
                .setRole("Manager")
                .build();
        newUserModal.submitButton.shouldBe(visible).click();
        userManagementPage.viewAllGroupsButton.shouldBe(visible).click();
        // Create Group
        groupManagementPage.newGroup.click();
        newGroupModal.input.setValue(groupName);
        newGroupModal.submitButton.click();
        // Add members to group
        groupManagementPage.getGroupNameElement(groupName).shouldBe(visible).click();
        groupMembersPage.addGroupMember.click();
        addGroupMemberModal.clickMatchingOption(userName_one,userName_one);
        addGroupMemberModal.submitButton.click();

        groupMembersPage.addGroupMember.click();
        addGroupMemberModal.clickMatchingOption(userName_two,userName_two);
        addGroupMemberModal.submitButton.click();

        groupMembersPage.addGroupMember.click();
        addGroupMemberModal.clickMatchingOption(managerName,managerName);
        addGroupMemberModal.submitButton.click();
        groupMembersPage.getMember(userName_one).should(Condition.exist);

        // Open the project's configuration and add group to the project
        headerHelper.dashboardButton.click();
        dashboardPage.clickIdDropdownByProjectName(projectName);
        dashboardPage.configureProjectLink.click();
        editProjectPage.clickOnMenuLinks("Permissions");
        permissionsPage.addGroup(groupName,"Project Member");

        // Logout from admin and login into user One
        headerHelper.logOut();
        loginPage.login(userName_one,"123456");


        // Delete the project
        headerHelper.logOut();
        loginPage.loginAsAdmin();
        headerHelper.dashboardButton.click();
        dashboardPage.clickIdDropdownByProjectName(projectName);
        dashboardPage.configureProjectLink.shouldBe(visible).click();
        editProjectPage.clickOnMenuLinks("Remove");

        ConfirmationModal.confirmButton.click();

        headerHelper.logOut();
    }
*/

    /**
     * A test to see what happens if a user that has tasks is deleted.
     * Tasks become unassigned.
     */
    @Test
    public void RemoveUserThatHasTasks()
    {
        loginPage.loginAsAdmin();

        String userName="UserWithTask";
        String taskName="Some Task";
        // Create new user
        headerHelper.clickGivenElementOnDropdown(headerHelper.usersButtonOnDropdown);
        userManagementPage.newUserButton.shouldBe(visible).click();
        newUserModal = new NewUserModal.Builder(userName,"123456","123456")
                .setName(userName)
                .setRole("User")
                .build();
        newUserModal.submitButton.shouldBe(visible).click();
        // Go to project: "Hole to another universe" and add user to this project as user
        headerHelper.dashboardButton.click();
        dashboardPage.clickProjectWithGivenTitleOnProjects("Hole to another universe");
        boardPage.configureProjectButton.shouldBe(visible).click();
        boardPage.configureThisProject.shouldBe(visible).click();
        permissionsPage.clickOnMenuLinks("Permissions");
        permissionsPage.addUser(userName,"User");
        permissionsPage.boardButton.click();
        // Create a Task and add user as assignee
        $x(String.format(boardPage.plusSignOnColumn,"Backlog")).click();
        newTaskModal = new NewTaskModal.Builder(taskName)
                .setComplexity("2")
                .setTextarea("Random explanation")
                .setAssignee(userName)
                .build();
        newTaskModal.submitButton.shouldBe(visible).click();
        assertTrue(boardPage.isAssigneeVisibleOnAnyTask(userName)); // confirm that a task with assignee: given user name exists
        // Go to user management and delete the user
        headerHelper.clickGivenElementOnDropdown(headerHelper.usersButtonOnDropdown);
        userManagementPage.clickUserIdDropdownByName(userName);
        userManagementPage.remove.shouldBe(visible,enabled).click();
        ConfirmationModal.confirmButton.click();
        // Go back to project: Hole to another universe and check assignee situation
        headerHelper.dashboardButton.shouldBe(visible).click();
        dashboardPage.clickProjectWithGivenTitleOnProjects("Hole to another universe");
        assertFalse(boardPage.isAssigneeVisibleOnAnyTask(userName)); // Confirm that since assigned user is deleted, it is now empty
        boardPage.clickTaskIdDropdownWithGivenTaskName(taskName);
        boardPage.assignToMe.click();
        assertEquals(boardPage.getAssignedUserNameWithGivenTaskName(taskName),"admin"); // Task should now have the assignee admin
        // Delete the task
        boardPage.clickTaskIdDropdownWithGivenTaskName(taskName);
        boardPage.removeTask.click();
        ConfirmationModal.confirmButton.click();
        headerHelper.logOut();
    }

}
