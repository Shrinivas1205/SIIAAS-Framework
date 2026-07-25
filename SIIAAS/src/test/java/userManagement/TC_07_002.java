package userManagement;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.UserManagementPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_07_002 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-07-002 Verify All Users table displays correct columns and user data")
	public void verifyAllUsersTableDisplaysCorrectUserData() {

		LoginPage loginPage = new LoginPage(page);
		UserManagementPage userManagement = new UserManagementPage(page);

		test.info("TC-07-002 Execution Started");

		LoggerUtil.info("========== TC-07-002 STARTED ==========");

		/*
		 * Step 1 : Login
		 */
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		/*
		 * Step 2 : Open User Management
		 */
		userManagement.openUserManagement();

		/*
		 * Step 3 : Verify First User Row
		 */
		Assert.assertTrue(userManagement.isFirstUserDisplayed(), "No user records available");

		/*
		 * Step 4 : Verify User Details
		 */
		Assert.assertTrue(userManagement.isAvatarDisplayed(), "Avatar missing");

		Assert.assertTrue(userManagement.isUsernameDisplayed(), "Username missing");

		Assert.assertTrue(userManagement.isEmailDisplayed(), "Email missing");

		Assert.assertTrue(userManagement.isRoleDisplayed(), "Role missing");

		Assert.assertTrue(userManagement.isDepartmentDisplayed(), "Department missing");

		Assert.assertTrue(userManagement.isPermissionDisplayed(), "Permission missing");

		Assert.assertTrue(userManagement.isLastActiveDisplayed(), "Last Active missing");

		Assert.assertTrue(userManagement.isJoinedDateDisplayed(), "Joined Date missing");

		userManagement.attachStepScreenshot("Atleast one user data is present");

		test.pass("User row details verified successfully");

		LoggerUtil.info("========== TC-07-002 PASSED ==========");
	}
}