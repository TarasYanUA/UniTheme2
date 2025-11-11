import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

/*
1) CS-Cart настройки -- Внешний вид:
Показывать мини-иконки в виде галереи --  нет
Включить быстрый просмотр -- да

2) UniTheme2 -- Настройки цветосхемы -- вкладка "Общее":
Скруглить углы для элементов интерфейса --  Полностью скруглить
Скруглить углы блоков, окон, баннеров --    да
Отображать заголовки заглавными буквами --  да
Стиль --    Использовать фоновую заливку
Отображать текст заглавными буквами --  да
Отображать тень --  нет
Добавить объем --   нет
Иконка Корзины -- Вариант 1
*/

public class ColorSchemeSettings_General_Var1 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsFor_ColorSchemeSettings_General_Var1() {
        //Настраиваем макет для тест-кейса
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Lightv2.click();
        layoutPage.setLayoutAsDefault();

        //Настраиваем CS-Cart настройки
        CsCartSettings csCartSettings = basicPage.navigateToAppearanceSettings();
        UtilsAdm.setCheckboxState(csCartSettings.setting_ThumbnailsGallery, false);
        UtilsAdm.setCheckboxState(csCartSettings.setting_QuickView, true);
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Общее"
        ColorSchemeSettings colorSchemeSettings = basicPage.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.fieldOfActiveColorScheme.click();
        colorSchemeSettings.activeColorScheme.click();
        makePause();
        new Select(colorSchemeSettings.setting_General_RoundCornersForElements).selectByValue("full");
        if (!colorSchemeSettings.setting_General_RoundCornersOfBlocks.isSelected())
            colorSchemeSettings.setting_General_RoundCornersOfBlocks.click();
        if (!colorSchemeSettings.setting_General_DisplayHeadersInCapitalLetters.isSelected())
            colorSchemeSettings.setting_General_DisplayHeadersInCapitalLetters.click();
        new Select(colorSchemeSettings.setting_General_ButtonsStyle).selectByValue("use_background");
        if (!colorSchemeSettings.setting_General_DisplayTextInCapitalLetters.isSelected())
            colorSchemeSettings.setting_General_DisplayTextInCapitalLetters.click();
        if (colorSchemeSettings.setting_General_AddShadow.isSelected())
            colorSchemeSettings.setting_General_AddShadow.click();
        if (colorSchemeSettings.setting_General_AddBulk.isSelected())
            colorSchemeSettings.setting_General_AddBulk.click();
        new Select(colorSchemeSettings.setting_General_CartIcon).selectByValue("type1");
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsFor_ColorSchemeSettings_General_Var1",
            description = "Здесь проверок нет, так как настройки цветосхемы отсутствуют в коде")
    public void checkColorSchemeSettings_General_Var1() {
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("1300 ColorSchemeSettings_General_Var1 - Block with products");
        stHomePage.selectLanguage_RTL();
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("1305 ColorSchemeSettings_General_Var1 - Block with products (RTL)");
        stHomePage.selectLanguage_RU();

        //Категория "Телефоны"
        stHomePage.navigateToHorizontalMenu_Phones();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("1310 ColorSchemeSettings_General_Var1 - Category page");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot("1315 ColorSchemeSettings_General_Var1 - QuickView");
        stCategoryPage.clickButton_WriteReview();
        takeScreenShot("1320 ColorSchemeSettings_General_Var1 - Write review");
        stCategoryPage.closeWriteReview.click();
        stCategoryPage.clickCloseQuickView();
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("1325 ColorSchemeSettings_General_Var1 - Category page (RTL)");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot("1330 ColorSchemeSettings_General_Var1 - QuickView (RTL)");
        stCategoryPage.clickButton_WriteReview();
        takeScreenShot("1335 ColorSchemeSettings_General_Var1 - Write review (RTL)");
        System.out.println("ColorSchemeSettings_General_Var1 has passed successfully!");
    }
}