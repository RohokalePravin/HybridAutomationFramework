package com.byzwiz.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class Helpers {
	protected WebDriver driver;
	protected WebDriverWait wait;

	// ✅ Constructor
	public Helpers(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	// ✅ Static click with retries + scroll
	public static void waitAndClick(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		int attempts = 0;
		while (attempts < 5) {
			try {
				WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
				Thread.sleep(500);
				element.click();
				return;
			} catch (ElementClickInterceptedException e) {
				System.out.println("Retrying click due to interception...");
				attempts++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ignored) {
				}
			} catch (Exception e) {
				throw new RuntimeException("Click failed for locator: " + locator.toString(), e);
			}
		}
		throw new RuntimeException("Unable to click after multiple attempts: " + locator.toString());
	}

	// ✅ File Upload via Robot
	public static void uploadFileWithRobot(String filePath) throws AWTException {
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		StringSelection selection = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	// ✅ Scroll to element and type text
	public static void scrollAndType(WebDriver driver, By locator, String text) {
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
		element.clear();
		element.sendKeys(text);
	}
}