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
Вертикальное меню + Колоночное заполнение + 5 колонок
+ Компактный вид отображения -- нет
+ Показывать иконки для пунктов меню второго уровня -- нет
+ Кол-во отображаемых элементов во 2-м уровне меню -- 0
+ Кол-во отображаемых элементов в 3-м уровне меню -- 10
+ Элементы второго уровня -- 12
+ Элементы третьего уровня -- 6
+ Минимальная высота для меню -- 300
*/

public class Menu21_Vertical_ColumnFilling_5columns_FullView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu21_Vertical_ColumnFilling_5columns_FullView(){
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
        menuSettings.selectSetting_MaximumColumns("5");
        if(menuSettings.setting_CompactDisplayView.isSelected()){
            menuSettings.setting_CompactDisplayView.click();
        }
        if(menuSettings.setting_ShowIconsForMenuItems.isSelected()){
            menuSettings.setting_ShowIconsForMenuItems.click();
        }
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("0");
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("10");
        menuSettings.clickAndType_setting_SecondLevelElements("12");
        menuSettings.clickAndType_setting_ThirdLevelElements("6");
        menuSettings.clickAndType_setting_MinimumHeightForMenu("300");
        menuSettings.tab_Content.click();
        menuSettings.selectMenuContent_MainMenu();
        menuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu21_Vertical_ColumnFilling_5columns_FullView")
    public void check_Menu21_Vertical_ColumnFilling_5columns_FullView(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.menuButton_Catalog.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu21.00 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu AllProducts");
        //Проверяем, что у меню Колоночное заполнение
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(DriverProvider.getDriver().findElements(By.cssSelector(".ty-menu__submenu .row-filling")).size() >=1,
                "Menu filling is not Column!");
        //Проверяем, что колонок 5
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[data-cols-count='5']")).size() >=1,
                "Menu columns are not equal 5 columns!");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu21.02 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu Electronic");
        //Проверяем, что у меню второго уровня отсутствуют иконки
        softAssert.assertFalse(DriverProvider.getDriver().findElements(By.cssSelector(".second-lvl .ut2-mwi-icon-wrap .ut2-mwi-icon")).size() >=1,
                "There are icons at the menu of the second level but shouldn't!");
        //Проверяем, что Кол-во отображаемых элементов во 2-м уровне меню -- 0
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[style='--menu-items:0;']")).size() >=1,
                "'Number of visible elements in the 2-level menu' is more than zero!");
        //Проверяем, что присутствует не меньше 10 кнопок "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-more")).size() >=10,
                "There are less than 10 buttons 'More' in the elements of the 2-level menu");
        //Проверяем, что Элементов второго уровня -- не меньше 7
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[class='second-lvl'][data-elem-index='6']")).size() >=1,
                "Number of elements in the 2-level menu is less than 7!");
        stHomePage.navigateToVerticalMenu_Apparel();
        takeScreenShot("Menu21.04 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu Apparel");
        stHomePage.navigateToVerticalMenu_SportsAndOutdoors();
        takeScreenShot("Menu21.06 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu SportsAndOutdoors");
        stHomePage.navigateToVerticalMenu_VideoGames();
        takeScreenShot("Menu21.08 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu VideoGames");

        stHomePage.menuButton_Catalog.click();
        stHomePage.selectLanguage_RTL();
        stHomePage.menuButton_Catalog.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu21.10 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu AllProducts (RTL)");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu21.12 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu Electronic (RTL)");
        stHomePage.navigateToVerticalMenu_Apparel();
        takeScreenShot("Menu21.14 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu Apparel (RTL)");
        stHomePage.navigateToVerticalMenu_SportsAndOutdoors();
        takeScreenShot("Menu21.16 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToVerticalMenu_VideoGames();
        takeScreenShot("Menu21.18 Menu21_Vertical_ColumnFilling_5columns_FullView - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}