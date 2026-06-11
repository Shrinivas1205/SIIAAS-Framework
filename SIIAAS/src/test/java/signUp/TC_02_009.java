package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;
import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.LoggerUtil;

public class TC_02_009 extends BaseTest {

	@Test(description = "TC-02-009 Verify Signup With Empty Name Field")
	public void verifySignupWithEmptyNameField() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		LoggerUtil.info("========== TC-02-009 STARTED ==========");

		test.info("TC-02-009 Execution Started");

		/*
		 * STEP 1 Open Signup Page
		 */

		loginPage.clickSignupLink();

		test.info("Signup Page Opened");

		/*
		 * STEP 2 Leave Name Blank
		 */

		signupPage.enterEmail("testuser@test.com");

		signupPage.enterPassword("Pass@123");

		signupPage.enterConfirmPassword("Pass@123");

		signupPage.selectDesignation("QA Engineer");

		signupPage.selectDepartment("Testing");

		signupPage.selectLocation("Hyderabad");

		test.info("All Fields Filled Except Name");

		/*
		 * STEP 3 Click Sign Up
		 */

		signupPage.clickSignUpButton();

		test.info("Clicked Sign Up");

		/*
		 * STEP 4 Verify Validation Message
		 */

		Assert.assertEquals(

				signupPage.getValidationMessage("All fields are required."),

				"All fields are required.",

				"Incorrect Validation Message"

		);

		test.pass("Name Required Validation Displayed Successfully");

		LoggerUtil.info("========== TC-02-009 PASSED ==========");
	}
}