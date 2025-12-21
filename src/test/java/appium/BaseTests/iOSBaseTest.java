package appium.BaseTests;

import Utils.AppiumUtils;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.URI;
import java.time.Duration;
//    Command to start appium on macOS
//    appium --port 4723 --address 127.0.0.1 --base-path /wd/hub --log-level debug --relaxed-security --allow-cors

public class iOSBaseTest extends AppiumUtils {
    public IOSDriver driver;
    public AppiumDriverLocalService service;
    protected XCUITestOptions options;
    @BeforeClass
    public void configuringAppium() throws Exception {
        service = startingAppium();
        service.start();
        //Android uses UiAutomator2
        options = getIOSOptions();
        //In iOS, we have to install web driver agent and then using that app and options the app is automated
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));
        driver = new IOSDriver(new URI("http://127.0.0.1:4723/wd/hub").toURL(),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown(){
        driver.quit(); //Closes the app once automation is done
        service.stop();
    }
}
