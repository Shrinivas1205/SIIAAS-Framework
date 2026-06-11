package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class TC_01_012 extends BaseTest {

	@Test(description = "TC-01-012 : Verify password is masked by default")
	public void verifyPasswordIsMaskedByDefault() {

		// Page Object Creation
		LoginPage loginPage = new LoginPage(page);

		LoggerUtil.info("========== STARTING TC-01-012 ==========");
		test.info("TC-01-012 Execution Started");

		// Enter Password
		LoggerUtil.info("Entering Password");
		test.info("Entering Password");

		loginPage.enterPassword(ConfigReader.getProperty("password"));

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Password_Entered");

		// Verify Password Is Masked
		LoggerUtil.info("Verifying Password Is Masked");
		test.info("Verifying Password Is Masked");

		Assert.assertTrue(loginPage.isPasswordMasked(), "Password field is not masked");

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Password_Masked");

		LoggerUtil.info("TC-01-012 PASSED");
		test.pass("Password field is masked successfully");
	}
}
