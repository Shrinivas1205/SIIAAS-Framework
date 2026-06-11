package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_032 extends BaseTest {

	@Test(description = "TC-02-032 Verify signup with very long Name input")
	public void verifySignupWithVeryLongName() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		test.info("TC-02-032 Execution Started");

		/*
		 * Open Signup Page
		 */
		loginPage.clickSignupLink();

		LoggerUtil.info("Signup Page Opened");

		/*
		 * Enter Signup Details
		 */
		signupPage.enterFullName(ConfigReader.getProperty("tc02032.name"));

		loginPage.attachStepScreenshot("Long_Name_Entered");

		signupPage.enterEmail(ConfigReader.getProperty("tc02032.email"));

		signupPage.enterPassword(ConfigReader.getProperty("tc02032.password"));

		signupPage.enterConfirmPassword(ConfigReader.getProperty("tc02032.confirmPassword"));

		signupPage.selectDesignation(ConfigReader.getProperty("signup.designation"));

		signupPage.selectDepartment(ConfigReader.getProperty("signup.department"));

		signupPage.selectLocation(ConfigReader.getProperty("signup.location"));

		loginPage.attachStepScreenshot("Signup_Details_Entered");

		/*
		 * Click Sign Up
		 */
		signupPage.clickSignUpButton();

		LoggerUtil.info("Clicked Sign Up Button");

		/*
		 * Verify Validation Message
		 *
		 * Replace the expected text below with the actual validation message displayed
		 * by your application.
		 */
		String actualMessage = signupPage.getValidationMessage("Ensure this field has no more than 150 characters.");

		Assert.assertEquals(actualMessage, "Ensure this field has no more than 150 characters.",
				"Incorrect validation message displayed");

		test.pass("Maximum character validation displayed");

		LoggerUtil.info("TC-02-032 PASSED");
	}
}