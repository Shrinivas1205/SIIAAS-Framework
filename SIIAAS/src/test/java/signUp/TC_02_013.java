package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_013 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class,
			description = "TC-02-013 Verify signup with empty Designation field")
	public void verifySignupWithEmptyDesignationField() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== TC-02-013 STARTED ==========");

		// Open Signup Page
		LoggerUtil.info("Opening Signup Page");

		loginPage.clickSignupLink();

		// Enter Signup Details Except Designation
		LoggerUtil.info("Entering Signup Details Except Designation");

		signupPage.enterFullName(ConfigReader.getProperty("tc02013.name"));
		signupPage.enterEmail(ConfigReader.getProperty("tc02013.email"));
		signupPage.enterPassword(ConfigReader.getProperty("tc02013.password"));
		signupPage.enterConfirmPassword(ConfigReader.getProperty("tc02013.confirmPassword"));
		//empty Designation field
		signupPage.selectDepartment(ConfigReader.getProperty("tc02013.department"));
		signupPage.selectLocation(ConfigReader.getProperty("tc02013.location"));

		loginPage.attachStepScreenshot("Entered signup details with empty Designation");

		// Click Sign Up
		LoggerUtil.info("Clicking Signup Button");

		signupPage.clickSignUpButton();

		// Verify Validation Message
		LoggerUtil.info("Verifying Validation Message");

		Assert.assertEquals(
				signupPage.getValidationMessage("All fields are required."),
				"All fields are required.",
				"Incorrect validation message displayed");

		loginPage.attachStepScreenshot("Validation message displayed for empty Designation field");

		LoggerUtil.info("========== TC-02-013 PASSED ==========");
	}
}