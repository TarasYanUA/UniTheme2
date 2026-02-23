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
Горизонтальное меню + Колоночное заполнение + 3-х уровневое меню
+ Количество колонок -- 3
+ Элементы второго уровня -- 12
+ Элементы третьего уровня -- 5
+ Количество видимых элементов в третьем уровне меню -- 2 (не влияет на трехуровневое меню)
+ Минимальная высота для меню -- 500
*/

public class Menu41_3LevelMenu_Horizontal_ColumnFilling extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu41_3LevelMenu_Horizontal_ColumnFilling(){
        //Настраиваем 3-х уровневое меню на странице "Дизайн -- Меню"
        BasicPage basicPage = new BasicPage();
        basicPage.navigateTo_WebsiteMenuPage();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.choose_MainMenu.click();
        mainMenuSettings.chooseMenu_Electronics.click();
        UtilsAdm.waitForTitleBarWindow();
        mainMenuSettings.menuTab_ABUniTheme2.click();
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_ActivateSettings, true);
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_Activate3LevelMenu, true);
        mainMenuSettings.button_Save3LevelMenu.click();

        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Lightv2.click();
        layoutPage.setLayoutAsDefault();
        mainMenuSettings.gearwheelOfTheBlock_MainMenu_LightV2.click();
        UtilsAdm.waitForTitleBarWindow();
        mainMenuSettings.menuSettings_buttonSettings.click();
        new Select(mainMenuSettings.setting_FillingType).selectByValue("column_filling");
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("3");
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "12");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "5");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("2");
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_CompactDisplayView, false);  //Выключаем Компактный вид для Горизонтального меню
        UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "500");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu41_3LevelMenu_Horizontal_ColumnFilling")
    public void check_Menu41_3LevelMenu_Horizontal_ColumnFilling(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_AllProducts);
        takeScreenShot("Menu41.00 Menu41_3LevelMenu_Horizontal_ColumnFilling - Menu AllProducts");

        Asserts_Menu asserts_menu = new Asserts_Menu();

        //Проверяем, что у меню Колоночное заполнение
        asserts_menu.assertElementPresence(asserts_menu.rowFilling, false);

        //Проверяем, что колонок 3
        asserts_menu.assertNumberOfElements(asserts_menu.columnsPerRow, 3);

        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu41.02 Menu41_3LevelMenu_Horizontal_ColumnFilling - Menu Electronic-Computers");

        //Проверяем, что Элементов второго уровня -- не меньше 7
        asserts_menu.assertMoreOrEqual(asserts_menu.numberOfElements_SecondLevel, 7);

        //Проверяем, что Элементов третьего уровня -- 5
        asserts_menu.assertSizeOfElements(asserts_menu.numberOfElements_ThirdLevel_Electronics, 5);

        //Проверяем, что в 3-х уровневом меню (Каскадный тип меню) "Элементы третьего уровня" -- 5
        asserts_menu.assertSizeOfElements(asserts_menu.cascadeMenu_elementsInThirdLevel, 5);

        //Проверяем, что в 3-х уровневом меню (Каскадный тип меню) Количество видимых элементов -- 2
        asserts_menu.assertSizeOfElements(asserts_menu.flyMenu_numberOfVisibleElements, 2);

        //Проверяем, что присутствует кнопка "Ещё" у элементов во 2-м уровне меню
        asserts_menu.assertElementPresence(asserts_menu.button_More_InElementsOfSecondLevel, true);

        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        asserts_menu.assertElementPresence(asserts_menu.button_MoreCategory_InSecondLevel, true);

        UtilsAdm.hoverOverElement(stHomePage.threeLevelMenu_CarElectronics);
        takeScreenShot("Menu41.04 Menu41_3LevelMenu_Horizontal_ColumnFilling - Menu Electronic-CarElectronics");

        stHomePage.selectLanguage("ar");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_AllProducts);
        takeScreenShot("Menu41.06 Menu41_3LevelMenu_Horizontal_ColumnFilling - Menu AllProducts (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu41.08 Menu41_3LevelMenu_Horizontal_ColumnFilling - Menu Electronic-Computers (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.threeLevelMenu_CarElectronics);
        takeScreenShot("Menu41.10 Menu41_3LevelMenu_Horizontal_ColumnFilling - Menu Electronic-CarElectronics (RTL)");
    }
}