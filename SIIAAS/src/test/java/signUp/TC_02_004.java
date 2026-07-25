package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.UserManagementPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_004 extends BaseTest {

	// Before running, change the pending user's email in config.properties

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-02-004 Verify admin approves a pending user request")
	public void verifyAdminApprovesUserRequest() {

		LoginPage loginPage = new LoginPage(page);
		UserManagementPage userPage = new UserManagementPage(page);

		LoggerUtil.info("========== TC-02-004 STARTED ==========");

		String email = ConfigReader.getProperty("pendingUserEmail");

		LoggerUtil.info("Logging in as Admin");

		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		loginPage.attachStepScreenshot("Admin_Login_Successful");

		LoggerUtil.info("Opening User Management");

		userPage.openUserManagement();

		loginPage.attachStepScreenshot("User_Management_Page");

		LoggerUtil.info("Opening User Requests");

		userPage.openUserRequestsTab();

		loginPage.attachStepScreenshot("User_Requests_Tab");

		LoggerUtil.info("Verifying Pending User");

		Assert.assertTrue(userPage.isUserPresent(email), "User Request Not Found");

		loginPage.attachStepScreenshot("Pending_User_Found");

		LoggerUtil.info("Opening Assign Permission Popup");

		userPage.clickAssign(email);

		Assert.assertTrue(userPage.isAssignPermissionPopupDisplayed(), "Assign Permission Popup Not Displayed");

		loginPage.attachStepScreenshot("Assign_Permission_Popup");

		LoggerUtil.info("Assigning Super User Permission");

		userPage.assignSuperUserPermission();

		loginPage.attachStepScreenshot("Permission_Assigned");

		LoggerUtil.info("Approving User");

		userPage.clickApprove(email);

		loginPage.attachStepScreenshot("Approve_Button_Clicked");

		LoggerUtil.info("Verifying Approval Toast");

		Assert.assertTrue(userPage.isApprovalToastDisplayed(), "Approval Toast Not Displayed");

		loginPage.attachStepScreenshot("Approval_Toast");

		LoggerUtil.info("Opening All Users");

		userPage.openAllUsersTab();

		loginPage.attachStepScreenshot("All_Users_Tab");

		LoggerUtil.info("Verifying User in All Users");

		Assert.assertTrue(userPage.isUserPresentInAllUsers(email), "User Not Found In All Users");

		loginPage.attachStepScreenshot("User_Found_In_All_Users");

		LoggerUtil.info("========== TC-02-004 PASSED ==========");
	}
}