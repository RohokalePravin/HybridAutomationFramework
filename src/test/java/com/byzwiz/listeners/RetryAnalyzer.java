package com.byzwiz.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;
    private static final int MAX_RETRY = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (count < MAX_RETRY) {
            System.out.println("🔁 Retrying test: " + result.getMethod().getMethodName() + " | Retry " + (count + 1) + " of " + MAX_RETRY);
            count++;
            return true;
        }
        return false;
    }
}
