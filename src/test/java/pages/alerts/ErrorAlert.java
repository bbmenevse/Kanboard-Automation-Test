package pages.alerts;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ErrorAlert {

    /**
     * This alert may be part of the page itself or appear in a modal
     */
    public final SelenideElement errorAlert = $("p.alert.alert-error");
}
