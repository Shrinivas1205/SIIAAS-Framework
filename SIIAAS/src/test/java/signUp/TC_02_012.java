package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_012 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-02-012 Verify signup with empty Confirm Password field")
	public void verifySignupWithEmptyConfirmPasswordField() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== TC-02-012 STARTED ==========");

		// Open Signup Page
		LoggerUtil.info("Opening Signup Page");

		loginPage.clickSignupLink();

		// Enter Signup Details Except Confirm Password
		LoggerUtil.info("Entering Signup Details Except Confirm Password");

		signupPage.enterFullName(ConfigReader.getProperty("tc02012.name"));
		signupPage.enterEmail(ConfigReader.getProperty("tc02012.email"));
		signupPage.enterPassword(ConfigReader.getProperty("tc02012.password"));
		// empty Confirm Password field
		signupPage.selectDesignation(ConfigReader.getProperty("tc02012.designation"));
		signupPage.selectDepartment(ConfigReader.getProperty("tc02012.department"));
		signupPage.selectLocation(ConfigReader.getProperty("tc02012.location"));

		loginPage.attachStepScreenshot("Entered signup details with empty Confirm Password");

		// Click Sign Up
		LoggerUtil.info("Clicking Signup Button");

		signupPage.clickSignUpButton();

		// Verify Validation Message
		LoggerUtil.info("Verifying Validation Message");

		Assert.assertEquals(signupPage.getValidationMessage("All fields are required."), "All fields are required.",
				"Incorrect validation message displayed");

		loginPage.attachStepScreenshot("Validation message displayed for empty Confirm Password field");

		LoggerUtil.info("========== TC-02-012 PASSED ==========");
	}
}