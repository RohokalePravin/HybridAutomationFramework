package com.byzwiz.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.markuputils.ExtentColor;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ExtentManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");

            // ---- Customization ----
            spark.config().setDocumentTitle("Byzwiz Store-builder Automation Report");
            spark.config().setReportName("Seller Regression Test Execution");
            spark.config().setTheme(Theme.DARK);
            spark.config().setTimelineEnabled(true);
            spark.config().setEncoding("utf-8");
            spark.config().setCss(".test-status.pass { background-color: #28a745 !important; } " +
                                  ".test-status.fail { background-color: #dc3545 !important; }");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // System info
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Tester", "Pravin");
        }
        return extent;
    }

    // Create a test for each method
    public static ExtentTest createTest(String testName) {
        ExtentTest extentTest = getInstance().createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    // Assign author to test
    public static void assignAuthor(String author) {
        getTest().assignAuthor(author);
    }

    // Assign category to test
    public static void assignCategory(String category) {
        getTest().assignCategory(category);
    }

    // Log step with colored label
    public static void logPass(String message) {
        getTest().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
    }

    public static void logFail(String message) {
        getTest().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
    }

    public static void logInfo(String message) {
        getTest().info(message);
    }

    // ✅ Add screenshot as Base64 (embedded in HTML)
 // ✅ Always embed screenshots as Base64 (works in email + saved reports)
    public static void addScreenshot(WebDriver driver, String screenshotName) {
        try {
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            getTest().addScreenCaptureFromBase64String(base64Screenshot, screenshotName);
        } catch (Exception e) {
            getTest().warning("⚠️ Failed to attach screenshot: " + e.getMessage());
        }
    }

}