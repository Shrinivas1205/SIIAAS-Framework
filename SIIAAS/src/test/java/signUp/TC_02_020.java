package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_020 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-02-020 Verify signup succeeds when email contains leading/trailing spaces")
	public void verifySignupWithEmailContainingSpaces() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== TC-02-020 STARTED ==========");

		// Open Signup Page
		LoggerUtil.info("Opening Signup Page");

		loginPage.clickSignupLink();

		loginPage.attachStepScreenshot("Signup page opened");

		// Enter Signup Details
		LoggerUtil.info("Entering Signup Details");

		signupPage.enterFullName(ConfigReader.getProperty("signup.name2"));

		signupPage.enterEmail(ConfigReader.getProperty("signup.emailWithSpaces"));

		signupPage.enterPassword(ConfigReader.getProperty("signup.password"));

		signupPage.enterConfirmPassword(ConfigReader.getProperty("signup.confirmPassword"));

		signupPage.selectDesignation(ConfigReader.getProperty("signup.designation"));

		signupPage.selectDepartment(ConfigReader.getProperty("signup.department"));

		signupPage.selectLocation(ConfigReader.getProperty("signup.location"));

		loginPage.attachStepScreenshot("Signup form filled with email containing leading and trailing spaces");

		// Click Signup
		LoggerUtil.info("Clicking Sign Up Button");

		signupPage.clickSignUpButton();

		loginPage.attachStepScreenshot("Clicked Sign Up button");

		// Verify Signup Success
		LoggerUtil.info("Verifying Signup Success Message");

		Assert.assertTrue(signupPage.isApprovalMessageDisplayed(),
				"Signup failed for email containing leading/trailing spaces");

		loginPage.attachStepScreenshot("Signup successful after trimming email spaces");

		LoggerUtil.info("========== TC-02-020 PASSED ==========");
	}
}