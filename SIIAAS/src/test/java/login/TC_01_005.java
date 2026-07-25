package login;
import org.testng.Assert;
import org.testng.annotations.Test;
import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;
import utils.RetryAnalyzer;
import utils.ScreenshotUtil;

public class TC_01_005 extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class,description = "TC-01-005 : Verify login with empty email field")
    public void verifyLoginWithEmptyEmailField() {

        /*
         * Page Object Creation
         */
        LoginPage loginPage = new LoginPage(page);

        LoggerUtil.info("========== STARTING TC-01-005 ==========");

        test.info("TC-01-005 Execution Started");

        /*
         * Leave Email Field Blank
         */
        LoggerUtil.info("Leaving Email Field Blank");

        test.info("Leaving Email Field Blank");

        ScreenshotUtil.captureAndAttachScreenshot(page,test,"Email_Field_Left_Blank");

        /*
         * Enter Password
         */
        LoggerUtil.info("Entering Password");

        test.info("Entering Password");

        loginPage.enterPassword(ConfigReader.getProperty("password"));

        ScreenshotUtil.captureAndAttachScreenshot(page,test,"Password_Entered");

        /*
         * Click Login Button
         */
        LoggerUtil.info("Clicking Login Button");

        test.info("Clicking Login Button");

        loginPage.clickLogin();

        ScreenshotUtil.captureAndAttachScreenshot(page,test,"Login_Button_Clicked");
        /*
         * Verify Validation Message
         */
        LoggerUtil.info("Verifying Email Required Validation Message");

        test.info("Verifying Email Required Validation Message");

        String actualMessage = loginPage.getEmailRequiredValidationMessage();

        ScreenshotUtil.captureAndAttachScreenshot(page,test,"Email_Required_Validation_Displayed");

        /*
         * Validation
         */
        Assert.assertEquals(actualMessage,"Email and password are required.","Incorrect validation message displayed");

        LoggerUtil.info("TC-01-005 PASSED");

        test.pass("Empty email field validation successful");
    }
}