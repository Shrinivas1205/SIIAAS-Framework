package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_014 extends BaseTest {

	@Test(description = "TC-02-014 Verify signup with empty Department field")
	public void verifySignupWithEmptyDepartmentField() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== STARTING TC-02-014 ==========");

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

		signupPage.enterFullName(ConfigReader.getProperty("tc02014.name"));

		signupPage.enterEmail(ConfigReader.getProperty("tc02014.email"));

		signupPage.enterPassword(ConfigReader.getProperty("tc02014.password"));

		signupPage.enterConfirmPassword(ConfigReader.getProperty("tc02014.confirmPassword"));

		signupPage.selectDesignation(ConfigReader.getProperty("tc02014.designation"));

		// Department intentionally blank
		signupPage.selectDepartment(ConfigReader.getProperty("tc02014.department"));

		signupPage.selectLocation(ConfigReader.getProperty("tc02014.location"));

		test.info("Entered Form Data With Empty Department");

		/*
		 * STEP 3 : Click Sign Up
		 */
		LoggerUtil.info("Clicking Sign Up Button");

		signupPage.clickSignUpButton();

		test.info("Sign Up Button Clicked");

		/*
		 * STEP 4 : Verify Validation Message
		 */
		LoggerUtil.info("Verifying Department Validation Message");

		String actualMessage = signupPage.getValidationMessage("All fields are required.");

		Assert.assertEquals(actualMessage, "All fields are required.", "Incorrect validation message displayed");

		test.pass("Department Required Validation Displayed Successfully");

		LoggerUtil.info("========== TC-02-014 PASSED ==========");
	}
}