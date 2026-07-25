package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.RetryAnalyzer;

public class TC_02_001 extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class, description = "TC-02-001 Successful Signup")

	public void verifySuccessfulSignup() {

		LoginPage loginPage = new LoginPage(page);

		SignupPage signupPage = new SignupPage(page);

		String name = ConfigReader.getProperty("tc02001.name");
		System.out.println(name);

		String email = ConfigReader.getProperty("tc02001.email");

		String password = ConfigReader.getProperty("tc02001.password");

		String confirmPassword = ConfigReader.getProperty("tc02001.confirmPassword");

		String designation = ConfigReader.getProperty("tc02001.designation");

		String department = ConfigReader.getProperty("tc02001.department");

		String location = ConfigReader.getProperty("tc02001.location");

		loginPage.clickSignupLink();

		signupPage.signup(

				name, email, password, confirmPassword, designation, department, location

		);

		signupPage.clickSignUpButton();

		Assert.assertTrue(

				signupPage.isApprovalMessageDisplayed(),

				"Signup failed."

		);

	}
}