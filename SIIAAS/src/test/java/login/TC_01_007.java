package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.LoggerUtil;
import utils.RetryAnalyzer;
import utils.ScreenshotUtil;

public class TC_01_007 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class,description = "TC-01-007 : Verify login with both fields empty")
	public void verifyLoginWithBothFieldsEmpty() {

		// Page Object Creation
		LoginPage loginPage = new LoginPage(page);

		LoggerUtil.info("========== STARTING TC-01-007 ==========");
		test.info("TC-01-007 Execution Started");

		// Leave Email and Password Fields Blank
		LoggerUtil.info("Leaving Email and Password Fields Blank");
		test.info("Leaving Email and Password Fields Blank");

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Both_Fields_Left_Blank");

		// Click Login Button
		LoggerUtil.info("Clicking Login Button");
		test.info("Clicking Login Button");

		loginPage.clickLogin();

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Login_Button_Clicked");

		// Verify Email Validation Message
		LoggerUtil.info("Verifying Email Required Validation Message");
		test.info("Verifying Email Required Validation Message");

		String emailValidationMessage = loginPage.getEmailRequiredValidationMessage();

		// Verify Password Validation Message
		LoggerUtil.info("Verifying Password Required Validation Message");
		test.info("Verifying Password Required Validation Message");

		String passwordValidationMessage = loginPage.getPasswordRequiredValidationMessage();

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Validation_Messages_Displayed");

		// Email Validation Assertion
		Assert.assertEquals(emailValidationMessage, "Email and password are required.",
				"Incorrect email validation message displayed");

		// Password Validation Assertion
		Assert.assertEquals(passwordValidationMessage, "Email and password are required.",
				"Incorrect password validation message displayed");

		LoggerUtil.info("TC-01-007 PASSED");
		test.pass("Both empty field validations verified successfully");
	}
}