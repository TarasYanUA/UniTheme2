package menu;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.BasicPage;
import taras.adminPanel.LayoutPage;
import taras.adminPanel.MainMenuSettings;
import taras.adminPanel.UtilsAdm;
import taras.storefront.AssertsOfMenu;
import taras.storefront.StHomePage;
import taras.storefront.UtilsStorefront;

/*
Работаем с макетом Light v2:
Горизонтальное меню + Колоночное заполнение + 2 колонки
+ Элементы второго уровня -- 5
+ Элементы третьего уровня -- 0
+ Количество видимых элементов в третьем уровне меню -- 5   //Здесь эту настройку не проверяем
+ Показывать иконки для пунктов меню второго уровня -- да
+ Минимальная высота для меню -- 700
*/

public class Menu13_Horizontal_ColumnFilling_2columns extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu13_Horizontal_ColumnFilling_2columns(){
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Lightv2.click();
        layoutPage.setLayoutAsDefault();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.gearwheelOfTheBlock_MainMenu_LightV2.click();
        UtilsAdm.waitForTitleBarWindow();
        mainMenuSettings.menuSettings_buttonSettings.click();
        new Select(mainMenuSettings.setting_FillingType).selectByValue("column_filling");
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("2");
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "5");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "0");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("5");
        if(!mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()){
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        if(mainMenuSettings.setting_CompactDisplayView.isSelected()){   //Выключаем Компактный вид для Горизонтального меню
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "700");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu13_Horizontal_ColumnFilling_2columns")
    public void check_Menu13_Horizontal_ColumnFilling_2columns(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_AllProducts);
        takeScreenShot("Menu13.00 Menu13_Horizontal_ColumnFilling_2columns - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню Колоночное заполнение
        softAssert.assertTrue(assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Column!");

        //Проверяем, что колонок 2
        softAssert.assertTrue(!assertsOfMenu.columnsPerRow("2").isEmpty(),
                "Menu columns are not equal 2 columns!");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu13.02 Menu13_Horizontal_ColumnFilling_2columns - Menu Electronic");

        //Проверяем, что у меню второго уровня присутствуют иконки
        softAssert.assertTrue(!assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are no icons at the menu of the second level!");

        //Проверяем, что Элементов второго уровня -- 5
        softAssert.assertEquals(assertsOfMenu.numberOfElements_SecondLevel.size(), 5,
                "Number of elements of the 2-level is not 5!");

        //Проверяем, что Элементов третьего уровня -- 0
        softAssert.assertTrue(assertsOfMenu.numberOfElements_ThirdLevel.isEmpty(),
                "Number of elements of the third level of the menu is more than zero!");

        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.button_MoreCategory_InTheSecondLevel.isEmpty(),
                "There is no button 'More [category]' in the second level of the menu!");

        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_Apparel);
        takeScreenShot("Menu13.04 Menu13_Horizontal_ColumnFilling_2columns - Menu Apparel");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_SportsAndOutdoors);
        takeScreenShot("Menu13.06 Menu13_Horizontal_ColumnFilling_2columns - Menu SportsAndOutdoors");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_VideoGames);
        takeScreenShot("Menu13.08 Menu13_Horizontal_ColumnFilling_2columns - Menu VideoGames");

        stHomePage.selectLanguage("ar");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu13.10 Menu13_Horizontal_ColumnFilling_2columns - Menu Electronic (RTL)");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_Apparel);
        takeScreenShot("Menu13.12 Menu13_Horizontal_ColumnFilling_2columns - Menu Apparel (RTL)");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_SportsAndOutdoors);
        takeScreenShot("Menu13.14 Menu13_Horizontal_ColumnFilling_2columns - Menu SportsAndOutdoors (RTL)");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_VideoGames);
        takeScreenShot("Menu13.16 Menu13_Horizontal_ColumnFilling_2columns - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}