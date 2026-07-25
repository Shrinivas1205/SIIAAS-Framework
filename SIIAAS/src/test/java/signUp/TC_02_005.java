package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.UserManagementPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_005 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-02-005 Verify Admin Rejects User Request")
	public void verifyAdminRejectsUserRequest() {

		LoginPage loginPage = new LoginPage(page);
		UserManagementPage userPage = new UserManagementPage(page);

		LoggerUtil.info("========== TC-02-005 STARTED ==========");

		String email = ConfigReader.getProperty("rejecteduseremail");

		// Login as Admin
		LoggerUtil.info("Logging in as Admin");

		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		loginPage.attachStepScreenshot("Admin_Login_Successful");

		// Open User Management
		LoggerUtil.info("Opening User Management");

		userPage.openUserManagement();

		loginPage.attachStepScreenshot("User_Management_Page");

		// Open User Requests
		LoggerUtil.info("Opening User Requests Tab");

		userPage.openUserRequestsTab();

		loginPage.attachStepScreenshot("User_Requests_Tab");

		// Verify Pending User
		LoggerUtil.info("Verifying Pending User Request");

		Assert.assertTrue(userPage.isUserPresent(email), "Pending User Request Not Found");

		loginPage.attachStepScreenshot("Pending_User_Request_Found");

		// Reject User
		LoggerUtil.info("Rejecting User Request");

		userPage.clickReject(email);

		loginPage.attachStepScreenshot("Reject_Button_Clicked");

		// Verify Toast
		LoggerUtil.info("Verifying Reject Toast Message");

		Assert.assertTrue(userPage.isRejectToastDisplayed(), "Reject Toast Not Displayed");

		loginPage.attachStepScreenshot("Reject_Toast_Displayed");

		LoggerUtil.info("========== TC-02-005 PASSED ==========");
	}
}