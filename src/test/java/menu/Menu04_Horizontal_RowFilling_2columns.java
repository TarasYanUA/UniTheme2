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
Работаем с макетом Light v2:
Горизонтальное меню + Строчное заполнение + 2 колонки
+ Элементы второго уровня -- 5
+ Элементы третьего уровня -- 0
+ Количество видимых элементов в третьем уровне меню -- 5
+ Показывать иконки для пунктов меню второго уровня -- да
+ Минимальная высота для меню -- 700
*/

public class Menu04_Horizontal_RowFilling_2columns extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu04_Horizontal_RowFilling_2columns(){
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
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("2");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("5");
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "5");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "0");
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_ShowIconsForMenuItems, true);
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_CompactDisplayView, false);   //Выключаем Компактный вид для Горизонтального меню
        UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "700");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu04_Horizontal_RowFilling_2columns")
    public void check_Menu04_Horizontal_RowFilling_2columns(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_AllProducts);
        takeScreenShot("Menu4.00 Menu04_Horizontal_RowFilling_2columns - Menu AllProducts");

        Asserts_Menu asserts_menu = new Asserts_Menu();

        //Проверяем, что у меню Строчное заполнение
        asserts_menu.assertElementPresence(asserts_menu.rowFilling, true);

        //Проверяем, что колонок 2
        asserts_menu.assertNumberOfElements(asserts_menu.columnsPerRow, 2);

        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu4.02 Menu04_Horizontal_RowFilling_2columns - Menu Electronic");

        //Проверяем, что у меню второго уровня присутствуют иконки
        asserts_menu.assertElementPresence(asserts_menu.iconsOfSecondLevel, true);

        //Проверяем, что Элементов второго уровня -- 5
        asserts_menu.assertNumberOfElements(asserts_menu.numberOfElements_SecondLevel, 5);

        //Проверяем, что Элементов третьего уровня -- 0 (то есть, отсутствуют)
        asserts_menu.assertElementPresence(asserts_menu.numberOfElements_ThirdLevel_Electronics, false);

        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        asserts_menu.assertElementPresence(asserts_menu.button_MoreCategory_InSecondLevel, true);

        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Apparel);
        takeScreenShot("Menu4.04 Menu04_Horizontal_RowFilling_2columns - Menu Apparel");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_SportsAndOutdoors);
        takeScreenShot("Menu4.06 Menu04_Horizontal_RowFilling_2columns - Menu SportsAndOutdoors");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_VideoGames);
        takeScreenShot("Menu4.08 Menu04_Horizontal_RowFilling_2columns - Menu VideoGames");

        stHomePage.selectLanguage("ar");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu4.10 Menu04_Horizontal_RowFilling_2columns - Menu Electronic (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Apparel);
        takeScreenShot("Menu4.12 Menu04_Horizontal_RowFilling_2columns - Menu Apparel (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_SportsAndOutdoors);
        takeScreenShot("Menu4.14 Menu04_Horizontal_RowFilling_2columns - Menu SportsAndOutdoors (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_VideoGames);
        takeScreenShot("Menu4.16 Menu04_Horizontal_RowFilling_2columns - Menu VideoGames (RTL)");
    }
}