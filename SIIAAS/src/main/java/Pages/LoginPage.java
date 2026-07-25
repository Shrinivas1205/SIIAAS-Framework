package Pages;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import BaseClass.BaseTest;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class LoginPage extends BaseTest {

	private Page page;

	public LoginPage(Page page) {

		this.page = page;
	}

	private String projectsTab = "//h2[normalize-space()= 'All Projects']";

	// TC-01-001

	/*
	 * Locators
	 */
	public String emailField = "input[type='email']";

	public String passwordField = "//input[@placeholder='Enter Password']";

	public String loginButton = "button:has-text('Log In')";

	/*
	 * Enter Email
	 */
	public void enterEmail(String email) {
		// Enter Valid Password
		LoggerUtil.info("Entering Valid Email");
		page.fill(emailField, email);
		attachStepScreenshot("Entered_Email");
	}

	/*
	 * Enter Password
	 */
	public void enterPassword(String password) {

		// Enter valid password
		LoggerUtil.info("Entering Valid Password");
		page.fill(passwordField, password);
		attachStepScreenshot("Entered_Password");
	}

	/*
	 * Click Login
	 */
	public void clickLogin() {

		// clicking login button
		LoggerUtil.info("Clicking Login Button");
		page.click(loginButton);
		attachStepScreenshot("Clicked Login");

	}

	/*
	 * Login Method
	 */
	public void login(String email, String password) {

		enterEmail(email);
		enterPassword(password);
		clickLogin();
	}

	public void EnterkeyPress() {
		// Press Enter Key
		LoggerUtil.info("Pressing Enter Key");
		page.keyboard().press("Enter");
		attachStepScreenshot("Pressed Enter Key");
		test.info("Pressed Enter Key");

	}

	/*
	 * Reusable Screenshot Attachment
	 */
	public void attachStepScreenshot(String stepName) {

		String path = ScreenshotUtil.captureScreenshot(page, stepName);

		// Screenshot couldn't be captured
		if (path == null || path.isEmpty()) {

			LoggerUtil.warn("Skipping screenshot attachment : " + stepName);

			if (test != null) {
				test.warning("Screenshot not available : " + stepName);
			}

			return;
		}

		try {

			test.info(stepName, MediaEntityBuilder.createScreenCaptureFromPath(path).build());

		} catch (Exception e) {

			LoggerUtil.error("Unable to attach screenshot : " + e.getMessage());
		}
	}

	// TC-01-002
	/*
	 * Method: Get Login Error Message
	 */
	public String getLoginErrorMessage() {

		LoggerUtil.info("Fetching Login Error Message");

		return page.locator("text='Invalid password.'").textContent();
	}

	// TC_01_003

	public String getLoginwrongemailErrorMessage() {

		LoggerUtil.info("Fetching Login Error Message");

		return page.locator("text='No account found with this email.'").textContent();
	}

	// TC_01_004
	public String getLoginwithunapprovedemailErrorMessage() {

		LoggerUtil.info("Fetching Login Error Message");

		return page.locator("text='Your permission request is still pending admin approval.'").textContent();
	}

	// TC_01_005 & TC_01_007
	public String getEmailRequiredValidationMessage() {

		LoggerUtil.info("Fetching Login Error Message");

		return page.locator("text='Email and password are required.'").textContent().trim();

	}

	// TC_01_006 & TC_01_007
	public String getPasswordRequiredValidationMessage() {

		LoggerUtil.info("Fetching Login Error Message");

		return page.locator("text='Email and password are required.'").textContent().trim();

	}

	// TC_01_008
	public String getInvalidEmailValidationMessage() {
		return page.locator("text='No account found with this email.'").textContent().trim();
	}

	// TC_01_009
	public boolean isDashboardDisplayed() {
		return page.getByRole(

				com.microsoft.playwright.options.AriaRole.HEADING,

				new Page.GetByRoleOptions().setName("All Projects")

		).isVisible();
	}

	// TC_01_010

	public String getEmailLengthValidationMessage() {
		return page.locator("text='No account found with this email.'").textContent().trim();
	}

	// TC_01_011 same validation for email and password like above

	// TC_01_012

	// Password Field Locator
	public String passwordField1 = "input[type='password']";

	// Verify Password Field Is Masked
	public boolean isPasswordMasked() {

		try {

			// Get field type attribute
			String fieldType = page.locator(passwordField1).getAttribute("value");

			LoggerUtil.info("Password Field Type : " + fieldType);

			// Validate password masking
			return fieldType != null && fieldType.equalsIgnoreCase("admin");

		} catch (Exception e) {

			LoggerUtil.error("Password field masking validation failed : " + e.getMessage());

			return false;
		}
	}

	// TC_01_013
	public void clickPasswordVisibilityIcon() {
		page.locator(
				"path[d='M8 10.25C9.24264 10.25 10.25 9.24264 10.25 8C10.25 6.75736 9.24264 5.75 8 5.75C6.75736 5.75 5.75 6.75736 5.75 8C5.75 9.24264 6.75736 10.25 8 10.25Z']")
				.click();
	}

	public String getPasswordFieldType() {
		return page.locator(passwordField).getAttribute("value");
	}

	// TC_01_014
	public boolean isLoginButtonVisible() {
		return page.locator(loginButton).isVisible();
	}

	public boolean isLoginButtonEnabled() {
		return page.locator(loginButton).isEnabled();
	}
	// TC_01_015

	public boolean isProjectsDashboardDisplayed() {

		try {

			// Replace with your actual dashboard locator
			Locator dashboard = page.locator(projectsTab);

			dashboard.waitFor();

			LoggerUtil.info("Projects Dashboard Displayed");

			return dashboard.isVisible();

		} catch (Exception e) {

			LoggerUtil.error("Projects Dashboard NOT displayed : " + e.getMessage());

			return false;
		}
	}

	// TC_01_017
	public boolean isForgotPasswordLinkVisible() {
		return page.locator("text='Forgot Password?'").isVisible();
	}

	public void clickForgotPasswordLink() {
		page.locator("text='Forgot Password?'").click();
	}

	public boolean isResetPasswordPageDisplayed() {
		return page.locator("text='Set a new password'").isVisible();
	}

	// TC_01_018

	public boolean isSignupLinkVisible() {
		return page.locator("text='Signup'").isVisible();
	}

	public void clickSignupLink() {
		LoggerUtil.info("Clicking sign up");
		page.locator(ConfigReader.getLocator("login.signup.button")).click();
		attachStepScreenshot("Clicked sign up");
	}

	public boolean isSignupPageDisplayed() {
		return page.locator("text='Designation'").isVisible();
	}

	// TC_01_021

	public boolean isWelcomeTitleVisible() {
		return page.locator("text='Welcome to SIIAAS'").isVisible();
	}

	public boolean isEmailFieldVisible() {
		return page.locator(emailField).isVisible();
	}

	public boolean isPasswordFieldVisible() {
		return page.locator(passwordField).isVisible();
	}

	public boolean isPasswordEyeIconVisible() {
		return page.locator(
				"path[d='M8 10.25C9.24264 10.25 10.25 9.24264 10.25 8C10.25 6.75736 9.24264 5.75 8 5.75C6.75736 5.75 5.75 6.75736 5.75 8C5.75 9.24264 6.75736 10.25 8 10.25Z']")
				.isVisible();
	}

	public boolean isSignupSectionVisible() {
		return page.locator("text='Don't Have an account?'").isVisible();
	}

	public boolean isLogoVisible() {
		return page.locator("img").first().isVisible();
	}

	public String getUnapprovedUserLoginErrorMessage() {

		LoggerUtil.info("Fetching Login Error Message");

		return page.locator("text='Your permission request is still pending admin approval.'").textContent();

	}
}
