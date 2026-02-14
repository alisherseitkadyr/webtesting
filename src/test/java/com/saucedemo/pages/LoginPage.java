package com.saucedemo.pages;

import com.saucedemo.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By error = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage typeUsername(String value) {
        Waits.visible(driver, username, 10).clear();
        driver.findElement(username).sendKeys(value);
        return this;
    }

    public LoginPage typePassword(String value) {
        Waits.visible(driver, password, 10).clear();
        driver.findElement(password).sendKeys(value);
        return this;
    }

    public ProductsPage loginValid(String user, String pass) {
        typeUsername(user);
        typePassword(pass);
        Waits.clickable(driver, loginBtn, 10).click();
        Waits.urlContains(driver, "inventory", 10);
        return new ProductsPage(driver);
    }

    public LoginPage loginInvalid(String user, String pass) {
        typeUsername(user);
        typePassword(pass);
        Waits.clickable(driver, loginBtn, 10).click();
        Waits.visible(driver, error, 10);
        return this;
    }

    public String getErrorText() {
        return driver.findElement(error).getText();
    }
}
