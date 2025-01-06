package api.projectapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
// Too many parameters. Won't test them all.
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectJsonObject {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("owner_id")
    private Integer ownerId;

    @JsonProperty("identifier")
    private String identifier;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("token")
    private String token;

    @JsonProperty("end_date")
    private String endDate;

    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty("is_public")
    private Boolean isPublic;

    @JsonProperty("is_private")
    private Boolean isPrivate;

    @JsonProperty("default_swimlane")
    private String defaultSwimlane;
    
    private Boolean result;

    @JsonProperty("show_default_swimlane")
    private Boolean showDefaultSwimlane;

    @JsonProperty("last_modified")
    private String lastModified;

    @JsonProperty("email")
    private String email;

    @JsonProperty("url")
    private Map<String, String> url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public Boolean getResult(){
        return result;
    }

    public void setResult(Boolean result){
        this.result = result;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getDefaultSwimlane() {
        return defaultSwimlane;
    }

    public void setDefaultSwimlane(String defaultSwimlane) {
        this.defaultSwimlane = defaultSwimlane;
    }

    public Boolean getShowDefaultSwimlane() {
        return showDefaultSwimlane;
    }

    public void setShowDefaultSwimlane(Boolean showDefaultSwimlane) {
        this.showDefaultSwimlane = showDefaultSwimlane;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, String> getUrl() {
        return url;
    }

    public void setUrl(Map<String, String> url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Takes a raw Json as String and converts it to a Project object.
     * @param json the json that will be converted to a Project object.
     * @return the object created from json
     * @throws Exception Json Exceptions
     */
    public static ProjectJsonObject fromJson(String json) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseMap = objectMapper.readValue(json, Map.class);
        Object result = responseMap.get("result");
        if(result instanceof Map)
        {
            result = (Map<String, Object>) responseMap.get("result");
            return objectMapper.convertValue(result, ProjectJsonObject.class);
        }
        else if(result instanceof Boolean){
            Map<String, Object> booleanResultMap = new HashMap<>();
            booleanResultMap.put("result", result);
            return objectMapper.convertValue(booleanResultMap, ProjectJsonObject.class);
        }
        else{
            System.out.println("Some result returned from kanboard I haven't seen before" +
                    "/n Check what returned exactly./n  Result: " + result.toString());
            return null;
        }

    }

    public Map<String, Object> toMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(this, Map.class);
    }
}
