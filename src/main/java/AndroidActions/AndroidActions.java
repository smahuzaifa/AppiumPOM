package AndroidActions;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AndroidActions {
    AndroidDriver driver;
    public AndroidActions(AndroidDriver driver) {
        this.driver = driver;
    }
    public void longPressActions(WebElement element){
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement)element).getId()));
        //This does a long tap for 500ms
    }
    public void longPressActions(WebElement element, int time){
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement)element).getId(),"duration",time));
        //This does long click on the element. The long tap lasts for 2 sec as we have mentioned the duration
    }
    public void scrollIntoView(String element){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+element+"\"));"));
        //Use this when you know where to scroll
    }
    public void scrollToEndAction(int a,int b,int c,int d, String x){ //Use this when the target of scroll is
        //not known
        boolean canScrollMore = (Boolean)((JavascriptExecutor)driver).executeScript("mobile: scrollGesture"
                ,ImmutableMap.of("left",a,"top",b,"width",c,"height",d, "direction",x,
                        //x can be down or up or left or right
                        "percent",3.0));
    }
    public void swipeAction(WebElement element, String x,double y){
        ((JavascriptExecutor)driver).executeScript("mobile: swipeGesture"
                ,ImmutableMap.of("elementId",((RemoteWebElement)element).getId(),
                        "direction",x,
                        "percent",y)); //The value of y should be between 0 and 1
    }
    public void dragAndDropGesture(WebElement element,int x,int y){
        ((JavascriptExecutor)driver).executeScript("mobile: dragGesture"
                ,ImmutableMap.of("elementId",((RemoteWebElement)element).getId(),
                        "endX",x, "endY",y)); //Mention the coordinates by switching from select element modes
    }
    public void waitForAttributeContains(By locator, String attribute, String value, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(locator), attribute, value));
    }
}
