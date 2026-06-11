package BaseClass;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

import factory.BrowserFactory;
import utils.ConfigReader;
import utils.ExtentManager;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

/*
 * ============================================================
 * BaseTest Class
 * ============================================================
 * Purpose:
 *   1. Browser setup        - @BeforeMethod
 *   2. Browser teardown     - @AfterMethod
 *   3. Report initialization- @BeforeSuite
 *   4. Screenshot handling  - null-safe capture + attach
 *   5. Logging              - LoggerUtil throughout
 * ============================================================
 */
public class BaseTest {

	// ─────────────────────────────────────────────────────────
	// Playwright Objects (shared across all test classes)
	// ─────────────────────────────────────────────────────────

	public Browser        browser;
	public BrowserContext context;
	public Page           page;
	
	public void logStepWithScreenshot(String message, String screenshotName) {

	    test.pass(message);

	    ScreenshotUtil.captureAndAttachScreenshot(
	            page,
	            test,
	            screenshotName
	    );
	}

	// ─────────────────────────────────────────────────────────
	// Extent Report Objects
	// ─────────────────────────────────────────────────────────

	public static ExtentReports extent;
	public static ExtentTest    test;

	// ============================================================
	// @BeforeSuite — Runs ONCE before the entire test suite
	// ============================================================
	@BeforeSuite
	public void startReport() {

		// Initialize Extent Report via ExtentManager singleton
		extent = ExtentManager.getInstance();

		LoggerUtil.info("Extent Report Initialized");
	}

	// ============================================================
	// @BeforeMethod — Runs BEFORE every individual test method
	// ============================================================
	@BeforeMethod
	public void setup(Method method) {

		// Create a new test node in the Extent Report
		test = extent.createTest(getClass().getName());

		LoggerUtil.info("========== TEST STARTED ==========");

		// ── STEP 1 : Launch Browser ───────────────────────────
		LoggerUtil.info("Launching Browser");
		test.info("Launching Browser");

		// Launch browser using config (e.g. chrome / firefox)
		page = BrowserFactory.initBrowser(
				ConfigReader.getProperty("browser")
		);

		// Store browser and context references from the page
		browser = page.context().browser();
		context = page.context();

		// Capture screenshot after browser launch (null-safe)
		String browserShot = ScreenshotUtil.captureScreenshot(page, "Browser_Launched");

		if (browserShot != null) {
			try {
				test.info(
						"Browser Launched",
						MediaEntityBuilder.createScreenCaptureFromPath(browserShot).build()
				);
			} catch (Exception e) {
				LoggerUtil.error("Failed to attach browser launch screenshot: " + e.getMessage());
			}
		}

		// ── STEP 2 : Open Application URL ────────────────────
		LoggerUtil.info("Opening Application URL");

		page.navigate(ConfigReader.getProperty("url"));

		// Wait for the page to fully load before proceeding
		page.waitForLoadState();

		LoggerUtil.info("Application Opened");

		// Capture screenshot after application opens (null-safe)
		String appShot = ScreenshotUtil.captureScreenshot(page, "Application_Opened");

		if (appShot != null) {
			try {
				test.pass(
						"Application Opened",
						MediaEntityBuilder.createScreenCaptureFromPath(appShot).build()
				);
			} catch (Exception e) {
				LoggerUtil.error("Failed to attach application opened screenshot: " + e.getMessage());
			}
		}
	}

	// ============================================================
	// @AfterMethod — Runs AFTER every individual test method
	// ============================================================
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {

		// ── TEST PASSED ───────────────────────────────────────
		if (result.getStatus() == ITestResult.SUCCESS) {

			LoggerUtil.info("TEST PASSED");

			// Capture pass screenshot (null-safe)
			String passShot = ScreenshotUtil.captureScreenshot(page, "Test_Passed");

			if (passShot != null) {
				try {
					test.pass(
							"TEST PASSED",
							MediaEntityBuilder.createScreenCaptureFromPath(passShot).build()
					);
				} catch (Exception e) {
					LoggerUtil.error("Failed to attach pass screenshot: " + e.getMessage());
				}
			} else {
				// Log pass status even if screenshot is unavailable
				test.pass("TEST PASSED (screenshot unavailable)");
			}
		}

		// ── TEST FAILED ───────────────────────────────────────
		else if (result.getStatus() == ITestResult.FAILURE) {

			LoggerUtil.error("TEST FAILED");

			// Capture failure screenshot (null-safe)
			String failShot = ScreenshotUtil.captureScreenshot(page, "Test_Failed");

			if (failShot != null) {
				try {
					test.fail(
							result.getThrowable(),
							MediaEntityBuilder.createScreenCaptureFromPath(failShot).build()
					);
				} catch (Exception e) {
					LoggerUtil.error("Failed to attach failure screenshot: " + e.getMessage());
				}
			} else {
				// Log failure with exception even if screenshot is unavailable
				test.fail(result.getThrowable());
			}
		}

		// ── TEST SKIPPED ──────────────────────────────────────
		else if (result.getStatus() == ITestResult.SKIP) {

			LoggerUtil.warn("TEST SKIPPED");
			test.skip("TEST SKIPPED");
		}
		

		// ── FLUSH REPORT ──────────────────────────────────────
		// Write current test results to the report file
		extent.flush();

		// ── CLOSE BROWSER ─────────────────────────────────────
		/*
		 * if (browser != null) { LoggerUtil.info("Closing Browser"); browser.close(); }
		 */

		LoggerUtil.info("========== TEST FINISHED ==========");
	}

	// ============================================================
	// @AfterSuite — Runs ONCE after the entire test suite finishes
	// ============================================================
	@AfterSuite
	public void flushReport() {

		// Final flush to ensure last test results are written
		if (extent != null) {
			extent.flush();
		}

		LoggerUtil.info("Extent Report Generated");
	}
	private void change() {
		
		System.out.println("change");// TODO Auto-generated method stub

	}
private void change1() {
		
		System.out.println("test");// TODO Auto-generated method stub

	}
}