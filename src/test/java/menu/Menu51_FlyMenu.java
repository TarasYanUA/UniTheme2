package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.BasicPage;
import taras.adminPanel.LayoutPage;
import taras.adminPanel.MainMenuSettings;
import taras.adminPanel.UtilsAdm;
import taras.asserts.Asserts_Menu;
import taras.constants.DriverProvider;
import taras.storefront.StHomePage;

import java.time.Duration;

/*
Работаем с макетом Default:
+ Элементы второго уровня -- 3
+ Элементы третьего уровня -- 4
+ Количество видимых элементов в третьем уровне меню -- 2
+ Показывать иконки для пунктов меню второго уровня --  нет
*/

public class Menu51_FlyMenu extends TestRunner{
    @Test(priority = 1)
    public void setConfiguration_Menu51_FlyMenu_Var2() {
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Default.click();
        layoutPage.setLayoutAsDefault();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.gearwheelOfTheBlock_FlyMenu_Default();
        mainMenuSettings.menuSettings_buttonSettings.click();
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_SecondLevelElements, "3");
        taras.adminPanel.UtilsAdm.clickAndType(mainMenuSettings.setting_ThirdLevelElements, "4");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("2");
        UtilsAdm.setCheckboxState(mainMenuSettings.setting_ShowIconsForMenuItems, false);
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfiguration_Menu51_FlyMenu_Var2")
    public void check_Menu51_FlyMenu_Var2(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.openFlyMenu();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ut2-lfl.ty-menu-item__products p")));

        SoftAssert softAssert = new SoftAssert();
        Asserts_Menu asserts_menu = new Asserts_Menu();

        //Проверяем, что у меню второго уровня отсутствуют иконки
        asserts_menu.assertElementPresence(asserts_menu.flyMenu_iconsOfSecondLevel, false);

        //Проверяем, что Элементов второго уровня -- 3
        asserts_menu.assertSizeOfElements(asserts_menu.flyMenu_NumberOfElements_SecondLevel, 3);

        //Проверяем, что Элементов третьего уровня -- 4
        asserts_menu.assertSizeOfElements(asserts_menu.flyMenu_NumberOfElements_ThirdLevel, 4);

        //Проверяем, что Количество видимых элементов в третьем уровне меню -- 2
        softAssert.assertEquals(DriverProvider.getDriver().findElements(By
                        .xpath(asserts_menu.flyMenu_numberOfVisibleElements)).size(), 2,
                "'Number of visible elements in the 3-level menu' is not 2!");

        //Проверяем, что присутствует кнопка "Ещё" у элементов во 2-м уровне меню
        asserts_menu.assertElementPresence(asserts_menu.flyMenu_ButtonMore, true);

        //Проверяем, что присутствует кнопка "Больше [категория]"
        asserts_menu.assertElementPresence(asserts_menu.flyMenu_ButtonMoreCategories, true);

        UtilsAdm.hoverOverElement(stHomePage.flyMenu_AllProducts);
        takeScreenShot("Menu51.00 Menu51_FlyMenu_Var2 - Menu AllProducts");
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_Electronics);
        takeScreenShot("Menu51.02 Menu51_FlyMenu_Var2 - Menu Electronics");
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_Apparel);
        takeScreenShot("Menu51.04 Menu51_FlyMenu_Var2 - Menu Apparel");
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_SportsAndOutdoors);
        takeScreenShot("Menu51.06 Menu51_FlyMenu_Var2 - Menu SportsAndOutdoors");
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_VideoGames);
        takeScreenShot("Menu51.08 Menu51_FlyMenu_Var2 - Menu VideoGames");
        stHomePage.button_CloseFlyMenu.click();

        stHomePage.selectLanguage("ar");
        stHomePage.openFlyMenu();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ut2-lfl.ty-menu-item__products p")));
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_AllProducts);
        takeScreenShot("Menu51.10 Menu51_FlyMenu_Var2 - Menu AllProducts (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_Electronics);
        takeScreenShot("Menu51.12 Menu51_FlyMenu_Var2 - Menu Electronics (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_Apparel);
        takeScreenShot("Menu51.14 Menu51_FlyMenu_Var2 - Menu Apparel (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_SportsAndOutdoors);
        takeScreenShot("Menu51.16 Menu51_FlyMenu_Var2 - Menu SportsAndOutdoors (RTL)");
        UtilsAdm.hoverOverElement(stHomePage.flyMenu_VideoGames);
        takeScreenShot("Menu51.18 Menu51_FlyMenu_Var2 - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}
