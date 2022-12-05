package taras.workPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import taras.AbstractPage;
import taras.DriverProvider;
import java.util.ArrayList;

public class AdminPanel extends AbstractPage {
    public AdminPanel(){
        super();
    }

    public void navigateToAddonsPage(AdminPanel adminPanel) {
        WebElement elementOfAddonsDropDown = hoverAddonsDropDown();
        Actions hoverAddonsDropDown = new Actions(DriverProvider.getDriver());
        hoverAddonsDropDown.moveToElement(elementOfAddonsDropDown);
        hoverAddonsDropDown.perform();
        navigateToAddonsManagementPage();
    }
    public void hoverToProductPage(){
        WebElement elementOfMenuProducts = hoverMenuProducts();
        Actions hoverMenuProducts = new Actions(DriverProvider.getDriver());
        hoverMenuProducts.moveToElement(elementOfMenuProducts);
        hoverMenuProducts.perform();
    }
    public CategoryPage navigateToCategoryPage(){
        categoryPage.click();
        return new CategoryPage();
    }
    public StorefrontMainPage navigateToStorefrontMainPage(){
        storefrontMainPage.click();
        return new StorefrontMainPage();
    }

    @FindBy(css = ".btn.btn-primary")
    private WebElement buttonAuthorization;
    @FindBy(xpath = "(//a[@class=\"dropdown-toggle addons\"])[1]")
    private WebElement addonsDropDown;
    @FindBy(css = "a[id='elm_menu_addons_manage_addons'][class='dropdown-submenu__link ']")
    private WebElement addonsManagementPage;
    @FindBy(xpath = "//tr[@id='addon_abt__unitheme2']//button[@class='btn dropdown-toggle']")
    private WebElement themeSectionsOnManagementPage;
    @FindBy(css = "div[class=\"btn-group dropleft open\"] a[href$='abt__ut2.settings']")
    private WebElement themeSettings;
    @FindBy(xpath = "//button[@class=\"close cm-notification-close cm-notification-close-ajax\"]")
    private WebElement closeWarning;
    @FindBy(xpath = "//li[@class='dropdown nav__header-main-menu-item ']//a[@href='#products']")
    private WebElement menuProducts;
    @FindBy(css = ".cs-icon.icon-shopping-cart")
    private WebElement categoryPage;
    @FindBy(css = "#product_list")
    private WebElement tabProductLists;
    @FindBy(id = "settings.abt__ut2.product_list.price_display_format")
    private WebElement settingPriceDisplayFormat;
    @FindBy(css = ".btn.btn-primary.cm-submit")
    private WebElement saveButtonOfSettings;
    @FindBy(css = ".cs-icon.icon-shopping-cart")
    private WebElement storefrontMainPage;

    
    public void clickButtonAuthorization(){
        buttonAuthorization.click();
    }
    public WebElement hoverAddonsDropDown(){
        return addonsDropDown;
    }
    public void navigateToAddonsManagementPage(){
        addonsManagementPage.click();
    }
    public void clickCloseWarning(){
        closeWarning.click();
    }
    public WebElement hoverMenuProducts(){
        return menuProducts;
    }
    public void clickThemeSectionsOnManagementPage(){
        themeSectionsOnManagementPage.click();
    }
    public void navigateToThemeSettings(){
        themeSettings.click();
    }
    public void clickTabProductLists(){
        tabProductLists.click();
    }

    public Select getSettingPriceDisplayFormat(){
        return new Select(settingPriceDisplayFormat);
    }
    public String selectSettingPriceDisplayFormat(String value){
        getSettingPriceDisplayFormat().selectByValue(value);
        return value;
    }
    public void clickSaveButtonOfSettings(){
        saveButtonOfSettings.click();
    }
}