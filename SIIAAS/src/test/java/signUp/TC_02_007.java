package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_007 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-02-007 Verify rejected user cannot log in")
	public void verifyRejectedUserCannotLogin() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== TC-02-007 STARTED ==========");

		// Login with Rejected User
		LoggerUtil.info("Entering Rejected User Credentials");

		loginPage.login(ConfigReader.getProperty("rejectedUserEmail"),
				ConfigReader.getProperty("rejectedUserPassword"));

		LoggerUtil.info("Clicked Login Button");

		page.waitForLoadState();

		loginPage.attachStepScreenshot("Rejected User attempted to login");

		// Verify Error Message
		LoggerUtil.info("Verifying Rejected User Error Message");

		String actualMessage = signupPage.getLoginwithDenieduserErrorMessage();

		Assert.assertTrue(
				actualMessage.contains("rejected")
						|| actualMessage.contains("Your permission request has been denied by the administrator.")
						|| actualMessage.contains("Your permission request is still pending admin approval."),
				"Expected rejection message not displayed");

		loginPage.attachStepScreenshot("Rejected User login blocked successfully");

		LoggerUtil.info("========== TC-02-007 PASSED ==========");
	}
}