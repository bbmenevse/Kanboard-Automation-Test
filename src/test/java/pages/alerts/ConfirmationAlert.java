package pages.alerts;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ConfirmationAlert {

    public final SelenideElement confirmationAlert = $("div.alert.alert-success.alert-fade-out");

    public String getConfirmationMessage()
    {
        return confirmationAlert.shouldBe(visible).getText();
    }

}
