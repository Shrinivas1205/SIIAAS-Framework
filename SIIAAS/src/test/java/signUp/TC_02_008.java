package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.UserManagementPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_008 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-02-008 Verify approved user details visible under All Users tab")
	public void verifyApprovedUserVisibleInAllUsers() {

		LoginPage loginPage = new LoginPage(page);
		UserManagementPage userManagementPage = new UserManagementPage(page);

		LoggerUtil.info("========== TC-02-008 STARTED ==========");

		// Login as Admin
		LoggerUtil.info("Logging in as Admin");

		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		page.waitForLoadState();

		loginPage.attachStepScreenshot("Admin logged in successfully");

		// Open User Management
		LoggerUtil.info("Opening User Management");

		userManagementPage.openUserManagement();

		// Open All Users Tab
		LoggerUtil.info("Opening All Users Tab");

		userManagementPage.openAllUsersTab();

		loginPage.attachStepScreenshot("All Users tab opened");

		// Verify Approved User Email
		LoggerUtil.info("Verifying Approved User Email");

		Assert.assertTrue(page
				.getByText(ConfigReader.getProperty("approvedUserEmail1"), new Page.GetByTextOptions().setExact(true))
				.isVisible(), "Approved User Email Not Found");

		// Verify Approved User Name
		LoggerUtil.info("Verifying Approved User Name");

		Assert.assertTrue(page
				.getByText(ConfigReader.getProperty("approvedUserName1"), new Page.GetByTextOptions().setExact(true))
				.isVisible(), "Approved User Name Not Found");

		loginPage.attachStepScreenshot("Approved User details displayed in All Users");

		LoggerUtil.info("========== TC-02-008 PASSED ==========");
	}
}