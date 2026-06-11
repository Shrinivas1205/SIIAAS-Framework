package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_013 extends BaseTest {

    @Test(
        description = "TC-02-013 Verify signup with empty Designation field"
    )
    public void verifySignupWithEmptyDesignationField() {

        LoginPage loginPage = new LoginPage(page);
        SignupPage signupPage = new SignupPage(page);

        LoggerUtil.info("========== STARTING TC-02-013 ==========");

        /*
         * STEP 1 : Open Signup Page
         */
        LoggerUtil.info("Opening Signup Page");

        loginPage.clickSignupLink();

        test.info("Signup Page Opened");

        /*
         * STEP 2 : Enter Signup Details
         */
        LoggerUtil.info("Entering Signup Details");

        signupPage.enterFullName(
                ConfigReader.getProperty("tc02013.name"));

        signupPage.enterEmail(
                ConfigReader.getProperty("tc02013.email"));

        signupPage.enterPassword(
                ConfigReader.getProperty("tc02013.password"));

        signupPage.enterConfirmPassword(
                ConfigReader.getProperty("tc02013.confirmPassword"));

        // Designation intentionally blank
        signupPage.selectDesignation(
                ConfigReader.getProperty("tc02013.designation"));

        signupPage.selectDepartment(
                ConfigReader.getProperty("tc02013.department"));

        signupPage.selectLocation(
                ConfigReader.getProperty("tc02013.location"));

        test.info("Entered Form Data With Empty Designation");

        /*
         * STEP 3 : Click Sign Up
         */
        LoggerUtil.info("Clicking Sign Up Button");

        signupPage.clickSignUpButton();

        test.info("Sign Up Button Clicked");

        /*
         * STEP 4 : Verify Validation Message
         */
        LoggerUtil.info("Verifying Designation Validation Message");

        String actualMessage =
                signupPage.getValidationMessage("All fields are required.");

        Assert.assertEquals(
                actualMessage,
                "All fields are required.",
                "Incorrect validation message displayed"
        );

        test.pass(
                "Designation Required Validation Displayed Successfully"
        );

        LoggerUtil.info("========== TC-02-013 PASSED ==========");
    }
}