package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Playwright;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;
import utils.ScreenshotUtil;

public class TC_01_011 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-01-011 : Verify login with very long password input")
	public void verifyLoginWithVeryLongPasswordInput() {

		// Page Object
		LoginPage loginPage = new LoginPage(page);

		LoggerUtil.info("========== STARTING TC-01-011 ==========");
		test.info("TC-01-011 Execution Started");

		// ==========================================
		// Enter Email
		// ==========================================

		LoggerUtil.info("Entering Email");
		test.info("Entering Email");

		loginPage.enterEmail(ConfigReader.getProperty("longpassworduseremail"));

		loginPage.attachStepScreenshot("Email_Entered");

		// ==========================================
		// Enter Very Long Password
		// ==========================================

		LoggerUtil.info("Entering Very Long Password");
		test.info("Entering Very Long Password");

		loginPage.enterPassword(ConfigReader.getProperty("longpassword"));

		loginPage.attachStepScreenshot("Very_Long_Password_Entered");

		// ==========================================
		// Click Login
		// ==========================================

		LoggerUtil.info("Clicking Login Button");
		test.info("Clicking Login Button");

		loginPage.clickLogin();

		loginPage.attachStepScreenshot("Login_Button_Clicked");

		// ==========================================
		// Verify Result
		// ==========================================

		LoggerUtil.info("Verifying Login Result");
		test.info("Verifying Login Result");

		// If dashboard is displayed, login succeeded -> FAIL
		if (loginPage.isProjectsDashboardDisplayed()) {
			
			loginPage.attachStepScreenshot("Projects_Dashboard_Displayed");

			test.fail("Application allowed login with a very long password.");

			LoggerUtil.error("TC-01-011 FAILED - Dashboard displayed.");

			Assert.fail("Projects Dashboard displayed. Login should not be successful with a very long password.");
			
			page.close();
			
		}

		// Otherwise verify the validation message
		String actualMessage = loginPage.getEmailLengthValidationMessage();

		loginPage.attachStepScreenshot("Validation_Message_Displayed");

		Assert.assertTrue(
				actualMessage.contains("maximum length") || actualMessage.contains("too long")
						|| actualMessage.contains("invalid password"),
				"Incorrect validation message displayed. Actual Message : " + actualMessage);

		test.pass("Application correctly rejected the very long password.");

		LoggerUtil.info("TC-01-011 PASSED");
	}
}