package taras.adminPanel;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;
import taras.storefront.StProductPage;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ProductSettings extends AbstractPage {
    public ProductSettings() {
        super();
    }

    @FindBy(css = "input[form='search_filters_form']")
    WebElement searchFieldOfProduct;

    @FindBy(css = ".products-list__image")
    WebElement chooseAnyProduct;

    @FindBy(css = ".dropdown-icon--tools")
    WebElement gearwheelOfProduct;

    @FindBy(xpath = "//ul[@class='dropdown-menu']//a[contains(text(), 'Предпросмотр')]")
    WebElement previewButton;


    public void clickAndType_SearchFieldOfProduct(String value) {
        UtilsAdm.closeAllNotifications();
        UtilsAdm.clickAndType(searchFieldOfProduct, value);
        searchFieldOfProduct.sendKeys(Keys.ENTER);
        UtilsAdm.makePause(4000);
        chooseAnyProduct();
        UtilsAdm.closeAllNotifications();
    }

    void chooseAnyProduct() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(chooseAnyProduct));
        chooseAnyProduct.click();
    }

    public StProductPage navigateToProductPage() {
        gearwheelOfProduct.click();
        previewButton.click();
        return new StProductPage();
    }


    //Вкладка товара "Общее"
    @FindBy(id = "product_description_product")
    public WebElement field_ProductName;

    @FindBy(id = "elm_price_price")
    public WebElement field_Price;

    @FindBy(id = "elm_list_price")
    public WebElement field_ListPrice;

    @FindBy(id = "elm_in_stock")
    public WebElement field_InStock;

    @FindBy(id = "elm_zero_price_action")
    public WebElement setting_ZeroPriceAction;

    @FindBy(id = "elm_product_unit_name")
    WebElement field_UnitName;

    @FindBy(id = "elm_product_units_in_product")
    WebElement field_UnitsInProduct;

    @FindBy(id = "elm_product_show_price_per_x_units")
    WebElement field_PricePerUnit;

    @FindBy(id = "elm_out_of_stock_actions")
    public WebElement setting_OutOfStockActions;

    @FindBy(id = "elm_details_layout")
    WebElement setting_ProductTemplate;

    @FindBy(css = "label[for='elm_product_short_descr']")
    WebElement fieldName_ShortDescription;

    @FindBy(xpath = "//label[@for='elm_product_short_descr']/..//i[@class='re-icon-html']")
    WebElement buttonHtml_ShortDescription;

    @FindBy(id = "redactor-uuid-1")
    WebElement field_ShortDescription;

    @FindBy(xpath = "//div[@id='redactor-uuid-1']/..//textarea[@class='cm-skip-check-item open']")
    WebElement fieldHtml_ShortDescription;

    @FindBy(css = "label[for='elm_product_promo_text']")
    WebElement fieldName_PromoText;

    @FindBy(xpath = "//label[@for='elm_product_promo_text']/..//a[@alt='HTML']")
    WebElement field_PromoText;

    @FindBy(xpath = "//label[@for='elm_product_promo_text']/..//textarea[@class=\"cm-skip-check-item open\"]")
    WebElement promoTextArea;


    public void setPricePerUnit(String value1, String value2, String value3) {
        UtilsAdm.clickAndType(field_UnitName, value1);
        UtilsAdm.clickAndType(field_UnitsInProduct, value2);
        UtilsAdm.clickAndType(field_PricePerUnit, value3);
    }

    public void selectSetting_ProductTemplate(String value) {
        UtilsAdm.scrollIntoCenter(setting_ProductTemplate);
        new Select(setting_ProductTemplate).selectByValue(value);
    }

    public void hoverAndTypeField_ShortDescription(String value) {
        UtilsAdm.scrollIntoCenter(fieldName_ShortDescription);
        new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(field_ShortDescription));
        buttonHtml_ShortDescription.click();
        UtilsAdm.clickAndType(fieldHtml_ShortDescription, value);
    }

    public void hoverAndTypeField_PromoText(String value) {
        UtilsAdm.scrollIntoCenter(fieldName_PromoText);
        new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(field_PromoText));
        field_PromoText.click();
        UtilsAdm.clickAndType(promoTextArea, value);
    }


    //Вкладка товара "Бонусные баллы"
    @FindBy(css = ".content__tabs-navigation #reward_points")
    public WebElement tab_RewardPoints;

    @FindBy(id = "pd_is_pbp")
    public WebElement setting_AllowPaymentByPoints;

    @FindBy(id = "qty_discounts")
    public WebElement tab_QuantityDiscounts;


    //Вкладка товара "Оптовые скидки"
    @FindBy(css = "#box_add_qty_discount .cm-value-decimal")
    public WebElement field_Quantity;

    @FindBy(css = "#box_add_qty_discount .cm-numeric")
    public WebElement field_Value;


    //Вкладка товара "Вариации"
    @FindBy(css = "a[href*='dispatch=product_variations.manage']")
    WebElement tab_Variations;

    @FindBy(id = "opener_update_product_group")
    WebElement button_AddVariations;


    public void selectAllVariations() {
        tab_Variations.click();

        BasicPage basicPage = new BasicPage();
        if (!DriverProvider.getDriver().findElements(By.cssSelector("div.product-variations__notification:not(.hidden)")).isEmpty())
            basicPage.clickSaveButtonOfSettings();

        if (!DriverProvider.getDriver().findElements(By.cssSelector("#content_variations_pagination .no-items")).isEmpty()) {
            UtilsAdm.closeAllNotifications();
            new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(8))
                    .until(ExpectedConditions.visibilityOf(button_AddVariations))
                    .click();
            UtilsAdm.waitForPopUpWindow();
            WebElement field_findFeaturesForVariations = DriverProvider.getDriver().findElement(By
                    .cssSelector("#generate_variations_container .select2-selection--multiple"));
            JavascriptExecutor js = (JavascriptExecutor) DriverProvider.getDriver();
            js.executeScript("arguments[0].click();", field_findFeaturesForVariations);

            new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(8))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".select2-results__options")));
            DriverProvider.getDriver().findElement(By
                    .xpath("//div[@class='object-picker__selection-product-feature']//span[text()='Цвет']"))
                    .click();
            js.executeScript("arguments[0].click();", field_findFeaturesForVariations);

            new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(8))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cm-variations-generator_add-all-variants")))
                    .click();
            new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(8))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[id*='tools_tab_create_new'] a")))
                    .click();

            WebElement button_SaveProductVariations = DriverProvider.getDriver().findElement(By
                    .cssSelector("#tools_variations_btn.btn-primary.cm-submit"));
            button_SaveProductVariations.click();
        }
    }
}