package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.BasicPage;
import taras.adminPanel.LayoutPage;
import taras.adminPanel.MainMenuSettings;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOfMenu;
import taras.storefront.StHomePage;
import java.time.Duration;

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
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        mainMenuSettings.menuSettings_buttonSettings.click();
        mainMenuSettings.clickAndType_setting_SecondLevelElements("6");
        mainMenuSettings.clickAndType_setting_ThirdLevelElements("6");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsInThirdLevelOfMenu("4");
        if(!mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()){
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfiguration_Menu50_FlyMenu_Var1")
    public void check_Menu50_FlyMenu_Var1(){
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.button_FlyMenu.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ut2-lfl.ty-menu-item__products p")));
        
        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();

        //Проверяем, что у меню второго уровня есть иконки
        softAssert.assertTrue(!assertsOfMenu.flyMenu_iconsOfSecondLevel.isEmpty(),
                "There are no icons at the menu of the second level!");

        //Проверяем, что Элементов второго уровня -- 6
        softAssert.assertEquals(assertsOfMenu.flyMenu_NumberOfElements_SecondLevel.size(), 6,
                "Number of elements of the second level is not 6!");

        //Проверяем, что Элементов третьего уровня -- 6
        softAssert.assertEquals(assertsOfMenu.flyMenu_NumberOfElements_ThirdLevel.size(), 6,
                "Number of elements of the third level is not 6!");

        //Проверяем, что Количество видимых элементов в третьем уровне меню -- 4
        softAssert.assertEquals(assertsOfMenu.flyMenu_numberOfVisibleElementsIn_3levelMenu.size(), 4,
                "'Number of visible elements in the 3-level menu' is not 4!");

        //Проверяем, что присутствует кнопка "Ещё" у элементов во 2-м уровне меню
        softAssert.assertTrue(!assertsOfMenu.flyMenu_ButtonMore.isEmpty(),
                "There are no buttons 'More' in the elements of the 2-level of Fly menu!");

        //Проверяем, что присутствует кнопка "Больше [категория]"
        softAssert.assertTrue(!assertsOfMenu.flyMenu_ButtonMoreCategories.isEmpty(),
                "There is no any button 'More [category]' in the second level of Fly menu!");

        stHomePage. navigateToFlyMenu_AllProducts();
        takeScreenShot("Menu50.00 Menu50_FlyMenu_Var1 - Menu AllProducts");
        stHomePage.navigateToFlyMenu_Electronics();
        takeScreenShot("Menu50.02 Menu50_FlyMenu_Var1 - Menu Electronics");
        stHomePage.navigateToFlyMenu_Apparel();
        takeScreenShot("Menu50.04 Menu50_FlyMenu_Var1 - Menu Apparel");
        stHomePage.navigateToFlyMenu_SportsAndOutdoors();
        takeScreenShot("Menu50.06 Menu50_FlyMenu_Var1 - Menu SportsAndOutdoors");
        stHomePage.navigateToFlyMenu_VideoGames();
        takeScreenShot("Menu50.08 Menu50_FlyMenu_Var1 - Menu VideoGames");
        stHomePage.button_CloseFlyMenu.click();

        stHomePage.selectLanguage_RTL();
        stHomePage.button_FlyMenu.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ut2-lfl.ty-menu-item__products p")));
        stHomePage.navigateToFlyMenu_AllProducts();
        takeScreenShot("Menu50.10 Menu50_FlyMenu_Var1 - Menu AllProducts (RTL)");
        stHomePage.navigateToFlyMenu_Electronics();
        takeScreenShot("Menu50.12 Menu50_FlyMenu_Var1 - Menu Electronics (RTL)");
        stHomePage.navigateToFlyMenu_Apparel();
        takeScreenShot("Menu50.14 Menu50_FlyMenu_Var1 - Menu Apparel (RTL)");
        stHomePage.navigateToFlyMenu_SportsAndOutdoors();
        takeScreenShot("Menu50.16 Menu50_FlyMenu_Var1 - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToFlyMenu_VideoGames();
        takeScreenShot("Menu50.18 Menu50_FlyMenu_Var1 - Menu VideoGames (RTL)");
        softAssert.assertAll();
    }
}
