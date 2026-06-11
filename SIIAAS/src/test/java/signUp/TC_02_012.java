package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_012 extends BaseTest {

	@Test(description = "TC-02-012 Verify signup with empty Confirm Password field")
	public void verifySignupWithEmptyConfirmPasswordField() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== STARTING TC-02-012 ==========");

		/*
		 * STEP 1 : Open Signup Page
		 */
		LoggerUtil.info("Opening Signup Page");

		loginPage.clickSignupLink();

		test.info("Signup Page Opened");

		/*
		 * STEP 2 : Enter Signup Details
		 */
		LoggerUtil.info("Entering Signup Details");

		signupPage.enterFullName(ConfigReader.getProperty("tc02012.name"));

		signupPage.enterEmail(ConfigReader.getProperty("tc02012.email"));

		signupPage.enterPassword(ConfigReader.getProperty("tc02012.password"));

		signupPage.enterConfirmPassword(ConfigReader.getProperty("tc02012.confirmPassword")); // Blank

		signupPage.selectDesignation(ConfigReader.getProperty("tc02012.designation"));

		signupPage.selectDepartment(ConfigReader.getProperty("tc02012.department"));

		signupPage.selectLocation(ConfigReader.getProperty("tc02012.location"));

		test.info("Entered Form Data With Empty Confirm Password");

		/*
		 * STEP 3 : Click Sign Up
		 */
		LoggerUtil.info("Clicking Sign Up Button");

		signupPage.clickSignUpButton();

		test.info("Sign Up Button Clicked");

		/*
		 * STEP 4 : Verify Validation Message
		 */
		LoggerUtil.info("Verifying Confirm Password Validation Message");

		String actualMessage = signupPage.getValidationMessage("All fields are required.");

		Assert.assertEquals(actualMessage, "All fields are required.", "Incorrect validation message displayed");

		test.pass("Confirm Password Required Validation Displayed Successfully");

		LoggerUtil.info("========== TC-02-012 PASSED ==========");
	}
}