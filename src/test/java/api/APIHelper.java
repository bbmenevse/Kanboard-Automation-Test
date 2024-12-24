package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import util.ConfigLoader;

import java.util.Map;

public class APIHelper {


        public static String buildRequest(String method, Map<String, Object> params) {
            Gson gson = new Gson();
            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("jsonrpc", "2.0");
            requestBody.addProperty("method", method);
            //// !!!!!
            requestBody.addProperty("id", 1); // Not making this dynamic for now as I use only admin, may change later.
            //// !!!!!
            if (params != null && !params.isEmpty()) {
                requestBody.add("params", gson.toJsonTree(params));
            }

            return gson.toJson(requestBody);
        }

    public static Response sendRequest(String requestBody, String userName, String password) {
        return RestAssured.given()
                .auth()
                .basic(userName, password)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(ConfigLoader.getProperty("baseapiurl"));
    }

}
