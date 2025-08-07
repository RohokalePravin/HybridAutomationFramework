package com.byzwiz.pages;

import org.openqa.selenium.*;
import com.byzwiz.utils.Helpers;
import com.byzwiz.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;

public class BusinessFirstPage {

    private static final Logger logger = LoggerUtil.getLogger(BusinessFirstPage.class);
    private WebDriver driver;

    public BusinessFirstPage(WebDriver driver) {
        this.driver = driver;
    }

    public void createPackagedFoodFirstPage() throws Exception {
        try {
            logger.info("🔹 Starting Packaged Food business creation...");

            Helpers.waitAndClick(driver, By.xpath("//button[normalize-space(.)='Start Another Business']"));
            Helpers.waitAndClick(driver, By.xpath("//p[normalize-space(.)='An Entreprenuer']"));
            Helpers.waitAndClick(driver, By.xpath("//button[normalize-space(.)='Continue']"));
            Helpers.waitAndClick(driver, By.xpath("//DIV[.='FoodProductsVideo']/DIV[1]/DIV[1]/SPAN/IMG[@data-nimg='fill']"));
            Helpers.waitAndClick(driver, By.xpath("//BUTTON[normalize-space(.)='Packaged Food']"));

            // Store and Company name
            String storeName = "Store_" + System.currentTimeMillis() / 1000;
            driver.findElement(By.id("storeName")).sendKeys(storeName);
            driver.findElement(By.id("companyName")).sendKeys("ByzWiz Pvt Ltd");
            logger.info("✅ Store and company name entered: " + storeName);

            // Description
            Helpers.waitAndClick(driver, By.xpath("//FORM[@action='#']/DIV[3]/DIV[4]/DIV[1]/DIV/DIV/DIV/P/DIV/P[.='Get Help']"));
            driver.findElement(By.xpath("//DIV[contains(@class,'MuiBox-root css-mbcwpm')]/DIV/TEXTAREA[1]"))
                  .sendKeys("food");
            Helpers.waitAndClick(driver, By.xpath("//button[normalize-space(.)='Generate']"));
            Helpers.waitAndClick(driver, By.xpath("//button[normalize-space(.)='Set as Description']"));
            logger.info("✅ Description generated and set.");

            // Save and Continue
            Helpers.waitAndClick(driver, By.xpath("//button[normalize-space(.)='Save and Continue']"));
            logger.info("✅ Packaged Food business first page saved successfully.");

        } catch (Exception e) {
            logger.error("❌ Error in createPackagedFoodFirstPage: " + e.getMessage(), e);
            throw e;
        }
    }

    public void createFreshFoodFirstPage() throws Exception {
        try {
            logger.info("🔹 Starting Fresh Food business creation...");

            Helpers.waitAndClick(driver, By.xpath("//button[normalize-space(.)='Start Another Business']"));
            Helpers.waitAndClick(driver, By.xpath("//p[normalize-space(.)='A Homepreneur']"));
            Helpers.waitAndClick(driver, By.xpath("//button[normalize-space(.)='Continue']"));
            Helpers.waitAndClick(driver, By.xpath("//DIV[.='FoodProductsVideo']/DIV[1]/DIV[1]/SPAN/IMG[@data-nimg='fill']"));
            Helpers.waitAndClick(driver, By.xpath("//button[normalize-space(.)='Fresh Food']"));

            String storeName = "Store_" + System.currentTimeMillis() / 1000;
            driver.findElement(By.id("companyName")).sendKeys(storeName);
            logger.info("✅ Store name entered: " + storeName);

            Helpers.waitAndClick(driver, By.xpath("//P[.='Get Help']"));
            driver.findElement(By.xpath("//div[contains(@class,'css-mbcwpm')]/div/textarea"))
                  .sendKeys("f_" + System.currentTimeMillis() / 1000);
            Helpers.waitAndClick(driver, By.xpath("//button[normalize-space(.)='Generate']"));
            Helpers.waitAndClick(driver, By.xpath("(//li)[3]"));
            driver.findElement(By.id("storeDesc")).sendKeys("Delight your taste buds with a variety of sumptuous dishes.");
            logger.info("✅ Description entered and finalized.");

            Helpers.waitAndClick(driver, By.xpath("//button[normalize-space(.)='Save and Continue']"));
            logger.info("✅ Fresh Food business first page saved successfully.");

        } catch (Exception e) {
            logger.error("❌ Error in createFreshFoodFirstPage: " + e.getMessage(), e);
            throw e;
        }
    }
}
