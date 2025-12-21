package appium.TestCases;

import appium.BaseTests.iOSBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.iOS.iOSAppLocatorsAndMethods;

public class iOSAppTest extends iOSBaseTest {
    @Test
    public void iOSAppTesting(){
        iOSAppLocatorsAndMethods io = new iOSAppLocatorsAndMethods(driver);
        io.clickAlertViews();
        io.setTextEntry();
        io.setMessage();
        io.setOK();
        io.setConfirmCancel();
        String content = io.extractTextOfMessage();
        Assert.assertEquals(content,"A message should be a short, complete sentence.");
        io.setConfirmButton();

    }
}