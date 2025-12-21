package appium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class BaseTest2 {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    @BeforeClass
    public void configuringAppium() throws URISyntaxException, MalformedURLException {
        //To Start the Appium Server automatically
        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users" +
                        "\\huzai\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();
        //This mentions the path to main.js which starts the server and then passing the appium server
        // url and then the port number

        //Setting the device and its capabilites
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel 9 Pro"); //Emulator
        options.setApp("C:\\Users\\huzai\\IdeaProjects\\Appium\\src\\test\\java\\resources\\General-Store.apk");

        //Creating androidDriver object
        driver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(),options);
        // Uniform Resource Identifier (URI) is a string that identifies a resource, while a
        // Uniform Resource Locator (URL) is a type of URI that specifies both the identity and the
        // location of a resource, typically on the web

        //Adding timeouts or waits
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //this will wait a maximum of 10 seconds for each execution or line of code
    }

    @AfterClass
    public void tearDown(){
        driver.quit(); //Closes the app once automation is done
        service.stop();
    }
}

