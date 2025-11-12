package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.*;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOfMenu;
import taras.storefront.StHomePage;

import java.time.Duration;

/*
Работаем с макетом Light:
* Добавляем много категорий в третий уровень Fly меню
* Добавляем банер в третий уровень Fly меню

Горизонтальное меню + Строчное заполнение + 3-х уровневое меню
+ Количество колонок -- 2
+ Элементы второго уровня -- 30
+ Элементы третьего уровня -- 80
+ Количество видимых элементов в третьем уровне меню -- 75 (не влияет на трехуровневое меню)
+ Минимальная высота для меню -- 500
*/
public class Menu47_3LevelMenu_Vertical_RowFilling_AddCategories_FullView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu47_3LevelMenu_Vertical_RowFilling_AddCategories_FullView() {
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
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Добавляем баннер для меню "Компьютеры"
        mainMenuSettings.addBannerToMenu(mainMenuSettings.categoryComputers,"img[src$='sports-bg-menu.jpg']");
        mainMenuSettings.selectLanguage("ar");
        mainMenuSettings.addBannerToMenu(mainMenuSettings.categoryComputers_RTL, "img[src$='sports-bg-menu.jpg']");
        mainMenuSettings.selectLanguage("ru");

        //Добавляем категории для Электроники
        CategoryPage categoryPage = basicPage.navigateToSection_Categories();
        categoryPage.gearwheelOnCategoryPage.click();
        categoryPage.button_AddBulkCategory.click();
        categoryPage.addNewCategoryLocations_Computers();

        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Light.click();
        layoutPage.setLayoutAsDefault();
        mainMenuSettings.gearwheelOfTheBlock_Categories_Light.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        mainMenuSettings.menuSettings_buttonSettings.click();
        new Select(mainMenuSettings.setting_FillingType).selectByValue("row_filling");
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("2");
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "30");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "80");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("75");
        if(mainMenuSettings.setting_CompactDisplayView.isSelected()){
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "500");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu47_3LevelMenu_Vertical_RowFilling_AddCategories_FullView")
    public void check_Menu47_3LevelMenu_Vertical_RowFilling_AddCategories_FullView() {
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu47.00 Menu47_3LevelMenu_Vertical_RowFilling_AddCategories_FullView - AllProducts");
        stHomePage.navigateToVerticalMenu_Electronic();
        stHomePage.navigateToMenu_ThreeLevelMenu_Computers();
        takeScreenShot("Menu47.02 Menu47_3LevelMenu_Vertical_RowFilling_AddCategories_FullView - Electronic-Computers");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню Строчное заполнение
        softAssert.assertTrue(!assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Row!");

        //Проверяем, что колонок 2
        softAssert.assertTrue(!assertsOfMenu.columnsPerRow("2").isEmpty(),
                "Menu columns are not equal 2 columns!");

        //Проверяем, что Элементов второго уровня -- не меньше 7
        softAssert.assertTrue(assertsOfMenu.numberOfElements_SecondLevel.size() >= 7,
                "Number of elements of the second level is less than 7!");

        //Проверяем, что Элементов третьего уровня -- 80
        softAssert.assertEquals(assertsOfMenu.numberOfElements_ThirdLevel_AllProducts.size(), 80,
                "'Third level elements' are not 80!");

        //Проверяем, что в 3-х уровневом меню (Каскадный тип меню) "Элементы третьего уровня" -- не меньше 7
        softAssert.assertTrue(assertsOfMenu.threeLevelMenu_elementsInThirdLevel.size() >= 7,
                "'Third level elements' at Cascade menu type are less than 7!");

        //Проверяем, что Количество видимых элементов в третьем уровне меню -- 75
        softAssert.assertTrue(!assertsOfMenu.numberOfVisibleElementsIn_3levelMenu("75").isEmpty(),
                "'Number of visible elements in the 3-level menu' is not 75!");

        //Проверяем, что присутствует кнопка "Больше [категория]" на третьем уровне меню
        softAssert.assertTrue(!assertsOfMenu.threeLevelMenu_button_MoreCategory.isEmpty(),
                "There is no button 'More [category]' in the third level of the menu!");

        //Проверяем, что присутствует баннер на третьем уровне меню
        softAssert.assertTrue(!assertsOfMenu.threeLevelMenu_banner.isEmpty(),
                "There is no banner in the third level of the menu!");

        stHomePage.navigateToMenu_ThreeLevelMenu_CarElectronics();
        takeScreenShot("Menu47.04 Menu47_3LevelMenu_Vertical_RowFilling_AddCategories_FullView - Electronic-CarElectronics");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage_RTL();
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu47.06 Menu47_3LevelMenu_Vertical_RowFilling_AddCategories_FullView - AllProducts (RTL)");
        stHomePage.navigateToVerticalMenu_Electronic();
        stHomePage.navigateToMenu_ThreeLevelMenu_Computers();
        takeScreenShot("Menu47.08 Menu47_3LevelMenu_Vertical_RowFilling_AddCategories_FullView - Electronic-Computers (RTL)");
        stHomePage.navigateToMenu_ThreeLevelMenu_CarElectronics();
        takeScreenShot("Menu47.10 Menu47_3LevelMenu_Vertical_RowFilling_AddCategories_FullView - Electronic-CarElectronics (RTL)");
        softAssert.assertAll();
    }
}