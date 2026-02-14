package com.saucedemo.pages;

import com.saucedemo.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage {
    private final WebDriver driver;

    private final By finishBtn = By.id("finish");
    private final By itemTotal = By.cssSelector(".summary_subtotal_label");
    private final By tax = By.cssSelector(".summary_tax_label");
    private final By total = By.cssSelector(".summary_total_label");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        Waits.urlContains(driver, "checkout-step-two", 10);
    }

    public String getItemTotalText() {
        return driver.findElement(itemTotal).getText();
    }

    public String getTaxText() {
        return driver.findElement(tax).getText();
    }

    public String getTotalText() {
        return driver.findElement(total).getText();
    }

    public CheckoutCompletePage finish() {
        Waits.clickable(driver, finishBtn, 10).click();
        Waits.urlContains(driver, "checkout-complete", 10);
        return new CheckoutCompletePage(driver);
    }
}
