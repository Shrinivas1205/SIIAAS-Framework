package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.LoggerUtil;

public class TC_02_028 extends BaseTest {

	@Test(description = "TC-02-028 Verify Sign Up button is visible and clickable")
	public void verifySignUpButtonVisibleAndClickable() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("Opening Signup Page");

		loginPage.clickSignupLink();

		LoggerUtil.info("Verifying Sign Up Button Visibility");

		Assert.assertTrue(signupPage.isSignUpButtonVisible(), "Sign Up Button is not visible");

		LoggerUtil.info("Verifying Sign Up Button Enabled State");

		Assert.assertTrue(signupPage.isSignUpButtonEnabled(), "Sign Up Button is disabled");

		loginPage.attachStepScreenshot("Before_Clicking_SignUp");

		signupPage.clickSignUp();

		loginPage.attachStepScreenshot("After_Clicking_SignUp");

		// Since all fields are empty,
		// button click should trigger validations.
		Assert.assertTrue(signupPage.getValidationMessage("required").length() > 0,
				"Button click did not trigger any validation");

		test.pass("Sign Up button is visible, enabled and clickable");

		LoggerUtil.info("TC-02-028 PASSED");
	}
}