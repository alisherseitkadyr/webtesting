package com.saucedemo.base;

import com.saucedemo.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected WebDriver driver;
    protected String baseUrl;

    @BeforeMethod
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "https://www.saucedemo.com/");
        driver = DriverFactory.createDriver();
        driver.get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected LoginPage loginPage() {
        
        return new LoginPage(driver);
    }
}
