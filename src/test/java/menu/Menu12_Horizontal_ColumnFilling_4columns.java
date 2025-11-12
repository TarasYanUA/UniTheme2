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

import java.time.Duration;

/*
Работаем с макетом Light v2:
Горизонтальное меню + Колоночное заполнение + 4 колонки
+ Элементы второго уровня -- 4
+ Элементы третьего уровня -- 4
+ Количество видимых элементов в третьем уровне меню -- 4   //Здесь эту настройку не проверяем
+ Показывать иконки для пунктов меню второго уровня -- да
+ Минимальная высота для меню -- 300
*/

public class Menu12_Horizontal_ColumnFilling_4columns extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu12_Horizontal_ColumnFilling_4columns() {
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
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("4");
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "4");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "4");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("4");
        if (!mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()) {
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        if (mainMenuSettings.setting_CompactDisplayView.isSelected()) {   //Выключаем Компактный вид для Горизонтального меню
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "300");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu12_Horizontal_ColumnFilling_4columns")
    public void check_Menu12_Horizontal_ColumnFilling_4columns() {
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.navigateToHorizontalMenu_AllProducts();
        takeScreenShot("Menu12.00 Menu12_Horizontal_ColumnFilling_4columns - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню Колоночное заполнение
        softAssert.assertTrue(assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Column!");

        //Проверяем, что колонок 4
        softAssert.assertTrue(!assertsOfMenu.columnsPerRow("4").isEmpty(),
                "Menu columns are not equal 4 columns!");
        stHomePage.navigateToHorizontalMenu_Electronic();
        takeScreenShot("Menu12.02 Menu12_Horizontal_ColumnFilling_4columns - Menu Electronic");

        //Проверяем, что у меню второго уровня присутствуют иконки
        softAssert.assertTrue(!assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are no icons at the menu of the second level!");

        //Проверяем, что Элементов второго уровня -- 4
        softAssert.assertEquals(assertsOfMenu.numberOfElements_SecondLevel.size(), 4,
                "Number of elements of the second level are not 4!");

        //Проверяем, что Элементов третьего уровня -- 4
        softAssert.assertEquals(assertsOfMenu.numberOfElements_ThirdLevel.size(), 4,
                "Number of elements of the third level are not 4!");

        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.button_MoreCategory_InTheSecondLevel.isEmpty(),
                "There is no button 'More [category]' in the second level of the menu!");

        stHomePage.navigateToHorizontalMenu_Apparel();
        takeScreenShot("Menu12.04 Menu12_Horizontal_ColumnFilling_4columns - Menu Apparel");
        stHomePage.navigateToHorizontalMenu_SportsAndOutdoors();
        takeScreenShot("Menu12.06 Menu12_Horizontal_ColumnFilling_4columns - Menu SportsAndOutdoors");
        stHomePage.navigateToHorizontalMenu_VideoGames();
        takeScreenShot("Menu12.08 Menu12_Horizontal_ColumnFilling_4columns - Menu VideoGames");

        stHomePage.selectLanguage_RTL();
        stHomePage.navigateToHorizontalMenu_Electronic();
        takeScreenShot("Menu12.10 Menu12_Horizontal_ColumnFilling_4columns - Menu Electronic (RTL)");
        stHomePage.navigateToHorizontalMenu_Apparel();
        takeScreenShot("Menu12.12 Menu12_Horizontal_ColumnFilling_4columns - Menu Apparel (RTL)");
        stHomePage.navigateToHorizontalMenu_SportsAndOutdoors();
        takeScreenShot("Menu12.14 Menu12_Horizontal_ColumnFilling_4columns - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToHorizontalMenu_VideoGames();
        takeScreenShot("Menu12.16 Menu12_Horizontal_ColumnFilling_4columns - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}