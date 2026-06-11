package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

/*
 * =========================================================
 * Test Case ID      : TC-01-001
 * Module            : Login
 * Test Scenario     : Valid login with correct credentials
 * Test Type         : Positive
 * Priority          : High
 * =========================================================
 */

public class TC_01_001 extends BaseTest {

	@Test(
			description = "TC-01-001 : Verify valid login with approved user credentials"
	)
	public void verifyValidLogin() {

		/*
		 * Test Start
		 */
		LoggerUtil.info("========== STARTING TC-01-001 ==========");

		test.info("Starting TC-01-001 Valid Login Test");

		/*
		 * Create Login Page Object
		 */
		LoginPage loginPage = new LoginPage(page);

        // till login click button
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
		
		
		page.waitForLoadState();

		/*
		 * Screenshot after login
		 */
		String loginShot = ScreenshotUtil.captureScreenshot(

				page,

				"TC_01_001_After_Login"

		);

		try {

			test.addScreenCaptureFromPath(loginShot);

		}

		catch (Exception e) {

			e.printStackTrace();
		}

		/*
		 * Step 4: Verify User Landed on Projects Dashboard
		 */
		LoggerUtil.info("Verifying Projects Dashboard");

		test.info("Verifying Projects Dashboard");

		boolean isDashboardVisible = page.locator("//span[normalize-space()= \"Projects\"]").isVisible();

		/*
		 * Dashboard Screenshot
		 */
		String dashboardShot = ScreenshotUtil.captureScreenshot(

				page,

				"TC_01_001_Dashboard"

		);

		try {

			test.addScreenCaptureFromPath(dashboardShot);

		}

		catch (Exception e) {

			e.printStackTrace();
		}

		/*
		 * Assertion
		 */
		Assert.assertTrue(

				isDashboardVisible,

				"User NOT landed on Projects Dashboard"

		);

		/*
		 * Success Logs
		 */
		LoggerUtil.info("TC-01-001 PASSED");

		test.pass("User logged in successfully and landed on Projects Dashboard");

		LoggerUtil.info("========== TC-01-001 COMPLETED ==========");
	}
}
