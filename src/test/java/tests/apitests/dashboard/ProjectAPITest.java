package tests.apitests.dashboard;

import api.projectapi.ProjectAPI;
import api.projectapi.ProjectJsonObject;
import org.testng.annotations.Test;
import util.ConfigLoader;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class ProjectAPITest {

    ProjectAPI projectAPI = new ProjectAPI();


    @Test(priority = 1)
    public void validateNumberOfProjects() {
        assertEquals(projectAPI.getNumberOfProjects(ConfigLoader.getProperty("admin.username"),ConfigLoader.getProperty("admin.password")),7);
    }
    @Test(priority = 2)
    public void createProject(){
        int projectNumber = projectAPI.getNumberOfProjects(ConfigLoader.getProperty("admin.username"),ConfigLoader.getProperty("admin.password"));
        ProjectJsonObject projectObject = new ProjectJsonObject();
        projectObject.setName("API Test Name");
        projectObject.setDescription("API Test Description");
        projectObject.setEmail("API Test Email");
        projectObject.setIdentifier("APITestIdentifier");
        projectObject.setOwnerId(1);
        projectAPI.createProject(ConfigLoader.getProperty("admin.username"),ConfigLoader.getProperty("admin.password"),projectObject.toMap());
        assertEquals(projectNumber+1,projectAPI.getNumberOfProjects(ConfigLoader.getProperty("admin.username"),ConfigLoader.getProperty("admin.password"))); // confirm that number of projects went up by 1
    }
    @Test(priority = 3)
    public void updateProject(){
        assertEquals(projectAPI.getProjectByName(ConfigLoader.getProperty("admin.username"),ConfigLoader.getProperty("admin.password"),"Created By API Project").getStatusCode(),200);
        projectAPI.updateProjectById(ConfigLoader.getProperty("admin.username"),ConfigLoader.getProperty("admin.password"),10); // I did the updating inside method.
        assertEquals(projectAPI.getProjectByName(ConfigLoader.getProperty("admin.username"),ConfigLoader.getProperty("admin.password"),"Created By API Project").getStatusCode(),200);
    }
    @Test(priority = 4)
    public void deleteProject(){
        int projectNumber = projectAPI.getNumberOfProjects(ConfigLoader.getProperty("admin.username"),ConfigLoader.getProperty("admin.password"));
        projectAPI.deleteProject(ConfigLoader.getProperty("admin.username"),ConfigLoader.getProperty("admin.password"),10);
        assertEquals(projectAPI.getNumberOfProjects(ConfigLoader.getProperty("admin.username"),ConfigLoader.getProperty("admin.password")),projectNumber-1);
    }

    @Test
    public void randomTest() throws Exception {
        ProjectJsonObject obj = ProjectJsonObject.fromJson(projectAPI.getProjectByName(ConfigLoader.getProperty("admin.username"),ConfigLoader.getProperty("admin.password"),"Flying Cow").getBody().asString());
        System.out.println("Description: " + obj.getDescription());
        System.out.println("Name: " + obj.getName());
        System.out.println("Identifier: " + obj.getIdentifier());
    }





}
