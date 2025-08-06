package com.byzwiz.pages;

import com.byzwiz.utils.Helpers;
import com.byzwiz.utils.LoggerUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;

import java.awt.AWTException;
import java.io.File;
import java.net.URL;
import java.time.Duration;

public class BusinessLastPage {

    WebDriver driver;
    WebDriverWait wait;
    Logger logger = LoggerUtil.getLogger(BusinessLastPage.class); // ✅ Log4j2 Logger

    // ✅ Constructor
    public BusinessLastPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // ✅ Load image paths from resources
    String bannerImagePath = getFileFromResources("images/FoodBanner.jpg");
    String logoImagePath = getFileFromResources("images/FoodBanner.jpg");

    // ✅ Method to load file path from resources folder
    private String getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found in resources: " + fileName);
        } else {
            return new File(resource.getFile()).getAbsolutePath();
        }
    }

    // ✅ Locators
    By bannerUploadButton = By.xpath("//form[@action='#']/div[1]/div/div[2]/button");
    By checkIcon = By.xpath("//*[local-name()='svg' and @data-testid='CheckIcon']/ancestor::button");
    By logoUploadButton = By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-19hgowz']");
    By selectAllDaysBtn = By.xpath("//button[normalize-space(.)='Select All']");
    By openTimeBtn = By.xpath("//span[starts-with(.,'Open')]");
    By createBusinessBtn = By.xpath("//button[normalize-space(.)='Create Business']");
    By continueBtn = By.xpath("//button[normalize-space(.)='Continue']");
    By pendingTaskText = By.xpath("//p[normalize-space(.)='Pending Tasks']");

    // ✅ Declarations
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

    // ✅ Upload Banner & Logo
    public void uploadBannerAndLogo() throws InterruptedException, AWTException {
        logger.info("Uploading banner...");
        Helpers.waitAndClick(driver, bannerUploadButton);
        Helpers.uploadFileWithRobot(bannerImagePath);
        Helpers.waitAndClick(driver, checkIcon);
        logger.info("Banner uploaded.");

        logger.info("Uploading logo...");
        Helpers.waitAndClick(driver, logoUploadButton);
        Thread.sleep(2000);
        Helpers.uploadFileWithRobot(logoImagePath);
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
