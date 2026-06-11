package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import Pages.UserManagementPage;

import utils.ConfigReader;
import utils.DataProviders;

public class TC_02_003 extends BaseTest {

	@Test(dataProvider = "userRequestData", dataProviderClass = DataProviders.class, description = "TC-02-003 Verify signed up user appears in User Requests")

	public void verifyUserRequestAppearsAfterSignup(

			String name, String email, String password, String confirmPassword, String designation, String department,
			String location

	) {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		test.info("TC-02-003 Execution Started");

		/*
		 * STEP 1 Open Signup
		 */

		loginPage.clickSignupLink();

		/*
		 * STEP 2 Signup User
		 */

		signupPage.signup(name, email, password, confirmPassword, designation, department, location);

		Assert.assertTrue(signupPage.issignUpsuccessful(), "Signup failed");

		test.pass("User Signup Successful");
		

		/*S
		 * STEP 3 Login as Admin
		 */
		page.waitForSelector("//button[normalize-space()= 'Log in']");

		 // till login click button
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		page.waitForLoadState();

		test.pass("Admin Login Successful");

		/*
		 * STEP 4 Open User Requests
		 */

		UserManagementPage userManagementPage = new UserManagementPage(page);

		userManagementPage.openUserManagement();

		userManagementPage.openUserRequestsTab();

		test.info("Opened User Requests");

		/*
		 * STEP 5 Verify User Exists
		 */

		Assert.assertTrue(userManagementPage.isUserPresent(email), "User request not found");

		/*
		 * STEP 6 Verify Approve Button
		 */

		Assert.assertTrue(userManagementPage.isApproveButtonVisible(email), "Approve button missing");

		/*
		 * STEP 7 Verify Reject Button
		 */

		Assert.assertTrue(userManagementPage.isRejectButtonVisible(email), "Reject button missing");

		test.pass("User Request Found Successfully with Approve/Reject Actions");
	}
}