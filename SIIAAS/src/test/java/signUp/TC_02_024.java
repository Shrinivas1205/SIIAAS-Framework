package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_024 extends BaseTest {

	@Test(description = "TC-02-024 : Verify Password field is masked by default")
	public void verifyPasswordFieldMaskedByDefault() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== TC-02-024 STARTED ==========");

		/*
		 * STEP 1 : Open Signup Page
		 */
		loginPage.clickSignupLink();

		/*
		 * STEP 2 : Capture Screenshot Before Entering Password
		 */
		loginPage.attachStepScreenshot("Before_Entering_Password");

		/*
		 * STEP 3 : Enter Password
		 */
		signupPage.enterPassword(ConfigReader.getProperty("password"));

		/*
		 * STEP 4 : Capture Screenshot After Entering Password
		 */
		loginPage.attachStepScreenshot("After_Entering_Password");

		/*
		 * STEP 5 : Verify Password Field Is Masked
		 */
		Assert.assertTrue(signupPage.isPasswordMasked(), "Password field is not masked by default");

		test.pass("Password field is masked by default");

		LoggerUtil.info("TC-02-024 PASSED");
	}
}