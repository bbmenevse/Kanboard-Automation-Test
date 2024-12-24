package helper;

import util.ConfigLoader;

import static com.codeborne.selenide.Selenide.open;

public class NavigationHelper {

    //Direct links to pages.

    public static void openIndexPage(){
        open(ConfigLoader.getProperty("baseurl"));
    }
    public static void openUserManagement(){
        open(ConfigLoader.getProperty("baseurl") + "users");
    }
    public static void checkUserDetails(String id) {
        open(ConfigLoader.getProperty("baseurl") +  "user/show/" + id);
    }
    public static void checkUserProjects(String id) {
        open(ConfigLoader.getProperty("baseurl")+"dashboard/" + id + "/projects");
    }
    public static void openProjectBoard(String id){
        open(ConfigLoader.getProperty("baseurl")+"board/" + id);
    }

}
