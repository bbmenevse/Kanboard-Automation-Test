package pages.project.projectconfiguration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.project.BaseProjectPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BaseProjectConfigurationPage extends BaseProjectPage {

    public final SelenideElement headerTitle = $("h1 span.title");
    public final SelenideElement sidebarContentTitle = $("div.page-header h2");
    // SIDEBAR MENU ELEMENTS
    public final SelenideElement menuElementsBaseLink = $$("div.sidebar a[href^='/project/']").find(Condition.text("Summary"));

    public void clickOnMenuLinks(String linkName) {
        try{
            $$("div.sidebar a[href^='/project/']")
                    .find(Condition.matchText("(?i)" + linkName)).click();
        }
        catch (Exception e){
            System.out.println("Menu does not have a link called: " + linkName);
            System.out.println("The exception that was thrown: " + e.getMessage());
        }
    }



}
