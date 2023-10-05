package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.MenuSettings;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOfMenu;
import taras.storefront.StHomePage;
import java.time.Duration;

/*
Работаем с макетом Light v2:
Горизонтальное меню + Колоночное заполнение + 2 колонки
+ Показывать иконки для пунктов меню второго уровня -- да
+ Кол-во отображаемых элементов во 2-м уровне меню -- 5 (здесь данная настройка роли не играет по причине настройки 'Элементы третьего уровня')
+ Кол-во отображаемых элементов в 3-м уровне меню -- 10 (здесь эту настройку не проверяем)
+ Элементы второго уровня -- 5
+ Элементы третьего уровня -- 0
+ Минимальная высота для меню -- 700
*/

public class Menu13_Horizontal_ColumnFilling_2columns extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu13_Horizontal_ColumnFilling_2columns(){
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_DesignLayouts();
        csCartSettings.layout_Lightv2.click();
        csCartSettings.setLayoutAsDefault();
        MenuSettings menuSettings = new MenuSettings();
        menuSettings.gearwheelOfTheBlock_MainMenu_LightV2.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        menuSettings.menuSettings_buttonSettings.click();
        menuSettings.selectSetting_FillingType("column_filling");
        menuSettings.selectSetting_MaximumColumns("2");
        if(menuSettings.setting_CompactDisplayView.isSelected()){   //Выключаем Компактный вид для Горизонтального меню
            menuSettings.setting_CompactDisplayView.click();
        }
        if(!menuSettings.setting_ShowIconsForMenuItems.isSelected()){
            menuSettings.setting_ShowIconsForMenuItems.click();
        }
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("5");
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("10");
        menuSettings.clickAndType_setting_SecondLevelElements("5");
        menuSettings.clickAndType_setting_ThirdLevelElements("0");
        menuSettings.clickAndType_setting_MinimumHeightForMenu("700");
        menuSettings.tab_Content.click();
        menuSettings.selectMenuContent_MainMenu();
        menuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu13_Horizontal_ColumnFilling_2columns")
    public void check_Menu13_Horizontal_ColumnFilling_2columns(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.navigateToHorizontalMenu_AllProducts();
        takeScreenShot("Menu13.00 Menu13_Horizontal_ColumnFilling_2columns - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();
        //Проверяем, что у меню Колоночное заполнение
        softAssert.assertTrue(assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Column!");
        //Проверяем, что колонок 2
        softAssert.assertTrue(!assertsOfMenu.twoColumns.isEmpty(),
                "Menu columns are not equal 2 columns!");
        stHomePage.navigateToHorizontalMenu_Electronic();
        takeScreenShot("Menu13.02 Menu13_Horizontal_ColumnFilling_2columns - Menu Electronic");
        //Проверяем, что у меню второго уровня присутствуют иконки
        softAssert.assertTrue(!assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are no icons at the menu of the second level!");
        //Проверяем, что Элементов второго уровня -- 5
        softAssert.assertTrue(assertsOfMenu.numberOfElements_SecondLevel.size() == 5,
                "Number of elements of the 2-level is not 5!");
        //Проверяем, что Элементов третьего уровня -- 0
        softAssert.assertTrue(assertsOfMenu.numberOfElements_ThirdLevel.isEmpty(),
                "Number of elements of the third level of the menu is more than zero!");
        stHomePage.navigateToHorizontalMenu_Apparel();
        takeScreenShot("Menu13.04 Menu13_Horizontal_ColumnFilling_2columns - Menu Apparel");
        stHomePage.navigateToHorizontalMenu_SportsAndOutdoors();
        takeScreenShot("Menu13.06 Menu13_Horizontal_ColumnFilling_2columns - Menu SportsAndOutdoors");
        stHomePage.navigateToHorizontalMenu_VideoGames();
        takeScreenShot("Menu13.08 Menu13_Horizontal_ColumnFilling_2columns - Menu VideoGames");

        stHomePage.selectLanguage_RTL();
        stHomePage.navigateToHorizontalMenu_Electronic();
        takeScreenShot("Menu13.10 Menu13_Horizontal_ColumnFilling_2columns - Menu Electronic (RTL)");
        stHomePage.navigateToHorizontalMenu_Apparel();
        takeScreenShot("Menu13.12 Menu13_Horizontal_ColumnFilling_2columns - Menu Apparel (RTL)");
        stHomePage.navigateToHorizontalMenu_SportsAndOutdoors();
        takeScreenShot("Menu13.14 Menu13_Horizontal_ColumnFilling_2columns - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToHorizontalMenu_VideoGames();
        takeScreenShot("Menu13.16 Menu13_Horizontal_ColumnFilling_2columns - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}