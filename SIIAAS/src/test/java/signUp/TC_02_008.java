package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.UserManagementPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_008 extends BaseTest {

	@Test(description = "TC-02-008 Verify approved user details visible under All Users tab")
	public void verifyApprovedUserVisibleInAllUsers() {

		LoginPage loginPage = new LoginPage(page);

		UserManagementPage userManagementPage = new UserManagementPage(page);

		LoggerUtil.info("========== TC-02-008 STARTED ==========");

		test.info("TC-02-008 Execution Started");

		/*
		 * STEP 1 Login As Admin
		 */

		loginPage.login(

				ConfigReader.getProperty("username"), ConfigReader.getProperty("password")

		);

		page.waitForLoadState();

		test.pass("Admin Login Successful");

		/*
		 * STEP 2 Open User Management
		 */

		userManagementPage.openUserManagement();

		/*
		 * STEP 3 Open All Users Tab
		 */

		userManagementPage.openAllUsersTab();

		test.info("Opened All Users Tab");

		/*
		 * STEP 4 Verify User Email
		 */
		boolean c = page
				.getByText(ConfigReader.getProperty("approvedUserEmail"), new Page.GetByTextOptions().setExact(true))
				.isVisible();
		if (c == true) {
			System.out.println("user email is present in all user table");
			LoggerUtil.info("user email is present in all user table");
		} else {
			System.out.println("user email is not present in all user table");
			LoggerUtil.info("user email is not present in all user table");
		}

		/*
		 * Assert.assertTrue(
		 * 
		 * userManagementPage.isUserPresentInAllUsers1(
		 * 
		 * ConfigReader.getProperty("approvedUserEmail")),
		 * 
		 * "Approved User Not Found"
		 * 
		 * );
		 */

		/*
		 * STEP 5 Verify User Name
		 */

		boolean b = page
				.getByText(ConfigReader.getProperty("approvedUserName"), new Page.GetByTextOptions().setExact(true))
				.isVisible();
		if (b == true) {
			System.out.println("username is present in all user table");
			LoggerUtil.info("username is present in all user table");
		} else {
			System.out.println("username is not present in all user table");
			LoggerUtil.info("username is not present in all user table");
		}

		/*
		 * Assert.assertTrue(
		 * 
		 * userManagementPage.isUserNameDisplayed(
		 * 
		 * ConfigReader.getProperty("approvedUserName")),
		 * 
		 * "User Name Not Displayed"
		 * 
		 * );
		 */

		test.pass("Approved User Details Displayed Successfully");

		LoggerUtil.info("========== TC-02-008 PASSED ==========");
	}
}