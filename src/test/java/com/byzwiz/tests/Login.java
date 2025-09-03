package com.byzwiz.tests;

import com.aventstack.extentreports.Status;
import com.byzwiz.base.BaseTest;
import com.byzwiz.pages.LoginPage;
import com.byzwiz.reports.ExtentReportManager;
import com.byzwiz.utils.ConfigReader;
import com.byzwiz.utils.LoginDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class Login extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(Login.class);

	@Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
	public void testSuccessfulLogin(String location, String mobile, String otp) throws InterruptedException {

		logger.info("Starting Login Flow Test");
		ExtentReportManager.getTest().log(Status.INFO, "Login Test started");

		// Load base URL from config and navigate
		String url = ConfigReader.getProperties().getProperty("baseUrl");
		logger.info("Navigating to URL: {}", url);
		driver.get(url);

		// Page object instantiation
		LoginPage loginPage = new LoginPage(driver);
		
		// Log in using the updated method signature
		logger.info("Logging in with mobile number: {}", mobile);
		loginPage.login(location, mobile, otp);

		Thread.sleep(2000);
		
		logger.info("Login Test Completed Successfully");
		ExtentReportManager.getTest().log(Status.PASS, "Login Test Flow completed successfully");
	}
}