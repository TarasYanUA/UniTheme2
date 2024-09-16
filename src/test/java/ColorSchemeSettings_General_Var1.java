import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import taras.adminPanel.ColorSchemeSettings;
import taras.adminPanel.CsCartSettings;
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
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_WebsiteLayouts();
        csCartSettings.layout_Lightv2.click();
        csCartSettings.setLayoutAsDefault();

        //Настраиваем CS-Cart настройки
        csCartSettings.navigateToAppearanceSettings();
        WebElement checkboxThumbnailsGallery = csCartSettings.setting_ThumbnailsGallery;
        if (checkboxThumbnailsGallery.isSelected()) {
            checkboxThumbnailsGallery.click();
        }
        WebElement checkboxSettingQuickView = csCartSettings.setting_QuickView;
        if (!checkboxSettingQuickView.isSelected()) {
            checkboxSettingQuickView.click();
        }
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Общее"
        ColorSchemeSettings colorSchemeSettings = csCartSettings.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.fieldOfActiveColorScheme.click();
        colorSchemeSettings.activeColorScheme.click();
        makePause();
        colorSchemeSettings.selectSetting_General_RoundCornersForElements("full");
        if(!colorSchemeSettings.setting_General_RoundCornersOfBlocks.isSelected()){
            colorSchemeSettings.setting_General_RoundCornersOfBlocks.click();
        }
        if(!colorSchemeSettings.setting_General_DisplayHeadersInCapitalLetters.isSelected()){
            colorSchemeSettings.setting_General_DisplayHeadersInCapitalLetters.click();
        }
        colorSchemeSettings.selectSetting_General_ButtonsStyle("use_background");
        if(!colorSchemeSettings.setting_General_DisplayTextInCapitalLetters.isSelected()){
            colorSchemeSettings.setting_General_DisplayTextInCapitalLetters.click();
        }
        if(colorSchemeSettings.setting_General_AddShadow.isSelected()){
            colorSchemeSettings.setting_General_AddShadow.click();
        }
        if(colorSchemeSettings.setting_General_AddBulk.isSelected()){
            colorSchemeSettings.setting_General_AddBulk.click();
        }
        colorSchemeSettings.selectSetting_General_CartIcon("type1");
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsFor_ColorSchemeSettings_General_Var1",
            description = "Здесь проверок нет, так как настройки цветосхемы отсутствуют в коде")
    public void checkColorSchemeSettings_General_Var1(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefront();
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
        takeScreenShot_withScroll("1315 ColorSchemeSettings_General_Var1 - QuickView");
        stCategoryPage.clickButton_WriteReview();
        takeScreenShot_withScroll("1320 ColorSchemeSettings_General_Var1 - Write review");
        stCategoryPage.closeWriteReview.click();
        stCategoryPage.clickCloseQuickView();
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("1325 ColorSchemeSettings_General_Var1 - Category page (RTL)");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot_withScroll("1330 ColorSchemeSettings_General_Var1 - QuickView (RTL)");
        stCategoryPage.clickButton_WriteReview();
        takeScreenShot_withScroll("1335 ColorSchemeSettings_General_Var1 - Write review (RTL)");
        System.out.println("ColorSchemeSettings_General_Var1 has passed successfully!");
    }
}