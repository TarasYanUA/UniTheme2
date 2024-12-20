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
Работаем с макетом Default. В этом тест-кейсе используются значения по умолчанию:
+ Показывать иконки для пунктов меню второго уровня --  да
+ Кол-во отображаемых элементов во 2-м уровне меню --   5
+ Кол-во отображаемых элементов в 3-м уровне меню --    10
+ Показать заголовок -- да
*/

public class Menu50_FlyMenu_Var1 extends TestRunner{
    @Test(priority = 1)
    public void setConfiguration_Menu50_FlyMenu_Var1() {
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_WebsiteLayouts();
        csCartSettings.layout_Default.click();
        csCartSettings.setLayoutAsDefault();
        MainMenuSettings mainMenuSettings = new MainMenuSettings();
        mainMenuSettings.gearwheelOfTheBlock_FlyMenu_Default.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        mainMenuSettings.menuSettings_buttonSettings.click();
        if(!mainMenuSettings.setting_ShowIconsForMenuItems.isSelected()){
            mainMenuSettings.setting_ShowIconsForMenuItems.click();
        }
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("5");
        mainMenuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("10");
        if(!mainMenuSettings.setting_ShowTitle.isSelected()){
            mainMenuSettings.setting_ShowTitle.click();
        }
        mainMenuSettings.button_saveBlock.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfiguration_Menu50_FlyMenu_Var1")
    public void check_Menu50_FlyMenu_Var1(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.button_FlyMenu.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ut2-lfl.ty-menu-item__products p")));
        
        SoftAssert softAssert = new SoftAssert();
        AssertsOfMenu assertsOfMenu = new AssertsOfMenu();
        //Проверка, что заголовок меню присутствует
        softAssert.assertTrue(!assertsOfMenu.flyMenu_title.isEmpty(),
                "There is no title in the Fly menu!");
        //Проверяем, что у меню второго уровня есть иконки
        softAssert.assertTrue(!assertsOfMenu.flyMenu_iconsOfSecondLevel.isEmpty(),
                "There are no icons at the menu of the second level!");
        //Проверяем, что Кол-во отображаемых элементов во 2-м уровне меню -- 5
        softAssert.assertTrue(assertsOfMenu.flyMenu_NumberOfElements_SecondLevel.size() == 5,
                "'Number of visible elements in the 2-level menu' is not 5!");
        //Проверяем, что Кол-во отображаемых элементов в 3-м уровне меню -- не меньше 7
        softAssert.assertTrue(assertsOfMenu.flyMenu_NumberOfElements_ThirdLevel.size() >= 7,
                "'Number of visible elements in the 3-level menu' is less than 7!");
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
