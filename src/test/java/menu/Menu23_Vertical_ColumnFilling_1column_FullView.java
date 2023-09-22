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
Вертикальное меню + Колоночное заполнение + 1 колонка
+ Компактный вид отображения -- нет
+ Показывать иконки для пунктов меню второго уровня -- нет
+ Кол-во отображаемых элементов во 2-м уровне меню -- 5
+ Кол-во отображаемых элементов в 3-м уровне меню -- 10
+ Элементы второго уровня -- 3
+ Элементы третьего уровня -- 6
+ Минимальная высота для меню -- 500
*/

public class Menu23_Vertical_ColumnFilling_1column_FullView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu23_Vertical_ColumnFilling_1column_FullView(){
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
        menuSettings.selectSetting_MaximumColumns("1");
        if(menuSettings.setting_CompactDisplayView.isSelected()){
            menuSettings.setting_CompactDisplayView.click();
        }
        if(menuSettings.setting_ShowIconsForMenuItems.isSelected()){
            menuSettings.setting_ShowIconsForMenuItems.click();
        }
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("5");
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("10");
        menuSettings.clickAndType_setting_SecondLevelElements("3");
        menuSettings.clickAndType_setting_ThirdLevelElements("6");
        menuSettings.clickAndType_setting_MinimumHeightForMenu("500");
        menuSettings.tab_Content.click();
        menuSettings.selectMenuContent_MainMenu();
        menuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu23_Vertical_ColumnFilling_1column_FullView")
    public void check_Menu23_Vertical_ColumnFilling_1column_FullView(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Catalog.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu23.00 Menu23_Vertical_ColumnFilling_1column_FullView - Menu AllProducts");
        //Проверяем, что у меню Колоночное заполнение
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(DriverProvider.getDriver().findElements(By.cssSelector(".ty-menu__submenu .row-filling")).size() >=1,
                "Menu filling is not Column!");
        //Проверяем, что присутствует 1 колонка
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[data-cols-count='1']")).size() >=1,
                "Menu columns are not equal 1 column!");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu23.02 Menu23_Vertical_ColumnFilling_1column_FullView - Menu Electronic");
        //Проверяем, что у меню второго уровня отсутствуют иконки
        softAssert.assertFalse(DriverProvider.getDriver().findElements(By.cssSelector(".second-lvl .ut2-mwi-icon-wrap .ut2-mwi-icon")).size() >=1,
                "There are icons at the menu of the second level but shouldn't!");
        //Проверяем, что Кол-во отображаемых элементов во 2-м уровне меню -- 5
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[style='--menu-items:5;']")).size() >=1,
                "'Number of visible elements in the 2-level menu' is not 5!");
        //Проверяем, что присутствует не меньше 3 кнопок "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-more")).size() >=3,
                "There are less than 3 buttons 'More' in the elements of the 2-level menu");
        //Проверяем, что Элементов второго уровня -- 3
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[class='second-lvl'][data-elem-index='2']")).size() >=1,
                "Number of elements of the 2-level is less than 3!");
        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-menu__submenu-alt-link")).size() >=1,
                "There is no button 'More [category]' in the 2-level menu!");
        stHomePage.navigateToVerticalMenu_Apparel();
        takeScreenShot("Menu23.04 Menu23_Vertical_ColumnFilling_1column_FullView - Menu Apparel");
        stHomePage.navigateToVerticalMenu_SportsAndOutdoors();
        takeScreenShot("Menu23.06 Menu23_Vertical_ColumnFilling_1column_FullView - Menu SportsAndOutdoors");
        stHomePage.navigateToVerticalMenu_VideoGames();
        takeScreenShot("Menu23.08 Menu23_Vertical_ColumnFilling_1column_FullView - Menu VideoGames");

        stHomePage.verticalMenu_menuButton_Catalog.click();
        stHomePage.selectLanguage_RTL();
        stHomePage.verticalMenu_menuButton_Catalog.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu23.10 Menu23_Vertical_ColumnFilling_1column_FullView - Menu AllProducts (RTL)");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu23.12 Menu23_Vertical_ColumnFilling_1column_FullView - Menu Electronic (RTL)");
        stHomePage.navigateToVerticalMenu_Apparel();
        takeScreenShot("Menu23.14 Menu23_Vertical_ColumnFilling_1column_FullView - Menu Apparel (RTL)");
        stHomePage.navigateToVerticalMenu_SportsAndOutdoors();
        takeScreenShot("Menu23.16 Menu23_Vertical_ColumnFilling_1column_FullView - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToVerticalMenu_VideoGames();
        takeScreenShot("Menu23.18 Menu23_Vertical_ColumnFilling_1column_FullView - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}