package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class TC_01_011 extends BaseTest {

    @Test(description = "TC-01-011 : Verify login with very long password input")
    public void verifyLoginWithVeryLongPasswordInput() {

        // Page Object Creation
        LoginPage loginPage = new LoginPage(page);

        LoggerUtil.info("========== STARTING TC-01-011 ==========");
        test.info("TC-01-011 Execution Started");

        // Enter Email
        LoggerUtil.info("Entering Email");
        test.info("Entering Email");

        loginPage.enterEmail(
                ConfigReader.getProperty("longpassworduseremail")
        );

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Email_Entered"
        );

        // Enter Very Long Password
        LoggerUtil.info("Entering Very Long Password");
        test.info("Entering Very Long Password");

        loginPage.enterPassword(
                ConfigReader.getProperty("longpassword")
        );

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Very_Long_Password_Entered"
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
        LoggerUtil.info("Verifying Password Length Validation");
        test.info("Verifying Password Length Validation");

        String actualMessage =
                loginPage.getEmailLengthValidationMessage();

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Password_Length_Validation_Displayed"
        );

        // Validation
        Assert.assertTrue(
                actualMessage.contains("maximum length")
                || actualMessage.contains("too long")
                || actualMessage.contains("invalid password"),
                "Incorrect validation message displayed"
        );

        LoggerUtil.info("TC-01-011 PASSED");
        test.pass("Very long password input validation successful");
    }
}