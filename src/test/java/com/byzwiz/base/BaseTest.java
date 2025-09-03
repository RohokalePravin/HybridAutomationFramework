package com.byzwiz.base;

import com.byzwiz.reports.ExtentManager;
import com.byzwiz.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void beforeSuite() {
        // Initialize ExtentReports
        ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setUp(Method method) {
        // ----- Browser Setup -----
        String browser = ConfigReader.get("browser");
        boolean headless = ConfigReader.getBoolean("headless");

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized", "--disable-notifications");
            if (headless) {
                options.addArguments("--headless=new", "--disable-gpu");
            }
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("dom.webnotifications.enabled", false);
            if (headless) options.addArguments("--headless");
            driver = new FirefoxDriver(options);
            driver.manage().window().maximize();

        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-notifications");
            if (headless) options.addArguments("--headless=new", "--disable-gpu");
            driver = new EdgeDriver(options);
            driver.manage().window().maximize();

        } else {
            throw new RuntimeException("Unsupported browser in config: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Launched " + browser + (headless ? " in headless mode." : "."));

        // ----- ExtentReports Setup -----
        ExtentManager.createTest(method.getName()); // ThreadLocal is already set internally
        ExtentManager.assignAuthor("Rohokale"); // Default author
        ExtentManager.assignCategory("Regression"); // Default category
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Capture screenshot if test fails
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentManager.addScreenshot(driver, result.getName());
            ExtentManager.logFail("Test Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentManager.logPass("Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentManager.logInfo("Test Skipped: " + result.getThrowable());
        }

        if (driver != null) {
            System.out.println("Closing browser...");
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }

    @AfterSuite
    public void afterSuite() {
        if (ExtentManager.getInstance() != null) {
            ExtentManager.getInstance().flush();
        }
    }
}