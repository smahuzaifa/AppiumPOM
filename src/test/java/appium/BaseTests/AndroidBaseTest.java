package appium.BaseTests;

import Utils.AppiumUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class AndroidBaseTest extends AppiumUtils {
    protected AndroidDriver driver;
    public AppiumDriverLocalService service;
    protected UiAutomator2Options options;
    protected ExtentReports extents;

    @BeforeClass
    public void configuringAppium() throws URISyntaxException, IOException {
        service = startingAppium();
        //service.start();
        //This mentions the path to main.js which starts the server and then passing the appium server
        // url and then the port number

        //Setting the device and its capabilites
        options = getAndroidOptions();
        //Creating androidDriver object
        driver = new AndroidDriver(service.getUrl(),options); //gets url where the appium service is running
        // Uniform Resource Identifier (URI) is a string that identifies a resource, while a
        // Uniform Resource Locator (URL) is a type of URI that specifies both the identity and the
        // location of a resource, typically on the web
        //Adding timeouts or waits
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //this will wait a maximum of 10 seconds for each execution or line of code
        init(driver);
    }

    @AfterClass
    public void tearDown(){
        driver.quit(); //Closes the app once automation is done
        service.stop();
    }


}

