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
        if (mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()) {
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        if (!mainMenuSettings.setting_CompactDisplayView.isSelected()) {
            mainMenuSettings.setting_CompactDisplayView.click();
        }
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
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu35.00 Menu35_Vertical_RowFilling_1column_CompactView - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню Строчное заполнение
        softAssert.assertTrue(!assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Row!");

        //Проверяем, что присутствует 1 колонка
        softAssert.assertTrue(!assertsOfMenu.columnsPerRow("1").isEmpty(),
                "Menu columns are not equal 1 column!");
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu35.02 Menu35_Vertical_RowFilling_1column_CompactView - Menu Electronic");

        //Проверяем, что у меню второго уровня отсутствуют иконки
        softAssert.assertTrue(assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are icons at the menu of the second level but shouldn't!");

        //Проверяем, что Элементов второго уровня -- 5
        softAssert.assertEquals(assertsOfMenu.numberOfElements_SecondLevel.size(), 5,
                "Number of elements of the second level is not 5!");

        //Проверяем, что Элементов третьего уровня -- 0
        softAssert.assertTrue(assertsOfMenu.numberOfElements_ThirdLevel.isEmpty(),
                "Number of elements of the third level of the menu is more than zero!");

        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.button_MoreCategory_InTheSecondLevel.isEmpty(),
                "There is no button 'More [category]' in the second level of the menu!");

        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_Apparel);
        takeScreenShot("Menu35.04 Menu35_Vertical_RowFilling_1column_CompactView - Menu Apparel");
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_SportsAndOutdoors);
        takeScreenShot("Menu35.06 Menu35_Vertical_RowFilling_1column_CompactView - Menu SportsAndOutdoors");
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_VideoGames);
        takeScreenShot("Menu35.08 Menu35_Vertical_RowFilling_1column_CompactView - Menu VideoGames");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage("ar");
        stHomePage.verticalMenu_menuButton_Categories.click();
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu35.10 Menu35_Vertical_RowFilling_1column_CompactView - Menu AllProducts (RTL)");
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu35.12 Menu35_Vertical_RowFilling_1column_CompactView - Menu Electronic (RTL)");
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_Apparel);
        takeScreenShot("Menu35.14 Menu35_Vertical_RowFilling_1column_CompactView - Menu Apparel (RTL)");
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_SportsAndOutdoors);
        takeScreenShot("Menu35.16 Menu35_Vertical_RowFilling_1column_CompactView - Menu SportsAndOutdoors (RTL)");
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_VideoGames);
        takeScreenShot("Menu35.18 Menu35_Vertical_RowFilling_1column_CompactView - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}