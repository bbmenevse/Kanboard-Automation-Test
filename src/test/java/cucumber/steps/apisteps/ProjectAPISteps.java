package cucumber.steps.apisteps;

import api.projectapi.ProjectAPI;
import api.projectapi.ProjectJsonObject;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class ProjectAPISteps extends BaseSteps{
    Map<String, Object> updateParams;
    private int initialProjectCount;
    private Response response;
    private ProjectAPI projectAPI;

    @When("I retrieve the number of projects")
    public void iRetrieveTheNumberOfProjects() {
        initialProjectCount = projectAPI.getNumberOfProjects();
    }

    @Then("the number of projects should be {int}")
    public void theNumberOfProjectsShouldBe(int expectedCount) {
        System.out.println("Initial project count: " + initialProjectCount);
        System.out.println("Expected project count: " + expectedCount);
        assertEquals(expectedCount, initialProjectCount);
    }
    @When("I Update a project by id or identifier with the following details:")
    public void iUpdateProjectByIdOrIdentifier(io.cucumber.datatable.DataTable dataTable) throws Exception {
        updateParams = new HashMap<>(dataTable.asMap(String.class, Object.class));

       Response response =  projectAPI.updateProjectById(updateParams);
       // We could simply check if the result is "true" as well, but wanted to get details.
       if(response.getStatusCode() == 200){
           if(response.jsonPath().getMap("error")!=null){
               fail("Missing argument! ID is missing.");
           }
           System.out.println(response.getBody().asString());
           if(response.jsonPath().getBoolean("result")){
               //System.out.println("Update was successful");
           }
           else{
               fail("The update did not pass Through!");
           }
       }
       else{
           //System.out.println("Connection failed!");
           fail("Connection failed!");
       }
    }


    @When("I create a new project with the following details:")
    public void iCreateANewProjectWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        Map<String, Object> params = new HashMap<>(dataTable.asMap(String.class, Object.class));
        projectAPI.createProject( params);
    }

    @Then("the number of projects should increase by {int}")
    public void theNumberOfProjectsShouldIncreaseBy(int increment) {
        int updatedProjectCount = projectAPI.getNumberOfProjects();
        assertEquals(initialProjectCount + increment, updatedProjectCount);
    }

    @Then("the updates should be reflected in the project details")
    public void theUpdatesShouldBeReflectedInTheProjectDetails() throws Exception {
        // Retrieve updated project details
        int projectId = Integer.parseInt(updateParams.get("project_id").toString());
        Response response = projectAPI.getProjectById(projectId);

        // Get Project requires "project_id" but returns "id" in results as project id
        // This creates a mismatch so I fixed it
        if (updateParams.containsKey("project_id")) {
            updateParams.put("id", updateParams.remove("project_id"));
        }
        // Validate the details match the updateParams
        ProjectJsonObject updatedProject = ProjectJsonObject.fromJson(response.getBody().asString());
        for (Map.Entry<String, Object> entry : updateParams.entrySet()) {
            String key = entry.getKey();
            Object expectedValue = entry.getValue();

            Object actualValue = updatedProject.toMap().get(key);

            // Normalize types for comparison
            if (actualValue instanceof String && expectedValue instanceof Integer) {
                actualValue = Integer.parseInt((String) actualValue);
            } else if (actualValue instanceof Integer && expectedValue instanceof String) {
                expectedValue = Integer.parseInt((String) expectedValue);
            }

            assertEquals(actualValue, expectedValue, "Mismatch for field: " + key);
        }
    }


    @When("I remove project with given id: {int}")
    public void iRemoveAProjectCalled(int projectId) {
        projectAPI.deleteProject(projectId);
    }

}
