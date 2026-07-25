package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_017 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-02-017 Verify signup with invalid email missing domain")
	public void verifySignupWithInvalidEmailMissingDomain() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== TC-02-017 STARTED ==========");

		// Open Signup Page
		LoggerUtil.info("Opening Signup Page");

		loginPage.clickSignupLink();

		loginPage.attachStepScreenshot("Signup page opened");

		// Enter Signup Details
		LoggerUtil.info("Entering Signup Details With Invalid Email");

		signupPage.enterFullName(ConfigReader.getProperty("signup.name"));

		signupPage.enterEmail(ConfigReader.getProperty("signup.invalidEmailMissingDomain"));

		signupPage.enterPassword(ConfigReader.getProperty("signup.password"));

		signupPage.enterConfirmPassword(ConfigReader.getProperty("signup.confirmPassword"));

		signupPage.selectDesignation(ConfigReader.getProperty("signup.designation"));

		signupPage.selectDepartment(ConfigReader.getProperty("signup.department"));

		signupPage.selectLocation(ConfigReader.getProperty("signup.location"));

		loginPage.attachStepScreenshot("Entered invalid email without domain");

		// Click Sign Up
		LoggerUtil.info("Clicking Sign Up Button");

		signupPage.clickSignUpButton();

		loginPage.attachStepScreenshot("Clicked Sign Up with invalid email");

		// Verify Validation Message
		LoggerUtil.info("Verifying Invalid Email Validation Message");

		Assert.assertEquals(

				signupPage.getValidationMessage("Please enter a valid email address."),

				"Please enter a valid email address.",

				"Incorrect validation message displayed"

		);

		loginPage.attachStepScreenshot("Invalid email validation displayed");

		LoggerUtil.info("========== TC-02-017 PASSED ==========");
	}
}