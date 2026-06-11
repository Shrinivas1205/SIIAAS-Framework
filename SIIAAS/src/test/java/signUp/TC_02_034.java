package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.LoggerUtil;

public class TC_02_034 extends BaseTest {

	@Test(description = "TC-02-034 Verify Tab Key Navigation Across Signup Fields")
	public void verifyTabNavigationAcrossSignupFields() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		test.info("TC-02-034 Execution Started");

		/*
		 * Open Signup Page
		 */
		loginPage.clickSignupLink();

		LoggerUtil.info("Signup Page Opened");

		/*
		 * Click Name Field
		 */
		page.locator(signupPage.fullNameField).click();

		loginPage.attachStepScreenshot("Name_Field_Focused");

		/*
		 * l Name -> Email
		 */
		page.keyboard().press("Tab");
		String placeholder = signupPage.getFocusedElementAttribute("placeholder");

		Assert.assertEquals(placeholder, "Enter your email id");

		/*
		 * Email -> Password
		 */
		page.keyboard().press("Tab");
		String placeholder1 = signupPage.getFocusedElementAttribute("placeholder");

		Assert.assertEquals(placeholder1, "Enter your Password");

		/*
		 * Password -> Confirm Password
		 */
		page.keyboard().press("Tab");
		page.keyboard().press("Tab");
		String placeholder2 = signupPage.getFocusedElementAttribute("placeholder");

		Assert.assertEquals(placeholder2, "Confirm your password");
		/*
		 * Confirm Password -> Designation
		 */
		page.keyboard().press("Tab");
		page.keyboard().press("Tab");
		String placeholder3 = signupPage.getFocusedElementAttribute("placeholder");

		Assert.assertEquals(placeholder3, "Enter your designation");

		/*
		 * Designation -> Department
		 */
		page.keyboard().press("Tab");
		String placeholder4 = signupPage.getFocusedElementAttribute("placeholder");

		Assert.assertEquals(placeholder4, "Enter your department");

		/*
		 * Department -> Location
		 */
		page.keyboard().press("Tab");
		String placeholder5 = signupPage.getFocusedElementAttribute("placeholder");

		Assert.assertEquals(placeholder5, "Enter your Location");

		/*
		 * Location -> Sign Up Button
		 */
		page.keyboard().press("Tab");

		Assert.assertTrue(signupPage.getFocusedElementText().contains("Sign Up"),
				"Focus did not move to Sign Up button");

		loginPage.attachStepScreenshot("SignUp_Button_Focused");

		test.pass("Tab navigation sequence verified successfully");

		LoggerUtil.info("TC-02-034 PASSED");
	}
}