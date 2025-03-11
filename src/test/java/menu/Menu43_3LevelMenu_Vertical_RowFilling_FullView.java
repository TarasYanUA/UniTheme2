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
Вертикальное меню + Строчное заполнение + 3-х уровневое меню
+ Количество колонок -- 1
+ Элементы второго уровня -- 5
+ Элементы третьего уровня -- 5
+ Количество видимых элементов в третьем уровне меню -- 5 (не влияет на трехуровневое меню)
+ Компактный вид отображения -- нет
+ Минимальная высота для меню -- 500
*/

public class Menu43_3LevelMenu_Vertical_RowFilling_FullView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu43_3LevelMenu_Vertical_RowFilling_FullView(){
        //Настраиваем 3-х уровневое меню на странице "Дизайн -- Меню"
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateTo_WebsiteMenuPage();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.choose_MainMenu.click();
        mainMenuSettings.chooseMenu_Electronics.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        mainMenuSettings.menuTab_ABUniTheme2.click();
        if(!mainMenuSettings.setting_ActivateSettings.isSelected()){
            mainMenuSettings.setting_ActivateSettings.click();
        }
        if(!mainMenuSettings.setting_Activate3LevelMenu.isSelected()){
            mainMenuSettings.setting_Activate3LevelMenu.click();
        }
        mainMenuSettings.button_Save3LevelMenu.click();

        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        csCartSettings.navigateToSection_WebsiteLayouts();
        csCartSettings.layout_Light.click();
        csCartSettings.setLayoutAsDefault();
        mainMenuSettings.gearwheelOfTheBlock_Categories_Light.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        mainMenuSettings.menuSettings_buttonSettings.click();
        mainMenuSettings.selectSetting_FillingType("row_filling");
        mainMenuSettings.selectSetting_MaximumColumns("1");

        mainMenuSettings.clickAndType_setting_SecondLevelElements("5");
        mainMenuSettings.clickAndType_setting_ThirdLevelElements("5");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("5");
        if(mainMenuSettings.setting_CompactDisplayView.isSelected()){
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        mainMenuSettings.clickAndType_setting_MinimumHeightForMenu("500");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu43_3LevelMenu_Vertical_RowFilling_FullView")
    public void check_Menu43_3LevelMenu_Vertical_RowFilling_FullView(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu43.00 Menu43_3LevelMenu_Vertical_RowFilling_FullView - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню Строчное заполнение
        softAssert.assertTrue(!assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Row!");

        //Проверяем, что присутствует 1 колонка
        softAssert.assertTrue(!assertsOfMenu.columnsPerRow("1").isEmpty(),
                "Menu columns are not equal 1 column!");

        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu43.02 Menu43_3LevelMenu_Vertical_RowFilling_FullView - Menu Electronic-Computers");

        //Проверяем, что Элементов второго уровня -- 5
        softAssert.assertTrue(assertsOfMenu.numberOfElements_SecondLevel.size() == 5,
                "Number of elements of the second level is not 5!");

        //Проверяем, что Элементов третьего уровня -- 5
        softAssert.assertEquals(assertsOfMenu.threeLevelMenu_elementsInThirdLevel.size(), 5,
                "'Third level elements' are not equal 5!");

        //Проверяем, что в 3-х уровневом меню (Каскадный тип меню) "Элементы третьего уровня" -- 5
        softAssert.assertTrue(assertsOfMenu.threeLevelMenu_elementsInThirdLevel.size() == 5,
                "'Third level elements' at Cascade menu type are not 5!");

        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.button_MoreCategoryInTheSecondLevel_MoreElectronics.isEmpty(),
                "There is no button 'More [category]' in the second level of the menu!");

        stHomePage.navigateToMenu_ThreeLevelMenu_CarElectronics();
        takeScreenShot("Menu43.04 Menu43_3LevelMenu_Vertical_RowFilling_FullView - Menu Electronic-CarElectronics");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage_RTL();
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu43.06 Menu43_3LevelMenu_Vertical_RowFilling_FullView - Menu AllProducts (RTL)");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu43.08 Menu43_3LevelMenu_Vertical_RowFilling_FullView - Menu Electronic-Computers (RTL)");
        stHomePage.navigateToMenu_ThreeLevelMenu_CarElectronics();
        takeScreenShot("Menu43.10 Menu43_3LevelMenu_Vertical_RowFilling_FullView - Menu Electronic-CarElectronics (RTL)");
        softAssert.assertAll();
    }
}