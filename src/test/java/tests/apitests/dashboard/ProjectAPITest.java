package tests.apitests.dashboard;

import api.projectapi.ProjectAPI;
import api.projectapi.ProjectJsonObject;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.ConfigLoader;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class ProjectAPITest {

    ProjectAPI projectAPI;
    @BeforeClass
    public void setUp(){
        projectAPI = new ProjectAPI(ConfigLoader.getProperty("admin.username"),ConfigLoader.getProperty("admin.password"));
    }

    @Test(priority = 1)
    public void validateNumberOfProjects() {
        assertEquals(projectAPI.getNumberOfProjects(),7);
    }
    @Test(priority = 2)
    public void createProject(){
        int projectNumber = projectAPI.getNumberOfProjects();
        ProjectJsonObject projectObject = new ProjectJsonObject();
        projectObject.setName("API Test Name");
        projectObject.setDescription("API Test Description");
        projectObject.setEmail("API Test Email");
        projectObject.setIdentifier("APITestIdentifier");
        projectObject.setOwnerId(1);
        projectAPI.createProject(projectObject.toMap());
        assertEquals(projectNumber+1,projectAPI.getNumberOfProjects()); // confirm that number of projects went up by 1
    }
    @Test(priority = 3)
    public void updateProject() throws Exception {
        Response response = projectAPI.getProjectByIdentifier("APITestIdentifier");
        ProjectJsonObject projectJsonObject = ProjectJsonObject.fromJson(response.getBody().asString());

        Map<String, Object> projectObject = new HashMap<>();
        projectObject.put("project_id",projectJsonObject.getId());
        projectObject.put("name","Updated API Project");
        projectObject.put("description", "Updated description via API");
        assertEquals(projectAPI.getProjectByName("Created By API Project").getStatusCode(),200);
        projectAPI.updateProjectById(projectObject); // I did the updating inside method.
        assertEquals(projectAPI.getProjectByName("Created By API Project").getStatusCode(),200);
    }
    @Test(priority = 4)
    public void deleteProject(){
        int projectNumber = projectAPI.getNumberOfProjects();
        projectAPI.deleteProject(10);
        assertEquals(projectAPI.getNumberOfProjects(),projectNumber-1);
    }

    @Test
    public void randomTest() throws Exception {
        ProjectJsonObject obj = ProjectJsonObject.fromJson(projectAPI.getProjectByName("Flying Cow").getBody().asString());
        System.out.println("Description: " + obj.getDescription());
        System.out.println("Name: " + obj.getName());
        System.out.println("Identifier: " + obj.getIdentifier());
    }

}
