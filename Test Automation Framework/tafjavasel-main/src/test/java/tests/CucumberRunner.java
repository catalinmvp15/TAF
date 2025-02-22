package tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",   // Path to the feature file
    glue = {"tests.stepdefinitions"},           // Correct glue path
    plugin = {
        "pretty",
        "json:target/cucumber-reports/cucumber.json",
        "html:target/cucumber-html-reports"
    },
    monochrome = true,                          // Cleaner console output
    tags = "@an and not @Skip"                 // Include @an and exclude @Skip
)
public class CucumberRunner {
    // Entry point for Cucumber tests
}
