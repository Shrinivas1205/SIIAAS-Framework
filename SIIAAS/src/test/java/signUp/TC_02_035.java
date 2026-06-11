package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import Pages.UserManagementPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_035 extends BaseTest {

	@Test(description = "TC-02-035 Verify User Request Count Badge Increments After New Signup")
	public void verifyUserRequestCountIncrement() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);
		UserManagementPage userManagementPage = new UserManagementPage(page);

		// ==========================================
		// Step 1 : Login As Admin
		// ==========================================

		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		userManagementPage.openUserManagement();
		userManagementPage.openUserRequestsTab();

		int beforeCount = userManagementPage.getUserRequestsCount();

		LoggerUtil.info("Before Signup Count : " + beforeCount);

		// ==========================================
		// Step 2 : Logout
		// ==========================================

		userManagementPage.logout();

		Assert.assertTrue(userManagementPage.isLogoutSuccessful(), "Logout failed");

		// ==========================================
		// Step 3 : Signup New User
		// ==========================================

		loginPage.clickSignupLink();

		String uniqueEmail = "automation" + System.currentTimeMillis() + "@test.com";

		signupPage.signup(ConfigReader.getProperty("tc02035.name"), uniqueEmail,
				ConfigReader.getProperty("tc02035.password"), ConfigReader.getProperty("tc02035.confirmPassword"),
				ConfigReader.getProperty("tc02035.designation"), ConfigReader.getProperty("tc02035.department"),
				ConfigReader.getProperty("tc02035.location"));

		signupPage.clickSignUpButton();

		Assert.assertTrue(signupPage.isApprovalMessageDisplayed(), "Signup failed");

		// ==========================================
		// Step 4 : Login Again As Admin
		// ==========================================

		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		userManagementPage.openUserManagement();
		userManagementPage.openUserRequestsTab();

		//page.reload();

		int afterCount = userManagementPage.getUserRequestsCount();

		LoggerUtil.info("After Signup Count : " + afterCount);

		// ==========================================
		// Validation
		// ==========================================

		Assert.assertEquals(afterCount, beforeCount + 1, "User Request count did not increment by 1");

		test.pass("User Request Count Incremented Successfully");

		LoggerUtil.info("TC-02-035 PASSED");
	}
}