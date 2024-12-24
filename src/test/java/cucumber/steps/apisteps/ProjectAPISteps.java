package cucumber.steps.apisteps;

import api.projectapi.ProjectAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import util.ConfigLoader;

import static org.testng.Assert.assertEquals;

public class ProjectAPISteps {
    private int initialProjectCount;
    private ProjectAPI projectAPI;

    @Given("I have access to the Kanboard API")
    public void i_have_access_to_the_kanboard_api() {
        projectAPI = new ProjectAPI();
    }

    @When("I retrieve the number of projects")
    public void i_retrieve_the_number_of_projects() {
        initialProjectCount = projectAPI.getNumberOfProjects(
                ConfigLoader.getProperty("admin.username"),
                ConfigLoader.getProperty("admin.password"));
    }

    @Then("the number of projects should be {int}")
    public void the_number_of_projects_should_be(int expectedCount) {
        assertEquals(expectedCount, initialProjectCount);
    }

    @When("I create a new project with name {string} and description {string}")
    public void i_create_a_new_project_with_name_and_description(String name, String description) {
        projectAPI.createProject(
                ConfigLoader.getProperty("admin.username"),
                ConfigLoader.getProperty("admin.password"),
                name,
                description);
    }

    @Then("the number of projects should increase by {int}")
    public void the_number_of_projects_should_increase_by(int increment) {
        int updatedProjectCount = projectAPI.getNumberOfProjects(
                ConfigLoader.getProperty("admin.username"),
                ConfigLoader.getProperty("admin.password"));
        assertEquals(initialProjectCount + increment, updatedProjectCount);
    }

}
