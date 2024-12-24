package cucumber.steps.generalsteps;

import driver.BaseDriver;
import helper.HeaderHelper;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class Hooks {

    // Driver setup
    protected BaseDriver baseDriver = new BaseDriver();

    // Page objects for login/logout
    HeaderHelper headerHelper = new HeaderHelper();

    @Before("@WithHook")
    public void setUp() {
        //System.out.println("Setting up the driver and logging in...");

        // Start the driver
        baseDriver.setUp();

    }

    @After("@WithHook")
    public void tearDown() {
        //System.out.println("Logging out and tearing down the driver...");

        // Log out
        headerHelper.clickGivenElementOnDropdown(headerHelper.logoutOnDropdown);

        // Quit the driver
        baseDriver.tearDown();
    }
}