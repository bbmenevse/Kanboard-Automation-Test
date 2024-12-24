package pages.managements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GroupMembersPage {

    public final SelenideElement allUsers = $("div.page-header a:has(i.fa-user)");
    public final SelenideElement viewAllGroups = $("div.page-header a:has(i.fa-users)");
    public final SelenideElement addGroupMember = $("div.page-header a:has(i.fa-plus)");

    // Sort Menu Elements
    public final SelenideElement sortButton = $("div.table-list-header-menu a.dropdown-menu-link-icon");

    public final SelenideElement userId = $$("div#dropdown a").findBy(Condition.exactText("User ID"));
    public final SelenideElement userName = $$("div#dropdown a").findBy(Condition.exactText("Username"));
    public final SelenideElement name = $$("div#dropdown a").findBy(Condition.exactText("Name"));
    public final SelenideElement email = $$("div#dropdown a").findBy(Condition.exactText("Email"));
    public final SelenideElement accountType = $$("div#dropdown a").findBy(Condition.exactText("Account type"));
    public final SelenideElement role = $$("div#dropdown a").findBy(Condition.exactText("Role"));
    public final SelenideElement twoFactor = $$("div#dropdown a").findBy(Condition.exactText("Two Factor"));
    public final SelenideElement Status = $$("div#dropdown a").findBy(Condition.matchText("Status"));
    // MENU END

    // ID DROPDOWN MENU ELEMENTS
    // No direct button for id, I created a method instead: openSortAndClickOption()
    public final SelenideElement viewUser = $("div#dropdown a:has(i.fa-user)");
    public final SelenideElement deleteUser = $("div#dropdown a:has(i.fa-trash)");
    // END
    public final ElementsCollection membersCollection = $$("div.table-list-row.table-border-left");

    /**
     * Clicks sort button and clicks the sort option that matches optionText.
     * @param optionText The option in the sort menu that will be clicked.
     */
    public void openSortAndClickOption(String optionText)
    {
        sortButton.shouldBe(visible).click();
        SelenideElement optionElement = $$("div#dropdown a")
                .findBy(Condition.matchText(optionText));
        optionElement.shouldBe(visible).click();
    }

    /**
     * Clicks the user with given name
     * @param userName
     */
    public void clickIdDropdownElement(String userName)
    {
        SelenideElement memberElement = membersCollection.findBy(Condition.text(userName));
        memberElement.$("div.dropdown a").shouldBe(visible).click();
    }

    public SelenideElement getMember(String userName){
        return membersCollection.findBy(Condition.text(userName)).$("span.table-list-title a");
    }

    /**
     * Used for sorting tests.
     * @param firstMember The expected first member of the group.
     * @param lastMember The expected last member of the group.
     * @return If the inputs match the actual first and last elements on the page, return true. Else return false.
     */
    public boolean isSortOrderedCorrectly(String firstMember, String lastMember)
    {
        ElementsCollection memberNameElement = $$("span.table-list-title a");
        String firstText = "";
        String lastText = "";
        if (!memberNameElement.isEmpty()) {
            firstText = memberNameElement.first().getText();
            lastText = memberNameElement.last().getText();
        }

        //System.out.println("First: " + firstText);
        //System.out.println("Last: " + lastText);

        return firstMember.equals(firstText) && lastMember.equals(lastText);
    }

}
