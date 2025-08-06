package com.byzwiz.utils;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class CombinedDataProvider {

    @DataProvider(name = "combinedData")
    public Object[][] combinedData() throws IOException {
        Object[][] loginData = ExcelUtils.getData("src/test/resources/TestData/LoginData.xlsx", "Sheet1");
        Object[][] businessData = ExcelUtils.getData("src/test/resources/TestData/BusinessData.xlsx", "Sheet1");

        Object[][] combined = new Object[loginData.length][9];

        for (int i = 0; i < loginData.length; i++) {
            combined[i][0] = loginData[i][0]; // email
            combined[i][1] = loginData[i][1]; // password
            combined[i][2] = businessData[i][0]; // gst
            combined[i][3] = businessData[i][1]; // fssai
            combined[i][4] = businessData[i][2]; // address1
            combined[i][5] = businessData[i][3]; // address2
            combined[i][6] = businessData[i][4]; // pincode
            combined[i][7] = businessData[i][5]; // pickupLabelText
            combined[i][8] = businessData[i][6]; // pickupSame
        }

        return combined;
    }
}
