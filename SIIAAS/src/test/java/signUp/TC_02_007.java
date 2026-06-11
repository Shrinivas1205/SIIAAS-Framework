package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_007 extends BaseTest {

	@Test(description = "TC-02-007 Verify rejected user cannot log in")
	public void verifyRejectedUserCannotLogin() {

		LoginPage loginPage = new LoginPage(page);

		LoggerUtil.info("========== TC-02-007 STARTED ==========");

		test.info("TC-02-007 Execution Started");

		/*
		 * STEP 1 Login using rejected user credentials
		 */

		LoggerUtil.info("Entering Rejected User Credentials");

		loginPage.login(

				ConfigReader.getProperty("rejectedUserEmail"), ConfigReader.getProperty("rejectedUserPassword")

		);

		page.waitForLoadState();

		test.info("Rejected User Login Attempted");

		/*
		 * STEP 2 Verify Error Message
		 */

		String actualMessage = loginPage.getLoginwithunapprovedemailErrorMessage();

		Assert.assertTrue(

				actualMessage.contains("rejected") || actualMessage.contains("not approved")
						|| actualMessage.contains("Your permission request is still pending admin approval."),

				"Expected rejection message not displayed"

		);

		test.pass("Rejected User Login Blocked Successfully");

		LoggerUtil.info("========== TC-02-007 PASSED ==========");
	}
}