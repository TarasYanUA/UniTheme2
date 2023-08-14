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
Работаем с макетом Light v2. В этом тест-кейсе используются значения по умолчанию:
Горизонтальное меню + Строчное заполнение + 2 колонки
+ Показывать иконки для пунктов меню второго уровня -- да
+ Кол-во отображаемых элементов во 2-м уровне меню -- 5
+ Кол-во отображаемых элементов в 3-м уровне меню -- 10
+ Элементы второго уровня -- 12
+ Элементы третьего уровня -- 6
+ Минимальная высота для меню -- 500
*/

public class Menu04_Horizontal_RowFilling_2columns extends TestRunner{
    @Test(priority = 1)
    public void setConfigurations_Menu04_Horizontal_RowFilling_2columns(){
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_Layouts();
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
        menuSettings.clickAndType_setting_SecondLevelElements("12");
        menuSettings.clickAndType_setting_ThirdLevelElements("6");
        menuSettings.clickAndType_setting_MinimumHeightForMenu("500");
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
        takeScreenShot_withoutScroll("Menu400 Menu04_Horizontal_RowFilling_2columns - Menu AllProducts");
        //Проверяем, что у меню Строчное заполнение
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-menu__submenu .row-filling")).size() >=1,
                "Menu filling is not Row!");
        //Проверяем, что колонок 2
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[data-cols-count='2']")).size() >=1,
                "Menu columns are not equal 2 columns!");
        stHomePage.navigateToMenu_Electronic();
        takeScreenShot_withoutScroll("Menu402 Menu04_Horizontal_RowFilling_2columns - Menu Electronic");
        //Проверяем, что у меню второго уровня есть иконки
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".second-lvl .ut2-mwi-icon-wrap .ut2-mwi-icon")).size() >=1,
                "There are no icons at the menu of the second level!");
        //Проверяем, что Кол-во отображаемых элементов во 2-м уровне меню -- 5
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[style='--menu-items:5;']")).size() >=1,
                "'Number of visible elements in the 2-level menu' is not 5!");
        //Проверяем, что присутствует не меньше 3 кнопок "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-more")).size() >=3,
                "There are less than 3 buttons 'More' in the elements of the 2-level menu");
        //Проверяем, что Элементов второго уровня -- не меньше 6
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[class='second-lvl'][data-elem-index='6']")).size() >=1,
                "Number of elements of the 2-level is less than 6!");
        softAssert.assertAll();
        stHomePage.navigateToMenu_Apparel();
        takeScreenShot_withoutScroll("Menu404 Menu04_Horizontal_RowFilling_2columns - Menu Apparel");
        stHomePage.navigateToMenu_SportsAndOutdoors();
        takeScreenShot_withoutScroll("Menu406 Menu04_Horizontal_RowFilling_2columns - Menu SportsAndOutdoors");
        stHomePage.navigateToMenu_VideoGames();
        takeScreenShot_withoutScroll("Menu408 Menu04_Horizontal_RowFilling_2columns - Menu VideoGames");

        stHomePage.selectLanguage_RTL();
        stHomePage.navigateToMenu_Electronic();
        takeScreenShot_withoutScroll("Menu410 Menu04_Horizontal_RowFilling_2columns - Menu Electronic (RTL)");
        stHomePage.navigateToMenu_Apparel();
        takeScreenShot_withoutScroll("Menu412 Menu04_Horizontal_RowFilling_2columns - Menu Apparel (RTL)");
        stHomePage.navigateToMenu_SportsAndOutdoors();
        takeScreenShot_withoutScroll("Menu414 Menu04_Horizontal_RowFilling_2columns - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToMenu_VideoGames();
        takeScreenShot_withoutScroll("Menu416 Menu04_Horizontal_RowFilling_2columns - Menu VideoGames (RTL)");
    }
}