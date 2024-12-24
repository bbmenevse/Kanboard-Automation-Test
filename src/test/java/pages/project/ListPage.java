package pages.project;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ListPage extends BaseProjectPage{

    // CONFIGURATION DROPDOWN MENU
    // Button to click on dropdown is in BaseProjectPage
    public final SelenideElement addNewTask = $("ul.dropdown-submenu-open i.fa.fa-plus");
    public final SelenideElement activity = $("ul.dropdown-submenu-open i.fa.fa-dashboard");
    public final SelenideElement addCustomFilter = $("ul.dropdown-submenu-open i.fa.fa-filter");
    public final SelenideElement analytics = $("ul.dropdown-submenu-open i.fa.fa-line-chart");
    public final SelenideElement exports = $("ul.dropdown-submenu-open i.fa.fa-upload");
    public final SelenideElement importTasks = $("ul.dropdown-submenu-open i.fa.fa-download");
    public final SelenideElement configureThisProject = $("ul.dropdown-submenu-open i.fa.fa-cog");
    public final SelenideElement manageProject = $("ul.dropdown-submenu-open i.fa.fa-folder");
    // END

    public final SelenideElement taskNumber = $("div.table-list-header-count");
    public final SelenideElement selectAll = $("a[data-list-item-selection='all']");
    public final SelenideElement unselectAll = $("a[data-list-item-selection='none']");
    /*
    applyAction only appears when there is at least 1 selected task.
     */
    public final SelenideElement applyAction = $("div.list-item-actions a.dropdown-menu.dropdown-menu-link-icon");
    public final SelenideElement showAndHideSubtasks = $("a i.fa-fw.fa-tasks");

    public final String taskCheckBox = "input[value='%s']";
}
