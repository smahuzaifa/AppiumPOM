package appium.BaseTests;

import Utils.AppiumUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class iOSBaseTest extends AppiumUtils {
    public IOSDriver driver;
    public AppiumDriverLocalService service;
    protected XCUITestOptions options;
    protected ExtentReports extents;
    @BeforeClass
    public void configuringAppium() throws Exception {
        service = startingAppium();
        service.start();
        //Android uses UiAutomator2
        options = getIOSOptions();
        //In iOS, we have to install web driver agent and then using that app and options the app is automated
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));
        driver = new IOSDriver(service.getUrl(),options); //gets url where the appium service is running
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown(){
        driver.quit(); //Closes the app once automation is done
        service.stop();
    }

}
