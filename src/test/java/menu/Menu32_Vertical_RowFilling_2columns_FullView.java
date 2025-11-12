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
Работаем с макетом Light:
Вертикальное меню + Строчное заполнение + 2 колонки
+ Элементы второго уровня -- 5
+ Элементы третьего уровня -- 7
+ Количество видимых элементов в третьем уровне меню -- 1
+ Показывать иконки для пунктов меню второго уровня -- да
+ Компактный вид отображения -- нет
+ Минимальная высота для меню -- 700
*/

public class Menu32_Vertical_RowFilling_2columns_FullView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu32_Vertical_RowFilling_2columns_FullView() {
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Light.click();
        layoutPage.setLayoutAsDefault();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.gearwheelOfTheBlock_Categories_Light.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        mainMenuSettings.menuSettings_buttonSettings.click();
        new Select(mainMenuSettings.setting_FillingType).selectByValue("row_filling");
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("2");
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "5");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "7");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("1");
        if (!mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()) {
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        if (mainMenuSettings.setting_CompactDisplayView.isSelected()) {
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "700");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu32_Vertical_RowFilling_2columns_FullView")
    public void check_Menu32_Vertical_RowFilling_2columns_FullView() {
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu32.00 Menu32_Vertical_RowFilling_2columns_FullView - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню Строчное заполнение
        softAssert.assertTrue(!assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Row!");

        //Проверяем, что колонок 2
        softAssert.assertTrue(!assertsOfMenu.columnsPerRow("2").isEmpty(),
                "Menu columns are not equal 2 columns!");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu32.02 Menu32_Vertical_RowFilling_2columns_FullView - Menu Electronic");

        //Проверяем, что у меню второго уровня присутствуют иконки
        softAssert.assertTrue(!assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are no icons at the menu of the second level!");

        //Проверяем, что Элементов второго уровня -- 5
        softAssert.assertEquals(assertsOfMenu.numberOfElements_SecondLevel.size(), 5,
                "Number of elements of the second level is not 5!");

        //Проверяем, что Элементов третьего уровня -- 7
        softAssert.assertEquals(assertsOfMenu.numberOfElements_ThirdLevel.size(), 7,
                "Number of elements of the third level is not 7!");

        //Проверяем, что Количество видимых элементов в третьем уровне меню -- 1
        softAssert.assertTrue(!assertsOfMenu.numberOfVisibleElementsIn_3levelMenu("1").isEmpty(),
                "'Number of visible elements in the 3-level menu' is not 1!");

        //Проверяем, что присутствует не меньше 10 кнопок "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(assertsOfMenu.button_More_InElementsOf2levelMenu.size() >= 10,
                "There are less than 10 buttons 'More' in the elements of the second level of the menu!");

        stHomePage.navigateToVerticalMenu_Apparel();
        takeScreenShot("Menu32.04 Menu32_Vertical_RowFilling_2columns_FullView - Menu Apparel");
        stHomePage.navigateToVerticalMenu_SportsAndOutdoors();
        takeScreenShot("Menu32.06 Menu32_Vertical_RowFilling_2columns_FullView - Menu SportsAndOutdoors");
        stHomePage.navigateToVerticalMenu_VideoGames();
        takeScreenShot("Menu32.08 Menu32_Vertical_RowFilling_2columns_FullView - Menu VideoGames");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage_RTL();
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu32.10 Menu32_Vertical_RowFilling_2columns_FullView - Menu AllProducts (RTL)");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu32.12 Menu32_Vertical_RowFilling_2columns_FullView - Menu Electronic (RTL)");
        stHomePage.navigateToVerticalMenu_Apparel();
        takeScreenShot("Menu32.14 Menu32_Vertical_RowFilling_2columns_FullView - Menu Apparel (RTL)");
        stHomePage.navigateToVerticalMenu_SportsAndOutdoors();
        takeScreenShot("Menu32.16 Menu32_Vertical_RowFilling_2columns_FullView - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToVerticalMenu_VideoGames();
        takeScreenShot("Menu32.18 Menu32_Vertical_RowFilling_2columns_FullView - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}