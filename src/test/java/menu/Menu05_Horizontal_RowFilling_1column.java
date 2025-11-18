package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.BasicPage;
import taras.adminPanel.LayoutPage;
import taras.adminPanel.MainMenuSettings;
import taras.adminPanel.UtilsAdm;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOfMenu;
import taras.storefront.StHomePage;
import taras.storefront.UtilsStorefront;

import java.time.Duration;

/*
Работаем с макетом Light v2:
Горизонтальное меню + Строчное заполнение + 1 колонка
+ Элементы второго уровня -- 3
+ Элементы третьего уровня -- 6
+ Количество видимых элементов в третьем уровне меню -- 5
+ Показывать иконки для пунктов меню второго уровня -- нет
+ Минимальная высота для меню -- 500
*/

public class Menu05_Horizontal_RowFilling_1column extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu05_Horizontal_RowFilling_1column() {
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
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("1");
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "3");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "6");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("5");
        if (mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()) {
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        if (mainMenuSettings.setting_CompactDisplayView.isSelected()) {   //Выключаем Компактный вид для Горизонтального меню
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "500");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu05_Horizontal_RowFilling_1column")
    public void check_Menu05_Horizontal_RowFilling_1column() {
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_AllProducts);
        takeScreenShot("Menu5.00 Menu05_Horizontal_RowFilling_1column - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню Строчное заполнение
        softAssert.assertTrue(!assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Row!");

        //Проверяем, что присутствует 1 колонка
        softAssert.assertTrue(!assertsOfMenu.columnsPerRow("1").isEmpty(),
                "Menu columns are not equal 1 column!");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu5.02 Menu05_Horizontal_RowFilling_1column - Menu Electronic");

        //Проверяем, что у меню второго уровня отсутствуют иконки
        softAssert.assertTrue(assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are icons in the second level of the menu but shouldn't!");

        //Проверяем, что Элементов второго уровня -- 3
        softAssert.assertEquals(assertsOfMenu.numberOfElements_SecondLevel.size(), 3,
                "Number of elements of the 2-level is not 3!");

        //Проверяем, что Элементов третьего уровня -- 6
        softAssert.assertEquals(assertsOfMenu.numberOfElements_ThirdLevel.size(), 6,
                "Number of elements of the third level is not 6!");

        //Проверяем, что Количество видимых элементов в третьем уровне меню -- 5
        softAssert.assertTrue(!assertsOfMenu.numberOfVisibleElementsIn_3levelMenu("5").isEmpty(),
                "'Number of visible elements in the 3-level menu' is not 5!");

        //Проверяем, что присутствует не меньше 3 кнопок "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(assertsOfMenu.button_More_InElementsOf2levelMenu.size() >= 3,
                "There are less than 3 buttons 'More' in the elements of the second level of the menu!");

        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.button_MoreCategory_InTheSecondLevel.isEmpty(),
                "There is no button 'More [category]' in the second level of the menu!");

        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_Apparel);
        takeScreenShot("Menu5.04 Menu05_Horizontal_RowFilling_1column - Menu Apparel");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_SportsAndOutdoors);
        takeScreenShot("Menu5.06 Menu05_Horizontal_RowFilling_1column - Menu SportsAndOutdoors");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_VideoGames);
        takeScreenShot("Menu5.08 Menu05_Horizontal_RowFilling_1column - Menu VideoGames");

        stHomePage.selectLanguage("ar");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu5.10 Menu05_Horizontal_RowFilling_1column - Menu Electronic (RTL)");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_Apparel);
        takeScreenShot("Menu5.12 Menu05_Horizontal_RowFilling_1column - Menu Apparel (RTL)");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_SportsAndOutdoors);
        takeScreenShot("Menu5.14 Menu05_Horizontal_RowFilling_1column - Menu SportsAndOutdoors (RTL)");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_VideoGames);
        takeScreenShot("Menu5.16 Menu05_Horizontal_RowFilling_1column - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}