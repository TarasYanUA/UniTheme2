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
Работаем с макетом Light v2:
Горизонтальное меню + Колоночное заполнение + 5 колонок
+ Показывать иконки для пунктов меню второго уровня -- нет
+ Кол-во отображаемых элементов во 2-м уровне меню -- 2
+ Кол-во отображаемых элементов в 3-м уровне меню -- 10
+ Элементы второго уровня -- 12
+ Элементы третьего уровня -- 6
+ Минимальная высота для меню -- 500
*/

public class Menu11_Horizontal_ColumnFilling_5columns extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu11_Horizontal_ColumnFilling_5columns(){
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_DesignLayouts();
        csCartSettings.layout_Lightv2.click();
        csCartSettings.setLayoutAsDefault();
        MenuSettings menuSettings = new MenuSettings();
        menuSettings.gearwheelOfTheBlock_MainMenu.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        menuSettings.menuSettings_buttonSettings.click();
        menuSettings.selectSetting_FillingType("column_filling");
        menuSettings.selectSetting_MaximumColumns("5");
        if(menuSettings.setting_CompactDisplayView.isSelected()){   //Выключаем Компактный вид для Горизонтального меню
            menuSettings.setting_CompactDisplayView.click();
        }
        if(menuSettings.setting_ShowIconsForMenuItems.isSelected()){
            menuSettings.setting_ShowIconsForMenuItems.click();
        }
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("2");
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("10");
        menuSettings.clickAndType_setting_SecondLevelElements("12");
        menuSettings.clickAndType_setting_ThirdLevelElements("6");
        menuSettings.clickAndType_setting_MinimumHeightForMenu("500");
        menuSettings.tab_Content.click();
        menuSettings.selectMenuContent_MainMenu();
        menuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu11_Horizontal_ColumnFilling_5columns")
    public void check_Menu11_Horizontal_ColumnFilling_5columns(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.navigateToMenu_AllProducts();
        takeScreenShot("Menu11.00 Menu11_Horizontal_ColumnFilling_5columns - Menu AllProducts");
        //Проверяем, что у меню Колоночное заполнение
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(DriverProvider.getDriver().findElements(By.cssSelector(".ty-menu__submenu .row-filling")).size() >=1,
                "Menu filling is not Column!");
        //Проверяем, что колонок 5
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[data-cols-count='5']")).size() >=1,
                "Menu columns are not equal 5 columns!");
        stHomePage.navigateToMenu_Electronic();
        takeScreenShot("Menu11.02 Menu11_Horizontal_ColumnFilling_5columns - Menu Electronic");
        //Проверяем, что у меню второго уровня отсутствуют иконки
        softAssert.assertFalse(DriverProvider.getDriver().findElements(By.cssSelector(".second-lvl .ut2-mwi-icon-wrap .ut2-mwi-icon")).size() >=1,
                "There are icons at the menu of the second level but shouldn't!");
        //Проверяем, что Кол-во отображаемых элементов во 2-м уровне меню -- 2
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[style='--menu-items:2;']")).size() >=1,
                "'Number of visible elements in the 2-level menu' is not 2!");
        //Проверяем, что присутствует не меньше 10 кнопок "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-more")).size() >=10,
                "There are less than 10 buttons 'More' in the elements of the 2-level menu");
        //Проверяем, что Элементов второго уровня -- не меньше 7
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[class='second-lvl'][data-elem-index='6']")).size() >=1,
                "Number of elements in the 2-level menu is less than 7!");
        stHomePage.navigateToMenu_Apparel();
        takeScreenShot("Menu11.04 Menu11_Horizontal_ColumnFilling_5columns - Menu Apparel");
        stHomePage.navigateToMenu_SportsAndOutdoors();
        takeScreenShot("Menu11.06 Menu11_Horizontal_ColumnFilling_5columns - Menu SportsAndOutdoors");
        stHomePage.navigateToMenu_VideoGames();
        takeScreenShot("Menu11.08 Menu11_Horizontal_ColumnFilling_5columns - Menu VideoGames");

        stHomePage.selectLanguage_RTL();
        stHomePage.navigateToMenu_Electronic();
        takeScreenShot("Menu11.10 Menu11_Horizontal_ColumnFilling_5columns - Menu Electronic (RTL)");
        stHomePage.navigateToMenu_Apparel();
        takeScreenShot("Menu11.12 Menu11_Horizontal_ColumnFilling_5columns - Menu Apparel (RTL)");
        stHomePage.navigateToMenu_SportsAndOutdoors();
        takeScreenShot("Menu11.14 Menu11_Horizontal_ColumnFilling_5columns - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToMenu_VideoGames();
        takeScreenShot("Menu11.16 Menu11_Horizontal_ColumnFilling_5columns - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}