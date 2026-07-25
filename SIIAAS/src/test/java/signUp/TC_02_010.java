package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_010 extends BaseTest {

    @Test(
        description = "TC-02-010 Verify signup with empty Email Id field"
    )
    public void verifySignupWithEmptyEmailField() {

        LoginPage loginPage = new LoginPage(page);
        SignupPage signupPage = new SignupPage(page);

        LoggerUtil.info("========== STARTING TC-02-010 ==========");

        /*
         * Step 1 : Open Signup Page
         */
        loginPage.clickSignupLink();

        test.info("Signup Page Opened");

        /*
         * Step 2 : Fill Form With Empty Email
         */
        signupPage.enterFullName(
                ConfigReader.getProperty("signup.name"));

        // Email blank

        signupPage.enterPassword(
                ConfigReader.getProperty("signup.password"));

        signupPage.enterConfirmPassword(
                ConfigReader.getProperty("signup.confirmPassword"));

        signupPage.selectDesignation(
                ConfigReader.getProperty("signup.designation"));

        signupPage.selectDepartment(
                ConfigReader.getProperty("signup.department"));

        signupPage.selectLocation(
                ConfigReader.getProperty("signup.location"));

        test.info("Entered Signup Details With Empty Email");

        /*
         * Step 3 : Click Signup
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
                "Email Required Validation Displayed Successfully"
        );

        LoggerUtil.info("TC-02-010 PASSED");
    }
}