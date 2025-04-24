package taras.storefront;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    @FindBy(css = "a[title='Droid 3']")
    private WebElement phoneProduct_Droid3;

    @FindBy(css = "a[href*='nike']")
    private WebElement clothProduct;

    @FindBy(css = "form[name='product_form_280'] div[class*='__buttons']")
    private WebElement menClothProduct;

    @FindBy(xpath = "//a[@title='Droid 3']/../../..//a[@data-ca-target-id='product_quick_view']")
    private WebElement quickViewOfPhoneProduct_Droid3;

    @FindBy(xpath = "//form[@name='product_form_280']//a[@data-ca-target-id='product_quick_view']")
    private WebElement quickViewOfMenClothProduct;

    @FindBy(css = ".ui-button-icon.ui-icon")
    private WebElement closeQuickView;

    @FindBy(css = ".ty-icon.ut2-icon-products-without-options")
    private WebElement listWithoutOptions_ProductListView;

    @FindBy(css = ".ty-icon.ut2-icon-short-list")
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

    @FindBy(css = ".ut2-pb__title .ty-product-review-write-product-review-button")
    private WebElement button_WriteReview;

    @FindBy(xpath = "(//button[contains(@class, 'ui-dialog-titlebar-close')])[2]")
    public WebElement closeWriteReview;


    public void makePause(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void hoverToPhoneProduct() {
        Actions hoverPhoneProduct = new Actions(DriverProvider.getDriver());
        hoverPhoneProduct.moveToElement(phoneProduct_Droid3);
        hoverPhoneProduct.perform();
    }

    public void hoverToClothProduct() {
        Actions hoverClothProduct = new Actions(DriverProvider.getDriver());
        hoverClothProduct.moveToElement(clothProduct);
        hoverClothProduct.perform();
    }

    public void hoverToMenClothProduct() {
        Actions hoverMenClothProduct = new Actions(DriverProvider.getDriver());
        hoverMenClothProduct.moveToElement(menClothProduct);
        hoverMenClothProduct.perform();
    }

    public void clickListWithoutOptions_ProductListView(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        listWithoutOptions_ProductListView.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(8)))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajax_loading_box[style = 'display: block;']")));
        makePause();
    }

    public void clickCompactList_ProductListView(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        compactList_ProductListView.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(8)))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajax_loading_box[style = 'display: block;']")));
        makePause();
    }

    public void hoverToButtonAddToCart(){
        Actions hoverToElement = new Actions(DriverProvider.getDriver());
        hoverToElement.moveToElement(buttonAddToCart);
        hoverToElement.perform();
    }

    public void clickButtonQuickView(){
        buttonQuickView.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(8)))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajax_loading_box[style = 'display: block;']")));
    }

    public void clickQuickViewOfPhoneProduct(){
        quickViewOfPhoneProduct_Droid3.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(8)))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajax_loading_box[style = 'display: block;']")));
        makePause();
    }

    public void clickQuickViewOfMenClothProduct(){
        quickViewOfMenClothProduct.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(8)))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajax_loading_box[style = 'display: block;']")));
        makePause();
    }

    public WebElement hoverCloseQuickView(){return closeQuickView;}
    public void clickCloseQuickView(){
        WebElement element = hoverCloseQuickView();
        Actions hoverToElement = new Actions(DriverProvider.getDriver());
        hoverToElement.moveToElement(element);
        hoverToElement.perform();
        closeQuickView.click();}

    public void clickButton_WriteReview(){
        button_WriteReview.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(8)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#ui-id-2")));
    }
}