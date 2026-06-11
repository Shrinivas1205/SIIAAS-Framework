package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_023 extends BaseTest {

    @Test(description = "TC-02-023 : Verify signup with password below minimum length")
    public void verifySignupWithShortPassword() {

        LoginPage loginPage = new LoginPage(page);
        SignupPage signupPage = new SignupPage(page);

        LoggerUtil.info("========== TC-02-023 STARTED ==========");

        /*
         * STEP 1 : Open Signup Page
         */
        loginPage.clickSignupLink();

        /*
         * STEP 2 : Fill Signup Form
         */
        signupPage.enterFullName(
                ConfigReader.getProperty("signup.name"));

        signupPage.enterEmail(
                ConfigReader.getProperty("tc02023.email"));

        signupPage.enterPassword(
                ConfigReader.getProperty("tc02023.password"));

        signupPage.enterConfirmPassword(
                ConfigReader.getProperty("tc02023.confirmPassword"));

        signupPage.selectDesignation(
                ConfigReader.getProperty("signup.designation"));

        signupPage.selectDepartment(
                ConfigReader.getProperty("signup.department"));

        signupPage.selectLocation(
                ConfigReader.getProperty("signup.location"));

        /*
         * STEP 3 : Click Sign Up
         */
        signupPage.clickSignUpButton();

        /*
         * STEP 4 : Verify Validation Message
         */
        String actualMessage =
                signupPage.getValidationMessage(
                        "Password must be at least 8 characters."
                );

        Assert.assertEquals(
                actualMessage,
                "Password must be at least 8 characters.",
                "Minimum password length validation message mismatch"
        );

        test.pass("Minimum password length validation displayed successfully");

        LoggerUtil.info("TC-02-023 PASSED");
    }
}