package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import utils.LoggerUtil;

public class SignupPage {

	private Page page;

	public SignupPage(Page page) {
		this.page = page;
	}

	/*
	 * Locators
	 */
	public String fullNameField = "//input[@placeholder='Enter your name']";

	public String emailField = "input[type='email']";

	public String passwordField = "//input[@placeholder='Enter your Password']";

	public String confirmPasswordField = "//input[@placeholder='Confirm your password']";

	public String designationDropdown = "//input[@placeholder='Enter your designation']";

	public String departmentDropdown = "//input[@placeholder='Enter your department']";

	public String locationDropdown = "//input[@placeholder='Enter your Location']";

	public String signUpButton = "//button[normalize-space()='Sign Up']";

	private String passwordEyeIcon = "(//*[local-name()='svg'])[1]";

	private String nameValidationMessage = "//input[@placeholder='Enter your name']/following::p[1]";

	private String emailRequiredValidation = "//input[@placeholder='Enter your name']/following::p[1]";

	private String passwordRequiredValidation = "//input[@placeholder='Enter your name']/following::p[1]";
	// TC-02-027

	public String nameLabel = "//label[contains(.,'Name')]/span";

	public String emailLabel = "//label[contains(.,'Email')]/span";

	public String passwordLabel = "//label[contains(.,'Password')]/span";

	public String confirmPasswordLabel = "//label[contains(.,'Confirm Password')]/span";

	public String designationLabel = "//label[contains(.,'Designation')]/span";

	public String departmentLabel = "//label[contains(.,'Department')]/span";

	public String locationLabel = "//label[contains(.,'Location')]/span";

	private String loginLink = "//a[text()='Login']";

	private String profileDropdown = "//div[@class='flex flex-row justify-start items-center gap-3']//div[3]//*[name()='svg']";

	private String logoutOption = "//div[normalize-space()='Logout']";

	// Update based on actual popup HTML
	private String logoutPopup = "//div[contains(text(),'Are you sure')]";

	private String confirmLogoutButton = "(//button[normalize-space()='Logout'])[2]";

	LoginPage loginPage = new LoginPage(page);

	/*
	 * Click Signup Button
	 */
	public void clickSignUpButton() {

		page.click(signUpButton);
	}

	/*
	 * Verify Signup Success
	 *
	 * 
	 * /* Complete Signup Flow
	 */

	public void enterFullName(String name) {
		page.fill(fullNameField, name);
	}

	public void enterEmail(String email) {
		page.fill(emailField, email);
	}

	public void enterPassword(String password) {
		page.fill(passwordField, password);
	}

	public void enterConfirmPassword(String confirmPassword) {
		page.fill(confirmPasswordField, confirmPassword);
	}

	public void selectDesignation(String designation) {
		page.fill(designationDropdown, designation);
	}

	public void selectDepartment(String department) {
		page.fill(departmentDropdown, department);
	}

	public void selectLocation(String location) {
		page.fill(locationDropdown, location);
	}

	public void signup(String fullName, String email, String password, String confirmPassword, String designation,
			String department, String location) {

		enterFullName(fullName);

		enterEmail(email);

		enterPassword(password);

		enterConfirmPassword(confirmPassword);

		selectDesignation(designation);

		selectDepartment(department);

		selectLocation(location);
	}

	/*
	 * Verify Signup Success
	 *
	 * After signup application redirects to Login page
	 */
	public boolean isApprovalMessageDisplayed() {
		try {

			// Replace with your actual dashboard locator
			Locator eemail = page.locator("//input[@placeholder ='Enter email id']");

			eemail.waitFor();

			LoggerUtil.info("User completed Sign up");

			return eemail.isVisible();

		} catch (Exception e) {

			LoggerUtil.error("User not completed Sign up " + e.getMessage());

			return false;
		}
	}

	public boolean issignUpsuccessful() {

		return page.locator("//input[@placeholder ='Enter email id']").isVisible();
	}

	/*
	 * TC-02-007 Rejected User Error Message not in use this method
	 */
	public String getRejectedUserErrorMessage() {

		LoggerUtil.info("Fetching Rejected User Error Message");

		return page.locator("//div[contains(@class,'text-red')]").textContent().trim();
	}

	// common method TC_02_009, TC_02_010, TC_02_011, TC_02_012, TC_02_013,
	public String getValidationMessage(String message) {

		LoggerUtil.info("Fetching Validation Message : " + message);

		return page.locator("(//p[contains(normalize-space(),'" + message + "')])[1]").textContent().trim();
	}

	// TC_02_020
	public String getEmailValue() {

		LoggerUtil.info("Fetching Email Field Value");

		return page.locator(emailField).inputValue();
	}

	/*
	 * TC-02-024 Verify Password Field Is Masked
	 */
	public boolean isPasswordMasked() {

		LoggerUtil.info("Verifying Password Field Is Masked");

		String fieldType = page.locator(passwordField).getAttribute("type");

		LoggerUtil.info("Password Field Type : " + fieldType);

		return fieldType != null && fieldType.equals("password");
	}

	/*
	 * TC-02-025 Verify Confirm Password Field Is Masked
	 */
	public boolean isConfirmPasswordMasked() {

		try {

			String fieldType = page.locator(confirmPasswordField).getAttribute("type");

			LoggerUtil.info("Confirm Password Field Type : " + fieldType);

			return fieldType != null && fieldType.equalsIgnoreCase("password");

		} catch (Exception e) {

			LoggerUtil.error("Confirm Password masking validation failed : " + e.getMessage());

			return false;
		}
	}

	// TC_02_026
	/*
	 * Click Password Eye Icon
	 */
	public void clickPasswordEyeIcon() {

		LoggerUtil.info("Clicking Password Visibility Icon");

		page.locator(passwordEyeIcon).click();

		loginPage.attachStepScreenshot("Password_Visibility_Toggled");
	}

	/*
	 * Get Password Field Type password -> masked text -> visible
	 */
	public String getPasswordFieldType() {

		return page.locator(passwordField).getAttribute("type");
	}

	// tc 27
	/*
	 * Verify Required Field Asterisk
	 */
	public boolean isLabelMarkedRequired(String fieldName) {

		LoggerUtil.info("Verifying required asterisk for field : " + fieldName);

		return page.locator("//label[normalize-space(text())='" + fieldName + "']//span[text()='*']").count() > 0;
	}
	// TC-02-028

	/*
	 * Verify Sign Up Button Visible
	 */
	public boolean isSignUpButtonVisible() {

		LoggerUtil.info("Checking Sign Up Button Visibility");

		return page.locator(signUpButton).isVisible();
	}

	/*
	 * Verify Sign Up Button Enabled
	 */
	public boolean isSignUpButtonEnabled() {

		LoggerUtil.info("Checking Sign Up Button Enabled State");

		return page.locator(signUpButton).isEnabled();
	}

	/*
	 * Click Sign Up Button
	 */
	public void clickSignUp() {

		LoggerUtil.info("Clicking Sign Up Button");

		page.locator(signUpButton).click();

		loginPage.attachStepScreenshot("Clicked_SignUp_Button");
	}

	/*
	 * TC-02-029 Verify Login Link Visibility
	 */
	public boolean isLoginLinkVisible() {

		LoggerUtil.info("Checking Login Link Visibility");

		return page.locator(loginLink).isVisible();
	}

	/*
	 * Click Login Link
	 */
	public void clickLoginLink() {

		LoggerUtil.info("Clicking Login Link");

		page.locator(loginLink).click();

		loginPage.attachStepScreenshot("Clicked_Login_Link");
	}

	/*
	 * Verify Login Page Displayed
	 */
	public boolean isLoginPageDisplayed() {

		LoggerUtil.info("Verifying Login Page");

		return page.locator("//input[@placeholder='Enter email id']").isVisible();
	}

	// tc 34
	/*
	 * Returns currently focused element
	 */
	public String getFocusedElementAttribute(String attributeName) {

		Object value = page.evaluate("(attr) => document.activeElement.getAttribute(attr)", attributeName);

		return value == null ? "" : value.toString();
	}

	public String getFocusedElementText() {

		return page.evaluate("() => document.activeElement.textContent").toString();
	}
	// tc 35

	public void clickLogout() {

		LoggerUtil.info("Opening Profile Dropdown");

		page.locator(profileDropdown).click();

		LoggerUtil.info("Clicking Logout Menu");

		page.locator(logoutOption).click();
	}

	public boolean isLogoutPopupDisplayed() {

		LoggerUtil.info("Verifying Logout Confirmation Popup");

		return page.locator(logoutPopup).isVisible();
	}

	public void confirmLogout() {

		LoggerUtil.info("Confirming Logout");

		page.locator(confirmLogoutButton).click();
	}

	public void logoutFromApplication() {

		clickLogout();

		if (isLogoutPopupDisplayed()) {

			confirmLogout();
		}
	}

	// tc 38
	/*
	 * TC-02-038 Verify Signup Page Loaded
	 */
	public boolean isSignupPageDisplayed() {

		LoggerUtil.info("Verifying Signup Page Loaded");

		return page.locator(fullNameField).isVisible() && page.locator(emailField).isVisible()
				&& page.locator(passwordField).isVisible() && page.locator(confirmPasswordField).isVisible()
				&& page.locator(designationDropdown).isVisible() && page.locator(departmentDropdown).isVisible()
				&& page.locator(locationDropdown).isVisible() && page.locator(signUpButton).isVisible();
	}
	// TC-02-039

	public String getNameValue() {
	    return page.locator(fullNameField).inputValue();
	}

	public String getPasswordValue() {
	    return page.locator(passwordField).inputValue();
	}

	public String getConfirmPasswordValue() {
	    return page.locator(confirmPasswordField).inputValue();
	}

	public String getDesignationValue() {
	    return page.locator(designationDropdown).inputValue();
	}

	public String getDepartmentValue() {
	    return page.locator(departmentDropdown).inputValue();
	}

	public String getLocationValue() {
	    return page.locator(locationDropdown).inputValue();
	}
}