package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_011 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-02-011 Verify signup with empty Password field")
	public void verifySignupWithEmptyPasswordField() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== TC-02-011 STARTED ==========");

		// Open Signup Page
		LoggerUtil.info("Opening Signup Page");

		loginPage.clickSignupLink();

		// Enter Signup Details Except Password
		LoggerUtil.info("Entering Signup Details Except Password");

		signupPage.enterFullName(ConfigReader.getProperty("tc02011.name"));
		signupPage.enterEmail(ConfigReader.getProperty("tc02011.email"));
		// Empty Password
		signupPage.enterConfirmPassword(ConfigReader.getProperty("tc02011.confirmPassword"));
		signupPage.selectDesignation(ConfigReader.getProperty("tc02011.designation"));
		signupPage.selectDepartment(ConfigReader.getProperty("tc02011.department"));
		signupPage.selectLocation(ConfigReader.getProperty("tc02011.location"));

		loginPage.attachStepScreenshot("Entered signup details with empty Password");

		// Click Sign Up
		LoggerUtil.info("Clicking Signup Button");

		signupPage.clickSignUpButton();

		// Verify Validation Message
		LoggerUtil.info("Verifying Validation Message");

		Assert.assertEquals(signupPage.getValidationMessage("All fields are required."), "All fields are required.",
				"Incorrect validation message displayed");

		loginPage.attachStepScreenshot("Validation message displayed for empty Password field");

		LoggerUtil.info("========== TC-02-011 PASSED ==========");
	}
}