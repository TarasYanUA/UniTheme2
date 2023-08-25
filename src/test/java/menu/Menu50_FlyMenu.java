package menu;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.MenuSettings;
import taras.constants.DriverProvider;
import taras.storefront.StHomePage;
import java.time.Duration;

/*
Работаем с макетом Default. В этом тест-кейсе используются значения по умолчанию:
+ Показывать иконки для пунктов меню второго уровня -- да
+ Кол-во отображаемых элементов во 2-м уровне меню -- 5
+ Кол-во отображаемых элементов в 3-м уровне меню -- 10
+ Показать заголовок -- нет

*/

public class Menu50_FlyMenu extends TestRunner{
    @Test(priority = 1)
    public void setConfiguration_Menu50_FlyMenu() {
        //Настраиваем меню на странице "Дизайн -- Макеты -- вкладка "По умолчанию"
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_DesignLayouts();
        csCartSettings.layout_Default.click();
        csCartSettings.setLayoutAsDefault();
        MenuSettings menuSettings = new MenuSettings();
        menuSettings.gearwheelOfTheBlock_FlyMenu.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-titlebar")));
        menuSettings.menuSettings_buttonSettings.click();
        if(!menuSettings.setting_ShowIconsForMenuItems.isSelected()){
            menuSettings.setting_ShowIconsForMenuItems.click();
        }
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_2LevelMenu("5");
        menuSettings.clickAndType_setting_NumberOfVisibleElementsIn_3LevelMenu("10");
        if(menuSettings.setting_ShowTitle.isSelected()){
            menuSettings.setting_ShowTitle.click();
        }
        menuSettings.button_saveBlock.click();
    }
//(priority = 2, dependsOnMethods = "setConfiguration_Menu50_FlyMenu")
    @Test
    public void check_Menu50_FlyMenu(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.button_FlyMenu.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ut2-lfl.ty-menu-item__products p")));
        stHomePage. navigateToFlyMenu_AllProducts();
        takeScreenShot("Menu50.00 Menu50_FlyMenu - Menu AllProducts");
        stHomePage.navigateToFlyMenu_Electronics();
        takeScreenShot("Menu50.02 Menu50_FlyMenu - Menu Electronics");
        stHomePage.navigateToFlyMenu_Apparel();
        takeScreenShot("Menu50.04 Menu50_FlyMenu - Menu Apparel");
        stHomePage.navigateToFlyMenu_SportsAndOutdoors();
        takeScreenShot("Menu50.06 Menu50_FlyMenu - Menu SportsAndOutdoors");
        stHomePage.navigateToFlyMenu_VideoGames();
        takeScreenShot("Menu50.08 Menu50_FlyMenu - Menu VideoGames");
        stHomePage.button_CloseFlyMenu.click();

        stHomePage.selectLanguage_RTL();
        stHomePage.button_FlyMenu.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ut2-lfl.ty-menu-item__products p")));
        stHomePage.navigateToFlyMenu_AllProducts();
        takeScreenShot("Menu50.10 Menu50_FlyMenu - Menu AllProducts (RTL)");
        stHomePage.navigateToFlyMenu_Electronics();
        takeScreenShot("Menu50.12 Menu50_FlyMenu - Menu Electronics (RTL)");
        stHomePage.navigateToFlyMenu_Apparel();
        takeScreenShot("Menu50.14 Menu50_FlyMenu - Menu Apparel (RTL)");
        stHomePage.navigateToFlyMenu_SportsAndOutdoors();
        takeScreenShot("Menu50.16 Menu50_FlyMenu - Menu SportsAndOutdoors (RTL)");
        stHomePage.navigateToFlyMenu_VideoGames();
        takeScreenShot("Menu50.18 Menu50_FlyMenu - Menu VideoGames (RTL)");
    }
}
