package login;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class TC_01_004 extends BaseTest {

    @Test(description = "TC-01-004 :Verify login attempt by non-approved user")
    public void verifyNonApprovedUserLogin() {

        /*
         * Page Object Creation
         */
        LoginPage loginPage = new LoginPage(page);

        LoggerUtil.info("========== STARTING TC-01-004 ==========");

        test.info("TC-01-004 Execution Started");

        /*
         * Enter Non-Approved User Email
         */
        LoggerUtil.info("Entering Non-Approved User Email");

        test.info("Entering Non-Approved User Email");

        loginPage.enterEmail(ConfigReader.getProperty("unapproveduseremail"));

        ScreenshotUtil.captureAndAttachScreenshot(

                page,
                test,
                "Non_Approved_Email_Entered"

        );

        /*
         * Enter Correct Password
         */
        LoggerUtil.info("Entering Correct Password");

        test.info("Entering Correct Password");

        loginPage.enterPassword(ConfigReader.getProperty("unapproveduserpassword"));

        ScreenshotUtil.captureAndAttachScreenshot(

                page,
                test,
                "Correct_Password_Entered"

        );

        /*
         * Click Login Button
         */
        LoggerUtil.info("Clicking Login Button");

        test.info("Clicking Login Button");

        loginPage.clickLogin();

        ScreenshotUtil.captureAndAttachScreenshot(

                page,
                test,
                "Login_Button_Clicked"

        );

        /*
         * Verify Error Message
         */
        LoggerUtil.info("Verifying Account Approval Error Message");

        test.info("Verifying Account Approval Error Message");

        String actualMessage = loginPage.getLoginwithunapprovedemailErrorMessage();

        ScreenshotUtil.captureAndAttachScreenshot(

                page,
                test,
                "Approval_Error_Message_Displayed"

        );

        /*
         * Validation
         */
        Assert.assertTrue(

                actualMessage.contains("Your permission request is still pending admin approval.")
                ||
                actualMessage.contains("Invalid email or password"),

                "Incorrect error message displayed"

        );

        LoggerUtil.info("TC-01-004 PASSED");

        test.pass("Non-approved user login validation successful");
    }
}