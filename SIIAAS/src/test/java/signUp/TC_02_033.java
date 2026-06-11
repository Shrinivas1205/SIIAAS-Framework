package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_033 extends BaseTest {

    @Test(description = "TC-02-033 Verify signup with numeric-only Name")
    public void verifySignupWithNumericOnlyName() {

        LoginPage loginPage = new LoginPage(page);
        SignupPage signupPage = new SignupPage(page);

        test.info("TC-02-033 Execution Started");

        /*
         * Open Signup Page
         */
        loginPage.clickSignupLink();

        LoggerUtil.info("Signup Page Opened");

        /*
         * Enter Signup Details
         */
        signupPage.enterFullName(
                ConfigReader.getProperty("tc02033.name"));

        signupPage.enterEmail(
                ConfigReader.getProperty("tc02033.email"));

        signupPage.enterPassword(
                ConfigReader.getProperty("tc02033.password"));

        signupPage.enterConfirmPassword(
                ConfigReader.getProperty("tc02033.confirmPassword"));

        signupPage.selectDesignation(
                ConfigReader.getProperty("signup.designation"));

        signupPage.selectDepartment(
                ConfigReader.getProperty("signup.department"));

        signupPage.selectLocation(
                ConfigReader.getProperty("signup.location"));

        loginPage.attachStepScreenshot("Numeric_Name_Entered");

        /*
         * Click Sign Up
         */
        signupPage.clickSignUpButton();

        LoggerUtil.info("Clicked Sign Up Button");

        loginPage.attachStepScreenshot("Validation_Message_Displayed");

        /*
         * Verify Validation Message
         * Replace below text if actual application message differs.
         */
        String actualMessage = signupPage.getValidationMessage(
                "Name cannot be numeric only. Please enter a valid value.");

        Assert.assertEquals(
                actualMessage,
                "Name cannot be numeric only. Please enter a valid value.",
                "Incorrect validation message displayed");

        test.pass("Validation message displayed for numeric-only name");

        LoggerUtil.info("TC-02-033 PASSED");
    }
}