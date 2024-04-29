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
Вертикальное меню + Строчное заполнение + 3-х уровневое меню + Компактный вид
+ Количество колонок -- 1
+ Показывать иконки для пунктов меню второго уровня -- да (в данном кейсе настройка бесполезна)
+ Кол-во отображаемых элементов во 2-м уровне меню -- 5
+ Кол-во отображаемых элементов в 3-м уровне меню -- 1
+ Элементы второго уровня -- 5
+ Элементы третьего уровня -- 4
+ Минимальная высота для меню -- 500
*/

public class Menu44_3LevelMenu_Vertical_RowFilling_CompactView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu44_3LevelMenu_Vertical_RowFilling_CompactView(){
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
        if(!mainMenuSettings.setting_CompactDisplayView.isSelected()){
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        if(!mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()){
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("5");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("1");
        mainMenuSettings.clickAndType_setting_SecondLevelElements("5");
        mainMenuSettings.clickAndType_setting_ThirdLevelElements("4");
        mainMenuSettings.clickAndType_setting_MinimumHeightForMenu("500");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu44_3LevelMenu_Vertical_RowFilling_CompactView")
    public void check_Menu44_3LevelMenu_Vertical_RowFilling_CompactView(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu44.00 Menu44_3LevelMenu_Vertical_RowFilling_CompactView - Menu AllProducts");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();
        //Проверяем, что у меню Строчное заполнение
        softAssert.assertTrue(!assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Row!");
        //Проверяем, что присутствует 1 колонка
        softAssert.assertTrue(!assertsOfMenu.oneColumn.isEmpty(),
                "Menu columns are not equal 1 column!");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu44.02 Menu44_3LevelMenu_Vertical_RowFilling_CompactView - Menu Electronic-Computers");
        //Проверяем, что Элементов второго уровня -- 5
        softAssert.assertTrue(assertsOfMenu.numberOfElements_SecondLevel.size() == 5,
                "Number of elements of the second level is not 5!");
        //Проверяем, что Элементов третьего уровня -- 2
        softAssert.assertTrue(assertsOfMenu.threeLevelMenu_elementsInThirdLevel.size() == 2,
                "'Third level elements' are not equal 2!");
        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.button_MoreCategoryInTheSecondLevel.isEmpty(),
                "There is no button 'More [category]' in the second level of the menu!");
        stHomePage.navigateToMenu_ThreeLevelMenu_CarElectronics();
        takeScreenShot("Menu44.04 Menu44_3LevelMenu_Vertical_RowFilling_CompactView - Menu Electronic-CarElectronics");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage_RTL();
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu44.06 Menu44_3LevelMenu_Vertical_RowFilling_CompactView - Menu AllProducts (RTL)");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu44.08 Menu44_3LevelMenu_Vertical_RowFilling_CompactView - Menu Electronic-Computers (RTL)");
        stHomePage.navigateToMenu_ThreeLevelMenu_CarElectronics();
        takeScreenShot("Menu44.10 Menu44_3LevelMenu_Vertical_RowFilling_CompactView - Menu Electronic-CarElectronics (RTL)");
        softAssert.assertAll();
    }
}