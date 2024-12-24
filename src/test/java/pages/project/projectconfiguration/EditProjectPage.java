package pages.project.projectconfiguration;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class EditProjectPage extends BaseProjectConfigurationPage{

    // Similar to NewProjectModal but has more inputs

    public final SelenideElement nameInput = $("input#form-name");
    public final SelenideElement emailInput = $("input#form-email");
    public final SelenideElement identifierInput = $("input#form-identifier");
    public final SelenideElement descriptionTextarea = $("textarea");
    public final SelenideElement taskLimitInput = $("input#form-task_limit");
    public final SelenideElement isPrivateCheckbox = $("input[name='is_private']");
    public final SelenideElement ownerSelect = $("select#form-owner_id");
    public final SelenideElement startDateInput = $("input#form-start_date");
    public final SelenideElement endDateInput = $("input#form-end_date");
    public final SelenideElement priorityDefaultInput = $("input#form-priority_default");
    public final SelenideElement priorityStartInput = $("input#form-priority_start");
    public final SelenideElement priorityEndInput = $("input#form-priority_end");
    public final SelenideElement saveButton = $("button.btn");

    public final SelenideElement errorMessage = $("ul.form-errors");



}
