package pages.Modals;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class NewProjectModal {

    public final SelenideElement projectNameInput = $("#form-name"); // REQUIRED
    public final SelenideElement identifierInput = $("#form-identifier"); // UNIQUE
    public final SelenideElement taskLimitInput = $("#form-task_limit");

    // THESE ONLY APPEAR IF A PROJECT SELECTED FOR DUPLICATE
    public final SelenideElement duplicateSelect = $("select#form-src_project_id");
    public final SelenideElement permissions = $("input[name='projectPermissionModel']");
    public final SelenideElement customRoles = $("input[name='projectRoleModel']");
    public final SelenideElement categories = $("input[name='categoryModel']");
    public final SelenideElement tags = $("input[name='tagDuplicationModel']");
    public final SelenideElement actions = $("input[name='actionModel']");
    public final SelenideElement customFilters = $("input[name='customFilterModel']");
    public final SelenideElement metaData = $("input[name='projectMetadataModel']");
    public final SelenideElement tasks = $("input[name='projectTaskDuplicationModel']");
    // END
    public final SelenideElement submitButton = $("button[type='submit']");
    public final SelenideElement cancelButton = $("div.form-actions a");

    public final SelenideElement formError = $("ul.form-errors li"); // errors that pop up on invalid input

    public NewProjectModal() {}

    public static class Builder {
        private final String projectName;
        private String identifier;
        private String taskLimit;
        private String duplicateProject;
        private boolean setPermissions = false;
        private boolean setCustomRoles = false;
        private boolean setCategories = false;
        private boolean setTags = false;
        private boolean setActions = false;
        private boolean setCustomFilters = false;
        private boolean setMetaData = false;
        private boolean setTasks = false;

        public Builder(String projectName) {  // projectName is required
            this.projectName = projectName;
        }

        public Builder setIdentifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder setTaskLimit(String taskLimit) {
            this.taskLimit = taskLimit;
            return this;
        }

        public Builder setDuplicateProject(String duplicateProject) {
            this.duplicateProject = duplicateProject;
            return this;
        }

        public Builder includePermissions() {
            this.setPermissions = true;
            return this;
        }

        public Builder includeCustomRoles() {
            this.setCustomRoles = true;
            return this;
        }

        public Builder includeCategories() {
            this.setCategories = true;
            return this;
        }

        public Builder includeTags() {
            this.setTags = true;
            return this;
        }

        public Builder includeActions() {
            this.setActions = true;
            return this;
        }

        public Builder includeCustomFilters() {
            this.setCustomFilters = true;
            return this;
        }

        public Builder includeMetaData() {
            this.setMetaData = true;
            return this;
        }

        public Builder includeTasks() {
            this.setTasks = true;
            return this;
        }

        public NewProjectModal build() {
            NewProjectModal modal = new NewProjectModal();

            if (projectName != null) {
                modal.projectNameInput.setValue(projectName);
            }

            if (identifier != null) {
                modal.identifierInput.setValue(identifier);
            }

            if (taskLimit != null) {
                modal.taskLimitInput.setValue(taskLimit);
            }

            if (duplicateProject != null) {
                modal.duplicateSelect.selectOption(duplicateProject);
                if (setPermissions) modal.permissions.setSelected(true);
                if (setCustomRoles) modal.customRoles.setSelected(true);
                if (setCategories) modal.categories.setSelected(true);
                if (setTags) modal.tags.setSelected(true);
                if (setActions) modal.actions.setSelected(true);
                if (setCustomFilters) modal.customFilters.setSelected(true);
                if (setMetaData) modal.metaData.setSelected(true);
                if (setTasks) modal.tasks.setSelected(true);
            }

            return modal;
        }
    }
}
