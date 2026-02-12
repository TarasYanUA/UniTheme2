package menu;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.BasicPage;
import taras.adminPanel.LayoutPage;
import taras.adminPanel.MainMenuSettings;
import taras.adminPanel.UtilsAdm;
import taras.asserts.Asserts_Menu;
import taras.constants.DriverProvider;
import taras.storefront.StHomePage;

/*
Работаем с макетом Default:
+ Элементы второго уровня -- 6
+ Элементы третьего уровня -- 6
+ Количество видимых элементов в третьем уровне меню -- 4
+ Показывать иконки для пунктов меню второго уровня --  да
*/

public class Menu50_FlyMenu extends TestRunner{
    @Test(priority = 1)
    public void setConfiguration_Menu50_FlyMenu_Var1() {
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Default.click();
        layoutPage.setLayoutAsDefault();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.gearwheelOfTheBlock_FlyMenu_Default();
        mainMenuSettings.menuSettings_buttonSettings.click();
        UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "6");
        UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "6");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("4");
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_ShowIconsForMenuItems, true);
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfiguration_Menu50_FlyMenu_Var1")
    public void check_Menu50_FlyMenu_Var1(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.openFlyMenu();
        
        SoftAssert softAssert = new SoftAssert();
        Asserts_Menu asserts_menu = new Asserts_Menu();

        //Проверяем, что у меню второго уровня присутствуют иконки
        asserts_menu.assertElementPresence(asserts_menu.flyMenu_iconsOfSecondLevel, true);

        //Проверяем, что Элементов второго уровня -- 6
        asserts_menu.assertSizeOfElements(asserts_menu.flyMenu_NumberOfElements_SecondLevel, 6);

        //Проверяем, что Элементов третьего уровня -- 6
        asserts_menu.assertSizeOfElements(asserts_menu.flyMenu_NumberOfElements_ThirdLevel, 6);

        //Проверяем, что Количество видимых элементов в третьем уровне меню -- 4
        softAssert.assertEquals(DriverProvider.getDriver().findElements(By
                        .xpath(asserts_menu.flyMenu_numberOfVisibleElements)).size(), 4,
                "'Number of visible elements in the 3-level menu' is not 4!");

        //Проверяем, что присутствует кнопка "Ещё" у элементов во 2-м уровне меню
        asserts_menu.assertElementPresence(asserts_menu.flyMenu_ButtonMore, true);

        //Проверяем, что присутствует кнопка "Больше [категория]"
        asserts_menu.assertElementPresence(asserts_menu.flyMenu_ButtonMoreCategories, true);

        UtilsAdm.hoverOverElement(stHomePage.flyMenu_AllProducts);
        takeScreenShot("Menu50.00 Menu50_FlyMenu_Var1 - Menu AllProducts");
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_Electronics);
        takeScreenShot("Menu50.02 Menu50_FlyMenu_Var1 - Menu Electronics");
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_Apparel);
        takeScreenShot("Menu50.04 Menu50_FlyMenu_Var1 - Menu Apparel");
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_SportsAndOutdoors);
        takeScreenShot("Menu50.06 Menu50_FlyMenu_Var1 - Menu SportsAndOutdoors");
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_VideoGames);
        takeScreenShot("Menu50.08 Menu50_FlyMenu_Var1 - Menu VideoGames");
        stHomePage.button_CloseFlyMenu.click();

        stHomePage.selectLanguage("ar");
        stHomePage.openFlyMenu();
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_AllProducts);
        takeScreenShot("Menu50.10 Menu50_FlyMenu_Var1 - Menu AllProducts (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_Electronics);
        takeScreenShot("Menu50.12 Menu50_FlyMenu_Var1 - Menu Electronics (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_Apparel);
        takeScreenShot("Menu50.14 Menu50_FlyMenu_Var1 - Menu Apparel (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_SportsAndOutdoors);
        takeScreenShot("Menu50.16 Menu50_FlyMenu_Var1 - Menu SportsAndOutdoors (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_VideoGames);
        takeScreenShot("Menu50.18 Menu50_FlyMenu_Var1 - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}