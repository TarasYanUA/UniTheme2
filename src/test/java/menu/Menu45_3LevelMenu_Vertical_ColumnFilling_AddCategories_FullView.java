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
* Добавляем банер в третий уровень Fly меню

Вертикальное меню + Колоночное заполнение + 3-х уровневое меню
+ Количество колонок -- 4
+ Кол-во отображаемых элементов во 2-м уровне меню -- 75
+ Кол-во отображаемых элементов в 3-м уровне меню -- 75
+ Элементы второго уровня -- 30
+ Элементы третьего уровня -- 80
+ Минимальная высота для меню -- 500
*/
public class Menu45_3LevelMenu_Vertical_ColumnFilling_AddCategories_FullView extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu45_3LevelMenu_Vertical_ColumnFilling_AddCategories_FullView() {
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
        //Добавляем баннер для меню "Компьютеры"
        mainMenuSettings.arrowOfCategory.click();
        mainMenuSettings.categoryComputers.click();
        mainMenuSettings.menuTab_ABUniTheme2.click();
        if(DriverProvider.getDriver().findElements(By.cssSelector("<p><img src=\"design/themes/abt__unitheme2/media/images/abt__unitheme2/electronics-bg-menu.jpg\"></p>")).isEmpty()) {
            mainMenuSettings.button_Html.click();
            mainMenuSettings.clickAndType_Field_HtmlContent();
        }
        mainMenuSettings.button_Save3LevelMenu.click();
        mainMenuSettings.selectLanguage_RTL();
        mainMenuSettings.arrowOfCategory.click();
        mainMenuSettings.categoryComputers.click();
        mainMenuSettings.menuTab_ABUniTheme2.click();
        if(DriverProvider.getDriver().findElements(By.cssSelector("img[src$='sports-bg-menu.jpg']")).isEmpty()) {
            mainMenuSettings.button_Html.click();
            mainMenuSettings.clickAndType_Field_HtmlContent();
        }
        mainMenuSettings.button_Save3LevelMenu.click();
        mainMenuSettings.selectLanguage_RU();

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
        mainMenuSettings.selectSetting_FillingType("column_filling");
        mainMenuSettings.selectSetting_MaximumColumns("4");
        if(mainMenuSettings.setting_CompactDisplayView.isSelected()){
            mainMenuSettings.setting_CompactDisplayView.click();
        }
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("75");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("75");
        mainMenuSettings.clickAndType_setting_SecondLevelElements("30");
        mainMenuSettings.clickAndType_setting_ThirdLevelElements("80");
        mainMenuSettings.clickAndType_setting_MinimumHeightForMenu("500");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu45_3LevelMenu_Vertical_ColumnFilling_AddCategories_FullView")
    public void check_Menu45_3LevelMenu_Vertical_ColumnFilling_AddCategories_FullView() {
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu45.00 Menu45_3LevelMenu_Vertical_ColumnFilling_AddCategories_FullView - AllProducts");
        stHomePage.navigateToVerticalMenu_Electronic();
        stHomePage.navigateToMenu_ThreeLevelMenu_Computers();
        takeScreenShot("Menu45.02 Menu45_3LevelMenu_Vertical_ColumnFilling_AddCategories_FullView - Electronic-Computers");

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();
        //Проверяем, что у меню Колоночное заполнение
        softAssert.assertTrue(assertsOfMenu.rowFilling.isEmpty(),
                "Menu filling is not Column!");
        //Проверяем, что колонок 4
        softAssert.assertTrue(!assertsOfMenu.fourColumns.isEmpty(),
                "Menu columns are not equal 4 columns!");
        //Проверяем, что Элементов второго уровня -- не меньше 7
        softAssert.assertTrue(assertsOfMenu.numberOfElements_SecondLevel.size() >= 7,
                "Number of elements of the second level is less than 7!");
        //Проверяем, что Элементов третьего уровня -- не меньше 75
        softAssert.assertTrue(assertsOfMenu.threeLevelMenu_elementsInThirdLevel.size() >= 75,
                "'Third level elements' are less than 75!");
        //Проверяем, что на третьем уровне меню присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.threeLevelMenu_button_MoreCategory.isEmpty(),
                "There is no button 'More [category]' in the third level of the menu!");
        //Проверяем, что на третьем уровне меню присутствует баннер
        softAssert.assertTrue(!assertsOfMenu.threeLevelMenu_banner.isEmpty(),
                "There is no banner in the third level of the menu!");
        stHomePage.navigateToMenu_ThreeLevelMenu_CarElectronics();
        takeScreenShot("Menu45.04 Menu45_3LevelMenu_Vertical_ColumnFilling_AddCategories_FullView - Electronic-CarElectronics");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage_RTL();
        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.navigateToVerticalMenu_AllProducts();
        takeScreenShot("Menu45.06 Menu45_3LevelMenu_Vertical_ColumnFilling_AddCategories_FullView - AllProducts (RTL)");
        stHomePage.navigateToVerticalMenu_Electronic();
        stHomePage.navigateToMenu_ThreeLevelMenu_Computers();
        takeScreenShot("Menu45.08 Menu45_3LevelMenu_Vertical_ColumnFilling_AddCategories_FullView - Electronic-Computers (RTL)");
        stHomePage.navigateToMenu_ThreeLevelMenu_CarElectronics();
        takeScreenShot("Menu45.10 Menu45_3LevelMenu_Vertical_ColumnFilling_AddCategories_FullView - Electronic-CarElectronics (RTL)");
        softAssert.assertAll();
    }
}