package taras.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

public class MenuSettings extends AbstractPage {
    public MenuSettings(){super();}

    @FindBy(css = "div[data-block-id='150'] .bm-action-properties.action")
    public WebElement gearwheelOfTheBlock_MainMenu;
    @FindBy(css = "div[data-block-id='145'] .bm-action-properties.action")
    public WebElement gearwheelOfTheBlock_Categories;
    @FindBy(css = "a[id*='sw_case_settings_']")
    public WebElement menuSettings_buttonSettings;
    @FindBy(css = "select[name='block_data[properties][abt__ut2_filling_type]']")
    private WebElement setting_FillingType;
    public Select getSetting_FillingType(){return new Select(setting_FillingType);}
    public void selectSetting_FillingType(String value){
        getSetting_FillingType().selectByValue(value);
    }
    @FindBy(css = "select[name='block_data[properties][abt__ut2_columns_count]']")
    private WebElement setting_MaximumColumns;
    public Select getSetting_MaximumColumns(){return new Select(setting_MaximumColumns);}
    public void selectSetting_MaximumColumns(String value){
        getSetting_MaximumColumns().selectByValue(value);
    }
    @FindBy(css = "input[type='checkbox'][name='block_data[properties][abt__menu_compact_view]']")
    public WebElement setting_CompactDisplayView;
    @FindBy(css = "input[type='checkbox'][name='block_data[properties][abt_menu_icon_items]']")
    public WebElement setting_ShowIconsForMenuItems;
    @FindBy(css = "input[name='block_data[properties][no_hidden_elements_second_level_view]']")
    private WebElement setting_NumberOfVisibleElementsIn_2LevelMenu;
    public void clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu(String value){
        setting_NumberOfVisibleElementsIn_2LevelMenu.click();
        setting_NumberOfVisibleElementsIn_2LevelMenu.clear();
        setting_NumberOfVisibleElementsIn_2LevelMenu.sendKeys(value);
    }
    @FindBy(css = "input[name='block_data[properties][elements_per_column_third_level_view]']")
    private WebElement setting_NumberOfVisibleElementsIn_3LevelMenu;
    public void clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu(String value){
        setting_NumberOfVisibleElementsIn_3LevelMenu.click();
        setting_NumberOfVisibleElementsIn_3LevelMenu.clear();
        setting_NumberOfVisibleElementsIn_3LevelMenu.sendKeys(value);
    }
    @FindBy(css = "input[name='block_data[properties][dropdown_second_level_elements]']")
    private WebElement setting_SecondLevelElements;
    public void clickAndType_setting_SecondLevelElements(String value) {
        setting_SecondLevelElements.click();
        setting_SecondLevelElements.clear();
        setting_SecondLevelElements.sendKeys(value);
    }
    @FindBy(css = "input[name='block_data[properties][dropdown_third_level_elements]']")
    private WebElement setting_ThirdLevelElements;
    public void clickAndType_setting_ThirdLevelElements(String value) {
        setting_ThirdLevelElements.click();
        setting_ThirdLevelElements.clear();
        setting_ThirdLevelElements.sendKeys(value);
    }
    @FindBy(css = "input[name='block_data[properties][abt__ut2_menu_min_height]']")
    private WebElement setting_MinimumHeightForMenu;
    public void clickAndType_setting_MinimumHeightForMenu(String value) {
        setting_MinimumHeightForMenu.click();
        setting_MinimumHeightForMenu.clear();
        setting_MinimumHeightForMenu.sendKeys(value);
    }
    @FindBy(css = "li[id^='block_contents_'] a")
    public WebElement tab_Content;
    @FindBy(css = "select[id$='_content_menu']")
    private WebElement MenuContent;
    public void selectMenuContent_MainMenu(){
        MenuContent.click();
        DriverProvider.getDriver().findElement(By.xpath("//option[contains(text(), 'Main menu')]")).click();
    }
    @FindBy(css = "input[name='dispatch[block_manager.update_block]']")
    public WebElement button_saveBlock;
}