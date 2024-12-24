package pages.Modals;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class NewUserModal {
    public final SelenideElement username = $("input[name='username']");
    public final SelenideElement name = $("input[name='name']");
    public final SelenideElement email = $("input[name='email']");
    public final SelenideElement password = $("input[name='password']");
    public final SelenideElement confirmation = $("input[name='confirmation']");
    public final SelenideElement role = $("select[name='role']");
    public final SelenideElement language= $("select[name='language']");

    public final SelenideElement submitButton = $("button.btn");
    public final SelenideElement cancelButton = $("div.form-actions a");

    // All errors:
    public final ElementsCollection allErrors = $$("ul.form-errors");

    // Page clickable, Not in the Modal
    //
    public final String dropdownLinkBaseLocator = "//a[contains(@class, 'dropdown-menu-link-icon') and contains(@class, 'dropdown-menu')]/strong[text()='#{id} ']";

    // Dropdown Menu elements
    public final String changePasswordLinkCssSelector = "a[href*='changePassword']"; // Using a partial match for the href

    // Dropdown Menu Elements----
    public void clickDropdown(int userId) {
        String locator = dropdownLinkBaseLocator.replace("#{id}", String.valueOf(userId));
        $x(locator).click();
    }
    public void clickChangePassword() {
        $(changePasswordLinkCssSelector).click();
    }
    // Dropdown Menu Elements End -----

    public NewUserModal(){

    }

    public static class Builder {

        private final NewUserModal newUserModal;

        // Required
        public String username;
        public String password;
        public String confirmation;

        // Optional
        public String name;
        public String email;
        public String role;
        public String language;

        public Builder(String username, String password, String confirmation) {
            newUserModal = new NewUserModal();
            newUserModal.username.setValue(username);
            newUserModal.password.setValue(password);
            newUserModal.confirmation.setValue(confirmation);
        }

        public Builder setName(String name) {
            newUserModal.name.setValue(name);
            return this;
        }

        public Builder setEmail(String email) {
            newUserModal.email.setValue(email);
            return this;
        }

        public Builder setRole(String role) {
            newUserModal.role.selectOption(role);
            return this;
        }

        public Builder setLanguage(String language) {
            newUserModal.language.setValue(language);
            return this;
        }
        public NewUserModal build() {
            return newUserModal;
        }
    }

}


