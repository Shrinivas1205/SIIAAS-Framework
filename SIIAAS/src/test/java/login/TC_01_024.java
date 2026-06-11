package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class TC_01_024 extends BaseTest {

	@Test(description = "TC-01-024 : Verify browser back button does not expose login page after login")
	public void verifyBrowserBackButtonAfterLogin() {

		// Page Object Creation
		LoginPage loginPage = new LoginPage(page);

		LoggerUtil.info("========== STARTING TC-01-024 ==========");
		test.info("TC-01-024 Execution Started");

		// Login Successfully
		LoggerUtil.info("Logging In With Valid Credentials");
		test.info("Logging In With Valid Credentials");

		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Successful_Login");

		// Verify Projects Dashboard Displayed
		Assert.assertTrue(loginPage.isProjectsDashboardDisplayed(), "Projects Dashboard is not displayed after login");

		// Click Browser Back Button
		LoggerUtil.info("Clicking Browser Back Button");
		test.info("Clicking Browser Back Button");
// back
		page.goBack();

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Browser_Back_Clicked");

		// Verify User Still On Projects Page
		LoggerUtil.info("Verifying User Is Not Redirected To Login Page");
		test.info("Verifying User Is Not Redirected To Login Page");

		boolean isProjectsDashboardDisplayed = loginPage.isProjectsDashboardDisplayed();

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Projects_Page_After_Back_Action");

		// Validation
		Assert.assertTrue(isProjectsDashboardDisplayed,
				"Login page is accessible using browser back button after login");

		LoggerUtil.info("TC-01-024 PASSED");
		test.pass("Browser back button validation verified successfully");
	}
}