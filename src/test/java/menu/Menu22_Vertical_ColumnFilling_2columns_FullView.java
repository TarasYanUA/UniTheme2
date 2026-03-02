package menu;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import taras.adminPanel.BasicPage;
import taras.adminPanel.LayoutPage;
import taras.adminPanel.MainMenuSettings;
import taras.adminPanel.UtilsAdm;
import taras.asserts.Asserts_Menu;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

/*
Работаем с макетом Light:
Вертикальное меню + Колоночное заполнение + 2 колонки
+ Элементы второго уровня -- 5 (здесь данная настройка роли не играет по причине настройки ниже)
+ Элементы третьего уровня -- 0
+ Количество видимых элементов в третьем уровне меню -- 10  (здесь эту настройку не проверяем)
+ Показывать иконки для пунктов меню второго уровня -- да
+ Компактный вид отображения -- нет
+ Минимальная высота для меню -- 700
*/

public class Menu22_Vertical_ColumnFilling_2columns_FullView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu22_Vertical_ColumnFilling_2columns_FullView(){
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Light.click();
        layoutPage.setLayoutAsDefault();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.gearwheelOfTheBlock_Categories_Light.click();
        UtilsAdm.waitForTitleBarWindow();
        mainMenuSettings.menuSettings_buttonSettings.click();
        new Select(mainMenuSettings.setting_FillingType).selectByValue("column_filling");
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("2");
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "5");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "0");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("10");
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_ShowIconsForMenuItems, true);
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_CompactDisplayView, false);
        UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "700");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu22_Vertical_ColumnFilling_2columns_FullView")
    public void check_Menu22_Vertical_ColumnFilling_2columns_FullView(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu22.00 Menu22_Vertical_ColumnFilling_2columns_FullView - Menu AllProducts");

        Asserts_Menu asserts_menu = new Asserts_Menu();

        //Проверяем, что у меню Колоночное заполнение
        asserts_menu.assertElementPresence(asserts_menu.rowFilling, false);

        //Проверяем, что колонок 2
        asserts_menu.assertNumberOfElements(asserts_menu.columnsPerRow, 2);

        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu22.02 Menu22_Vertical_ColumnFilling_2columns_FullView - Menu Electronic");

        //Проверяем, что у меню второго уровня присутствуют иконки
        asserts_menu.assertElementPresence(asserts_menu.iconsOfSecondLevel, true);

        //Проверяем, что Элементов второго уровня -- 5
        asserts_menu.assertSizeOfElements(asserts_menu.numberOfElements_SecondLevel, 5);

        //Проверяем, что Элементов третьего уровня -- 0
        asserts_menu.assertElementPresence(asserts_menu.numberOfElements_ThirdLevel_Electronics, false);

        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Apparel);
        takeScreenShot("Menu22.04 Menu22_Vertical_ColumnFilling_2columns_FullView - Menu Apparel");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_SportsAndOutdoors);
        takeScreenShot("Menu22.06 Menu22_Vertical_ColumnFilling_2columns_FullView - Menu SportsAndOutdoors");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_VideoGames);
        takeScreenShot("Menu22.08 Menu22_Vertical_ColumnFilling_2columns_FullView - Menu VideoGames");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage("ar");
        stHomePage.verticalMenu_menuButton_Categories.click();
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu22.10 Menu22_Vertical_ColumnFilling_2columns_FullView - Menu AllProducts (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu22.12 Menu22_Vertical_ColumnFilling_2columns_FullView - Menu Electronic (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Apparel);
        takeScreenShot("Menu22.14 Menu22_Vertical_ColumnFilling_2columns_FullView - Menu Apparel (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_SportsAndOutdoors);
        takeScreenShot("Menu22.16 Menu22_Vertical_ColumnFilling_2columns_FullView - Menu SportsAndOutdoors (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_VideoGames);
        takeScreenShot("Menu22.18 Menu22_Vertical_ColumnFilling_2columns_FullView - Menu VideoGames (RTL)");
    }
}