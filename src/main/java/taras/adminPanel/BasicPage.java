package taras.adminPanel;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import taras.adminPanel.themeSettings.ThemeSettings_Product;
import taras.adminPanel.themeSettings.ThemeSettings_ProductLists;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;
import taras.storefront.StHomePage;

public class BasicPage extends AbstractPage implements CheckMenuToBeActive {
    public BasicPage() {
        super();
    }

    @FindBy(css = ".btn.btn-primary.cm-submit")
    private WebElement saveButtonOfSettings;

    @FindBy(id = "administration")
    private WebElement menu_Settings;

    @FindBy(id = "products_features")
    WebElement section_Features;

    @FindBy(css = "a[href$='section_id=General']")
    private WebElement section_GeneralSettings;

    @FindBy(css = "a[href*='section_id=Appearance']")
    private WebElement section_Appearance;

    @FindBy(css = "a[href*='section_id=Checkout']")
    private WebElement section_Checkout;

    @FindBy(css = "a[href$='taxes.manage'] div")
    private WebElement section_Taxes;

    @FindBy(id = "products_products")
    private WebElement section_Products;

    @FindBy(id = "products_categories")
    private WebElement section_Categories;

    @FindBy(id = "website_menus")
    private WebElement menu_WebsiteMenu;

    @FindBy(xpath = "//span[text()='Веб-сайт']")
    private WebElement menu_Website;

    @FindBy(id = "website_themes")
    private WebElement menu_Themes;

    @FindBy(xpath = "//span[text()='Маркетинг']")
    private WebElement menu_Marketing;

    @FindBy(css = ".nav__actions-bar a[href$='block_manager.manage']")
    private WebElement section_Layouts;

    @FindBy(css = "#marketing_banners")
    private WebElement section_Banners;


    public void clickSaveButtonOfSettings() {
        saveButtonOfSettings.click();
        UtilsAdm.makePause(1500);
    }

    public StHomePage navigateToStorefront() {
        String currentUrl = DriverProvider.getDriver().getCurrentUrl();
        String[] url = currentUrl.split("admin.php");
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("window.open('" + url[0] + "')");
        return new StHomePage();
    }

    public void navigateToMenuSettings() {
        checkMenuToBeActive("dispatch=addons.manage", menu_Addons);
        menu_Settings.click();
    }

    public FeaturePage navigateToSection_Features() {
        checkMenu_Products_ToBeActive();
        section_Features.click();
        UtilsAdm.checkPageOnEngLang();
        return new FeaturePage();
    }

    public CsCartSettings navigateToAppearanceSettings() {
        navigateToMenuSettings();
        section_GeneralSettings.click();
        section_Appearance.click();
        return new CsCartSettings();
    }

    public void navigateToCheckoutSettings() {
        navigateToMenuSettings();
        section_GeneralSettings.click();
        section_Checkout.click();
    }

    public void navigateToTaxes() {
        navigateToMenuSettings();
        section_Taxes.click();
    }

    public ProductSettings navigateToSection_Products() {
        checkMenu_Products_ToBeActive();
        section_Products.click();
        UtilsAdm.checkPageOnEngLang();
        return new ProductSettings();
    }

    public CategoryPage navigateToSection_Categories() {
        checkMenu_Products_ToBeActive();
        section_Categories.click();
        return new CategoryPage();
    }

    public void navigateTo_WebsiteMenuPage() {
        checkMenuToBeActive("dispatch=themes.manage", menu_Website);
        menu_WebsiteMenu.click();
    }

    public LayoutPage navigateToSection_WebsiteLayouts() {
        checkMenuToBeActive("dispatch=themes.manage", menu_Website);
        menu_Themes.click();
        section_Layouts.click();
        return new LayoutPage();
    }

    public BannerPage navigateToSection_Banners() {
        checkMenuToBeActive("dispatch=promotions.manage", menu_Marketing);
        section_Banners.click();
        return new BannerPage();
    }

    public void selectLanguageForAdminElement(String ruArEn) {
        UtilsAdm.closeAllNotifications();
        DriverProvider.getDriver().findElement(By.cssSelector(".content-variant-wrap--language span")).click();
        DriverProvider.getDriver().findElement(By.cssSelector(".content-variant-wrap--language a[name='" + ruArEn + "']")).click();
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
}