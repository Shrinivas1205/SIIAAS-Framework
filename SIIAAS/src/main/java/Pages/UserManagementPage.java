package Pages;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import BaseClass.BaseTest;
import utils.ConfigReader;
import utils.LocatorReader;
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

	private String userManagementMenu = ConfigReader.getLocator("usermanagement.menu");
	private String allUsersTab = ConfigReader.getLocator("usermanagement.allUsers.tab");
	private String userRequestsTab = ConfigReader.getLocator("usermanagement.userRequests.tab");
	private String userTable = ConfigReader.getLocator("usermanagement.table");
	private String addUserButton = ConfigReader.getLocator("usermanagement.addUser.button");
	private String searchBox = ConfigReader.getLocator("usermanagement.search");
	private String nextButton = ConfigReader.getLocator("usermanagement.next");
	private String previousButton = ConfigReader.getLocator("usermanagement.previous");
	private String requestCountBadge = ConfigReader.getLocator("usermanagement.request.count");
	private String assignPermissionPopup = ConfigReader.getLocator("usermanagement.assign.popup");
	private String superUserCheckbox = ConfigReader.getLocator("usermanagement.superuser.checkbox");
	private String assignSuperUserButton = ConfigReader.getLocator("usermanagement.assign.superuser.button");
	private String successToast = ConfigReader.getLocator("usermanagement.success.toast");
	private String rejectToast = ConfigReader.getLocator("usermanagement.reject.toast");
	private String profileDropdown = ConfigReader.getLocator("profile.dropdown");
	private String logoutOption = ConfigReader.getLocator("profile.logout");
	private String logoutConfirmButton = ConfigReader.getLocator("profile.logout.confirm");
	private String loginEmailField = ConfigReader.getLocator("login.email");
	private final String userHeader = ConfigReader.getLocator("usermanagement.header.user");
	private final String emailHeader = ConfigReader.getLocator("usermanagement.header.email");
	private final String roleHeader = ConfigReader.getLocator("usermanagement.header.role");
	private final String departmentHeader = ConfigReader.getLocator("usermanagement.header.department");
	private final String permissionHeader = ConfigReader.getLocator("usermanagement.header.permissions");
	private final String lastActiveHeader = ConfigReader.getLocator("usermanagement.header.lastactive");
	private final String joinedHeader = ConfigReader.getLocator("usermanagement.header.joined");
	private String firstRow = ConfigReader.getLocator("usermanagement.firstrow");
	private String firstRowAvatar = ConfigReader.getLocator("usermanagement.firstrow.avatar");
	private String firstRowUsername = ConfigReader.getLocator("usermanagement.firstrow.username");
	private String firstRowEmail = ConfigReader.getLocator("usermanagement.firstrow.email");
	private String firstRowRole = ConfigReader.getLocator("usermanagement.firstrow.role");
	private String firstRowDepartment = ConfigReader.getLocator("usermanagement.firstrow.department");
	private String firstRowPermission = ConfigReader.getLocator("usermanagement.firstrow.permission");
	private String firstRowLastActive = ConfigReader.getLocator("usermanagement.firstrow.lastactive");
	private String firstRowJoined = ConfigReader.getLocator("usermanagement.firstrow.joined");
	

	/*
	 * All Users Table
	 */

	private String userEmailRow = "//td[contains(text(),'%s')]";

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
	// UI Verification Methods
	// ============================================================

	/*
	 * Verify All Users Tab Displayed
	 */
	public boolean isAllUsersTabDisplayed() {

		LoggerUtil.info("Verifying All Users Tab");

		return page.locator(allUsersTab).isVisible();
	}

	/*
	 * Verify User Table Displayed
	 */
	public boolean isUserTableDisplayed() {

		LoggerUtil.info("Verifying User Table");

		return page.locator("//table").isVisible();
	}

	/*
	 * Verify Search Box Displayed
	 */
	public boolean isSearchBoxDisplayed() {

		LoggerUtil.info("Verifying Search Box");

		return page.locator("//input[contains(@placeholder,'Search users')]").isVisible();
	}

	/*
	 * Verify Add User Button Displayed
	 */
	public boolean isAddUserButtonDisplayed() {

		LoggerUtil.info("Verifying Add User Button");

		return page.locator("//button[contains(.,'Add User')]").isVisible();
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

		return page.locator("//tr[td[contains(text(),'" + email + "')]]//button[contains(text(),'Deny')]").isVisible();
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

	// TC_07_001
	
	/*
	 * Verify USER Header Displayed
	 */
	public boolean isUserHeaderDisplayed() {

		LoggerUtil.info("Verifying USER Header");

		return page.locator(userHeader).isVisible();
	}

	/*
	 * Verify EMAIL Header Displayed
	 */
	public boolean isEmailHeaderDisplayed() {

		LoggerUtil.info("Verifying EMAIL Header");

		return page.locator(emailHeader).isVisible();
	}

	/*
	 * Verify ROLE Header Displayed
	 */
	public boolean isRoleHeaderDisplayed() {

		LoggerUtil.info("Verifying ROLE Header");

		return page.locator(roleHeader).isVisible();
	}

	/*
	 * Verify DEPARTMENT Header Displayed
	 */
	public boolean isDepartmentHeaderDisplayed() {

		LoggerUtil.info("Verifying DEPARTMENT Header");

		return page.locator(departmentHeader).isVisible();
	}

	/*
	 * Verify PERMISSIONS Header Displayed
	 */
	public boolean isPermissionHeaderDisplayed() {

		LoggerUtil.info("Verifying PERMISSIONS Header");

		return page.locator(permissionHeader).isVisible();
	}

	/*
	 * Verify LAST ACTIVE Header Displayed
	 */
	public boolean isLastActiveHeaderDisplayed() {

		LoggerUtil.info("Verifying LAST ACTIVE Header");

		return page.locator(lastActiveHeader).isVisible();
	}

	/*
	 * Verify JOINED Header Displayed
	 */
	public boolean isJoinedHeaderDisplayed() {

		LoggerUtil.info("Verifying JOINED Header");

		return page.locator(joinedHeader).isVisible();
	}
	
	//TC_07_002
	
	/*
	 * Verify First User Row Exists
	 */
	public boolean isFirstUserDisplayed() {

		LoggerUtil.info("Verifying First User Row");

		return page.locator(firstRow).isVisible();
	}

	/*
	 * Verify Avatar
	 */
	public boolean isAvatarDisplayed() {

		LoggerUtil.info("Verifying Avatar");

		return page.locator(firstRowAvatar).isVisible();
	}

	/*
	 * Verify Username
	 */
	public boolean isUsernameDisplayed() {

		LoggerUtil.info("Verifying Username");

		return !page.locator(firstRowUsername).textContent().trim().isEmpty();
	}

	/*
	 * Verify Email
	 */
	public boolean isEmailDisplayed() {

		LoggerUtil.info("Verifying Email");

		return !page.locator(firstRowEmail).textContent().trim().isEmpty();
	}

	/*
	 * Verify Role
	 */
	public boolean isRoleDisplayed() {

		LoggerUtil.info("Verifying Role");

		String role = page.locator(firstRowRole).textContent().trim();

		return role.equalsIgnoreCase("ADMIN")
				|| role.equalsIgnoreCase("SUPER USER")
				|| role.equalsIgnoreCase("USER");
	}

	/*
	 * Verify Department
	 */
	public boolean isDepartmentDisplayed() {

		LoggerUtil.info("Verifying Department");

		return !page.locator(firstRowDepartment).textContent().trim().isEmpty();
	}

	/*
	 * Verify Permission
	 */
	public boolean isPermissionDisplayed() {

		LoggerUtil.info("Verifying Permission");

		return !page.locator(firstRowPermission).textContent().trim().isEmpty();
	}

	/*
	 * Verify Last Active
	 */
	public boolean isLastActiveDisplayed() {

		LoggerUtil.info("Verifying Last Active");

		return !page.locator(firstRowLastActive).textContent().trim().isEmpty();
	}

	/*
	 * Verify Joined Date
	 */
	public boolean isJoinedDateDisplayed() {

		LoggerUtil.info("Verifying Joined Date");

		return !page.locator(firstRowJoined).textContent().trim().isEmpty();
	}

	// TC_02_005
	/*
	 * Click Reject Button
	 */
	public void clickReject(String email) {

		LoggerUtil.info("Rejecting User Request");

		page.locator("//tr[td[contains(text(),'" + email + "')]]//button[contains(text(),'Deny')]").click();

		LoggerUtil.info("Clicked Deny on All users tab");

		page.locator("(//button[normalize-space()='Cancel']/following::button)[1]").click();

		LoggerUtil.info("Clicked Deny on pop up");

		attachStepScreenshot("Deny_Clicked");
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

	// TC_02_035
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

		return page.locator("//th[normalize-space()='" + columnName + "']").isVisible();
	}
}