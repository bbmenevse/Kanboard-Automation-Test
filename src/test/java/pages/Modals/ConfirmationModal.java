package pages.Modals;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ConfirmationModal {
    // This modal appears when deleting, removing a task or in similar situations
    public static final SelenideElement modalCloseButton = $("div#modal-header a");
    public static final SelenideElement confirmationText = $("div p");
    public static final SelenideElement modalTitle = $("div.page-header h2");
    public static final SelenideElement confirmButton = $("button#modal-confirm-button");
    public static final SelenideElement cancelButton = $("div.form-actions a");

    public static void clickConfirmButton()
    {
        confirmButton.shouldBe(visible).click();
    }


}
