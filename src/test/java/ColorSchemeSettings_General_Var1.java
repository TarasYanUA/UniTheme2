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
        colorSchemeSettings.selectActiveColorScheme();
        new Select(colorSchemeSettings.setting_General_RoundCornersForElements).selectByValue("full");
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_General_RoundCornersOfBlocks, true);
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_General_DisplayHeadersInCapitalLetters, true);
        new Select(colorSchemeSettings.setting_General_ButtonsStyle).selectByValue("use_background");
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_General_DisplayTextInCapitalLetters, true);
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_General_AddShadow, false);
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_General_AddBulk, false);
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
        stHomePage.selectLanguage("ar");
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("1305 ColorSchemeSettings_General_Var1 - Block with products (RTL)");
        stHomePage.selectLanguage("ru");

        //Категория "Телефоны"
        stHomePage.navigateToHorizontalMenu_Phones();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.hoverToProduct("Droid 3");
        takeScreenShot("1310 ColorSchemeSettings_General_Var1 - Category page");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot("1315 ColorSchemeSettings_General_Var1 - QuickView");
        stCategoryPage.openWindow_WriteReview();
        takeScreenShot("1320 ColorSchemeSettings_General_Var1 - Write review");
        stCategoryPage.closeWriteReview.click();
        stHomePage.selectLanguage("ar");
        stCategoryPage.hoverToProduct("Droid 3");
        takeScreenShot("1325 ColorSchemeSettings_General_Var1 - Category page (RTL)");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot("1330 ColorSchemeSettings_General_Var1 - QuickView (RTL)");
        stCategoryPage.openWindow_WriteReview();
        takeScreenShot("1335 ColorSchemeSettings_General_Var1 - Write review (RTL)");
        System.out.println("ColorSchemeSettings_General_Var1 has passed successfully!");
    }
}