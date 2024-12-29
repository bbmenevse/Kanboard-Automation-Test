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

    public static void validateResponse(Response response, int expectedStatusCode) {
        if (response == null) {
            throw new IllegalArgumentException("Response cannot be null.");
        }

        if (response.getStatusCode() != expectedStatusCode) {
            throw new RuntimeException("API call does not match the expected status code: " + response.getStatusCode()
                    + ", Response: " + " /n Expected status code:"+ response.getBody().asString());
        }

        String responseBody = response.getBody().asString();
        if (response.getStatusCode() == 204 || (responseBody != null && !responseBody.isEmpty())) {
            System.out.println("Empty body from response as intended.");
        } else {
            throw new IllegalStateException("Unexpected empty response body for this operation.");
        }

        //System.out.println("Raw JSON Response: " + responseBody);

        try {
            Object result = response.jsonPath().get("result");
            if (result == null) {
                throw new IllegalStateException("Unexpected response: result is null.");
            }
            //return result;
        } catch (Exception e) {
            throw new IllegalStateException("Failed to parse response JSON: " + e.getMessage(), e);
        }
    }

}
