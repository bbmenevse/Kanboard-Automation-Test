package pages.managements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GroupManagementPage {

    public SelenideElement allUsers = $("div.page-header a:has(i.fa.fa-user)");
    public SelenideElement newGroup = $("div.page-header a:has(i.fa.fa-user-plus)");
    public SelenideElement searchBar = $("input#form-search");

    // SORT MENU ELEMENTS
    public SelenideElement sortButton = $("div.table-list-header-menu div.dropdown a"); // Works since sort button is first in hierarchy, but also picks dropdown elements.

    public SelenideElement groupId = $$("div#dropdown a").findBy(Condition.text("Group ID"));
    public SelenideElement name = $$("div#dropdown a").findBy(Condition.text("Name"));
    public SelenideElement externalId = $$("div#dropdown a").findBy(Condition.text("External ID"));
    // END

    // ID DROPDOWN ELEMENTS
    public SelenideElement addGroupMembers = $("div#dropdown a:has(i.fa-plus)");
    public SelenideElement members = $("div#dropdown a:has(i.fa-users)");
    public SelenideElement edit = $("div#dropdown a:has(i.fa-edit)");
    public SelenideElement remove = $("div#dropdown a:has(i.fa-trash-o)");
    // END

    public ElementsCollection groupsCollection = $$("div.table-list-row.table-border-left");

    /**
     * Returns the #Number Dropdown Element for given groupName.
     * @param groupName the name of the group.
     * @return the Dropdown Element.
     */
    public SelenideElement getIdDropdownOnGivenGroup(String groupName){
        SelenideElement groupElement = groupsCollection.findBy(Condition.text(groupName)).$("a").shouldBe(Condition.exist);
        if(groupElement.exists())
        {
            return groupElement;
        }
        else{
            // TODO
            // will handle later
            return null;
        }
    }

    /**
     * Returns the <a> element that has groupName as text.
     * @param groupName the name of the group.
     * @return returns the <a> Element that contains group.
     */
    public SelenideElement getGroupNameElement(String groupName){
        SelenideElement groupElement = groupsCollection.findBy(Condition.text(groupName));
        return groupElement.$$("a").findBy(Condition.exactText(groupName));
    }

    /**
     *
     * @param groupName the name of the group that will be searched.
     * @return
     */
    public boolean doesGroupExist(String groupName)
    {
        try{
            groupsCollection.findBy(Condition.text(groupName)).$("div.table-list-details").shouldBe(Condition.exist);
            return true;
        }
        catch (ElementNotFound e)
        {
            return false;
        }
        /*
        SelenideElement groupElement = groupsCollection.findBy(Condition.text(groupName));
        return groupElement.$("div.table-list-details").exists();

         */
    }

    /**
     * Returns the number of members in a group.
     * @param groupName the name for the group.
     * @return the string after removing anything but number.
     */
    public String getUserCountForGivenGroup(String groupName){
        SelenideElement groupElement = groupsCollection.findBy(Condition.text(groupName));
        return groupElement.$("div.table-list-details").getText().replaceAll("\\D+", "");
    }

}
