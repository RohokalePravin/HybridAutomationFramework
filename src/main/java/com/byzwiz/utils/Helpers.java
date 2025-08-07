package com.byzwiz.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
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
                } catch (InterruptedException ignored) {}
            } catch (Exception e) {
                throw new RuntimeException("Click failed for locator: " + locator.toString(), e);
            }
        }
        throw new RuntimeException("Unable to click after multiple attempts: " + locator.toString());
    }

    // ✅ File Upload via Robot (only for local GUI mode)
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

    // ✅ For resolving image path
    public static String getImagePath(String fileName) {
        return new File("src/test/resources/images/" + fileName).getAbsolutePath();
    }

    // ✅ Wait and return element
    public static WebElement waitAndReturnElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // ✅ Check if running in Jenkins
    public static boolean isRunningInJenkins() {
        return System.getenv("JENKINS_HOME") != null;
    }

     // Detect headless mode from WebDriver
    public static boolean isHeadless(WebDriver driver) {
        Capabilities caps = ((HasCapabilities) driver).getCapabilities();
        Object headlessProp = caps.getCapability("headless");
        return headlessProp != null && headlessProp.toString().equalsIgnoreCase("true");
    }


    // ✅ Universal upload method using <input type="file">
    public static void uploadFileToHiddenInput(WebDriver driver, By inputLocator, String fileName) {
        WebElement input = waitAndReturnElement(driver, inputLocator);

        // Force input visible using JavaScript if needed
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", input);

        // Send absolute path to the input field
        input.sendKeys(getImagePath(fileName));
    }
}
