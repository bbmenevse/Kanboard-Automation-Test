package pages.userdetails;

import static com.codeborne.selenide.Selenide.$;

public class EditProfilePage extends BaseUserDetails {

    /*
    There is a scenario I am afraid to test. What would happen If I tried changing admin user's
    Application role to something else? I have no idea. But I am scared to try this.
    I would probably have to access mysql directly and fix it back to Administrator.
     */

    // CSS Selectors
    public final String userNameCssSelector = "input#form-username";
    public final String nameCssSelector = "input#form-name";
    public final String emailCssSelector = "input#form-email";
    public final String themeCssSelector = "select#form-theme";
    public final String timeZoneCssSelector = "select#form-timezone";
    public final String languageCssSelector = "select#form-language";
    public final String filterCssSelector = "input#form-filter";
    public final String applicationRoleCssSelector = "select#form-role";
    public final String saveButtonCssSelector = "button[type='submit'].btn.btn-blue";

    private EditProfilePage(EditProfileBuilder builder) {
        // First if to check if the String is null
        // If String is null, don't change anything about it
        // Second if to check if the object has value in it.
        // If no value was given, that means leave it empty.
        if (builder.userName != null) {
            $(userNameCssSelector).clear();
            if(!builder.userName.isEmpty()) {
                $(userNameCssSelector).setValue(builder.userName);
            }
        }
        if (builder.name != null) {
            $(nameCssSelector).clear();
            if(!builder.name.isEmpty()) {
                $(nameCssSelector).setValue(builder.name);
            }
        }
        if (builder.email != null) {
            $(emailCssSelector).clear();
            if(!builder.email.isEmpty()) {
                $(emailCssSelector).setValue(builder.email);
            }
        }
        if(builder.theme!=null)
        {
            $(themeCssSelector).setValue(builder.theme);
        }
        if(builder.timezone!=null)
        {
            $(timeZoneCssSelector).setValue(builder.timezone);
        }
        if(builder.language!=null)
        {
            $(languageCssSelector).setValue(builder.language);
        }
        if(builder.filter!= null)
        {
            $(filterCssSelector).clear();
            if(!builder.filter.isEmpty())
            {
                $(filterCssSelector).setValue(builder.filter);
            }
        }
        if(builder.applicationRole!=null)
        {
            $(applicationRoleCssSelector).setValue(builder.applicationRole);
        }

        $(saveButtonCssSelector).click();
    }

    public static class EditProfileBuilder {
        // Required
        private final String userName;
        // Optional
        private String name;
        private String email;
        private String theme;
        private String timezone;
        private String language;
        private String filter;
        private String applicationRole;

        public EditProfileBuilder(String username) {
            this.userName = username;
        }

        public EditProfileBuilder name(String name) {
            this.name = name;
            return this;
        }

        public EditProfileBuilder email(String email) {
            this.email = email;
            return this;
        }

        public EditProfileBuilder theme(String theme) {
            this.theme = theme;
            return this;
        }

        public EditProfileBuilder timezone(String timezone) {
            this.timezone = timezone;
            return this;
        }

        public EditProfileBuilder language(String language) {
            this.language = language;
            return this;
        }

        public EditProfileBuilder filter(String filter) {
            this.filter = filter;
            return this;
        }

        public EditProfileBuilder applicationRole(String applicationRole) {
            this.applicationRole = applicationRole;
            return this;
        }

        // Build method to create the EditProfile object
        public EditProfilePage build() {
            return new EditProfilePage(this);
        }
    }

}