package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_017 extends BaseTest {

	@Test(description = "TC-02-017 : Verify signup with invalid email missing domain")
	public void verifySignupWithInvalidEmailMissingDomain() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		test.info("TC-02-017 Execution Started");

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

		signupPage.enterEmail(ConfigReader.getProperty("signup.invalidEmailMissingDomain"));

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
		 * STEP 4 : Verify Validation
		 */
		String actualMessage = signupPage.getValidationMessage("Please enter a valid email address.");

		Assert.assertEquals(actualMessage, "Please enter a valid email address.");

		test.pass("Invalid Email Validation Verified Successfully");
	}
}