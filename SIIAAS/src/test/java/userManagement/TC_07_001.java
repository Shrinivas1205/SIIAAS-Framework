package userManagement;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.UserManagementPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_07_001 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-07-001 Verify All Users tab loads successfully with user list")
	public void verifyAllUsersTabLoadsSuccessfully() {

		LoginPage loginPage = new LoginPage(page);

		UserManagementPage userManagementPage = new UserManagementPage(page);

		LoggerUtil.info("========== TC-07-001 STARTED ==========");

		/*
		 * STEP 1 Login as Admin
		 */

		LoggerUtil.info("Logging in as Admin");

		loginPage.login(

				ConfigReader.getProperty("username"),

				ConfigReader.getProperty("password"));

		loginPage.attachStepScreenshot("Admin Logged In");

		page.waitForLoadState();

		/*
		 * STEP 2 Open User Management
		 */

		LoggerUtil.info("Opening User Management");

		userManagementPage.openUserManagement();

		loginPage.attachStepScreenshot("User Management Opened");

		/*
		 * STEP 3 Verify All Users Tab
		 */

		Assert.assertTrue(

				userManagementPage.isAllUsersTabDisplayed(),

				"All Users tab is not displayed");

		/*
		 * STEP 4 Verify User Table
		 */

		Assert.assertTrue(

				userManagementPage.isUserTableDisplayed(),

				"User table not displayed");

		/*
		 * STEP 5 Verify Table Headers
		 */

		Assert.assertTrue(userManagementPage.isUserHeaderDisplayed(), "USER header missing");

		Assert.assertTrue(userManagementPage.isEmailHeaderDisplayed(), "EMAIL header missing");

		Assert.assertTrue(userManagementPage.isRoleHeaderDisplayed(), "ROLE header missing");

		Assert.assertTrue(userManagementPage.isDepartmentHeaderDisplayed(), "DEPARTMENT header missing");

		Assert.assertTrue(userManagementPage.isPermissionHeaderDisplayed(), "PERMISSIONS header missing");

		Assert.assertTrue(userManagementPage.isLastActiveHeaderDisplayed(), "LAST ACTIVE header missing");

		Assert.assertTrue(userManagementPage.isJoinedHeaderDisplayed(), "JOINED header missing");

		loginPage.attachStepScreenshot("All Users Table Loaded Successfully");

		LoggerUtil.info("========== TC-07-001 PASSED ==========");
	}
}