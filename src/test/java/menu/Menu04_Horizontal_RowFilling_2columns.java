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
Горизонтальное меню + Строчное заполнение + 2 колонки
+ Показывать иконки для пунктов меню второго уровня -- да
+ Кол-во отображаемых элементов во 2-м уровне меню -- 5 (здесь данная настройка роли не играет по причине настройки 'Элементы третьего уровня')
+ Кол-во отображаемых элементов в 3-м уровне меню -- 10
+ Элементы второго уровня -- 5 (здесь данная настройка роли не играет по причине настройки ниже)
+ Элементы третьего уровня -- 0
+ Минимальная высота для меню -- 700
*/

public class Menu04_Horizontal_RowFilling_2columns extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu04_Horizontal_RowFilling_2columns(){
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
        menuSettings.selectSetting_FillingType("row_filling");
        menuSettings.selectSetting_MaximumColumns("2");
        if(menuSettings.setting_CompactDisplayView.isSelected()){   //Выключаем Компактный вид для Горизонтального меню
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

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu04_Horizontal_RowFilling_2columns")
    public void check_Menu04_Horizontal_RowFilling_2columns(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.navigateToMenu_AllProducts();
        takeScreenShot("Menu4.00 Menu04_Horizontal_RowFilling_2columns - Menu AllProducts");
        //Проверяем, что у меню Строчное заполнение
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-menu__submenu .row-filling")).size() >=1,
                "Menu filling is not Row!");
        //Проверяем, что колонок 2
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[data-cols-count='2']")).size() >=1,
                "Menu columns are not equal 2 columns!");
        stHomePage.navigateToMenu_Electronic();
        takeScreenShot("Menu4.02 Menu04_Horizontal_RowFilling_2columns - Menu Electronic");
        //Проверяем, что у меню второго уровня присутствуют иконки
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-menu__submenu-link.item-icon")).size() >=1,
                "There are no icons at the menu of the second level!");
        //Проверяем, что Элементов второго уровня -- 0
        softAssert.assertFalse(DriverProvider.getDriver().findElements(By.cssSelector("div[class='second-lvl'][data-elem-index='0']")).size() >=1,
                "Number of elements of the 2-level is more than zero!");
        stHomePage.navigateToMenu_Apparel();
        takeScreenShot("Menu4.04 Menu04_Horizontal_RowFilling_2columns - Menu Apparel");
        stHomePage.navigateToMenu_SportsAndOutdoors();
        takeScreenShot("Menu4.06 Menu04_Horizontal_RowFilling_2columns - Menu SportsAndOutdoors");
        stHomePage.navigateToMenu_VideoGames();
        takeScreenShot("Menu4.08 Menu04_Horizontal_RowFilling_2columns - Menu VideoGames");

        stHomePage.selectLanguage_RTL();
        stHomePage.navigateToMenu_Electronic();
        takeScreenShot("Menu4.10 Menu04_Horizontal_RowFilling_2columns - Menu Electronic (RTL)");
        stHomePage.navigateToMenu_Apparel();
        takeScreenShot("Menu4.12 Menu04_Horizontal_RowFilling_2columns - Menu Apparel (RTL)");
        stHomePage.navigateToMenu_SportsAndOutdoors();
        takeScreenShot("Menu4.14 Menu04_Horizontal_RowFilling_2columns - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToMenu_VideoGames();
        takeScreenShot("Menu4.16 Menu04_Horizontal_RowFilling_2columns - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}