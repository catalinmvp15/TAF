package com.example.testautomationframework.models;

/**
 * Interface representing the eMAG product page.
 * Provides methods to extract product details from the eMAG website.
 */
public interface EmagProductPage {
    
    /**
     * Extracts product details (titles and prices) from the eMAG website.
     * Prints the product details to the console and saves them to CSV and JSON files.
     */
    void extractProductDetails();

    void extractProductReviews();

    void extractProductDetailsFromFirstResult();
}
