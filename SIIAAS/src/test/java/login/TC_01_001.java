package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

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

	@Test(description = "TC-01-001 : Verify valid login with approved user credentials")
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
		page.waitForLoadState(LoadState.LOAD);

	
		/*
		 * Step 4: Verify User Landed on Projects Dashboard
		 */
		LoggerUtil.info("Verifying Projects Dashboard");

		test.info("Verifying Projects Dashboard");

		/*
		 * Dashboard Screenshot
		 */
		LoggerUtil.info("User Landed on dashboard");

		test.info("User Landed on dashboard");

		/*
		 * Assertion
		 */
		Assert.assertTrue(
				loginPage.isProjectsDashboardDisplayed(),

				"User landed on Projects Dashboard"

		);

		/*
		 * Success Logs
		 */
		LoggerUtil.info("TC-01-001 PASSED");

		test.pass("User logged in successfully and landed on Projects Dashboard");

		LoggerUtil.info("========== TC-01-001 COMPLETED ==========");
	}
}
