package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_011 extends BaseTest {

    @Test(
        description = "TC-02-011 Verify signup with empty Password field"
    )
    public void verifySignupWithEmptyPasswordField() {

        LoginPage loginPage = new LoginPage(page);
        SignupPage signupPage = new SignupPage(page);

        LoggerUtil.info("========== STARTING TC-02-011 ==========");

        /*
         * Step 1 : Open Signup Page
         */
        loginPage.clickSignupLink();

        test.info("Signup Page Opened");

        /*
         * Step 2 : Fill Form
         */
        signupPage.enterFullName(
                ConfigReader.getProperty("tc02011.name"));

        signupPage.enterEmail(
                ConfigReader.getProperty("tc02011.email"));

        signupPage.enterPassword(
                ConfigReader.getProperty("tc02011.password")); // Blank

        signupPage.enterConfirmPassword(
                ConfigReader.getProperty("tc02011.confirmPassword")); // Blank

        signupPage.selectDesignation(
                ConfigReader.getProperty("tc02011.designation"));

        signupPage.selectDepartment(
                ConfigReader.getProperty("tc02011.department"));

        signupPage.selectLocation(
                ConfigReader.getProperty("tc02011.location"));

        test.info("Entered Signup Details With Empty Password");

        /*
         * Step 3 : Click Sign Up
         */
        signupPage.clickSignUpButton();

        test.info("Clicked Sign Up Button");

        /*
         * Step 4 : Verify Validation Message
         */
        String actualMessage =
                signupPage.getValidationMessage("All fields are required.");

        Assert.assertEquals(
                actualMessage,
                "All fields are required.",
                "Incorrect validation message displayed"
        );

        test.pass(
                "Password Required Validation Displayed Successfully"
        );

        LoggerUtil.info("TC-02-011 PASSED");
    }
}