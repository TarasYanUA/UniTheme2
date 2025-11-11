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
Вертикальное меню + Строчное заполнение + 1 колонка
+ Элементы второго уровня -- 3
+ Элементы третьего уровня -- 6
+ Количество видимых элементов в третьем уровне меню -- 5
+ Показывать иконки для пунктов меню второго уровня -- нет
+ Компактный вид отображения -- нет
+ Минимальная высота для меню -- 500
*/

public class Menu33_Vertical_RowFilling_1column_FullView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu33_Vertical_RowFilling_1column_FullView(){
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
        mainMenuSettings.selectSetting_FillingType("row_filling");
        mainMenuSettings.selectSetting_MaximumColumns("1");
        mainMenuSettings.clickAndType_setting_SecondLevelElements("3");
        mainMenuSettings.clickAndType_setting_ThirdLevelElements("6");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("5");
        if(mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()){
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        if(mainMenuSettings.setting_CompactDisplayView.isSelected()){
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        mainMenuSettings.clickAndType_setting_MinimumHeightForMenu("500");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu33_Vertical_RowFilling_1column_FullView")
    public void check_Menu33_Vertical_RowFilling_1column_FullView(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu33.00 Menu33_Vertical_RowFilling_1column_FullView - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню Строчное заполнение
        softAssert.assertTrue(!assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Row!");

        //Проверяем, что присутствует 1 колонка
        softAssert.assertTrue(!assertsOfMenu.columnsPerRow("1").isEmpty(),
                "Menu columns are not equal 1 column!");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu33.02 Menu33_Vertical_RowFilling_1column_FullView - Menu Electronic");

        //Проверяем, что у меню второго уровня отсутствуют иконки
        softAssert.assertTrue(assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are icons at the menu of the second level but shouldn't!");

        //Проверяем, что Элементов второго уровня -- 3
        softAssert.assertEquals(assertsOfMenu.numberOfElements_SecondLevel.size(), 3,
                "Number of elements of the second level is not 3!");

        //Проверяем, что Элементов третьего уровня -- 6
        softAssert.assertEquals(assertsOfMenu.numberOfElements_ThirdLevel.size(), 6,
                "Number of elements of the third level is not 6!");

        //Проверяем, что Кол-во отображаемых элементов в 3-м уровне меню -- 5
        softAssert.assertTrue(!assertsOfMenu.numberOfVisibleElementsIn_3levelMenu("5").isEmpty(),
                "'Number of visible elements in the 3-level menu' is not 5!");

        //Проверяем, что присутствует кнопка "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(!assertsOfMenu.button_More_InElementsOf2levelMenu.isEmpty(),
                "There are no buttons 'More' in the elements of the 2-level menu!");

        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.button_MoreCategory_InTheSecondLevel.isEmpty(),
                "There is no button 'More [category]' in the second level of the menu!");

        stHomePage.navigateToVerticalMenu_Apparel();
        takeScreenShot("Menu33.04 Menu33_Vertical_RowFilling_1column_FullView - Menu Apparel");
        stHomePage.navigateToVerticalMenu_SportsAndOutdoors();
        takeScreenShot("Menu33.06 Menu33_Vertical_RowFilling_1column_FullView - Menu SportsAndOutdoors");
        stHomePage.navigateToVerticalMenu_VideoGames();
        takeScreenShot("Menu33.08 Menu33_Vertical_RowFilling_1column_FullView - Menu VideoGames");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage_RTL();
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu33.10 Menu33_Vertical_RowFilling_1column_FullView - Menu AllProducts (RTL)");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu33.12 Menu33_Vertical_RowFilling_1column_FullView - Menu Electronic (RTL)");
        stHomePage.navigateToVerticalMenu_Apparel();
        takeScreenShot("Menu33.14 Menu33_Vertical_RowFilling_1column_FullView - Menu Apparel (RTL)");
        stHomePage.navigateToVerticalMenu_SportsAndOutdoors();
        takeScreenShot("Menu33.16 Menu33_Vertical_RowFilling_1column_FullView - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToVerticalMenu_VideoGames();
        takeScreenShot("Menu33.18 Menu33_Vertical_RowFilling_1column_FullView - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}