package taras.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

public class LayoutPage extends AbstractPage {
    public LayoutPage() {super();}

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


    public void setLayoutAsDefault() {
        UtilsAdm.hoverNavigateAndClick(gearwheelOfActiveLayout);
        if (!DriverProvider.getDriver().findElements(By.cssSelector(".with-menu.active a[href*='block_manager.set_default_layout']")).isEmpty()) {
            button_makeByDefault.click();
            UtilsAdm.makePause(1500);
        }
    }


    //Настройки блока товаров
    @FindBy(css = "a[id^='sw_case_settings_']")
    public WebElement button_SettingsOfTemplate;

    @FindBy(css = "select[id$='_products_template']")
    public WebElement setting_BlockTemplate;

    @FindBy(css = "input[id$='_products_properties_item_number']")
    public WebElement checkbox_ShowItemNumber;

    @FindBy(css = "input[id$='_products_properties_number_of_columns']")
    public WebElement field_NumberOfColumnsInList;

    @FindBy(css = "select[id$='_products_properties_abt__ut2_loading_type']")
    public WebElement setting_LoadingType;

    @FindBy(css = "li[id^='block_contents_'] a")
    public WebElement tabOfBlock_Content;

    @FindBy(css = "select[id$='_content_items_filling']")
    public WebElement setting_Filling;

    @FindBy(css = "input[id$='_content_items_properties_items_limit']")
    public WebElement field_Limit;

    @FindBy(css = "li[id^='block_settings_']")
    public WebElement tabOfBlock_Settings;

    @FindBy(css = "input[id$='_products_properties_hide_add_to_cart_button']")
    public WebElement checkbox_HideAddToCartButton;

    @FindBy(css = "input[id$='_products_properties_show_price']")
    public WebElement checkbox_ShowPrice;

    @FindBy(css = "input[id$='_products_properties_enable_quick_view']")
    public WebElement checkbox_EnableQuickView;

    @FindBy(css = "input[id$='_products_properties_not_scroll_automatically']")
    public WebElement checkbox_DoNotScrollAutomatically;

    @FindBy(css = "input[id$='_products_properties_item_quantity']")
    public WebElement field_ItemQuantity;

    @FindBy(css = "input[id$='_products_properties_outside_navigation']")
    public WebElement checkbox_OutsideNavigation;

    @FindBy(css = "input[name='dispatch[block_manager.update_block]']")
    public WebElement button_saveBlock;


    public void navigateTo_BlockSettings(String blockName) {
        DriverProvider.getDriver().findElement(By.cssSelector("div[data-ca-block-name='" + blockName + "'] div[class*='bm-action-properties']")).click();
        UtilsAdm.waitForTitleBarWindow();
    }
}