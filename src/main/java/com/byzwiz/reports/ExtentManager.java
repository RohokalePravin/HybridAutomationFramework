package com.byzwiz.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            // Define the output report path
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
            spark.config().setDocumentTitle("Byzwiz Automation Report");
            spark.config().setReportName("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // System info - helpful in Jenkins environments
            extent.setSystemInfo("Project", "ByzWiz Framework");
            extent.setSystemInfo("Tester", "Pravin");
            extent.setSystemInfo("Environment", System.getProperty("env", "Jenkins/Local"));
        }
        return extent;
    }
}
