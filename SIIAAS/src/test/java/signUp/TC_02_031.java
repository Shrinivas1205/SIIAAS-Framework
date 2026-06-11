package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_031 extends BaseTest {

	@Test(description = "TC-02-031 Verify signup with special characters in Name")
	public void verifySignupWithSpecialCharactersInName() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		test.info("TC-02-031 Execution Started");

		/*
		 * Open Signup Page
		 */
		loginPage.clickSignupLink();

		LoggerUtil.info("Signup Page Opened");

		/*
		 * Enter Signup Details
		 */
		signupPage.enterFullName(ConfigReader.getProperty("tc02031.name"));

		signupPage.enterEmail(ConfigReader.getProperty("tc02031.email"));

		signupPage.enterPassword(ConfigReader.getProperty("tc02031.password"));

		signupPage.enterConfirmPassword(ConfigReader.getProperty("tc02031.confirmPassword"));

		signupPage.selectDesignation(ConfigReader.getProperty("signup.designation"));

		signupPage.selectDepartment(ConfigReader.getProperty("signup.department"));

		signupPage.selectLocation(ConfigReader.getProperty("signup.location"));

		loginPage.attachStepScreenshot("Special_Character_Name_Entered");

		/*
		 * Click Sign Up
		 */
		signupPage.clickSignUpButton();

		LoggerUtil.info("Clicked Sign Up Button");

		/*
		 * Verify Validation Message
		 */
		String actualMessage = signupPage.getValidationMessage("Enter a valid username.");

		Assert.assertEquals(actualMessage, "Enter a valid username.",
				"Incorrect validation message displayed");

		test.pass("Correct validation message displayed");

		LoggerUtil.info("TC-02-031 PASSED");
	}
}