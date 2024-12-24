package pages.index;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BaseIndexPage {


    // SHARED UI ELEMENTS (Overview My Projects My Tasks My Subtasks)
    public final SelenideElement newProjectButton = $("div.page-header a[href*='/project/create']");
    public final SelenideElement newPersonalProjectButton = $("div.page-header a[href*='/project/create/personal']");
    public final SelenideElement projectManagement = $("div.page-header a[href*='/projects'] i.fa.fa-fw.fa-folder");
    public final SelenideElement myActivityStream = $("div.page-header a[href*='/my-activity'] i.fa-dashboard.fa-fw.js-modal-medium");
    public final SelenideElement overViewButtonOnSideBar = $("div.sidebar a[href*='/dashboard/']:not([href*='/projects']):not([href*='task'])");
    public final SelenideElement myProjectsButtonOnSideBar = $("div.sidebar a[href*='/dashboard/'][href*='/projects']");
    public final SelenideElement myTasksButtonOnSideBar = $("div.sidebar a[href*='/tasks']");
    public final SelenideElement mySubtasksButtonOnSideBar = $("div.sidebar a[href*='/subtasks']");
    private final ElementsCollection projectsCollection = $$("div.table-list div.table-list-row");

    public final SelenideElement sortButton = $("div.sidebar-content div.table-list-header a.dropdown-menu-link-icon");

    /*
    The row locator is the same for tasks, subtasks, and projects.
    However, separate collections like tasksCollection and subtasksCollection are defined for better clarity and recognition.
    This makes the code more readable and avoids confusion when accessing elements specific to each context.
    */
    protected final ElementsCollection rowElementsCollection = $$("div.table-list-row");


    public int getCountOnPageHeader(){
        String countText = $("div.sidebar-content div.page-header h2").getOwnText();
        String numberText = countText.replaceAll("[^0-9]", "");
        return Integer.parseInt(numberText);
    }

}
