package com.byzwiz.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent = createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static ExtentReports createInstance() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        reporter.config().setReportName("ByzWiz Test Report");
        reporter.config().setDocumentTitle("Automation Results");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester", "Pravin");
        return extentReports;
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

    public static synchronized void createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        setTest(extentTest);
    }
}
