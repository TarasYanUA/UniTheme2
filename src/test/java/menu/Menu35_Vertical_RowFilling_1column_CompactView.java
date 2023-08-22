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
Вертикальное меню + Строчное заполнение + 1 колонка + Компактный вид
+ Показывать иконки для пунктов меню второго уровня -- нет
+ Кол-во отображаемых элементов во 2-м уровне меню -- 5 (здесь данная настройка роли не играет по причине настройки 'Элементы третьего уровня')
+ Кол-во отображаемых элементов в 3-м уровне меню -- 10
+ Элементы второго уровня -- 5 (здесь данная настройка роли не играет по причине настройки ниже)
+ Элементы третьего уровня -- 0
+ Минимальная высота для меню -- 600
*/

public class Menu35_Vertical_RowFilling_1column_CompactView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu35_Vertical_RowFilling_1column_CompactView(){
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
        menuSettings.selectSetting_MaximumColumns("1");
        if(!menuSettings.setting_CompactDisplayView.isSelected()){
            menuSettings.setting_CompactDisplayView.click();
        }
        if(menuSettings.setting_ShowIconsForMenuItems.isSelected()){
            menuSettings.setting_ShowIconsForMenuItems.click();
        }
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("5");
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("10");
        menuSettings.clickAndType_setting_SecondLevelElements("5");
        menuSettings.clickAndType_setting_ThirdLevelElements("0");
        menuSettings.clickAndType_setting_MinimumHeightForMenu("600");
        menuSettings.tab_Content.click();
        menuSettings.selectMenuContent_MainMenu();
        menuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu35_Vertical_RowFilling_1column_CompactView")
    public void check_Menu35_Vertical_RowFilling_1column_CompactView(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.menuButton_Catalog.click();
        stHomePage.navigateToMenu_AllProducts();
        takeScreenShot("Menu35.00 Menu35_Vertical_RowFilling_1column_CompactView - Menu AllProducts");
        //Проверяем, что у меню Строчное заполнение
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-menu__submenu .row-filling")).size() >=1,
                "Menu filling is not Row!");
        //Проверяем, что присутствует 1 колонка
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[data-cols-count='1']")).size() >=1,
                "Menu columns are not equal 1 column!");
        stHomePage.navigateToMenu_Electronic();
        takeScreenShot("Menu35.02 Menu35_Vertical_RowFilling_1column_CompactView - Menu Electronic");
        //Проверяем, что у меню второго уровня отсутствуют иконки
        softAssert.assertFalse(DriverProvider.getDriver().findElements(By.cssSelector(".second-lvl .ut2-mwi-icon-wrap .ut2-mwi-icon")).size() >=1,
                "There are icons at the menu of the second level but shouldn't!");
        //Проверяем, что Элементов второго уровня -- 0
        softAssert.assertFalse(DriverProvider.getDriver().findElements(By.cssSelector("div[class='second-lvl'][data-elem-index='0']")).size() >=1,
                "Number of elements of the 2-level is more than zero!");
        stHomePage.navigateToMenu_Apparel();
        takeScreenShot("Menu35.04 Menu35_Vertical_RowFilling_1column_CompactView - Menu Apparel");
        stHomePage.navigateToMenu_SportsAndOutdoors();
        takeScreenShot("Menu35.06 Menu35_Vertical_RowFilling_1column_CompactView - Menu SportsAndOutdoors");
        stHomePage.navigateToMenu_VideoGames();
        takeScreenShot("Menu35.08 Menu35_Vertical_RowFilling_1column_CompactView - Menu VideoGames");

        stHomePage.menuButton_Catalog.click();
        stHomePage.selectLanguage_RTL();
        stHomePage.menuButton_Catalog.click();
        stHomePage.navigateToMenu_AllProducts();
        takeScreenShot("Menu35.10 Menu35_Vertical_RowFilling_1column_CompactView - Menu AllProducts (RTL)");
        stHomePage.navigateToMenu_Electronic();
        takeScreenShot("Menu35.12 Menu35_Vertical_RowFilling_1column_CompactView - Menu Electronic (RTL)");
        stHomePage.navigateToMenu_Apparel();
        takeScreenShot("Menu35.14 Menu35_Vertical_RowFilling_1column_CompactView - Menu Apparel (RTL)");
        stHomePage.navigateToMenu_SportsAndOutdoors();
        takeScreenShot("Menu35.16 Menu35_Vertical_RowFilling_1column_CompactView - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToMenu_VideoGames();
        takeScreenShot("Menu35.18 Menu35_Vertical_RowFilling_1column_CompactView - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}