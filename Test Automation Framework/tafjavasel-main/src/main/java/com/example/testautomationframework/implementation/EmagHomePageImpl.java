package com.example.testautomationframework.implementation;

import com.example.testautomationframework.models.EmagHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Implementation of the EmagHomePage interface.
 */
public class EmagHomePageImpl implements EmagHomePage {
    private WebDriver driver;

    /**
     * Constructor for EmagHomePageImpl.
     *
     * @param driver the WebDriver instance to use
     */
    public EmagHomePageImpl(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Searches for a product on the eMAG website.
     *
     * @param product the product to search for
     */
    @Override
    public void searchProduct(String product) {
        WebElement searchBox = driver.findElement(By.id("searchboxTrigger"));
        searchBox.sendKeys(product);
        searchBox.submit();
    }

    @Override
    public void selectFirstProduct() {
        WebElement firstProduct = driver.findElement(By.cssSelector(".card-item"));
        firstProduct.click();
    }
}
