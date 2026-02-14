package com.saucedemo.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public final class DriverFactory {
    private DriverFactory() {}

    public static WebDriver createDriver() {
        String rawBrowser = System.getProperty("browser");
        String browser = (rawBrowser == null || rawBrowser.trim().isEmpty())
                ? "chrome"
                : rawBrowser.trim().toLowerCase();

        String rawHeadless = System.getProperty("headless", "false");
        boolean headless = Boolean.parseBoolean(rawHeadless);

        WebDriver driver;
        switch (browser) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                if (headless) options.addArguments("-headless");
                driver = new FirefoxDriver(options);
            }
            case "chrome" -> {
    WebDriverManager.chromedriver().setup();

    ChromeOptions options = new ChromeOptions();

    if (headless) {
        options.addArguments("--headless=new");
    }

    options.addArguments("--window-size=1920,1080");

    // 🔥 Disable password manager & breach alerts
    options.addArguments("--disable-notifications");
    options.addArguments("--disable-popup-blocking");
options.addArguments("--guest");

    // 🔥 Most important: disable password manager services
    options.addArguments("--disable-features=PasswordManagerOnboarding,PasswordCheck");
    options.addArguments("--disable-save-password-bubble");

    // 🔥 Disable credential service
    options.setExperimentalOption("prefs", java.util.Map.of(
            "credentials_enable_service", false,
            "profile.password_manager_enabled", false
    ));

    driver = new ChromeDriver(options);
}


            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        return driver;
    }
}
