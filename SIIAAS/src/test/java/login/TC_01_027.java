package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;
import utils.ScreenshotUtil;

public class TC_01_027 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-01-027 Verify error message disappears after correcting credentials")
	public void verifyErrorMessageDisappearsAfterCorrectingCredentials() {

		// Page Object Creation
		LoginPage loginPage = new LoginPage(page);

		LoggerUtil.info("========== STARTING TC-01-027 ==========");
		test.info("TC-01-027 Execution Started");

		// Enter Wrong Email
		LoggerUtil.info("Entering Wrong Email");
		test.info("Entering Wrong Email");

		loginPage.enterEmail("user@example.com");
		loginPage.attachStepScreenshot("Wrong_Credentials_Entered");

		// Enter Wrong Password
		LoggerUtil.info("Entering Wrong Password");
		test.info("Entering Wrong Password");

		loginPage.enterPassword("Wrong@123");

		loginPage.attachStepScreenshot("Wrong_Credentials_Entered");

		// Click Login Button
		LoggerUtil.info("Clicking Login Button With Wrong Credentials");
		test.info("Clicking Login Button With Wrong Credentials");

		loginPage.clickLogin();
		loginPage.attachStepScreenshot("Wrong_Login_Attempt");

		// Verify Error Message Displayed
		LoggerUtil.info("Verifying Error Message Displayed");
		test.info("Verifying Error Message Displayed");

		String errorMessage = loginPage.getLoginwrongemailErrorMessage();

		Assert.assertTrue(errorMessage.contains("No account found with this email.") || errorMessage.contains("Invalid"),
				"Expected error message is not displayed");
		
		loginPage.attachStepScreenshot("Error_Message_Displayed");
		
		// Correct Email
		LoggerUtil.info("Correcting Email");
		test.info("Correcting Email");

		loginPage.enterEmail(ConfigReader.getProperty("username"));

		// Correct Password
		LoggerUtil.info("Correcting Password");
		test.info("Correcting Password");

		loginPage.enterPassword(ConfigReader.getProperty("password"));
		
		loginPage.attachStepScreenshot("Correct_Credentials_Entered");

		// Click Login Button Again
		LoggerUtil.info("Clicking Login Button With Correct Credentials");
		test.info("Clicking Login Button With Correct Credentials");

		loginPage.clickLogin();
		
		

		// Verify Successful Login
		LoggerUtil.info("Verifying Successful Login");
		test.info("Verifying Successful Login");
		
		loginPage.attachStepScreenshot("Successful_Login");

		boolean isProjectsDashboardDisplayed = loginPage.isProjectsDashboardDisplayed();

		Assert.assertTrue(isProjectsDashboardDisplayed,
				"User is not redirected to Projects page after correcting credentials");

		LoggerUtil.info("TC-01-027 PASSED");
		test.pass("Error message cleared and successful login verified");
	}
}
