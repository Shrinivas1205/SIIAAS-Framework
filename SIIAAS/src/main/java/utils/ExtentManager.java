package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    public static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            /*
             * Report location
             */
            ExtentSparkReporter spark =
                    new ExtentSparkReporter("reports/SIIAS_Automation_Report.html");

            /*
             * Report Name
             */
            spark.config().setReportName("SIIAS Automation Report");

            /*
             * Document Title
             */
            spark.config().setDocumentTitle("SIIAS Test Report");

            extent = new ExtentReports();

            extent.attachReporter(spark);

            /*
             * System Information
             */
            extent.setSystemInfo("Tester", "Shrinivas A");
            extent.setSystemInfo("Framework", "Playwright Java");
            extent.setSystemInfo("Environment", "QA");

        }

        return extent;
    }
}