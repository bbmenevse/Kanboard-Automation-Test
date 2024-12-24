package api.projectapi;

import api.APIHelper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import util.ConfigLoader;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

/**
 * The reason behind taking userName and password as inputs is,
 * the api calls will have different responses to different users/roles.
 *
 * get
 */

/*
A single method could have suffice if needed. It would take userName, userPassword, api method, parameters.
That would be better in my opinion but for this project, I will create CRUD methods.
 */
    /*
    Admin account can't update a project that was created using API, as owner is not set if not specified.
    Even admin accounts can't update a project via API, even though they can do it on browser.
     */
public class ProjectAPI {


    public int getNumberOfProjects(String userName, String password){
        // Set the base URI

        String requestBody = APIHelper.buildRequest("getAllProjects",null);

        // Make the API call with Basic Auth
        Response response = APIHelper.sendRequest(requestBody,userName,password);

        JsonPath jsonPath = response.jsonPath();

        return jsonPath.getList("result").size();
    }

    /*
    Kanboard returns an empty body in contrast to what is written on:
    https://docs.kanboard.org/v1/api/project_procedures/#createproject
    If, the json does not have user id.

     */
    public void createProject(String userName,String password,String projectName, String description) {

        Map<String, Object> params = new HashMap<>();
        params.put("name",projectName);
        params.put("description",description);
        params.put("owner_id",1);

        String requestBody = APIHelper.buildRequest("createProject",params);

        Response response = APIHelper.sendRequest(requestBody,userName,password);

        if (response.getStatusCode() == 200 && response.getBody().asString() != null) {
            try {
                Object result = response.jsonPath().get("result");
                if (result == null || !(result instanceof Integer)) {
                    throw new IllegalStateException("Unexpected response: result is null or not an integer.");
                }
            } catch (Exception e) {
                throw new IllegalStateException("Failed to parse response JSON: " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("API call failed with status code: " + response.getStatusCode()
                    + ", Response: " + response.getBody().asString());
        }
    }

    public int getProjectByName(String userName, String password, String projectName){
        Map<String, Object> params = new HashMap<>();
        params.put("name",projectName);

        String requestBody = APIHelper.buildRequest("getProjectByName",params);

        Response response = APIHelper.sendRequest(requestBody,userName,password);

        if (response.getStatusCode() == 200 && response.getBody().asString() != null) {
            String responseBody = response.getBody().asString();
            System.out.println("Raw JSON Response: " + responseBody);

            try {
                Object result = response.jsonPath().get("result");
                if (result == null) {
                    throw new IllegalStateException("Unexpected response: result is null.");
                }

            } catch (Exception e) {
                throw new IllegalStateException("Failed to parse response JSON: " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("API call failed with status code: " + response.getStatusCode()
                    + ", Response: " + response.getBody().asString());
        }
        return response.getStatusCode();
    }

    // There is no update by name choice, as names are not unique.
    // If a user does not have permission on a project, they will not be able to update it, even if admin.
    public void updateProjectById(String userName, String password, int projectId){
        Map<String, Object> params = new HashMap<>();
        params.put("project_id",projectId);
        params.put("name","Updated API Project");
        params.put("description", "Updated description via API");

        String requestBody = APIHelper.buildRequest("updateProject",params);

        Response response = APIHelper.sendRequest(requestBody,userName,password);

        if (response.getStatusCode() == 200 && response.getBody().asString() != null) {
            String responseBody = response.getBody().asString();
            System.out.println("Raw JSON Response: " + responseBody);

            try {
                Object result = response.jsonPath().get("result");
                if (result == null) {
                    throw new IllegalStateException("Unexpected response: result is null.");
                }

            } catch (Exception e) {
                throw new IllegalStateException("Failed to parse response JSON: " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("API call failed with status code: " + response.getStatusCode()
                    + ", Response: " + response.getBody().asString());
        }
    }

    public void deleteProject(String userName, String password, int projectId){

        Map<String, Object> params = new HashMap<>();
        params.put("project_id",projectId);

        String requestBody = APIHelper.buildRequest("removeProject",params);

        Response response = APIHelper.sendRequest(requestBody,userName,password);

        if (response.getStatusCode() == 200 && response.getBody().asString() != null) {
            String responseBody = response.getBody().asString();
            System.out.println("Raw JSON Response: " + responseBody);

            try {
                Object result = response.jsonPath().get("result");
                if (result == null) {
                    throw new IllegalStateException("Unexpected response: result is null.");
                }

            } catch (Exception e) {
                throw new IllegalStateException("Failed to parse response JSON: " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("API call failed with status code: " + response.getStatusCode()
                    + ", Response: " + response.getBody().asString());
        }
    }


}