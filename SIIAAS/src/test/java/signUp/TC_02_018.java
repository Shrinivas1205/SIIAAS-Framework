package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_018 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-02-018 Verify signup with invalid email without @ symbol")
	public void verifySignupWithInvalidEmailWithoutAtSymbol() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== TC-02-018 STARTED ==========");

		// STEP 1 : Open Signup Page
		LoggerUtil.info("Opening Signup Page");

		loginPage.clickSignupLink();

		loginPage.attachStepScreenshot("Signup page opened");

		// STEP 2 : Fill Signup Form
		LoggerUtil.info("Entering Signup Details");

		signupPage.enterFullName(ConfigReader.getProperty("signup.name"));

		signupPage.enterEmail(ConfigReader.getProperty("signup.invalidEmailWithoutAt"));

		signupPage.enterPassword(ConfigReader.getProperty("signup.password"));

		signupPage.enterConfirmPassword(ConfigReader.getProperty("signup.confirmPassword"));

		signupPage.selectDesignation(ConfigReader.getProperty("signup.designation"));

		signupPage.selectDepartment(ConfigReader.getProperty("signup.department"));

		signupPage.selectLocation(ConfigReader.getProperty("signup.location"));

		loginPage.attachStepScreenshot("Entered signup details with invalid email");

		// STEP 3 : Click Sign Up
		LoggerUtil.info("Clicking Sign Up Button");

		signupPage.clickSignUpButton();

		loginPage.attachStepScreenshot("Clicked Sign Up button");

		// STEP 4 : Verify Validation Message
		LoggerUtil.info("Verifying Invalid Email Validation Message");

		String actualMessage = signupPage.getValidationMessage("Please enter a valid email address.");

		Assert.assertEquals(actualMessage, "Please enter a valid email address.",
				"Incorrect validation message displayed");

		loginPage.attachStepScreenshot("Invalid email validation message displayed");

		LoggerUtil.info("========== TC-02-018 PASSED ==========");
	}
}