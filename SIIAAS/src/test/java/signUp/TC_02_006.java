package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_006 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-02-006 Verify approved user can log in successfully")
	public void verifyApprovedUserCanLoginSuccessfully() {

		LoginPage loginPage = new LoginPage(page);

		LoggerUtil.info("========== TC-02-006 STARTED ==========");

		// Login with Approved User
		LoggerUtil.info("Entering Approved User Credentials");

		loginPage.login(ConfigReader.getProperty("approvedUserEmail"),
				ConfigReader.getProperty("approvedUserPassword"));

		LoggerUtil.info("Approved User Clicked Login Button");

		page.waitForLoadState();

		loginPage.attachStepScreenshot("Approved User logged in");

		// Verify Dashboard
		LoggerUtil.info("Verifying Projects Dashboard");

		Assert.assertTrue(loginPage.isProjectsDashboardDisplayed(), "Projects Dashboard Not Displayed");

		loginPage.attachStepScreenshot("Projects Dashboard displayed successfully");

		LoggerUtil.info("========== TC-02-006 PASSED ==========");
	}
}