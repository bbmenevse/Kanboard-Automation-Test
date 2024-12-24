package pages.settings;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BaseSettingsPage {

    private final String settingsSidebarMenuElementsLocator="//div[@class='sidebar']//a[normalize-space(text())='%s']";

    public final SelenideElement about = $("div.sidebar a[href='/settings']");
    public final SelenideElement applicationSettings = $("div.sidebar a[href='/settings/application']");
    public final SelenideElement emailSettings = $("div.sidebar a[href='/settings/email']");
    public final SelenideElement projectSettings = $("div.sidebar a[href='/settings/project']");
    public final SelenideElement boardSettings = $("div.sidebar a[href='/settings/board']");
    public final SelenideElement tagsManagement = $("div.sidebar a[href='/settings/tags']");
    public final SelenideElement linkLabels = $("div.sidebar a[href='/settings/links/labels']");
    public final SelenideElement currencyRates = $("div.sidebar a[href='/settings/currencies']");
    public final SelenideElement integrations = $("div.sidebar a[href='/settings/integrations']");
    public final SelenideElement webhooks = $("div.sidebar a[href='/settings/webhook']");
    public final SelenideElement apiSettings = $("div.sidebar a[href='/settings/api']");

    public void clickLinkByText(String linkText) {
        $x(String.format(settingsSidebarMenuElementsLocator, linkText)).click();
    }
}
