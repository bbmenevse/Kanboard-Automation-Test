package cucumber.steps.apisteps;

import api.projectapi.ProjectAPI;
import io.cucumber.java.en.Given;

public class BaseSteps {

    protected ProjectAPI projectAPI;

    @Given("I have access to the Kanboard API with user {string} and password {string}")
    public void iHaveAccessToTheKanboardApi(String userName, String password) {
        projectAPI = new ProjectAPI(userName,password);
    }
}
