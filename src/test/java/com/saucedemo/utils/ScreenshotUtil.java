package com.saucedemo.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class ScreenshotUtil implements ITestListener {

    private static String safeName(String name) {
        return name.replaceAll("[^a-zA-Z0-9._-]", "_");
    }

    private static void saveScreenshot(WebDriver driver, String testName) {
        try {
            if (!(driver instanceof TakesScreenshot ts)) return;

            byte[] png = ts.getScreenshotAs(OutputType.BYTES);
            Path dir = Path.of("target", "screenshots");
            Files.createDirectories(dir);

            String timestamp = LocalDateTime.now().toString().replace(":", "-");
            Path file = dir.resolve(safeName(testName) + "_" + timestamp + ".png");
            Files.write(file, png);

            System.out.println("[SCREENSHOT] Saved: " + file.toAbsolutePath());
        } catch (Exception e) {
            System.out.println("[SCREENSHOT] Failed to save screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object instance = result.getInstance();
        try {
            // Expect BaseTest has 'driver' field
            WebDriver driver = (WebDriver) instance.getClass().getSuperclass().getDeclaredField("driver").get(instance);
            saveScreenshot(driver, result.getName());
        } catch (Exception ignored) {
            // if reflection fails, don't crash the suite
        }
    }

    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
}
