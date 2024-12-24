package pages.project.projectconfiguration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/*
When a user is added to the project individually and later included in a group, the role assigned to the user directly
takes precedence over the role assigned through the group. Gonna assume this is intentional. Could also be that
higher role is picked for the said user. Also, same user in 2 different groups with different roles should be checked.
*/
public class PermissionsPage extends BaseProjectConfigurationPage{
    // USER
    public SelenideElement userNameInput = $("input#form-name[placeholder='Enter user name...']");
    public SelenideElement firstRoleSelect = $$("select#form-role").get(0); // Exact copy of group role element
    public final ElementsCollection dropdownUserSuggestions = $$("ul.ui-autocomplete li"); // all elements on drop list
    public SelenideElement addUserButton = $$("button.btn").get(0);
    //GROUP
    public SelenideElement groupNameInput = $("input#form-name[placeholder='Enter group name...']");
    public SelenideElement SecondRoleSelect = $$("select#form-role").get(1); // Exact copy of group role element
    public final ElementsCollection dropdownGroupSuggestions = $$("ul.ui-autocomplete li"); // all elements on drop list
    public SelenideElement addGroupButton = $$("button.btn").get(1);
    public void clickRemoveForUser(String username) {
        // Locate the row containing the username
        SelenideElement userRow = $$("tr")
                .find(Condition.text(username));

        // Find the "Remove" link within that row and click it
        userRow.$("a[href*='removeUser']").click();
    }

    /**
     * Enter a name, select from suggestions, and set role.
     * @param userName The user name to search and select.
     * @param role The role to select (e.g., "Project Manager").
     */
    public void addUser(String userName, String role) {
        userNameInput.setValue(userName);
        dropdownUserSuggestions.find(Condition.matchText(".*" + userName + ".*")).shouldBe(Condition.visible).click();
        firstRoleSelect.selectOption(1);
        addUserButton.shouldBe(Condition.enabled);
        addUserButton.click();
    }

    /**
     * Enter a name, select from suggestions, and set role.
     * @param groupName The group name to search and select.
     * @param role The role to select (e.g., "Project Manager").
     */
    public void addGroup(String groupName, String role) {
        groupNameInput.setValue(groupName);
        dropdownGroupSuggestions.find(Condition.matchText(".*" + groupName + ".*"))
                .shouldBe(Condition.visible).click();
        // Select the role dynamically by its text
        SecondRoleSelect.selectOption(role);
        addGroupButton.shouldBe(Condition.enabled).click();
    }



}
