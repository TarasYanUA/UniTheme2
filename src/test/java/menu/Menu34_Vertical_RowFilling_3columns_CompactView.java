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
Вертикальное меню + Строчное заполнение + 3 колонки + Компактный вид
+ Элементы второго уровня -- 30
+ Элементы третьего уровня -- 30
+ Количество видимых элементов в третьем уровне меню -- 5
+ Показывать иконки для пунктов меню второго уровня -- да
+ Минимальная высота для меню -- 600
*/

public class Menu34_Vertical_RowFilling_3columns_CompactView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu34_Vertical_RowFilling_3columns_CompactView(){
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
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("3");
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "30");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "30");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("5");
        if(!mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()){
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        if(!mainMenuSettings.setting_CompactDisplayView.isSelected()){
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "600");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu34_Vertical_RowFilling_3columns_CompactView")
    public void check_Menu34_Vertical_RowFilling_3columns_CompactView(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu34.00 Menu34_Vertical_RowFilling_3columns_CompactView - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню Строчное заполнение
        softAssert.assertTrue(!assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Row!");

        //Проверяем, что колонок 3
        softAssert.assertTrue(!assertsOfMenu.columnsPerRow("3").isEmpty(),
                "Menu columns are not equal 3 columns!");
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu34.02 Menu34_Vertical_RowFilling_3columns_CompactView - Menu Electronic");

        //Проверяем, что у меню второго уровня присутствуют иконки
        softAssert.assertTrue(!assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are no icons at the menu of the second level!");

        //Проверяем, что Элементов второго уровня -- не меньше 7
        softAssert.assertTrue(assertsOfMenu.numberOfElements_SecondLevel.size() >= 7,
                "Number of elements of the second level is less than 7!");

        //Проверяем, что Элементов третьего уровня -- не меньше 7
        softAssert.assertTrue(assertsOfMenu.numberOfElements_ThirdLevel.size() >= 7,
                "Number of elements of the third level is less than 7!");

        //Проверяем, что Кол-во отображаемых элементов в 3-м уровне меню -- 5
        softAssert.assertTrue(!assertsOfMenu.numberOfVisibleElementsIn_3levelMenu("5").isEmpty(),
                "'Number of visible elements in the 3-level menu' is not 5!");

        //Проверяем, что присутствует кнопка "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(!assertsOfMenu.button_More_InElementsOf2levelMenu.isEmpty(),
                "There are no buttons 'More' in the elements of the 2-level menu!");

        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_Apparel);
        takeScreenShot("Menu34.04 Menu34_Vertical_RowFilling_3columns_CompactView - Menu Apparel");
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_SportsAndOutdoors);
        takeScreenShot("Menu34.06 Menu34_Vertical_RowFilling_3columns_CompactView - Menu SportsAndOutdoors");
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_VideoGames);
        takeScreenShot("Menu34.08 Menu34_Vertical_RowFilling_3columns_CompactView - Menu VideoGames");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage("ar");
        stHomePage.verticalMenu_menuButton_Categories.click();
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu34.10 Menu34_Vertical_RowFilling_3columns_CompactView - Menu AllProducts (RTL)");
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu34.12 Menu34_Vertical_RowFilling_3columns_CompactView - Menu Electronic (RTL)");
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_Apparel);
        takeScreenShot("Menu34.14 Menu34_Vertical_RowFilling_3columns_CompactView - Menu Apparel (RTL)");
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_SportsAndOutdoors);
        takeScreenShot("Menu34.16 Menu34_Vertical_RowFilling_3columns_CompactView - Menu SportsAndOutdoors (RTL)");
        UtilsStorefront.hoverOverElement(stHomePage.verticalMenu_VideoGames);
        takeScreenShot("Menu34.18 Menu34_Vertical_RowFilling_3columns_CompactView - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}