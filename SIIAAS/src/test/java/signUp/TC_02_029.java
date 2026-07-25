package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_029 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class,description = "TC-02-029 Verify Login link navigates to Login Page")
	public void verifyLoginLinkNavigatesToLoginPage() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("Opening Signup Page");

		loginPage.clickSignupLink();

		LoggerUtil.info("Verifying Login Link Visibility");

		Assert.assertTrue(signupPage.isLoginLinkVisible(), "Login Link is not visible");

		loginPage.attachStepScreenshot("Before_Clicking_Login_Link");

		signupPage.clickLoginLink();

		loginPage.attachStepScreenshot("After_Clicking_Login_Link");

		LoggerUtil.info("Verifying User Navigated To Login Page");

		Assert.assertTrue(signupPage.isLoginPageDisplayed(), "User was not navigated to Login Page");

		test.pass("User successfully navigated to Login page");

		LoggerUtil.info("TC-02-029 PASSED");
	}
}