package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import Pages.UserManagementPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;

public class TC_02_003 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-02-003 Verify signed up user appears in User Requests")

	public void verifyUserRequestAppearsAfterSignup() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);
		UserManagementPage userManagementPage = new UserManagementPage(page);

		LoggerUtil.info("========== TC-02-003 STARTED ==========");

		String name = ConfigReader.getProperty("tc02003.name");
		String email = ConfigReader.getProperty("tc02003.email");
		String password = ConfigReader.getProperty("tc02003.password");
		String confirmPassword = ConfigReader.getProperty("tc02003.confirmPassword");
		String designation = ConfigReader.getProperty("tc02003.designation");
		String department = ConfigReader.getProperty("tc02003.department");
		String location = ConfigReader.getProperty("tc02003.location");

		LoggerUtil.info("Opening Signup Page");

		loginPage.clickSignupLink();
		loginPage.attachStepScreenshot("Signup_Page_Opened");

		LoggerUtil.info("Entering Signup Details");

		signupPage.signup(name, email, password, confirmPassword, designation, department, location);

		loginPage.attachStepScreenshot("Signup_Details_Entered");

		LoggerUtil.info("Clicking Signup Button");

		signupPage.clickSignUpButton();

		loginPage.attachStepScreenshot("Signup_Button_Clicked");

		Assert.assertTrue(signupPage.isApprovalMessageDisplayed(), "Signup failed");

		LoggerUtil.info("Signup completed successfully");

		loginPage.attachStepScreenshot("Signup_Successful");

		LoggerUtil.info("Logging in as Admin");

		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		loginPage.attachStepScreenshot("Admin_Login");

		LoggerUtil.info("Opening User Management");

		userManagementPage.openUserManagement();

		userManagementPage.openUserRequestsTab();

		loginPage.attachStepScreenshot("User_Requests_Page");

		LoggerUtil.info("Verifying User Request");

		Assert.assertTrue(userManagementPage.isUserPresent(email), "User request not found");

		loginPage.attachStepScreenshot("User_Request_Found");

		LoggerUtil.info("Verifying Approve Button");

		Assert.assertTrue(userManagementPage.isApproveButtonVisible(email), "Approve button missing");

		LoggerUtil.info("Verifying Reject Button");

		Assert.assertTrue(userManagementPage.isRejectButtonVisible(email), "Reject button missing");

		loginPage.attachStepScreenshot("Approve_Reject_Buttons");

		LoggerUtil.info("========== TC-02-003 PASSED ==========");
	}
}