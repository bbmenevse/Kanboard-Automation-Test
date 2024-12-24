package driver;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import util.ConfigLoader;

public class DriverManager {

    public enum BrowserType {
        CHROME, FIREFOX
    }

    // ThreadLocal to hold WebDriver instances for each thread
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    // Method to set up WebDriver based on browser type
    public static WebDriver getDriver(BrowserType browserType, String resolution) {
        String[] dimensions = resolution.split("x");

        // Check if WebDriver instance exists for the current thread
        if (threadLocalDriver.get() == null) {
            WebDriver driver;

            switch (browserType) {
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--lang=" + ConfigLoader.getProperty("language"));

                    // Resolution Manual
                    if (!resolution.equals("maximize")) {
                        chromeOptions.addArguments("--window-size=" + dimensions[0] + "," + dimensions[1]);
                        driver = new org.openqa.selenium.chrome.ChromeDriver(chromeOptions);
                        driver.manage().window().setPosition(new org.openqa.selenium.Point(0, 0));
                    } else {
                        chromeOptions.addArguments("--start-maximized");
                        driver = new org.openqa.selenium.chrome.ChromeDriver(chromeOptions);
                    }
                    break;

                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();

                    firefoxOptions.addArguments("--disable-extensions");
                    firefoxOptions.addPreference("intl.accept_languages", ConfigLoader.getProperty("language"));

                    // Self Reminder: DPI Scale in Windows affects Firefox dimensions
                    if (!resolution.equals("maximize")) {
                        firefoxOptions.addArguments("--width=" + dimensions[0]);
                        firefoxOptions.addArguments("--height=" + dimensions[1]);
                        firefoxOptions.addPreference("layout.css.devPixelsPerPx", "1.0");
                        driver = new org.openqa.selenium.firefox.FirefoxDriver(firefoxOptions);
                        driver.manage().window().setPosition(new org.openqa.selenium.Point(0, 0));
                    } else {
                        driver = new org.openqa.selenium.firefox.FirefoxDriver(firefoxOptions);
                        driver.manage().window().maximize();
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browserType);
            }

            // Set the WebDriver instance for the current thread
            threadLocalDriver.set(driver);

            // Set Selenide WebDriver
            WebDriverRunner.setWebDriver(driver);
        }

        return threadLocalDriver.get();
    }

    // Method to quit the WebDriver for the current thread
    public static void quitDriver() {
        WebDriver driver = threadLocalDriver.get();
        if (driver != null) {
            driver.quit();
            WebDriverRunner.closeWebDriver();
            threadLocalDriver.remove(); // Remove instance to prevent memory leaks
        }
    }
}