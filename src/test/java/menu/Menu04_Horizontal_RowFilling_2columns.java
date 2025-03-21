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
Работаем с макетом Light v2:
Горизонтальное меню + Строчное заполнение + 2 колонки
+ Элементы второго уровня -- 5
+ Элементы третьего уровня -- 0
+ Количество видимых элементов в третьем уровне меню -- 5
+ Показывать иконки для пунктов меню второго уровня -- да
+ Минимальная высота для меню -- 700
*/

public class Menu04_Horizontal_RowFilling_2columns extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu04_Horizontal_RowFilling_2columns(){
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_WebsiteLayouts();
        csCartSettings.layout_Lightv2.click();
        csCartSettings.setLayoutAsDefault();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.gearwheelOfTheBlock_MainMenu_LightV2.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        mainMenuSettings.menuSettings_buttonSettings.click();
        mainMenuSettings.selectSetting_FillingType("row_filling");
        mainMenuSettings.selectSetting_MaximumColumns("2");


        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("5");
        mainMenuSettings.clickAndType_setting_SecondLevelElements("5");
        mainMenuSettings.clickAndType_setting_ThirdLevelElements("0");
        if(!mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()){
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        if(mainMenuSettings.setting_CompactDisplayView.isSelected()){   //Выключаем Компактный вид для Горизонтального меню
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        mainMenuSettings.clickAndType_setting_MinimumHeightForMenu("700");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu04_Horizontal_RowFilling_2columns")
    public void check_Menu04_Horizontal_RowFilling_2columns(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.navigateToHorizontalMenu_AllProducts();
        takeScreenShot("Menu4.00 Menu04_Horizontal_RowFilling_2columns - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню Строчное заполнение
        softAssert.assertTrue(!assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Row!");

        //Проверяем, что колонок 2
        softAssert.assertTrue(!assertsOfMenu.columnsPerRow("2").isEmpty(),
                "Menu columns are not equal 2 columns!");
        stHomePage.navigateToHorizontalMenu_Electronic();
        takeScreenShot("Menu4.02 Menu04_Horizontal_RowFilling_2columns - Menu Electronic");

        //Проверяем, что у меню второго уровня присутствуют иконки
        softAssert.assertTrue(!assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are no icons at the menu of the second level!");

        //Проверяем, что Элементов второго уровня -- 5
        softAssert.assertEquals(assertsOfMenu.numberOfElements_SecondLevel.size(), 5,
                "Number of elements of the 2-level is not 5!");

        //Проверяем, что Элементов третьего уровня -- 0
        softAssert.assertTrue(assertsOfMenu.numberOfElements_ThirdLevel.isEmpty(),
                "Number of elements of the third level of the menu is more than zero!");

        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.button_MoreCategory_InTheSecondLevel.isEmpty(),
                "There is no button 'More [category]' in the second level of the menu!");

        stHomePage.navigateToHorizontalMenu_Apparel();
        takeScreenShot("Menu4.04 Menu04_Horizontal_RowFilling_2columns - Menu Apparel");
        stHomePage.navigateToHorizontalMenu_SportsAndOutdoors();
        takeScreenShot("Menu4.06 Menu04_Horizontal_RowFilling_2columns - Menu SportsAndOutdoors");
        stHomePage.navigateToHorizontalMenu_VideoGames();
        takeScreenShot("Menu4.08 Menu04_Horizontal_RowFilling_2columns - Menu VideoGames");

        stHomePage.selectLanguage_RTL();
        stHomePage.navigateToHorizontalMenu_Electronic();
        takeScreenShot("Menu4.10 Menu04_Horizontal_RowFilling_2columns - Menu Electronic (RTL)");
        stHomePage.navigateToHorizontalMenu_Apparel();
        takeScreenShot("Menu4.12 Menu04_Horizontal_RowFilling_2columns - Menu Apparel (RTL)");
        stHomePage.navigateToHorizontalMenu_SportsAndOutdoors();
        takeScreenShot("Menu4.14 Menu04_Horizontal_RowFilling_2columns - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToHorizontalMenu_VideoGames();
        takeScreenShot("Menu4.16 Menu04_Horizontal_RowFilling_2columns - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}