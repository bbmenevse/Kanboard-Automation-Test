package pages.Modals;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class EditGroupModal {
    public final SelenideElement groupName = $("input#form-name");
    public final SelenideElement submitButton = $("button.btn");
    public final SelenideElement cancelButton = $("div.form-actions a");
}
