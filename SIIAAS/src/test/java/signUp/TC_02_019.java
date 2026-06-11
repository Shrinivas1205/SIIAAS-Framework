package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_019 extends BaseTest {

	@Test(description = "TC-02-019 : Verify signup with already registered email")
	public void verifySignupWithAlreadyRegisteredEmail() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		test.info("TC-02-019 Execution Started");

		/*
		 * STEP 1 : Open Signup Page
		 */
		LoggerUtil.info("Opening Signup Page");

		loginPage.clickSignupLink();

		/*
		 * STEP 2 : Fill Signup Form
		 */
		LoggerUtil.info("Entering Signup Details");

		signupPage.enterFullName(ConfigReader.getProperty("signup.name"));

		signupPage.enterEmail(ConfigReader.getProperty("signup.existingEmail"));

		signupPage.enterPassword(ConfigReader.getProperty("signup.password"));

		signupPage.enterConfirmPassword(ConfigReader.getProperty("signup.confirmPassword"));

		signupPage.selectDesignation(ConfigReader.getProperty("signup.designation"));

		signupPage.selectDepartment(ConfigReader.getProperty("signup.department"));

		signupPage.selectLocation(ConfigReader.getProperty("signup.location"));

		/*
		 * STEP 3 : Click Sign Up
		 */
		LoggerUtil.info("Clicking Sign Up Button");

		signupPage.clickSignUpButton();

		/*
		 * STEP 4 : Verify Error Message
		 */
		String actualMessage = signupPage.getValidationMessage("user with this email already exists.");

		Assert.assertEquals(actualMessage, "user with this email already exists.", "Incorrect error message displayed");

		LoggerUtil.info("Existing Email Validation Verified");

		test.pass("Existing Email Validation Verified Successfully");
	}
}