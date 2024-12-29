package api.projectapi;

import api.APIHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
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
    Just encountered same problem with removeProject. You can't delete a project if you are not assigned to the project,
    even as admin.
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
    public Response createProject(String userName,String password, Map<String, Object> params) {
        String requestBody = APIHelper.buildRequest("createProject",params);
        System.out.println(requestBody);
        Response response = APIHelper.sendRequest(requestBody,userName,password);
        System.out.println(response.asString());
        return response;
    }

    public Response getProjectByName(String userName, String password, String projectName){
        Map<String, Object> params = new HashMap<>();
        params.put("name",projectName);
        String requestBody = APIHelper.buildRequest("getProjectByName",params);
        Response response = APIHelper.sendRequest(requestBody,userName,password);
        //ApiResponseValidator.validateResponse(response,200);
        return response;
    }

    // There is no update by name choice, as names are not unique.
    // If a user does not have permission on a project, they will not be able to update it, even if admin.
    public Response updateProjectById(String userName, String password, int projectId){
        Map<String, Object> params = new HashMap<>();
        params.put("project_id",projectId);
        params.put("name","Updated API Project");
        params.put("description", "Updated description via API");

        String requestBody = APIHelper.buildRequest("updateProject",params);
        Response response = APIHelper.sendRequest(requestBody,userName,password);
        return response;

        //ApiResponseValidator.validateResponse(response,200);

    }


    public Response deleteProject(String userName, String password, int projectId){

        Map<String, Object> params = new HashMap<>();
        params.put("project_id",projectId);

        String requestBody = APIHelper.buildRequest("removeProject",params);
        Response response = APIHelper.sendRequest(requestBody,userName,password);
        return response;

        //ApiResponseValidator.validateResponse(response,200);
    }


}