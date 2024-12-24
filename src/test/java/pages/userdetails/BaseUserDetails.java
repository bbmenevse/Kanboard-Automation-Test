package pages.userdetails;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class BaseUserDetails {

    // These elements only appear on Admin
    public final String allUsersButtonCssSelector = "div.page-header a[href='/users']";
    public final String newUserButtonCssSelector = "div.page-header i.fa.fa-plus";
    public final String importButtonCssSelector = "div.page-header i.fa.fa-upload";
    public final String viewAllGroupsButtonCssSelector = "div.page-header i.fa.fa-fw.fa-users";
    // END

    // SIDEBAR ELEMENTS
    public final String sidebarTitleLocator = "//div[@class='sidebar-title']/h2[text()='\"%s\"']";
    public final String menuElementsLocator = "//a[text()=\"%s\"]";
    // SIDEBAR CONTENT ELEMENTS
    public final SelenideElement titleElement = $("div.page-header h2");

    public boolean isInformationHeadingVisible(String title) {
        return $x(String.format(sidebarTitleLocator,title)).exists();
    }
    public boolean isLinkVisible(String text) {
        return $x(String.format(menuElementsLocator,text)).exists();
    }
    public void clickLinkUsingText(String text) {
        $x(String.format(menuElementsLocator,text)).click();
    }

    public boolean isFirstHeaderInSidebarVisible(String name)
    {
        return titleElement.getText().equals(name);
    }



}
