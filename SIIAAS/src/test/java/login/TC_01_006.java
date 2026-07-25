package login;

import org.openqa.selenium.bidi.browsingcontext.Locator;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Locator.WaitForOptions;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;
import utils.ScreenshotUtil;

public class TC_01_006 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-01-006 : Verify login with empty password field")
	public void verifyLoginWithEmptyPasswordField() throws InterruptedException {

		/*
		 * Page Object Creation
		 */
		LoginPage loginPage = new LoginPage(page);

		LoggerUtil.info("========== STARTING TC-01-006 ==========");

		test.info("TC-01-006 Execution Started");

		/*
		 * Enter Valid Email
		 */
		LoggerUtil.info("Entering Valid Email");

		test.info("Entering Valid Email");

		loginPage.enterEmail(ConfigReader.getProperty("username"));

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Valid_Email_Entered");

		/*
		 * Leave Password Field Blank
		 */
		LoggerUtil.info("Leaving Password Field Blank");

		test.info("Leaving Password Field Blank");

		ScreenshotUtil.captureAndAttachScreenshot(page, test, "Password_Field_Left_Blank");

		/*
		 * Click Login Button
		 */
		LoggerUtil.info("Clicking Login Button");

		test.info("Clicking Login Button");

		loginPage.clickLogin();
		
		Thread.sleep(2000);
		

		/*
		 * Verify Validation Message
		 */
		LoggerUtil.info("Verifying Password Required Validation Message");

		test.info("Verifying Password Required Validation Message");

		String actualMessage = loginPage.getPasswordRequiredValidationMessage();
		
		test.info("Password Required Validation Message verified");


		/*
		 * Validation
		 */
		Assert.assertEquals(actualMessage, "Email and password are required.",
				"Incorrect validation message displayed");

		LoggerUtil.info("TC-01-006 PASSED");

		test.pass("Empty password field validation successful");
	}
}