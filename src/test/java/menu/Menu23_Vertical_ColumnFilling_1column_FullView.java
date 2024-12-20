package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.MainMenuSettings;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOfMenu;
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
        csCartSettings.navigateToSection_WebsiteLayouts();
        csCartSettings.layout_Light.click();
        csCartSettings.setLayoutAsDefault();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.gearwheelOfTheBlock_Categories_Light.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        mainMenuSettings.menuSettings_buttonSettings.click();
        mainMenuSettings.selectSetting_FillingType("column_filling");
        mainMenuSettings.selectSetting_MaximumColumns("1");
        if(mainMenuSettings.setting_CompactDisplayView.isSelected()){
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        if(mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()){
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("5");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("10");
        mainMenuSettings.clickAndType_setting_SecondLevelElements("3");
        mainMenuSettings.clickAndType_setting_ThirdLevelElements("6");
        mainMenuSettings.clickAndType_setting_MinimumHeightForMenu("500");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu23_Vertical_ColumnFilling_1column_FullView")
    public void check_Menu23_Vertical_ColumnFilling_1column_FullView(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu23.00 Menu23_Vertical_ColumnFilling_1column_FullView - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();
        //Проверяем, что у меню Колоночное заполнение
        softAssert.assertTrue(assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Column!");
        //Проверяем, что присутствует 1 колонка
        softAssert.assertTrue(!assertsOfMenu.oneColumn.isEmpty(),
                "Menu columns are not equal 1 column!");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu23.02 Menu23_Vertical_ColumnFilling_1column_FullView - Menu Electronic");
        //Проверяем, что у меню второго уровня отсутствуют иконки
        softAssert.assertFalse(!assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are icons at the menu of the second level but shouldn't!");
        //Проверяем, что Кол-во отображаемых элементов в 3-м уровне меню -- 5
        softAssert.assertTrue(!assertsOfMenu.numberOfElementsIn3levelMenu_Five.isEmpty(),
                "'Number of visible elements in the 3-level menu' is not 5!");
        //Проверяем, что Элементов второго уровня -- 3
        softAssert.assertTrue(assertsOfMenu.numberOfElements_SecondLevel.size() == 3,
                "Number of elements of the second level is not 3!");
        //Проверяем, что Элементов третьего уровня -- 6
        softAssert.assertTrue(assertsOfMenu.numberOfElements_ThirdLevel.size() == 6,
                "Number of elements of the third level is not 6!");
        //Проверяем, что присутствует кнопка "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(!assertsOfMenu.button_MoreInElementsOf2levelMenu.isEmpty(),
                "There are no buttons 'More' in the elements of the 2-level menu!");
        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.button_MoreCategoryInTheSecondLevel.isEmpty(),
                "There is no button 'More [category]' in the second level of the menu!");
        stHomePage.navigateToVerticalMenu_Apparel();
        takeScreenShot("Menu23.04 Menu23_Vertical_ColumnFilling_1column_FullView - Menu Apparel");
        stHomePage.navigateToVerticalMenu_SportsAndOutdoors();
        takeScreenShot("Menu23.06 Menu23_Vertical_ColumnFilling_1column_FullView - Menu SportsAndOutdoors");
        stHomePage.navigateToVerticalMenu_VideoGames();
        takeScreenShot("Menu23.08 Menu23_Vertical_ColumnFilling_1column_FullView - Menu VideoGames");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage_RTL();
        stHomePage.verticalMenu_menuButton_Categories.click();
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