package appium.BaseTests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterTest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporter {

    protected static ExtentReports extent;

    static {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String path = System.getProperty("user.dir") + "/reports/Report_" + timeStamp + ".html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "SMAH");
    }

    @AfterTest
    public void tearDownReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
