package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_022 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class,description = "TC-02-022 : Verify signup with mismatched password and confirm password")
	public void verifySignupWithMismatchedPasswords() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== TC-02-022 STARTED ==========");

		/*
		 * STEP 1 : Open Signup Page
		 */
		loginPage.clickSignupLink();

		/*
		 * STEP 2 : Fill Signup Form
		 */
		signupPage.enterFullName(ConfigReader.getProperty("signup.name"));

		signupPage.enterEmail(ConfigReader.getProperty("tc02022.email"));

		signupPage.enterPassword(ConfigReader.getProperty("tc02022.password"));

		signupPage.enterConfirmPassword(ConfigReader.getProperty("tc02022.confirmPassword"));

		signupPage.selectDesignation(ConfigReader.getProperty("signup.designation"));

		signupPage.selectDepartment(ConfigReader.getProperty("signup.department"));

		signupPage.selectLocation(ConfigReader.getProperty("signup.location"));

		/*
		 * STEP 3 : Click Sign Up
		 */
		signupPage.clickSignUpButton();

		/*
		 * STEP 4 : Verify Validation Message
		 */
		String actualMessage = signupPage.getValidationMessage("Passwords do not match");

		Assert.assertEquals(actualMessage, "Passwords do not match", "Incorrect validation message displayed");

		test.pass("Password mismatch validation displayed successfully");

		LoggerUtil.info("TC-02-022 PASSED");
	}
}