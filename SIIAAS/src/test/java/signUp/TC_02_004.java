package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.UserManagementPage;
import utils.ConfigReader;

public class TC_02_004 extends BaseTest {
	
	//before running change the mail id of the approver user

	@Test(description = "TC-02-004 Verify admin approves a pending user request")
	public void verifyAdminApprovesUserRequest() {

		String email = ConfigReader.getProperty("pendingUserEmail");

		LoginPage loginPage = new LoginPage(page);

		UserManagementPage userPage = new UserManagementPage(page);

		test.info("TC-02-004 Execution Started");

		// Login

		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		test.pass("Admin Login Successful");

		// Open User Management

		userPage.openUserManagement();

		userPage.openUserRequestsTab();

		test.pass("User Requests Opened");

		// Verify Request

		Assert.assertTrue(userPage.isUserPresent(email), "User Request Not Found");

		test.pass("Pending User Found");

		// Assign Permission

		userPage.clickAssign(email);

		Assert.assertTrue(userPage.isAssignPermissionPopupDisplayed());

		test.pass("Assign Permission Popup Opened");

		userPage.assignSuperUserPermission();

		test.pass("Super User Assigned");

		// Approve

		userPage.clickApprove(email);

		test.pass("Approve Button Clicked");

		// Toast

		Assert.assertTrue(userPage.isApprovalToastDisplayed(), "Approval Toast Not Displayed");

		test.pass("Approval Toast Verified");

		// All Users

		userPage.openAllUsersTab();

		Assert.assertTrue(userPage.isUserPresentInAllUsers(email), "User Not Found In All Users");

		test.pass("User Appears In All Users");

		test.pass("TC-02-004 Passed");
	}
}