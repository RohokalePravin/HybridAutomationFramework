package com.byzwiz.utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class CleanUpUtil {

	public static void cleanUpBeforeRun() {
		deleteFolder("allure-results");
		deleteFolder("extent-reports");
		deleteFolder("test-output");
		deleteFolder("screenshots");
		deleteFolder("logs");
	}

	private static void deleteFolder(String folderName) {
		File folder = new File(folderName);
		if (folder.exists()) {
			try {
				FileUtils.deleteDirectory(folder);
				System.out.println("✅ Deleted: " + folderName);
			} catch (IOException e) {
				System.out.println("❌ Failed to delete: " + folderName);
			}
		}
	}
}