package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class TC_01_009 extends BaseTest {

	@Test(description = "TC-01-009 : Verify login with email containing spaces")
	public void verifyLoginWithEmailContainingSpaces() {

		// Page Object Creation
		LoginPage loginPage = new LoginPage(page);

		LoggerUtil.info("========== STARTING TC-01-009 ==========");
		test.info("TC-01-009 Execution Started");

		// Enter Email With Leading/Trailing Spaces
		LoggerUtil.info("Entering Email With Spaces");
		test.info("Entering Email With Spaces");

		loginPage.enterEmail(" admin@april.com ");

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Email_With_Spaces_Entered");

		// Enter Password
		LoggerUtil.info("Entering Password");
		test.info("Entering Password");

		loginPage.enterPassword("admin");

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Password_Entered");

		// Click Login Button
		LoggerUtil.info("Clicking Login Button");
		test.info("Clicking Login Button");

		loginPage.clickLogin();

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Login_Button_Clicked");

		// Wait For Dashboard
		page.waitForTimeout(3000);

		// Validate Login
		LoggerUtil.info("Validating Login Behavior");
		test.info("Validating Login Behavior");

		Assert.assertTrue(loginPage.isDashboardDisplayed(), "User was unable to login with spaced email");

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Dashboard_Displayed");

		LoggerUtil.info("TC-01-009 PASSED");
		test.pass("Application successfully handled email containing spaces");
	}
}