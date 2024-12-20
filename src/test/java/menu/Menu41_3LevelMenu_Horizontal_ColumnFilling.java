package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.MainMenuSettings;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOfMenu;
import taras.storefront.StHomePage;

import java.time.Duration;

/*
Работаем с макетом Light v2:
Горизонтальное меню + Колоночное заполнение + 3-х уровневое меню
+ Количество колонок -- 3
+ Показывать иконки для пунктов меню второго уровня -- да (в данном кейсе настройка бесполезна)
+ Кол-во отображаемых элементов во 2-м уровне меню -- 2
+ Кол-во отображаемых элементов в 3-м уровне меню -- 2
+ Элементы второго уровня -- 12
+ Элементы третьего уровня -- 5
+ Минимальная высота для меню -- 500
*/

public class Menu41_3LevelMenu_Horizontal_ColumnFilling extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu41_3LevelMenu_Horizontal_ColumnFilling(){
        //Настраиваем 3-х уровневое меню на странице "Дизайн -- Меню"
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateTo_WebsiteMenuPage();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.choose_MainMenu.click();
        mainMenuSettings.chooseMenu_Electronics.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        mainMenuSettings.menuTab_ABUniTheme2.click();
        if(!mainMenuSettings.setting_ActivateSettings.isSelected()){
            mainMenuSettings.setting_ActivateSettings.click();
        }
        if(!mainMenuSettings.setting_Activate3LevelMenu.isSelected()){
            mainMenuSettings.setting_Activate3LevelMenu.click();
        }
        mainMenuSettings.button_Save3LevelMenu.click();

        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        csCartSettings.navigateToSection_WebsiteLayouts();
        csCartSettings.layout_Lightv2.click();
        csCartSettings.setLayoutAsDefault();
        mainMenuSettings.gearwheelOfTheBlock_MainMenu_LightV2.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        mainMenuSettings.menuSettings_buttonSettings.click();
        mainMenuSettings.selectSetting_FillingType("column_filling");
        mainMenuSettings.selectSetting_MaximumColumns("3");
        if(mainMenuSettings.setting_CompactDisplayView.isSelected()){   //Выключаем Компактный вид для Горизонтального меню
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        if(!mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()){
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("2");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("2");
        mainMenuSettings.clickAndType_setting_SecondLevelElements("12");
        mainMenuSettings.clickAndType_setting_ThirdLevelElements("5");
        mainMenuSettings.clickAndType_setting_MinimumHeightForMenu("500");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu41_3LevelMenu_Horizontal_ColumnFilling")
    public void check_Menu41_3LevelMenu_Horizontal_ColumnFilling(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.navigateToHorizontalMenu_AllProducts();
        takeScreenShot("Menu41.00 Menu41_3LevelMenu_Horizontal_ColumnFilling - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();
        //Проверяем, что у меню Колоночное заполнение
        softAssert.assertTrue(assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Column!");
        //Проверяем, что колонок 3
        softAssert.assertTrue(!assertsOfMenu.threeColumns.isEmpty(),
                "Menu columns are not equal 3 columns!");
        stHomePage.navigateToHorizontalMenu_Electronic();
        takeScreenShot("Menu41.02 Menu41_3LevelMenu_Horizontal_ColumnFilling - Menu Electronic-Computers");
        //Проверяем, что Кол-во отображаемых элементов в 3-м уровне меню -- 2
        softAssert.assertTrue(!assertsOfMenu.numberOfElementsIn3levelMenu_Two.isEmpty(),
                "'Number of visible elements in the 3-level menu' is not 2!");
        //Проверяем, что Элементов второго уровня -- не меньше 7
        softAssert.assertTrue(assertsOfMenu.numberOfElements_SecondLevel.size() >= 7,
                "Number of elements of the second level is less than 7!");
        //Проверяем, что Элементов третьего уровня -- 4
        softAssert.assertTrue(assertsOfMenu.threeLevelMenu_elementsInThirdLevel.size() == 4,
                "'Third level elements' are not equal 4!");
        //Проверяем, что присутствует кнопка "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(!assertsOfMenu.button_MoreInElementsOf2levelMenu.isEmpty(),
                "There are no buttons 'More' in the elements of the 2-level menu!");
        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.button_MoreCategoryInTheSecondLevel.isEmpty(),
                "There is no button 'More [category]' in the second level of the menu!");
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