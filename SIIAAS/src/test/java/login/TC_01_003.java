package login;

import org.testng.Assert;
import org.testng.annotations.Test;
import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

/*
 * TC-01-003
 * 
 * Test Scenario:
 * Login with unregistered email
 */

public class TC_01_003 extends BaseTest {

    @Test(description = " TC-01-003 :Verify login with unregistered email")
    public void verifyUnregisteredEmailLogin() {

        LoggerUtil.info("========== STARTING TC-01-003 ==========");

        /*
         * Create Login Page object
         */
        LoginPage loginPage = new LoginPage(page);

        /*
         * Enter Unregistered Email
         */
        LoggerUtil.info("Entering Unregistered Email");

        test.info("Entering Unregistered Email");

        loginPage.enterEmail("notexist@example.com");

        ScreenshotUtil.captureAndAttachScreenshot( page, test, "Unregistered_Email_Entered");

        /*
         * Enter Password
         */
        LoggerUtil.info("Entering Password");

        test.info("Entering Password");

        loginPage.enterPassword("Pass@123");

        ScreenshotUtil.captureAndAttachScreenshot(

                page, test, "Password_Entered"
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
         * Wait for error message
         */
        page.waitForTimeout(3000);

        /*
         * Get Error Message
         */
        LoggerUtil.info("Verifying Error Message");

        test.info("Verifying Error Message");

        String actualError =
                loginPage.getLoginwrongemailErrorMessage(); 

        System.out.println("Actual Error Message: " + actualError);

        ScreenshotUtil.captureAndAttachScreenshot(

                page,

                test,

                "Error_Message_Displayed"

        );

        /*
         * Validate Error Message
         */
        Assert.assertTrue(

                actualError.contains("No account found with this email.") || actualError.contains("not found"), "Expected error message NOT displayed" );

        LoggerUtil.info("========== TC-01-003 PASSED ==========");

        test.pass("Proper error message displayed for unregistered email");
    }
}