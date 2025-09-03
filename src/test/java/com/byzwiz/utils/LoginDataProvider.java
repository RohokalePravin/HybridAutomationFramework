package com.byzwiz.utils;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        // Data for a successful login.
        // The OTP is a single string now.
        return new Object[][] {
                {"Kad Nagar Undri", "8390289030", "1234"},
                // Add more test cases here
        };
    }
}