package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.BasicPage;
import taras.adminPanel.LayoutPage;
import taras.adminPanel.MainMenuSettings;
import taras.adminPanel.UtilsAdm;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOfMenu;
import taras.storefront.StHomePage;
import taras.storefront.UtilsStorefront;

import java.time.Duration;

/*
Работаем с макетом Light v2:
Горизонтальное меню + Колоночное заполнение + 6 колонок
+ Элементы второго уровня -- 12
+ Элементы третьего уровня -- 7
+ Количество видимых элементов в третьем уровне меню -- 5
+ Показывать иконки для пунктов меню второго уровня -- да
+ Минимальная высота для меню -- 500
*/

public class Menu10_Horizontal_ColumnFilling_6columns extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu10_Horizontal_ColumnFilling_6columns(){
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Lightv2.click();
        layoutPage.setLayoutAsDefault();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.gearwheelOfTheBlock_MainMenu_LightV2.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        mainMenuSettings.menuSettings_buttonSettings.click();
        new Select(mainMenuSettings.setting_FillingType).selectByValue("column_filling");
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("6");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("5");
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "12");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "7");
        if(!mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()){
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        if(mainMenuSettings.setting_CompactDisplayView.isSelected()){   //Выключаем Компактный вид для Горизонтального меню
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "500");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu10_Horizontal_ColumnFilling_6columns")
    public void check_Menu10_Horizontal_ColumnFilling_6columns(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_AllProducts);
        takeScreenShot("Menu10.00 Menu10_Horizontal_ColumnFilling_6columns - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню Колоночное заполнение
        softAssert.assertTrue(assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Column!");

        //Проверяем, что колонок 6
        softAssert.assertTrue(!assertsOfMenu.columnsPerRow("6").isEmpty(),
                "Menu columns are not equal 6 columns!");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu10.02 Menu10_Horizontal_ColumnFilling_6columns - Menu Electronic");

        //Проверяем, что у меню второго уровня присутствуют иконки
        softAssert.assertTrue(!assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are no icons at the menu of the second level!");

        //Проверяем, что Элементов второго уровня -- 7
        softAssert.assertEquals(assertsOfMenu.numberOfElements_SecondLevel.size(), 7,
                "Number of elements of the 2-level is not 7!");

        //Проверяем, что Элементов третьего уровня -- 7
        softAssert.assertEquals(assertsOfMenu.numberOfElements_ThirdLevel.size(), 7,
                "Number of elements of the third level is not 7!");

        //Проверяем, что Количество видимых элементов в третьем уровне меню -- 5
        softAssert.assertTrue(!assertsOfMenu.numberOfVisibleElementsIn_3levelMenu("5").isEmpty(),
                "'Number of visible elements in the 3-level menu' is not 5!");

        //Проверяем, что присутствует не меньше 3 кнопок "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(assertsOfMenu.button_More_InElementsOf2levelMenu.size() >= 3,
                "There are less than 3 buttons 'More' in the elements of the second level of the menu!");

        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_Apparel);
        takeScreenShot("Menu10.04 Menu10_Horizontal_ColumnFilling_6columns - Menu Apparel");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_SportsAndOutdoors);
        takeScreenShot("Menu10.06 Menu10_Horizontal_ColumnFilling_6columns - Menu SportsAndOutdoors");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_VideoGames);
        takeScreenShot("Menu10.08 Menu10_Horizontal_ColumnFilling_6columns - Menu VideoGames");

        stHomePage.selectLanguage("ar");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu10.10 Menu10_Horizontal_ColumnFilling_6columns - Menu Electronic (RTL)");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_Apparel);
        takeScreenShot("Menu10.12 Menu10_Horizontal_ColumnFilling_6columns - Menu Apparel (RTL)");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_SportsAndOutdoors);
        takeScreenShot("Menu10.14 Menu10_Horizontal_ColumnFilling_6columns - Menu SportsAndOutdoors (RTL)");
        UtilsStorefront.hoverOverElement(stHomePage.horizontalMenu_VideoGames);
        takeScreenShot("Menu10.16 Menu10_Horizontal_ColumnFilling_6columns - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}