package com.byzwiz.utils;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private static final Logger log = LoggerUtil.getLogger(ScreenshotUtil.class);

    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = testName + "_" + timestamp + ".png";
        String screenshotPath = "screenshots/" + screenshotName;

        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.createDirectories(new File("screenshots").toPath()); // Ensure folder exists
            Files.copy(screenshot.toPath(), new File(screenshotPath).toPath());

            log.info("Screenshot saved: " + screenshotPath);
            return screenshotPath;
        } catch (IOException e) {
            log.error("Failed to save screenshot for " + testName, e);
            return null;
        }
    }
}
