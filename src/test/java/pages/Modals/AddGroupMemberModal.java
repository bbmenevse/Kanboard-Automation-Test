package pages.Modals;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AddGroupMemberModal {


    public final SelenideElement input = $("div#modal-content input.select-dropdown-input");
    public final SelenideElement firstOption = $$("ul#select-dropdown-menu li.select-dropdown-menu-item").first();

    public final SelenideElement submitButton = $("button.btn");
    public final SelenideElement cancelButton = $("div.form-actions a");

    /**
     * @param inputValue The value that will be entered into the input field.
     * @param userNameToSelect The username that will be picked on options
     */
    public void clickMatchingOption(String inputValue, String userNameToSelect)
    {
        input.setValue(inputValue);
        SelenideElement matchingOption = $$("ul#select-dropdown-menu li.select-dropdown-menu-item")
                .find(Condition.text(userNameToSelect));
        matchingOption.shouldBe(Condition.visible).click();
    }

    public void clickFirstOption(String inputValue)
    {
        input.setValue(inputValue);
        SelenideElement matchingOption = $$("ul#select-dropdown-menu li.select-dropdown-menu-item")
                .first();
        matchingOption.shouldBe(Condition.visible).click();
    }

}
