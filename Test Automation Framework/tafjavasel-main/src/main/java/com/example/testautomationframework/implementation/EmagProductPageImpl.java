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

    private By productTitles = By.cssSelector(".card-item .card-v2-title a");
    private By productPrices = By.cssSelector(".product-new-price[data-test='main-price']");
    private By productRatings = By.cssSelector(".card-item .star-rating span");
    private By productReviews = By.cssSelector(".product-review span");
    private static final Logger logger = Logger.getLogger(EmagProductPageImpl.class.getName());

    /**
     * Constructor to initialize WebDriver.
     *
     * @param driver the WebDriver instance to use
     */
    public EmagProductPageImpl(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased wait time to 20 seconds
    }

    /**
     * Extracts product details (titles and prices) from the eMAG website.
     * Prints the product details to the console and saves them to CSV and JSON files.
     */
    @Override
    public void extractProductDetails() {
        try {
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
        } catch (Exception e) {
            logger.severe("Failed to extract product details: " + e.getMessage());
        }
    }

    @Override
    public void extractProductReviews() {
        List<WebElement> ratings = driver.findElements(productRatings);
        List<WebElement> reviews = driver.findElements(productReviews);
    
        for (int i = 0; i < Math.min(ratings.size(), reviews.size()); i++) {
            logger.info("⭐ Rating: " + ratings.get(i).getText() + " - " + reviews.get(i).getText() + " reviews");
        }
    }

    /**
     * Extracts product details (name, price, rating, and link) from the product details page.
     * Prints the product details to the console and saves them to CSV and JSON files.
     */
    @Override
    public void extractProductDetailsFromFirstResult() {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".page-title")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-new-price")));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".star-rating span")));

        WebElement titleElement = driver.findElement(By.cssSelector(".page-title"));
        WebElement priceElement = driver.findElement(By.cssSelector(".product-new-price"));
        // WebElement ratingElement = driver.findElement(By.cssSelector(".star-rating span"));
        String productLink = driver.getCurrentUrl();

        String name = titleElement.getText();
        String price = priceElement.getText();
        // String rating = ratingElement.getText();

        Map<String, String> productDetails = new HashMap<>();
        productDetails.put("name", name);
        productDetails.put("price", price);
        // productDetails.put("rating", rating);
        productDetails.put("link", productLink);

        logger.info("🔍 Product details found on eMAG:");
        logger.info("Name: " + name);
        logger.info("Price: " + price);
        // logger.info("Rating: " + rating);
        logger.info("Link: " + productLink);

        // Save data
       getProductDetailsList(productDetails);
    }

    private void getProductDetailsList(Map<String, String> productDetails) {
        // Save data
        List<Map<String, String>> productDetailsList = new ArrayList<>();
        productDetailsList.add(productDetails);
        DataExporter.saveToCSV(productDetailsList, "target/emag_product_details.csv");
        DataExporter.saveToJSON(productDetailsList, "target/emag_product_details.json");
    }
}