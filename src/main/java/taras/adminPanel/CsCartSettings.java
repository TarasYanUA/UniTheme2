package taras.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;
import taras.storefront.StHomePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class CsCartSettings extends AbstractPage implements CheckPageOnEngLang, CheckMenuToBeActive {
    public CsCartSettings() {
        super();
    }

    @FindBy(css = ".btn.btn-primary.cm-submit")
    private WebElement saveButtonOfSettings;

    @FindBy(css = ".cm-notification-close")
    public List<WebElement> closeNotification;


    public void closeNotificationIfPresent() {
        if (!closeNotification.isEmpty())
            closeNotification.getFirst().click();
    }

    public void clickSaveButtonOfSettings() {
        saveButtonOfSettings.click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public StHomePage navigateToStorefront() {
        String currentUrl = DriverProvider.getDriver().getCurrentUrl();
        String[] url = currentUrl.split("admin.php");
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("window.open('" + url[0] + "')");
        return new StHomePage();
    }


    //Меню "Настройки"
    @FindBy(id = "administration")
    private WebElement menu_Settings;

    //Меню "Настройки -- Общие настройки -- Внешний вид"
    @FindBy(css = "a[href$='section_id=General']")
    private WebElement section_GeneralSettings;

    @FindBy(css = "a[href*='section_id=Appearance']")
    private WebElement section_Appearance;

    @FindBy(id = "field___show_prices_taxed_clean_116")
    public WebElement setting_DisplayPricesWithTaxesOnCategoryAndProductPages;

    @FindBy(css = "input[id*='field___thumbnails_gallery']")
    public WebElement setting_ThumbnailsGallery;

    @FindBy(id = "field___in_stock_field_146")
    public WebElement setting_NumberOfAvailableProducts;

    @FindBy(id = "field___product_details_in_tab_288")
    public WebElement setting_ProductDetailsInTab;

    @FindBy(id = "field___default_product_details_view_180")
    private WebElement setting_ProductPageView;

    @FindBy(css = "input[id*='field___enable_quick_view']")
    public WebElement setting_QuickView;


    public void navigateToAppearanceSettings() {
        menu_Settings.click();
        section_GeneralSettings.click();
        section_Appearance.click();
    }

    public void selectSetting_ProductPageView(String value) {
        new Select(setting_ProductPageView).selectByValue(value);
    }


    //Меню "Настройки -- Общие настройки -- Оформить заказ"
    @FindBy(css = "a[href*='section_id=Checkout']")
    private WebElement section_Checkout;

    @FindBy(id = "field___tax_calculation_179")
    public WebElement setting_TaxCalculationMethodBasedOn;

    public void navigateToCheckoutSettings() {
        menu_Settings.click();
        section_GeneralSettings.click();
        section_Checkout.click();
    }

    public void selectSetting_TaxCalculationMethodBasedOn(String value) {
        new Select(setting_TaxCalculationMethodBasedOn).selectByValue(value);
    }


    //Меню "Настройки -- Налоги"
    @FindBy(css = "a[href$='taxes.manage'] div")
    private WebElement section_Taxes;

    @FindBy(xpath = "//input[@type='checkbox'][@name='tax_data[7][price_includes_tax]']")
    public WebElement setting_priceIncludesTax;

    @FindBy(css = "tr[data-ct-tax-id='7'] td")
    public WebElement vat20;

    @FindBy(css = "a[data-ca-dispatch='dispatch[taxes.m_update]']")
    public WebElement button_saveTaxes;

    @FindBy(css = ".bulk-edit__btn.bulk-edit__btn--actions span")
    public WebElement button_Actions;

    @FindBy(css = "a[data-ca-dispatch=\"dispatch[taxes.apply_selected_taxes]\"]")
    public WebElement button_ApplySelectedTaxesToAllProducts;


    public void navigateToTaxes() {
        menu_Settings.click();
        section_Taxes.click();
    }

    public void setTaxesForAllProducts() {   //Настраиваем налог для всех товаров
        navigateToCheckoutSettings();
        selectSetting_TaxCalculationMethodBasedOn("unit_price");
        clickSaveButtonOfSettings();
        navigateToTaxes();
        WebElement checkboxPriceIncludesTax = setting_priceIncludesTax;
        if (checkboxPriceIncludesTax.isSelected()) {
            checkboxPriceIncludesTax.click();
            button_saveTaxes.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            vat20.click();
            (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn-group.bulk-edit__wrapper")));
            button_Actions.click();
            button_ApplySelectedTaxesToAllProducts.click();
        }

        //Настраиваем CS-Cart настройку
        navigateToAppearanceSettings();
        WebElement checkboxDisplayPricesWithTaxesOnCategoryAndProductPages = setting_DisplayPricesWithTaxesOnCategoryAndProductPages;
        if (!checkboxDisplayPricesWithTaxesOnCategoryAndProductPages.isSelected()) {
            checkboxDisplayPricesWithTaxesOnCategoryAndProductPages.click();
            clickSaveButtonOfSettings();
        }
    }


    //Меню "Товары --Товары"
    @FindBy(id = "products_products")
    private WebElement section_Products;


    public ProductSettings navigateToSection_Products() {
        checkMenu_Products_ToBeActive();
        section_Products.click();
        checkPageOnEngLang();
        return new ProductSettings();
    }


    //Меню "Товары -- Категории"
    @FindBy(id = "products_categories")
    private WebElement section_Categories;

    @FindBy(css = ".nav__actions-bar .dropdown-icon--tools")
    public WebElement gearwheelOnCategoryPage;

    @FindBy(css = "a[href*='dispatch=categories.m_add']")
    public WebElement button_AddBulkCategory;

    @FindBy(css = "a[id*='opener_picker_location_category_']")
    WebElement button_CategoryLocation;

    @FindBy(css = ".span3")
    public WebElement field_CategoryName;

    @FindBy(css = ".btn-clone")
    public WebElement button_Clone;

    @FindBy(css = "a[data-ca-dispatch='dispatch[categories.m_add]']")
    public WebElement button_Create;

    @FindBy(xpath = "//span[@class='ui-button-icon ui-icon ui-icon-closethick']")
    WebElement button_CrossOnTop;


    public void navigateToSection_Categories() {
        checkMenu_Products_ToBeActive();
        section_Categories.click();
    }

    public void addNewCategoryLocations_Computers() {
        if (!DriverProvider.getDriver().findElements(By.cssSelector("select[name='categories_data[0][parent_id]']")).isEmpty()) {
            DriverProvider.getDriver().findElement(By.cssSelector("select[name='categories_data[0][parent_id]']")).click();
            DriverProvider.getDriver().findElement(By.cssSelector("option[value=\"166\"]")).click();
            field_CategoryName.click();
            field_CategoryName.sendKeys("AutoTestCategory");
            for (int i = 1; i < 80; i++) {
                button_Clone.click();
            }
            button_Create.click();
        } else {
            button_CategoryLocation.click();
            if (!DriverProvider.getDriver().findElements(By.xpath("//span[text()='Магазин: CS-Cart']")).isEmpty())
                DriverProvider.getDriver().findElement(By.xpath("//span[text()='Магазин: CS-Cart']")).click();
            DriverProvider.getDriver().findElement(By.cssSelector("#category_166")).click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (DriverProvider.getDriver().findElements(By.xpath("//label[text()='AutoTestCategory']")).isEmpty()) {
                DriverProvider.getDriver().findElement(By.cssSelector("#category_166")).click();
                field_CategoryName.click();
                field_CategoryName.sendKeys("AutoTestCategory");
                for (int i = 1; i < 80; i++) {
                    button_Clone.click();
                }
                button_Create.click();
            } else
                button_CrossOnTop.click();
        }
    }

    //Меню "Товары -- Характеристики"
    @FindBy(id = "products_features")
    private WebElement section_Features;

    @FindBy(css = "a[data-ca-external-click-id=\"opener_group18\"]")
    private WebElement featureBrand;

    @FindBy(css = "a[data-ca-external-click-id=\"opener_group23\"]")
    public WebElement feature_HardDrive;

    @FindBy(css = "label[for='elm_feature_description_23']")
    private WebElement field_FeatureDescription_HardDrive;

    @FindBy(css = ".re-button.re-html.re-button-icon")
    private WebElement button_Html_HardDrive;

    @FindBy(css = ".cm-skip-check-item.open")
    private WebElement field_HtmlDescriptionOfFeature;

    @FindBy(css = "input[id='elm_feature_display_on_catalog_18']")
    public WebElement showInProductList;

    @FindBy(id = "elm_feature_display_on_product_18")
    public WebElement showOnFeaturesTab_Brand;

    @FindBy(id = "elm_feature_display_on_header_23")
    public WebElement showInHeaderOnProductPage_HardDisk;

    @FindBy(css = ".buttons-container-picker input[value='Сохранить']")
    public WebElement button_SaveFeature;


    public void navigateToSection_Features() {
        checkMenu_Products_ToBeActive();
        section_Features.click();
        checkPageOnEngLang();
    }

    public void clickAndTypeField_DescriptionOfFeature(String value) {
        field_FeatureDescription_HardDrive.click();
        new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(field_FeatureDescription_HardDrive));
        Actions scroll = new Actions(DriverProvider.getDriver());
        scroll.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(field_FeatureDescription_HardDrive), 0, 50).perform();
        button_Html_HardDrive.click();
        field_HtmlDescriptionOfFeature.click();
        field_HtmlDescriptionOfFeature.clear();
        field_HtmlDescriptionOfFeature.sendKeys(value);
    }

    public void clickFeatureBrand() {
        featureBrand.click();
    }


    //Меню "Модули -- Скачанные модули"
    @FindBy(xpath = "//span[text()='Модули']")
    private WebElement menu_Addons;

    @FindBy(id = "addons_downloaded_add_ons")
    private WebElement section_DownloadedAddons;

    @FindBy(xpath = "//tr[@id='addon_abt__unitheme2']//button[@class='btn dropdown-toggle']")
    private WebElement themeSectionsOnPage_DownloadedAddons;

    @FindBy(css = "div[class='btn-group dropleft open'] a[href$='abt__ut2.settings']")
    private WebElement themeSettings;

    @FindBy(css = ".nav-tabs #products")
    public WebElement tab_Product;

    @FindBy(css = "#product_list")
    private WebElement tab_ProductLists;

    @FindBy(css = "div[class='btn-group dropleft open'] a[href$='abt__ut2.less_settings']")
    private WebElement colorSchemeSettings;


    public void navigateTo_DownloadedAddonsPage() {
        checkMenuToBeActive("dispatch=addons.manage", menu_Addons);
        section_DownloadedAddons.click();
    }

    public ThemeSettings_ProductLists navigateTo_ThemeSettings_tabProductLists() {
        navigateTo_DownloadedAddonsPage();
        themeSectionsOnPage_DownloadedAddons.click();
        themeSettings.click();
        tab_ProductLists.click();
        return new ThemeSettings_ProductLists();
    }

    public ThemeSettings_Product navigateTo_ThemeSettings_tabProduct() {
        navigateTo_DownloadedAddonsPage();
        themeSectionsOnPage_DownloadedAddons.click();
        themeSettings.click();
        tab_Product.click();
        return new ThemeSettings_Product();
    }

    public ColorSchemeSettings navigateTo_ColorSchemeSettings() {
        navigateTo_DownloadedAddonsPage();
        themeSectionsOnPage_DownloadedAddons.click();
        colorSchemeSettings.click();
        return new ColorSchemeSettings();
    }


    //Меню "Веб-сайт -- Темы -- Макеты"
    @FindBy(xpath = "//span[text()='Веб-сайт']")
    private WebElement menu_Website;

    @FindBy(id = "website_themes")
    private WebElement menu_Themes;

    @FindBy(css = ".nav__actions-bar a[href$='block_manager.manage']")
    private WebElement section_Layouts;

    @FindBy(css = "a[href$='block_manager.manage&s_layout=5']")
    public WebElement layout_Light;

    @FindBy(css = "a[href$='block_manager.manage&s_layout=6']")
    public WebElement layout_Lightv2;

    @FindBy(css = "a[href$='block_manager.manage&s_layout=3']")
    public WebElement layout_Default;

    @FindBy(css = ".with-menu.active .dropdown-toggle")
    private WebElement gearwheelOfActiveLayout;

    @FindBy(css = ".with-menu.active a[href*='block_manager.set_default_layout']")
    private WebElement button_makeByDefault;

    @FindBy(xpath = "//a[text()='Домашняя страница']")
    public WebElement layout_TabHomePage;


    //Настройки блока товаров
    @FindBy(css = "a[id^='sw_case_settings_']")
    public WebElement button_SettingsOfTemplate;

    @FindBy(css = "select[id$='_products_template']")
    WebElement setting_BlockTemplate;

    @FindBy(css = "input[id$='_products_properties_item_number']")
    public WebElement checkbox_ShowItemNumber;

    @FindBy(css = "input[id$='_products_properties_number_of_columns']")
    WebElement field_NumberOfColumnsInList;

    @FindBy(css = "select[id$='_products_properties_abt__ut2_loading_type']")
    WebElement setting_LoadingType;

    @FindBy(css = "li[id^='block_contents_'] a")
    public WebElement tabOfBlock_Content;

    @FindBy(css = "select[id$='_content_items_filling']")
    WebElement setting_Filling;

    @FindBy(css = "input[id$='_content_items_properties_items_limit']")
    WebElement field_Limit;

    @FindBy(css = "li[id^='block_settings_']")
    public WebElement tabOfBlock_BlockSettings;

    @FindBy(css = "input[id$='_products_properties_hide_add_to_cart_button']")
    public WebElement checkbox_HideAddToCartButton;

    @FindBy(css = "input[id$='_products_properties_show_price']")
    public WebElement checkbox_ShowPrice;

    @FindBy(css = "input[id$='_products_properties_enable_quick_view']")
    public WebElement checkbox_EnableQuickView;

    @FindBy(css = "input[id$='_products_properties_not_scroll_automatically']")
    public WebElement checkbox_DoNotScrollAutomatically;

    @FindBy(css = "input[id$='_products_properties_item_quantity']")
    WebElement field_ItemQuantity;

    @FindBy(css = "input[id$='_products_properties_outside_navigation']")
    public WebElement checkbox_OutsideNavigation;

    @FindBy(css = "input[name='dispatch[block_manager.update_block]']")
    public WebElement button_saveBlock;


    public void navigateToSection_WebsiteLayouts() {
        checkMenuToBeActive("dispatch=themes.manage", menu_Website);
        menu_Themes.click();
        section_Layouts.click();
    }

    public WebElement hoverGearwheelOfActiveLayout() {
        return gearwheelOfActiveLayout;
    }

    public void setLayoutAsDefault() {
        WebElement element = hoverGearwheelOfActiveLayout();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
        gearwheelOfActiveLayout.click();
        if (!DriverProvider.getDriver().findElements(By.cssSelector(".with-menu.active a[href*='block_manager.set_default_layout']")).isEmpty()) {
            button_makeByDefault.click();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void navigateToBlockSettings(String blockName) {
        DriverProvider.getDriver().findElement(By.cssSelector("div[data-ca-block-name='" + blockName + "'] div[class*='bm-action-properties']")).click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ui-dialog-title")));
    }

    public void selectSetting_BlockTemplate(String value) {
        new Select(setting_BlockTemplate).selectByValue(value);
    }

    public void clickAndType_Field_NumberOfColumnsInList(String value) {
        field_NumberOfColumnsInList.click();
        field_NumberOfColumnsInList.clear();
        field_NumberOfColumnsInList.sendKeys(value);
    }

    public void selectSetting_LoadingType(String value) {
        new Select(setting_LoadingType).selectByValue(value);
    }

    public void selectSetting_Filling(String value) {
        new Select(setting_Filling).selectByValue(value);
    }

    public void clickAndType_Field_Limit(String value) {
        field_Limit.click();
        field_Limit.clear();
        field_Limit.sendKeys(value);
    }

    public void clickAndType_Field_ItemQuantity(String value) {
        field_ItemQuantity.click();
        field_ItemQuantity.clear();
        field_ItemQuantity.sendKeys(value);
    }


    //Меню "Веб-сайт -- Меню"
    @FindBy(id = "website_menus")
    private WebElement menu_WebsiteMenu;


    public void navigateTo_WebsiteMenuPage() {
        checkMenuToBeActive("dispatch=themes.manage", menu_Website);
        menu_WebsiteMenu.click();
    }
}