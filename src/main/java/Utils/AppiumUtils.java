package Utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;

public class AppiumUtils {
    protected AppiumDriver driver;
    public AppiumDriverLocalService service;//Parent Driver which works with both android and iOS
    //This is grandparent which is shared between both android and iOS actions
    public void init(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    public AppiumDriverLocalService startingAppium(){
        //To Start the Appium Server automatically
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

}