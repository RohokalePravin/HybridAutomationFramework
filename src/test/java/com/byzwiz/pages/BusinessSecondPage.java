package com.byzwiz.pages;

import com.byzwiz.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BusinessSecondPage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    private static final Logger logger = LoggerUtil.getLogger(BusinessSecondPage.class);

    public BusinessSecondPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.js = (JavascriptExecutor) driver;
    }

    // ✅ Reusable force click logic
    public void forceClickNoButton(By locator) throws InterruptedException {
        logger.info("Attempting to force click 'No' button: " + locator);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(locator));
        js.executeScript("arguments[0].click();", button);
        Thread.sleep(500);

        String classValue = button.getAttribute("class");
        if (classValue == null || (!classValue.toLowerCase().contains("selected") && !classValue.toLowerCase().contains("active"))) {
            logger.warn("'No' button did not get selected, retrying...");
            js.executeScript("arguments[0].click();", button);
            Thread.sleep(500);
        }
        logger.info("'No' button clicked and verified.");
    }

    // ✅ Address form fill logic
    public void fillAddress1(String gst, String fssai, String address1, String address2, String pincode, String pickupLabelText, boolean pickupSame) throws InterruptedException {
        logger.info("Filling address form: GST=" + gst + ", FSSAI=" + fssai);

        // GST and FSSAI selections
        if (gst.equalsIgnoreCase("No")) {
            forceClickNoButton(By.xpath("//form[@action='#']/div[1]//button[.='No']"));
        }
        if (fssai.equalsIgnoreCase("No")) {
            forceClickNoButton(By.xpath("//form[@action='#']/div[2]//button[.='No']"));
        }

        // Address
        logger.info("Entering address details...");
        driver.findElement(By.id("address")).sendKeys(address1);
        driver.findElement(By.xpath("//form[@action='#']/div[3]//input[@placeholder='Enter your address']")).sendKeys(address2);
        driver.findElement(By.id("pincode")).sendKeys(pincode);

        // Pickup label + same checkbox
        WebElement pickupLabel = driver.findElement(By.id("pickupLabel"));
        pickupLabel.click();

        WebElement pickupSameCheckbox = driver.findElement(By.name("isPickUpAddressSame"));
        if (pickupSame) {
            js.executeScript("arguments[0].click();", pickupSameCheckbox);
            logger.info("Pickup address marked as same.");
        }

        pickupLabel.sendKeys(pickupLabelText);

        // ✅ Save and Continue
        By saveBtnLocator = By.xpath("//button[normalize-space(.)='Save and Continue']");
        WebElement saveAndContinue = wait.until(ExpectedConditions.elementToBeClickable(saveBtnLocator));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", saveAndContinue);
        Thread.sleep(500);
        saveAndContinue.click();
        logger.info("Clicked 'Save and Continue' on address page.");
    }

    @SuppressWarnings("unused")
    private void waitAndClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}
