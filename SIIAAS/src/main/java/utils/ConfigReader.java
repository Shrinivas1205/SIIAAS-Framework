package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import constants.FrameworkConstants;

/*
 * This class reads values from config.properties file.
 */

public class ConfigReader {

	// Java Properties class object
	static Properties prop;

	/*
	 * Static block executes only once when class loads.
	 */
	static {

		try {

			// Load config file
			FileInputStream fis = new FileInputStream(FrameworkConstants.CONFIG_PATH);

			prop = new Properties();

			// Read properties file
			prop.load(fis);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/*
	 * Generic method to fetch property value.
	 */
	public static String getProperty(String key) {

		return prop.getProperty(key);
	}
}