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
Вертикальное меню + Строчное заполнение + 5 колонок
+ Элементы второго уровня -- 7
+ Элементы третьего уровня -- 4
+ Количество видимых элементов в третьем уровне меню -- 3
+ Показывать иконки для пунктов меню второго уровня -- нет
+ Компактный вид отображения -- нет
+ Минимальная высота для меню -- 300
*/

public class Menu30_Vertical_RowFilling_5columns_FullView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu30_Vertical_RowFilling_5columns_FullView(){
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Light.click();
        layoutPage.setLayoutAsDefault();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.gearwheelOfTheBlock_Categories_Light.click();
        UtilsAdm.waitForTitleBarWindow();
        mainMenuSettings.menuSettings_buttonSettings.click();
        new Select(mainMenuSettings.setting_FillingType).selectByValue("row_filling");
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("5");
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "7");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "4");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("3");
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_ShowIconsForMenuItems, false);
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_CompactDisplayView, false);
        UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "300");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu30_Vertical_RowFilling_5columns_FullView")
    public void check_Menu30_Vertical_RowFilling_5columns_FullView(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu30.00 Menu30_Vertical_RowFilling_5columns_FullView - Menu AllProducts");

        Asserts_Menu asserts_menu = new Asserts_Menu();

        //Проверяем, что у меню Строчное заполнение
        asserts_menu.assertElementPresence(asserts_menu.rowFilling, true);

        //Проверяем, что колонок 5
        asserts_menu.assertNumberOfElements(asserts_menu.columnsPerRow, 5);

        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu30.02 Menu30_Vertical_RowFilling_5columns_FullView - Menu Electronic");

        //Проверяем, что у меню второго уровня отсутствуют иконки
        asserts_menu.assertElementPresence(asserts_menu.iconsOfSecondLevel, false);

        //Проверяем, что Элементов второго уровня -- 7
        asserts_menu.assertSizeOfElements(asserts_menu.numberOfElements_SecondLevel, 7);

        //Проверяем, что Элементов третьего уровня -- 4
        asserts_menu.assertSizeOfElements(asserts_menu.numberOfElements_ThirdLevel_Electronics, 4);

        //Проверяем, что Количество видимых элементов в третьем уровне меню -- 3
        asserts_menu.assertNumberOfElements(asserts_menu.numberOfVisibleElementsIn_ThirdLevel, 3);

        //Проверяем, что присутствует кнопка "Ещё" у элементов во 2-м уровне меню
        asserts_menu.assertElementPresence(asserts_menu.button_More_InElementsOfSecondLevel, true);

        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Apparel);
        takeScreenShot("Menu30.04 Menu30_Vertical_RowFilling_5columns_FullView - Menu Apparel");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_SportsAndOutdoors);
        takeScreenShot("Menu30.06 Menu30_Vertical_RowFilling_5columns_FullView - Menu SportsAndOutdoors");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_VideoGames);
        takeScreenShot("Menu30.08 Menu30_Vertical_RowFilling_5columns_FullView - Menu VideoGames");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage("ar");
        stHomePage.verticalMenu_menuButton_Categories.click();
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu30.10 Menu30_Vertical_RowFilling_5columns_FullView - Menu AllProducts (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu30.12 Menu30_Vertical_RowFilling_5columns_FullView - Menu Electronic (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Apparel);
        takeScreenShot("Menu30.14 Menu30_Vertical_RowFilling_5columns_FullView - Menu Apparel (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_SportsAndOutdoors);
        takeScreenShot("Menu30.16 Menu30_Vertical_RowFilling_5columns_FullView - Menu SportsAndOutdoors (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_VideoGames);
        takeScreenShot("Menu30.18 Menu30_Vertical_RowFilling_5columns_FullView - Menu VideoGames (RTL)");
    }
}