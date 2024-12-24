package pages.index;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {

    // TODO
    // IF AN ELEMENT HAS MULTIPLE CLASS, CLASS OF THE GIVEN ORDER CAN CHANGE AFTER USAGE
    // THIS HAPPENS IN THE #ID IN PROJECTS, ALSO NEED TO CHECK OTHERS.
    // WILL USE CONTAINS

    // SHARED UI ELEMENTS (Overview My Projects My Tasks My Subtasks)
    public SelenideElement newProjectButton = $("div.page-header a[href*='/project/create']");
    public SelenideElement newPersonalProjectButton = $("div.page-header a[href*='/project/create/personal']");
    public SelenideElement projectManagement = $("div.page-header a[href*='/projects'] i.fa.fa-fw.fa-folder");
    public SelenideElement myActivityStream = $("div.page-header a[href*='/my-activity'] i.fa-dashboard.fa-fw.js-modal-medium");
    public SelenideElement overViewButtonOnSideBar = $("div.sidebar a[href*='/dashboard/']:not([href*='/projects']):not([href*='task'])");
    public SelenideElement myProjectsButtonOnSideBar = $("div.sidebar a[href*='/dashboard/'][href*='/projects']");
    public SelenideElement myTasksButton = $("div.sidebar a[href*='/tasks']");
    public SelenideElement mySubtasksButton = $("div.sidebar a[href*='/subtasks']");

    // <----------------------------------------- OVERVIEW ELEMENTS START ----------------------------------------->
    public SelenideElement searchBarOnSideBar = $("div.sidebar-content input#form-search");

    // sortButtonOnSearchBar MENU ELEMENTS
    public SelenideElement defaultFilters = $("div.sidebar-content div.input-addon-item div.dropdown a.dropdown-menu-link-icon");

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

    // <----------------------------------------- MY PROJECTS ELEMENTS START ----------------------------------------->
    // Sort button on (all projects) or (seperate projects) have the same css. So we will have to use nth child.

    // SortButtonOnProjects MENU ELEMENTS
    public SelenideElement sortButtonOnProjects = $("div.sidebar-content div.table-list-header-menu a.dropdown-menu");

    public SelenideElement projectIdInSortMenuOnProjects = $("div#dropdown a[href*='order=projects.id']");
    public SelenideElement projectNameInSortMenuOnProjects = $("div#dropdown a[href*='order=projects.name']");
    public SelenideElement projectStatusInSortMenuOnProjects = $("div#dropdown a[href*='order=projects.is_active']");
    public SelenideElement projectStartDateInSortMenuOnProjects = $("div#dropdown a[href*='order=projects.start_date']");
    public SelenideElement projectEndDateInSortMenuOnProjects = $("div#dropdown a[href*='order=projects.end_date']");
    public SelenideElement projectPublicInSortMenuOnProjects = $("div#dropdown a[href*='order=projects.is_public']");
    public SelenideElement projectPersonalInSortMenuOnProjects = $("div#dropdown a[href*='order=projects.is_public']");
    // MENU END

    public final String projectNameOnProjectsLocator = "//div[@class='table-list']//a[normalize-space(text())=\"%s\"]";

    // ClickableIDButtonOnProjects MENU ELEMENTS
    public final String idDropdownOnProjectsLocator = "//div[@class='dropdown']/a/strong[contains(text(), '#%s')]";

    public SelenideElement boardLink = $("div#dropdown ul.dropdown-submenu-open li a[href*='/board/']");
    public SelenideElement listingLink = $("div#dropdown ul.dropdown-submenu-open li a[href*='/list/']");
    public SelenideElement activityLink = $("div#dropdown ul.dropdown-submenu-open li a[href*='/project/'][href*='/activity']");
    public SelenideElement analyticsLink = $("div#dropdown ul.dropdown-submenu-open li a[href*='/analytics/tasks/']");
    public SelenideElement configureProjectLink = $("div#dropdown ul.dropdown-submenu-open li a[href*='/project/']:not([href*='/activity'])");
    // MENU END

    // "class='table-list-title " has space at the end, wierd.
    public final String backlogCountLocator = "//span[@class='table-list-title ']/a[text()=\"%s\"]/ancestor::div[contains(@class,'table-list-row')]//small[text()='Backlog']/preceding-sibling::strong";
    public final String readyCountLocator = "//span[@class='table-list-title ']/a[text()=\"%s\"]/ancestor::div[contains(@class,'table-list-row')]//small[text()='Ready']/preceding-sibling::strong";
    public final String workInProgressCountLocator = "//span[@class='table-list-title ']/a[text()=\"%s\"]/ancestor::div[contains(@class,'table-list-row')]//small[text()='Work in progress']/preceding-sibling::strong";
    public final String doneCountLocator = "//span[@class='table-list-title ']/a[text()=\"%s\"]/ancestor::div[contains(@class,'table-list-row')]//small[text()='Done']/preceding-sibling::strong";

    //<----------------------------------------- MY TASKS ELEMENTS START ----------------------------------------->

    public final String projectNameOnTasksLocator = ("//div[@class='page-header']//a[normalize-space(text())=\"%s\"]");

    // SortButtonOnProjects MENU ELEMENTS
    public String sortButtonOnIndividualProject = "(//a[text()=\"%s\"]/ancestor::div[@class='page-header']/following-sibling::div[@class='table-list']//div[@class='table-list-header-menu']//a[@href='#'])[1]"; // works

    public SelenideElement taskId = $("div#dropdown a[href*='order=tasks.id']");
    public SelenideElement taskSwimlane = $("div#dropdown a[href*='order=swimlane_name']");
    public SelenideElement taskColumn = $("div#dropdown a[href*='order=column_name']");
    public SelenideElement taskCategory = $("div#dropdown a[href*='order=category_name']");
    public SelenideElement taskPriority = $("div#dropdown a[href*='order=tasks.priority']");
    public SelenideElement taskPosition = $("div#dropdown a[href*='order=tasks.position']");
    public SelenideElement taskTitle = $("div#dropdown a[href*='order=tasks.title']");
    public SelenideElement taskAssignee = $("div#dropdown a[href*='order=assignee_name']");
    public SelenideElement taskDueDate = $("div#dropdown a[href*='order=tasks.date_due']");
    public SelenideElement taskStartDate = $("div#dropdown a[href*='order=tasks.date_started']");
    public SelenideElement taskStatus = $("div#dropdown a[href*='order=tasks.is_active']");
    public SelenideElement taskReference = $("div#dropdown a[href*='order=tasks.reference']");
    // MENU END

    // DropdownMenuOnIndividualProjectTasks MENU ELEMENTS
    public final String dropDownOnIndividualProjectsWithIdLocator = "//div[contains(@class, 'table-list-row') and contains(@class, 'color')]//a[strong[contains(text(), '#%s')]]";

    public SelenideElement setStartDate = $("div#dropdown a[href*='/start/redirect/dashboard']"); // If start date is already selected, this option is removed from menu!
    public SelenideElement editTask = $("div#dropdown a[href*='/edit']");
    public SelenideElement addSubtask = $("div#dropdown a[href*='/subtask/create']");
    public SelenideElement addInternalLink = $("div#dropdown a[href*='/internal-link/create']");
    public SelenideElement addExternalLink = $("div#dropdown a[href*='/external-link/find']");
    public SelenideElement addComment = $("div#dropdown a[href*='/comment/create']");
    public SelenideElement attachDocument = $("div#dropdown a[href*='/file/create']");
    public SelenideElement addScreenshot = $("div#dropdown a[href*='/screenshot']");
    public SelenideElement duplicateTask = $("div#dropdown a[href*='/duplicate']");
    public SelenideElement duplicateToProject = $("div#dropdown a[href*='/copy-to-project/']");
    public SelenideElement moveToProject = $("div#dropdown a[href*='/move-to-project/']");
    public SelenideElement sendByEmail = $("div#dropdown a[href*='/email/create']");
    public SelenideElement removeTask = $("div#dropdown a[href*='/remove']");
    public SelenideElement closeTask = $("div#dropdown a[href*='/close']");
    // MENU END

    // These could be used with id
    public final String taskWithGivenIdOnTasksCssSelector ="span.table-list-title a[href*='/task/']"; // Care, we add id to this in method!
    public final String userWithGivenIdOnTasksCssSelector = "span.task-board-change-assignee[data-url*='/\"%s\"/";
    // taskWithGivenNameLocator userWithGivenNameOnTasksLocator

    public final String taskWithGivenNameLocator = "//div[@class='table-list']//a[text()=\"%s\"]";
    public final String userWithGivenNameOnTasksLocator= "//div[@class='task-list-avatars']//span[@class='task-avatar-assignee' and contains(text(), \"%s\")]";
    public final String complexityTooltipOnTasksLocator = "//span[@class='table-list-title ']/a[text()=\"%s\"]/ancestor::div[contains(@class,'table-list-row')]//span[@class='task-score' and normalize-space()=\"%s\"]";
    public final String timeSpentTooltipOnTasksLocator = "//span[@class='table-list-title ']/a[text()=\"%s\"]/ancestor::div[contains(@class,'table-list-row')]//span[@class='task-time-estimated' and text()[normalize-space()=\"%s\"]]";
    public final String startDateTooltipOnTasksLocator = "//span[@class='table-list-title ']/a[text()=\"%s\"]/ancestor::div[contains(@class,'table-list-row')]//span[@class='task-date' and @title=\"Start date\" and text()[normalize-space()=\"%s\"]]";
    public final String dueDateTooltipOnTasksLocator = "//span[@class='table-list-title ']/a[text()=\"%s\"]/ancestor::div[contains(@class,'table-list-row')]//span[@title=\"Due date\" and text()[normalize-space()=\"%s\"]]";
    public final String commentsTooltipOnTasksLocator = "//span[@class='table-list-title ']/a[text()=\"%s\"]/ancestor::div[contains(@class,'table-list-row')]//a[@class='js-modal-medium' and contains(@title,'comment')]"; // 1 comment : if 2+ comments exist, title changes to 'x comments'
    public final String taskPriorityOnTasks = "//span[@class='table-list-title ']/a[text()=\"%s\"]/ancestor::div[contains(@class,'table-list-row')]//span[@class='task-priority' and text()=\"%s\"]";

    public final String subtaskNameLocator = "//span[@class='subtask-title']/a[contains(text(), \"%s\")]";
    // playOrPauseButtonOnSubTasks finds using Subtask name so if there are multiple subtasks with same name, this will not be enough to distinct between them.
    // ALSO Kanboard allows users to create tasks with the same name in the same project and I find this wierd.
    public final String playOrPauseButtonOnSubTasksLocator = "//span[@class='subtask-title']/a[text()=\"%s\"]/ancestor::span[@class='subtask-cell column-50']/following-sibling::span[contains(@class,'subtask-time-tracking-cell')]//a[@class='js-subtask-toggle-timer']"; // TODO CHECK
    public final String taskComplationPercentageImageLocator ="//span[contains(@class,'table-list-title')]/a[text()=\"%s\"]/ancestor::div[contains(@class,'table-list-row')]//span[contains(@class,'tooltip') and contains(@data-href,'subtasks')]";

    // <----------------------------------------- VISIBILITY CHECK    ----------------------------------------->

    // <----------------------------------------- OVERVIEW ELEMENTS START ----------------------------------------->

    // sortButtonOnSearchBar MENU ELEMENTS
    public boolean areDropdownFiltersOnSearchBarVisible() {
        defaultFilters.click();
        return resetFilters.isDisplayed() &&
                myTasks.isDisplayed() &&
                myTasksDueTomorrow.isDisplayed() &&
                tasksDueToday.isDisplayed() &&
                tasksDueTomorrow.isDisplayed() &&
                tasksDueYesterday.isDisplayed() &&
                closedTasks.isDisplayed() &&
                openTasks.isDisplayed() &&
                notAssigned.isDisplayed() &&
                assigned.isDisplayed() &&
                noCategory.isDisplayed() &&
                viewAdvanced.isDisplayed();
    }

    // <----------------------------------------- MY PROJECTS ELEMENTS START ----------------------------------------->


    public boolean isProjectWithGivenStringVisible(String name) {
        SelenideElement link = $(byText(name));
        return link.exists() && link.isDisplayed();
    }

    // SortButtonOnProjects MENU ELEMENTS
    public boolean areSortButtonMenuOnProjectsElementsVisible() {
        if(clickSortButtonOnProjects()) {
            return projectIdInSortMenuOnProjects.isDisplayed() &&
                    projectNameInSortMenuOnProjects.isDisplayed() &&
                    projectStatusInSortMenuOnProjects.isDisplayed() &&
                    projectStartDateInSortMenuOnProjects.isDisplayed() &&
                    projectEndDateInSortMenuOnProjects.isDisplayed() &&
                    projectPublicInSortMenuOnProjects.isDisplayed() &&
                    projectPersonalInSortMenuOnProjects.isDisplayed();
        }
        return false;
    }


    public boolean isProjectPersonal(String projectTitle) {
        String locator = String.format("//span[@class='table-list-title ']/a[text()=\"%s\"]/ancestor::div[contains(@class,'table-list-row')]//i[contains(@class, 'fa-lock')]", projectTitle);
        SelenideElement lockIcon = $x(locator);
        return lockIcon.exists() && lockIcon.isDisplayed();
    }

    // Checks if the task counts are correctly handled
    public boolean isTaskCountCorrectForBacklog(String projectTitle, int expectedBacklogCount) {
        SelenideElement backlogCountElement = $x(String.format(backlogCountLocator, projectTitle));
        if(!backlogCountElement.exists())
        {
            return false;
        }
        String backlogCountText = backlogCountElement.getText().replaceAll("\\D", ""); // Remove anything but numbers
        return Integer.parseInt(backlogCountText) == expectedBacklogCount;
    }

    public boolean isTaskCountCorrectForReady(String projectTitle, int expectedReadyCount) {
        SelenideElement readyCountElement = $x(String.format(readyCountLocator, projectTitle));
        if(!readyCountElement.exists())
        {
            return false;
        }
        String readyCountText = readyCountElement.getText().replaceAll("\\D", "");
        return Integer.parseInt(readyCountText) == expectedReadyCount;
    }

    public boolean isTaskCountCorrectForWorkInProgress(String projectTitle, int expectedWIPCount) {
        SelenideElement wipCountElement = $x(String.format(workInProgressCountLocator, projectTitle));
        if(!wipCountElement.exists())
        {
            return false;
        }
        String wipCountText = wipCountElement.getText().replaceAll("\\D", "");
        return Integer.parseInt(wipCountText) == expectedWIPCount;
    }

    public boolean isTaskCountCorrectForDone(String projectTitle, int expectedDoneCount) {
        SelenideElement doneCountElement = $x(String.format(doneCountLocator, projectTitle));
        if(!doneCountElement.exists())
        {
            return false;
        }
        String doneCountText = doneCountElement.getText().replaceAll("\\D", "");
        return Integer.parseInt(doneCountText) == expectedDoneCount;
    }

    //<----------------------------------------- MY TASKS ELEMENTS START ----------------------------------------->

    public boolean isTaskWithGivenIdOnTasksVisible(String id)
    {
        return $(taskWithGivenIdOnTasksCssSelector +id).isDisplayed();
    }

    public boolean isUserNameAndAvatarOnGivenTaskVisible(String id)
    {
        return $(userWithGivenIdOnTasksCssSelector +id+"/edit']").isDisplayed();
    }

    public boolean isSortButtonOnIndividualProjectsMenuVisible(String name)
    {
        // TODO
        // FIX
        return $x(String.format(sortButtonOnIndividualProject,name)).isDisplayed();
    }

    // SortButtonOnTasks MENU ELEMENTS
    public boolean isTaskSortMenuElementsVisible(String name) {
        if(!isSortButtonOnIndividualProjectsMenuVisible(name))
        {
            return false;
        }
        clickIndividualProjectSortWithGivenName(name);
        return taskId.isDisplayed()&&
                taskSwimlane.isDisplayed()&&
                taskColumn.isDisplayed()&&
                taskCategory.isDisplayed()&&
                taskPriority.isDisplayed()&&
                taskPosition.isDisplayed()&&
                taskTitle.isDisplayed()&&
                taskAssignee.isDisplayed()&&
                taskDueDate.isDisplayed()&&
                taskStartDate.isDisplayed()&&
                taskStatus.isDisplayed()&&
                taskReference.isDisplayed();
    }
    // MENU END

    // TaskWithGivenID MENU
    public boolean isTaskWithGivenNameMenuElementsVisible(int id) {
        if(!isTaskWithGivenIdOnIndividualProjectsVisible(id))
        {
            return false;
        }
        clickTaskWithGivenIdOnIndividualProjectsVisible(id);
        return  editTask.isDisplayed()&&
                // This element may or may not be there
                // If the start date was already set, this option disappears
                // $(startDateCssSelector).isDisplayed()&&
                addSubtask.isDisplayed()&&
                addInternalLink.isDisplayed()&&
                addExternalLink.isDisplayed()&&
                addComment.isDisplayed()&&
                attachDocument.isDisplayed()&&
                addScreenshot.isDisplayed()&&
                duplicateTask.isDisplayed()&&
                duplicateToProject.isDisplayed()&&
                moveToProject.isDisplayed()&&
                sendByEmail.isDisplayed()&&
                removeTask.isDisplayed()&&
                closeTask.isDisplayed();
    }
    // MENU END

    public boolean isTaskWithGivenNameVisible(String name){
        return $x(String.format(taskWithGivenNameLocator,name)).isDisplayed();
    }
    public boolean isUserWithGivenNameOnTasksVisible(String name){
        return $x(String.format(userWithGivenNameOnTasksLocator,name)).isDisplayed();
    }

    // Works!
    public boolean isTaskWithGivenIdOnIndividualProjectsVisible(int id) {
        SelenideElement element = $x(String.format(dropDownOnIndividualProjectsWithIdLocator,id));
        return element.exists() && element.isDisplayed();
    }

    // CHECKS IF THE GIVEN TASK HAS THE GIVEN COMPLEXITY SCORE
    public boolean isTaskComplexityNumberCorrectForProject(String taskTitle, String expectedScore) {
        SelenideElement element = $x(String.format(complexityTooltipOnTasksLocator, taskTitle, expectedScore));
        return element.exists() && element.isDisplayed();
    }
    // CHECKS IF THE DESCRIPTION ON FILE IMAGE IS MATCHING THE GIVEN TEXT
    public boolean isDescriptionOnHoverCorrect(String tooltipText) {

            SelenideElement hoverElement = $("span.tooltip i.fa.fa-file-text-o");
            SelenideElement tooltipContainer = $("#tooltip-container");
            actions().moveToElement(hoverElement).perform();
            return tooltipContainer.exists() && tooltipContainer.should(Condition.visible).getText().contains(tooltipText);

    }

    // Checks if the given Task title has the given Due Time
    public boolean isTimeSpentOnGivenTaskCorrect(String taskTitle, String dueTime) {
        SelenideElement taskTimeElement = $x(String.format(timeSpentTooltipOnTasksLocator, taskTitle, dueTime));
        if(!taskTimeElement.exists()) {
                return false;
        }
        String fullText = taskTimeElement.getText().trim();

        // There are multiple text in the given element. First part is : "Time spent and Estimated"
        // After next line, actual value is given so we split the text and take that part
        String[] splitText = fullText.split("\\R"); // Splits by line break (newlines)
        String actualTime = splitText[splitText.length - 1].trim();
        //System.out.println("Actual Time: "+actualTime);
        return actualTime.equals(dueTime);
    }

    public boolean isStartDateTooltipOnTasksCorrect(String taskTitle, String startTime) {
        SelenideElement taskTimeElement = $x(String.format(startDateTooltipOnTasksLocator, taskTitle, startTime));
        return taskTimeElement.exists();
    }

    public boolean isDueDateTooltipOnTasksCorrect(String taskTitle, String dueTime) {
        SelenideElement taskTimeElement = $x(String.format(dueDateTooltipOnTasksLocator, taskTitle, dueTime));
        return taskTimeElement.exists();
    }

    // This Tooltip is only visible if there are Subtasks on a task.
    // Shows percentage of the task's progress
    public boolean isPopupOnhoverOverSubtasksTooltipByTaskTitleVisible(String taskTitle) {
        SelenideElement tooltip = $(String.format(taskComplationPercentageImageLocator, taskTitle));
        if(!tooltip.exists())
        {
            return false;
        }
        tooltip.hover();
        SelenideElement popUp = $("div#tooltip-container table.table-small th");
        return popUp.exists() && popUp.has(Condition.text("Subtask"));
    }

    public boolean isTaskPriorityCorrect(String taskName, int priorityNumber) {
        String priorityValue = "P" + priorityNumber; // Could also take directly as String
        String xpath = String.format(taskPriorityOnTasks, taskName, priorityValue);
        SelenideElement taskPriorityElement = $(By.xpath(xpath));
        if (!taskPriorityElement.exists())
        {
            return false;
        }

        // Check if the element exists and is visible
        return taskPriorityElement.isDisplayed();
    }

    //<----------------------------------------- MY SUBTASKS ELEMENTS START ----------------------------------------->

    //
    // CHECKS IF THE SUBTASK EXISTS/VISIBLE
    public boolean isSubtaskWithGivenNameVisible(String name) {
        String dynamicXpath = String.format(subtaskNameLocator, name);
        return $x(dynamicXpath).exists() && $x(dynamicXpath).isDisplayed();
    }

    public boolean doesIconChangeAfterClick(String subtaskName) {

        String dynamicXpath = String.format("//span[@class='subtask-title']/a[contains(text(), \"%s\")]", subtaskName);
        SelenideElement iconBefore = $x(dynamicXpath + "/i");

        // Return false if the element is not found
        if (!iconBefore.exists()) {
            return false;
        }

        String iconClassBefore = iconBefore.getAttribute("class");

        // Click the subtask link
        $x(dynamicXpath).click();

        SelenideElement iconAfter = $x(dynamicXpath + "/i");

        // Wait for the icon class to change after the click
        iconAfter.shouldNotHave(Condition.attribute("class", iconClassBefore));

        String iconClassAfter = iconAfter.getAttribute("class");

        return !iconClassBefore.equals(iconClassAfter);
    }


    // ACTIVE SIDEBAR ELEMENT CONTROL
    public boolean isElementActive(SelenideElement element){
        return element.parent().has(cssClass("active"));
    }

    
    //<------------------------------------ CLICKABLES ------------------------------------>


    // <------------------ BEFORE MY PROJECTS STARTS ------------------>

    // MENU END

    // <------------------ MY PROJECTS ------------------>
    public boolean clickSortButtonOnProjects(){
        if(sortButtonOnProjects.exists())
        {
            sortButtonOnProjects.click();
            return true;
        }
        else {
            return false;
        }
    }

    // ID DROPDOWN MENU ON PROJECTS
    public boolean clickProjectWithGivenNumber(int givenNumber) {
        SelenideElement projectNumberElement = $x(String.format(idDropdownOnProjectsLocator, givenNumber));
        if (projectNumberElement.exists() && projectNumberElement.isDisplayed()) {
            projectNumberElement.click();
            return true;
        }
        else {
            System.out.println("Project with given number #" + givenNumber + " was not found!");
            return false;
            // throw new NoSuchElementException("Project with given number #" + givenNumber + " was not found!");
        }
    }

    public void clickProjectWithGivenTitleOnProjects(String name)
    {
        $x(String.format(projectNameOnProjectsLocator,name)).shouldBe(visible).click();
    }

    // <------------------ MY TASKS ------------------>

    public void clickProjectNameOnTasksWithGivenTitle(String name)
    {
        $x(String.format(projectNameOnTasksLocator,name)).click();
    }

    // SortOnTasks MENU ELEMENTS
    public void clickIndividualProjectSortWithGivenName(String name)
    {
        //Click the menu opener
        $x(String.format(sortButtonOnIndividualProject,name)).shouldBe(visible).click();
    }

    // MENU END

    // TaskWithGivenID MENU
    public void clickTaskWithGivenIdOnIndividualProjectsVisible(Integer id) {
        $x(String.format(dropDownOnIndividualProjectsWithIdLocator,id)).click();
    }

    // CLICK ON GIVEN SUBTASK WITH GIVEN NAME
    // CHANGES THE IMAGE
    public void clickSubtaskWithGivenName(String name)
    {
        String dynamicXpath = String.format(subtaskNameLocator, name);
        $x(dynamicXpath).click();
    }

    // GENERAL PURPOSED METHODS
    public void clickGivenLocator(String locator, String... variables) {
        try {
            String formattedLocator = variables.length > 0 ? String.format(locator, (Object[]) variables) : locator;
            SelenideElement element;
            if (formattedLocator.startsWith("//")) {
                element = $x(formattedLocator);
            } else {
                element = $(formattedLocator);
            }
            if (element.exists()) {
                element.click();
            } else {
                System.out.println("Element with locator: " + formattedLocator + " not found.");
            }
        } catch (IllegalArgumentException e) {
            // Handle invalid locator format or any String.format-related issues
            System.out.println("Invalid locator format: " + locator + ". Error: " + e.getMessage());
        } catch (Exception e) {
            // Catch any other exceptions, including issues during click or element interaction
            System.out.println("An error occurred while attempting to click the element: " + e.getMessage());
        }
    }

    public boolean isGivenLocatorVisible(String locator, String... variables){
        try {
            // Format locator with variables, if provided
            String formattedLocator = variables.length > 0 ? String.format(locator, (Object[]) variables) : locator;

            // Check if it's an XPath or CSS selector based on the starting characters
            SelenideElement element;
            if (formattedLocator.startsWith("//")) {
                element = $x(formattedLocator);
            } else {
                System.out.println("Formatted locator: " + formattedLocator);
                element = $(formattedLocator);
            }

            if (element.exists()) {
                return true;
            } else {
                System.out.println("Element with locator: " + formattedLocator + " not found.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid locator format: " + locator + ". Error: " + e.getMessage());
        } catch (Exception e) {
            // Catch any other exceptions, including issues during click or element interaction
            System.out.println("An error occurred while attempting to click the element: " + e.getMessage());
        }
        return false;
    }


    ////////////////////////////////////////// I MAY CHANGE ALL OF DASHBOARD PAGE AS I KNOW A LOT BETTER NOW //////////////////////////////////
    // THIS IS A NEW
    public void clickIdDropdownByProjectName(String projectName)
    {
        // div.table-list-row.table-border-left
        SelenideElement projectElement = $$("div.table-list-row.table-border-left").find(text(projectName));
        projectElement.$("div.dropdown a").click();

    }

}
