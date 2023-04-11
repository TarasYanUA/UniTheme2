package taras.storefront;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;
import java.time.Duration;
import java.util.List;
import static taras.constants.DriverProvider.getDriver;

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
    @FindBy(css = "div[class='ut2-sorting-wrap'] span[class='ty-icon ty-icon-short-list']")
    private WebElement compactList_ProductListView;
    @FindBy(css = "a[class*='ut2-quick-view-button']")
    public WebElement buttonQuickView;
    @FindBy(css = "button[id*='button_cart']")
    public WebElement buttonAddToCart;
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
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        makePause();
    }
    public void clickQuickViewOfMenClothProduct(){
        quickViewOfMenClothProduct.click();
    }
    public void clickGrid_ProductListView(){
        grid_ProductListView.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#ajax_loading_box[style = 'display: block;']")));
    }
    public void clickListWithoutOptions_ProductListView(){
        listWithoutOptions_ProductListView.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#ajax_loading_box[style = 'display: block;']")));
    }
    public void clickCompactList_ProductListView(){
        compactList_ProductListView.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#ajax_loading_box[style = 'display: block;']")));
    }
    public WebElement moveToButtonAddToCart(){return buttonAddToCart;}
    public void hoverToButtonAddToCart(){
        WebElement elementOfButtonAddToCart = moveToButtonAddToCart();
        Actions hoverToElement = new Actions(DriverProvider.getDriver());
        hoverToElement.moveToElement(elementOfButtonAddToCart);
        hoverToElement.perform();
    }
    public void clickButtonQuickView(){
        buttonQuickView.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
    }
    public void clickCloseQuickView(){closeQuickView.click();}
    public void makePause(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}