package pages.index;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MyProjectsPage extends  BaseIndexPage{

    public final ElementsCollection projectsCollection = $$("div.table-list-row");

    // SORT MENU FOR PROJECTS
    // The sort button is shared across pages and is defined in BaseIndexPage.
    public final SelenideElement projectId = $("div#dropdown a[href*='order=projects.id']");
    public final SelenideElement projectName = $("div#dropdown a[href*='order=projects.name']");
    public final SelenideElement projectStatus= $("div#dropdown a[href*='order=projects.is_active']");
    public final SelenideElement projectStartDate = $("div#dropdown a[href*='order=projects.start_date']");
    public final SelenideElement projectEndDate = $("div#dropdown a[href*='order=projects.end_date']");
    public final SelenideElement projectPublic = $("div#dropdown a[href*='order=projects.is_public']");
    public final SelenideElement projectPersonal = $("div#dropdown a[href*='order=projects.is_public']");
    // MENU END

    // ID DROPDOWN MENU ELEMENTS
    public final SelenideElement board = $("div#dropdown a:has(i.fa-th)");
    public final SelenideElement list = $("div#dropdown a:has(i.fa-list)");
    public final SelenideElement activity = $("div#dropdown a:has(i.fa-dashboard)");
    public final SelenideElement analytics = $("div#dropdown a:has(i.fa-line-chart)");
    public final SelenideElement configureThisProject = $("div#dropdown a:has(i.fa-cog)");


    public void clickIdDropdownByProjectName(String projectName){
        SelenideElement dropdownElement = projectsCollection.findBy(Condition.text(projectName)).$("div.dropdown a");
        dropdownElement.shouldBe(Condition.visible).click();
    }

    public void clickProject(String projectName){
        projectsCollection.findBy(Condition.text(projectName)).$("span.table-list-title a").click();
    }

    public boolean doesProjectExist(String projectName){
        SelenideElement dropdownElement = projectsCollection.findBy(Condition.text(projectName));
        return dropdownElement.exists();
    }


}
