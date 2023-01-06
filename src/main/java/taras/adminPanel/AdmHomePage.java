package taras.adminPanel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;
import taras.storefront.StHomePage;

public class AdmHomePage extends AbstractPage {
    public AdmHomePage(){
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
    @FindBy(css = "a[href$='product_features.manage']")
    private WebElement menuFeatures;
    @FindBy(css = "a[href$='feature_id=18'][data-ca-external-click-id]")
    private WebElement featureBrand;
    @FindBy(css = "input[id='elm_feature_display_on_catalog_18']")
    public WebElement showInProductList;
    @FindBy(css = ".btn.btn-primary.cm-submit")
    private WebElement saveButtonOfSettings;
    @FindBy(css = ".cs-icon.icon-shopping-cart")
    private WebElement storefrontMainPage;
    @FindBy(css = "#mainrightnavbar")
    private WebElement settingsOfCsCart;
    @FindBy(css = "#elm_menu_settings_Appearance")
    private WebElement appearanceSettingsOfCsCart;

    //Настройки CS-Cart, вкладка "Внешний вид"
    @FindBy(css = "input[id*='field___enable_quick_view']")
    public WebElement settingQuickView;
    @FindBy(css = "input[id*='field___thumbnails_gallery']")
    public WebElement settingThumbnailsGallery;

    //Настройки темы, вкладка "Списки товаров"
    @FindBy(css = "#product_list")
    private WebElement tabProductLists;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.show_gallery']")
    public WebElement settingMiniIconsGallery;
    @FindBy(css = "input[id=\"settings.abt__ut2.product_list.decolorate_out_of_stock_products\"]")
    public WebElement settingOutOfStockProducts;
    @FindBy(id = "settings.abt__ut2.product_list.price_display_format")
    private WebElement settingPriceDisplayFormat;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.price_position_top']")
    public WebElement settingPriceAtTheTop;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.show_rating']")
    public WebElement settingProductRating;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.show_rating_num']")
    public WebElement settingCommonValueOfProductRating;
    @FindBy(css = "select[id='settings.abt__ut2.product_list.products_multicolumns.enable_hover_gallery.desktop']")
    private WebElement settingSwitchProductImageWhenHoveringMousePointer;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.grid_item_height.desktop']")
    private WebElement settingMinHeightForProductCell;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.image_width.desktop']")
    private WebElement settingProductIconWidth;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.image_height.desktop']")
    private WebElement settingProductIconHeight;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.show_sku.desktop']")
    public WebElement settingShowProductCode;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.show_amount.desktop']")
    public WebElement settingDisplayAvailabilityStatus;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.show_qty.desktop']")
    public WebElement settingShowQuantityChanger;
    @FindBy(css = "select[id='settings.abt__ut2.product_list.products_multicolumns.show_button_add_to_cart.desktop']")
    private WebElement settingShowAddToCartButton;
    @FindBy(css = "select[id='settings.abt__ut2.product_list.products_multicolumns.grid_item_bottom_content.desktop']")
    private WebElement settingAdditionalProductInformation;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.show_content_on_hover.desktop']")
    public WebElement settingShowAdditionalInformationOnHover;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.show_brand_logo.desktop']")
    public WebElement settingShowBrandLogo;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.show_you_save.desktop']")
    public WebElement settingShowYouSave;
    @FindBy(css = "select[id='settings.abt__ut2.product_list.products_multicolumns.enable_hover_gallery.desktop']")
    private WebElement settingSwitchProductImageWhenHovering;


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
    public void navigateMenuFeatures(){
        menuFeatures.click();
    }
    public void clickFeatureBrand(){
        featureBrand.click();
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
    public void selectSettingPriceDisplayFormat(String value){
        getSettingPriceDisplayFormat().selectByValue(value);
    }
    public Select getSettingSwitchProductImageWhenHoveringMousePointer(){
        return new Select(settingSwitchProductImageWhenHoveringMousePointer);
    }
    public void selectSettingSwitchProductImageWhenHoveringMousePointer(String value){
        getSettingSwitchProductImageWhenHoveringMousePointer().selectByValue(value);
    }
    public void clickSaveButtonOfSettings(){
        saveButtonOfSettings.click();
    }
    public void clickAppearanceSettingsOfCsCart(){
        appearanceSettingsOfCsCart.click();
    }
    public void clickAndTypeSettingMinHeightForProductCell(String value){
        settingMinHeightForProductCell.click();
        settingMinHeightForProductCell.clear();
        settingMinHeightForProductCell.sendKeys(value);
    }
    public void clickAndTypeSettingProductIconWidth(String value){
        settingProductIconWidth.click();
        settingProductIconWidth.clear();
        settingProductIconWidth.sendKeys(value);
    }
    public void clickAndTypeSettingProductIconHeight(String value){
        settingProductIconHeight.click();
        settingProductIconHeight.clear();
        settingProductIconHeight.sendKeys(value);
    }

    public Select getSettingShowAddToCartButton(){
        return new Select(settingShowAddToCartButton);
    }
    public void selectSettingShowAddToCartButton(String value){
        getSettingShowAddToCartButton().selectByValue(value);
    }

    public Select getSettingAdditionalProductInformation(){
        return new Select(settingAdditionalProductInformation);
    }
    public void selectSettingAdditionalProductInformation(String value){
        getSettingAdditionalProductInformation().selectByValue(value);
    }

    public Select getSettingSwitchProductImageWhenHovering(){
        return new Select(settingSwitchProductImageWhenHovering);
    }
    public void selectSettingSwitchProductImageWhenHovering(String value){
        getSettingSwitchProductImageWhenHovering().selectByValue(value);
    }
}