package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.LoggerUtil;

public class TC_02_016 extends BaseTest {

	@Test(description = "TC-02-016 : Verify signup with all fields empty")
	public void verifySignupWithAllFieldsEmpty() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		test.info("TC-02-016 Execution Started");

		/*
		 * STEP 1 : Open Signup Page
		 */
		LoggerUtil.info("Opening Signup Page");

		loginPage.clickSignupLink();

		/*
		 * STEP 2 : Click Sign Up Without Entering Any Data
		 */
		LoggerUtil.info("Clicking Sign Up Without Entering Data");

		signupPage.clickSignUpButton();

		/*
		 * STEP 3 : Verify All Validation Messages
		 */

		Assert.assertEquals(signupPage.getValidationMessage("All fields are required."), "All fields are required.");

		LoggerUtil.info("All Mandatory Field Validations Displayed Successfully");

		test.pass("All Required Field Validations Verified Successfully");
	}
}