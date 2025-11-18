package taras.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

public class MainMenuSettings extends AbstractPage {
    public MainMenuSettings() {
        super();
    }

    @FindBy(css = "div[data-ca-block-name='Главное меню'] .bm-action-properties.action")
    public WebElement gearwheelOfTheBlock_MainMenu_LightV2;

    @FindBy(css = "div[data-ca-block-name='Каталог товаров'] .bm-action-properties.action")
    public WebElement gearwheelOfTheBlock_Categories_Light;

    @FindBy(css = "a[id*='sw_case_settings_']")
    public WebElement menuSettings_buttonSettings;

    @FindBy(css = "select[name='block_data[properties][abt__ut2_filling_type]']")
    public WebElement setting_FillingType;

    @FindBy(css = "select[name='block_data[properties][abt__ut2_columns_count]']")
    public WebElement setting_MaximumColumns;

    @FindBy(css = "input[type='checkbox'][name='block_data[properties][abt__menu_compact_view]']")
    public WebElement setting_CompactDisplayView;

    @FindBy(css = "input[type='checkbox'][name='block_data[properties][abt_menu_icon_items]']")
    public WebElement setting_ShowIconsForMenuItems;

    @FindBy(css = "input[name='block_data[properties][abt__no_hidden_elements_third_level_view]")
    private WebElement setting_NumberOfVisibleElementsInThirdLevelOfMenu;

    @FindBy(css = "input[name='block_data[properties][dropdown_second_level_elements]']")
    public WebElement setting_SecondLevelElements;

    @FindBy(css = "input[name='block_data[properties][dropdown_third_level_elements]']")
    public WebElement setting_ThirdLevelElements;

    @FindBy(css = "input[name='block_data[properties][abt__ut2_menu_min_height]']")
    public WebElement setting_MinimumHeightForMenu;

    @FindBy(css = "li[id^='block_contents_'] a")
    public WebElement tab_Content;

    @FindBy(css = "select[id$='_content_menu']")
    private WebElement field_menuContent;

    @FindBy(css = "input[name='dispatch[block_manager.update_block]']")
    public WebElement button_saveBlock;


    public void clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu(String value) {
        Actions actions = new Actions(DriverProvider.getDriver());
        actions.moveToElement(setting_NumberOfVisibleElementsInThirdLevelOfMenu).build().perform();
        UtilsAdm.clickAndType(setting_NumberOfVisibleElementsInThirdLevelOfMenu, value);
    }

    public void selectMenuContent_MainMenu() {
        field_menuContent.click();
        DriverProvider.getDriver().findElement(By.xpath("//option[contains(text(), 'Main menu')]")).click();
    }

    public void gearwheelOfTheBlock_FlyMenu_Default() {
        if (!DriverProvider.getDriver().findElements(By.cssSelector("div[data-ca-block-name='Меню'] .bm-action-properties.action")).isEmpty()) {
            DriverProvider.getDriver().findElement(By.cssSelector("div[data-ca-block-name='Меню'] .bm-action-properties.action")).click();
        } else {
            DriverProvider.getDriver().findElement(By.cssSelector("div[data-ca-block-name='AB: FLY меню'] .bm-action-properties.action")).click();
        }
        UtilsAdm.waitForTitleBarWindow();
    }


    //Настройки меню на странице "Веб-сайт -- Меню"
    @FindBy(xpath = "//a[contains(text(), 'AB: Main menu')]")
    public WebElement choose_MainMenu;

    @FindBy(xpath = "//a[text()='Электроника']")
    public WebElement chooseMenu_Electronics;

    @FindBy(xpath = "//div[contains(@class, 'ui-dialog-content')]//a[text()='AB: UniTheme2']")
    public WebElement menuTab_ABUniTheme2;

    @FindBy(css = ".ui-dialog-content input[id*='abt__ut2_mwi__status_']")
    public WebElement setting_ActivateSettings;

    @FindBy(css = ".ui-dialog-content input[id*='abt__ut2_mwi__dropdown_']")
    public WebElement setting_Activate3LevelMenu;

    @FindBy(css = ".ui-dialog-content input[name='dispatch[static_data.update]']")
    public WebElement button_Save3LevelMenu;

    @FindBy(css = "span[id*='on_item_'] .icon-caret-right")
    public WebElement arrowOfCategory;

    @FindBy(xpath = "//div[@class='items-container multi-level longtap-selection']//a[text()='Компьютеры']")
    public WebElement categoryComputers;

    @FindBy(xpath = "//div[@class='items-container multi-level longtap-selection']//a[text()='Computers']")
    public WebElement categoryComputers_RTL;

    @FindBy(css = ".re-icon-html")
    private WebElement button_Html;

    @FindBy(css = ".cm-skip-check-item.open")
    private WebElement field_HtmlContent;

    @FindBy(css = "a[id*='wrap_content'] span")
    private WebElement languageButton;
    
    
    public void addBannerToMenu(WebElement menu, String condition) {
        arrowOfCategory.click();
        menu.click();
        menuTab_ABUniTheme2.click();
        if (DriverProvider.getDriver().findElements(By.cssSelector(condition)).isEmpty()) {
            button_Html.click();
            UtilsAdm.clickAndType(field_HtmlContent,
                    "<p><img src=\"design/themes/abt__unitheme2/media/images/abt__unitheme2/sports-bg-menu.jpg\"></p>");
        }
        button_Save3LevelMenu.click();
    }

    public void selectLanguage(String ruArEn) {
        UtilsAdm.closeAllNotifications();
        languageButton.click();
        DriverProvider.getDriver().findElement(By.cssSelector("div[data-language='" + ruArEn + "']")).click();
    }
}