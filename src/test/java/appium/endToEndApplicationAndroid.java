package appium;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pageObjects.android.formPage;
import pageObjects.android.ProductCatalogue;

public class endToEndApplicationAndroid extends BaseTest2{
    @Test
    public void FirstPage(){
        formPage fp = new formPage(driver);
        fp.setNameField("Hello");
        fp.setCountry("Bermuda").click();
        fp.setGender("Female");
        fp.setLetsShopButton();
    }
    @Test
    public void addToCart(){
        FirstPage();
        formPage fp = new formPage(driver);
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
}
