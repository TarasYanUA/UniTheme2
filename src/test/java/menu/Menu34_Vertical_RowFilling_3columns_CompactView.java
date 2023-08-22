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
Вертикальное меню + Строчное заполнение + 3 колонки + Компактный вид
+ Показывать иконки для пунктов меню второго уровня -- да
+ Кол-во отображаемых элементов во 2-м уровне меню -- 5
+ Кол-во отображаемых элементов в 3-м уровне меню -- 30
+ Элементы второго уровня -- 30
+ Элементы третьего уровня -- 30
+ Минимальная высота для меню -- 600
*/

public class Menu34_Vertical_RowFilling_3columns_CompactView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu34_Vertical_RowFilling_3columns_CompactView(){
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
        menuSettings.selectSetting_FillingType("row_filling");
        menuSettings.selectSetting_MaximumColumns("3");
        if(!menuSettings.setting_CompactDisplayView.isSelected()){
            menuSettings.setting_CompactDisplayView.click();
        }
        if(!menuSettings.setting_ShowIconsForMenuItems.isSelected()){
            menuSettings.setting_ShowIconsForMenuItems.click();
        }
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("5");
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("30");
        menuSettings.clickAndType_setting_SecondLevelElements("30");
        menuSettings.clickAndType_setting_ThirdLevelElements("30");
        menuSettings.clickAndType_setting_MinimumHeightForMenu("600");
        menuSettings.tab_Content.click();
        menuSettings.selectMenuContent_MainMenu();
        menuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu34_Vertical_RowFilling_3columns_CompactView")
    public void check_Menu34_Vertical_RowFilling_3columns_CompactView(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.menuButton_Catalog.click();
        stHomePage.navigateToMenu_AllProducts();
        takeScreenShot("Menu34.00 Menu34_Vertical_RowFilling_3columns_CompactView - Menu AllProducts");
        //Проверяем, что у меню Строчное заполнение
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-menu__submenu .row-filling")).size() >=1,
                "Menu filling is not Row!");
        //Проверяем, что колонок 3
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[data-cols-count='3']")).size() >=1,
                "Menu columns are not equal 3 columns!");
        stHomePage.navigateToMenu_Electronic();
        takeScreenShot("Menu34.02 Menu34_Vertical_RowFilling_3columns_CompactView - Menu Electronic");
        //Проверяем, что у меню второго уровня есть иконки
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".second-lvl .ut2-mwi-icon-wrap .ut2-mwi-icon")).size() >=1,
                "There are no icons at the menu of the second level!");
        //Проверяем, что Кол-во отображаемых элементов во 2-м уровне меню -- 5
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[style='--menu-items:5;']")).size() >=1,
                "'Number of visible elements in the 2-level menu' is not 5!");
        //Проверяем, что присутствует не меньше 3 кнопок "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-more")).size() >=3,
                "There are less than three buttons 'More' in the elements of the 2-level menu");
        //Проверяем, что Элементов второго уровня -- 4
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[class='second-lvl'][data-elem-index='3']")).size() >=1,
                "Number of elements of the 2-level is less than 4!");
        stHomePage.navigateToMenu_Apparel();
        takeScreenShot("Menu34.04 Menu34_Vertical_RowFilling_3columns_CompactView - Menu Apparel");
        stHomePage.navigateToMenu_SportsAndOutdoors();
        takeScreenShot("Menu34.06 Menu34_Vertical_RowFilling_3columns_CompactView - Menu SportsAndOutdoors");
        stHomePage.navigateToMenu_VideoGames();
        takeScreenShot("Menu34.08 Menu34_Vertical_RowFilling_3columns_CompactView - Menu VideoGames");

        stHomePage.menuButton_Catalog.click();
        stHomePage.selectLanguage_RTL();
        stHomePage.menuButton_Catalog.click();
        stHomePage.navigateToMenu_AllProducts();
        takeScreenShot("Menu34.10 Menu34_Vertical_RowFilling_3columns_CompactView - Menu AllProducts (RTL)");
        stHomePage.navigateToMenu_Electronic();
        takeScreenShot("Menu34.12 Menu34_Vertical_RowFilling_3columns_CompactView - Menu Electronic (RTL)");
        stHomePage.navigateToMenu_Apparel();
        takeScreenShot("Menu34.14 Menu34_Vertical_RowFilling_3columns_CompactView - Menu Apparel (RTL)");
        stHomePage.navigateToMenu_SportsAndOutdoors();
        takeScreenShot("Menu34.16 Menu34_Vertical_RowFilling_3columns_CompactView - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToMenu_VideoGames();
        takeScreenShot("Menu34.18 Menu34_Vertical_RowFilling_3columns_CompactView - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}