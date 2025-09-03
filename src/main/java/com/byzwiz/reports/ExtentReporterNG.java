package com.byzwiz.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	private static ExtentReports extent;

	public static ExtentReports getReporter() {
		if (extent == null) {
			// initialize ExtentReports with HTMLReporter, config, etc.
			ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/extent.html");
			extent = new ExtentReports();
			extent.attachReporter(reporter);
		}
		return extent;
	}
}