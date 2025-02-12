package tests.stepdefinitions;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.example.testautomationframework.implementation.EmagHomePageImpl;
import com.example.testautomationframework.models.EmagHomePage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for eMAG search functionality.
 */
public class StepDefinitions {

    WebDriver driver;
    EmagHomePage emagHomePage;

    /**
     * Sets up the WebDriver before each scenario.
     */
    @Before
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\Catalin\\Documents\\TAF\\Drivers\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    /**
     * Tears down the WebDriver after each scenario.
     */
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Opens the eMAG website.
     */
    @Given("I open the eMAG website")
    public void i_open_the_e_mag_website() {
        driver.get("https://www.emag.ro");
        emagHomePage = new EmagHomePageImpl(driver);
    }

    /**
     * Searches for a product on the eMAG website.
     * 
     * @param product the product to search for
     */
    @When("I search for {string}")
    public void i_search_for(String product) {
        emagHomePage.searchProduct(product);
    }

    /**
     * Verifies that search results are displayed.
     */
    @Then("I should see search results")
    public void i_should_see_search_results() {
        boolean isResultsDisplayed = driver.findElements(By.cssSelector(".card-item")).size() > 0;
        assertTrue("Search results are not displayed!", isResultsDisplayed);
    }

}