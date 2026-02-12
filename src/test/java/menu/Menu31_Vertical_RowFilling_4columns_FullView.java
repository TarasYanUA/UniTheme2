package menu;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import taras.adminPanel.BasicPage;
import taras.adminPanel.LayoutPage;
import taras.adminPanel.MainMenuSettings;
import taras.adminPanel.UtilsAdm;
import taras.asserts.Asserts_Menu;
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
        UtilsAdm.waitForTitleBarWindow();
        mainMenuSettings.menuSettings_buttonSettings.click();
        new Select(mainMenuSettings.setting_FillingType).selectByValue("row_filling");
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("4");
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "30");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "30");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("5");
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_ShowIconsForMenuItems, true);
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_CompactDisplayView, false);
        UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "600");
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

        Asserts_Menu asserts_menu = new Asserts_Menu();

        //Проверяем, что у меню Строчное заполнение
        asserts_menu.assertElementPresence(asserts_menu.rowFilling, true);

        //Проверяем, что колонок 4
        asserts_menu.assertNumberOfElements(asserts_menu.columnsPerRow, 4);

        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu31.02 Menu31_Vertical_RowFilling_4columns_FullView - Menu Electronic");

        //Проверяем, что у меню второго уровня присутствуют иконки
        asserts_menu.assertElementPresence(asserts_menu.iconsOfSecondLevel, true);

        //Проверяем, что Элементов второго уровня -- не меньше 7
        asserts_menu.assertMoreOrEqual(asserts_menu.numberOfElements_SecondLevel, 7);

        //Проверяем, что Элементов третьего уровня -- не меньше 7
        asserts_menu.assertMoreOrEqual(asserts_menu.numberOfElements_ThirdLevel_Electronics, 7);

        //Проверяем, что Количество видимых элементов в третьем уровне меню -- 5
        asserts_menu.assertNumberOfElements(asserts_menu.numberOfVisibleElementsIn_ThirdLevel, 5);

        //Проверяем, что присутствует кнопка "Ещё" у элементов во 2-м уровне меню
        asserts_menu.assertElementPresence(asserts_menu.button_More_InElementsOfSecondLevel, true);

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
    }
}