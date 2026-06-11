package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.UserManagementPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_036 extends BaseTest {

    @Test(description = "TC-02-036 Verify User Requests Table Shows Correct Columns")
    public void verifyUserRequestsTableColumns() {

        LoginPage loginPage = new LoginPage(page);
        UserManagementPage userManagementPage = new UserManagementPage(page);

        LoggerUtil.info("Starting TC-02-036");

        // Login as Admin
        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password"));

        // Navigate to User Requests
        userManagementPage.openUserManagement();
        userManagementPage.openUserRequestsTab();

        userManagementPage.attachStepScreenshot(
                "User_Requests_Table");

        // Verify Columns

        Assert.assertTrue(
                userManagementPage.isColumnDisplayed("Name"),
                "Name column not displayed");

        Assert.assertTrue(
                userManagementPage.isColumnDisplayed("Email"),
                "Email column not displayed");

        Assert.assertTrue(
                userManagementPage.isColumnDisplayed("Phone"),
                "Phone column not displayed");

        Assert.assertTrue(
                userManagementPage.isColumnDisplayed("Request Date"),
                "Request Date column not displayed");

        Assert.assertTrue(
                userManagementPage.isColumnDisplayed("Permissions"),
                "Permissions column not displayed");

        Assert.assertTrue(
                userManagementPage.isColumnDisplayed("Access Level"),
                "Access Level column not displayed");

        Assert.assertTrue(
                userManagementPage.isColumnDisplayed("Actions"),
                "Actions column not displayed");

        test.pass("All expected User Requests table columns are displayed");

        LoggerUtil.info("TC-02-036 PASSED");
    }
}