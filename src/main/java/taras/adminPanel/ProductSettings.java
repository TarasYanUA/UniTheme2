package taras.adminPanel;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;
import taras.storefront.ProductPage;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ProductSettings extends AbstractPage {
    public ProductSettings(){super();}

    @FindBy(css = "input[form='search_filters_form']")
    WebElement searchFieldOfProduct;
    @FindBy(css = ".dropdown-icon--tools")
    WebElement gearwheelOfProduct;
    @FindBy(css = ".products-list__image")
    WebElement chooseAnyProduct;
    @FindBy(xpath = "//ul[@class='dropdown-menu']//a[contains(text(), 'Предпросмотр')]")
    WebElement previewButton;

    public void clickAndType_SearchFieldOfProduct(String value){
        searchFieldOfProduct.click();
        searchFieldOfProduct.sendKeys(value);
        searchFieldOfProduct.sendKeys(Keys.ENTER);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductPage navigateToProductPage(){
        gearwheelOfProduct.click();
        previewButton.click();
        return new ProductPage();
    }
    public void chooseAnyProduct(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(chooseAnyProduct));
        chooseAnyProduct.click();
    }

    //Вкладка товара "Общее"
    @FindBy(id = "product_description_product")
    WebElement field_ProductName;
    @FindBy(id = "elm_price_price")
    WebElement field_Price;
    @FindBy(id = "elm_list_price")
    WebElement field_ListPrice;
    @FindBy(id = "elm_in_stock")
    WebElement field_InStock;
    @FindBy(id = "elm_zero_price_action")
    WebElement setting_ZeroPriceAction;
    @FindBy(id = "elm_product_unit_name")
    WebElement field_UnitName;
    @FindBy(id = "elm_product_units_in_product")
    WebElement field_UnitsInProduct;
    @FindBy(id = "elm_product_show_price_per_x_units")
    WebElement field_PricePerUnit;
    @FindBy(id = "elm_out_of_stock_actions")
    WebElement setting_OutOfStockActions;
    @FindBy(id = "elm_details_layout")
    WebElement setting_ProductTemplate;
    @FindBy(css = "label[for='elm_product_short_descr']")
    WebElement fieldName_ShortDescription;
    @FindBy(id = "redactor-uuid-1")
    WebElement field_ShortDescription;
    @FindBy(css = "label[for='elm_product_promo_text']")
    WebElement fieldName_PromoText;
    @FindBy(id = "redactor-uuid-2")
    WebElement field_PromoText;

    public void clickAndTypeField_ProductName(String value){
        field_ProductName.click();
        field_ProductName.clear();
        field_ProductName.sendKeys(value);
    }

    public void clickAndTypeField_Price(String value){
        field_Price.click();
        field_Price.clear();
        field_Price.sendKeys(value);
    }

    public void clickAndTypeField_ListPrice(String value){
        field_ListPrice.click();
        field_ListPrice.clear();
        field_ListPrice.sendKeys(value);
    }

    public void clickAndTypeField_InStock(String value){
        field_InStock.click();
        field_InStock.clear();
        field_InStock.sendKeys(value);
    }

    public void setPricePerUnit(String value1, String value2, String value3){
        field_UnitName.click();
        field_UnitName.clear();
        field_UnitName.sendKeys(value1);
        field_UnitsInProduct.click();
        field_UnitsInProduct.clear();
        field_UnitsInProduct.sendKeys(value2);
        field_PricePerUnit.click();
        field_PricePerUnit.clear();
        field_PricePerUnit.sendKeys(value3);
    }
    public Select getSetting_ZeroPriceAction(){
        return new Select(setting_ZeroPriceAction);
    }
    public void selectSetting_ZeroPriceAction(String value){
        getSetting_ZeroPriceAction().selectByValue(value);
    }

    public Select getSetting_OutOfStockActions(){
        return new Select(setting_OutOfStockActions);
    }
    public void selectSetting_OutOfStockActions(String value){
        getSetting_OutOfStockActions().selectByValue(value);
    }

    public Select getSetting_ProductTemplate(){return new Select(setting_ProductTemplate);}
    public void selectSetting_ProductTemplate(String value){
        getSetting_ProductTemplate().selectByValue(value);
    }

    public WebElement getField_ShortDescription(){return fieldName_ShortDescription;}
    public void hoverAndTypeField_ShortDescription(String value){
        WebElement element = getField_ShortDescription();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(element), 0, 800);
        hover.perform();
        field_ShortDescription.click();
        field_ShortDescription.clear();
        field_ShortDescription.sendKeys(value);
    }

    public WebElement getField_PromoText(){return fieldName_PromoText;}
    public void hoverAndTypeField_PromoText(String value){
        WebElement element = getField_PromoText();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.scrollToElement(element);
        hover.perform();
        field_PromoText.click();
        field_PromoText.clear();
        field_PromoText.sendKeys(value);
    }


    //вкладка товара "Бонусные баллы"
    @FindBy(css = ".content__tabs-navigation #reward_points")
    public WebElement tab_RewardPoints;
    @FindBy(id = "pd_is_pbp")
    public WebElement setting_AllowPaymentByPoints;
    @FindBy(id = "qty_discounts")
    public WebElement tab_QuantityDiscounts;
    @FindBy(css = "#box_add_qty_discount .cm-value-decimal")
    WebElement field_Quantity;
    @FindBy(css = "#box_add_qty_discount .cm-numeric")
    WebElement field_Value;

    public void clickAndType_field_Quantity(String value){
        field_Quantity.click();
        field_Quantity.clear();
        field_Quantity.sendKeys(value);
    }
    public void clickAndType_field_Value(String value){
        field_Value.click();
        field_Value.clear();
        field_Value.sendKeys(value);
    }
}