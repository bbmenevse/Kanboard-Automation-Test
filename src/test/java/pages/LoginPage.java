package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import util.ConfigLoader;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    // Locators for the login elements

    public SelenideElement alertMessage = $("p.alert.alert-error");

    public SelenideElement userNameInput = $("#form-username");
    public SelenideElement passwordInput = $("#form-password");
    public SelenideElement userNameLabel = $("label[for='form-username']");
    public SelenideElement passWordLabel = $("label[for='form-password']");
    public SelenideElement loginButton = $(".btn.btn-blue");
    public SelenideElement rememberMeCheckbox = $("input[type='checkbox'][name='remember_me']");
    public SelenideElement forgotPasswordLink = $("a[href='/forgot-password']");

    public void loginAsAdmin() {
        login(ConfigLoader.getProperty("admin.username"), ConfigLoader.getProperty("admin.password"));
    }

    public void loginAsManager() {
        login(ConfigLoader.getProperty("manager.username"), ConfigLoader.getProperty("manager.password"));
    }

    public void loginAsUser() {
        login(ConfigLoader.getProperty("user.username"), ConfigLoader.getProperty("user.password"));
    }

    public void invalidLoginAttempt() {
        login("invalidName","invalidPassword2");
    }

    public void login(String username, String password) {
        open("http://localhost:88");
        userNameInput.shouldBe(visible).setValue(username);
        passwordInput.setValue(password);
        loginButton.click();
    }

}
