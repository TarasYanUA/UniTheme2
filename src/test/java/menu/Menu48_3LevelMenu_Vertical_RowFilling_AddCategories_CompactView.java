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
* Добавляем много категорий в третий уровень Fly меню

Горизонтальное меню + Строчное заполнение + 3-х уровневое меню + Компактный вид
+ Количество колонок -- 1
+ Кол-во отображаемых элементов во 2-м уровне меню -- 75
+ Кол-во отображаемых элементов в 3-м уровне меню -- 75
+ Элементы второго уровня -- 30
+ Элементы третьего уровня -- 80
+ Минимальная высота для меню -- 700
*/
public class Menu48_3LevelMenu_Vertical_RowFilling_AddCategories_CompactView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu48_3LevelMenu_Vertical_RowFilling_AddCategories_CompactView() {
        //Настраиваем 3-х уровневое меню на странице "Дизайн -- Меню"
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateTo_WebsiteMenuPage();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.choose_MainMenu.click();
        mainMenuSettings.chooseMenu_Electronics.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        mainMenuSettings.menuTab_ABUniTheme2.click();
        if (!mainMenuSettings.setting_ActivateSettings.isSelected()) {
            mainMenuSettings.setting_ActivateSettings.click();
        }
        if (!mainMenuSettings.setting_Activate3LevelMenu.isSelected()) {
            mainMenuSettings.setting_Activate3LevelMenu.click();
        }
        mainMenuSettings.button_Save3LevelMenu.click();

        //Добавляем категории для Электроники
        csCartSettings.navigateToSection_Categories();
        if(DriverProvider.getDriver().findElements(By.cssSelector(".categories-company .icon-caret-right")).isEmpty()) {
            csCartSettings.gearwheelOnCategoryPage.click();
            csCartSettings.button_AddBulkCategory.click();
            csCartSettings.selectCategoryLocation_Computers();
            csCartSettings.clickAndType_Field_CategoryName();
            for(int i = 1; i < 80; i++) {
                csCartSettings.button_Clone.click();
            }
            csCartSettings.button_Create.click();
        }

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
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("75");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("75");
        mainMenuSettings.clickAndType_setting_SecondLevelElements("30");
        mainMenuSettings.clickAndType_setting_ThirdLevelElements("80");
        mainMenuSettings.clickAndType_setting_MinimumHeightForMenu("700");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu48_3LevelMenu_Vertical_RowFilling_AddCategories_CompactView")
    public void check_Menu48_3LevelMenu_Vertical_RowFilling_AddCategories_CompactView() {
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu48.00 Menu48_3LevelMenu_Vertical_RowFilling_AddCategories_CompactView - AllProducts");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu48.02 Menu48_3LevelMenu_Vertical_RowFilling_AddCategories_CompactView - Electronic-Computers");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();
        //Проверяем, что у меню Строчное заполнение
        softAssert.assertTrue(!assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Row!");
        //Проверяем, что колонок 1
        softAssert.assertTrue(!assertsOfMenu.oneColumn.isEmpty(),
                "Menu columns are not equal 1 column!");
        //Проверяем, что Элементов второго уровня -- не меньше 7
        softAssert.assertTrue(assertsOfMenu.numberOfElements_SecondLevel.size() >= 7,
                "Number of elements of the second level is less than 7!");
        //Проверяем, что Элементов третьего уровня -- не меньше 75
        softAssert.assertTrue(assertsOfMenu.threeLevelMenu_elementsInThirdLevel.size() >= 75,
                "'Third level elements' are less than 75!");
        //Проверяем, что на третьем уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.threeLevelMenu_button_MoreCategory.isEmpty(),
                "There is no button 'More [category]' in the third level of the menu!"); //Ошибка https://abteam.planfix.com/task/41448 п.3
        stHomePage.navigateToMenu_ThreeLevelMenu_CarElectronics();
        takeScreenShot("Menu48.04 Menu48_3LevelMenu_Vertical_RowFilling_AddCategories_CompactView - Electronic-CarElectronics");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage_RTL();
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu48.06 Menu48_3LevelMenu_Vertical_RowFilling_AddCategories_CompactView - AllProducts (RTL)");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu48.08 Menu48_3LevelMenu_Vertical_RowFilling_AddCategories_CompactView - Electronic-Computers (RTL)");
        stHomePage.navigateToMenu_ThreeLevelMenu_CarElectronics();
        takeScreenShot("Menu48.10 Menu48_3LevelMenu_Vertical_RowFilling_AddCategories_CompactView - Electronic-CarElectronics (RTL)");
        softAssert.assertAll();
    }
}