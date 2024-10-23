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
Горизонтальное меню + Колоночное заполнение + 5 колонок
+ Показывать иконки для пунктов меню второго уровня -- нет
+ Кол-во отображаемых элементов во 2-м уровне меню -- 2
+ Кол-во отображаемых элементов в 3-м уровне меню -- 10
+ Элементы второго уровня -- 12
+ Элементы третьего уровня -- 4
+ Минимальная высота для меню -- 500
*/

public class Menu11_Horizontal_ColumnFilling_5columns extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu11_Horizontal_ColumnFilling_5columns(){
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_WebsiteLayouts();
        csCartSettings.layout_Lightv2.click();
        csCartSettings.setLayoutAsDefault();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.gearwheelOfTheBlock_MainMenu_LightV2.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        mainMenuSettings.menuSettings_buttonSettings.click();
        mainMenuSettings.selectSetting_FillingType("column_filling");
        mainMenuSettings.selectSetting_MaximumColumns("5");
        if(mainMenuSettings.setting_CompactDisplayView.isSelected()){   //Выключаем Компактный вид для Горизонтального меню
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        if(mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()){
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("2");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("10");
        mainMenuSettings.clickAndType_setting_SecondLevelElements("12");
        mainMenuSettings.clickAndType_setting_ThirdLevelElements("4");
        mainMenuSettings.clickAndType_setting_MinimumHeightForMenu("500");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu11_Horizontal_ColumnFilling_5columns")
    public void check_Menu11_Horizontal_ColumnFilling_5columns(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.navigateToHorizontalMenu_AllProducts();
        takeScreenShot("Menu11.00 Menu11_Horizontal_ColumnFilling_5columns - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();
        //Проверяем, что у меню Колоночное заполнение
        softAssert.assertTrue(assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Column!");
        //Проверяем, что колонок 5
        softAssert.assertTrue(!assertsOfMenu.fiveColumns.isEmpty(),
                "Menu columns are not equal 5 columns!");
        stHomePage.navigateToHorizontalMenu_Electronic();
        takeScreenShot("Menu11.02 Menu11_Horizontal_ColumnFilling_5columns - Menu Electronic");
        //Проверяем, что у меню второго уровня отсутствуют иконки
        softAssert.assertTrue(assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are icons at the menu of the second level but shouldn't!");
        //Проверяем, что Кол-во отображаемых элементов в 3-м уровне меню -- 2
        softAssert.assertTrue(!assertsOfMenu.numberOfElementsIn3levelMenu_Two.isEmpty(),
                "'Number of visible elements in the 3-level menu' is not 2!");
        //Проверяем, что Элементов второго уровня -- 7
        softAssert.assertTrue(assertsOfMenu.numberOfElements_SecondLevel.size() == 7,
                "Number of elements of the 2-level is not 7!");
        //Проверяем, что Элементов третьего уровня -- 4
        softAssert.assertTrue(assertsOfMenu.numberOfElements_ThirdLevel.size() == 4,
                "Number of elements of the third level is not 4!");
        //Проверяем, что присутствует не меньше 10 кнопок "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(assertsOfMenu.button_MoreInElementsOf2levelMenu.size() >= 10,
                "There are less than 10 buttons 'More' in the elements of the second level of the menu!");
        stHomePage.navigateToHorizontalMenu_Apparel();
        takeScreenShot("Menu11.04 Menu11_Horizontal_ColumnFilling_5columns - Menu Apparel");
        stHomePage.navigateToHorizontalMenu_SportsAndOutdoors();
        takeScreenShot("Menu11.06 Menu11_Horizontal_ColumnFilling_5columns - Menu SportsAndOutdoors");
        stHomePage.navigateToHorizontalMenu_VideoGames();
        takeScreenShot("Menu11.08 Menu11_Horizontal_ColumnFilling_5columns - Menu VideoGames");

        stHomePage.selectLanguage_RTL();
        stHomePage.navigateToHorizontalMenu_Electronic();
        takeScreenShot("Menu11.10 Menu11_Horizontal_ColumnFilling_5columns - Menu Electronic (RTL)");
        stHomePage.navigateToHorizontalMenu_Apparel();
        takeScreenShot("Menu11.12 Menu11_Horizontal_ColumnFilling_5columns - Menu Apparel (RTL)");
        stHomePage.navigateToHorizontalMenu_SportsAndOutdoors();
        takeScreenShot("Menu11.14 Menu11_Horizontal_ColumnFilling_5columns - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToHorizontalMenu_VideoGames();
        takeScreenShot("Menu11.16 Menu11_Horizontal_ColumnFilling_5columns - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}