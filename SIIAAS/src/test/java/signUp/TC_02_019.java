package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_019 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-02-019 Verify signup with already registered email")
	public void verifySignupWithAlreadyRegisteredEmail() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== TC-02-019 STARTED ==========");

		// Open Signup Page
		LoggerUtil.info("Opening Signup Page");

		loginPage.clickSignupLink();

		loginPage.attachStepScreenshot("Signup page opened");

		// Enter Existing User Details
		LoggerUtil.info("Entering existing user signup details");

		signupPage.enterFullName(ConfigReader.getProperty("signup.name"));

		signupPage.enterEmail(ConfigReader.getProperty("signup.existingEmail"));

		signupPage.enterPassword(ConfigReader.getProperty("signup.password"));

		signupPage.enterConfirmPassword(ConfigReader.getProperty("signup.confirmPassword"));

		signupPage.selectDesignation(ConfigReader.getProperty("signup.designation"));

		signupPage.selectDepartment(ConfigReader.getProperty("signup.department"));

		signupPage.selectLocation(ConfigReader.getProperty("signup.location"));

		loginPage.attachStepScreenshot("Entered already registered email");

		// Click Sign Up
		LoggerUtil.info("Clicking Sign Up Button");

		signupPage.clickSignUpButton();

		loginPage.attachStepScreenshot("Clicked Sign Up with existing email");

		// Verify Existing Email Validation
		LoggerUtil.info("Verifying existing email validation message");

		String actualMessage = signupPage.getValidationMessage("Email already exists.");

		Assert.assertEquals(actualMessage, "Email already exists.", "Incorrect error message displayed");

		loginPage.attachStepScreenshot("Existing email validation displayed");

		LoggerUtil.info("========== TC-02-019 PASSED ==========");
	}
}
