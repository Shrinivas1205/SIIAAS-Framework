package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.DataProviders;
import utils.LoggerUtil;

public class TC_02_002 extends BaseTest {

	@Test(dataProvider = "strongPasswordData", dataProviderClass = DataProviders.class)
	public void verifySignupWithStrongPassword(String name, String email, String password, String confirmPassword,
			String designation, String department, String location) {

		LoginPage loginPage = new LoginPage(page);
		SignupPage signupPage = new SignupPage(page);

		loginPage.clickSignupLink();

		signupPage.signup(name, email, password, confirmPassword, designation, department, location);
		loginPage.attachStepScreenshot("User enterted sign up details");
		signupPage.clickSignUpButton();
		loginPage.attachStepScreenshot("Userclicked sign up button");

		Assert.assertTrue(signupPage.isApprovalMessageDisplayed(), "Signup failed");
		loginPage.attachStepScreenshot("User completed Sign up Successfully");
	}
}