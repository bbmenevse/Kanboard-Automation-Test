package pages.userdetails;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class UserSummaryPage extends BaseUserDetails {

    /* Note:
    Two-factor authentication disabled doesn't have a text.
    Some elements can also be left empty.
     */

    public final String labelLocator = "//li[normalize-space(text())=\"%s\"]";
    public final String textLocator = labelLocator + "/strong";

    public boolean isLabelVisible(String labelName){
        // .contains is case-sensitive. If I try login when its actually Login, it will return false!
        SelenideElement loginElement = $x(String.format(labelLocator,labelName));
        return loginElement.exists() && loginElement.getText().trim().contains(labelName);
    }

    /**
     * Checks if the given label has the given value in front of it.
     * This method is case sensetive! For example "Full Name:" will work but "Full name:" won't work.
     * @param labelName The label that is chosen to be used.
     * @param text The value in front of the label.
     * @return
     */
    public boolean isTextInFrontOfLabelVisible(String labelName, String text){
        SelenideElement loginElement = $x(String.format(textLocator,labelName));
        //System.out.println("Element text: " + loginElement.getText());
        return loginElement.exists() && loginElement.getText().equalsIgnoreCase(text);
    }
}
