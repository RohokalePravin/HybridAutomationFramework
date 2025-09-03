package com.byzwiz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.byzwiz.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class LoginPage {
	WebDriver driver;
	private static final Logger log = LoggerUtil.getLogger(LoginPage.class);
	private WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait with a 10-second
																		// timeout
		log.info("Login Page initialized");
	}

	// Locators
//	private final By enterLocationField = By.xpath("//DIV[contains(@class,'page_searchSection__wJTu_')]/DIV/DIV/input");
//	private final By selectLocationDropdown = By.xpath("//DIV[contains(@class,'page_searchSection__wJTu_')]/DIV/DIV/DIV[contains(text(), 'Kad Nagar Undri')]");
	private final By selectCard = By.xpath("//IMG[@src=\"/static/landingBuyIcon.png\"]");
	private final By loginBtn = By.xpath("//DIV[contains(@class,'navbar_loginOptions__vkHZP')]/P[.='Log In']");
	private final By mobileField = By.xpath(
			"//DIV[contains(@class,'loginmodal_outerMostLoginWrapper__acRCX')]/DIV/DIV/INPUT[@class='input_inputField__QSlZN undefined']");
	private final By getOTPBtn = By.xpath("//BUTTON[.='Get OTP']");
	private final By digits1 = By.xpath("//div[contains(@class,'otpfield_otpBoxesWrapper')]//input[1]");
	private final By digits2 = By.xpath("//div[contains(@class,'otpfield_otpBoxesWrapper')]//input[2]");
	private final By digits3 = By.xpath("//div[contains(@class,'otpfield_otpBoxesWrapper')]//input[3]");
	private final By digits4 = By.xpath("//div[contains(@class,'otpfield_otpBoxesWrapper')]//input[4]");
	private final By submitBtn = By.xpath("//BUTTON[normalize-space(.)='Verify and Proceed']");

	public void login(String location, String mobile, String otp) {
		try {
			log.info("Attempting to log in with mobile number: {}", mobile);

			// Step 2: Scroll to and select the food card
			WebElement cardElement = wait.until(ExpectedConditions.presenceOfElementLocated(selectCard));
			((org.openqa.selenium.JavascriptExecutor) driver)
					.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", cardElement);
			wait.until(ExpectedConditions.elementToBeClickable(selectCard)).click();

			// Step 3: Click the login button
			wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();

			// Step 4: Enter mobile number and get OTP
			wait.until(ExpectedConditions.elementToBeClickable(mobileField)).sendKeys(mobile);
			wait.until(ExpectedConditions.elementToBeClickable(getOTPBtn)).click();

			// Step 5: Enter the 4-digit OTP
			char[] otpDigits = otp.toCharArray();
			wait.until(ExpectedConditions.elementToBeClickable(digits1)).sendKeys(String.valueOf(otpDigits[0]));
			wait.until(ExpectedConditions.elementToBeClickable(digits2)).sendKeys(String.valueOf(otpDigits[1]));
			wait.until(ExpectedConditions.elementToBeClickable(digits3)).sendKeys(String.valueOf(otpDigits[2]));
			wait.until(ExpectedConditions.elementToBeClickable(digits4)).sendKeys(String.valueOf(otpDigits[3]));

			Thread.sleep(2000);
			// Step 6: Submit the OTP
			wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();

			log.info("Login submitted successfully");

		} catch (Exception e) {
			log.error("Login failed: {}", e.getMessage(), e);
			throw new RuntimeException("Login failed", e);
		}
	}

}