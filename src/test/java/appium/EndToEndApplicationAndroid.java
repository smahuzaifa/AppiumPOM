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
    @Test
    public void ToastMessage() throws InterruptedException {
        FormPage fp = new FormPage(driver);
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        scrollIntoView("Bermuda");
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Bermuda\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.androidsample.generalstore:id/radioFemale\")")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        //Toast Message
        Thread.sleep(1500);
        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        //android.widget.Toast this is the class in which the toast messages are stored.
        //We are using this because we cannot inspect toast messages
        System.out.println(toastMessage);
        Assert.assertEquals(toastMessage,"Please enter your name");
    }
    @Test
    public void addToCart(){
        FormPage fp = new FormPage(driver);
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        scrollIntoView("Bermuda");
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Bermuda\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.androidsample.generalstore:id/radioFemale\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.androidsample.generalstore:id/nameField\")")).sendKeys("Hello");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        scrollIntoView("Air Jordan 9 Retro");
        //When there are multiple elements and no unique id can be found we use driver.findElements
        int productSize = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        for(int i=0;i<productSize;i++){
            String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if(productName.equalsIgnoreCase("Air Jordan 9 Retro")){
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        //Explicit wait
        waitForAttributeContains(By.id
                ("com.androidsample.generalstore:id/toolbar_title"), "text", "Cart", 5);
        int cartSize = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        for(int i=0;i<cartSize;i++){
            String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if(productName.equalsIgnoreCase("Air Jordan 9 Retro")){
                System.out.println("The product has been added");
                break;
            }
        }
    }
    @Test
    public void addingCartValue(){
        FormPage fp = new FormPage(driver);
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        scrollIntoView("Bermuda");
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Bermuda\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.androidsample.generalstore:id/radioFemale\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.androidsample.generalstore:id/nameField\")")).sendKeys("Hello");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        scrollIntoView("Air Jordan 9 Retro");
        //When there are multiple elemnts and no unique id can be found we use driver.findElements
        int productSize = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        for(int i=0;i<productSize;i++){
            String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if(productName.equalsIgnoreCase("Air Jordan 9 Retro")){
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }
        scrollIntoView("Jordan 6 Rings");
        for(int i=0;i<productSize;i++){
            String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if(productName.equalsIgnoreCase("Jordan 6 Rings")){
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        //Explicit wait
        waitForAttributeContains(By.id
                ("com.androidsample.generalstore:id/toolbar_title"), "text", "Cart", 5);
        int cartSize = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        //Adding the cart value
        List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        int count = productPrices.size();
        double sum=0;
        for(int i=0;i<count;i++){
            String value = productPrices.get(i).getText();
            double amount = getFormattedAmount(value );//to remove $
            sum = sum + amount;
        }
        System.out.println("The value in the cart adds up to "+sum);
        String actualValueWith$ = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        Double actualValue= getFormattedAmount(actualValueWith$);
        System.out.println("The actual value in the cart is "+actualValue);
        Assert.assertEquals(sum,actualValue);
    }
    @Test
    public void cartPageActions(){
        FormPage fp = new FormPage(driver);
        addingCartValue();
        driver.findElement(By.className("android.widget.CheckBox")).click();
        longPressActions(driver.findElement(By.id("com.androidsample.generalstore:id/termsButton")));
        Assert.assertTrue(driver.findElement(By.id("android:id/message")).isDisplayed());
        driver.findElement(By.id("android:id/button1")).click();
    }
    @Test
    public void handlingBrowserViaTheApp(){
        FormPage fp = new FormPage(driver);
        addToCart();
        driver.findElement(By.className("android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        //Handling Hybrid application
        driver.getContextHandles();
        System.out.println("The available contexts are "+driver.getContextHandles());
        driver.context("WEBVIEW_com.androidsample.generalstore");
        //The context has now been switched to the webview
        driver.context("NATIVE_APP");
    }
}
