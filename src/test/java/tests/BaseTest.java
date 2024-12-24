package tests;

import driver.BaseDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected BaseDriver baseDriver = new BaseDriver();
    @BeforeClass
    protected void setDriver(){
        baseDriver.setUp();
    }

    @AfterClass
    protected void quitDriver(){
        baseDriver.tearDown();
    }
}
