package appium.BaseTests;

import Utils.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class BaseTest2 extends AppiumUtils {
    protected AndroidDriver driver;
    public AppiumDriverLocalService service;
    protected UiAutomator2Options options;

    @BeforeClass
    public void configuringAppium() throws URISyntaxException, MalformedURLException {
        service = startingAppium();
        service.start();
        //This mentions the path to main.js which starts the server and then passing the appium server
        // url and then the port number

        //Setting the device and its capabilites
        options = getAndroidOptions();
        //Creating androidDriver object
        driver = new AndroidDriver(new URI("http://127.0.0.1:4723/wd/hub").toURL(),options);
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

