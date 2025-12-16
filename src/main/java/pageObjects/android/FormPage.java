package pageObjects.android;
import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class FormPage extends AndroidActions {
    AndroidDriver driver;
    public FormPage(AndroidDriver driver){ //Constructor
        super(driver); //Calls the parent class constructors drivers
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameField;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countrySelector;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.androidsample.generalstore:id/radioFemale\")")
    private WebElement femaleCheck;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.androidsample.generalstore:id/radioMale\")")
    private WebElement maleCheck;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement letsShopButton;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Bermuda\")")
    private WebElement bermuda;



    public void setNameField(String name){
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }
    public void setGender(String gender){
        if (gender.toLowerCase().contains("Female")){
            femaleCheck.click();
        }else maleCheck.click();
    }
    public WebElement setCountry(String country){
        countrySelector.click();
        scrollIntoView(country);
        return bermuda;
    }
    public void setLetsShopButton(){
        letsShopButton.click();
    }

}
