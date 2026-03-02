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
Вертикальное меню + Строчное заполнение + 1 колонка + Компактный вид
+ Элементы второго уровня -- 5
+ Элементы третьего уровня -- 0
+ Количество видимых элементов в третьем уровне меню -- 5   //Здесь эту настройку не проверяем
+ Показывать иконки для пунктов меню второго уровня -- нет
+ Минимальная высота для меню -- 600
*/

public class Menu35_Vertical_RowFilling_1column_CompactView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu35_Vertical_RowFilling_1column_CompactView() {
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
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("1");
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "5");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "0");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("10");
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_ShowIconsForMenuItems, false);
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_CompactDisplayView, true);
        UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "600");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu35_Vertical_RowFilling_1column_CompactView")
    public void check_Menu35_Vertical_RowFilling_1column_CompactView() {
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu35.00 Menu35_Vertical_RowFilling_1column_CompactView - Menu AllProducts");

        Asserts_Menu asserts_menu = new Asserts_Menu();

        //Проверяем, что у меню Строчное заполнение
        asserts_menu.assertElementPresence(asserts_menu.rowFilling, true);

        //Проверяем, что присутствует 1 колонка
        asserts_menu.assertNumberOfElements(asserts_menu.columnsPerRow, 1);

        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu35.02 Menu35_Vertical_RowFilling_1column_CompactView - Menu Electronic");

        //Проверяем, что у меню второго уровня отсутствуют иконки
        asserts_menu.assertElementPresence(asserts_menu.iconsOfSecondLevel, false);

        //Проверяем, что Элементов второго уровня -- 5
        asserts_menu.assertSizeOfElements(asserts_menu.numberOfElements_SecondLevel, 5);

        //Проверяем, что Элементов третьего уровня -- 0 (то есть, отсутствуют)
        asserts_menu.assertElementPresence(asserts_menu.numberOfElements_ThirdLevel_Electronics, false);

        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        asserts_menu.assertElementPresence(asserts_menu.button_MoreCategory_InSecondLevel, true);

        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Apparel);
        takeScreenShot("Menu35.04 Menu35_Vertical_RowFilling_1column_CompactView - Menu Apparel");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_SportsAndOutdoors);
        takeScreenShot("Menu35.06 Menu35_Vertical_RowFilling_1column_CompactView - Menu SportsAndOutdoors");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_VideoGames);
        takeScreenShot("Menu35.08 Menu35_Vertical_RowFilling_1column_CompactView - Menu VideoGames");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage("ar");
        stHomePage.verticalMenu_menuButton_Categories.click();
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu35.10 Menu35_Vertical_RowFilling_1column_CompactView - Menu AllProducts (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu35.12 Menu35_Vertical_RowFilling_1column_CompactView - Menu Electronic (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Apparel);
        takeScreenShot("Menu35.14 Menu35_Vertical_RowFilling_1column_CompactView - Menu Apparel (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_SportsAndOutdoors);
        takeScreenShot("Menu35.16 Menu35_Vertical_RowFilling_1column_CompactView - Menu SportsAndOutdoors (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_VideoGames);
        takeScreenShot("Menu35.18 Menu35_Vertical_RowFilling_1column_CompactView - Menu VideoGames (RTL)");
    }
}