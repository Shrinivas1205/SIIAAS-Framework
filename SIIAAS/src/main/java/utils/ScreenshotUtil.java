package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

public class ScreenshotUtil {

    public static String captureScreenshot(Page page, String fileName) {

        if (page == null) {
            LoggerUtil.error("Page is NULL — skipping screenshot: " + fileName);
            return null;
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Ensure screenshots folder exists
        File screenshotsDir = new File(System.getProperty("user.dir") + "/screenshots/");
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdirs();
        }

        String path = screenshotsDir.getAbsolutePath()
                + "/" + fileName + "_" + timeStamp + ".png";

        try {

            page.screenshot(
                    new Page.ScreenshotOptions()
                            .setPath(new File(path).toPath())
                            .setFullPage(false)
                            .setTimeout(30000)   // ✅ Increased from 10s → 30s
            );

            return path;

        } catch (Exception e) {
            LoggerUtil.error("Screenshot capture failed [" + fileName + "]: " + e.getMessage());
            return null;   // Caller must handle null
        }
    }

    public static void captureAndAttachScreenshot(Page page, ExtentTest test, String fileName) {

        try {

            String screenshotPath = captureScreenshot(page, fileName);

            // ✅ Only attach if screenshot was actually captured
            if (screenshotPath != null && !screenshotPath.isEmpty()) {
                test.addScreenCaptureFromPath(screenshotPath);
            } else {
                test.warning("Screenshot not available for: " + fileName);
            }

        } catch (Exception e) {
            LoggerUtil.error("Failed to attach screenshot to report: " + e.getMessage());
        }
    }
}