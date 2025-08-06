package com.byzwiz.pages;

import com.byzwiz.utils.Helpers;
import com.byzwiz.utils.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;

public class AddHeaderPage {

    WebDriver driver;
    WebDriverWait wait;
    Logger logger = LoggerUtil.getLogger(AddHeaderPage.class);

    public AddHeaderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
    }

    // ========== Packaged Food Header ==========
    public void packagedFoodCatHeader() {
        logger.info("🚀 Starting Packaged Food Header flow");

        Helpers.waitAndClick(driver, By.linkText("Manage Inventory"));
        logger.info("✅ Clicked on 'Manage Inventory'");

        Helpers.waitAndClick(driver, By.xpath("//SPAN[normalize-space(.)='Please select categories']"));
        driver.findElement(By.xpath("//INPUT[contains(@class,'MuiBox-root css-97xfk7')]"))
                .sendKeys("Packaged Food");
        logger.info("✅ Selected 'Packaged Food' category");

        Helpers.waitAndClick(driver, By.xpath("//P[normalize-space(.)='Packaged Food']"));
        Helpers.waitAndClick(driver, By.xpath("//P[normalize-space(.)='Veg']"));
        Helpers.waitAndClick(driver, By.xpath("//P[contains(.,'Non Veg')]"));
        Helpers.waitAndClick(driver, By.xpath("//BUTTON[contains(.,'Save and Continue')]"));
        logger.info("✅ Saved category selection");

        Helpers.waitAndClick(driver, By.xpath("//button[.//*[local-name()='svg' and @data-testid='AddOutlinedIcon']]"));

        String uniqueText = "Inventory_" + System.currentTimeMillis() / 1000;
        WebElement headerInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        headerInput.clear();
        headerInput.sendKeys(uniqueText);
        logger.info("✅ Entered header name: " + uniqueText);

        Helpers.waitAndClick(driver, By.xpath("//DIV[contains(@class,'MuiBox-root css-frhfaj')]/DIV/DIV[1]/DIV"));
        Helpers.waitAndClick(driver, By.xpath("//P[normalize-space(.)='Packaged Food']"));

        Helpers.waitAndClick(driver, By.xpath("//BUTTON[normalize-space(.)='Apply']"));
        logger.info("🎉 Packaged Food Header applied successfully");
    }

    // ========== Fresh Food Header ==========
    public void freshFoodCatHeader() {
        logger.info("🚀 Starting Fresh Food Header flow");

        Helpers.waitAndClick(driver, By.linkText("Manage Menu"));
        logger.info("✅ Clicked on 'Manage Menu'");

        Helpers.waitAndClick(driver, By.xpath("//SPAN[normalize-space(.)='Please select categories']"));
        driver.findElement(By.xpath("//INPUT[contains(@class,'MuiBox-root css-97xfk7')]"))
                .sendKeys("Health Food");
        logger.info("✅ Typed 'Health Food'");

        Helpers.waitAndClick(driver, By.xpath("//FORM[@action='#']/DIV/DIV/DIV/DIV[.='Health Food']"));
        Helpers.waitAndClick(driver, By.xpath("//P[normalize-space(.)='Veg']"));
        Helpers.waitAndClick(driver, By.xpath("//P[contains(.,'Non Veg')]"));
        Helpers.waitAndClick(driver, By.xpath("//BUTTON[contains(.,'Save and Continue')]"));
        logger.info("✅ Saved Health Food category");

        Helpers.waitAndClick(driver, By.xpath("//button[.//*[local-name()='svg' and @data-testid='AddOutlinedIcon']]"));

        String uniqueText = "Menu_" + System.currentTimeMillis() / 1000;
        WebElement headerInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Menu Header Name']")));
        headerInput.clear();
        headerInput.sendKeys(uniqueText);
        logger.info("✅ Entered header name: " + uniqueText);

        Helpers.waitAndClick(driver, By.xpath("//DIV[contains(@class,'MuiBox-root css-frhfaj')]/DIV/DIV[1]/DIV"));
        Helpers.waitAndClick(driver, By.xpath("//P[normalize-space(.)='Health Food']"));

        Helpers.waitAndClick(driver, By.xpath("//BUTTON[normalize-space(.)='Apply']"));
        logger.info("🎉 Fresh Food Header applied successfully");
    }

    // ========== Packaged Pet Food Header ==========
    public void packagedPetFoodCatHeader() {
        logger.info("🚀 Starting Packaged Pet Food Header flow");

        Helpers.waitAndClick(driver, By.linkText("Manage Inventory"));
        logger.info("✅ Clicked on 'Manage Inventory'");

        Helpers.waitAndClick(driver, By.xpath("//SPAN[normalize-space(.)='Please select categories']"));
        driver.findElement(By.xpath("//INPUT[contains(@class,'MuiBox-root css-97xfk7')]"))
                .sendKeys("Packaged Pet Food");
        logger.info("✅ Selected 'Packaged Pet Food'");

        Helpers.waitAndClick(driver, By.xpath("//P[normalize-space(.)='Packaged Pet Food']"));
        Helpers.waitAndClick(driver, By.xpath("//BUTTON[contains(.,'Save and Continue')]"));
        logger.info("✅ Category saved");

        Helpers.waitAndClick(driver, By.xpath("//button[.//*[local-name()='svg' and @data-testid='AddOutlinedIcon']]"));

        String uniqueText = "Inventory_" + System.currentTimeMillis() / 1000;
        WebElement headerInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        headerInput.clear();
        headerInput.sendKeys(uniqueText);
        logger.info("✅ Entered header name: " + uniqueText);

        Helpers.waitAndClick(driver, By.xpath("//DIV[contains(@class,'MuiBox-root css-frhfaj')]/DIV/DIV[1]/DIV"));
        Helpers.waitAndClick(driver, By.xpath("//P[normalize-space(.)='Packaged Pet Food']"));

        Helpers.waitAndClick(driver, By.xpath("//BUTTON[normalize-space(.)='Apply']"));
        logger.info("🎉 Packaged Pet Food Header applied successfully");
    }
}
