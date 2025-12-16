package Utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class iOSActions extends AppiumUtils {
    IOSDriver driver;
    public iOSActions(IOSDriver driver){
        super(driver);
        this.driver=driver;
    }
    public void longPressActions(WebElement element, int time){
        ((JavascriptExecutor)driver).executeScript("mobile: touchAndHold",
                ImmutableMap.of("elementId", ((RemoteWebElement)element).getId(),"duration",time));
    }
    public void scrollAction(WebElement element, String direction){
        ((JavascriptExecutor)driver).executeScript("mobile: scroll"
                ,ImmutableMap.of("elementId", ((RemoteWebElement)element).getId(),"direction",direction));
    }
    public void swipeAction(WebElement element, String direction){
        ((JavascriptExecutor)driver).executeScript("mobile: swipe"
                ,ImmutableMap.of("elementId", ((RemoteWebElement)element).getId(),"direction",direction));
    }

}
