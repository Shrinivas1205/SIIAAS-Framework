package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_040 extends BaseTest {

	@Test(description = "TC-02-040 Pending user cannot login")
	public void verifyPendingUserCannotLogin() {

		LoginPage loginPage = new LoginPage(page);

		String pendingEmail = ConfigReader.getProperty("pendingUserEmail");

		String pendingPassword = ConfigReader.getProperty("pendingUserPassword");

		LoggerUtil.info("Attempting login with Pending User");

		test.info("Entering Pending User Credentials");

		loginPage.enterEmail(pendingEmail);
		loginPage.enterPassword(pendingPassword);

		loginPage.attachStepScreenshot("Pending_User_Credentials");

		loginPage.clickLogin();

		loginPage.attachStepScreenshot("Pending_User_Login_Attempt");

		String actualMessage = loginPage.getUnapprovedUserLoginErrorMessage();

		Assert.assertEquals(actualMessage, "Your permission request is still pending admin approval.",
				"Validation message mismatch");

		test.pass("Pending user login was blocked successfully");

		LoggerUtil.info("TC-02-040 PASSED");
	}
}