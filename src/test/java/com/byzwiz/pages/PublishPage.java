package com.byzwiz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.byzwiz.utils.Helpers;
import com.byzwiz.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;

public class PublishPage {

    WebDriver driver;
    private static final Logger logger = LoggerUtil.getLogger(PublishPage.class);

    public PublishPage(WebDriver driver) {
        this.driver = driver;
    }

    public void publishBusiness() throws InterruptedException {
        logger.info("Navigating to Dashboard...");
        Helpers.waitAndClick(driver, By.linkText("Dashboard"));
        Thread.sleep(1000);

        logger.info("Clicking on 'Publish Business'...");
        Helpers.waitAndClick(driver, By.xpath("//p[normalize-space(.)='Publish Business']"));

        logger.info("✅ Business Published Successfully!");
    }
}
