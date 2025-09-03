package com.byzwiz.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static String reportPath;

    static {
        initReports();
    }

    private static void initReports() {
        // Create date folder once per run
        String dateFolder = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String baseDir = System.getProperty("user.dir") + "/test-output/" + dateFolder;
        new File(baseDir).mkdirs();

        // Unique report name by time
        String timestamp = new SimpleDateFormat("HH-mm-ss").format(new Date());
        reportPath = baseDir + "/ExtentReport_" + timestamp + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle("Automation Results");
        spark.config().setReportName("ByzWiz Test Report");
        spark.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        // System info
        extent.setSystemInfo("Tester", "Pravin");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    public static synchronized ExtentReports getExtent() {
        return extent;
    }

    public static synchronized ExtentTest getTest() {
        return test.get();
    }

    public static synchronized void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static synchronized void removeTest() {
        test.remove();
    }

    public static synchronized void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }

    // ✅ Always assign author + category
    public static synchronized void createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName)
                                      .assignAuthor("Rohokale")
                                      .assignCategory("Regression");
        setTest(extentTest);
    }

    // ✅ Logging helper to make sure INFO logs go to *all* reports
    public static synchronized void logInfo(String message) {
        ExtentTest currentTest = getTest();
        if (currentTest != null) {
            currentTest.info(message);
        }
    }

    public static String getReportPath() {
        return reportPath;
    }
}