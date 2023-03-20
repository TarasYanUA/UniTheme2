package taras.storefront;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import taras.constants.DriverProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;

public class ProductPage extends AbstractPage {
    public ProductPage(){super();}
    @FindBy(css = "a[id*='sw_select'][id*='wrap_language']")
    private WebElement gearwheel_Language;
    @FindBy(css = "a[data-ca-name=\"ru\"]")
    private WebElement language_RU;
    @FindBy(css = "a[data-ca-name=\"ar\"]")
    private WebElement language_RTL;
    @FindBy(css = "#features a")
    public WebElement tab_Features;
    @FindBy(css = "a[id*='opener_ut2_features_dialog_']")
    public WebElement featureDescription;


    public void shiftLanguage_RU(){
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,0);");
        gearwheel_Language.click();
        language_RU.click();
    }
    public void shiftLanguage_RTL(){
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,0);");
        gearwheel_Language.click();
        language_RTL.click();
    }

    public WebElement hoverTab_Features(){
        return tab_Features;
    }
    public void scrollToAndClickTab_Features(){
        WebElement element = hoverTab_Features();
        Actions scroll = new Actions(DriverProvider.getDriver());
        scroll.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(element), 0, 750);
        scroll.perform();
        tab_Features.click();
    }
}