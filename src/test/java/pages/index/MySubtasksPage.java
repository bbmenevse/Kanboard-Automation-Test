package pages.index;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class MySubtasksPage extends BaseIndexPage{

    // The sort button is shared across pages and is defined in BaseIndexPage.
    public final ElementsCollection sortsCollection = $$("div#dropdown li");
    public final ElementsCollection subTasksCollection = $$("div.task-list-subtask");
    public final SelenideElement taskId = sortsCollection.findBy(Condition.text("Task ID"));
    public final SelenideElement title = sortsCollection.findBy(Condition.text("Title"));
    public final SelenideElement priority = sortsCollection.findBy(Condition.text("Priority"));
    // MENU END

    private final ElementsCollection taskContainers =  $$("div.table-list-row");
    private final ElementsCollection subTaskContainers = $$("div.task-list-subtask");

    public void clickIdDropdownByText(String taskName){
        taskContainers.findBy(Condition.text(taskName)).$("div.dropdown").click();
    }

    public void clickTaskName(String taskName){
        taskContainers.findBy(Condition.text(taskName)).$("div.table-list-row span a").click();
    }

    public void clickSubtaskName(String subtaskName){
        subTaskContainers.findBy(Condition.text(subtaskName)).$("span.subtask-title a").click();
    }

    public void clicktimerBySubtaskName(String subtaskName){
        subTaskContainers.findBy(Condition.text(subtaskName)).$("span.subtask-timer-toggle a").click();
    }

    public boolean doesIconsChangeAsExpected(String subtaskName) {


        if(!subTaskContainers.findBy(Condition.text(subtaskName)).exists())
        {
            return false;
        }


        // Get the initial title
        String initialTitle = subTaskContainers.findBy(Condition.text(subtaskName)).$("span.subtask-title a").getAttribute("title");

        // Click the element and get the updated title
        subTaskContainers.findBy(Condition.text(subtaskName)).$("span.subtask-title a").click();
        String firstUpdatedTitle = subTaskContainers.findBy(Condition.text(subtaskName)).$("span.subtask-title a").getAttribute("title");

        // Check if the title has changed
        if (initialTitle.equals(firstUpdatedTitle)) {
            return false;
        }

        // Click again and get the second updated title
        subTaskContainers.findBy(Condition.text(subtaskName)).$("span.subtask-title a").click();
        String secondUpdatedTitle = subTaskContainers.findBy(Condition.text(subtaskName)).$("span.subtask-title a").getAttribute("title");

        // Check if the title has changed again
        if (firstUpdatedTitle.equals(secondUpdatedTitle)) {
            return false;
        }

        // Click a third time and check if the title cycles back to the initial value
        subTaskContainers.findBy(Condition.text(subtaskName)).$("span.subtask-title a").click();
        String thirdUpdatedTitle = subTaskContainers.findBy(Condition.text(subtaskName)).$("span.subtask-title a").getAttribute("title");

        if (secondUpdatedTitle.equals(thirdUpdatedTitle)) {
            return false;
        } else if (!thirdUpdatedTitle.equals(initialTitle)) {
            return false;
        }

        return true;
    }


}
