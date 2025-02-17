package com.example.testautomationframework.implementation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.testautomationframework.models.EmagProductPage;
import com.example.testautomationframework.utility.DataExporter;

/**
 * Implementation of the EmagProductPage interface.
 * Provides methods to extract product details from the eMAG website.
 */
public class EmagProductPageImpl implements EmagProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By productTitles = By.cssSelector(".card-v2-title a");
    private By productPrices = By.cssSelector(".product-new-price");
    private By productRatings = By.cssSelector(".star-rating span");
    private By productReviews = By.cssSelector(".product-review span");
    private static final Logger logger = Logger.getLogger(EmagProductPageImpl.class.getName());

    /**
     * Constructor to initialize WebDriver.
     *
     * @param driver the WebDriver instance to use
     */
    public EmagProductPageImpl(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait time
    }

    /**
     * Extracts product details (titles and prices) from the eMAG website.
     * Prints the product details to the console and saves them to CSV and JSON files.
     */
    @Override
    public void extractProductDetails() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productTitles));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productPrices));

        List<WebElement> titles = driver.findElements(productTitles);
        List<WebElement> prices = driver.findElements(productPrices);

        List<Map<String, String>> productList = new ArrayList<>();
        logger.info("🔍 Products found on eMAG:");

        for (int i = 0; i < Math.min(titles.size(), prices.size()); i++) {
            String name = titles.get(i).getText();
            String price = prices.get(i).getText();

            Map<String, String> product = new HashMap<>();
            product.put("name", name);
            product.put("price", price);
            productList.add(product);

            logger.info((i + 1) + ". " + name + " - " + price);
        }

        // Save data
        DataExporter.saveToCSV(productList, "target/emag_products.csv");
        DataExporter.saveToJSON(productList, "target/emag_products.json");
    }

    @Override
    public void extractProductReviews() {
        List<WebElement> ratings = driver.findElements(productRatings);
        List<WebElement> reviews = driver.findElements(productReviews);
    
        for (int i = 0; i < Math.min(ratings.size(), reviews.size()); i++) {
            logger.info("⭐ Rating: " + ratings.get(i).getText() + " - " + reviews.get(i).getText() + " reviews");
        }
    }
}