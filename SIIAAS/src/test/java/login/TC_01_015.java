package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_01_015 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class,description = "TC-01-015 : Verify pressing Enter key submits the login form")
	public void verifyEnterKeySubmitsLoginForm() {
		
		

		// Page Object Creation
		LoginPage loginPage = new LoginPage(page);

		LoggerUtil.info("========== STARTING TC-01-015 ==========");
		
		  // till login click button
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
		
		//Press enter key
		loginPage.EnterkeyPress();

		// Wait For Navigation
		//page.waitForLoadState();

		// Optional Extra Wait
		page.waitForTimeout(3000);

		loginPage.attachStepScreenshot("After_Enter_Key");


		// Verify Dashboard

		Assert.assertTrue(loginPage.isProjectsDashboardDisplayed(), "User NOT landed on Projects Dashboard");
		loginPage.attachStepScreenshot("Projects_Dashboard_Displayed");


		LoggerUtil.info("TC-01-015 PASSED");
		test.pass("Enter key successfully submitted login form");
	}
}