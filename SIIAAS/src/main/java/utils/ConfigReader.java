package utils;

import java.io.InputStream;
import java.util.Properties;

/*
 * ============================================================
 * ConfigReader
 * ============================================================
 * Purpose:
 * Reads values from:
 *
 * 1. config.properties
 * 2. locators.properties
 *
 * Both files must be placed inside:
 *
 * src/test/resources
 *
 * This implementation works with:
 * ✔ Eclipse
 * ✔ IntelliJ
 * ✔ Maven
 * ✔ Jenkins
 * ✔ GitHub Actions
 * ✔ Packaged JAR
 * ============================================================
 */

public final class ConfigReader {

	private static final Properties configProperties = new Properties();
	private static final Properties locatorProperties = new Properties();

	static {

		try {

			// ==================================================
			// Load config.properties
			// ==================================================

			InputStream configStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");

			if (configStream == null) {

				throw new RuntimeException("config.properties not found inside src/test/resources");

			}

			configProperties.load(configStream);

			// ==================================================
			// Load locators.properties
			// ==================================================

			InputStream locatorStream = ConfigReader.class.getClassLoader().getResourceAsStream("locators.properties");

			if (locatorStream == null) {

				throw new RuntimeException("locators.properties not found inside src/test/resources");

			}

			locatorProperties.load(locatorStream);

		}

		catch (Exception e) {

			throw new RuntimeException("Failed to load properties files.", e);

		}

	}

	/**
	 * Returns value from config.properties
	 */
	public static String getProperty(String key) {

		return configProperties.getProperty(key);

	}

	/**
	 * Returns XPath/CSS locator from locators.properties
	 */
	public static String getLocator(String key) {

		return locatorProperties.getProperty(key);

	}

}