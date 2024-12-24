package pages.managements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.Objects;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class UserManagementPage {

    public final SelenideElement newUserButton = $("div.page-header a:has(i.fa.fa-plus)");
    public final SelenideElement invitePeople = $("div.page-header a:has(i.fa.fa-paper-plane)");
    public final SelenideElement importButton = $("div.page-header a:has(i.fa.fa-upload)");
    public final SelenideElement viewAllGroupsButton = $("div.page-header a:has(i.fa.fa-fw.fa-users)");

    // SORT ELEMENTS
    public final SelenideElement sortButton = $("div.table-list a.dropdown-menu.dropdown-menu-link-icon");
    public final String sortButtonElementLocator = "//div[@id='dropdown']//a[contains(text(),'%s')]"; // Will use name as input for selenide element

    // ID DROPDOWN ELEMENTS
    public final String idDropdownButtonLocator = "//a[contains(@class, 'dropdown-menu-link-icon')]//strong[contains(text(), '#%s')]"; // Only strong part does the job too

    public final SelenideElement viewButton = $("div#dropdown a:has(i.fa.fa-fw.fa-user)");
    public final SelenideElement editButton = $("div#dropdown a:has(i.fa.fa-edit)");
    public final SelenideElement avatarButton = $("div#dropdown a:has(i.fa.fa-smile");
    public final SelenideElement changePasswordButton = $("div#dropdown a:has(i.fa.fa-key");
    public final SelenideElement twoFactorAuthenticationButton = $("div#dropdown a:has(i.fa.fa-shield");
    public final SelenideElement publicAccessButton = $("div#dropdown a:has(i.fa.fa-share-alt");
    public final SelenideElement notificationsButton = $("div#dropdown a:has(i.fa.fa-bell-o");
    public final SelenideElement externalAccountButton = $("div#dropdown a:has(i.fa.fa-user-circle-o");
    public final SelenideElement integrationsButton = $("div#dropdown a:has(i.fa.fa-rocket");
    public final SelenideElement apiAccessButton = $("div#dropdown a:has(i.fa.fa-cloud");
    public final SelenideElement userDashboardButton = $("div#dropdown a:has(i.fa.fa-fw.fa-dashboard");
    public final SelenideElement timeTrackingButton = $("div#dropdown a:has(i.fa.fa-clock-o");
    public final SelenideElement lastLoginsButton = $("div#dropdown a:has(i.fa.fa-id-badge");
    public final SelenideElement persistentConnectionsButton = $("div#dropdown a:has(i.fa.fa-database");
    public final SelenideElement passwordResetHistoryButton = $("div#dropdown a:has(i.fa.fa-legal)");
    public final SelenideElement disable = $("div#dropdown a:has(i.fa.fa-times)");
    public final SelenideElement remove= $("div#dropdown a:has(i.fa.fa-trash-o)");

    public void clickElementOnSortMenuByGivenText(String text){
        sortButton.click();
        SelenideElement selenideElement = $x(String.format(sortButtonElementLocator,text));
        if(selenideElement.exists())
        {
            selenideElement.click();
        }
        else {
            System.out.println("Element not found with given locator: " + String.format(sortButtonElementLocator,text));
        }
    }

    /**
     * @param userName the name of the user
     * @return ID as String for the given user name.
     */
    public String getUserIdByName(String userName) {
        SelenideElement userElement = $$("div.table-list-row")
                .findBy(Condition.text(userName));

        String userId = userElement.$("div.dropdown a.dropdown-menu")
                .getText()
                .replaceAll("[^0-9]", ""); // Remove everything except numbers

        return userId;
    }

    /**
     * This is used to click dropdown element of a specific user, with given id.
     * While this works, this is not optimal for parallel programming, as id's will get mixed.
     * @param id The ID of the user that will be clicked
     */
    public void clickIdDropdown(int id){
        // It seems the last sort type used is saved in kanboard
        // So we need to be careful about which one is the active sort
        // when using this
        SelenideElement selenideElement = $x(String.format(idDropdownButtonLocator, id));
        if (selenideElement.exists())
        {
            selenideElement.click();
        }
    }

    public void clickIdMenuElement(String elementLink, String name)
    {
        clickUserIdDropdownByName(name);
        SelenideElement selenideElement = $(elementLink);
        if(selenideElement.exists())
        {
            selenideElement.click();
        }
    }

    /**
     *
     * @param n the nth element found with the given xpath locator.
     * @return the element if it exists, null if it doesn't.
     */
    public String getNthDropdownId(int n) {
        SelenideElement selenideElement = $x("(//div[contains(@class, 'table-list-row')]//div[contains(@class, 'dropdown')]//strong)[" + n + "]");
        if(!selenideElement.exists())
        {
            return null;
        }
        return selenideElement.getText().replaceAll("#","");
    }

    /**
     *
     * @param userName the user that will be clicked.
     */
    public void clickUserIdDropdownByName(String userName) {
        // Locate the row containing the user title
        SelenideElement userElement = $$("div.table-list-row")
                .find(Condition.text(userName)); //

        // Click the dropdown button containing the #id in the found row
        userElement.$("div.dropdown a.dropdown-menu-link-icon").shouldBe(visible).click();
    }

    public enum UserState {
        EXISTS,
        DOES_NOT_EXIST
    }

    /**
     * Checks whether the user exists and verifies the expected state.
     *+
     * @param userName the name of the user to be checked.
     * @param expectedState true if the user is expected to exist or be created; false if the user is expected to be removed or does not exist.
     * @return true if the user's actual state matches the expected state.
     */
    public boolean isUserInState(String userName, UserState expectedState) {
        // Locate the row containing the user title
        SelenideElement userElement = $$("div.table-list-row")
                .find(Condition.text(userName));

        if (expectedState == UserState.EXISTS) {
            // Wait for the user element to be visible if it should exist
            return userElement.shouldBe(visible).exists();
        } else if (expectedState == UserState.DOES_NOT_EXIST) {
            // Wait for the user element to disappear if it should not exist
            userElement.should(disappear, Duration.ofSeconds(8));
            return !userElement.exists();
        }
        return false;
    }

    /**
     * Is used for checking if any duplicate admin user exists.
     * @return true if duplication exists, false otherwise.
     */
    public boolean isAdminDuplicate()
    {
        ElementsCollection collection = $$("div.table-list-row span.table-list-title")
                .filter(Condition.exactText("admin"));

         return collection.size() > 1;
    }

    public boolean isMailToLinkCorrect(String mail)
    {
        SelenideElement element = $$("div.table-list-row  div.table-list-details")
                .find(Condition.text(mail));
        return Objects.equals(element.$("a").getAttribute("href"), "mailto:" + mail);

    }


}
