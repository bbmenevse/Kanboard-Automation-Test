package pages.Modals;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class NewTaskModal {
    public final SelenideElement alert = $("ul.form-errors"); // Same element is used for all errors, text change

    public final SelenideElement taskTitle = $("input#form-title");
    public final SelenideElement strikeThroughButton = $("i.fa.fa-strikethrough"); // Chosen only this one for text editor
    public final SelenideElement textarea = $("textarea");
    public final SelenideElement tagsArea = $("ul.select2-selection__rendered");
    public final SelenideElement colorPicker = $("#form-color_id");
    public final SelenideElement assignee = $("select#form-owner_id");
    public final SelenideElement column = $("select#form-column_id");
    public final SelenideElement priority = $("select#form-priority");
    public final SelenideElement dueDate = $("input#form-date_due");
    public final SelenideElement startDate = $("input#form-date_started");
    public final SelenideElement originalEstimate = $("input#form-time_estimated");
    public final SelenideElement timeSpent = $("input#form-time_spent");
    public final SelenideElement complexity = $("input#form-score");
    public final SelenideElement reference = $("input#form-reference");
    // THESE ONES ARE ONLY FOR CREATEING TASK,
    // ON EDIT, THESE BUTTONS DON'T APPEAR
    public final SelenideElement createAnotherTaskCheckbox = $("input[name='another_task']");
    public final SelenideElement duplicateTaskToAnotherProjectCheckbox = $("input[name='duplicate_multiple_projects']");
    /*
    selectProject only comes up if duplicate is checked. Also, Button for submit and cancel remains same so they will work on the now form.
     */
    public final SelenideElement selectProject = $("select[id=\"form-project_ids[]\"]");
    // END
    public final SelenideElement submitButton = $("button.btn");
    public final SelenideElement cancelButton = $("div.form-actions a");

    public NewTaskModal() {
    }

    public static class Builder {
        private final NewTaskModal newTaskModal;

        public Builder(String taskTitle) {
            newTaskModal = new NewTaskModal();
            newTaskModal.taskTitle.setValue(taskTitle);
        }

        public Builder setTextarea(String text) {
            newTaskModal.textarea.setValue(text);
            return this;
        }

        public Builder setTagsArea(String tags) {
            newTaskModal.tagsArea.setValue(tags);
            return this;
        }

        public Builder setColorPicker(String color) {
            newTaskModal.colorPicker.selectOptionContainingText(color);
            return this;
        }

        public Builder setAssignee(String assignee) {
            newTaskModal.assignee.selectOptionContainingText(assignee);
            return this;
        }

        public Builder setColumn(String column) {
            newTaskModal.column.selectOptionContainingText(column);
            return this;
        }

        public Builder setPriority(String priority) {
            newTaskModal.priority.selectOptionContainingText(priority);
            return this;
        }

        public Builder setDueDate(String dueDate) {
            newTaskModal.dueDate.setValue(dueDate);
            return this;
        }

        public Builder setStartDate(String startDate) {
            newTaskModal.startDate.setValue(startDate);
            return this;
        }

        public Builder setOriginalEstimate(String estimate) {
            newTaskModal.originalEstimate.setValue(estimate);
            return this;
        }

        public Builder setTimeSpent(String timeSpent) {
            newTaskModal.timeSpent.setValue(timeSpent);
            return this;
        }

        public Builder setComplexity(String complexity) {
            newTaskModal.complexity.setValue(complexity);
            return this;
        }

        public Builder setReference(String reference) {
            newTaskModal.reference.setValue(reference);
            return this;
        }

        public Builder checkCreateAnotherTask() {
            newTaskModal.createAnotherTaskCheckbox.setSelected(true);
            return this;
        }

        public Builder checkDuplicateTaskToAnotherProject() {
            newTaskModal.duplicateTaskToAnotherProjectCheckbox.setSelected(true);
            return this;
        }

        public Builder setSelectProject(String project) {
            newTaskModal.selectProject.selectOptionContainingText(project);
            return this;
        }

        public NewTaskModal build() {
            return newTaskModal;
        }
    }
}
