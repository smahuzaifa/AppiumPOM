package appium.BaseTests;
import Utils.AppiumUtils;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static appium.BaseTests.ExtentReporter.extent;

public class Listener extends AppiumUtils implements ITestListener {
    ExtentTest test;
    AppiumDriver driver;
    //ExtentTest represents a single test in the report and is used to log each
    // test step and its status(pass, fail, skip, etc.) into the Extent report.

    @Override
    public void onTestStart(ITestResult result) {
        if (extent != null) {
            test = extent.createTest(result.getMethod().getMethodName());
        } else {
            System.out.println("ExtentReports is null in onTestStart");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (test != null) {
            test.log(Status.PASS, "Test Passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (test != null) {
            test.log(Status.FAIL, "Test Failed");
            test.fail(result.getThrowable());
        } else {
            System.out.println("ExtentTest is null in onTestFailure - likely onTestStart never executed");
        }
        try {
            var field = result.getTestClass().getRealClass().getSuperclass().getDeclaredField("driver");
            field.setAccessible(true); // Allow reflective access to non‑public 'driver' field in the base test class
            driver = (AppiumDriver) field.get(result.getInstance());
            String path = getScreenshotPath(result.getMethod().getMethodName(), driver);
            System.out.println("Screenshot path for Extent: " + path);
            if (test != null) {
                test.addScreenCaptureFromPath(path, result.getMethod().getMethodName());
            }
            /*
            First, it uses reflection to find the driver field on your base test class. getSuperclass().getDeclaredField("driver")
            means “go to my parent class (like AndroidBaseTest) and get its field whose name is driver.”
            var field just lets Java infer the type of that field variable.

            Second, field.setAccessible(true) tells Java “even if this driver field is private or protected, allow access to it.”

            Third, driver = (AppiumDriver) field.get(result.getInstance()); actually reads the value of that driver field from the
            current test object instance and stores it into your listener’s driver variable, so the listener can use the same
            AppiumDriver as the test.

            Fourth, it takes a screenshot and attaches it to the Extent report: getScreenshotPath(...) saves the screenshot to a
            file and returns its path, then test.addScreenCaptureFromPath(...) tells Extent “for this failed
             */

        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        } else {
            System.out.println("ExtentReports not initialized - skipping flush");
        }
    }
}
