package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.BasicPage;
import taras.adminPanel.CategoryPage;
import taras.adminPanel.MainMenuSettings;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOfMenu;
import taras.storefront.StHomePage;

import java.time.Duration;

/*
Работаем с макетом Light:
* Добавляем много категорий в третий уровень Fly меню

Горизонтальное меню + Колоночное заполнение + 3-х уровневое меню  + Компактный вид
+ Количество колонок -- 1
+ Элементы второго уровня -- 30
+ Элементы третьего уровня -- 80
+ Количество видимых элементов в третьем уровне меню -- 75 (не влияет на трехуровневое меню)
+ Минимальная высота для меню -- 700
*/
public class Menu46_3LevelMenu_Vertical_ColumnFilling_AddCategories_CompactView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu46_3LevelMenu_Vertical_ColumnFilling_AddCategories_CompactView() {
        //Настраиваем 3-х уровневое меню на странице "Дизайн -- Меню"
        BasicPage basicPage = new BasicPage();
        basicPage.navigateTo_WebsiteMenuPage();
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
        CategoryPage categoryPage = basicPage.navigateToSection_Categories();
        categoryPage.gearwheelOnCategoryPage.click();
        categoryPage.button_AddBulkCategory.click();
        categoryPage.addNewCategoryLocations_Computers();

        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        basicPage.navigateToSection_WebsiteLayouts();
        basicPage.layout_Light.click();
        basicPage.setLayoutAsDefault();
        mainMenuSettings.gearwheelOfTheBlock_Categories_Light.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        mainMenuSettings.menuSettings_buttonSettings.click();
        mainMenuSettings.selectSetting_FillingType("column_filling");
        mainMenuSettings.selectSetting_MaximumColumns("1");
        mainMenuSettings.clickAndType_setting_SecondLevelElements("30");
        mainMenuSettings.clickAndType_setting_ThirdLevelElements("80");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("75");
        if (!mainMenuSettings.setting_CompactDisplayView.isSelected()) {
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        mainMenuSettings.clickAndType_setting_MinimumHeightForMenu("700");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();

    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu46_3LevelMenu_Vertical_ColumnFilling_AddCategories_CompactView")
    public void check_Menu46_3LevelMenu_Vertical_ColumnFilling_AddCategories_CompactView() {
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu46.00 Menu46_3LevelMenu_Vertical_ColumnFilling_AddCategories_CompactView - AllProducts");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu46.02 Menu46_3LevelMenu_Vertical_ColumnFilling_AddCategories_CompactView - Electronic-Computers");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню Колоночное заполнение
        softAssert.assertTrue(assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Column!");

        //Проверяем, что колонок 1
        softAssert.assertTrue(!assertsOfMenu.columnsPerRow("1").isEmpty(),
                "Menu columns are not equal 1 column!");

        //Проверяем, что Элементов второго уровня -- не меньше 7
        softAssert.assertTrue(assertsOfMenu.numberOfElements_SecondLevel.size() >= 7,
                "Number of elements of the second level is less than 7!");

        //Проверяем, что Элементов третьего уровня -- 80
        softAssert.assertEquals(assertsOfMenu.numberOfElements_ThirdLevel_AllProducts.size(), 80,
                "Number of elements of the third level is not 80!");

        //Проверяем, что в 3-х уровневом меню (Каскадный тип меню) "Элементы третьего уровня" -- не меньше 7
        softAssert.assertTrue(assertsOfMenu.threeLevelMenu_elementsInThirdLevel.size() >= 7,
                "'Third level elements' at Cascade menu type are less than 7!");

        //Проверяем, что Количество видимых элементов в третьем уровне меню -- 75
        softAssert.assertTrue(!assertsOfMenu.numberOfVisibleElementsIn_3levelMenu("75").isEmpty(),
                "'Number of visible elements in the 3-level menu' is not 75!");

        //Проверяем, что на третьем уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.threeLevelMenu_button_MoreCategory.isEmpty(),
                "There is no button 'More [category]' in the third level of the menu!");

        stHomePage.navigateToMenu_ThreeLevelMenu_CarElectronics();
        takeScreenShot("Menu46.04 Menu46_3LevelMenu_Vertical_ColumnFilling_AddCategories_CompactView - Electronic-CarElectronics");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage_RTL();
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu46.06 Menu46_3LevelMenu_Vertical_ColumnFilling_AddCategories_CompactView - AllProducts (RTL)");
        stHomePage.navigateToVerticalMenu_Electronic();
        takeScreenShot("Menu46.08 Menu46_3LevelMenu_Vertical_ColumnFilling_AddCategories_CompactView - Electronic-Computers (RTL)");
        stHomePage.navigateToMenu_ThreeLevelMenu_CarElectronics();
        takeScreenShot("Menu46.10 Menu46_3LevelMenu_Vertical_ColumnFilling_AddCategories_CompactView - Electronic-CarElectronics (RTL)");
        softAssert.assertAll();
    }
}