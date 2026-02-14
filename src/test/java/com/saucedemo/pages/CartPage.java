package com.saucedemo.pages;

import com.saucedemo.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;

    private final By cartTitle = By.cssSelector(".title");
    private final By checkoutBtn = By.id("checkout");
    private final By continueShoppingBtn = By.id("continue-shopping");
    private final By cartItems = By.cssSelector(".cart_item");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        Waits.visible(driver, cartTitle, 10);
    }

    public int cartItemsCount() {
        return driver.findElements(cartItems).size();
    }

    public CheckoutStepOnePage checkout() {
        Waits.clickable(driver, checkoutBtn, 10).click();
        Waits.urlContains(driver, "checkout-step-one", 10);
        return new CheckoutStepOnePage(driver);
    }

    public ProductsPage continueShopping() {
        Waits.clickable(driver, continueShoppingBtn, 10).click();
        Waits.urlContains(driver, "inventory", 10);
        return new ProductsPage(driver);
    }
}
