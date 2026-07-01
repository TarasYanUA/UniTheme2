package taras.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.util.List;

public class LayoutPage extends AbstractPage {
    public LayoutPage() {
        super();
    }

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

    @FindBy(css = "li[id^='create_new_blocks']")
    WebElement tab_CreateNewBlock;

    @FindBy(css = "div[data-ca-block-name='Баннеры'] .select-block-box")
    WebElement newBlockTemplate_Banners;

    @FindBy(id = "block_0_0_banners_name")
    WebElement field_BlockTitle;

    @FindBy(id = "block_0_0_banners_template")
    WebElement blockTemplate;

    @FindBy(css = "a[href*='dispatch=banners.picker']")
    WebElement blockButton_AddBanners;

    @FindBy(id = "elm_name")
    WebElement searchBannerByName;

    @FindBy(css = "input[name='dispatch[banners.picker]']")
    WebElement blockButton_Search;

    @FindBy(css = ".buttons-container-picker input[value='Создать']")
    WebElement blockButton_CreateNewBlock;


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

    public String getLayoutIDByBlockName(String blockName) {
        String layoutID = DriverProvider.getDriver()
                .findElement(By.xpath("//div[@title='" + blockName + "']/../.."))
                .getAttribute("id");

        System.out.println("ID макета: " + layoutID);
        return layoutID;
    }

    public void switchOffAllBlocksAtLayout(String layoutID) {
        WebElement myLayout = DriverProvider.getDriver()
                .findElement(By.id(layoutID));

        List<WebElement> buttons_SwitchOff = myLayout.findElements(By
                .cssSelector("div[data-ca-block-name] div.cm-tooltip.cm-action.bm-action-switch.action:not(.switch-off)"));

        for (WebElement button_SwitchOff : buttons_SwitchOff)
            button_SwitchOff.click();
    }

    public void createNewBlockWithBannerAtLayout(String layoutID, String bannerName) {
        WebElement myLayout = DriverProvider.getDriver()
                .findElement(By.id(layoutID));

        UtilsAdm.hoverNavigateAndClick(myLayout.findElement(By.cssSelector(".cs-icon--type-plus")));
        myLayout.findElement(By.cssSelector(".bm-action-add-block")).click();
        UtilsAdm.waitForPopUpWindow();

        tab_CreateNewBlock.click();
        UtilsAdm.scrollIntoCenter(newBlockTemplate_Banners);
        newBlockTemplate_Banners.click();
        UtilsAdm.waitForSpinnerDisappear();
        UtilsAdm.clickAndType(field_BlockTitle, bannerName);
        new Select(blockTemplate).selectByVisibleText("AB: Расширенный баннер");
        UtilsAdm.makePause(1000);
        tabOfBlock_Content.click();
        blockButton_AddBanners.click();
        UtilsAdm.waitForSpinnerDisappear();
        UtilsAdm.clickAndType(searchBannerByName, bannerName);
        blockButton_Search.click();
        UtilsAdm.waitForSpinnerDisappear();
        DriverProvider.getDriver().findElement(By.cssSelector("div[id*='pagination_objects'] input[name='block_items[]']")).click();
        DriverProvider.getDriver().findElement(By.cssSelector("input[value='Добавить баннеры и закрыть']")).click();
        blockButton_CreateNewBlock.click();
        UtilsAdm.waitForSpinnerDisappear();
    }
}