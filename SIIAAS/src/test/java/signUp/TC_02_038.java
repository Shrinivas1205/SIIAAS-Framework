package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.LoggerUtil;

public class TC_02_038 extends BaseTest {

	@Test(description = "TC-02-038 Verify Signup Page Accessible From Login Page")
	public void verifySignupPageAccessibleFromLoginPage() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("Starting TC-02-038");

		// Click Signup Link
		loginPage.clickSignupLink();

		loginPage.attachStepScreenshot("Signup_Link_Clicked");

		// Verify Signup Page Opened
		Assert.assertTrue(signupPage.isSignupPageDisplayed(), "Signup page did not open successfully");

		loginPage.attachStepScreenshot("Signup_Page_Displayed");

		test.pass("Signup page opened successfully from Login page");

		LoggerUtil.info("TC-02-038 PASSED");
	}
}