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
    @FindBy(css = ".nav-tabs #products")
    public WebElement tab_Product;
    @FindBy(css = "#product_list")
    private WebElement tabProductLists;
    @FindBy(css = "div[class=\"btn-group dropleft open\"] a[href$='abt__ut2.less_settings']")
    private WebElement colorSchemeSettings;
    @FindBy(xpath = "//li[@class='dropdown nav__header-main-menu-item ']//a[@href='#products']")
    private WebElement menuProducts;
    @FindBy(css = "a[href$='products.manage']")
    private WebElement section_Products;
    @FindBy(css = "a[href$='product_features.manage']")
    private WebElement section_Features;
    @FindBy(css = "a[href$='feature_id=18'][data-ca-external-click-id]")
    private WebElement featureBrand;
    @FindBy(css = "a[data-ca-external-click-id=\"opener_group23\"]")
    public WebElement feature_HardDrive;
    @FindBy(css = "label[for='elm_feature_description_23']")
    private WebElement field_FeatureDescription;
    @FindBy(css = ".controls .redactor-layer p")
    private WebElement field_DescriptionOfFeature;
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
    public WebElement showInHeaderOnProductPage_Brand;
    @FindBy(id = "elm_feature_display_on_header_23")
    public WebElement showInHeaderOnProductPage_HardDisk;
    @FindBy(css = "input[value='Сохранить']")
    public WebElement button_SaveFeature;

    //Настройки CS-Cart, вкладка "Внешний вид"
    @FindBy(css = "input[id*='field___enable_quick_view']")
    public WebElement setting_QuickView;
    @FindBy(css = "input[id*='field___thumbnails_gallery']")
    public WebElement setting_ThumbnailsGallery;
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
    public WebElement hoverSettingsOfCsCart(){return settingsOfCsCart;}
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
    public WebElement hoverFeatureDescription(){return field_FeatureDescription;}
    public void scrollToFeatureDescription(){
        WebElement element = hoverFeatureDescription();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.scrollToElement(element);
        hover.perform();
        field_FeatureDescription.click();
    }
    public void clickAndTypeField_DescriptionOfFeature(String value){
        field_DescriptionOfFeature.click();
        field_DescriptionOfFeature.clear();
        field_DescriptionOfFeature.sendKeys(value);
    }
    public void clickFeatureBrand(){
        featureBrand.click();
    }
    public void clickThemeSectionsOnManagementPage(){
        themeSectionsOnManagementPage.click();
    }
    public ThemeSettings_ProductLists navigateTo_ThemeSettings_tabProductLists(){
        navigateToAddonsPage();
        clickThemeSectionsOnManagementPage();
        themeSettings.click();
        tabProductLists.click();
        return new ThemeSettings_ProductLists();
    }
    public ThemeSettings_Product navigateTo_ThemeSettings_tabProduct(){
        navigateToAddonsPage();
        clickThemeSectionsOnManagementPage();
        themeSettings.click();
        tab_Product.click();
        return new ThemeSettings_Product();
    }
    public ColorSchemeSettings navigateTo_ColorSchemeSettings(){
        navigateToAddonsPage();
        clickThemeSectionsOnManagementPage();
        colorSchemeSettings.click();
        return new ColorSchemeSettings();
    }
}