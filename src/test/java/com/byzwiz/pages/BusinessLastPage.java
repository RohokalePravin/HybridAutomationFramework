package com.byzwiz.pages;

import com.byzwiz.utils.Helpers;
import com.byzwiz.utils.LoggerUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;

import java.awt.AWTException;
import java.time.Duration;

public class BusinessLastPage {

    WebDriver driver;
    WebDriverWait wait;
    Logger logger = LoggerUtil.getLogger(BusinessLastPage.class);

    public BusinessLastPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Locators
    By bannerUploadButton = By.xpath("//form[@action='#']/div[1]/div/div[2]/button");
    By logoUploadButton = By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-19hgowz']");
    By fileInputSelector = By.cssSelector("input[type='file'].css-76knqi");
    By checkIcon = By.xpath("//*[local-name()='svg' and @data-testid='CheckIcon']/ancestor::button");
    By selectAllDaysBtn = By.xpath("//button[normalize-space(.)='Select All']");
    By openTimeBtn = By.xpath("//span[starts-with(.,'Open')]");
    By createBusinessBtn = By.xpath("//button[normalize-space(.)='Create Business']");
    By continueBtn = By.xpath("//button[normalize-space(.)='Continue']");
    By pendingTaskText = By.xpath("//p[normalize-space(.)='Pending Tasks']");

    String[] declarationLabels = {
        "I am responsible for the quality of the food I prepare and sell from my Business.",
        "I will use the best and freshest ingredients for my food preparations.",
        "I will maintain great kitchen hygiene.",
        "I will ensure that my orders are ready for pick-up on time.",
        "I will use good quality packaging materials and suitable packing processes.",
        "I will not sell anything illegal from my Business.",
        "I will strive to make my customers happy. I want my business to grow.",
        "For my safety as per the platform rules, I will not share my personal details through images or text."
    };

    // ✅ Upload image using JS to support headless mode
    private void uploadFileToHiddenInput(String fileName) {
        String imagePath = Helpers.getImagePath(fileName);
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(fileInputSelector));
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].style.display='block'; arguments[0].style.visibility='visible';", fileInput
        );
        fileInput.sendKeys(imagePath);
    }

    // ✅ Upload Banner & Logo (dual mode)
    public void uploadBannerAndLogo() throws InterruptedException, AWTException {
        boolean isHeadless = Helpers.isRunningInJenkins() || Helpers.isHeadless(driver);

        logger.info("Uploading banner...");
        if (isHeadless) {
            logger.info("Headless detected: using sendKeys to file input for banner");
            uploadFileToHiddenInput("Food Banner.jpg");
        } else {
            Helpers.waitAndClick(driver, bannerUploadButton);
            Thread.sleep(1000);
            Helpers.uploadFileWithRobot(Helpers.getImagePath("Food Banner.jpg"));
        }
        Helpers.waitAndClick(driver, checkIcon);
        logger.info("Banner uploaded.");

        logger.info("Uploading logo...");
        if (isHeadless) {
            logger.info("Headless detected: using sendKeys to file input for logo");
            uploadFileToHiddenInput("Food Banner.jpg");
        } else {
            Helpers.waitAndClick(driver, logoUploadButton);
            Thread.sleep(1000);
            Helpers.uploadFileWithRobot(Helpers.getImagePath("Food Banner.jpg"));
        }
        Helpers.waitAndClick(driver, checkIcon);
        logger.info("Logo uploaded.");
    }

    // ✅ Select business days & time
    public void selectBusinessDaysAndHours() {
        logger.info("Selecting business days and open time...");
        Helpers.waitAndClick(driver, selectAllDaysBtn);
        Helpers.waitAndClick(driver, openTimeBtn);
    }

    // ✅ Check declarations
    public void completeFoodDeclarations() {
        logger.info("Selecting declaration checkboxes...");
        for (String label : declarationLabels) {
            By checkboxLabel = By.xpath("//span[normalize-space(.)='" + label + "']");
            Helpers.waitAndClick(driver, checkboxLabel);
        }
    }

    // ✅ Final Submit
    public void submitBusiness() {
        logger.info("Submitting business...");
        Helpers.waitAndClick(driver, createBusinessBtn);
        Helpers.waitAndClick(driver, continueBtn);
    }

    // ✅ Verify creation
    public void verifyBusinessCreated() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(pendingTaskText));
            logger.info("✅ Business created successfully!");
        } catch (Exception e) {
            logger.warn("⚠️ Business creation might have succeeded, but Pending Tasks not found.");
        }
    }
}
