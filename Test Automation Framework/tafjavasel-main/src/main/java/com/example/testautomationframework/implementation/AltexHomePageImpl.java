package com.example.testautomationframework.implementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.testautomationframework.models.AltexHomepage;

/**
 * Implementation of the AltexHomePage interface.
 * Provides methods to interact with the Altex home page.
 */
public class AltexHomePageImpl implements AltexHomepage {

    private WebDriver driver;

    private By searchBox = By.xpath("//input[@placeholder='Cauta produsul dorit']");
    private By productElement = By.xpath("//div[contains(@class, 'Product')]");

    /**
     * Constructor to initialize WebDriver.
     *
     * @param driver the WebDriver instance to use
     */
    public AltexHomePageImpl(WebDriver driver) {
        this.driver = driver;
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

    @Override
    public void verifyElementIsDisplayed() {
        WebElement element = driver.findElement(productElement);
        if (element.isDisplayed()) {
            System.out.println("Element is displayed");
    } else {
            System.out.println("Element is not displayed");
        }
    }


}
