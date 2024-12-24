package cucumber.steps.generalsteps;

import io.cucumber.java.en.*;
import pages.LoginPage;
import pages.index.MySubtasksPage;

import static com.codeborne.selenide.Condition.visible;
import static org.testng.Assert.assertTrue;

public class MySubtasksSteps {

    LoginPage loginPage = new LoginPage();
    MySubtasksPage mySubtasksPage = new MySubtasksPage();

    @Given("the user logins with username: {string} and password: {string}")
    public void loginKanboard(String username, String password){
        loginPage.login(username,password);
        mySubtasksPage.mySubtasksButtonOnSideBar.shouldBe(visible).click();
    }

    @When("the user clicks on {string}")
    public void clickOnSubtask(String subtaskName) {
        assertTrue(mySubtasksPage.doesIconsChangeAsExpected(subtaskName));
    }

    @Then("the icon should change as expected")
    public void verifyIconChange() {

    }

}