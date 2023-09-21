package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.MenuSettings;
import taras.constants.DriverProvider;
import taras.storefront.StHomePage;

import java.time.Duration;

/*
Работаем с макетом Light v2:
Горизонтальное меню + Колоночное заполнение + 3-х уровневое меню
+ Количество колонок -- 3
+ Показывать иконки для пунктов меню второго уровня -- да (в данном кейсе настройка бесполезна)
+ Кол-во отображаемых элементов во 2-м уровне меню -- 2
+ Кол-во отображаемых элементов в 3-м уровне меню -- 2 !!!
+ Элементы второго уровня -- 12
+ Элементы третьего уровня -- 5 !!!
+ Минимальная высота для меню -- 500
*/

public class Menu41_3LevelMenu_Horizontal_ColumnFilling extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu41_3LevelMenu_Horizontal_ColumnFilling(){
        //Настраиваем 3-х уровневое меню на странице "Дизайн -- Меню"
        CsCartSettings csCartSettings = new CsCartSettings();
        MenuSettings menuSettings = csCartSettings.navigateToSection_DesignMenu();
        menuSettings.choose_MainMenu.click();
        menuSettings.chooseMenu_Electronics.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        menuSettings.menuTab_ABUniTheme2.click();
        if(!menuSettings.setting_ActivateSettings.isSelected()){
            menuSettings.setting_ActivateSettings.click();
        }
        if(!menuSettings.setting_Activate3LevelMenu.isSelected()){
            menuSettings.setting_Activate3LevelMenu.click();
        }
        menuSettings.button_Save3LevelMenu.click();

        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        csCartSettings.navigateToSection_DesignLayouts();
        csCartSettings.layout_Lightv2.click();
        csCartSettings.setLayoutAsDefault();
        menuSettings.gearwheelOfTheBlock_MainMenu.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        menuSettings.menuSettings_buttonSettings.click();
        menuSettings.selectSetting_FillingType("column_filling");
        menuSettings.selectSetting_MaximumColumns("3");
        if(menuSettings.setting_CompactDisplayView.isSelected()){   //Выключаем Компактный вид для Горизонтального меню
            menuSettings.setting_CompactDisplayView.click();
        }
        if(!menuSettings.setting_ShowIconsForMenuItems.isSelected()){
            menuSettings.setting_ShowIconsForMenuItems.click();
        }
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("2");
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("2");
        menuSettings.clickAndType_setting_SecondLevelElements("12");
        menuSettings.clickAndType_setting_ThirdLevelElements("5");
        menuSettings.clickAndType_setting_MinimumHeightForMenu("500");
        menuSettings.tab_Content.click();
        menuSettings.selectMenuContent_MainMenu();
        menuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu41_3LevelMenu_Horizontal_ColumnFilling")
    public void check_Menu41_3LevelMenu_Horizontal_ColumnFilling(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.navigateToHorizontalMenu_AllProducts();
        takeScreenShot("Menu41.00 Menu41_3LevelMenu_Horizontal_ColumnFilling - Menu AllProducts");
        //Проверяем, что у меню Колоночное заполнение
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(DriverProvider.getDriver().findElements(By.cssSelector(".ty-menu__submenu .row-filling")).size() >=1,
                "Menu filling is not Column!");
        //Проверяем, что колонок 3
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[data-cols-count='3']")).size() >=1,
                "Menu columns are not equal 3 columns!");
        stHomePage.navigateToHorizontalMenu_Electronic();
        takeScreenShot("Menu41.02 Menu41_3LevelMenu_Horizontal_ColumnFilling - Menu Electronic-Computers");
        //Проверяем, что присутствует кнопка "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-more")).size() >=1,
                "There is no any button 'More' in the elements of the 2-level menu");
        //Проверяем, что Элементов второго уровня -- не меньше 7
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[class='second-lvl'][data-elem-index='6']")).size() >=1,
                "Number of elements of the 2-level is less than 7!");
        //Проверяем, что Элементов третьего уровня -- 2
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".second-lvl[data-elem-index='0'] .tree-level-col .ty-menu__submenu-item")).size() ==2,
                "'Third level elements' are not equal 2!"); //Здесь есть ошибка в количестве, оформленная в задаче https://abteam.planfix.com/task/41448 пункт №2
        //Проверяем, что на третьем уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-btn-text")).size() >=1,
                "There is no any button 'More [category]' in the 3-level menu!");
        stHomePage.navigateToMenu_ThreeLevelMenu_CarElectronics();
        takeScreenShot("Menu41.04 Menu41_3LevelMenu_Horizontal_ColumnFilling - Menu Electronic-CarElectronics");

        stHomePage.selectLanguage_RTL();
        stHomePage.navigateToHorizontalMenu_AllProducts();
        takeScreenShot("Menu41.06 Menu41_3LevelMenu_Horizontal_ColumnFilling - Menu AllProducts (RTL)");
        stHomePage.navigateToHorizontalMenu_Electronic();
        takeScreenShot("Menu41.08 Menu41_3LevelMenu_Horizontal_ColumnFilling - Menu Electronic-Computers (RTL)");
        stHomePage.navigateToMenu_ThreeLevelMenu_CarElectronics();
        takeScreenShot("Menu41.10 Menu41_3LevelMenu_Horizontal_ColumnFilling - Menu Electronic-CarElectronics (RTL)");
        softAssert.assertAll();
    }
}