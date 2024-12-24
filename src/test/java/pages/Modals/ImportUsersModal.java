package pages.Modals;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ImportUsersModal {

    public final SelenideElement delimiter = $("select#form-delimiter");
    public final SelenideElement enclosure = $("select#form-enclosure");
    public final SelenideElement importFileInput = $("input#form-file");

    public final SelenideElement importButton = $("button.btn");
    public final SelenideElement cancelButton = $("div.form-actions a");

}
