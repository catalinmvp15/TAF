package com.example.testautomationframework.implementation;

import org.openqa.selenium.WebDriver;

import com.example.testautomationframework.models.AltexProduct;

public class AltexProductImpl implements AltexProduct{
     private WebDriver driver;

    public AltexProductImpl(WebDriver driver) {
        this.driver = driver;
    }
}
