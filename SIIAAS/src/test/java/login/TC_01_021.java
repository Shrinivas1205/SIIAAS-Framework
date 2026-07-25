package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.LoggerUtil;
import utils.RetryAnalyzer;
import utils.ScreenshotUtil;

public class TC_01_021 extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class,description = "TC-01-021 : Verify login page UI elements are displayed correctly")
    public void verifyLoginPageUIElements() {

        // Page Object Creation
        LoginPage loginPage = new LoginPage(page);

        LoggerUtil.info("========== STARTING TC-01-021 ==========");
        test.info("TC-01-021 Execution Started");

        // Verify Welcome Title
        LoggerUtil.info("Verifying Welcome Title");
        test.info("Verifying Welcome Title");

        Assert.assertTrue(
                loginPage.isWelcomeTitleVisible(),
                "'Welcome to SIIAAS' title is not visible"
        );

        // Verify Email Field
        LoggerUtil.info("Verifying Email Field");
        test.info("Verifying Email Field");

        Assert.assertTrue(
                loginPage.isEmailFieldVisible(),
                "Email field is not visible"
        );

        // Verify Password Field
        LoggerUtil.info("Verifying Password Field");
        test.info("Verifying Password Field");

        Assert.assertTrue(
                loginPage.isPasswordFieldVisible(),
                "Password field is not visible"
        );

        // Verify Password Eye Icon
        LoggerUtil.info("Verifying Password Eye Icon");
        test.info("Verifying Password Eye Icon");

        Assert.assertTrue(
                loginPage.isPasswordEyeIconVisible(),
                "Password eye icon is not visible"
        );

        // Verify Forgot Password Link
        LoggerUtil.info("Verifying Forgot Password Link");
        test.info("Verifying Forgot Password Link");

        Assert.assertTrue(
                loginPage.isForgotPasswordLinkVisible(),
                "'Forgot Password?' link is not visible"
        );

        // Verify Login Button
        LoggerUtil.info("Verifying Login Button");
        test.info("Verifying Login Button");

        Assert.assertTrue(
                loginPage.isLoginButtonVisible(),
                "'Log In' button is not visible"
        );

        // Verify Signup Section
        LoggerUtil.info("Verifying Signup Section");
        test.info("Verifying Signup Section");

        Assert.assertTrue(
                loginPage.isSignupSectionVisible(),
                "'Don't Have an account? Signup' section is not visible"
        );

        // Verify Logo
        LoggerUtil.info("Verifying Application Logo");
        test.info("Verifying Application Logo");

        Assert.assertTrue(
                loginPage.isLogoVisible(),
                "Application logo is not visible"
        );

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Login_Page_UI_Elements"
        );

        LoggerUtil.info("TC-01-021 PASSED");
        test.pass("All login page UI elements verified successfully");
    }
}