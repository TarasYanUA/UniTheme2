package taras.storefront;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;

public class ProductPage extends AbstractPage {
    public ProductPage(){super();}
    @FindBy(css = "a[id*='sw_select'][id*='wrap_language']")
    private WebElement gearwheel_Language;
    @FindBy(css = "a[data-ca-name=\"ru\"]")
    private WebElement language_RU;
    @FindBy(css = "#features a")
    public WebElement tab_Features;


    public void shiftLanguage_RU(){
        gearwheel_Language.click();
        language_RU.click();
    }
}
