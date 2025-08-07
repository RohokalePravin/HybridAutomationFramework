package com.byzwiz.pages;

import com.byzwiz.utils.Helpers;
import com.byzwiz.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Paths;

public class AddItemsPage {

    WebDriver driver;
    WebDriverWait wait;
    private static final Logger logger = LoggerUtil.getLogger(AddItemsPage.class);

    public AddItemsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
    }

    public void freshFoodNonCustomItem() throws Exception {
        logger.info("🚀 Starting freshFoodNonCustomItem flow");

        try {
            // Click Add Icon
            Helpers.waitAndClick(driver, By.xpath("//DIV[contains(@role,'button')]/DIV[1]/DIV/DIV/DIV/BUTTON[1]/*[local-name()='svg']"));

            // Enter Item Name
            driver.findElement(By.name("itemName")).sendKeys("Fresh Home Food for breakfast and dinner");

            // Select Category
            Helpers.waitAndClick(driver, By.xpath("//DIV[contains(@class,'MuiBox-root css-kogpwf')]/DIV[2]/DIV[2]/DIV/DIV[@role='button']"));
            Helpers.waitAndClick(driver, By.xpath("//p[normalize-space(.)='Healthy Soups']"));

            // Upload Image – Dual support (Jenkins + Local)
            File imageFile = Paths.get("src", "test", "resources", "images", "FoodBanner.jpg").toFile();
            if (!imageFile.exists()) {
                throw new RuntimeException("Image not found at: " + imageFile.getAbsolutePath());
            }

            if (System.getenv("JENKINS_HOME") != null) {
                logger.info("Uploading image via sendKeys (Jenkins-safe)");
                WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
                fileInput.sendKeys(imageFile.getAbsolutePath());
            } else {
                logger.info("Uploading image via Robot (local)");
                Helpers.waitAndClick(driver, By.xpath("//BUTTON[normalize-space(.)='Add Photos']"));
                Helpers.uploadFileWithRobot(imageFile.getAbsolutePath());
            }

            logger.info("✅ Uploaded image: " + imageFile.getAbsolutePath());

            // Close image modal
            Helpers.waitAndClick(driver, By.xpath("//BUTTON[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-18bb0i1')]"));

            // Enter Description
            Helpers.waitAndClick(driver, By.id("itemDesc"));
            Helpers.scrollAndType(driver, By.id("itemDesc"), "A delicious and aromatic dish of spicy biryani made.");

            // Select Tags
            String[] tags = {"Jain Food", "Egg-Less", "Gluten Free", "Lactose Free", "No Added Sugar", "Extra Spicy"};
            for (String tag : tags) {
                Helpers.waitAndClick(driver, By.xpath("//label[normalize-space(.)='" + tag + "']"));
            }

            // Sell Within
            Helpers.waitAndClick(driver, By.id("sellWithin"));
            Helpers.waitAndClick(driver, By.xpath("//UL[contains(@role,'listbox')]/LI[2]"));

            // Quantity & Pack Info
            driver.findElement(By.xpath("//INPUT[@id='quantityDescriptor[0].itemWeight.price']")).sendKeys("100");

            Helpers.waitAndClick(driver, By.xpath("//DIV[@tabindex='0' and @role='button' and @aria-haspopup='listbox' and @aria-label='Without label' and @aria-labelledby='quantityDescriptor[0].itemWeight.unit']"));
            Helpers.waitAndClick(driver, By.xpath("//li[normalize-space(.)='Pack of']"));

            driver.findElement(By.xpath("//DIV[.='Value (Pack of)​']/descendant::INPUT")).sendKeys("4");
            driver.findElement(By.id("quantityDescriptor[0].itemWeight.itemStock")).sendKeys("4");

            // Package weight unit
            Helpers.waitAndClick(driver, By.xpath("//div[@tabindex='0' and @role='button' and @aria-haspopup='listbox' and @aria-label='Without label' and @aria-labelledby='quantityDescriptor[0].packageWeight.value']"));
            Helpers.waitAndClick(driver, By.xpath("//UL[contains(@role,'listbox')]/LI[7]"));

            // Toggle Customization to No
            Helpers.waitAndClick(driver, By.xpath("//button[normalize-space(.)='No']"));

            // Scroll to Add Item button
            WebElement addItemButton = driver.findElement(By.xpath("//*[normalize-space(.)='Add Item']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", addItemButton);

            // Preparation Time
            Helpers.waitAndClick(driver, By.id("prepTime.days"));
            Helpers.waitAndClick(driver, By.xpath("//UL[contains(@role,'listbox')]/LI[3]"));

            // Enter Keyword and Submit
            driver.findElement(By.id("searchKeyWord")).sendKeys("#FoodItem#Item");
            Helpers.waitAndClick(driver, By.xpath("//button[normalize-space(.)='Add Item']"));

            Thread.sleep(2000); // Optional – remove if running in CI/CD headless mode
            logger.info("✅ Fresh Food Non customized item added successfully");

        } catch (Exception e) {
            logger.error("❌ Error in freshFoodNonCustomItem: " + e.getMessage(), e);
            throw e;
        }
    }
}
