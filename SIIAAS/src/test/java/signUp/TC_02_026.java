package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_026 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class,description = "TC-02-026 : Verify password visibility toggle functionality")
	public void verifyPasswordVisibilityToggle() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== STARTING TC-02-026 ==========");

		/*
		 * STEP 1 - Open Signup Page
		 */
		loginPage.clickSignupLink();

		/*
		 * STEP 2 - Enter Password
		 */
		signupPage.enterPassword(ConfigReader.getProperty("password"));

		/*
		 * STEP 3 - Verify Initially Masked
		 */
		Assert.assertEquals(signupPage.getPasswordFieldType(), "password", "Password should be masked initially");
		loginPage.attachStepScreenshot("Password is masked by default");

		test.pass("Password is masked by default");

		/*
		 * STEP 4 - Click Eye Icon
		 */
		signupPage.clickPasswordEyeIcon();

		/*
		 * STEP 5 - Verify Visible
		 */
		Assert.assertEquals(signupPage.getPasswordFieldType(), "text",
				"Password should be visible after clicking eye icon");
		loginPage.attachStepScreenshot("Password became visible");
		test.pass("Password became visible");

		/*
		 * STEP 6 - Click Eye Icon Again
		 */
		signupPage.clickPasswordEyeIcon();

		/*
		 * STEP 7 - Verify Masked Again
		 */
		Assert.assertEquals(signupPage.getPasswordFieldType(), "password", "Password should be masked again");
		loginPage.attachStepScreenshot("Password masked again successfully");

		test.pass("Password masked again successfully");

		LoggerUtil.info("TC-02-026 PASSED");
	}
}