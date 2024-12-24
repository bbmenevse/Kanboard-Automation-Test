package pages.userdetails;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ChangePasswordPage extends BaseUserDetails {

    private final String currentPasswordCssSelector = "input#form-current_password";
    private final String newPasswordCssSelector = "input#form-password";
    private final String confirmPasswordCssSelector = "input#form-confirmation";
    private final String saveButtonCssLocator = "button.btn";


    private final SelenideElement formErrorsElement = $("ul.form-errors");

    public void changePassword(String currentPassword, String newPassword, String confirmPassword){
        $(currentPasswordCssSelector).setValue(currentPassword);
        $(newPasswordCssSelector).setValue(newPassword);
        $(confirmPasswordCssSelector).setValue(confirmPassword);
        $(saveButtonCssLocator).click();
    }

    public String getErrorMessage()
    {
        return formErrorsElement.getText();
    }

}
