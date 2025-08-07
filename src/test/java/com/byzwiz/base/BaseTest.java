package com.byzwiz.base;

import com.byzwiz.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.get("browser");
        boolean headless = ConfigReader.getBoolean("headless");

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");

            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--remote-allow-origins=*");
            } else {
                options.addArguments("--start-maximized");
            }

            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("dom.webnotifications.enabled", false);

            if (headless) {
                options.addArguments("--headless");
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");
            }

            driver = new FirefoxDriver(options);

        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-notifications");

            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-gpu");
            }

            driver = new EdgeDriver(options);

        } else {
            throw new RuntimeException("Unsupported browser in config: " + browser);
        }

        // Maximize only in non-headless mode (for consistency)
        if (!headless) {
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Launched " + browser + (headless ? " in headless mode." : "."));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing browser...");
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }
}
