package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class TC_01_013 extends BaseTest {

	@Test(description = "TC-01-013 : Verify password visibility toggle using eye icon")
	public void verifyPasswordVisibilityToggle() {

		// Page Object Creation
		LoginPage loginPage = new LoginPage(page);

		LoggerUtil.info("========== STARTING TC-01-013 ==========");
		test.info("TC-01-013 Execution Started");

		// Enter Password
		LoggerUtil.info("Entering Password");
		test.info("Entering Password");

		loginPage.enterPassword(ConfigReader.getProperty("password"));

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Password_Entered");

		// Click Eye Icon - Reveal Password
		LoggerUtil.info("Clicking Eye Icon To Reveal Password");
		test.info("Clicking Eye Icon To Reveal Password");

		loginPage.clickPasswordVisibilityIcon();

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Password_Revealed");

		// Verify Password Is Visible
		String visiblePasswordFieldType = loginPage.getPasswordFieldType();

		Assert.assertEquals(visiblePasswordFieldType, ConfigReader.getProperty("password"),
				"Password is not visible after clicking eye icon");

		// Click Eye Icon Again - Mask Password
		LoggerUtil.info("Clicking Eye Icon To Mask Password");
		test.info("Clicking Eye Icon To Mask Password");

		loginPage.clickPasswordVisibilityIcon();

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Password_Masked_Again");

		// Verify Password Is Masked Again
		String maskedPasswordFieldType = loginPage.getPasswordFieldType();

		Assert.assertEquals(maskedPasswordFieldType, ConfigReader.getProperty("password"),
				"Password is not masked after clicking eye icon again");

		LoggerUtil.info("TC-01-013 PASSED");
		test.pass("Password visibility toggle verified successfully");
	}
}