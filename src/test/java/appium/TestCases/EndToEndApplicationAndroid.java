package appium.TestCases;

import appium.BaseTests.AndroidBaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.android.FormPage;
import pageObjects.android.ProductCatalogue;

public class EndToEndApplicationAndroid extends AndroidBaseTest {
    private FormPage fp;
    public EndToEndApplicationAndroid() {
        super();
    }

//    @BeforeMethod
//    public void setActivity() {
//        fp = new FormPage(driver);
//        fp.preSetup();
//    }

    @Test(dataProvider = "getData")
    public void FirstPage(String name, String country, String gender){
       FormPage fp = new FormPage(driver);
//        @BeforeMethod creates a FormPage object and stores it in the field fp, so when FirstPage runs right after,
//                it can reuse that same fp without calling new FormPage(driver) again.
        fp.setNameField(name);
        fp.setCountry(country).click();
        fp.setGender(gender);
        fp.setLetsShopButton();
        fp.preSetup();
    }
    @Test
    public void FirstPage2(){
        FormPage fp = new FormPage(driver);
        fp.setNameField("Hello");
        fp.setCountry("Bermuda").click();
        fp.setGender("Female");
        fp.setLetsShopButton();
        fp.preSetup();
    }
    @Test
    public void addToCart(){
        FirstPage2();
        String shoe = "Air Jordan 9 Retro";
        ProductCatalogue pc = new ProductCatalogue(driver);
        pc.scrollIntoView(shoe);
        pc.selectProduct(shoe);
        pc.clickAddToCart();
        //Explicit wait
        fp.waitForAttributeContains(By.id
                ("com.androidsample.generalstore:id/toolbar_title"), "text", "Cart", 5);
        pc.calculatingCartSize(shoe);
    }
    @DataProvider //When we want to test same test with different types of data
    public Object[][] getData(){
        return new Object[][]{{"Hello","Bermuda","Female"},{"Hello World!","Bermuda","Male"}};
        //passing multiple data
    }
}
