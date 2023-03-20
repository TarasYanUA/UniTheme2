package taras.adminPanel;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;
import taras.storefront.ProductPage;

public class ProductSettings extends AbstractPage {
    public ProductSettings(){super();}
    @FindBy(css = ".sidebar-field input")
    private WebElement searchFieldOfProduct;
    @FindBy(css = ".products-list__image")
    public WebElement chooseAnyProduct;
    @FindBy(xpath = "//div[@class=\" btn-bar btn-toolbar nav__actions-bar dropleft\"]//div[@class=\"btn-group dropleft\"]")
    private WebElement gearwheelOfProduct;
    @FindBy(xpath = "//ul[@class='dropdown-menu']//a[contains(text(), 'Предпросмотр')]")
    private WebElement previewButton;

    @FindBy(id = "elm_zero_price_action")
    private WebElement setting_ZeroPriceAction;
    @FindBy(id = "elm_product_unit_name")
    private WebElement field_UnitName;
    @FindBy(id = "elm_product_units_in_product")
    private WebElement field_UnitsInProduct;
    @FindBy(id = "elm_product_show_price_per_x_units")
    private WebElement field_PricePerUnit;
    @FindBy(id = "elm_out_of_stock_actions")
    private WebElement setting_OutOfStockActions;
    @FindBy(id = "elm_details_layout")
    private WebElement setting_ProductTemplate;
    @FindBy(id = "redactor-uuid-1")
    public WebElement field_ShortDescription;

    //вкладка "Бонусные баллы"
    @FindBy(id = "reward_points")
    public WebElement tab_RewardPoints;
    @FindBy(id = "pd_is_pbp")
    public WebElement setting_AllowPaymentByPoints;


    public void clickAndType_SearchFieldOfProduct(String value){
        searchFieldOfProduct.click();
        searchFieldOfProduct.sendKeys(value);
        searchFieldOfProduct.sendKeys(Keys.ENTER);
    }
    public ProductPage navigateToProductPage(){
        gearwheelOfProduct.click();
        previewButton.click();
        return new ProductPage();
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

    public WebElement getField_ShortDescription(){return field_ShortDescription;}
    public void hoverAndTypeField_ShortDescription(String value){
        WebElement element = getField_ShortDescription();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
        field_ShortDescription.click();
        field_ShortDescription.clear();
        field_ShortDescription.sendKeys(value);
    }
}