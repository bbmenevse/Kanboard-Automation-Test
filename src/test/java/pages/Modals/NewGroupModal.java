package pages.Modals;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class NewGroupModal {

    public final SelenideElement input = $("div#modal-content input#form-name");
    public final SelenideElement submitButton = $("button.btn");
    public final SelenideElement cancelButton = $("div.form-actions a");



}
