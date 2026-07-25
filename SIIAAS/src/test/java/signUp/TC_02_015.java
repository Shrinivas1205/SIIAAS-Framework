package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_015 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-02-015 Verify signup with empty Location field")
	public void verifySignupWithEmptyLocation() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== TC-02-015 STARTED ==========");

		// Open Signup Page
		LoggerUtil.info("Opening Signup Page");

		loginPage.clickSignupLink();

		// Enter Signup Details Except Location
		LoggerUtil.info("Entering Signup Details Except Location");

		signupPage.enterFullName(ConfigReader.getProperty("tc02014.name"));
		signupPage.enterEmail(ConfigReader.getProperty("tc02014.email"));
		signupPage.enterPassword(ConfigReader.getProperty("tc02014.password"));
		signupPage.enterConfirmPassword(ConfigReader.getProperty("tc02014.confirmPassword"));
		signupPage.selectDesignation(ConfigReader.getProperty("tc02014.designation"));
		signupPage.selectDepartment(ConfigReader.getProperty("tc02014.department"));
		// Location intentionally left blank

		loginPage.attachStepScreenshot("Entered signup details with empty Location");

		// Click Sign Up
		LoggerUtil.info("Clicking Signup Button");

		signupPage.clickSignUpButton();

		// Verify Validation Message
		LoggerUtil.info("Verifying Validation Message");

		Assert.assertEquals(signupPage.getValidationMessage("All fields are required."), "All fields are required.",
				"Incorrect validation message displayed");

		loginPage.attachStepScreenshot("Validation message displayed for empty Location field");

		LoggerUtil.info("========== TC-02-015 PASSED ==========");
	}
}