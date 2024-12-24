package pages.Modals;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CommentsOnTaskModal {
    public final SelenideElement changeSortingElement = $("div#modal-content a.js-modal-replace");
    public final SelenideElement sendByMailElement = $("div#modal-content a.js-modal-medium:has(i.fa.fa-paper-plane)");
    public final ElementsCollection commentsCollection = $$("div.comment");
    public final SelenideElement textArea = $("textarea");
    public final SelenideElement visibilitySelectElement = $("select#form-visibility");
    public final SelenideElement submitButton = $("button.btn");
    public final SelenideElement cancelButton = $("div.form-actions a");
    public final SelenideElement closeModalButton = $("a#modal-close-button");

    // Comment Menu Elements

    public final SelenideElement link = $("div#dropdown li a:has(i.fa-link)");
    public final SelenideElement reply = $("div#dropdown li a:has(i.fa-reply)");
    public final SelenideElement edit = $("div#dropdown li a:has(i.fa-edit)");
    public final SelenideElement remove = $("div#dropdown li a:has(i.fa-trash-o)");

    public boolean doesCommentExistByCommentText(String commentText){
        closeModalButton.shouldBe(visible);
        SelenideElement commentElement = commentsCollection.findBy(Condition.text(commentText));
        return commentElement.exists();
    }

    public void clickCommentDropdownByComment(String commentText){
        commentsCollection.findBy(Condition.text(commentText)).$("div.dropdown a").click();
    }

    public void addComment(String comment, Role role) {
        textArea.setValue(comment);
        visibilitySelectElement.selectOption(role.getDisplayName());
    }

    public boolean isUserOnCommentCorrect(String commentText, String userName){
       return commentsCollection.findBy(Condition.text(commentText)).$("strong.comment-username").getOwnText().equals(userName);
    }

    public boolean doesUserHaveImage(String userName)
    {
        return commentsCollection.findBy(Condition.text(userName)).$("img").isImage();
    }





    /**
     * Roles for visibility select options
     */
    public enum Role {
        STANDARD_USERS("Standard users"),
        APPLICATION_MANAGERS("Application managers or more"),
        ADMINISTRATORS("Administrators");

        private final String displayName;

        Role(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

}
