package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.BasicPage;
import taras.adminPanel.MainMenuSettings;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOfMenu;
import taras.storefront.StHomePage;

import java.time.Duration;

/*
Работаем с макетом Light:
Вертикальное меню + Колоночное заполнение + 2 колонки + Компактный вид
+ Элементы второго уровня -- 5
+ Элементы третьего уровня -- 5
+ Количество видимых элементов в третьем уровне меню -- 2
+ Показывать иконки для пунктов меню второго уровня -- да
+ Минимальная высота для меню -- 700
*/

public class Menu24_Vertical_ColumnFilling_2columns_CompactView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu24_Vertical_ColumnFilling_2columns_CompactView(){
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        BasicPage basicPage = new BasicPage();
        basicPage.navigateToSection_WebsiteLayouts();
        basicPage.layout_Light.click();
        basicPage.setLayoutAsDefault();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.gearwheelOfTheBlock_Categories_Light.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        mainMenuSettings.menuSettings_buttonSettings.click();
        mainMenuSettings.selectSetting_FillingType("column_filling");
        mainMenuSettings.selectSetting_MaximumColumns("2");
        mainMenuSettings.clickAndType_setting_SecondLevelElements("5");
        mainMenuSettings.clickAndType_setting_ThirdLevelElements("5");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("2");
        if(!mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()){
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        if(!mainMenuSettings.setting_CompactDisplayView.isSelected()){
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        mainMenuSettings.clickAndType_setting_MinimumHeightForMenu("700");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu24_Vertical_ColumnFilling_2columns_CompactView")
    public void check_Menu24_Vertical_ColumnFilling_2columns_CompactView(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu24.00 Menu24_Vertical_ColumnFilling_2columns_CompactView - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню Колоночное заполнение
        softAssert.assertTrue(assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Column!");

        //Проверяем, что колонок 2
        softAssert.assertTrue(!assertsOfMenu.columnsPerRow("2").isEmpty(),
                "Menu columns are not equal 2 columns!");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu24.02 Menu24_Vertical_ColumnFilling_2columns_CompactView - Menu Electronic");

        //Проверяем, что у меню второго уровня присутствуют иконки
        softAssert.assertTrue(!assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are no icons at the menu of the second level!");

        //Проверяем, что Элементов второго уровня -- 5
        softAssert.assertEquals(assertsOfMenu.numberOfElements_SecondLevel.size(), 5,
                "Number of elements of the second level is not 5!");

        //Проверяем, что Элементов третьего уровня -- 5
        softAssert.assertEquals(assertsOfMenu.numberOfElements_ThirdLevel.size(), 5,
                "Number of elements of the third level is not 5!");

        //Проверяем, что Кол-во отображаемых элементов в 3-м уровне меню -- 2
        softAssert.assertTrue(!assertsOfMenu.numberOfVisibleElementsIn_3levelMenu("2").isEmpty(),
                "'Number of visible elements in the 3-level menu' is not 2!");

        //Проверяем, что присутствует не меньше 10 кнопок "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(assertsOfMenu.button_More_InElementsOf2levelMenu.size() >= 10,
                "There are less than 10 buttons 'More' in the elements of the second level of the menu!");

        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.button_MoreCategory_InTheSecondLevel.isEmpty(),
                "There is no button 'More [category]' in the second level of the menu!");

        stHomePage.navigateToVerticalMenu_Apparel();
        takeScreenShot("Menu24.04 Menu24_Vertical_ColumnFilling_2columns_CompactView - Menu Apparel");
        stHomePage.navigateToVerticalMenu_SportsAndOutdoors();
        takeScreenShot("Menu24.06 Menu24_Vertical_ColumnFilling_2columns_CompactView - Menu SportsAndOutdoors");
        stHomePage.navigateToVerticalMenu_VideoGames();
        takeScreenShot("Menu24.08 Menu24_Vertical_ColumnFilling_2columns_CompactView - Menu VideoGames");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage_RTL();
        stHomePage.verticalMenu_menuButton_Categories.click();
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