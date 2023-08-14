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
Горизонтальное меню + Строчное заполнение + 4 колонки
+ Показывать иконки для пунктов меню второго уровня -- да
+ Кол-во отображаемых элементов во 2-м уровне меню -- 4
+ Кол-во отображаемых элементов в 3-м уровне меню -- 10
+ Элементы второго уровня -- 4
+ Элементы третьего уровня -- 4
+ Минимальная высота для меню -- 300
*/

public class Menu03_Horizontal_RowFilling_4columns extends TestRunner{
    @Test(priority = 1)
    public void setConfigurations_Menu03_Horizontal_RowFilling_4columns(){
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
        menuSettings.selectSetting_MaximumColumns("4");
        if(menuSettings.setting_CompactDisplayView.isSelected()){   //Выключаем Компактный вид для Горизонтального меню
            menuSettings.setting_CompactDisplayView.click();
        }
        if(!menuSettings.setting_ShowIconsForMenuItems.isSelected()){
            menuSettings.setting_ShowIconsForMenuItems.click();
        }
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("4");
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("10");
        menuSettings.clickAndType_setting_SecondLevelElements("4");
        menuSettings.clickAndType_setting_ThirdLevelElements("4");
        menuSettings.clickAndType_setting_MinimumHeightForMenu("300");
        menuSettings.tab_Content.click();
        menuSettings.selectMenuContent_MainMenu();
        menuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu03_Horizontal_RowFilling_4columns")
    public void check_Menu03_Horizontal_RowFilling_4columns(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.navigateToMenu_AllProducts();
        takeScreenShot_withoutScroll("Menu300 Menu03_Horizontal_RowFilling_4columns - Menu AllProducts");
        //Проверяем, что у меню Строчное заполнение
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-menu__submenu .row-filling")).size() >=1,
                "Menu filling is not Row!");
        //Проверяем, что колонок 4
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[data-cols-count='4']")).size() >=1,
                "Menu columns are not equal 4 columns!");
        stHomePage.navigateToMenu_Electronic();
        takeScreenShot_withoutScroll("Menu302 Menu03_Horizontal_RowFilling_4columns - Menu Electronic");
        //Проверяем, что у меню второго уровня есть иконки
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".second-lvl .ut2-mwi-icon-wrap .ut2-mwi-icon")).size() >=1,
                "There are no icons at the menu of the second level!");
        //Проверяем, что отсутствуют кнопки "Ещё" у элементов во 2-м уровне меню
        softAssert.assertFalse(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-more")).size() >=1,
                "There are buttons 'More' in the elements of the 2-level menu but shouldn't!");
        //Проверяем, что Элементов второго уровня -- не меньше 3
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[class='second-lvl'][data-elem-index='3']")).size() >=1,
                "Number of elements of the 2-level is less than 3!");
        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-menu__submenu-alt-link")).size() >=1,
                "There is no button 'More [category]' in the 2-level menu!");
        stHomePage.navigateToMenu_Apparel();
        takeScreenShot_withoutScroll("Menu304 Menu03_Horizontal_RowFilling_4columns - Menu Apparel");
        stHomePage.navigateToMenu_SportsAndOutdoors();
        takeScreenShot_withoutScroll("Menu306 Menu03_Horizontal_RowFilling_4columns - Menu SportsAndOutdoors");
        stHomePage.navigateToMenu_VideoGames();
        takeScreenShot_withoutScroll("Menu308 Menu03_Horizontal_RowFilling_4columns - Menu VideoGames");

        stHomePage.selectLanguage_RTL();
        stHomePage.navigateToMenu_Electronic();
        takeScreenShot_withoutScroll("Menu310 Menu03_Horizontal_RowFilling_4columns - Menu Electronic (RTL)");
        stHomePage.navigateToMenu_Apparel();
        takeScreenShot_withoutScroll("Menu312 Menu03_Horizontal_RowFilling_4columns - Menu Apparel (RTL)");
        stHomePage.navigateToMenu_SportsAndOutdoors();
        takeScreenShot_withoutScroll("Menu314 Menu03_Horizontal_RowFilling_4columns - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToMenu_VideoGames();
        takeScreenShot_withoutScroll("Menu316 Menu03_Horizontal_RowFilling_4columns - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}