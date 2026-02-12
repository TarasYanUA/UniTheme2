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
Работаем с макетом Light v2. В этом тест-кейсе используются значения по умолчанию:
Горизонтальное меню + Строчное заполнение + 6 колонок
+ Элементы второго уровня -- 12
+ Элементы третьего уровня -- 6
+ Количество видимых элементов в третьем уровне меню -- 5
+ Показывать иконки для пунктов меню второго уровня -- да
+ Минимальная высота для меню -- 500
*/

/*
Кол-во отображаемых элементов в 3-м уровне меню
заменить на:
Количество видимых элементов в третьем уровне меню
*/

public class Menu01_Horizontal_RowFilling_6columns extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_Menu01_Horizontal_RowFilling_6columns(){
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Lightv2.click();
        layoutPage.setLayoutAsDefault();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.gearwheelOfTheBlock_MainMenu_LightV2.click();
        taras.adminPanel.UtilsAdm.waitForTitleBarWindow();
        mainMenuSettings.menuSettings_buttonSettings.click();
        new Select(mainMenuSettings.setting_FillingType).selectByValue("row_filling");
        new Select(mainMenuSettings.setting_MaximumColumns).selectByValue("6");
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "12");
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "6");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("5");
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_ShowIconsForMenuItems, true);
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_CompactDisplayView, false);   //Выключаем Компактный вид для Горизонтального меню
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "500");
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_MinimumHeightForMenu, "500");
        mainMenuSettings.tab_Content.click();
        mainMenuSettings.selectMenuContent_MainMenu();
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_Menu01_Horizontal_RowFilling_6columns")
    public void check_Menu01_Horizontal_RowFilling_6columns(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_AllProducts);
        takeScreenShot("Menu1.00 Menu01_Horizontal_RowFilling_6columns - Menu AllProducts");

        Asserts_Menu asserts_menu = new Asserts_Menu();

        //Проверяем, что у меню Строчное заполнение
        asserts_menu.assertElementPresence(asserts_menu.rowFilling, true);

        //Проверяем, что колонок 6
        asserts_menu.assertNumberOfElements(asserts_menu.columnsPerRow, 6);

        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu1.02 Menu01_Horizontal_RowFilling_6columns - Menu Electronic");

        //Проверяем, что у меню второго уровня есть иконки
        asserts_menu.assertElementPresence(asserts_menu.iconsOfSecondLevel, true);

        //Проверяем, что Элементов второго уровня -- 7
        asserts_menu.assertSizeOfElements(asserts_menu.numberOfElements_SecondLevel, 7);

        //Проверяем, что Элементов третьего уровня -- 6
        asserts_menu.assertSizeOfElements(asserts_menu.numberOfElements_ThirdLevel_Electronics, 6);

        //Проверяем, что Количество видимых элементов в третьем уровне меню -- 5
        asserts_menu.assertNumberOfElements(asserts_menu.numberOfVisibleElementsIn_ThirdLevel, 5);

        //Проверяем, что присутствует не меньше 3 кнопок "Ещё" у элементов во 2-м уровне меню
        asserts_menu.assertMoreOrEqual(asserts_menu.button_More_InElementsOfSecondLevel, 3);

        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Apparel);
        takeScreenShot("Menu1.04 Menu01_Horizontal_RowFilling_6columns - Menu Apparel");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_SportsAndOutdoors);
        takeScreenShot("Menu1.06 Menu01_Horizontal_RowFilling_6columns - Menu SportsAndOutdoors");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_VideoGames);
        takeScreenShot("Menu1.08 Menu01_Horizontal_RowFilling_6columns - Menu VideoGames");

        stHomePage.selectLanguage("ar");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Electronic);
        takeScreenShot("Menu1.10 Menu01_Horizontal_RowFilling_6columns - Menu Electronic (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_Apparel);
        takeScreenShot("Menu1.12 Menu01_Horizontal_RowFilling_6columns - Menu Apparel (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_SportsAndOutdoors);
        takeScreenShot("Menu1.14 Menu01_Horizontal_RowFilling_6columns - Menu SportsAndOutdoors (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.horizontalMenu_VideoGames);
        takeScreenShot("Menu1.16 Menu01_Horizontal_RowFilling_6columns - Menu VideoGames (RTL)");
    }
}