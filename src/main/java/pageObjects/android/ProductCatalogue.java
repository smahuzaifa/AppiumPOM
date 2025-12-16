package pageObjects.android;

import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AndroidActions {
    AndroidDriver driver;
    public ProductCatalogue(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    private List<WebElement> productName;
    @AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart")
    private List<WebElement> productAddCart;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement appbar_btn_cart;

    public void selectProduct(String product){
        int productSize =  productName.size();
        for(int i=0;i<productSize;i++){
            String NameOfTheProduct = productName.get(i).getText();
            if(NameOfTheProduct.equalsIgnoreCase(product)){
                productAddCart.get(i).click();
                break;
            }
        }
    }
    public void clickAddToCart(){
        appbar_btn_cart.click();
    }
    public void calculatingCartSize(String product){
        int cartSize = productName.size();
        for(int i=0;i<cartSize;i++){
            String productName2 = productName.get(i).getText();
            if(productName2.equalsIgnoreCase(product)){
                System.out.println("The product has been added");
                break;
            }
        }
    }
}
