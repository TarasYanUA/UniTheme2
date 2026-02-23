package menu;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.asserts.Asserts_Menu;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

/*
Работаем с макетом Light:
* Добавляем много категорий в третий уровень Fly меню
* Добавляем баннер в третий уровень Fly меню

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
        taras.adminPanel.UtilsAdm.waitForTitleBarWindow();
        mainMenuSettings.menuTab_ABUniTheme2.click();
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_ActivateSettings, true);
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_Activate3LevelMenu, true);
        mainMenuSettings.button_Save3LevelMenu.click();
        UtilsAdm.makePause(2000);

        //Добавляем баннер для меню "Компьютеры"
        mainMenuSettings.addBannerToMenu(mainMenuSettings.categoryComputers,"img[src$='electronics-bg-menu.jpg']");
        basicPage.selectLanguageForAdminElement("ar");
        mainMenuSettings.addBannerToMenu(mainMenuSettings.categoryComputers_RTL, "img[src$='sports-bg-menu.jpg']");
        basicPage.selectLanguageForAdminElement("ru");

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
        taras.adminPanel.UtilsAdm.waitForTitleBarWindow();
        mainMenuSettings.menuSettings_buttonSettings.click();
        new Select(mainMenuSettings.setting_FillingType).selectByValue("row_filling");
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("2");
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "30");
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "80");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("75");
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_CompactDisplayView, false);
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "500");
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
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu47.00 Menu47_3LevelMenu_Vertical_RowFilling_AddCategories_FullView - AllProducts");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Electronic);
        UtilsAdm.hoverOverElement(stHomePage.threeLevelMenu_Computers);
        takeScreenShot("Menu47.02 Menu47_3LevelMenu_Vertical_RowFilling_AddCategories_FullView - Electronic-Computers");

        Asserts_Menu asserts_menu = new Asserts_Menu();

        //Проверяем, что у меню Строчное заполнение
        asserts_menu.assertElementPresence(asserts_menu.rowFilling, true);

        //Проверяем, что колонок 2
        asserts_menu.assertNumberOfElements(asserts_menu.columnsPerRow, 2);

        //Проверяем, что Элементов второго уровня -- не меньше 7
        asserts_menu.assertMoreOrEqual(asserts_menu.numberOfElements_SecondLevel, 7);

        //Проверяем, что Элементов третьего уровня -- 80
        asserts_menu.assertSizeOfElements(asserts_menu.numberOfElements_ThirdLevel_Electronics, 80);

        //Проверяем, что в 3-х уровневом меню (Каскадный тип меню) "Элементы третьего уровня" -- не меньше 7
        asserts_menu.assertMoreOrEqual(asserts_menu.cascadeMenu_elementsInThirdLevel, 7);

        //Проверяем, что Количество видимых элементов в третьем уровне меню -- 75
        asserts_menu.assertNumberOfElements(asserts_menu.flyMenu_numberOfVisibleElements,75);

        //Проверяем, что присутствует кнопка "Больше [категория]" для 3-х уровневого меню (Каскадный тип меню)
        asserts_menu.assertElementPresence(asserts_menu.cascadeMenu_MoreCategory, true);

        //Проверяем, что присутствует баннер на третьем уровне меню
        asserts_menu.assertElementPresence(asserts_menu.banner_InThirdLevel, true);

        UtilsAdm.hoverOverElement(stHomePage.threeLevelMenu_CarElectronics);
        takeScreenShot("Menu47.04 Menu47_3LevelMenu_Vertical_RowFilling_AddCategories_FullView - Electronic-CarElectronics");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage("ar");
        stHomePage.verticalMenu_menuButton_Categories.click();
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu47.06 Menu47_3LevelMenu_Vertical_RowFilling_AddCategories_FullView - AllProducts (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Electronic);
        UtilsAdm.hoverOverElement(stHomePage.threeLevelMenu_Computers);
        takeScreenShot("Menu47.08 Menu47_3LevelMenu_Vertical_RowFilling_AddCategories_FullView - Electronic-Computers (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.threeLevelMenu_CarElectronics);
        takeScreenShot("Menu47.10 Menu47_3LevelMenu_Vertical_RowFilling_AddCategories_FullView - Electronic-CarElectronics (RTL)");
    }
}