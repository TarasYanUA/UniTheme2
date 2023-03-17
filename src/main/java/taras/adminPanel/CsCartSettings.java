package taras.adminPanel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;
import taras.storefront.StHomePage;

public class CsCartSettings extends AbstractPage {
    public CsCartSettings(){
        super();
    }

    public void navigateToAddonsPage() {
        WebElement elementOfAddonsDropDown = hoverAddonsDropDown();
        Actions hoverAddonsDropDown = new Actions(DriverProvider.getDriver());
        hoverAddonsDropDown.moveToElement(elementOfAddonsDropDown);
        hoverAddonsDropDown.perform();
        navigateToAddonsManagementPage();
    }
    public void navigateToAppearanceSettingsOfCsCart(){
        WebElement mainRightNavBar = hoverSettingsOfCsCart();
        Actions hoverSettingsOfCsCart = new Actions(DriverProvider.getDriver());
        hoverSettingsOfCsCart.moveToElement(mainRightNavBar);
        hoverSettingsOfCsCart.perform();
        clickAppearanceSettingsOfCsCart();
    }
    public void hoverToProductMenu(){
        WebElement elementOfMenuProducts = hoverMenuProducts();
        Actions hoverMenuProducts = new Actions(DriverProvider.getDriver());
        hoverMenuProducts.moveToElement(elementOfMenuProducts);
        hoverMenuProducts.perform();
    }
    public StHomePage navigateToStorefrontMainPage(){
        storefrontMainPage.click();
        return new StHomePage();
    }

    @FindBy(css = ".btn.btn-primary")
    private WebElement buttonAuthorization;
    @FindBy(css = "#bp_off_bottom_panel")
    private WebElement bottomAdminPanel;
    @FindBy(xpath = "(//a[@class=\"dropdown-toggle addons\"])[1]")
    private WebElement addonsDropDown;
    @FindBy(css = "a[id='elm_menu_addons_manage_addons'][class='dropdown-submenu__link ']")
    private WebElement addonsManagementPage;
    @FindBy(xpath = "//tr[@id='addon_abt__unitheme2']//button[@class='btn dropdown-toggle']")
    private WebElement themeSectionsOnManagementPage;
    @FindBy(css = "div[class=\"btn-group dropleft open\"] a[href$='abt__ut2.settings']")
    private WebElement themeSettings;
    @FindBy(xpath = "//li[@class='dropdown nav__header-main-menu-item ']//a[@href='#products']")
    private WebElement menuProducts;
    @FindBy(css = "a[href$='products.manage']")
    private WebElement section_Products;
    @FindBy(css = "a[href$='product_features.manage']")
    private WebElement section_Features;
    @FindBy(css = "a[href$='feature_id=18'][data-ca-external-click-id]")
    private WebElement featureBrand;
    @FindBy(css = ".btn.btn-primary.cm-submit")
    private WebElement saveButtonOfSettings;
    @FindBy(css = ".cs-icon.icon-shopping-cart")
    private WebElement storefrontMainPage;
    @FindBy(css = "#elm_menu_settings")
    private WebElement settingsOfCsCart;
    @FindBy(css = "#elm_menu_settings_Appearance")
    private WebElement appearanceSettingsOfCsCart;
    @FindBy(css = "input[id='elm_feature_display_on_catalog_18']")
    public WebElement showInProductList;
    @FindBy(id = "elm_feature_display_on_header_18")
    public WebElement showInHeaderOnProductPage;

    //Настройки CS-Cart, вкладка "Внешний вид"
    @FindBy(css = "input[id*='field___enable_quick_view']")
    public WebElement setting_QuickView;
    @FindBy(css = "input[id*='field___thumbnails_gallery']")
    public WebElement setting_ThumbnailsGallery;
    @FindBy(id = "field___show_prices_taxed_clean_116")
    public WebElement setting_PriceWithTaxes;
    @FindBy(id = "field___in_stock_field_146")
    public WebElement setting_NumberOfAvailableProducts;
    @FindBy(id = "field___product_details_in_tab_288")
    public WebElement setting_ProductDetailsInTab;



    public void clickSaveButtonOfSettings(){
        saveButtonOfSettings.click();
    }
    public void clickAppearanceSettingsOfCsCart(){
        appearanceSettingsOfCsCart.click();
    }
    public void clickButtonAuthorization(){
        buttonAuthorization.click();
    }
    public void closeBottomAdminPanel(){
        bottomAdminPanel.click();
    }
    public WebElement hoverAddonsDropDown(){
        return addonsDropDown;
    }
    public WebElement hoverSettingsOfCsCart(){
        return settingsOfCsCart;
    }
    public void navigateToAddonsManagementPage(){
        addonsManagementPage.click();
    }
    public WebElement hoverMenuProducts(){
        return menuProducts;
    }
    public ProductSettings navigateToSection_Products(){
        WebElement element = hoverMenuProducts();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
        section_Products.click();
        return new ProductSettings();
    }
    public void navigateToSection_Features(){
        section_Features.click();
    }
    public void clickFeatureBrand(){
        featureBrand.click();
    }
    public void clickThemeSectionsOnManagementPage(){
        themeSectionsOnManagementPage.click();
    }
    public ThemeSettings_ProductLists navigateToThemeSettings(){
        themeSettings.click();
        return new ThemeSettings_ProductLists();
    }
}