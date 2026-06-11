package factory;

import com.microsoft.playwright.*;

/*
 * Browser Factory Class
 * 
 * Purpose:
 * 1. Launch browser
 * 2. Create context
 * 3. Create page
 * 4. Return page object
 */

public class BrowserFactory {

	/*
	 * Global Objects
	 */
	public static Playwright playwright;

	public static Browser browser;

	public static BrowserContext context;

	public static Page page;

	/*
	 * Initialize Browser
	 */
	public static Page initBrowser(String browserName) {

		/*
		 * Create Playwright instance
		 */
		playwright = Playwright.create();

		/*
		 * Launch browser based on config
		 */
		switch (browserName.toLowerCase()) {

		case "chrome":

			browser = playwright.chromium().launch(

					new BrowserType.LaunchOptions()

							.setHeadless(false)

			);

			break;

		case "firefox":

			browser = playwright.firefox().launch(

					new BrowserType.LaunchOptions()

							.setHeadless(false)

			);

			break;

		case "webkit":

			browser = playwright.webkit().launch(

					new BrowserType.LaunchOptions()

							.setHeadless(false)

			);

			break;

		default:

			throw new RuntimeException(

					"Invalid Browser Name: " + browserName

			);
		}

		/*
		 * Create Browser Context
		 */
		context = browser.newContext();

		/*
		 * Create New Page
		 */
		page = context.newPage();

		/*
		 * Return Page
		 */
		return page;
	}

	/*
	 * Close Browser
	 */
	public static void closeBrowser() {

		if (browser != null) {

			browser.close();
		}

		if (playwright != null) {

			playwright.close();
		}
	}
}