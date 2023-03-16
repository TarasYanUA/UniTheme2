package taras.adminPanel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import taras.constants.AbstractPage;

public class ProductSettings extends AbstractPage {
    public ProductSettings(){super();}
    @FindBy(id = "elm_zero_price_action")
    private WebElement setting_ZeroPriceAction;
    @FindBy(id = "elm_out_of_stock_actions")
    private WebElement setting_OutOfStockActions;


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
}