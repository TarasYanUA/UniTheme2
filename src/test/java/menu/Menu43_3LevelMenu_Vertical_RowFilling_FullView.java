package menu;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import taras.adminPanel.BasicPage;
import taras.adminPanel.LayoutPage;
import taras.adminPanel.MainMenuSettings;
import taras.adminPanel.UtilsAdm;
import taras.asserts.Asserts_Menu;
import taras.storefront.StHomePage;

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

        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Light.click();
        layoutPage.setLayoutAsDefault();
        mainMenuSettings.gearwheelOfTheBlock_Categories_Light.click();
        UtilsAdm.waitForTitleBarWindow();
        mainMenuSettings.menuSettings_buttonSettings.click();
        new Select(mainMenuSettings.setting_FillingType).selectByValue("row_filling");
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("1");
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "5");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "5");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("5");
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_CompactDisplayView, false);
        UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "500");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu43_3LevelMenu_Vertical_RowFilling_FullView")
    public void check_Menu43_3LevelMenu_Vertical_RowFilling_FullView(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.verticalMenu_menuButton_Categories.click();
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu43.00 Menu43_3LevelMenu_Vertical_RowFilling_FullView - Menu AllProducts");

        Asserts_Menu asserts_menu = new Asserts_Menu();

        //Проверяем, что у меню Строчное заполнение
        asserts_menu.assertElementPresence(asserts_menu.rowFilling, true);

        //Проверяем, что присутствует 1 колонка
        asserts_menu.assertNumberOfElements(asserts_menu.columnsPerRow, 1);

        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu43.02 Menu43_3LevelMenu_Vertical_RowFilling_FullView - Menu Electronic-Computers");

        //Проверяем, что Элементов второго уровня -- 5
        asserts_menu.assertSizeOfElements(asserts_menu.numberOfElements_SecondLevel, 5);

        //Проверяем, что Элементов третьего уровня -- 5
        asserts_menu.assertSizeOfElements(asserts_menu.numberOfElements_ThirdLevel_Electronics, 5);

        //Проверяем, что в 3-х уровневом меню (Каскадный тип меню) "Элементы третьего уровня" -- 5
        asserts_menu.assertSizeOfElements(asserts_menu.cascadeMenu_elementsInThirdLevel, 5);

        //Проверяем, что во втором уровне меню присутствует кнопка "Больше [категория]"
        asserts_menu.assertElementPresence(asserts_menu.button_MoreCategory_InSecondLevel, true);

        UtilsAdm.hoverOverElement(stHomePage.threeLevelMenu_CarElectronics);
        takeScreenShot("Menu43.04 Menu43_3LevelMenu_Vertical_RowFilling_FullView - Menu Electronic-CarElectronics");

        stHomePage.verticalMenu_menuButton_Categories.click();
        stHomePage.selectLanguage("ar");
        stHomePage.verticalMenu_menuButton_Categories.click();
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_AllProducts);
        takeScreenShot("Menu43.06 Menu43_3LevelMenu_Vertical_RowFilling_FullView - Menu AllProducts (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.verticalMenu_Electronic);
        takeScreenShot("Menu43.08 Menu43_3LevelMenu_Vertical_RowFilling_FullView - Menu Electronic-Computers (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.threeLevelMenu_CarElectronics);
        takeScreenShot("Menu43.10 Menu43_3LevelMenu_Vertical_RowFilling_FullView - Menu Electronic-CarElectronics (RTL)");
    }
}