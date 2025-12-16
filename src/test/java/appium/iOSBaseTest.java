package appium;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.URI;
import java.time.Duration;
//    Command to start appium on macOS
//    appium --port 4723 --address 127.0.0.1 --base-path /wd/hub --log-level debug --relaxed-security --allow-cors

public class iOSBaseTest {
    public IOSDriver driver;
    public AppiumDriverLocalService service;
    @BeforeClass
    public void configuringAppium() throws Exception {
        String os = System.getProperty("os.name").toLowerCase();
        String appiumJSPath;

        if (os.contains("win")) {
            appiumJSPath = "C:\\Users\\huzai\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
        } else if (os.contains("mac")) {
            appiumJSPath = "/opt/homebrew/lib/node_modules/appium/build/lib/main.js";
        } else {
            throw new RuntimeException("Unsupported OS: " + os);
        }

        service = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder()
                        .withAppiumJS(new File(appiumJSPath))
                        .withIPAddress("127.0.0.1")
                        .usingPort(4723)
                        .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
        );
        //service.start();
        //Android uses UiAutomator2
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 17 Pro");
        options.setApp("/Users/huzaifa/IdeaProjects/AppiumPOM/src/test/java/resources/UIKitCatalog.app");
        options.setPlatformVersion("26.1");
        options.setUdid("AB44849F-788D-480A-89A8-3A4DB94B2742");
        //In iOS, we have to install web driver agent and then using that app and options the app is automated
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));

        driver = new IOSDriver(new URI("http://127.0.0.1:4723").toURL(),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown(){
        driver.quit(); //Closes the app once automation is done
        //service.stop();
    }
}

