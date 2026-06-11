package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

/*
 * =========================================================
 * Test Case ID      : TC-01-002
 * Module            : Login
 * Test Scenario     : Login with incorrect password
 * Test Type         : Negative
 * Priority          : High
 * =========================================================
 *
 * Preconditions:
 * 1. User must be registered
 * 2. User must be approved by Admin
 *
 * Test Steps:
 * 1. Open Login Page
 * 2. Enter valid email
 * 3. Enter wrong password
 * 4. Click Login button
 * 5. Verify error message
 *
 * Expected Result:
 * Error message should display:
 * "Invalid email or password"
 *
 * =========================================================
 */

public class TC_01_002 extends BaseTest {

	@Test(
			description = "TC-01-002 : Verify login with incorrect password"
	)
	public void verifyInvalidPasswordLogin() throws InterruptedException {

		/*
		 * Test Start
		 */
		LoggerUtil.info("========== STARTING TC-01-002 ==========");

		test.info("Starting Invalid Password Login Test");

		/*
		 * Create Login Page Object
		 */
		LoginPage loginPage = new LoginPage(page);

		/*
		 * Step 1: Enter Valid Email
		 */
		LoggerUtil.info("Entering Valid Email");

		test.info("Entering Valid Email");

		loginPage.enterEmail(ConfigReader.getProperty("username"));

		/*
		 * Screenshot
		 */
		String emailShot = ScreenshotUtil.captureScreenshot(

				page,

				"Entered_Valid_Email"

		);

		try {

			test.addScreenCaptureFromPath(emailShot);

		}

		catch (Exception e) {

			e.printStackTrace();
		}

		/*
		 * Step 2: Enter Wrong Password
		 */
		LoggerUtil.info("Entering Wrong Password");

		test.info("Entering Wrong Password");

		loginPage.enterPassword(ConfigReader.getProperty("wrongpassword"));

		/*
		 * Screenshot
		 */
		String passwordShot = ScreenshotUtil.captureScreenshot(

				page,

				"Entered_Wrong_Password"

		);

		try {

			test.addScreenCaptureFromPath(passwordShot);

		}

		catch (Exception e) {

			e.printStackTrace();
		}

		/*
		 * Step 3: Click Login Button
		 */
		LoggerUtil.info("Clicking Login Button");

		test.info("Clicking Login Button");

		loginPage.clickLogin();

		/*
		 * Wait for response
		 */
		page.waitForLoadState();

		/*
		 * Screenshot after login attempt
		 */
		String loginAttemptShot = ScreenshotUtil.captureScreenshot(

				page,

				"After_Invalid_Login"

		);

		try {
			test.addScreenCaptureFromPath(loginAttemptShot);

		}

		catch (Exception e) {

			e.printStackTrace();
		}

		/*
		 * Step 4: Verify Error Message
		 */
		LoggerUtil.info("Verifying Error Message");

		test.info("Verifying Login Error Message");

		String actualErrorMessage =

				loginPage.getLoginErrorMessage();

		/*
		 * Screenshot for error message
		 */
		String errorShot = ScreenshotUtil.captureScreenshot(

				page,

				"Login_Error_Message"

		);

		try {

			test.addScreenCaptureFromPath(errorShot);

		}

		catch (Exception e) {

			e.printStackTrace();
		}
		
		Thread.sleep(2000);

		/*
		 * Assertion
		 */
		Assert.assertEquals(actualErrorMessage,	"Invalid password.","Incorrect error message displayed");

		/*
		 * Success Log
		 */
		LoggerUtil.info("Proper Error Message Displayed");

		test.pass("Invalid Login Validation Successful");

		LoggerUtil.info("========== TC-01-002 PASSED ==========");
	}
}