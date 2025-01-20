package api.projectfileapi;

import api.APIHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ProjectFileAPI {

    public int createProjectFile(String userName, String password, int projectId, String fileName, Object file  ){
        Object[] params = APIHelper.createParameterNamelessParams();
        String requestBody = APIHelper.buildRequest("createProjectFile",params);

        // Make the API call with Basic Auth
        Response response = APIHelper.sendRequest(requestBody,userName,password);

        JsonPath jsonPath = response.jsonPath();

        return jsonPath.getList("result").size();
    }
}
