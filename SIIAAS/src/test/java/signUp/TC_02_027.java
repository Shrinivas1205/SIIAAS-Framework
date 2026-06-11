package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.LoggerUtil;

public class TC_02_027 extends BaseTest {

    @Test(description = "TC-02-027 Verify all required fields are marked with asterisk (*)")
    public void verifyAllRequiredFieldsMarkedWithAsterisk() {

        LoginPage loginPage = new LoginPage(page);
        SignupPage signupPage = new SignupPage(page);

        test.info("TC-02-027 Execution Started");

        /*
         * Step 1 : Open Signup Page
         */
        loginPage.clickSignupLink();

        LoggerUtil.info("Signup Page Opened");

        /*
         * Step 2 : Verify Required Fields
         */
        Assert.assertTrue(signupPage.isLabelMarkedRequired("Name"),
                "Name field is not marked as required");

        Assert.assertTrue(signupPage.isLabelMarkedRequired("Email Id"),
                "Email Id field is not marked as required");

        Assert.assertTrue(signupPage.isLabelMarkedRequired("Password"),
                "Password field is not marked as required");

        Assert.assertTrue(signupPage.isLabelMarkedRequired("Confirm Password"),
                "Confirm Password field is not marked as required");

        Assert.assertTrue(signupPage.isLabelMarkedRequired("Designation"),
                "Designation field is not marked as required");

        Assert.assertTrue(signupPage.isLabelMarkedRequired("Department"),
                "Department field is not marked as required");

        Assert.assertTrue(signupPage.isLabelMarkedRequired("Location"),
                "Location field is not marked as required");

        test.pass("All required fields are marked with asterisk (*)");

        LoggerUtil.info("TC-02-027 PASSED");
    }
}