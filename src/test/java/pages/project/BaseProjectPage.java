package pages.project;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

// The menu (project-header) is the same for all pages
public class BaseProjectPage {

    // Task Number is added to Header on all Project page
    public final SelenideElement currentTaskNumberOnHeader = $("span.title span:not([class]):not([title])");
    public final SelenideElement maximumTaskNumberOnHeader = $("span.title span[title='Task limit']"); // Need to remove text part first

    // CONFIGURE PROJECT MENU
    public final SelenideElement configureProjectButton = $("div.dropdown i.fa.fa-cog:not(.fa-fw)");


    public final SelenideElement addNewTask = $("ul.dropdown-submenu-open a:has(i.fa.fa-plus)");
    public final SelenideElement activity = $("ul.dropdown-submenu-open a:has(i.fa.fa-dashboard)");
    public final SelenideElement addCustomFilter = $("ul.dropdown-submenu-open a:has(i.fa.fa-filter)");
    public final SelenideElement analytics = $("ul.dropdown-submenu-open a:has(i.fa.fa-line-chart)");
    public final SelenideElement exports = $("ul.dropdown-submenu-open a:has(i.fa.fa-upload)");
    public final SelenideElement importTasks = $("ul.dropdown-submenu-open a:has(i.fa.fa-download)");
    public final SelenideElement configureThisProject = $("ul.dropdown-submenu-open a:has(i.fa.fa-cog)");
    public final SelenideElement manageProject = $("ul.dropdown-submenu-open a:has(i.fa.fa-folder)");
    // END
    // MENU END

    public final SelenideElement overViewButton = $("i.fa.fa-eye");
    public final SelenideElement boardButton = $("i.fa.fa-th");
    public final SelenideElement listButton = $("i.fa.fa-list");

    // These elements and their functionality are the same as those in IndexPage
    // I plan to test these on only one of the pages
    public final SelenideElement searchBar = $("input#form-search");

    // sortButtonOnSearchBar MENU ELEMENTS
    public final SelenideElement defaultFilters = $("div.input-addon i.fa-filter");

    public SelenideElement resetFilters = $("div#dropdown a[data-filter='']");
    public SelenideElement myTasks = $("div#dropdown a[data-filter='status:open assignee:me']");
    public SelenideElement myTasksDueTomorrow = $("div#dropdown a[data-filter='status:open assignee:me due:tomorrow']");
    public SelenideElement tasksDueToday = $("div#dropdown a[data-filter='status:open due:today']");
    public SelenideElement tasksDueTomorrow = $("div#dropdown a[data-filter='status:open due:tomorrow']");
    public SelenideElement tasksDueYesterday = $("div#dropdown a[data-filter='status:open due:yesterday']");
    public SelenideElement closedTasks = $("div#dropdown a[data-filter='status:closed']");
    public SelenideElement openTasks = $("div#dropdown a[data-filter='status:open']");
    public SelenideElement notAssigned = $("div#dropdown a[data-filter='status:open assignee:nobody']");
    public SelenideElement assigned = $("div#dropdown a[data-filter='status:open assignee:anybody']");
    public SelenideElement noCategory = $("div#dropdown a[data-filter='status:open category:none']");
    public SelenideElement viewAdvanced = $("div#dropdown a[href*='docs.kanboard.org']");
    // MENU END

    public final SelenideElement userFilters = $("");

}
