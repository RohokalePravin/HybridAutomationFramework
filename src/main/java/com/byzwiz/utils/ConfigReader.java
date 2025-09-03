package com.byzwiz.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static Properties prop;

	public static Properties getProperties() {
		if (prop == null) {
			try {
				prop = new Properties();
				FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop;
	}

	private static Properties properties = new Properties();

	static {
		try {
			FileInputStream fis = new FileInputStream("config.properties");
			properties.load(fis);
		} catch (IOException e) {
			System.err.println("❌ Failed to load config.properties: " + e.getMessage());
		}
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}

	public static boolean getBoolean(String key) {
		return Boolean.parseBoolean(properties.getProperty(key));
	}
}