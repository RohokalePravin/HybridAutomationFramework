package com.byzwiz.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class ScreenshotUtil {

    // ✅ Capture screenshot and return Base64 string for ExtentReports
	public static String captureScreenshot(WebDriver driver, String screenshotName) {
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    return ts.getScreenshotAs(OutputType.BASE64);
	}

}