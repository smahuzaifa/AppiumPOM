package Utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppiumUtils {
    AppiumDriver driver; //Parent Driver which works with both android and iOS
    //This is grandparent which is shared between both android and iOS actions
    public AppiumUtils(AppiumDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
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