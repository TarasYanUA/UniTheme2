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
        UtilsAdm.waitForTitleBarWindow();
        mainMenuSettings.menuTab_ABUniTheme2.click();
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_ActivateSettings, true);
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_Activate3LevelMenu, true);
        mainMenuSettings.button_Save3LevelMenu.click();

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
        UtilsAdm.waitForTitleBarWindow();
        mainMenuSettings.menuSettings_buttonSettings.click();
        new Select(mainMenuSettings.setting_FillingType).selectByValue("column_filling");
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("1");
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "30");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "80");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("75");
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_CompactDisplayView, true);
        UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "700");
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
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu46.00 Menu46_3LevelMenu_Vertical_ColumnFilling_AddCategories_CompactView - AllProducts");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu46.02 Menu46_3LevelMenu_Vertical_ColumnFilling_AddCategories_CompactView - Electronic-Computers");

        Asserts_Menu asserts_menu = new Asserts_Menu();

        //Проверяем, что у меню Колоночное заполнение
        asserts_menu.assertElementPresence(asserts_menu.rowFilling, false);

        //Проверяем, что колонок 1
        asserts_menu.assertNumberOfElements(asserts_menu.columnsPerRow, 1);

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

        UtilsAdm.hoverOverElement(stHomePage.threeLevelMenu_CarElectronics);
        takeScreenShot("Menu46.04 Menu46_3LevelMenu_Vertical_ColumnFilling_AddCategories_CompactView - Electronic-CarElectronics");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage("ar");
        stHomePage.verticalMenu_menuButton_Categories.click();
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu46.06 Menu46_3LevelMenu_Vertical_ColumnFilling_AddCategories_CompactView - AllProducts (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu46.08 Menu46_3LevelMenu_Vertical_ColumnFilling_AddCategories_CompactView - Electronic-Computers (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.threeLevelMenu_CarElectronics);
        takeScreenShot("Menu46.10 Menu46_3LevelMenu_Vertical_ColumnFilling_AddCategories_CompactView - Electronic-CarElectronics (RTL)");
    }
}