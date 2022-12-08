package taras.workPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import taras.AbstractPage;
import taras.DriverProvider;

public class CategoryPage extends AbstractPage {
    public CategoryPage(){
        super();
    }

    @FindBy(css = "form[name='product_form_74'] div[class='ut2-gl__buttons']")
    private WebElement phoneProduct;
    @FindBy(css = "a[href*='nike']")
    private WebElement clothProduct;
    @FindBy(xpath = "//form[@name='product_form_74']//a[@data-ca-target-id='product_quick_view']")
    private WebElement quickViewOfPhoneProduct;
    @FindBy(css = ".ui-button-icon.ui-icon")
    private WebElement closeQuickView;
    @FindBy(css = ".ty-icon.ty-icon-products-multicolumns")
    private WebElement grid_ProductListView;
    @FindBy(css = ".ty-icon.ty-icon-products-without-options")
    private WebElement listWithoutOptions_ProductListView;
    @FindBy(css = "div[class='ut2-sorting-wrap'] span[class='ty-icon ty-icon-short-list']")
    private WebElement compactList_ProductListView;


    public WebElement hoverPhoneProduct(){
        return phoneProduct;
    }
    public void hoverToPhoneProduct() {
        WebElement elementOfPhoneProduct = hoverPhoneProduct();
        Actions hoverPhoneProduct = new Actions(DriverProvider.getDriver());
        hoverPhoneProduct.moveToElement(elementOfPhoneProduct);
        hoverPhoneProduct.perform();
    }
    public WebElement hoverClothProduct(){
        return clothProduct;
    }
    public void hoverToClothProduct() {
        WebElement elementOfClothProduct = hoverClothProduct();
        Actions hoverClothProduct = new Actions(DriverProvider.getDriver());
        hoverClothProduct.moveToElement(elementOfClothProduct);
        hoverClothProduct.perform();
    }
    public void clickQuickViewOfPhoneProduct(){
        quickViewOfPhoneProduct.click();
    }
    public void clickCloseQuickView(){
        closeQuickView.click();
    }
    public void clickGrid_ProductListView(){grid_ProductListView.click();}
    public void clickListWithoutOptions_ProductListView(){listWithoutOptions_ProductListView.click();}
    public void clickCompactList_ProductListView(){compactList_ProductListView.click();}
}