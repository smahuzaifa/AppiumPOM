package pageObjects.iOS;

import IOSActions.iOSActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class iOSAppLocatorsAndMethods { //extends iOSActions {
//    IOSDriver driver;
//    public iOSAppLocatorsAndMethods(IOSDriver driver){
//        super(driver);
//        this.driver = driver;
//        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
//    }
    @iOSXCUITFindBy(accessibility = "Alert Views")
    private WebElement alertViews;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Text Entry\"]")
    private WebElement textEntry;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeAlert[`name == \"A Short Title Is Best\"`]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell")
    private WebElement message;
    @iOSXCUITFindBy(accessibility = "OK")
    private WebElement okButton;
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"Confirm / Cancel\"")
    private WebElement confirmCancel;
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"A message should be a short, complete sentence.\"")
    private WebElement messageContent;
    @iOSXCUITFindBy(accessibility = "Confirm")
    private WebElement confirmButton;

    public void clickAlertViews(){
        alertViews.click();
    }
    public void setTextEntry(){
        textEntry.click();
    }
    public void setMessage(){
        message.sendKeys("hello world!");
    }
    public void setOK(){
        okButton.click();
    }
    public void setConfirmCancel(){
        confirmCancel.click();
    }
    public String  extractTextOfMessage(){
        return messageContent.getText();
    }
    public void setConfirmButton(){
        confirmButton.click();
    }
}
