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
Вертикальное меню + Колоночное заполнение + 1 колонка
+ Элементы второго уровня -- 3
+ Элементы третьего уровня -- 10
+ Количество видимых элементов в третьем уровне меню -- 6
+ Показывать иконки для пунктов меню второго уровня -- нет
+ Компактный вид отображения -- нет
+ Минимальная высота для меню -- 500
*/

public class Menu23_Vertical_ColumnFilling_1column_FullView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu23_Vertical_ColumnFilling_1column_FullView(){
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
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("1");
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "3");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "10");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("6");
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_ShowIconsForMenuItems, false);
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_CompactDisplayView, false);
        UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "500");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu23_Vertical_ColumnFilling_1column_FullView")
    public void check_Menu23_Vertical_ColumnFilling_1column_FullView(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu23.00 Menu23_Vertical_ColumnFilling_1column_FullView - Menu AllProducts");

        Asserts_Menu asserts_menu = new Asserts_Menu();

        //Проверяем, что у меню Колоночное заполнение
        asserts_menu.assertElementPresence(asserts_menu.rowFilling, false);

        //Проверяем, что присутствует 1 колонка
        asserts_menu.assertNumberOfElements(asserts_menu.columnsPerRow, 1);

        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu23.02 Menu23_Vertical_ColumnFilling_1column_FullView - Menu Electronic");

        //Проверяем, что у меню второго уровня отсутствуют иконки
        asserts_menu.assertElementPresence(asserts_menu.iconsOfSecondLevel, false);

        //Проверяем, что Элементов второго уровня -- 3
        asserts_menu.assertNumberOfElements(asserts_menu.numberOfElements_SecondLevel, 3);

        //Проверяем, что Элементов третьего уровня -- больше или равно 7 (хоть в настройке ставим 10)
        asserts_menu.assertMoreOrEqual(asserts_menu.numberOfElements_ThirdLevel_Electronics, 7);

        //Проверяем, что Количество видимых элементов в третьем уровне меню -- 6
        asserts_menu.assertNumberOfElements(asserts_menu.numberOfVisibleElementsIn_ThirdLevel, 6);

        //Проверяем, что присутствует кнопка "Ещё" у элементов во 2-м уровне меню
        asserts_menu.assertElementPresence(asserts_menu.button_More_InElementsOfSecondLevel, true);

        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        asserts_menu.assertElementPresence(asserts_menu.button_MoreCategory_InSecondLevel, true);

        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Apparel);
        takeScreenShot("Menu23.04 Menu23_Vertical_ColumnFilling_1column_FullView - Menu Apparel");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_SportsAndOutdoors);
        takeScreenShot("Menu23.06 Menu23_Vertical_ColumnFilling_1column_FullView - Menu SportsAndOutdoors");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_VideoGames);
        takeScreenShot("Menu23.08 Menu23_Vertical_ColumnFilling_1column_FullView - Menu VideoGames");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage("ar");
        stHomePage.verticalMenu_menuButton_Categories.click();
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu23.10 Menu23_Vertical_ColumnFilling_1column_FullView - Menu AllProducts (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu23.12 Menu23_Vertical_ColumnFilling_1column_FullView - Menu Electronic (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Apparel);
        takeScreenShot("Menu23.14 Menu23_Vertical_ColumnFilling_1column_FullView - Menu Apparel (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_SportsAndOutdoors);
        takeScreenShot("Menu23.16 Menu23_Vertical_ColumnFilling_1column_FullView - Menu SportsAndOutdoors (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_VideoGames);
        takeScreenShot("Menu23.18 Menu23_Vertical_ColumnFilling_1column_FullView - Menu VideoGames (RTL)");
    }
}