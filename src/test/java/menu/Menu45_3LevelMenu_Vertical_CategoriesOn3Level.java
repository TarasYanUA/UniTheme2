package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.MenuSettings;
import taras.constants.DriverProvider;

import java.time.Duration;

/*
Работаем с макетом Light v2:
* Добавляем много категорий во второй уровень Fly меню

Горизонтальное меню + Колоночное заполнение + 3-х уровневое меню
+ Количество колонок -- 4
+ Показывать иконки для пунктов меню второго уровня -- да (в данном кейсе настройка бесполезна)
+ Кол-во отображаемых элементов во 2-м уровне меню -- 75
+ Кол-во отображаемых элементов в 3-м уровне меню -- 75
+ Элементы второго уровня -- 30
+ Элементы третьего уровня -- 80
+ Минимальная высота для меню -- 500
*/
public class Menu45_3LevelMenu_Vertical_CategoriesOn3Level extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu45_3LevelMenu_CategoriesOn3Level() {
        //Настраиваем 3-х уровневое меню на странице "Дизайн -- Меню"
        CsCartSettings csCartSettings = new CsCartSettings();
        MenuSettings menuSettings = csCartSettings.navigateToSection_DesignMenu();
        menuSettings.choose_MainMenu.click();
        menuSettings.chooseMenu_Electronics.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        menuSettings.menuTab_ABUniTheme2.click();
        if (!menuSettings.setting_ActivateSettings.isSelected()) {
            menuSettings.setting_ActivateSettings.click();
        }
        if (!menuSettings.setting_Activate3LevelMenu.isSelected()) {
            menuSettings.setting_Activate3LevelMenu.click();
        }
        menuSettings.button_Save3LevelMenu.click();

        //Добавляем категории для Электроники
        csCartSettings.navigateToSection_Categories();
        if(DriverProvider.getDriver().findElements(By.xpath("//a[text() = 'AutoTestCategory']")).isEmpty()) {
            csCartSettings.gearwheelOnCategoryPage.click();
            csCartSettings.button_AddBulkCategory.click();
            csCartSettings.selectCategoryLocation_Computers();
            csCartSettings.clickAndType_Field_CategoryName();
            for(int i = 1; i < 80; i++) {
                csCartSettings.button_Clone.click();
            }
            csCartSettings.button_Create.click();
        }

        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        csCartSettings.navigateToSection_DesignLayouts();
        csCartSettings.layout_Light.click();
        csCartSettings.setLayoutAsDefault();
        menuSettings.gearwheelOfTheBlock_Categories_Light.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        menuSettings.menuSettings_buttonSettings.click();
        menuSettings.selectSetting_FillingType("column_filling");
        menuSettings.selectSetting_MaximumColumns("4");
        if(menuSettings.setting_CompactDisplayView.isSelected()){
            menuSettings.setting_CompactDisplayView.click();
        }
        if(!menuSettings.setting_ShowIconsForMenuItems.isSelected()){
            menuSettings.setting_ShowIconsForMenuItems.click();
        }
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("75");
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("75");
        menuSettings.clickAndType_setting_SecondLevelElements("30");
        menuSettings.clickAndType_setting_ThirdLevelElements("80");
        menuSettings.clickAndType_setting_MinimumHeightForMenu("500");
        menuSettings.tab_Content.click();
        menuSettings.selectMenuContent_MainMenu();
        menuSettings.button_saveBlock.click();
    }
}