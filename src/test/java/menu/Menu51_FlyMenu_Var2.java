package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.MenuSettings;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOfMenu;
import taras.storefront.StHomePage;

import java.time.Duration;

/*
Работаем с макетом Default:
+ Показывать иконки для пунктов меню второго уровня --  нет
+ Кол-во отображаемых элементов во 2-м уровне меню --   3
+ Кол-во отображаемых элементов в 3-м уровне меню --    2
+ Показать заголовок -- нет
*/

public class Menu51_FlyMenu_Var2 extends TestRunner{
    @Test(priority = 1)
    public void setConfiguration_Menu51_FlyMenu_Var2() {
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_DesignLayouts();
        csCartSettings.layout_Default.click();
        csCartSettings.setLayoutAsDefault();
        MenuSettings menuSettings = new MenuSettings();
        menuSettings.gearwheelOfTheBlock_FlyMenu_Default.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        menuSettings.menuSettings_buttonSettings.click();
        if(menuSettings.setting_ShowIconsForMenuItems.isSelected()){
            menuSettings.setting_ShowIconsForMenuItems.click();
        }
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("3");
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("2");
        if(menuSettings.setting_ShowTitle.isSelected()){
            menuSettings.setting_ShowTitle.click();
        }
        menuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfiguration_Menu51_FlyMenu_Var2")
    public void check_Menu51_FlyMenu_Var2(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.button_FlyMenu.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ut2-lfl.ty-menu-item__products p")));

        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();
        //Проверка, что заголовок меню отсутствует
        softAssert.assertTrue(assertsOfMenu.flyMenu_title.isEmpty(),
                "There is a title in the Fly menu but shouldn't!");
        //Проверяем, что у меню второго уровня отсутствуют иконки
        softAssert.assertTrue(assertsOfMenu.flyMenu_iconsOfSecondLevel.isEmpty(),
                "There are icons at the menu of the second level but shouldn't!");
        //Проверяем, что Кол-во отображаемых элементов во 2-м уровне меню -- 3
        softAssert.assertTrue(assertsOfMenu.flyMenu_NumberOfElements_SecondLevel.size() == 3 ||
                        assertsOfMenu.flyMenu_NumberOfElements_SecondLevelWithButtonMore.size() == 3,
                "'Number of visible elements in the 2-level menu' is not 3!");
        //Проверяем, что Кол-во отображаемых элементов в 3-м уровне меню -- 2
        softAssert.assertTrue(assertsOfMenu.flyMenu_NumberOfElements_ThirdLevel.size() == 2,
                "'Number of visible elements in the 3-level menu' is not 2!");
        //Проверяем, что присутствует кнопка "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(!assertsOfMenu.flyMenu_ButtonMore.isEmpty(),
                "There is no any button 'More' in the elements of the second level of the Fly menu!");
        //Проверяем, что присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.flyMenu_ButtonMoreCategories.isEmpty(),
                "There is no any button 'More [category]' in the second level of Fly menu!");

        stHomePage. navigateToFlyMenu_AllProducts();
        takeScreenShot("Menu51.00 Menu51_FlyMenu_Var2 - Menu AllProducts");
        stHomePage.navigateToFlyMenu_Electronics();
        takeScreenShot("Menu51.02 Menu51_FlyMenu_Var2 - Menu Electronics");
        stHomePage.navigateToFlyMenu_Apparel();
        takeScreenShot("Menu51.04 Menu51_FlyMenu_Var2 - Menu Apparel");
        stHomePage.navigateToFlyMenu_SportsAndOutdoors();
        takeScreenShot("Menu51.06 Menu51_FlyMenu_Var2 - Menu SportsAndOutdoors");
        stHomePage.navigateToFlyMenu_VideoGames();
        takeScreenShot("Menu51.08 Menu51_FlyMenu_Var2 - Menu VideoGames");
        stHomePage.button_CloseFlyMenu.click();

        stHomePage.selectLanguage_RTL();
        stHomePage.button_FlyMenu.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ut2-lfl.ty-menu-item__products p")));
        stHomePage.navigateToFlyMenu_AllProducts();
        takeScreenShot("Menu51.10 Menu51_FlyMenu_Var2 - Menu AllProducts (RTL)");
        stHomePage.navigateToFlyMenu_Electronics();
        takeScreenShot("Menu51.12 Menu51_FlyMenu_Var2 - Menu Electronics (RTL)");
        stHomePage.navigateToFlyMenu_Apparel();
        takeScreenShot("Menu51.14 Menu51_FlyMenu_Var2 - Menu Apparel (RTL)");
        stHomePage.navigateToFlyMenu_SportsAndOutdoors();
        takeScreenShot("Menu51.16 Menu51_FlyMenu_Var2 - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToFlyMenu_VideoGames();
        takeScreenShot("Menu51.18 Menu51_FlyMenu_Var2 - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}
