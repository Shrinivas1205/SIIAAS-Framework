package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_025 extends BaseTest {

	@Test(description = "TC-02-025 : Verify Confirm Password field is masked by default")
	public void verifyConfirmPasswordFieldMaskedByDefault() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== STARTING TC-02-025 ==========");

		/*
		 * Step 1 : Open Signup Page
		 */
		loginPage.clickSignupLink();

		/*
		 * Step 2 : Screenshot Before Entering Confirm Password
		 */
		loginPage.attachStepScreenshot("Before_Entering_Confirm_Password");

		/*
		 * Step 3 : Enter Confirm Password
		 */
		signupPage.enterConfirmPassword(ConfigReader.getProperty("signup.confirmPassword"));

		/*
		 * Step 4 : Screenshot After Entering Confirm Password
		 */
		loginPage.attachStepScreenshot("After_Entering_Confirm_Password");

		/*
		 * Step 5 : Verify Confirm Password Field Is Masked
		 */
		Assert.assertTrue(

				signupPage.isConfirmPasswordMasked(),

				"Confirm Password field is NOT masked by default"

		);

		test.pass("Confirm Password field is masked by default");

		LoggerUtil.info("TC-02-025 PASSED");
	}
}