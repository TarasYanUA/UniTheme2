package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.MenuSettings;
import taras.constants.DriverProvider;
import taras.storefront.StHomePage;

import java.time.Duration;

/*
Работаем с макетом Light:
Вертикальное меню + Колоночное заполнение + 2 колонки + Компактный вид
+ Показывать иконки для пунктов меню второго уровня -- да
+ Кол-во отображаемых элементов во 2-м уровне меню -- 2
+ Кол-во отображаемых элементов в 3-м уровне меню -- 10
+ Элементы второго уровня -- 5
+ Элементы третьего уровня -- 5
+ Минимальная высота для меню -- 700
*/

public class Menu24_Vertical_ColumnFilling_2columns_CompactView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu24_Vertical_ColumnFilling_2columns_CompactView(){
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_DesignLayouts();
        csCartSettings.layout_Light.click();
        csCartSettings.setLayoutAsDefault();
        MenuSettings menuSettings = new MenuSettings();
        menuSettings.gearwheelOfTheBlock_Categories.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        menuSettings.menuSettings_buttonSettings.click();
        menuSettings.selectSetting_FillingType("column_filling");
        menuSettings.selectSetting_MaximumColumns("2");
        if(!menuSettings.setting_CompactDisplayView.isSelected()){
            menuSettings.setting_CompactDisplayView.click();
        }
        if(!menuSettings.setting_ShowIconsForMenuItems.isSelected()){
            menuSettings.setting_ShowIconsForMenuItems.click();
        }
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("2");
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("10");
        menuSettings.clickAndType_setting_SecondLevelElements("5");
        menuSettings.clickAndType_setting_ThirdLevelElements("5");
        menuSettings.clickAndType_setting_MinimumHeightForMenu("700");
        menuSettings.tab_Content.click();
        menuSettings.selectMenuContent_MainMenu();
        menuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu24_Vertical_ColumnFilling_2columns_CompactView")
    public void check_Menu24_Vertical_ColumnFilling_2columns_CompactView(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Catalog.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu24.00 Menu24_Vertical_ColumnFilling_2columns_CompactView - Menu AllProducts");
        //Проверяем, что у меню Колоночное заполнение
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(DriverProvider.getDriver().findElements(By.cssSelector(".ty-menu__submenu .row-filling")).size() >=1,
                "Menu filling is not Column!");
        //Проверяем, что колонок 2
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[data-cols-count='2']")).size() >=1,
                "Menu columns are not equal 2 columns!");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu24.02 Menu24_Vertical_ColumnFilling_2columns_CompactView - Menu Electronic");
        //Проверяем, что у меню второго уровня присутствуют иконки
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".second-lvl .ut2-mwi-icon-wrap .ut2-mwi-icon")).size() >=1,
                "There are no icons at the menu of the second level!");
        //Проверяем, что Элементов второго уровня -- 5
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[class='second-lvl'][data-elem-index='4']")).size() >=1,
                "Number of elements of the 2-level is less than 5!");
        stHomePage.navigateToVerticalMenu_Apparel();
        takeScreenShot("Menu24.04 Menu24_Vertical_ColumnFilling_2columns_CompactView - Menu Apparel");
        stHomePage.navigateToVerticalMenu_SportsAndOutdoors();
        takeScreenShot("Menu24.06 Menu24_Vertical_ColumnFilling_2columns_CompactView - Menu SportsAndOutdoors");
        stHomePage.navigateToVerticalMenu_VideoGames();
        takeScreenShot("Menu24.08 Menu24_Vertical_ColumnFilling_2columns_CompactView - Menu VideoGames");

        stHomePage.verticalMenu_menuButton_Catalog.click();
        stHomePage.selectLanguage_RTL();
        stHomePage.verticalMenu_menuButton_Catalog.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu24.10 Menu24_Vertical_ColumnFilling_2columns_CompactView - Menu AllProducts (RTL)");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu24.12 Menu24_Vertical_ColumnFilling_2columns_CompactView - Menu Electronic (RTL)");
        stHomePage.navigateToVerticalMenu_Apparel();
        takeScreenShot("Menu24.14 Menu24_Vertical_ColumnFilling_2columns_CompactView - Menu Apparel (RTL)");
        stHomePage.navigateToVerticalMenu_SportsAndOutdoors();
        takeScreenShot("Menu24.16 Menu24_Vertical_ColumnFilling_2columns_CompactView - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToVerticalMenu_VideoGames();
        takeScreenShot("Menu24.18 Menu24_Vertical_ColumnFilling_2columns_CompactView - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}