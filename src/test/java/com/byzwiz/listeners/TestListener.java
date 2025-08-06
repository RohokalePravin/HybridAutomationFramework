package com.byzwiz.listeners;

import com.aventstack.extentreports.Status;
import com.byzwiz.base.BaseTest;
import com.byzwiz.reports.ExtentReportManager;
import com.byzwiz.utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("===== Test Suite Started =====");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("===== Test Suite Finished =====");
        ExtentReportManager.flushReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportManager.createTest(result.getMethod().getMethodName());
        ExtentReportManager.getTest().log(Status.INFO, "Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "✅ Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ScreenshotUtil.captureScreenshot(BaseTest.getDriver(), testName);
        ExtentReportManager.getTest().log(Status.FAIL,
                "❌ Test Failed: " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Throwable skipReason = result.getThrowable();
        ExtentReportManager.getTest().log(Status.SKIP,
                "⚠️ Test Skipped" + (skipReason != null ? ": " + skipReason.getMessage() : ""));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ExtentReportManager.getTest().log(Status.WARNING,
                "⚠️ Test failed but within success percentage.");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }
}
