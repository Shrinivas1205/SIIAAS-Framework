package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class TC_01_014 extends BaseTest {

	@Test(description = "TC-01-014 : Verify Log In button is visible and clickable")
	public void verifyLoginButtonIsVisibleAndClickable() {

		// Page Object Creation
		LoginPage loginPage = new LoginPage(page);

		LoggerUtil.info("========== STARTING TC-01-014 ==========");
		test.info("TC-01-014 Execution Started");

		// Verify Log In Button Is Visible
		LoggerUtil.info("Verifying Log In Button Visibility");
		test.info("Verifying Log In Button Visibility");

		boolean isLoginButtonVisible = loginPage.isLoginButtonVisible();

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Login_Button_Visible");

		Assert.assertTrue(isLoginButtonVisible, "Log In button is not visible");

		// Verify Log In Button Is Enabled
		LoggerUtil.info("Verifying Log In Button Is Enabled");
		test.info("Verifying Log In Button Is Enabled");

		boolean isLoginButtonEnabled = loginPage.isLoginButtonEnabled();

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Login_Button_Enabled");

		Assert.assertTrue(isLoginButtonEnabled, "Log In button is disabled");

		// Click Log In Button
		LoggerUtil.info("Clicking Log In Button");
		test.info("Clicking Log In Button");

		loginPage.clickLogin();

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Login_Button_Clicked");

		LoggerUtil.info("TC-01-014 PASSED");
		test.pass("Log In button visibility and clickability verified successfully");
	}
}