package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class TC_01_010 extends BaseTest {

    @Test(description = "TC-01-010 : Verify login with very long email input")
    public void verifyLoginWithVeryLongEmailInput() {

        // Page Object Creation
        LoginPage loginPage = new LoginPage(page);

        LoggerUtil.info("========== STARTING TC-01-010 ==========");
        test.info("TC-01-010 Execution Started");

        // Generate Very Long Email (>254 Characters)
        String longEmail = "a".repeat(250) + "@example.com";

        // Enter Very Long Email
        LoggerUtil.info("Entering Very Long Email");
        test.info("Entering Very Long Email");

        loginPage.enterEmail(longEmail);

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Very_Long_Email_Entered"
        );

        // Enter Password
        LoggerUtil.info("Entering Password");
        test.info("Entering Password");

        loginPage.enterPassword("Pass@123");

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
        LoggerUtil.info("Verifying Email Length Validation");
        test.info("Verifying Email Length Validation");

        String actualMessage =
                loginPage.getEmailLengthValidationMessage();

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Email_Length_Validation_Displayed"
        );

        // Validation
        Assert.assertTrue(
                actualMessage.contains("No account found with this email.")
                || actualMessage.contains("maximum length")
                || actualMessage.contains("too long"),
                "Incorrect validation message displayed"
        );

        LoggerUtil.info("TC-01-010 PASSED");
        test.pass("Very long email input validation successful"); 
    }
}