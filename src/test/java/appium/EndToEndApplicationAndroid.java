package appium;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.android.FormPage;

import java.util.List;

public class EndToEndApplicationAndroid extends BaseTest2{
    @Test
    public void FirstPage(){
        FormPage fp = new FormPage(driver);
        fp.setNameField("Hello");
        fp.setCountry("Bermuda").click();
        fp.setGender("Female");
        fp.setLetsShopButton();
    }
}
