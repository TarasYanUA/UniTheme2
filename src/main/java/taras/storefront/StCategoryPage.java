package taras.storefront;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import taras.adminPanel.UtilsAdm;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.time.Duration;
import java.util.List;

public class StCategoryPage extends AbstractPage {
    public StCategoryPage() {
        super();
    }

    @FindBy(css = "form[name='product_form_280']")
    private WebElement menClothProduct;

    @FindBy(xpath = "//a[@title='Droid 3']/../../..//a[@data-ca-target-id='product_quick_view']")
    private WebElement quickViewOfPhoneProduct_Droid3;

    @FindBy(xpath = "//form[@name='product_form_280']//a[@data-ca-target-id='product_quick_view']")
    private WebElement quickViewOfMenClothProduct;

    @FindBy(xpath = "//form[@name='product_form_34']//a[@data-ca-target-id='product_quick_view']")
    private WebElement quickViewOfWomanClothProduct;

    @FindBy(css = ".ui-button-icon.ui-icon")
    public WebElement closeQuickView;

    @FindBy(css = ".ty-icon.ut2-icon-products-without-options")
    public WebElement listWithoutOptions_ProductListView;

    @FindBy(css = ".ty-icon.ut2-icon-short-list")
    public WebElement compactList_ProductListView;

    @FindBy(css = "a[class*='ut2-quick-view-button']")
    public WebElement buttonQuickView;

    @FindBy(css = "button[id*='button_cart']")
    public WebElement button_GeneralAddToCart;

    @FindBy(css = ".ty-icon.ut2-icon-use_icon_cart")
    public WebElement button_VariationAddToCart;

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


    public void hoverToProduct(String productTitle) {
        WebElement product = DriverProvider.getDriver().findElement(By.xpath("//a[contains(@title, '" + productTitle + "')]"));
        WebElement quickView = DriverProvider.getDriver().findElement(By.xpath(product + "/../../..//a//a[@data-ca-target-id='product_quick_view']"));

        taras.adminPanel.UtilsAdm.scrollToElementAndScrollBelow(product, 20);
        UtilsAdm.hoverOverElement(quickView);
    }

    public void selectProductListView(WebElement element) {
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("window.scrollTo(0, 0);");
        element.click();
        UtilsAdm.waitForSpinnerDisappear();
    }

    public void clickButtonQuickView() {
        buttonQuickView.click();
        UtilsAdm.waitForSpinnerDisappear();
    }

    public void clickQuickViewOfPhoneProduct() {
        quickViewOfPhoneProduct_Droid3.click();
        UtilsAdm.waitForSpinnerDisappear();
        UtilsAdm.hoverOverElement(closeQuickView);
    }

    public void clickQuickViewOfMenClothProduct() {
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("window.scrollTo(0, 0);");
        UtilsAdm.hoverOverElement(menClothProduct);
        taras.adminPanel.UtilsAdm.hoverNavigateAndClick(quickViewOfMenClothProduct);
        UtilsAdm.waitForSpinnerDisappear();
    }

    public void openWindow_WriteReview() {
        button_WriteReview.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(8)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#ui-id-2")));
    }
}