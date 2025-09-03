package com.byzwiz.tests;

import com.byzwiz.utils.EmailSender;
import org.testng.annotations.AfterSuite;

public class ReportEmailSender {

	@AfterSuite
	public void sendReport() {
		EmailSender
				.sendExtentReport("C:\\Users\\Pravin\\autoScript\\automationframework\\test-output\\ExtentReport.html");
	}

}