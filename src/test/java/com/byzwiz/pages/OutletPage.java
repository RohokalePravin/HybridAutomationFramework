package com.byzwiz.pages;

import com.byzwiz.utils.Helpers;
import com.byzwiz.utils.LoggerUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class OutletPage {
    WebDriver driver;
    WebDriverWait wait;
    Logger logger = LoggerUtil.getLogger(OutletPage.class);

    public OutletPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void Outlet1() throws InterruptedException {
        logger.info("Starting Outlet1 method...");
        Helpers.waitAndClick(driver, By.xpath("//BUTTON[normalize-space(.)='Continue']"));
        Helpers.waitAndClick(driver, By.xpath("//BUTTON[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-17dnra4')]"));
        Helpers.waitAndClick(driver, By.xpath("//DIV[contains(@class,'MuiBox-root css-mgfsjw')]/DIV[.='Home']"));
        Helpers.waitAndClick(driver, By.xpath("//P[normalize-space(.)='Add new pick-up point']"));

        driver.findElement(By.id("firstAddress")).sendKeys("Misty moors undri");
        driver.findElement(By.id("secondAddress")).sendKeys("Kad nagar");
        driver.findElement(By.id("pincode")).sendKeys("411060");

        Helpers.waitAndClick(driver, By.id("pickupLabel"));
        driver.findElement(By.id("pickupLabel")).sendKeys("Home");

        Thread.sleep(1000);
        Helpers.waitAndClick(driver, By.xpath("//BUTTON[normalize-space(.)='Save Pick-Up Address']"));

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//DIV[@role='status']")));
            logger.info("✅ Outlet created successfully!");
        } catch (Exception e) {
            logger.error("❌ Outlet creation failed.", e);
        }

        Helpers.waitAndClick(driver, By.xpath("//DIV[contains(@class,'MuiBox-root css-mgfsjw')]/DIV[.='Home']"));
        Helpers.waitAndClick(driver, By.xpath("//DIV[@id='__next']/HEADER/DIV/DIV/DIV[1]/DIV[2]/DIV[2]/DIV[1]/DIV[2]/P"));
        Helpers.waitAndClick(driver, By.xpath("//P[normalize-space(.)='Publish Business']"));
        logger.info("✅ Outlet Published Successfully");
    }

    public void Outlet2() throws InterruptedException {
        logger.info("Starting Outlet2 method...");
        Helpers.waitAndClick(driver, By.xpath("//BUTTON[normalize-space(.)='Continue']"));
        Helpers.waitAndClick(driver, By.xpath("//BUTTON[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-17dnra4')]"));
        Helpers.waitAndClick(driver, By.xpath("//DIV[contains(@class,'MuiBox-root css-mgfsjw')]/DIV[.='Home']"));
        Helpers.waitAndClick(driver, By.xpath("//P[normalize-space(.)='Add new pick-up point']"));

        driver.findElement(By.id("firstAddress")).sendKeys("Misty moors undri");
        driver.findElement(By.id("secondAddress")).sendKeys("Kad nagar");
        driver.findElement(By.id("pincode")).sendKeys("411060");

        Helpers.waitAndClick(driver, By.id("pickupLabel"));
        driver.findElement(By.id("pickupLabel")).sendKeys("Office");

        Thread.sleep(1000);
        Helpers.waitAndClick(driver, By.xpath("//SPAN[normalize-space(.)='Do you wish to manage deliveries by yourself ?']"));
        Helpers.waitAndClick(driver, By.id("mui-component-select-baseDistance"));
        Helpers.waitAndClick(driver, By.xpath("//LI[@data-value='4']"));

        driver.findElement(By.id("basePrice")).sendKeys("50");
        driver.findElement(By.id("perKmPrice")).sendKeys("5");

        Helpers.waitAndClick(driver, By.xpath("//BUTTON[normalize-space(.)='Save Pick-Up Address']"));

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//DIV[@role='status']")));
            logger.info("✅ Fresh Food Outlet with delivery management optional created successfully!");
        } catch (Exception e) {
            logger.error("❌ Fresh Food Outlet with delivery management optional creation failed.", e);
        }

        Helpers.waitAndClick(driver, By.xpath("//DIV[contains(@class,'MuiBox-root css-mgfsjw')]/DIV[.='Home']"));
        Helpers.waitAndClick(driver, By.xpath("//DIV[normalize-space(.)='Office']"));
        Thread.sleep(1000);
        Helpers.waitAndClick(driver, By.xpath("//P[normalize-space(.)='Publish Business']"));
        logger.info("✅ Fresh Food Outlet with delivery management optional Published Successfully");
    }

    public void Outlet3() throws InterruptedException {
        logger.info("Starting Outlet3 method...");
        Helpers.waitAndClick(driver, By.xpath("//BUTTON[normalize-space(.)='Continue']"));
        Helpers.waitAndClick(driver, By.xpath("//BUTTON[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-17dnra4')]"));
        Helpers.waitAndClick(driver, By.xpath("//P[normalize-space(.)='Office']"));
        Helpers.waitAndClick(driver, By.xpath("//P[normalize-space(.)='Add new pick-up point']"));

        driver.findElement(By.id("firstAddress")).sendKeys("Bhalawani, nageshware temple");
        driver.findElement(By.id("secondAddress")).sendKeys("Kad nagar");
        driver.findElement(By.id("pincode")).sendKeys("414103");

        Helpers.waitAndClick(driver, By.id("pickupLabel"));
        driver.findElement(By.id("pickupLabel")).sendKeys("Office2");

        Thread.sleep(1000);
        Helpers.waitAndClick(driver, By.id("mui-component-select-baseDistance"));
        Helpers.waitAndClick(driver, By.xpath("//LI[@data-value='4']"));

        driver.findElement(By.id("basePrice")).sendKeys("50");
        driver.findElement(By.id("perKmPrice")).sendKeys("5");

        Helpers.waitAndClick(driver, By.xpath("//BUTTON[normalize-space(.)='Save Pick-Up Address']"));

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//DIV[@role='status']")));
            logger.info("✅ Fresh Food Outlet with delivery management mandatory created successfully!");
        } catch (Exception e) {
            logger.error("❌ Fresh Food Outlet with delivery management mandatory creation failed.", e);
        }

        Helpers.waitAndClick(driver, By.xpath("//P[normalize-space(.)='Office']"));
        Helpers.waitAndClick(driver, By.xpath("//DIV[normalize-space(.)='Office2']"));
        Thread.sleep(1000);
        Helpers.waitAndClick(driver, By.xpath("//P[normalize-space(.)='Publish Business']"));
        logger.info("✅ Fresh Food Outlet with delivery management mandatory Published Successfully");
    }
}
