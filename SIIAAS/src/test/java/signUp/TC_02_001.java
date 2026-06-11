package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.DataProviders;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class TC_02_001 extends BaseTest {

    @Test(
        dataProvider = "Sheet1",
        dataProviderClass = DataProviders.class,
        description = "TC-02-001 : Successful signup with all valid inputs"
    )
    public void verifySuccessfulSignup(

            String name,
            String email,
            String password,
            String confirmPassword,
            String designation,
            String department,
            String location

    ) {

        LoginPage loginPage = new LoginPage(page);

        SignupPage signupPage = new SignupPage(page);

        // =====================================================
        // TC START
        // =====================================================

        LoggerUtil.info("========== STARTING TC-02-001 ==========");

        test.info(
                "TC-02-001 Execution Started | User : "
                        + name
        );

        // =====================================================
        // STEP 1
        // Open Signup Page
        // =====================================================

        LoggerUtil.info("Opening Signup Page");

        loginPage.clickSignupLink();

        page.waitForLoadState();

        page.waitForSelector(
                "//button[normalize-space() ='Sign Up']"
        );

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Signup_Page_Opened"
        );

        // =====================================================
        // STEP 2
        // Fill Signup Form
        // =====================================================

        LoggerUtil.info(
                "Entering Signup Details For : "
                        + name
        );

        signupPage.signup(
                name,
                email,
                password,
                confirmPassword,
                designation,
                department,
                location
        );

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Signup_Form_Submitted"
        );

        // =====================================================
        // STEP 3
        // Verify Signup Success
        // =====================================================

        LoggerUtil.info("Verifying Signup Success");

        Assert.assertTrue(
                signupPage.isApprovalMessageDisplayed(),
                "Signup failed for user : " + name
        );

        ScreenshotUtil.captureAndAttachScreenshot(
                page,
                test,
                "Signup_Successful"
        );

        LoggerUtil.info(
                "TC-02-001 PASSED FOR USER : "
                        + name
        );

        test.pass(
                "User successfully submitted signup request : "
                        + name
        );
    }
}