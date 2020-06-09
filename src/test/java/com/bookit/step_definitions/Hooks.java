package com.bookit.step_definitions;


import com.bookit.utilities.DBUtility;
import com.bookit.utilities.Driver;
import com.bookit.utilities.Environment;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    /**
     * This hook will be executed only for scenarios that are annotated with @db tag
     */

    @Before("@db")
    public void dbSetup() {
        DBUtility.createDBConnection(Environment.DB_HOST, Environment.DB_USERNAME, Environment.DB_PASSWORD);
    }

    /**
     * This hook will be executed only for scenarios that are annotated with @db tag
     */

    @After("@db")
    public void dbTearDown() {
        DBUtility.destroy();
    }

    /**
     * This hook will be executed only for scenarios that are annotated with @ui tag
     */
    @Before("@ui")
    public void uiSetup() {
        Driver.getDriver().manage().window().maximize();

    }

    /**
     * This hook will be executed only for scenarios that are annotated with @ui tag
     */
    @After("@ui")
    public void uiTeardown() {
        Driver.closeDriver();
    }


    @After()
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            // how to check if scenario failed
            TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
            byte[] image = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            // attach screenshot to the report
            scenario.embed(image, "image/png", scenario.getName());
        }
        System.out.println("Test clean up");
        Driver.closeDriver();

    }

}
