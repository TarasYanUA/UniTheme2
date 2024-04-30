package taras.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;
import taras.storefront.StHomePage;

public class CsCartSettings extends AbstractPage implements CheckPageOnEngLang, CheckMenuToBeActive {
    public CsCartSettings(){
        super();
    }

    @FindBy(css = ".btn.btn-primary.cm-submit")
    private WebElement saveButtonOfSettings;

    public void clickSaveButtonOfSettings(){
        saveButtonOfSettings.click();
    }

    public StHomePage navigateToStorefront(){
        String currentUrl = DriverProvider.getDriver().getCurrentUrl();
        String[] url = currentUrl.split("admin.php");
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("window.open('"+url[0]+"')");
        return new StHomePage();
    }


    @FindBy(xpath = "//span[text()='Модули']")
    private WebElement menu_Addons;
    @FindBy(id = "addons_downloaded_add_ons")
    private WebElement menu_DownloadedAddons;

    public void navigateTo_DownloadedAddonsPage() {
        checkMenuToBeActive(menu_Addons);
        menu_DownloadedAddons.click();
    }

    //Меню "Настройки"
    @FindBy(id = "administration")
    private WebElement menu_Settings;
    @FindBy(css = "a[href$='section_id=General']")
    private WebElement section_GeneralSettings;

    //Меню "Настройки -- Внешний вид"
    @FindBy(css = "a[href$='section_id=Appearance']")
    private WebElement section_Appearance;
    @FindBy(css = "input[id*='field___enable_quick_view']")
    public WebElement setting_QuickView;
    @FindBy(css = "input[id*='field___thumbnails_gallery']")
    public WebElement setting_ThumbnailsGallery;
    @FindBy(id = "field___in_stock_field_146")
    public WebElement setting_NumberOfAvailableProducts;
    @FindBy(id = "field___product_details_in_tab_288")
    public WebElement setting_ProductDetailsInTab;

    public void navigateToAppearanceSettings(){
        menu_Settings.click();
        section_GeneralSettings.click();
        section_Appearance.click();
    }

    //Меню "Товары --Товары"
    @FindBy(xpath = "//span[text()='Товары']")
    private WebElement menu_Products;
    @FindBy(id = "products_products")
    private WebElement section_Products;

    public ProductSettings navigateToSection_Products(){
        checkMenuToBeActive(menu_Products);
        section_Products.click();
        checkPageOnEngLang();
        return new ProductSettings();
    }

    //Меню "Товары -- Категории"
    @FindBy(id = "products_categories")
    private WebElement section_Categories;
    @FindBy(css = ".nav__actions-bar .dropdown-icon--tools")
    public WebElement gearwheelOnCategoryPage;
    @FindBy(css = "a[href$='categories.m_add']")
    public WebElement button_AddBulkCategory;
    @FindBy(css = "select[name='categories_data[0][parent_id]']")
    public WebElement categoryLocation;
    @FindBy(css = ".span3")
    public WebElement field_CategoryName;
    @FindBy(css = ".btn-clone")
    public WebElement button_Clone;
    @FindBy(css = "a[data-ca-dispatch='dispatch[categories.m_add]']")
    public WebElement button_Create;

    public void navigateToSection_Categories(){
        checkMenuToBeActive(menu_Products);
        section_Categories.click();
    }

    private Select getCategoryLocation(){return new Select(categoryLocation);}
    public void selectCategoryLocation_Computers(){
        getCategoryLocation().selectByValue("167");
    }

    public void clickAndType_Field_CategoryName(){
        field_CategoryName.click();
        field_CategoryName.sendKeys("AutoTestCategory");
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
    @FindBy(css = ".controls .redactor-layer p")
    private WebElement field_DescriptionOfFeature;
    @FindBy(css = "input[id='elm_feature_display_on_catalog_18']")
    public WebElement showInProductList;
    @FindBy(id = "elm_feature_display_on_header_18")
    public WebElement showInHeaderOnProductPage_Brand;
    @FindBy(id = "elm_feature_display_on_header_23")
    public WebElement showInHeaderOnProductPage_HardDisk;
    @FindBy(css = "input[value='Сохранить']")
    public WebElement button_SaveFeature;

    public void navigateToSection_Features(){
        checkMenuToBeActive(menu_Products);
        section_Features.click();
        checkPageOnEngLang();
    }
    public WebElement hoverFeatureDescription_HardDrive(){return field_FeatureDescription_HardDrive;}
    public void scrollToFeatureDescription_HardDrive(){
        WebElement element = hoverFeatureDescription_HardDrive();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.scrollToElement(element);
        hover.perform();
        field_FeatureDescription_HardDrive.click();
    }
    public void clickAndTypeField_DescriptionOfFeature(String value){
        field_DescriptionOfFeature.click();
        field_DescriptionOfFeature.clear();
        field_DescriptionOfFeature.sendKeys(value);
    }
    public void clickFeatureBrand(){
        featureBrand.click();
    }


    //Меню "Модули -- Скачанные модули"
    @FindBy(xpath = "//tr[@id='addon_abt__unitheme2']//button[@class='btn dropdown-toggle']")
    private WebElement themeSectionsOnPage_DownloadedAddons;
    @FindBy(css = "div[class=\"btn-group dropleft open\"] a[href$='abt__ut2.settings']")
    private WebElement themeSettings;
    @FindBy(css = ".nav-tabs #products")
    public WebElement tab_Product;
    @FindBy(css = "#product_list")
    private WebElement tab_ProductLists;
    @FindBy(css = "div[class=\"btn-group dropleft open\"] a[href$='abt__ut2.less_settings']")
    private WebElement colorSchemeSettings;

    public ThemeSettings_ProductLists navigateTo_ThemeSettings_tabProductLists(){
        navigateTo_DownloadedAddonsPage();
        themeSectionsOnPage_DownloadedAddons.click();
        themeSettings.click();
        tab_ProductLists.click();
        return new ThemeSettings_ProductLists();
    }
    public ThemeSettings_Product navigateTo_ThemeSettings_tabProduct(){
        navigateTo_DownloadedAddonsPage();
        themeSectionsOnPage_DownloadedAddons.click();
        themeSettings.click();
        tab_Product.click();
        return new ThemeSettings_Product();
    }
    public ColorSchemeSettings navigateTo_ColorSchemeSettings(){
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

    public void navigateToSection_WebsiteLayouts(){
        checkMenuToBeActive(menu_Website);
        menu_Themes.click();
        section_Layouts.click();
    }

    public WebElement hoverGearwheelOfActiveLayout(){return gearwheelOfActiveLayout;}
    public void setLayoutAsDefault(){
        WebElement element = hoverGearwheelOfActiveLayout();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
        gearwheelOfActiveLayout.click();
        if(!DriverProvider.getDriver().findElements(By.cssSelector(".with-menu.active a[href*='block_manager.set_default_layout']")).isEmpty()){
            button_makeByDefault.click();
            try { Thread.sleep(1500);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    //Меню "Веб-сайт -- Меню"
    @FindBy(id = "website_menus")
    private WebElement menu_WebsiteMenu;

    public void navigateTo_WebsiteMenuPage(){
        checkMenuToBeActive(menu_Website);
        menu_WebsiteMenu.click();
    }
}