package taras.storefront;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.util.List;

public class StCategoryPage extends AbstractPage {
    public StCategoryPage(){
        super();
    }

    @FindBy(css = "form[name='product_form_74'] div.ut2-gl__body")
    private WebElement phoneProduct;
    @FindBy(css = "a[href*='nike']")
    private WebElement clothProduct;
    @FindBy(css = "form[name='product_form_280'] div[class*='__buttons']")
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
    @FindBy(css = "div[class*='ut2-show-rating-num']")
    public WebElement commonValueOfProductRating;
    @FindBy(css = ".ut2-rating-stars-empty")
    public WebElement emptyRatingStars;
    @FindBy(css = "div[class='ut2-sorting-wrap'] span[class='ty-icon ty-icon-short-list']")
    private WebElement compactList_ProductListView;
    @FindBy(css = "a[class*='ut2-quick-view-button']")
    public WebElement buttonQuickView;
    @FindBy(css = "span[id*='product_code']")
    public WebElement productCode;
    @FindBy(css = ".ty-compact-list__amount")
    public WebElement availabilityStatus;
    @FindBy(css = "button[id*='button_cart']")
    public WebElement buttonAddToCart;
    @FindBy(css = "div[class*='cm-value-changer']")
    public WebElement quantityCharger;
    @FindBy(css = ".ty-btn__add-to-cart")
    public WebElement button_AddToCart;
    @FindBy(css = ".ty-btn__secondary.cm-notification-close")
    public WebElement button_ContinueShopping;
    @FindBy(css = ".notification-content.alert")
    public List<WebElement> notification_AlertSuccess;
    @FindBy(css = ".close.cm-notification-close")
    public WebElement closeNotification_AlertSuccess;
    @FindBy(css = ".ut2-add-to-wish")
    public WebElement button_AddToWishList;
    @FindBy(css = ".ut2-add-to-compare")
    public WebElement button_AddToComparisonList;
    @FindBy(css = ".cm-notification-close")
    public WebElement closeNotificationWindow;
    @FindBy(css = "a.ut2-add-to-wish.active")
    public WebElement button_AddToWishList_ActiveStatus;
    @FindBy(css = "a.ut2-add-to-compare.active")
    public WebElement button_AddToComparisonList_ActiveStatus;

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
    public WebElement moveToButtonAddToCart(){return buttonAddToCart;}
    public void hoverToButtonAddToCart(){
        WebElement elementOfButtonAddToCart = moveToButtonAddToCart();
        Actions hoverToElement = new Actions(DriverProvider.getDriver());
        hoverToElement.moveToElement(elementOfButtonAddToCart);
        hoverToElement.perform();
    }
    public void clickButtonQuickView(){
        buttonQuickView.click();
    }


}