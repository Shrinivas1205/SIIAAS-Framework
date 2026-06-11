package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.LoggerUtil;

public class TC_02_015 extends BaseTest {

	@Test(description = "TC-02-015 : Verify signup with empty Location field")
	public void verifySignupWithEmptyLocation() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		test.info("TC-02-015 Execution Started");

		/*
		 * STEP 1 : Open Signup Page
		 */
		LoggerUtil.info("Opening Signup Page");

		loginPage.clickSignupLink();

		/*
		 * STEP 2 : Fill Form Except Location
		 */
		LoggerUtil.info("Entering Signup Details Without Location");

		signupPage.enterFullName("John Doe");

		signupPage.enterEmail("john.location@test.com");

		signupPage.enterPassword("Pass@123");

		signupPage.enterConfirmPassword("Pass@123");

		signupPage.selectDesignation("Engineer");

		signupPage.selectDepartment("IT");

		// Intentionally NOT entering Location

		/*
		 * STEP 3 : Click Sign Up
		 */
		LoggerUtil.info("Clicking Sign Up Button");

		signupPage.clickSignUpButton();

		/*
		 * STEP 4 : Verify Validation Message
		 */
		String actualMessage = signupPage.getValidationMessage("All fields are required.");

		LoggerUtil.info("Validation Message Displayed : " + actualMessage);

		Assert.assertEquals(actualMessage, "All fields are required.", "Incorrect validation message displayed");

		test.pass("Location Required Validation Verified Successfully");
	}
}