package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_020 extends BaseTest {

	@Test(description = "TC-02-020 : Verify signup succeeds when email contains leading/trailing spaces")
	public void verifySignupWithEmailContainingSpaces() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== TC-02-020 STARTED ==========");

		/*
		 * STEP 1 : Open Signup Page
		 */
		loginPage.clickSignupLink();

		/*
		 * STEP 2 : Enter Signup Details
		 */
		signupPage.enterFullName(ConfigReader.getProperty("signup.name"));

		signupPage.enterEmail(ConfigReader.getProperty("signup.emailWithSpaces"));

		signupPage.enterPassword(ConfigReader.getProperty("signup.password"));

		signupPage.enterConfirmPassword(ConfigReader.getProperty("signup.confirmPassword"));

		signupPage.selectDesignation(ConfigReader.getProperty("signup.designation"));

		signupPage.selectDepartment(ConfigReader.getProperty("signup.department"));

		signupPage.selectLocation(ConfigReader.getProperty("signup.location"));

		/*
		 * STEP 3 : Click Signup
		 */
		signupPage.clickSignUpButton();

		/*
		 * STEP 4 : Verify Signup Success
		 */
		Assert.assertTrue(signupPage.isApprovalMessageDisplayed(), "Signup failed for email containing spaces");

		LoggerUtil.info("Signup successful with email containing spaces");

		test.pass("Application trimmed spaces and signup completed successfully");
	}
}