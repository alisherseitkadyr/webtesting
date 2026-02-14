package com.saucedemo.pages;

import com.saucedemo.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage {
    private final WebDriver driver;

    private final By title = By.cssSelector(".title");
    private final By cartLink = By.cssSelector(".shopping_cart_link");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By sortSelect = By.cssSelector("[data-test='product_sort_container']");
    private final By inventoryItems = By.cssSelector(".inventory_item");
    private final By menuBtn = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
private final By sortSelectAlt = By.cssSelector(".product_sort_container"); // fallback


    // buttons by item index
    private final By addToCartButtons = By.cssSelector("button.btn_inventory");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        Waits.visible(driver, title, 10);
        Waits.visible(driver, inventoryItems, 10);
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public int itemsCount() {
        return driver.findElements(inventoryItems).size();
    }

    public ProductsPage addItemByIndex(int index) {
        List<?> btns = driver.findElements(addToCartButtons);
        if (index < 0 || index >= btns.size()) {
            throw new IllegalArgumentException("Index out of range: " + index);
        }
        driver.findElements(addToCartButtons).get(index).click();
        return this;
    }

    public ProductsPage removeItemByIndex(int index) {
        // same selector: button will change to "Remove" after adding
        return addItemByIndex(index);
    }

    public int cartBadgeCount() {
        try {
            return Integer.parseInt(driver.findElement(cartBadge).getText().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    public CartPage openCart() {
        Waits.clickable(driver, cartLink, 10).click();
        Waits.urlContains(driver, "cart", 10);
        return new CartPage(driver);
    }

   public ProductsPage sortByVisibleText(String text) {
    org.openqa.selenium.WebElement dropdown;
    try {
        dropdown = com.saucedemo.utils.Waits.visible(driver, sortSelect, 10);
    } catch (org.openqa.selenium.TimeoutException e) {
        dropdown = com.saucedemo.utils.Waits.visible(driver, sortSelectAlt, 10);
    }

    org.openqa.selenium.support.ui.Select select =
            new org.openqa.selenium.support.ui.Select(dropdown);
    select.selectByVisibleText(text);
    return this;
}


    public LoginPage logout() {
        Waits.clickable(driver, menuBtn, 10).click();
        Waits.clickable(driver, logoutLink, 10).click();
        Waits.urlContains(driver, "saucedemo.com", 10);
        return new LoginPage(driver);
    }
}
