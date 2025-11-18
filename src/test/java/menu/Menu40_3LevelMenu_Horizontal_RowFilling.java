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
Горизонтальное меню + Строчное заполнение + 3-х уровневое меню
+ Количество колонок -- 5
+ Элементы второго уровня -- 12
+ Элементы третьего уровня -- 6
+ Количество видимых элементов в третьем уровне меню -- 4 (не влияет на трехуровневое меню)
+ Минимальная высота для меню -- 500
*/

public class Menu40_3LevelMenu_Horizontal_RowFilling extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu40_3LevelMenu_Horizontal_RowFilling(){
        //Настраиваем 3-х уровневое меню на странице "Дизайн -- Меню"
        BasicPage basicPage = new BasicPage();
        basicPage.navigateTo_WebsiteMenuPage();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.choose_MainMenu.click();
        mainMenuSettings.chooseMenu_Electronics.click();
        taras.adminPanel.UtilsAdm.waitForTitleBarWindow();
        mainMenuSettings.menuTab_ABUniTheme2.click();
        if(!mainMenuSettings.setting_ActivateSettings.isSelected()){
            mainMenuSettings.setting_ActivateSettings.click();
        }
        if(!mainMenuSettings.setting_Activate3LevelMenu.isSelected()){
            mainMenuSettings.setting_Activate3LevelMenu.click();
        }
        mainMenuSettings.button_Save3LevelMenu.click();

        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Lightv2.click();
        layoutPage.setLayoutAsDefault();
        mainMenuSettings.gearwheelOfTheBlock_MainMenu_LightV2.click();
        taras.adminPanel.UtilsAdm.waitForTitleBarWindow();
        mainMenuSettings.menuSettings_buttonSettings.click();
        new Select(mainMenuSettings.setting_FillingType).selectByValue("row_filling");
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("5");
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "12");
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "6");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("4");
        if(mainMenuSettings.setting_CompactDisplayView.isSelected()){   //Выключаем Компактный вид для Горизонтального меню
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "500");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu40_3LevelMenu_Horizontal_RowFilling")
    public void check_Menu40_3LevelMenu_Horizontal_RowFilling(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_AllProducts);
        takeScreenShot("Menu40.00 Menu40_3LevelMenu_Horizontal_RowFilling - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню Строчное заполнение
        softAssert.assertTrue(!assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Row!");

        //Проверяем, что колонок 5
        softAssert.assertTrue(!assertsOfMenu.columnsPerRow("5").isEmpty(),
                "Menu columns are not equal 5 columns!");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu40.02 Menu40_3LevelMenu_Horizontal_RowFilling - Menu Electronic-Computers");

        //Проверяем, что Элементов второго уровня -- не меньше 7
        softAssert.assertTrue(assertsOfMenu.numberOfElements_SecondLevel.size() >= 7,
                "Number of elements of the second level is less than 7!");

        //Проверяем, что Элементов третьего уровня -- 6
        softAssert.assertEquals(assertsOfMenu.threeLevelMenu_elementsInThirdLevel.size(), 6,
                "'Third level elements' are not equal 6!");

        //Проверяем, что в 3-х уровневом меню (Каскадный тип меню) "Элементы третьего уровня" -- 6
        softAssert.assertEquals(assertsOfMenu.threeLevelMenu_elementsInThirdLevel.size(), 6,
                "'Third level elements' at Cascade menu type are not 6!");

        //Проверяем, что Количество видимых элементов в третьем уровне меню -- 4
        softAssert.assertTrue(!assertsOfMenu.numberOfVisibleElementsIn_3levelMenu("4").isEmpty(),
                "'Number of visible elements in the 3-level menu' is not 4!");

        //Проверяем, что присутствует кнопка "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(!assertsOfMenu.button_More_InElementsOf2levelMenu.isEmpty(),
                "There are no buttons 'More' in the elements of the 2-level menu!");

        UtilsAdm.hoverOverElement(stHomePage.threeLevelMenu_CarElectronics);
        takeScreenShot("Menu40.04 Menu40_3LevelMenu_Horizontal_RowFilling - Menu Electronic-CarElectronics");

        stHomePage.selectLanguage("ar");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_AllProducts);
        takeScreenShot("Menu40.06 Menu40_3LevelMenu_Horizontal_RowFilling - Menu AllProducts (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu40.08 Menu40_3LevelMenu_Horizontal_RowFilling - Menu Electronic-Computers (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.threeLevelMenu_CarElectronics);
        takeScreenShot("Menu40.10 Menu40_3LevelMenu_Horizontal_RowFilling - Menu Electronic-CarElectronics (RTL)");
        softAssert.assertAll();
    }
}