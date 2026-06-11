package Pages;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.microsoft.playwright.Page;

import BaseClass.BaseTest;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class UserManagementPage extends BaseTest {

	private Page page;

	public UserManagementPage(Page page) {

		this.page = page;
	}

	// ============================================================
	// Locators
	// ============================================================

	private String userManagementMenu = "//span[normalize-space()='User Management']";

	private String userRequestsTab = "//button[contains(text(),'User Requests')]";

	private String allUsersTab = "//button[contains(.,'All Users')]";

	private String requestCountBadge = "//button[contains(.,'User Requests')]//span";

	private String assignPermissionPopup = "//h2[contains(text(),'Assign Permission')]";

	private String superUserCheckbox = "body > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)";

	private String assignSuperUserButton = "//button[contains(.,'Assign Super User Role')]";

	private String successToast = "//div[contains(text (), 'request approved')]"; //
	private String rejectToast = "//div[contains(text (), 'request rejected')]"; // div[@id='4']
	
	private String profileDropdown =
	        "//div[@class='flex flex-row justify-start items-center gap-3']//div[3]//*[name()='svg']";

	private String logoutOption =
	        "//div[normalize-space()='Logout']";

	private String logoutConfirmButton =
	        "(//button[normalize-space()='Logout'])[2]";

	private String loginEmailField =
	        "//input[@placeholder='Enter email id']";

	/*
	 * All Users Table
	 */

	private String userEmailRow = "//td[contains(text(),'%s')]";

	private String userNameCell = "//tr[td[contains(text(),'%s')]]";

	private String userRoleCell = "//tr[td[contains(text(),'%s')]]//td[contains(@class,'role')]";

	private String userDepartmentCell = "//tr[td[contains(text(),'%s')]]//td[contains(@class,'department')]";

	// ============================================================
	// Screenshot Helper
	// ============================================================

	public void attachStepScreenshot(String stepName) {

		String path = ScreenshotUtil.captureScreenshot(page, stepName);

		try {

			test.info(stepName, MediaEntityBuilder.createScreenCaptureFromPath(path).build());

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// ============================================================
	// Navigation Methods
	// ============================================================

	public void openUserManagement() {

		LoggerUtil.info("Opening User Management");

		page.click(userManagementMenu);

		page.waitForLoadState();

		attachStepScreenshot("User_Management_Opened");
	}

	public void openUserRequestsTab() {

		LoggerUtil.info("Opening User Requests Tab");

		page.click(userRequestsTab);

		page.waitForLoadState();

		attachStepScreenshot("User_Requests_Tab");
	}

	public void openAllUsersTab() {

		LoggerUtil.info("Opening All Users Tab");

		page.click(allUsersTab);

		page.waitForLoadState();

		attachStepScreenshot("All_Users_Tab");
	}

	// ============================================================
	// User Verification
	// ============================================================

	public boolean isUserPresent(String email) {

		LoggerUtil.info("Verifying User Request : " + email);

		return page.locator("//td[contains(text(),'" + email + "')]").isVisible();
	}

	public boolean isApproveButtonVisible(String email) {

		LoggerUtil.info("Verifying Approve Button");

		return page.locator("//tr[td[contains(text(),'" + email + "')]]//button[contains(text(),'Approve')]")
				.isVisible();
	}

	public boolean isRejectButtonVisible(String email) {

		LoggerUtil.info("Verifying Reject Button");

		return page.locator("//tr[td[contains(text(),'" + email + "')]]//button[contains(text(),'Reject')]")
				.isVisible();
	}

	public String getRequestCount() {

		LoggerUtil.info("Fetching User Request Count");

		return page.locator(requestCountBadge).textContent();
	}

	// ============================================================
	// Assign Permission
	// ============================================================

	public void clickAssign(String email) {

		LoggerUtil.info("Clicking Assign Button");

		page.locator("//tr[td[contains(text(),'" + email + "')]]//button[contains(.,'Assign')]").click();

		attachStepScreenshot("Assign_Popup_Opened");
	}

	public boolean isAssignPermissionPopupDisplayed() {

		LoggerUtil.info("Verifying Assign Permission Popup");

		return page.locator(assignPermissionPopup).isVisible();
	}

	public void selectSuperUserRole() {

		LoggerUtil.info("Selecting Super User Role");

		page.locator(superUserCheckbox).click();
		attachStepScreenshot("Super_User_Selected");
	}

	public void clickAssignSuperUserRole() {

		LoggerUtil.info("Assigning Super User Role");

		page.click(assignSuperUserButton);

		page.waitForLoadState();

		attachStepScreenshot("Super_User_Assigned");
	}

	public void assignSuperUserPermission() {

		selectSuperUserRole();

		clickAssignSuperUserRole();
	}

	// ============================================================
	// Approve Request
	// ============================================================

	public void clickApprove(String email) {

		LoggerUtil.info("Approving User Request");

		page.locator("//tr[td[contains(text(),'" + email + "')]]//button[contains(text(),'Approve')]").click();

		attachStepScreenshot("Approve_Clicked");
	}

	public boolean isApprovalToastDisplayed() {

		LoggerUtil.info("Verifying Approval Toast");

		return page.locator(successToast).isVisible();
	}

	// ============================================================
	// All Users Verification
	// ============================================================

	public boolean isUserPresentInAllUsers(String email) {

		LoggerUtil.info("Verifying User In All Users");

		return page.locator("//td[contains(text(),'" + email + "')]").isVisible();
	}

	// TC_02_005
	/*
	 * Click Reject Button
	 */
	public void clickReject(String email) {

		LoggerUtil.info("Rejecting User Request");

		page.locator("//tr[td[contains(text(),'" + email + "')]]//button[contains(text(),'Reject')]").click();

		attachStepScreenshot("Reject_Clicked");
	}

	/*
	 * Verify Reject Toast
	 */
	public boolean isRejectToastDisplayed() {

		LoggerUtil.info("Verifying Reject Toast");

		return page.locator(rejectToast).isVisible();
	}

	/*
	 * Verify User Removed From User Requests
	 */
	public boolean isUserNotPresent(String email) {

		return page.locator("//td[contains(text(),'" + email + "')]").count() == 0;
	}

	// TC_02_008
	/*
	 * Verify User Exists
	 */
	public boolean isUserPresentInAllUsers1(String email) {

		LoggerUtil.info("Verifying User In All Users : " + email);

		return page.locator(String.format(userEmailRow, email)).isVisible();
	}

	/*
	 * Verify Name
	 */
	public boolean isUserNameDisplayed(String name) {

		LoggerUtil.info("Verifying User Name : " + name);

		return page.locator("text=" + name).isVisible();
	}

	/*
	 * Verify Email
	 */
	public boolean isUserEmailDisplayed(String email) {

		LoggerUtil.info("Verifying User Email : " + email);

		return page.locator("text=" + email).isVisible();
	}

	public boolean isTextVisible(String value) {

		return page.locator("text=" + value).isVisible();
	}
	
	//TC_02_035
	public int getUserRequestsCount() {

	    LoggerUtil.info("Fetching User Requests Count");

	    String countText = page.locator(requestCountBadge).textContent().trim();

	    return Integer.parseInt(countText);
	}
	/*
	 * Logout From Application
	 */
	public void logout() {

	    LoggerUtil.info("Opening Profile Dropdown");

	    page.locator(profileDropdown).click();

	    attachStepScreenshot("Profile_Dropdown_Opened");

	    LoggerUtil.info("Clicking Logout");

	    page.locator(logoutOption).click();

	    attachStepScreenshot("Logout_Clicked");

	    LoggerUtil.info("Confirming Logout");

	    page.locator(logoutConfirmButton).click();

	    attachStepScreenshot("Logout_Confirmed");
	    
	}
	/*
	 * Verify User Redirected To Login Page
	 */
	public boolean isLogoutSuccessful() {

	    LoggerUtil.info("Verifying Login Page After Logout");

	    return page.locator(loginEmailField).isVisible();
	}
	/*
	 * Verify Table Column Header
	 */
	public boolean isColumnDisplayed(String columnName) {

	    LoggerUtil.info("Verifying Column : " + columnName);

	    return page.locator(
	            "//th[normalize-space()='" + columnName + "']"
	    ).isVisible();
	}
}