package menu;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.MenuSettings;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOfMenu;
import taras.storefront.StHomePage;
import java.time.Duration;

/*
Работаем с макетом Light v2. В этом тест-кейсе используются значения по умолчанию:
Горизонтальное меню + Строчное заполнение + 6 колонок
+ Показывать иконки для пунктов меню второго уровня -- да
+ Кол-во отображаемых элементов во 2-м уровне меню -- 5
+ Кол-во отображаемых элементов в 3-м уровне меню -- 10
+ Элементы второго уровня -- 12
+ Элементы третьего уровня -- 6
+ Минимальная высота для меню -- 500
*/

public class Menu01_Horizontal_RowFilling_6columns extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu01_Horizontal_RowFilling_6columns(){
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_DesignLayouts();
        csCartSettings.layout_Lightv2.click();
        csCartSettings.setLayoutAsDefault();
        MenuSettings menuSettings = new MenuSettings();
        menuSettings.gearwheelOfTheBlock_MainMenu_LightV2.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        menuSettings.menuSettings_buttonSettings.click();
        menuSettings.selectSetting_FillingType("row_filling");
        menuSettings.selectSetting_MaximumColumns("6");
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

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu01_Horizontal_RowFilling_6columns")
    public void check_Menu01_Horizontal_RowFilling_6columns(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.navigateToHorizontalMenu_AllProducts();
        takeScreenShot("Menu1.00 Menu01_Horizontal_RowFilling_6columns - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();
        //Проверяем, что у меню Строчное заполнение
        softAssert.assertTrue(!assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Row!");
        //Проверяем, что колонок 6
        softAssert.assertTrue(!assertsOfMenu.sixColumns.isEmpty(),
                "Menu columns are not equal 6 columns!");
        stHomePage.navigateToHorizontalMenu_Electronic();
        takeScreenShot("Menu1.02 Menu01_Horizontal_RowFilling_6columns - Menu Electronic");
        //Проверяем, что у меню второго уровня есть иконки
        softAssert.assertTrue(!assertsOfMenu.iconsOfSecondLevel.isEmpty(),
                "There are no icons at the menu of the second level!");
        //Проверяем, что Кол-во отображаемых элементов во 2-м уровне меню -- 5
        softAssert.assertTrue(!assertsOfMenu.numberOElementsIn2levelMenu_isFive.isEmpty(),
                "'Number of visible elements in the 2-level menu' is not 5!");
        //Проверяем, что присутствует не меньше 3 кнопок "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(assertsOfMenu.button_MoreInElementsOf2levelMenu.size() >=3,
                "There are less than three buttons 'More' in the elements of the second level of the menu!");
        //Проверяем, что Элементов второго уровня -- не меньше 7
        softAssert.assertTrue(!assertsOfMenu.numberOfElementsOfSecondLevel_isSeven.isEmpty(),
                "Number of elements of the second level is less than 7!");
        stHomePage.navigateToHorizontalMenu_Apparel();
        takeScreenShot("Menu1.04 Menu01_Horizontal_RowFilling_6columns - Menu Apparel");
        stHomePage.navigateToHorizontalMenu_SportsAndOutdoors();
        takeScreenShot("Menu1.06 Menu01_Horizontal_RowFilling_6columns - Menu SportsAndOutdoors");
        stHomePage.navigateToHorizontalMenu_VideoGames();
        takeScreenShot("Menu1.08 Menu01_Horizontal_RowFilling_6columns - Menu VideoGames");

        stHomePage.selectLanguage_RTL();
        stHomePage.navigateToHorizontalMenu_Electronic();
        takeScreenShot("Menu1.10 Menu01_Horizontal_RowFilling_6columns - Menu Electronic (RTL)");
        stHomePage.navigateToHorizontalMenu_Apparel();
        takeScreenShot("Menu1.12 Menu01_Horizontal_RowFilling_6columns - Menu Apparel (RTL)");
        stHomePage.navigateToHorizontalMenu_SportsAndOutdoors();
        takeScreenShot("Menu1.14 Menu01_Horizontal_RowFilling_6columns - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToHorizontalMenu_VideoGames();
        takeScreenShot("Menu1.16 Menu01_Horizontal_RowFilling_6columns - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}