package com.saucedemo.pages;

import com.saucedemo.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage {
    private final WebDriver driver;

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By error = By.cssSelector("[data-test='error']");

    public CheckoutStepOnePage(WebDriver driver) {
        this.driver = driver;
        Waits.urlContains(driver, "checkout-step-one", 10);
    }

    public CheckoutStepOnePage fill(String fn, String ln, String pc) {
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(fn);
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(ln);
        driver.findElement(postalCode).clear();
        driver.findElement(postalCode).sendKeys(pc);
        return this;
    }

    public CheckoutOverviewPage continueCheckout() {
        Waits.clickable(driver, continueBtn, 10).click();
        Waits.urlContains(driver, "checkout-step-two", 10);
        return new CheckoutOverviewPage(driver);
    }

    public CheckoutStepOnePage continueExpectError() {
        Waits.clickable(driver, continueBtn, 10).click();
        Waits.visible(driver, error, 10);
        return this;
    }

    public String getErrorText() {
        return driver.findElement(error).getText();
    }
}
