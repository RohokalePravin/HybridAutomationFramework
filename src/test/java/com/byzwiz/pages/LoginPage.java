package com.byzwiz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.byzwiz.utils.Helpers;
import com.byzwiz.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;

public class LoginPage {
    private WebDriver driver;
    private static final Logger log = LoggerUtil.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        log.info("🔧 LoginPage initialized");
    }

    // Locators
    private final By loginBtn = By.xpath("//button[normalize-space(.)='Log In']");
    private final By loginWithEmail = By.xpath("//p[normalize-space(.)='Log In with Email']");
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By submitBtn = By.xpath("//form[@action='#']//div[.='Log In']");

    public void login(String email, String password) {
        try {
            log.info("🟡 Attempting login with email: {}", email);
            Helpers.waitAndClick(driver, loginBtn);
            Helpers.waitAndClick(driver, loginWithEmail);

            driver.findElement(emailField).sendKeys(email);
            driver.findElement(passwordField).sendKeys(password);
            driver.findElement(submitBtn).click();

            log.info("✅ Login submitted successfully");
        } catch (Exception e) {
            log.error("❌ Login failed: {}", e.getMessage(), e);
            throw new RuntimeException("Login failed: " + e.getMessage(), e);
        }
    }
}
