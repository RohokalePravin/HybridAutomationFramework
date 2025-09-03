package com.byzwiz.listeners;

import com.aventstack.extentreports.Status;
import com.byzwiz.base.BaseTest;
import com.byzwiz.reports.ExtentReportManager;
import com.byzwiz.utils.ScreenshotUtil;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestNGListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtentReportManager.createTest(testName);
		ExtentReportManager.getTest().log(Status.INFO, "Test Started: " + testName);
		System.out.println("Test Started: " + testName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReportManager.getTest().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
	    String testName = result.getMethod().getMethodName();

	    // Capture screenshot as Base64
	    String base64Screenshot = ScreenshotUtil.captureScreenshot(BaseTest.getDriver(), testName);

	    // Log failure with screenshot
	    ExtentReportManager.getTest().fail(
	        "Test Failed: " + result.getThrowable(),
	        com.aventstack.extentreports.MediaEntityBuilder
	            .createScreenCaptureFromBase64String(base64Screenshot, testName)
	            .build()
	    );
	}




	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentReportManager.getTest().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test Suite Finished");
		ExtentReportManager.flushReports();
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test Suite Started");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}
}