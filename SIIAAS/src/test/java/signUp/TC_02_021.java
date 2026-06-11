package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_021 extends BaseTest {

	@Test(description = "TC-02-021 : Verify signup with weak numeric-only password")
	public void verifySignupWithWeakPasswordNumbersOnly() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== TC-02-021 STARTED ==========");

		/*
		 * STEP 1 : Open Signup Page
		 */
		loginPage.clickSignupLink();

		/*
		 * STEP 2 : Fill Signup Form
		 */
		signupPage.enterFullName(ConfigReader.getProperty("signup.name"));

		signupPage.enterEmail(ConfigReader.getProperty("tc02021.email"));

		signupPage.enterPassword(ConfigReader.getProperty("tc02021.password"));

		signupPage.enterConfirmPassword(ConfigReader.getProperty("tc02021.confirmPassword"));

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
		String actualMessage = signupPage.getValidationMessage("Password must be at least 8 characters.");

		Assert.assertEquals(actualMessage, "Password must be at least 8 characters.");

		test.pass("Weak password validation displayed successfully");

		LoggerUtil.info("TC-02-021 PASSED");
	}
}