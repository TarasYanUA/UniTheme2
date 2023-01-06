package taras.storefront;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

public class StCategoryPage extends AbstractPage {
    public StCategoryPage(){
        super();
    }

    @FindBy(css = "form[name='product_form_74'] div[class='ut2-gl__buttons']")
    private WebElement phoneProduct;
    @FindBy(css = "a[href*='nike']")
    private WebElement clothProduct;
    @FindBy(css = "form[name='product_form_280'] div[class='ut2-gl__buttons']")
    private WebElement menClothProduct;
    @FindBy(xpath = "//form[@name='product_form_74']//a[@data-ca-target-id='product_quick_view']")
    private WebElement quickViewOfPhoneProduct;
    @FindBy(xpath = "//form[@name='product_form_280']//a[@data-ca-target-id='product_quick_view']")
    private WebElement quickViewOfMenClothProduct;
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
    public WebElement hoverMenClothProduct(){
        return menClothProduct;
    }
    public void hoverToMenClothProduct() {
        WebElement elementOfPhoneProduct = hoverMenClothProduct();
        Actions hoverMenClothProduct = new Actions(DriverProvider.getDriver());
        hoverMenClothProduct.moveToElement(elementOfPhoneProduct);
        hoverMenClothProduct.perform();
    }
    public void clickQuickViewOfPhoneProduct(){
        quickViewOfPhoneProduct.click();
    }
    public void clickCloseQuickView(){
        closeQuickView.click();
    }
    public void clickQuickViewOfMenClothProduct(){
        quickViewOfMenClothProduct.click();
    }
    public void clickGrid_ProductListView(){grid_ProductListView.click();}
    public void clickListWithoutOptions_ProductListView(){listWithoutOptions_ProductListView.click();}
    public void clickCompactList_ProductListView(){compactList_ProductListView.click();}
}