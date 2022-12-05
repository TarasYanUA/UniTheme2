package taras.workPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.AbstractPage;

public class ProductPage extends AbstractPage {
    public ProductPage(){
        super();
    }
    @FindBy(xpath = "(//div[@class='sidebar-field']//input[@type='text'])[1]")
    private WebElement searchFieldForProduct;
    @FindBy(className = "products-list__image")
    private WebElement productOnStorefront;
    @FindBy(css = ".cs-icon.icon-reorder")
    private WebElement listOfCategories;
    @FindBy(xpath = "//div[@class=\" btn-bar btn-toolbar nav__actions-bar dropleft\"]//div[@class=\"btn-group dropleft\"]")
    private WebElement gearwheelOfProduct;
    @FindBy(xpath = "//a[contains(@href, '2010-tour-staff-bag-10')][@id=\"187\"]")
    private WebElement previewButton;
    @FindBy(xpath = "//a[@class=\"products-list__image--link\"][contains(@href, 'product_id=187')]")
    private WebElement productTourStaffBag;
    @FindBy(id = "button_cart_187")
    private WebElement buttonAddToCart;

    
    public void clickAndTypeToSearchField(){
        searchFieldForProduct.click();
        searchFieldForProduct.sendKeys("GoPro");
        searchFieldForProduct.sendKeys(Keys.ENTER);
    }

    public void chooseProductGoPro(){
        productOnStorefront.click();
    }
    public void clickAtListOfCategories(){
        listOfCategories.click();
    }
    public void clickGearWheelOfProduct(){
        gearwheelOfProduct.click();
    }
    public void clickPreviewButton(){
        previewButton.click();
    }
    public void chooseProductTourStaffBag(){
        productTourStaffBag.click();
    }
    public void clickButtonAddToCart(){
        buttonAddToCart.click();
    }
}