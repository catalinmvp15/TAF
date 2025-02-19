package com.example.testautomationframework.implementation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.testautomationframework.models.AltexHomepage;

/**
 * Implementation of the AltexHomePage interface.
 * Provides methods to interact with the Altex home page.
 */
public class AltexHomePageImpl implements AltexHomepage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By searchBox = By.xpath("//input[@placeholder='Cauta produsul dorit']");
    private By productElement = By.xpath("//div[contains(@class, 'Product')]");

    /**
     * Constructor to initialize WebDriver and WebDriverWait.
     *
     * @param driver the WebDriver instance to use
     */
    public AltexHomePageImpl(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Initialize WebDriverWait with 20 seconds wait time
    }

    /**
     * Searches for a product by its ID.
     *
     * @param productId the ID of the product to search for
     */
    @Override
    public void searchProductByID(String productID) {
        WebElement searchInput = driver.findElement(searchBox);
        searchInput.sendKeys(productID);
        searchInput.submit();
    }

    /**
     * Verifies that the product element is displayed.
     */
    @Override
    public void verifyElementIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productElement));
        WebElement element = driver.findElement(productElement);
        if (element.isDisplayed()) {
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }
    }
}
