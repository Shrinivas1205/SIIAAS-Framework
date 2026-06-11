package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class TC_01_026 extends BaseTest {

	@Test(description = "TC-01-026 : Verify login with uppercase or mixed-case email")
	public void verifyLoginWithUppercaseOrMixedCaseEmail() {

		// Page Object Creation
		LoginPage loginPage = new LoginPage(page);

		LoggerUtil.info("========== STARTING TC-01-026 ==========");
		test.info("TC-01-026 Execution Started");

		// Enter Uppercase/Mixed-Case Email
		LoggerUtil.info("Entering Uppercase/Mixed-Case Email");
		test.info("Entering Uppercase/Mixed-Case Email");

		loginPage.enterEmail("ADMIN@APRIL.COM");

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Uppercase_Email_Entered");

		// Enter Valid Password
		LoggerUtil.info("Entering Valid Password");
		test.info("Entering Valid Password");

		loginPage.enterPassword("admin");

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Password_Entered");

		// Click Login Button
		LoggerUtil.info("Clicking Login Button");
		test.info("Clicking Login Button");

		loginPage.clickLogin();

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Login_Button_Clicked");

		// Verify User Redirected To Projects Page
		LoggerUtil.info("Verifying Navigation To Projects Page");
		test.info("Verifying Navigation To Projects Page");

		boolean isProjectsDashboardDisplayed = loginPage.isProjectsDashboardDisplayed();

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Projects_Page_Displayed");

		// Validation
		Assert.assertTrue(isProjectsDashboardDisplayed, "Login failed with uppercase/mixed-case email");

		LoggerUtil.info("TC-01-026 PASSED");
		test.pass("Case-insensitive email login verified successfully");
	}
}