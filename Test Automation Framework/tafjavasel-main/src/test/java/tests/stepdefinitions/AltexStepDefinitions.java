package tests.stepdefinitions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.example.testautomationframework.implementation.AltexHomePageImpl;
import com.example.testautomationframework.implementation.AltexProductImpl;
import com.example.testautomationframework.models.AltexHomepage;
import com.example.testautomationframework.models.AltexProduct;

import dev.failsafe.internal.util.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AltexStepDefinitions {

    private EdgeDriver driver;
    private AltexHomepage altexHomepage;
    private AltexProduct  altexProduct;

     /**
     * Sets up the WebDriver before each scenario.
     */
    @Before
    public void setUp() {
        EdgeOptions options = new EdgeOptions();
        
        // Generate a unique user data directory
        Path userDataDir = Paths.get("C:\\Users\\Catalin\\Documents\\TAF\\Test Automation Framework\\tafjavasel-main\\driver\\uniqueUserData_" + System.currentTimeMillis());
        try {
            Files.createDirectories(userDataDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        options.addArguments("user-data-dir=" + userDataDir.toString());

        driver = new EdgeDriver(options);
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

    @Given("I am on the Altex homepage")
    public void iAmOnTheAltexHomepage() {
        driver.get("https://altex.ro/");
        altexHomepage = new AltexHomePageImpl(driver);
        altexProduct = new AltexProductImpl(driver);
    }

    @When("I search for a product with ID {string}")
    public void iSearchForAProductWithID(String productID) {
        Assert.notNull(productID, "Product ID cannot be null");
        altexHomepage.searchProductByID(productID);
    }

    @Then("I should see the product details")
    public void iShouldSeeTheProductDetails() {
        altexHomepage.verifyElementIsDisplayed();
    }
    
}
