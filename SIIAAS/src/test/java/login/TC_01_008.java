package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;
import utils.ScreenshotUtil;

public class TC_01_008 extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class,description = "TC-01-008 : Verify login with invalid email format")
    public void verifyLoginWithInvalidEmailFormat() {

        // Page Object Creation
        LoginPage loginPage = new LoginPage(page);

        LoggerUtil.info("========== STARTING TC-01-008 ==========");
        test.info("TC-01-008 Execution Started");

        // Enter Invalid Email
        LoggerUtil.info("Entering Invalid Email Format");
        test.info("Entering Invalid Email Format");

        loginPage.enterEmail(ConfigReader.getProperty("invaildemail"));

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Invalid_Email_Entered"
        );

        // Enter Password
        LoggerUtil.info("Entering Password");
        test.info("Entering Password");

        loginPage.enterPassword(ConfigReader.getProperty("password"));

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Password_Entered"
        );

        // Click Login Button
        LoggerUtil.info("Clicking Login Button");
        test.info("Clicking Login Button");

        loginPage.clickLogin();

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Login_Button_Clicked"
        );

        // Verify Validation Message
        LoggerUtil.info("Verifying Invalid Email Validation Message");
        test.info("Verifying Invalid Email Validation Message");

        String actualMessage =
                loginPage.getInvalidEmailValidationMessage();

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Invalid_Email_Validation_Displayed"
        );

        // Validation
        Assert.assertEquals(
                actualMessage,
                "No account found with this email.",
                "Incorrect validation message displayed"
        );

        LoggerUtil.info("TC-01-008 PASSED");
        test.pass("Invalid email format validation successful");
    }
}