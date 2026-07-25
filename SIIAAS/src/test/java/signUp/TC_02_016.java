package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_016 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-02-016 Verify signup with all fields empty")
	public void verifySignupWithAllFieldsEmpty() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== TC-02-016 STARTED ==========");

		// Open Signup Page
		LoggerUtil.info("Opening Signup Page");

		loginPage.clickSignupLink();

		loginPage.attachStepScreenshot("Signup page opened");

		// Click Sign Up Without Entering Any Data
		LoggerUtil.info("Clicking Sign Up Without Entering Any Data");

		signupPage.clickSignUpButton();

		loginPage.attachStepScreenshot("Clicked Sign Up with all fields empty");

		// Verify Validation Message
		LoggerUtil.info("Verifying Validation Message");

		Assert.assertEquals(signupPage.getValidationMessage("All fields are required."), "All fields are required.",
				"Incorrect validation message displayed");

		loginPage.attachStepScreenshot("Validation message displayed for all mandatory fields");

		LoggerUtil.info("========== TC-02-016 PASSED ==========");
	}
}