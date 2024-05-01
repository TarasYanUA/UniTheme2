package taras.storefront;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import taras.constants.DriverProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;

public class ProductPage extends AbstractPage {
    public ProductPage(){super();}
    @FindBy(css = ".cm-btn-success")
    public WebElement cookie;
    @FindBy(css = "a[id*='sw_select'][id*='wrap_language']")
    private WebElement gearwheel_Language;
    @FindBy(css = "a[data-ca-name=\"en\"]")
    private WebElement language_EN;
    @FindBy(css = "a[data-ca-name=\"ar\"]")
    private WebElement language_RTL;
    @FindBy(css = "#features a")
    private WebElement tab_Features;
    @FindBy(id = "content_features")
    private WebElement tab_FeaturesForNonTabs;
    @FindBy(css = "a[id*='opener_ut2_features_dialog_']")
    public WebElement featureDescription;
    @FindBy(css = "#subscribe_form_wrapper input")
    public WebElement checkbox_NotifyMe;
    @FindBy(css = ".ty-tabs__span")
    private WebElement blockWithProducts_MostPopular;

    public WebElement getBlockWithProducts_MostPopular(){return blockWithProducts_MostPopular;}
    public void hoverToBlockWithProducts(){
        WebElement element = getBlockWithProducts_MostPopular();
        Actions hoverProduct = new Actions(DriverProvider.getDriver());
        hoverProduct.moveToElement(element);
        hoverProduct.perform();
    }

    @FindBy(css = ".ty-icon.ut2-icon-use_icon_cart")
    public WebElement buttonAddToCart_ProductWithOptions;
    @FindBy(css = ".ui-icon-closethick")
    public WebElement closePopUpWindow;


    public void shiftLanguage_EN(){
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,0);");
        if(!DriverProvider.getDriver().findElements(By.cssSelector(".cm-notification-close")).isEmpty()){
            DriverProvider.getDriver().findElement(By.cssSelector(".cm-notification-close")).click();
        }
        gearwheel_Language.click();
        language_EN.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void shiftLanguage_RTL(){
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,0);");
        if(!DriverProvider.getDriver().findElements(By.cssSelector(".cm-notification-close")).isEmpty()){
            DriverProvider.getDriver().findElement(By.cssSelector(".cm-notification-close")).click();
        }
        gearwheel_Language.click();
        language_RTL.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement hoverTab_Features(){
        return tab_Features;
    }
    public void scrollToAndClickTab_Features(){
        WebElement element = hoverTab_Features();
        Actions scroll = new Actions(DriverProvider.getDriver());
        scroll.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(element), 0, 600);
        scroll.perform();
        tab_Features.click();
    }

    public WebElement hoverTab_FeaturesForNonTabs(){
        return tab_FeaturesForNonTabs;
    }
    public void scrollToAndClickTab_FeaturesForNonTabs(){
        WebElement element = hoverTab_FeaturesForNonTabs();
        Actions scroll = new Actions(DriverProvider.getDriver());
        scroll.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(element), 0, 50);
        scroll.perform();
        tab_FeaturesForNonTabs.click();
    }
}