package signUp;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class TC_02_006 extends BaseTest {

    @Test(
        description = "TC-02-006 Verify approved user can log in successfully"
    )
    public void verifyApprovedUserCanLoginSuccessfully() {

        LoginPage loginPage = new LoginPage(page);

        LoggerUtil.info("========== TC-02-006 STARTED ==========");

        test.info("TC-02-006 Execution Started");

        /*
         * STEP 1
         * Login with approved user
         */

        LoggerUtil.info("Entering Approved User Credentials");

        loginPage.login(

                ConfigReader.getProperty("approvedUserEmail"),
                ConfigReader.getProperty("approvedUserPassword")

        );

        page.waitForLoadState();

        test.info("Approved User Login Attempted");

        /*
         * STEP 2
         * Verify Dashboard
         */

        LoggerUtil.info("Verifying Projects Dashboard");

        Assert.assertTrue(

                loginPage.isProjectsDashboardDisplayed(),

                "Projects Dashboard Not Displayed"

        );

        test.pass("Approved User Logged In Successfully");

        LoggerUtil.info("========== TC-02-006 PASSED ==========");
    }
}