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
Горизонтальное меню + Колоночное заполнение + 1 колонка
+ Элементы второго уровня -- 3
+ Элементы третьего уровня -- 6
+ Количество видимых элементов в третьем уровне меню -- 5
+ Показывать иконки для пунктов меню второго уровня -- нет
+ Минимальная высота для меню -- 500
*/

public class Menu14_Horizontal_ColumnFilling_1column extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu14_Horizontal_ColumnFilling_1column(){
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
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("1");
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "3");
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "6");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("5");
        if(mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()){
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        if(mainMenuSettings.setting_CompactDisplayView.isSelected()){   //Выключаем Компактный вид для Горизонтального меню
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "500");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu14_Horizontal_ColumnFilling_1column")
    public void check_Menu14_Horizontal_ColumnFilling_1column(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_AllProducts);
        takeScreenShot("Menu14.00 Menu14_Horizontal_ColumnFilling_1column - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню Колоночное заполнение
        softAssert.assertTrue(assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Column!");

        //Проверяем, что присутствует 1 колонка
        softAssert.assertTrue(!assertsOfMenu.columnsPerRow("1").isEmpty(),
                "Menu columns are not equal 1 column!");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu14.02 Menu14_Horizontal_ColumnFilling_1column - Menu Electronic");

        //Проверяем, что у меню второго уровня отсутствуют иконки
        softAssert.assertTrue(assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are icons at the menu of the second level but shouldn't!");

        //Проверяем, что Элементов второго уровня -- 3
        softAssert.assertEquals(assertsOfMenu.numberOfElements_SecondLevel.size(), 3,
                "Number of elements of the 2-level is not 3!");

        //Проверяем, что Элементов третьего уровня -- 6
        softAssert.assertEquals(assertsOfMenu.numberOfElements_ThirdLevel.size(), 6,
                "Number of elements of the third level is not 6!");

        //Проверяем, что Количество видимых элементов в третьем уровне меню -- 5
        softAssert.assertTrue(!assertsOfMenu.numberOfVisibleElementsIn_3levelMenu("5").isEmpty(),
                "'Number of visible elements in the 3-level menu' is not 5!");

        //Проверяем, что присутствует не меньше 3 кнопок "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(assertsOfMenu.button_More_InElementsOf2levelMenu.size() >= 3,
                "There are less than 3 buttons 'More' in the elements of the 2-level menu!");

        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.button_MoreCategory_InTheSecondLevel.isEmpty(),
                "There is no button 'More [category]' in the 2-level menu!");

        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Apparel);
        takeScreenShot("Menu14.04 Menu14_Horizontal_ColumnFilling_1column - Menu Apparel");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_SportsAndOutdoors);
        takeScreenShot("Menu14.06 Menu14_Horizontal_ColumnFilling_1column - Menu SportsAndOutdoors");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_VideoGames);
        takeScreenShot("Menu14.08 Menu14_Horizontal_ColumnFilling_1column - Menu VideoGames");

        stHomePage.selectLanguage("ar");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu14.10 Menu14_Horizontal_ColumnFilling_1column - Menu Electronic (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Apparel);
        takeScreenShot("Menu14.12 Menu14_Horizontal_ColumnFilling_1column - Menu Apparel (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_SportsAndOutdoors);
        takeScreenShot("Menu14.14 Menu14_Horizontal_ColumnFilling_1column - Menu SportsAndOutdoors (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_VideoGames);
        takeScreenShot("Menu14.16 Menu14_Horizontal_ColumnFilling_1column - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}