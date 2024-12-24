package driver;


import util.ConfigLoader;

public class BaseDriver {

    public void setUp() {
        // Load properties for browser and resolution
        String browser = ConfigLoader.getProperty("browser");
        String resolution = ConfigLoader.getProperty("resolution");

        if (browser.equalsIgnoreCase("chrome")) {
            DriverManager.getDriver(DriverManager.BrowserType.CHROME, resolution);
        } else if (browser.equalsIgnoreCase("firefox")) {
            DriverManager.getDriver(DriverManager.BrowserType.FIREFOX, resolution);
        }
    }

    public void tearDown() {
        DriverManager.quitDriver();
    }
}
