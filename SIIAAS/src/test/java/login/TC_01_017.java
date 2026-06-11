package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class TC_01_017 extends BaseTest {

    @Test(description = "TC-01-017 : Verify 'Forgot Password?' link is present and navigates correctly")
    public void verifyForgotPasswordLinkNavigation() {

        // Page Object Creation
        LoginPage loginPage = new LoginPage(page);

        LoggerUtil.info("========== STARTING TC-01-017 ==========");
        test.info("TC-01-017 Execution Started");

        // Verify Forgot Password Link Visibility
        LoggerUtil.info("Verifying Forgot Password Link Visibility");
        test.info("Verifying Forgot Password Link Visibility");

        boolean isForgotPasswordLinkVisible =
                loginPage.isForgotPasswordLinkVisible();

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Forgot_Password_Link_Visible"
        );

        Assert.assertTrue(
                isForgotPasswordLinkVisible,
                "'Forgot Password?' link is not visible"
        );

        // Click Forgot Password Link
        LoggerUtil.info("Clicking Forgot Password Link");
        test.info("Clicking Forgot Password Link");

        loginPage.clickForgotPasswordLink();

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Forgot_Password_Link_Clicked"
        );

        // Verify Navigation To Reset Password Page
        LoggerUtil.info("Verifying Navigation To Reset Password Page");
        test.info("Verifying Navigation To Reset Password Page");

        boolean isResetPasswordPageDisplayed =
                loginPage.isResetPasswordPageDisplayed();

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Reset_Password_Page_Displayed"
        );

        // Validation
        Assert.assertTrue(
                isResetPasswordPageDisplayed,
                "User is not redirected to Reset Password page"
        );

        LoggerUtil.info("TC-01-017 PASSED");
        test.pass("Forgot Password link navigation verified successfully");
    }
}