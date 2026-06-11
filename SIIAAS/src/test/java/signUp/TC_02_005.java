package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.UserManagementPage;
import utils.ConfigReader;

public class TC_02_005 extends BaseTest {

	@Test(description = "TC-02-005 Verify Admin Rejects User Request")
	public void verifyAdminRejectsUserRequest() {

		String email = ConfigReader.getProperty("rejecteduseremail");

		LoginPage loginPage = new LoginPage(page);

		UserManagementPage userPage = new UserManagementPage(page);

		test.info("TC-02-005 Execution Started");

		// =====================================================
		// Login
		// =====================================================

		loginPage.login(

				ConfigReader.getProperty("username"),

				ConfigReader.getProperty("password")

		);

		test.pass("Admin Login Successful");

		// =====================================================
		// Open User Requests
		// =====================================================

		userPage.openUserManagement();

		userPage.openUserRequestsTab();

		test.pass("User Requests Opened");

		// =====================================================
		// Verify Request Exists
		// =====================================================

		Assert.assertTrue(

				userPage.isUserPresent(email),

				"Pending User Request Not Found"

		);

		test.pass("Pending User Request Found");

		// =====================================================
		// Reject User
		// =====================================================

		userPage.clickReject(email);

		test.pass("Reject Button Clicked");

		// =====================================================
		// Verify Toast
		// =====================================================

		// Toast

		Assert.assertTrue(userPage.isRejectToastDisplayed(), "Reject Toast Not Displayed");

		test.pass("User Request Rejected");

	}
}