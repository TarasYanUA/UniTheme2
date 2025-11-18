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
Работаем с макетом Light:
Вертикальное меню + Строчное заполнение + 4 колонки
+ Элементы второго уровня -- 30
+ Элементы третьего уровня -- 30
+ Количество видимых элементов в третьем уровне меню -- 5
+ Показывать иконки для пунктов меню второго уровня -- да
+ Компактный вид отображения -- нет
+ Минимальная высота для меню -- 600
*/

public class Menu31_Vertical_RowFilling_4columns_FullView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu31_Vertical_RowFilling_4columns_FullView(){
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Light.click();
        layoutPage.setLayoutAsDefault();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.gearwheelOfTheBlock_Categories_Light.click();
        taras.adminPanel.UtilsAdm.waitForTitleBarWindow();
        mainMenuSettings.menuSettings_buttonSettings.click();
        new Select(mainMenuSettings.setting_FillingType).selectByValue("row_filling");
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("4");
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "30");
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "30");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("5");
        if(!mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()){
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        if(mainMenuSettings.setting_CompactDisplayView.isSelected()){
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "600");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu31_Vertical_RowFilling_4columns_FullView")
    public void check_Menu31_Vertical_RowFilling_4columns_FullView(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu31.00 Menu31_Vertical_RowFilling_4columns_FullView - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню Строчное заполнение
        softAssert.assertTrue(!assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Row!");

        //Проверяем, что колонок 4
        softAssert.assertTrue(!assertsOfMenu.columnsPerRow("4").isEmpty(),
                "Menu columns are not equal 4 columns!");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu31.02 Menu31_Vertical_RowFilling_4columns_FullView - Menu Electronic");

        //Проверяем, что у меню второго уровня присутствуют иконки
        softAssert.assertTrue(!assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are no icons at the menu of the second level!");

        //Проверяем, что Элементов второго уровня -- не меньше 7
        softAssert.assertTrue(assertsOfMenu.numberOfElements_SecondLevel.size() >= 7,
                "Number of elements of the second level is less than 7!");

        //Проверяем, что Элементов третьего уровня -- не меньше 7
        softAssert.assertTrue(assertsOfMenu.numberOfElements_ThirdLevel.size() >= 7,
                "Number of elements of the third level is less than 7!");

        //Проверяем, что Кол-во отображаемых элементов в 3-м уровне меню -- 5
        softAssert.assertTrue(!assertsOfMenu.numberOfVisibleElementsIn_3levelMenu("5").isEmpty(),
                "'Number of visible elements in the 3-level menu' is not 5!");

        //Проверяем, что присутствует кнопка "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(!assertsOfMenu.button_More_InElementsOf2levelMenu.isEmpty(),
                "There are no buttons 'More' in the elements of the 2-level menu!");

        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Apparel);
        takeScreenShot("Menu31.04 Menu31_Vertical_RowFilling_4columns_FullView - Menu Apparel");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_SportsAndOutdoors);
        takeScreenShot("Menu31.06 Menu31_Vertical_RowFilling_4columns_FullView - Menu SportsAndOutdoors");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_VideoGames);
        takeScreenShot("Menu31.08 Menu31_Vertical_RowFilling_4columns_FullView - Menu VideoGames");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage("ar");
        stHomePage.verticalMenu_menuButton_Categories.click();
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu31.10 Menu31_Vertical_RowFilling_4columns_FullView - Menu AllProducts (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu31.12 Menu31_Vertical_RowFilling_4columns_FullView - Menu Electronic (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Apparel);
        takeScreenShot("Menu31.14 Menu31_Vertical_RowFilling_4columns_FullView - Menu Apparel (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_SportsAndOutdoors);
        takeScreenShot("Menu31.16 Menu31_Vertical_RowFilling_4columns_FullView - Menu SportsAndOutdoors (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_VideoGames);
        takeScreenShot("Menu31.18 Menu31_Vertical_RowFilling_4columns_FullView - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}