package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.UserManagementPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_037 extends BaseTest {

	@Test(description = "TC-02-037 Verify Admin Assigns Permission Before Approving User")
	public void verifyAdminAssignPermissionBeforeApproval() {

		LoginPage loginPage = new LoginPage(page);
		UserManagementPage userManagementPage = new UserManagementPage(page);

		String email = ConfigReader.getProperty("pendingUserEmail");

		LoggerUtil.info("Starting TC-02-037");

		// Login as Admin
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		// Navigate to User Requests
		userManagementPage.openUserManagement();
		userManagementPage.openUserRequestsTab();

		// Verify User Exists
		Assert.assertTrue(userManagementPage.isUserPresent(email), "Pending user request not found");

		// Click Assign
		userManagementPage.clickAssign(email);

		// Verify Assign Permission Popup
		Assert.assertTrue(userManagementPage.isAssignPermissionPopupDisplayed(),
				"Assign Permission popup not displayed");

		// Assign Super User Permission
		userManagementPage.assignSuperUserPermission();

		// Approve User
		userManagementPage.clickApprove(email);

		// Verify Approval Success
		Assert.assertTrue(userManagementPage.isApprovalToastDisplayed(), "Approval success message not displayed");

		userManagementPage.attachStepScreenshot("User_Approved_With_Assigned_Role");

		// Open All Users
		userManagementPage.openAllUsersTab();

		// Verify User Exists In All Users
		Assert.assertTrue(userManagementPage.isUserPresentInAllUsers(email), "Approved user not found in All Users");

		test.pass("Permission assigned successfully and user approved");

		LoggerUtil.info("TC-02-037 PASSED");
	}
}