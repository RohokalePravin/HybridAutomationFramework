package com.byzwiz.pages;

import com.byzwiz.utils.Helpers;
import com.byzwiz.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.io.File;

public class AddItemsPage {

    WebDriver driver;
    WebDriverWait wait;
    Helpers helpers;
    private static final Logger logger = LoggerUtil.getLogger(AddItemsPage.class);

    public AddItemsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
        this.helpers = new Helpers(driver);
    }

    public void freshFoodNonCustomItem() throws Exception {
        logger.info("🚀 Starting freshFoodNonCustomItem flow");

        try {
            Helpers.waitAndClick(driver, By.xpath("//DIV[contains(@role,'button')]/DIV[1]/DIV/DIV/DIV/BUTTON[1]/*[local-name()='svg']"));
            driver.findElement(By.name("itemName")).sendKeys("Fresh Home Food for breakfast and dinner");
            logger.info("Entered item name");

            Helpers.waitAndClick(driver, By.xpath("//DIV[contains(@class,'MuiBox-root css-kogpwf')]/DIV[2]/DIV[2]/DIV/DIV[@role='button']"));
            Helpers.waitAndClick(driver, By.xpath("//p[normalize-space(.)='Healthy Soups']"));
            logger.info("Selected item category");

            // ✅ Jenkins-compatible image upload (relative path)
            File imageFile = Paths.get("src", "test", "resources", "images", "FoodBanner.jpg").toFile();
            if (!imageFile.exists()) {
                throw new RuntimeException("Image not found at: " + imageFile.getAbsolutePath());
            }

            Helpers.waitAndClick(driver, By.xpath("//BUTTON[normalize-space(.)='Add Photos']"));
            Helpers.uploadFileWithRobot(imageFile.getAbsolutePath());
            logger.info("Uploaded image: " + imageFile.getAbsolutePath());

            Helpers.waitAndClick(driver, By.xpath("//BUTTON[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-18bb0i1')]"));

            Helpers.waitAndClick(driver, By.id("itemDesc"));
            Helpers.scrollAndType(driver, By.id("itemDesc"), "A delicious and aromatic dish of spicy biryani made.");
            logger.info("Entered item description");

            // Select checkboxes
            Helpers.waitAndClick(driver, By.xpath("//label[normalize-space(.)='Jain Food']"));
            Helpers.waitAndClick(driver, By.xpath("//label[normalize-space(.)='Egg-Less']"));
            Helpers.waitAndClick(driver, By.xpath("//label[normalize-space(.)='Gluten Free']"));
            Helpers.waitAndClick(driver, By.xpath("//label[normalize-space(.)='Lactose Free']"));
            Helpers.waitAndClick(driver, By.xpath("//label[normalize-space(.)='No Added Sugar']"));
            Helpers.waitAndClick(driver, By.xpath("//label[normalize-space(.)='Extra Spicy']"));
            logger.info("Selected Attributes tags");

            // Sell within
            Helpers.waitAndClick(driver, By.id("sellWithin"));
            Helpers.waitAndClick(driver, By.xpath("//UL[contains(@role,'listbox')]/LI[2]"));
            logger.info("Selected sell within value");

            // Quantity & Pack Info
            driver.findElement(By.xpath("//INPUT[@id='quantityDescriptor[0].itemWeight.price']")).sendKeys("100");
            Helpers.waitAndClick(driver, By.xpath("//DIV[@tabindex='0' and normalize-space(@role)='button' and @aria-haspopup='listbox' and normalize-space(@aria-label)='Without label' and @aria-labelledby='quantityDescriptor[0].itemWeight.unit']"));
            Helpers.waitAndClick(driver, By.xpath("//li[normalize-space(.)='Pack of']"));
            driver.findElement(By.xpath("//DIV[.='Value (Pack of)​']/descendant::INPUT")).sendKeys("4");
            driver.findElement(By.id("quantityDescriptor[0].itemWeight.itemStock")).sendKeys("4");
            logger.info("Filled pack info and quantity");

            // Package weight unit
            Helpers.waitAndClick(driver, By.xpath("//div[@tabindex='0' and @role='button' and @aria-haspopup='listbox' and @aria-label='Without label' and @aria-labelledby='quantityDescriptor[0].packageWeight.value']"));
            Helpers.waitAndClick(driver, By.xpath("//UL[contains(@role,'listbox')]/LI[7]"));
            logger.info("Selected package weight unit");

            // Toggle Option
            Helpers.waitAndClick(driver, By.xpath("//button[normalize-space(.)='No']"));
            logger.info("Customization No selected");

            // Scroll and enter prep time
            WebElement addItemButton = driver.findElement(By.xpath("//*[normalize-space(.)='Add Item']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", addItemButton);

            Helpers.waitAndClick(driver, By.id("prepTime.days"));
            Helpers.waitAndClick(driver, By.xpath("//UL[contains(@role,'listbox')]/LI[3]"));
            logger.info("Entered preparation time");

            // Keyword and submit
            driver.findElement(By.id("searchKeyWord")).sendKeys("#FoodItem#Item");
            Helpers.waitAndClick(driver, By.xpath("//button[normalize-space(.)='Add Item']"));

            Thread.sleep(2000);
            logger.info("✅ Fresh Food Non customized item added successfully");

        } catch (Exception e) {
            logger.error("❌ Error in freshFoodNonCustomItem: " + e.getMessage(), e);
            throw e;
        }
    }
}
