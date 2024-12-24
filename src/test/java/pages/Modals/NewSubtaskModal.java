package pages.Modals;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class NewSubtaskModal {
    public final SelenideElement title = $("textarea#form-title");
    public final SelenideElement assignee = $("select#form-user_id");
    public final SelenideElement meButton = $("a[data-target-id=form-user_id]");
    public final SelenideElement originalEstimate = $("input#form-time_estimated");
    public final SelenideElement createAnotherTaskCheckBox = $("input[type='checkbox']");
    public final SelenideElement submitButton = $("button.btn");
    public final SelenideElement cancelButton = $("div.form-actions a");

    private NewSubtaskModal() {}

    // Method to submit the form
    public void submit() {
        submitButton.click();
    }

    // Builder inner class
    public static class Builder {
        private final NewSubtaskModal modal;

        public Builder() {
            this.modal = new NewSubtaskModal();
        }

        public Builder setTitle(String title) {
            this.modal.title.setValue(title);
            return this;
        }

        public Builder setAssignee(String assignee) {
            this.modal.assignee.selectOption(assignee);
            return this;
        }

        public Builder setOriginalEstimate(String estimate) {
            this.modal.originalEstimate.setValue(estimate);
            return this;
        }

        public Builder checkCreateAnotherTask(boolean check) {
            this.modal.createAnotherTaskCheckBox.setSelected(check);
            return this;
        }

        public NewSubtaskModal build() {
            return this.modal;
        }
    }
}
