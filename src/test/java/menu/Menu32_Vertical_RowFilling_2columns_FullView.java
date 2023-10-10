package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.MenuSettings;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOfMenu;
import taras.storefront.StHomePage;
import java.time.Duration;

/*
Работаем с макетом Light:
Вертикальное меню + Строчное заполнение + 2 колонки
+ Компактный вид отображения -- нет
+ Показывать иконки для пунктов меню второго уровня -- да
+ Кол-во отображаемых элементов во 2-м уровне меню -- 5
+ Кол-во отображаемых элементов в 3-м уровне меню -- 10  (здесь эту настройку не проверяем)
+ Элементы второго уровня -- 5
+ Элементы третьего уровня -- 0
+ Минимальная высота для меню -- 700
*/

public class Menu32_Vertical_RowFilling_2columns_FullView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu32_Vertical_RowFilling_2columns_FullView(){
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_DesignLayouts();
        csCartSettings.layout_Light.click();
        csCartSettings.setLayoutAsDefault();
        MenuSettings menuSettings = new MenuSettings();
        menuSettings.gearwheelOfTheBlock_Categories_Light.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        menuSettings.menuSettings_buttonSettings.click();
        menuSettings.selectSetting_FillingType("row_filling");
        menuSettings.selectSetting_MaximumColumns("2");
        if(menuSettings.setting_CompactDisplayView.isSelected()){
            menuSettings.setting_CompactDisplayView.click();
        }
        if(!menuSettings.setting_ShowIconsForMenuItems.isSelected()){
            menuSettings.setting_ShowIconsForMenuItems.click();
        }
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("5");
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("10");
        menuSettings.clickAndType_setting_SecondLevelElements("5");
        menuSettings.clickAndType_setting_ThirdLevelElements("0");
        menuSettings.clickAndType_setting_MinimumHeightForMenu("700");
        menuSettings.tab_Content.click();
        menuSettings.selectMenuContent_MainMenu();
        menuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu32_Vertical_RowFilling_2columns_FullView")
    public void check_Menu32_Vertical_RowFilling_2columns_FullView(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Catalog.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu32.00 Menu32_Vertical_RowFilling_2columns_FullView - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();
        //Проверяем, что у меню Строчное заполнение
        softAssert.assertTrue(!assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Row!");
        //Проверяем, что колонок 2
        softAssert.assertTrue(!assertsOfMenu.twoColumns.isEmpty(),
                "Menu columns are not equal 2 columns!");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu32.02 Menu32_Vertical_RowFilling_2columns_FullView - Menu Electronic");
        //Проверяем, что у меню второго уровня присутствуют иконки
        softAssert.assertTrue(!assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are no icons at the menu of the second level!");
        //Проверяем, что Элементов второго уровня -- 5
        softAssert.assertTrue(assertsOfMenu.numberOfElements_SecondLevel.size() == 5,
                "Number of elements of the second level is not 5!");
        //Проверяем, что Элементов третьего уровня -- 0
        softAssert.assertTrue(assertsOfMenu.numberOfElements_ThirdLevel.isEmpty(),
                "Number of elements of the third level of the menu is more than zero!");
        stHomePage.navigateToVerticalMenu_Apparel();
        takeScreenShot("Menu32.04 Menu32_Vertical_RowFilling_2columns_FullView - Menu Apparel");
        stHomePage.navigateToVerticalMenu_SportsAndOutdoors();
        takeScreenShot("Menu32.06 Menu32_Vertical_RowFilling_2columns_FullView - Menu SportsAndOutdoors");
        stHomePage.navigateToVerticalMenu_VideoGames();
        takeScreenShot("Menu32.08 Menu32_Vertical_RowFilling_2columns_FullView - Menu VideoGames");

        stHomePage.verticalMenu_menuButton_Catalog.click();
        stHomePage.selectLanguage_RTL();
        stHomePage.verticalMenu_menuButton_Catalog.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu32.10 Menu32_Vertical_RowFilling_2columns_FullView - Menu AllProducts (RTL)");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu32.12 Menu32_Vertical_RowFilling_2columns_FullView - Menu Electronic (RTL)");
        stHomePage.navigateToVerticalMenu_Apparel();
        takeScreenShot("Menu32.14 Menu32_Vertical_RowFilling_2columns_FullView - Menu Apparel (RTL)");
        stHomePage.navigateToVerticalMenu_SportsAndOutdoors();
        takeScreenShot("Menu32.16 Menu32_Vertical_RowFilling_2columns_FullView - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToVerticalMenu_VideoGames();
        takeScreenShot("Menu32.18 Menu32_Vertical_RowFilling_2columns_FullView - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}