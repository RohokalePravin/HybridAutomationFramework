package com.byzwiz.utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class CleanUpUtil {

    // This method is meant to be called at the start of your test suite
    public static void cleanUpBeforeRun() {
        clean("allure-results");
        clean("extent-reports");
        clean("test-output");
        clean("screenshots");
        clean("logs");
    }

    private static void clean(String folderName) {
        File folder = new File(folderName);
        if (folder.exists()) {
            try {
                FileUtils.deleteDirectory(folder);
                System.out.println("[INFO] ✅ Deleted: " + folderName);
            } catch (IOException e) {
                System.err.println("[ERROR] ❌ Failed to delete " + folderName + ": " + e.getMessage());
            }
        } else {
            System.out.println("[INFO] Skipped (not found): " + folderName);
        }
    }
}
