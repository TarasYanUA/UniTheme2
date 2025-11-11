import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

/*
1) CS-Cart настройки -- Внешний вид:
Показывать мини-иконки в виде галереи --  да
Включить быстрый просмотр -- да

2) UniTheme2 -- Настройки цветосхемы -- вкладка "Общее":
Скруглить углы для элементов интерфейса --  Не использовать
Скруглить углы блоков, окон, баннеров --    нет
Отображать заголовки заглавными буквами --  нет
Стиль --    Использовать только контур
Отображать текст заглавными буквами --  нет
Отображать тень --  да
Добавить объем --   да
Иконка Корзины -- Вариант 7
*/

public class ColorSchemeSettings_General_Var2 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsFor_ColorSchemeSettings_General_Var2() {
        //Настраиваем макет для тест-кейса
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Lightv2.click();
        layoutPage.setLayoutAsDefault();

        //Настраиваем CS-Cart настройки
        CsCartSettings csCartSettings = basicPage.navigateToAppearanceSettings();
        UtilsAdm.setCheckboxState(csCartSettings.setting_ThumbnailsGallery, true);
        UtilsAdm.setCheckboxState(csCartSettings.setting_QuickView, true);
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Общее"
        ColorSchemeSettings colorSchemeSettings = basicPage.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.fieldOfActiveColorScheme.click();
        colorSchemeSettings.activeColorScheme.click();
        makePause();
        new Select(colorSchemeSettings.setting_General_RoundCornersForElements).selectByValue("do_not_use");
        if (colorSchemeSettings.setting_General_RoundCornersOfBlocks.isSelected())
            colorSchemeSettings.setting_General_RoundCornersOfBlocks.click();
        if (colorSchemeSettings.setting_General_DisplayHeadersInCapitalLetters.isSelected())
            colorSchemeSettings.setting_General_DisplayHeadersInCapitalLetters.click();
        new Select(colorSchemeSettings.setting_General_ButtonsStyle).selectByValue("use_border");
        if (colorSchemeSettings.setting_General_DisplayTextInCapitalLetters.isSelected())
            colorSchemeSettings.setting_General_DisplayTextInCapitalLetters.click();
        if (!colorSchemeSettings.setting_General_AddShadow.isSelected())
            colorSchemeSettings.setting_General_AddShadow.click();
        if (!colorSchemeSettings.setting_General_AddBulk.isSelected())
            colorSchemeSettings.setting_General_AddBulk.click();
        new Select(colorSchemeSettings.setting_General_CartIcon).selectByValue("type7");
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsFor_ColorSchemeSettings_General_Var2",
            description = "Здесь проверок нет, так как настройки цветосхемы отсутствуют в коде")
    public void checkColorSchemeSettings_General_Var2() {
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("1400 ColorSchemeSettings_General_Var2 - Block with products");
        stHomePage.selectLanguage_RTL();
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("1405 ColorSchemeSettings_General_Var2 - Block with products (RTL)");
        stHomePage.selectLanguage_RU();

        //Категория "Телефоны"
        stHomePage.navigateToHorizontalMenu_Phones();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("1410 ColorSchemeSettings_General_Var2 - Category page");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot("1415 ColorSchemeSettings_General_Var2 - QuickView");
        stCategoryPage.clickButton_WriteReview();
        takeScreenShot("1420 ColorSchemeSettings_General_Var2 - Write review");
        stCategoryPage.closeWriteReview.click();
        stCategoryPage.clickCloseQuickView();
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("1425 ColorSchemeSettings_General_Var2 - Category page (RTL)");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot("1430 ColorSchemeSettings_General_Var2 - QuickView (RTL)");
        stCategoryPage.clickButton_WriteReview();
        takeScreenShot("1435 ColorSchemeSettings_General_Var2 - Write review (RTL)");
        System.out.println("ColorSchemeSettings_General_Var2 has passed successfully!");
    }
}