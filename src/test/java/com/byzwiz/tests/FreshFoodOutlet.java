package com.byzwiz.tests;

import com.aventstack.extentreports.Status;
import com.byzwiz.base.BaseTest;
import com.byzwiz.listeners.ExtentTestNGListener;
import com.byzwiz.pages.*;
import com.byzwiz.reports.ExtentReportManager;
import com.byzwiz.utils.CombinedDataProvider;
import com.byzwiz.utils.ConfigReader;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentTestNGListener.class)
public class FreshFoodOutlet extends BaseTest {

    @Test(dataProvider = "combinedData", dataProviderClass = CombinedDataProvider.class, retryAnalyzer = com.byzwiz.listeners.RetryAnalyzer.class)
    public void freshFoodOutletFlow(String email, String password,
                                    String gst, String fssai,
                                    String address1, String address2,
                                    String pincode, String pickupLabelText, String pickupSame) throws Exception {

    	String url = ConfigReader.get("baseUrl");
        driver.get(url);

        ExtentReportManager.getTest().log(Status.INFO, "Fresh Food Outlet Flow Started");

        // Page object instantiation
        LoginPage loginPage = new LoginPage(driver);
        BusinessFirstPage businessFirst = new BusinessFirstPage(driver);
        BusinessSecondPage businessSecondPage = new BusinessSecondPage(driver);
        BusinessLastPage lastPage = new BusinessLastPage(driver);
        AddHeaderPage addHeader = new AddHeaderPage(driver);
        AddItemsPage itemsPage = new AddItemsPage(driver);
        PublishPage publishPage = new PublishPage(driver);
        OutletPage outletPage = new OutletPage(driver);

        // Log in
        loginPage.login(email, password);

        // Create First Business Page
        businessFirst.createFreshFoodFirstPage();

        // Create Business Second Page
        businessSecondPage.fillAddress1(gst, fssai, address1, address2, pincode, pickupLabelText, Boolean.parseBoolean(pickupSame));

        // Create Business Third Page (upload image using relative path internally)
        lastPage.uploadBannerAndLogo();  // Assumes it uses /resources/images/ path internally
        lastPage.selectBusinessDaysAndHours();
        lastPage.completeFoodDeclarations();
        lastPage.submitBusiness();
        lastPage.verifyBusinessCreated();

        // Select Category and Add Header
        addHeader.freshFoodCatHeader();


        // Add non-custom item (may include image upload)
        itemsPage.freshFoodNonCustomItem();  // This method must also use relative Jenkins-safe path if it uploads images

        // Publish Business
        publishPage.publishBusiness();

        // Create and Publish Outlets (no image upload involved)
        outletPage.Outlet1();
        outletPage.Outlet2();
        outletPage.Outlet3();
    }
}
