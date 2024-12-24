package helper;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HeaderHelper {

    // HEADER IS MOSTLY SAME FOR ALL PAGES
    // SMALL DIFFERENCES DOES EXIST THOUGH
    public final SelenideElement dashboardButton = $("header a[href='/dashboard']");
    public final SelenideElement dashboardTitle = $("header div.title-container span.title");
    public final SelenideElement searchInput = $("header div.select-dropdown-input-container input.select-dropdown-input");
    public final SelenideElement firstOptionInSearchDropdown = $("ul#select-dropdown-menu li.select-dropdown-menu-item:first-child");
    public final SelenideElement notifications = $("header a[href*='/user/'][href*='/notifications/web']");
    public final SelenideElement plusSignDropdown= $("header a.dropdown-menu-link-icon.dropdown-menu");
    public final SelenideElement dropdownMenu= $("header div.dropdown:not(.header-creation-menu) a.dropdown-menu-link-icon.dropdown-menu");
    public final SelenideElement newProjectDropdown = $("div#dropdown a[href*='/project/create']");
    public final SelenideElement newPersonalProjectDropdown = $("div#dropdown a[href*='/project/create/personal']");

    // DROPDOWN MENU ELEMENTS
    // As the way Kanboard works, If an invalid character is put into the url instead of User ID,
    // Kanboard automatically rotates url to User's ID. Example: dashboard/xyz = dashboard/1 for user with ID=1
    public final SelenideElement userNameOnTop = $("ul.dropdown-submenu-open li.no-hover strong");
    public final SelenideElement myDashboardOnDropdown = $("ul.dropdown-submenu-open a[href*='/dashboard/']");
    public final SelenideElement myProfileOnDropdown = $("ul.dropdown-submenu-open a[href*='/user/show/']");
    public final SelenideElement projectsOnDropdown = $("ul.dropdown-submenu-open a[href*='/projects']");
    public final SelenideElement usersButtonOnDropdown = $("ul.dropdown-submenu-open a[href*='/users']");
    public final SelenideElement groupsOnDropdown = $("ul.dropdown-submenu-open a[href*='/groups']");
    public final SelenideElement extensionsOnDropdown = $("ul.dropdown-submenu-open a[href*='/extensions']");
    public final SelenideElement settingsOnDropdown= $("ul.dropdown-submenu-open a[href*='/settings']");
    public final SelenideElement documentationOnDropdown = $("ul.dropdown-submenu-open a[href*='https://docs.kanboard.org/v1/user/']");
    public final SelenideElement logoutOnDropdown = $("ul.dropdown-submenu-open a[href*='/logout']");

    // CLICK ELEMENT METHODS
    public void clickDropdownMenu() {
        dropdownMenu.shouldBe(visible).click();
    }

    public void putInputInSearchBar(String input)
    {
        searchInput.click();
        searchInput.sendKeys(input);
        firstOptionInSearchDropdown.should(visible).click();
    }

    public void clickGivenElementOnDropdown (SelenideElement element){
        clickDropdownMenu();
        element.click();
    }

    public void logOut()
    {
        clickDropdownMenu();
        logoutOnDropdown.shouldBe(visible).click();
    }

    public boolean isGivenHeaderElementVisible(SelenideElement elementLink){
        return elementLink.isDisplayed();
    }

    // DROPDOWN MENU ELEMENTS
    public boolean areDropdownMenuItemsVisible() {
        clickDropdownMenu();
        return userNameOnTop.isDisplayed() &&
                myDashboardOnDropdown.isDisplayed() &&
                myProfileOnDropdown.isDisplayed() &&
                projectsOnDropdown.isDisplayed() &&
                usersButtonOnDropdown.isDisplayed() &&
                groupsOnDropdown.isDisplayed() &&
                extensionsOnDropdown.isDisplayed() &&
                settingsOnDropdown.isDisplayed() &&
                documentationOnDropdown.isDisplayed() &&
                logoutOnDropdown.isDisplayed();
    }
}