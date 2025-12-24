package Utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;



public class AppiumUtils {
    protected AppiumDriver driver;
    public AppiumDriverLocalService service;//Parent Driver which works with both android and iOS
    //This is grandparent which is shared between both android and iOS actions
    public void init(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public AppiumDriverLocalService startingAppium() throws IOException {
        //To Start the Appium Server automatically
        String os = System.getProperty("os.name").toLowerCase();
        String appiumJSPath;
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/resources/data.properties");
        prop.load(fis);
        String ip = prop.getProperty("ipAddress");
        int port = Integer.parseInt(prop.getProperty("port"));
        System.out.println(System.getProperty("user.dir"));
        System.out.println("The IP is "+ip +" and the port is "+port);
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
                        .withIPAddress(ip)
                        .usingPort(port)
                        .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
        );
        return service;
    }
    public XCUITestOptions getIOSOptions() {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 17 Pro");
        options.setApp("/Users/huzaifa/IdeaProjects/AppiumPOM/src/test/java/resources/UIKitCatalog.app");
        options.setPlatformVersion("26.1");
        options.setUdid("AB44849F-788D-480A-89A8-3A4DB94B2742");
        return options;
    }
    public UiAutomator2Options getAndroidOptions() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel 9 Pro"); //Emulator
        options.setApp("/Users/huzaifa/IdeaProjects/AppiumPOM/src/test/java/resources/General-Store.apk");
        return options;
    }
    public double getFormattedAmount(String x){
        double price =Double.parseDouble(x.substring(1));
        System.out.println("The actual value in the cart is "+x);
        return price;
    }
    public void waitForElementToAppear(WebElement ele,String x, String y){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains((ele), x, y));
    }

    //Method for screenshot
    public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String reportDir = System.getProperty("user.dir") + "/reports/";
        File dir = new File(reportDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = testCaseName + ".png";
        File dest = new File(reportDir + fileName);
        FileUtils.copyFile(source, dest); //is a helper call that copies one file to another location.
        return fileName;
        /*
        This method takes a screenshot with Appium, saves it under the reports folder, and returns the file name.

        First it calls driver.getScreenshotAs(OutputType.FILE) to get the screenshot as a temporary File. Then it builds
        a folder path called reportDir that points to the reports directory inside your project. It checks whether
        that folder exists and, if not, creates it with mkdirs().

        Next it creates a file name by adding .png to the test name (for example, addToCart.png). It then builds a File
        dest pointing to reports/<testName>.png and copies the temporary screenshot file into this destination with
        FileUtils.copyFile(source, dest).

        Finally, it returns just the file name (testCaseName + ".png"), which your Extent report uses to find the image,
         assuming the HTML report is also in the same reports folder.
        * */
    }

}
