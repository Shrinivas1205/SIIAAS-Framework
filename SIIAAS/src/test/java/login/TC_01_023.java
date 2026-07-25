package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;
import utils.ScreenshotUtil;

public class TC_01_023 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-01-023 : Verify already logged-in user accessing login page is redirected")
	public void verifyLoggedInUserRedirectedFromLoginPage() {

		// Page Object Creation
		LoginPage loginPage = new LoginPage(page);

		LoggerUtil.info("========== STARTING TC-01-023 ==========");
		test.info("TC-01-023 Execution Started");

		// Login Successfully
		LoggerUtil.info("Logging In With Valid Credentials");
		test.info("Logging In With Valid Credentials");

		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		loginPage.attachStepScreenshot("Successful_Login");

		// Verify Projects Dashboard Displayed
		Assert.assertTrue(loginPage.isProjectsDashboardDisplayed(), "Projects Dashboard is not displayed after login");

		// Navigate To Login Page URL Again
		LoggerUtil.info("Navigating Back To Login Page URL");
		test.info("Navigating Back To Login Page URL");
		page.navigate("http://192.168.28.102:3000/");

		loginPage.attachStepScreenshot("Navigated_Back_To_Login_Page");

		// Verify Automatic Redirect To Projects Page
		LoggerUtil.info("Verifying Automatic Redirect To Projects Page");
		test.info("Verifying Automatic Redirect To Projects Page");

		boolean isProjectsDashboardDisplayed = loginPage.isProjectsDashboardDisplayed();

		loginPage.attachStepScreenshot("Redirected_To_Projects_Page");

		// Validation
		Assert.assertTrue(isProjectsDashboardDisplayed, "Logged-in user was not redirected to Projects page");

		LoggerUtil.info("TC-01-023 PASSED");
		test.pass("Logged-in user redirect verified successfully");
	}
}
