package pages.index;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MyTasksPage extends BaseIndexPage{

    // Sort Menu Elements
    // The sort button is shared across pages and is defined in BaseIndexPage.
    public final SelenideElement taskId = $("div#dropdown a[href*='order=tasks.id']");
    public final SelenideElement taskSwimlane = $("div#dropdown a[href*='order=swimlane_name']");
    public final SelenideElement taskColumn = $("div#dropdown a[href*='order=column_name']");
    public final SelenideElement taskCategory = $("div#dropdown a[href*='order=category_name']");
    public final SelenideElement taskPriority = $("div#dropdown a[href*='order=tasks.priority']");
    public final SelenideElement taskPosition = $("div#dropdown a[href*='order=tasks.position']");
    public final SelenideElement taskTitle = $("div#dropdown a[href*='order=tasks.title']");
    public final SelenideElement taskAssignee = $("div#dropdown a[href*='order=assignee_name']");
    public final SelenideElement taskDueDate = $("div#dropdown a[href*='order=tasks.date_due']");
    public final SelenideElement taskStartDate = $("div#dropdown a[href*='order=tasks.date_started']");
    public final SelenideElement taskStatus = $("div#dropdown a[href*='order=tasks.is_active']");
    public final SelenideElement taskReference = $("div#dropdown a[href*='order=tasks.reference']");
    // MENU END

    // ID Dropdown Menu Elements
    public final SelenideElement setStartDate = $("div#dropdown a[href*='/start/redirect/dashboard']"); // If start date is already selected, this option is removed from menu!
    public final SelenideElement editTask = $("div#dropdown a[href*='/edit']");
    public final SelenideElement addSubtask = $("div#dropdown a[href*='/subtask/create']");
    public final SelenideElement addInternalLink = $("div#dropdown a[href*='/internal-link/create']");
    public final SelenideElement addExternalLink = $("div#dropdown a[href*='/external-link/find']");
    public final SelenideElement addComment = $("div#dropdown a[href*='/comment/create']");
    public final SelenideElement attachDocument = $("div#dropdown a[href*='/file/create']");
    public final SelenideElement addScreenshot = $("div#dropdown a[href*='/screenshot']");
    public final SelenideElement duplicateTask = $("div#dropdown a[href*='/duplicate']");
    public final SelenideElement duplicateToProject = $("div#dropdown a[href*='/copy-to-project/']");
    public final SelenideElement moveToProject = $("div#dropdown a[href*='/move-to-project/']");
    public final SelenideElement sendByEmail = $("div#dropdown a[href*='/email/create']");
    public final SelenideElement removeTask = $("div#dropdown a[href*='/remove']");
    public final SelenideElement closeTask = $("div#dropdown a[href*='/close']");
    // MENU END

    public final ElementsCollection tasksCollection = $$("div.table-list-row");

    public void clickIdDropdownByTaskName(String taskName){
       SelenideElement taskDropdownElement = tasksCollection.findBy(Condition.text(taskName)).$("a");
       if(taskDropdownElement.exists())
       {
           taskDropdownElement.click();
       }
       else throw new NoSuchElementException("Task with the name: '" + taskName + "' could not be found!");
    }

    public void clickTaskName(String taskName){
        tasksCollection.findBy(Condition.text(taskName)).$("span.table-list-title a").click();
    }

    public int numberOfCommentsByTaskName(String taskName){
        return Integer.parseInt(tasksCollection.findBy(Condition.text(taskName))
                .$("a.js-modal-medium:has(i.fa-comments-o)").getText());
    }

    public void clickCommentsByTaskName(String taskName){
        tasksCollection.findBy(Condition.text(taskName))
                .$("a.js-modal-medium:has(i.fa-comments-o)").click();
    }

    public String getComplexityByTaskName(String taskName){
        return tasksCollection.findBy(Condition.text(taskName))
                .$("span.task-score").getOwnText();
    }

    public String getCommentNumberByTaskName(String taskName){
        try{
            return tasksCollection.findBy(Condition.text(taskName)).$("a:has(i.fa-comments-o)").shouldBe(visible, Duration.of(2, ChronoUnit.SECONDS)).getText();
        }catch (ElementNotFound e)
        {
            return "0";
        }

    }
    public boolean doesTaskExist(String taskName) {
        return tasksCollection.findBy(Condition.text(taskName)).exists();
    }
}
