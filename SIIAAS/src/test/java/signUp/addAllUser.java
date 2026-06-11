package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;
import BaseClass.BaseTest;
import Pages.LoginPage;
import Pages.SignupPage;
import utils.DataProviders;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class addAllUser extends BaseTest {

    @Test(dataProvider = "SignupData", dataProviderClass = DataProviders.class,
          description = "addAllUser : Verify successful signup with valid inputs")
    
  
    
    public void verifySuccessfulSignup(
            String name, String email, String password, String confirmPassword,
            String designation, String department, String location) throws InterruptedException {

        LoginPage loginPage   = new LoginPage(page);
        SignupPage signupPage = new SignupPage(page);

        LoggerUtil.info("========== STARTING addAllUser ==========");
        test.info("addAllUser Execution Started | User: " + name);

        // ── Step 1: Navigate to Signup page ──────────────────────────────────
        LoggerUtil.info("Clicking Signup Link");
        test.info("Clicking Signup Link");

        loginPage.clickSignupLink();          // ✅ Only ONE click - removed the duplicate

        // Wait for signup form to be visible before proceeding
        page.waitForLoadState();
        page.waitForSelector("//input[@placeholder='Enter your name']",
                new com.microsoft.playwright.Page.WaitForSelectorOptions()
                        .setTimeout(15000));

        ScreenshotUtil.captureAndAttachScreenshot(page, test, "Signup_Page_Opened");

        // ── Step 2: Fill the form ─────────────────────────────────────────────
        LoggerUtil.info("Filling signup form for: " + name);

        signupPage.enterFullName(name);
        signupPage.enterEmail(email);
        signupPage.enterPassword(password);
        signupPage.enterConfirmPassword(confirmPassword);
        signupPage.selectDesignation(designation);
        signupPage.selectDepartment(department);
        signupPage.selectLocation(location);

        ScreenshotUtil.captureAndAttachScreenshot(page, test, "Signup_Form_Filled");

        // ── Step 3: Submit ────────────────────────────────────────────────────
        LoggerUtil.info("Clicking Sign Up button");
        signupPage.clickSignUpButton();

        ScreenshotUtil.captureAndAttachScreenshot(page, test, "Signup_Submitted");

        // ── Step 4: Verify success message ───────────────────────────────────
        Assert.assertTrue(signupPage.isApprovalMessageDisplayed(),
                "Approval message not displayed after signup for user: " + name
        );

        ScreenshotUtil.captureAndAttachScreenshot(page, test, "Signup_Successful");

        LoggerUtil.info("addAllUser PASSED for: " + name);
        test.pass("User signup completed successfully: " + name);
    }
}