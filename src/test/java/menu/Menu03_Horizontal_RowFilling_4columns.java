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
Работаем с макетом Light v2:
Горизонтальное меню + Строчное заполнение + 4 колонки
+ Элементы второго уровня -- 4
+ Элементы третьего уровня -- 4
+ Количество видимых элементов в третьем уровне меню -- 4   //Здесь эту настройку не проверяем
+ Показывать иконки для пунктов меню второго уровня -- да
+ Минимальная высота для меню -- 300
*/

public class Menu03_Horizontal_RowFilling_4columns extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu03_Horizontal_RowFilling_4columns() {
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Lightv2.click();
        layoutPage.setLayoutAsDefault();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.gearwheelOfTheBlock_MainMenu_LightV2.click();
        UtilsAdm.waitForTitleBarWindow();
        mainMenuSettings.menuSettings_buttonSettings.click();
        new Select(mainMenuSettings.setting_FillingType).selectByValue("row_filling");
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("4");
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "4");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "4");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("4");
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_ShowIconsForMenuItems, true);
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_CompactDisplayView, false);   //Выключаем Компактный вид для Горизонтального меню
        UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "300");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu03_Horizontal_RowFilling_4columns")
    public void check_Menu03_Horizontal_RowFilling_4columns() {
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_AllProducts);
        takeScreenShot("Menu3.00 Menu03_Horizontal_RowFilling_4columns - Menu AllProducts");

        Asserts_Menu asserts_menu = new Asserts_Menu();

        //Проверяем, что у меню Строчное заполнение
        asserts_menu.assertElementPresence(asserts_menu.rowFilling, true);

        //Проверяем, что колонок 4
        asserts_menu.assertNumberOfElements(asserts_menu.columnsPerRow, 4);

        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu3.02 Menu03_Horizontal_RowFilling_4columns - Menu Electronic");

        //Проверяем, что у меню второго уровня есть иконки
        asserts_menu.assertElementPresence(asserts_menu.iconsOfSecondLevel, true);

        //Проверяем, что Элементов второго уровня -- 4
        asserts_menu.assertSizeOfElements(asserts_menu.numberOfElements_SecondLevel, 4);

        //Проверяем, что Элементов третьего уровня -- 4
        asserts_menu.assertSizeOfElements(asserts_menu.numberOfElements_ThirdLevel_Electronics, 4);

        //Проверяем, что отсутствуют кнопки "Ещё" у элементов во 2-м уровне меню
        asserts_menu.assertElementPresence(asserts_menu.button_More_InElementsOfSecondLevel, true);

        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        asserts_menu.assertElementPresence(asserts_menu.button_MoreCategory_InSecondLevel, true);

        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Apparel);
        takeScreenShot("Menu3.04 Menu03_Horizontal_RowFilling_4columns - Menu Apparel");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_SportsAndOutdoors);
        takeScreenShot("Menu3.06 Menu03_Horizontal_RowFilling_4columns - Menu SportsAndOutdoors");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_VideoGames);
        takeScreenShot("Menu3.08 Menu03_Horizontal_RowFilling_4columns - Menu VideoGames");

        stHomePage.selectLanguage("ar");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu3.10 Menu03_Horizontal_RowFilling_4columns - Menu Electronic (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Apparel);
        takeScreenShot("Menu3.12 Menu03_Horizontal_RowFilling_4columns - Menu Apparel (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_SportsAndOutdoors);
        takeScreenShot("Menu3.14 Menu03_Horizontal_RowFilling_4columns - Menu SportsAndOutdoors (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_VideoGames);
        takeScreenShot("Menu3.16 Menu03_Horizontal_RowFilling_4columns - Menu VideoGames (RTL)");
    }
}