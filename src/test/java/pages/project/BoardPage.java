package pages.project;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import pages.Modals.ConfirmationModal;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class BoardPage extends BaseProjectPage{

    // CONFIGURE PROJECT MENU ADDITIONS
    // BoardPage has these 3 additional menu elements
    public final SelenideElement collapseTasks = $("ul.dropdown-submenu-open a:has(i.fa-compress)");
    public final SelenideElement expandTasks = $("ul.dropdown-submenu-open a:has(i.fa-expand)");
    public final SelenideElement compactView = $("ul.dropdown-submenu-open i.fa-th"); // BUG REPORT: Icon is not inside <a> element
    public final SelenideElement expandVertically = $("ul.dropdown-submenu-open i.fa-arrow-down");
    // MENU END

    public final String taskWithId = ".draggable-item[data-task-id='%s']";
    public final String targetColumn = ".board-task-list[data-column-id='%s']";
    public final String plusSignOnColumn = "//span[@class='board-column-title']//a[text()='%s ']/ancestor::div[contains(@class, 'board-column-expanded') and contains(@class, 'board-column-expanded-header')]//i[contains(@class,'fa-plus')]";

    // COLUMN TITLE MENU
    public final String columnTitle = "//span[@class='board-column-title']//a[text()='%s ']"; // There is 1 space after column name

    public SelenideElement hideColumnButton = $("ul.dropdown-submenu-open a.board-toggle-column-view");
    public SelenideElement createTaskInBulkButton = $("ul.dropdown-submenu-open i.fa.fa-align-justify");
    public SelenideElement closeAllTasksButton = $("ul.dropdown-submenu-open i.fa.fa-close");
    // MENU END

    // SORT MENU
    public String sortButtonOnGivenColumn = "//span[@class='board-column-title']//a[text()='%s ']/ancestor::span[contains(@class, 'dropdown')]//i[@class='fa fa-sort']"; // There is 1 space after column name
    public String taskCountOnGivenColumn = "span#task-number-column-%s";
    // I will not do add all sorting elements
    // Since same images are reused for multiple sorts, I would either use href or use the text and xpath
    public SelenideElement sortTasksByIdAscending = $("ul.dropdown-submenu-open i.fa.fa-fw.fa-sort-numeric-asc");
    public SelenideElement sortTasksByIdDescending = $("ul.dropdown-submenu-open i.fa.fa-fw.fa-sort-numeric-desc");
    public SelenideElement sortTasksByAssigneeAscending = $("ul.dropdown-submenu-open i.fa.fa-fw.fa-sort-amount-asc");
    public SelenideElement sortTasksByAssigneeeDescending = $("ul.dropdown-submenu-open i.fa.fa-fw.fa-sort-amount-desc");

    // Tasks have same functionality as the ones in Dashboard page.
    // So I will refrain from adding them all and testing them a second time here.
    // I will focus on what is different, for example, Drag action.

    // ID DROPDOWN MENU
    public String idDropdownOnTask = "//strong[text()='#%s ']/parent::a/parent::div";

    public SelenideElement assignToMe = $("div#dropdown i.fa-hand-o-right");
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


    public void dragTaskToCategory(String taskId, String columnId){

        if (!taskId.matches("\\d+") || !columnId.matches("\\d+")) {
            System.out.println("There was a misinput at dragTaskToCategory! Only enter numbers!");
        }
        else{
            SelenideElement taskElement = $(String.format(taskWithId,taskId));
            SelenideElement targetColumnElement = $(String.format(targetColumn,columnId));
            if(taskElement.exists() && targetColumnElement.exists())
            {
                taskElement.shouldBe(enabled);
                actions().dragAndDrop(taskElement,targetColumnElement).perform();
            }
            else{
                // Could check which element has the problem if needed
                System.out.println("There is a problem with the given inputs!");
            }
        }
    }

    /**
     * Risky since
     * @param taskOneId the id of the first task
     * @param taskTwoId the id of the second task
     */
    public void dragTaskAboveAnotherTaskById(String taskOneId, String taskTwoId)
    {
        if (!taskOneId.matches("\\d+") || !taskTwoId.matches("\\d+")) {
            System.out.println("There was a misinput at dragTaskAboveAnotherTask! Only enter numbers!");
        }
        else{
            SelenideElement taskOne = $(String.format(taskWithId,taskOneId));
            SelenideElement taskTwo = $(String.format(taskWithId,taskTwoId));
            if(taskOne.exists() && taskTwo.exists())
            {
                actions().dragAndDrop(taskOne,taskTwo).perform();
            }
            else{
                // Could check which element has the problem if needed
                System.out.println("There is no tasks with at least one given input: " + taskOneId + " or " + taskTwoId + "!");
            }
        }
    }

    /**
     *
     * If TaskOne is above TaskTwo at the time of this action, TaskOne will be moved below TaskTwo
     * If TaskOne is below TaskTwo at the time of this action, TaskOne will be moved above TaskTwo
     * @param taskOneName the name of the first Task
     * @param taskTwoName the name of the second Task
     */
    public void dragTaskAboveAnotherTask(String taskOneName,String taskTwoName)
    {
        SelenideElement taskOne = $$("div.task-board.draggable-item").findBy(Condition.text(taskOneName));
        SelenideElement taskTwo = $$("div.task-board.draggable-item").findBy(Condition.text(taskTwoName));
            if(taskOne.exists() && taskTwo.exists())
            {
                actions().dragAndDrop(taskOne,taskTwo).perform();
            }
            else{
                System.out.println("There is no tasks with at least one given input!");
            }
    }

    public SelenideElement getTaskById(String id)
    {
        return $(String.format(taskWithId,id));
    }

    // CLICKS THE GIVEN HIDDEN COLUMN NAME'S PLUS ICON
    public void clickPlusIconForColumn(String columnName) {
        SelenideElement plusIcon = $(String.format(".board-column-title[title='%s'] .fa-plus-square", columnName));
        plusIcon.click();
    }

    // The background color changes to red in board when the task limit is exceeded
    public boolean isTaskLimitExceeded() {
        // <table> is not a visible element but is a container.
        final SelenideElement boardContainer = $("#board-container");
        int maxWaitTimeMs = 3000;
        int checkIntervalMs = 200;

        int elapsedTime = 0;
        while (elapsedTime < maxWaitTimeMs) {
            if (boardContainer.has(Condition.cssClass("board-task-list-limit"))) {
                return true;
            }
            Selenide.sleep(checkIntervalMs);
            elapsedTime += checkIntervalMs;
        }
        return false;
    }

    // To check if the tasks inside a column are ordered correctly
    // columnId: which column to check
    // firstTaskId: the id of the task which is supposed to be at top
    // lastTaskId: the id of the task which is supposed to be at bottom
    public boolean isColumnOrderCorrect(String columnId,String firstTaskName, String lastTaskName){
        String path = String.format(targetColumn,columnId);
        SelenideElement firstTask = $(path + " .task-board:first-child").shouldHave(Condition.text(firstTaskName)); // Choose the first element's header
        SelenideElement lastTask = $(path + " .task-board:last-child").shouldHave(Condition.text(lastTaskName)); // Choose the last element's header
        String firstElementName = firstTask.$("div.task-board-title").getText();
        String lastElementName = lastTask.$("div.task-board-title").getText();
        return (firstElementName.equals(firstTaskName)) && (lastElementName.equals(lastTaskName));
    }

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

    /**
     * Deletes the task with the given id.
     * Not practical for parallel programming.
     * @param taskId the id of the task that is going to be removed.
     */
    public void deleteTaskWithGivenId(String taskId){
        clickGivenLocator(String.format(idDropdownOnTask,taskId));
        removeTask.click();
        ConfirmationModal.confirmButton.click();
    }

    /**
     * Could improve performance If I add a specific column to search if needed.
     * @param taskName the name of the task that will be deleted.
     */
    public void deleteTaskByName(String taskName){
        SelenideElement taskElement = $$(".task-board").find(Condition.text(taskName)).$("a");
        taskElement.click();
        removeTask.click();
        ConfirmationModal.confirmButton.click();
    }

    /**
     *
     * @param columnIdOrName the id or name of the column that will have its tasks deleted.
     * While logic: If there is a dropdown inside column, it means there is a task,
     * so delete it. Repeat until not a single one left.
     */
    public void deleteAllTasksOnColumn(String columnIdOrName){
        // In case I forgot to use number instead of name
        String columnId = switch (columnIdOrName.toLowerCase()) {
            case "backlog" -> "1";
            case "ready" -> "2";
            case "work in progress" -> "3";
            case "done" -> "4";
            default ->
                // If input is already a number, use it directly
                    columnIdOrName;
        };

        while ($(String.format(targetColumn + " div.dropdown", columnId)).exists()) {
            SelenideElement taskDropdown = $(String.format(targetColumn + " div.dropdown", columnId));
            taskDropdown.click();
            removeTask.click();
            ConfirmationModal.confirmButton.click();
            ConfirmationModal.confirmButton.should(disappear);

        }
    }

    /**
     *
     * @param columnId The id for choosing which column's task count to find
     * 1: Backlog    2: Ready    3: Work in progress    4: Done
     * @return The number of task counts for the given column.
     */
    public int getTaskCountOnColumn(String columnId){
        return Integer.parseInt($(String.format(taskCountOnGivenColumn,columnId)).getOwnText());
    }

    /**
     *
     * @param userName The username to search on tasks.
     * @return Returns a boolean, true if it exists, false if it doesn't.
     */
    public boolean isAssigneeVisibleOnAnyTask(String userName){
        //System.out.println("*" + $("div.task-board-header span").getText() + "*");
        SelenideElement element = $$("div.task-board-header span")
                .find(Condition.text(userName.trim()));
        try{
           return element.shouldBe(visible).exists();
        }
        catch (ElementNotFound ex){
            return false;
        }
    }

    /**
     *
     * @param taskName The name of task that we want to use.
     * @return returns the selenide element's id dropdown button for the given task name.
     */
    public SelenideElement getTaskIdDropdownWithGivenUserName (String taskName){
        // NOT CONFIRMED, NEED TO TRY
        SelenideElement dropdown = $$("div.task-board-header")
                .find(Condition.have(Condition.text(taskName)))
                .$("div.dropdown a.dropdown-menu-link-icon");
        System.out.println(dropdown.getText());
        return dropdown;
    }

    /**
     * Opens the dropdown menu for the given task.
     * @param taskName Used to find the task.
     */
    public void clickTaskIdDropdownWithGivenTaskName(String taskName){
        $$("div.task-board-expanded")
                .find(Condition.have(Condition.text(taskName)))
                .$("div.dropdown a.dropdown-menu-link-icon").click();
    }

    /**
     *
     * @param taskName The task with the given name.
     * @return Returns assignee's name for the given task.
     * Since it is possible for multiple task to have same name,
     * it may not work as intended for that occasion.
     */
    public String getAssignedUserNameWithGivenTaskName(String taskName) {
        return $$("div.task-board-expanded")
                .find(Condition.have(Condition.text(taskName)))
                .$("span.task-board-assignee").getText();
    }

    /**
     *
     * @param taskName The task's name shown on task boards.
     * @return returns directly the task name element on board.
     */
    public SelenideElement getTaskAnchorElementByGivenName(String taskName){
        return $$("div.task-board-title a").find(Condition.text(taskName));
    }

    public SelenideElement getCollapsedTaskByName(String taskName){
        return $("div.task-board-collapsed a[title='" + taskName + "']");
    }

    /**
     * There may be issues if other tooltips also contain <a> elements.
     * Monitor for any problems that arise related to this behavior.
     *
     * @param taskName the name of the task whose comment icon will be clicked.
     */
    public void clickCommentsIconByTaskName(String taskName){
        $$("div.task-board").findBy(Condition.text(taskName)).$("div.task-board-icons a.js-modal-medium").click();
    }



}


