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
Работаем с макетом Light:
Вертикальное меню + Колоночное заполнение + 5 колонок
+ Компактный вид отображения -- нет
+ Показывать иконки для пунктов меню второго уровня -- нет
+ Кол-во отображаемых элементов во 2-м уровне меню -- 0
+ Кол-во отображаемых элементов в 3-м уровне меню -- 10
+ Элементы второго уровня -- 12
+ Элементы третьего уровня -- 6
+ Минимальная высота для меню -- 300
*/

public class Menu21_Vertical_ColumnFilling_5columns_FullView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu21_Vertical_ColumnFilling_5columns_FullView(){
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_WebsiteLayouts();
        csCartSettings.layout_Light.click();
        csCartSettings.setLayoutAsDefault();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.gearwheelOfTheBlock_Categories_Light.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        mainMenuSettings.menuSettings_buttonSettings.click();
        mainMenuSettings.selectSetting_FillingType("column_filling");
        mainMenuSettings.selectSetting_MaximumColumns("5");
        if(mainMenuSettings.setting_CompactDisplayView.isSelected()){
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        if(mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()){
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("0");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("10");
        mainMenuSettings.clickAndType_setting_SecondLevelElements("12");
        mainMenuSettings.clickAndType_setting_ThirdLevelElements("6");
        mainMenuSettings.clickAndType_setting_MinimumHeightForMenu("300");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu21_Vertical_ColumnFilling_5columns_FullView")
    public void check_Menu21_Vertical_ColumnFilling_5columns_FullView(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu21.00 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();
        //Проверяем, что у меню Колоночное заполнение
        softAssert.assertTrue(assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Column!");
        //Проверяем, что колонок 5
        softAssert.assertTrue(!assertsOfMenu.fiveColumns.isEmpty(),
                "Menu columns are not equal 5 columns!");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu21.02 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu Electronic");
        //Проверяем, что у меню второго уровня отсутствуют иконки
        softAssert.assertTrue(assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are icons at the menu of the second level but shouldn't!");
        //Проверяем, что Кол-во отображаемых элементов в 3-м уровне меню --  0
        softAssert.assertTrue(!assertsOfMenu.numberOfElementsIn3levelMenu_Zero.isEmpty(),
                "'Number of visible elements in the 3-level menu' is more than zero!");
        //Проверяем, что Элементов второго уровня -- не меньше 7
        softAssert.assertTrue(assertsOfMenu.numberOfElements_SecondLevel.size() >= 7,
                "Number of elements of the second level is less than 7!");
        //Проверяем, что Элементов третьего уровня -- 6
        softAssert.assertTrue(assertsOfMenu.numberOfElements_ThirdLevel.size() == 6,
                "Number of elements of the third level is not 6!");
        //Проверяем, что присутствует не меньше 10 кнопок "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(assertsOfMenu.button_MoreInElementsOf2levelMenu.size() >= 10,
                "There are less than 10 buttons 'More' in the elements of the second level of the menu!");
        stHomePage.navigateToVerticalMenu_Apparel();
        takeScreenShot("Menu21.04 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu Apparel");
        stHomePage.navigateToVerticalMenu_SportsAndOutdoors();
        takeScreenShot("Menu21.06 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu SportsAndOutdoors");
        stHomePage.navigateToVerticalMenu_VideoGames();
        takeScreenShot("Menu21.08 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu VideoGames");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage_RTL();
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu21.10 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu AllProducts (RTL)");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu21.12 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu Electronic (RTL)");
        stHomePage.navigateToVerticalMenu_Apparel();
        takeScreenShot("Menu21.14 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu Apparel (RTL)");
        stHomePage.navigateToVerticalMenu_SportsAndOutdoors();
        takeScreenShot("Menu21.16 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToVerticalMenu_VideoGames();
        takeScreenShot("Menu21.18 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}