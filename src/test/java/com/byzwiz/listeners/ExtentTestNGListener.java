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
    public void onStart(ITestContext context) {
        System.out.println("🚀 Test Suite Started: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentReportManager.createTest(testName);
        ExtentReportManager.getTest().log(Status.INFO, "🔹 Test Started: " + testName);
        System.out.println("▶️ Test Started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "✅ Test Passed");
        System.out.println("✅ Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ScreenshotUtil.captureScreenshot(BaseTest.getDriver(), testName);
        ExtentReportManager.getTest().log(Status.FAIL, "❌ Test Failed: " + result.getThrowable());
        System.out.println("❌ Test Failed: " + testName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().log(Status.SKIP, "⏭️ Test Skipped: " + result.getThrowable());
        System.out.println("⏭️ Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result); // reuse failure logic
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optional: log partial success
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🏁 Test Suite Finished: " + context.getName());
        ExtentReportManager.flushReports();
    }
}
