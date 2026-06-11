package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class TC_01_018 extends BaseTest {

    @Test(description = "TC-01-018 : Verify 'Signup' link is present and navigates correctly")
    public void verifySignupLinkNavigation() {

        // Page Object Creation
        LoginPage loginPage = new LoginPage(page);

        LoggerUtil.info("========== STARTING TC-01-018 ==========");
        test.info("TC-01-018 Execution Started");

        // Verify Signup Link Visibility
        LoggerUtil.info("Verifying Signup Link Visibility");
        test.info("Verifying Signup Link Visibility");

        boolean isSignupLinkVisible =
                loginPage.isSignupLinkVisible();

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Signup_Link_Visible"
        );

        Assert.assertTrue(
                isSignupLinkVisible,
                "'Signup' link is not visible"
        );

        // Click Signup Link
        LoggerUtil.info("Clicking Signup Link");
        test.info("Clicking Signup Link");

        loginPage.clickSignupLink();

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Signup_Link_Clicked"
        );

        // Verify Navigation To Signup Page
        LoggerUtil.info("Verifying Navigation To Signup Page");
        test.info("Verifying Navigation To Signup Page");

        boolean isSignupPageDisplayed =
                loginPage.isSignupPageDisplayed();

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Signup_Page_Displayed"
        );

        // Validation
        Assert.assertTrue(
                isSignupPageDisplayed,
                "User is not redirected to Signup page"
        );

        LoggerUtil.info("TC-01-018 PASSED");
        test.pass("Signup link navigation verified successfully");
    }
}