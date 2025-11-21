package taras.storefront;

import org.openqa.selenium.By;
import taras.adminPanel.UtilsAdm;
import taras.constants.DriverProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;

public class StProductPage extends AbstractPage {
    public StProductPage() {
        super();
    }

    @FindBy(css = ".cm-btn-success")
    public WebElement cookie;

    @FindBy(css = "a[id*='sw_select'][id*='wrap_language']")
    private WebElement gearwheel_Language;

    @FindBy(css = "#features a")
    private WebElement tab_Features;

    @FindBy(id = "content_features")
    private WebElement tab_FeaturesForNonTabs;

    @FindBy(css = "a[id*='opener_ut2_features_dialog_']")
    public WebElement featureDescription;

    @FindBy(css = "label[id*='label_sw_product_notify']")
    public WebElement checkbox_NotifyMe;

    @FindBy(css = ".ty-tabs__span")
    public WebElement blockWithProducts_MostPopular;

    @FindBy(css = "a[id*='opener_ut2_select_options'] .ut2-icon-use_icon_cart")
    public WebElement buttonAddToCart_ProductWithOptions;

    @FindBy(css = ".ui-icon-closethick")
    public WebElement closePopUpWindow;


    public void selectLanguage(String ruArEn) {
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,0);");
        UtilsAdm.closeAllNotifications();
        gearwheel_Language.click();
        DriverProvider.getDriver().findElement(By.cssSelector("a[data-ca-name='" + ruArEn + "']")).click();
        UtilsAdm.makePause(2000);
        UtilsAdm.hoverOverElement(gearwheel_Language);
    }

    public void scrollToAndClickTab_Features() {
        UtilsAdm.scrollToElementAndScrollBelow(tab_Features, 600);
        tab_Features.click();
    }

    public void scrollToAndClickTab_FeaturesForNonTabs() {
        UtilsAdm.scrollToElementAndScrollBelow(tab_FeaturesForNonTabs, 50);
        tab_FeaturesForNonTabs.click();
    }
}