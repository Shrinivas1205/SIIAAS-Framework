package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_009 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-02-009 Verify Signup With Empty Name Field")
	public void verifySignupWithEmptyNameField() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== TC-02-009 STARTED ==========");

		// Open Signup Page
		LoggerUtil.info("Opening Signup Page");

		loginPage.clickSignupLink();

		// Fill all fields except Name
		LoggerUtil.info("Entering Signup Details Except Name");

		signupPage.enterEmail(ConfigReader.getProperty("signup.email"));
		signupPage.enterPassword(ConfigReader.getProperty("signup.password"));
		signupPage.enterConfirmPassword(ConfigReader.getProperty("signup.confirmPassword"));
		signupPage.selectDesignation(ConfigReader.getProperty("signup.designation"));
		signupPage.selectDepartment(ConfigReader.getProperty("signup.department"));
		signupPage.selectLocation(ConfigReader.getProperty("signup.location"));

		loginPage.attachStepScreenshot("Entered all signup details except Name");

		// Click Sign Up
		LoggerUtil.info("Clicking Signup Button");

		signupPage.clickSignUpButton();

		// Verify Validation Message
		LoggerUtil.info("Verifying Validation Message");

		Assert.assertEquals(
				signupPage.getValidationMessage("All fields are required."),
				"All fields are required.",
				"Incorrect Validation Message");

		loginPage.attachStepScreenshot("Validation message displayed for empty Name field");

		LoggerUtil.info("========== TC-02-009 PASSED ==========");
	}
}