package com.byzwiz.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    private static ExtentReports extent;

    public static ExtentReports getReporter() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReport.html");
            reporter.config().setReportName("ByzWiz Automation Report");
            reporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            extent.setSystemInfo("Project", "ByzWiz");
            extent.setSystemInfo("Tester", "Pravin");
            extent.setSystemInfo("Environment", System.getProperty("env", "Jenkins/Local"));
        }
        return extent;
    }
}
