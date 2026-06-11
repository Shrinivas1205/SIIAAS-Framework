package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class TC_01_019 extends BaseTest {

    @Test(description = "TC-01-019 : Verify successful login redirects to Projects page")
    public void verifySuccessfulLoginRedirectsToProjectsPage() {

        // Page Object Creation
        LoginPage loginPage = new LoginPage(page);

        LoggerUtil.info("========== STARTING TC-01-019 ==========");
        test.info("TC-01-019 Execution Started");

        // Perform Login
        LoggerUtil.info("Entering Valid Approved Credentials");
        test.info("Entering Valid Approved Credentials");

        loginPage.login(

                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")

        );

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Valid_Credentials_Entered"
        );

        // Verify Projects Page Displayed
        LoggerUtil.info("Verifying Navigation To Projects Page");
        test.info("Verifying Navigation To Projects Page");

        boolean isProjectsDashboardDisplayed =
                loginPage.isProjectsDashboardDisplayed();

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Projects_Page_Displayed"
        );

        // Validation
        Assert.assertTrue(
                isProjectsDashboardDisplayed,
                "User is not redirected to Projects page after successful login"
        );

        LoggerUtil.info("TC-01-019 PASSED");
        test.pass("Successful login redirected user to Projects page");
    }
}