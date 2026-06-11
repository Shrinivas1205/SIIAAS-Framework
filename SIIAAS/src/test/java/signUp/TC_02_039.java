package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_039 extends BaseTest {

	@Test(description = "TC-02-039 Verify form values are retained after validation error")
	public void verifyFormRetainsValuesOnValidationError() {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		String name = ConfigReader.getProperty("tc02039.name");
		String email = ConfigReader.getProperty("tc02039.email");
		String password = ConfigReader.getProperty("tc02039.password");
		String confirmPassword = ConfigReader.getProperty("tc02039.confirmPassword");
		String designation = ConfigReader.getProperty("tc02039.designation");
		String department = ConfigReader.getProperty("tc02039.department");
		String location = ConfigReader.getProperty("tc02039.location");

		loginPage.clickSignupLink();

		signupPage.signup(name, email, password, confirmPassword, designation, department, location);

		loginPage.attachStepScreenshot("Before_Clicking_SignUp");

		signupPage.clickSignUpButton();

		loginPage.attachStepScreenshot("Validation_Error_Displayed");

		// Verify validation message
		String actualMessage = signupPage.getValidationMessage("Passwords do not match");

		Assert.assertEquals(actualMessage, "Passwords do not match", "Validation message mismatch");

		// Verify entered values are retained
		Assert.assertEquals(signupPage.getNameValue(), name);
		Assert.assertEquals(signupPage.getEmailValue(), email);
		Assert.assertEquals(signupPage.getPasswordValue(), password);
		Assert.assertEquals(signupPage.getConfirmPasswordValue(), confirmPassword);
		Assert.assertEquals(signupPage.getDesignationValue(), designation);
		Assert.assertEquals(signupPage.getDepartmentValue(), department);
		Assert.assertEquals(signupPage.getLocationValue(), location);

		loginPage.attachStepScreenshot("Values_Retained_After_Error");

		test.pass("Form values retained after validation error");

		LoggerUtil.info("TC-02-039 PASSED");
	}
}