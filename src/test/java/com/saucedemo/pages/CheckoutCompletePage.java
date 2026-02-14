package com.saucedemo.pages;

import com.saucedemo.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    private final WebDriver driver;

    private final By completeHeader = By.cssSelector(".complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        Waits.urlContains(driver, "checkout-complete", 10);
    }

    public String getHeader() {
        return driver.findElement(completeHeader).getText();
    }
}
