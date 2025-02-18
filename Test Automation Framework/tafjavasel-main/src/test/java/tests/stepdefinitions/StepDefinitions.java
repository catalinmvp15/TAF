package tests.stepdefinitions;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.example.testautomationframework.implementation.EmagHomePageImpl;
import com.example.testautomationframework.implementation.EmagProductPageImpl;
import com.example.testautomationframework.models.EmagHomePage;
import com.example.testautomationframework.models.EmagProductPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Step definitions for eMAG search functionality.
 */
public class StepDefinitions {

    private EdgeDriver driver;
    EmagHomePage emagHomePage;
    EmagProductPage emagProductPage;

    // /**
    //  * Sets up the WebDriver before each scenario.
    //  */
    // @Before
    // public void setUp() {
    //     EdgeOptions options = new EdgeOptions();
        
    //     // Generate a unique user data directory
    //     Path userDataDir = Paths.get("C:\\Users\\Catalin\\Documents\\TAF\\Test Automation Framework\\tafjavasel-main\\driver\\uniqueUserData_" + System.currentTimeMillis());
    //     try {
    //         Files.createDirectories(userDataDir);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     options.addArguments("user-data-dir=" + userDataDir.toString());

    //     driver = new EdgeDriver(options);
    //     driver.manage().window().maximize();
    // }

    // /**
    //  * Tears down the WebDriver after each scenario.
    //  */
    // @After
    // public void tearDown() {
    //     if (driver != null) {
    //         driver.quit();
    //     }
    // }

    /**
     * Opens the eMAG website.
     */
    @Given("I open the eMAG website")
    public void i_open_the_e_mag_website() {
        driver.get("https://www.emag.ro");
        emagHomePage = new EmagHomePageImpl(driver);
        emagProductPage = new EmagProductPageImpl(driver);
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

    @Then("I extract and display the product list with prices")
    public void extractProductDetails() {
        emagProductPage.extractProductDetailsFromFirstResult();
    }

    @Then("I extract and display the product ratings and reviews")
    public void extractProductReviews() {
        emagProductPage.extractProductReviews();
    }

    @And("I select the first product")
    public void selectFirstProduct() {
        emagHomePage.selectFirstProduct();
    }
}