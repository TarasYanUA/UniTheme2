package menu;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.BasicPage;
import taras.adminPanel.LayoutPage;
import taras.adminPanel.MainMenuSettings;
import taras.storefront.AssertsOfMenu;
import taras.storefront.StHomePage;

/*
Работаем с макетом Light v2:
Горизонтальное меню + Колоночное заполнение + 5 колонок
+ Элементы второго уровня -- 12
+ Элементы третьего уровня -- 4
+ Количество видимых элементов в третьем уровне меню -- 2
+ Показывать иконки для пунктов меню второго уровня -- нет
+ Минимальная высота для меню -- 500
*/

public class Menu11_Horizontal_ColumnFilling_5columns extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu11_Horizontal_ColumnFilling_5columns(){
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Lightv2.click();
        layoutPage.setLayoutAsDefault();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.gearwheelOfTheBlock_MainMenu_LightV2.click();
        taras.adminPanel.UtilsAdm.waitForTitleBarWindow();
        mainMenuSettings.menuSettings_buttonSettings.click();
        new Select(mainMenuSettings.setting_FillingType).selectByValue("column_filling");
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("5");
        if(mainMenuSettings.setting_CompactDisplayView.isSelected()){   //Выключаем Компактный вид для Горизонтального меню
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        if(mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()){
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("2");
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "12");
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "4");
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "500");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu11_Horizontal_ColumnFilling_5columns")
    public void check_Menu11_Horizontal_ColumnFilling_5columns(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_AllProducts);
        takeScreenShot("Menu11.00 Menu11_Horizontal_ColumnFilling_5columns - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню Колоночное заполнение
        softAssert.assertTrue(assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Column!");

        //Проверяем, что колонок 5
        softAssert.assertTrue(!assertsOfMenu.columnsPerRow("5").isEmpty(),
                "Menu columns are not equal 5 columns!");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu11.02 Menu11_Horizontal_ColumnFilling_5columns - Menu Electronic");

        //Проверяем, что у меню второго уровня отсутствуют иконки
        softAssert.assertTrue(assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are icons at the menu of the second level but shouldn't!");

        //Проверяем, что Элементов второго уровня -- 7
        softAssert.assertEquals(assertsOfMenu.numberOfElements_SecondLevel.size(), 7,
                "Number of elements of the 2-level is not 7!");

        //Проверяем, что Элементов третьего уровня -- 4
        softAssert.assertEquals(assertsOfMenu.numberOfElements_ThirdLevel.size(), 4,
                "Number of elements of the third level is not 4!");

        //Проверяем, что Количество видимых элементов в третьем уровне меню -- 2
        softAssert.assertTrue(!assertsOfMenu.numberOfVisibleElementsIn_3levelMenu("2").isEmpty(),
                "'Number of visible elements in the 3-level menu' is not 2!");

        //Проверяем, что присутствует не меньше 10 кнопок "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(assertsOfMenu.button_More_InElementsOf2levelMenu.size() >= 10,
                "There are less than 10 buttons 'More' in the elements of the second level of the menu!");

        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.button_MoreCategory_InTheSecondLevel.isEmpty(),
                "There is no button 'More [category]' in the second level of the menu!");

        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Apparel);
        takeScreenShot("Menu11.04 Menu11_Horizontal_ColumnFilling_5columns - Menu Apparel");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_SportsAndOutdoors);
        takeScreenShot("Menu11.06 Menu11_Horizontal_ColumnFilling_5columns - Menu SportsAndOutdoors");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_VideoGames);
        takeScreenShot("Menu11.08 Menu11_Horizontal_ColumnFilling_5columns - Menu VideoGames");

        stHomePage.selectLanguage("ar");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu11.10 Menu11_Horizontal_ColumnFilling_5columns - Menu Electronic (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Apparel);
        takeScreenShot("Menu11.12 Menu11_Horizontal_ColumnFilling_5columns - Menu Apparel (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_SportsAndOutdoors);
        takeScreenShot("Menu11.14 Menu11_Horizontal_ColumnFilling_5columns - Menu SportsAndOutdoors (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_VideoGames);
        takeScreenShot("Menu11.16 Menu11_Horizontal_ColumnFilling_5columns - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}