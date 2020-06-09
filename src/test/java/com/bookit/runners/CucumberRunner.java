package com.bookit.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import java.util.concurrent.locks.StampedLock;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com/bookit/step_definitions",
        features = "src/test/resources/features",
        plugin = {
                "json:target/cucumber.json"
        },
        tags = "",
        dryRun = false
)
public class CucumberRunner {


}
