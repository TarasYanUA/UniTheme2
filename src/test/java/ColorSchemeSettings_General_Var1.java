import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import taras.adminPanel.ColorSchemeSettings;
import taras.adminPanel.CsCartSettings;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;

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

public class ColorSchemeSettings_General_Var1 extends TestRunner{
    @Test(priority = 1)
    public void setConfigurationsFor_ColorSchemeSettings_General_Var1() {
        //Настраиваем CS-Cart настройки
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToAppearanceSettingsOfCsCart();
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
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.cookie.click();
        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        stHomePage.hoverToProductInProductBlock();
        takeScreenShot_withoutScroll("2100 ColorScheme, General, Var1 - Block with products");
        stHomePage.selectLanguage_RTL();
        stHomePage.scrollToBlockWithProducts();
        stHomePage.hoverToProductInProductBlock();
        takeScreenShot_withoutScroll("2105 ColorScheme, General, Var1 - Block with products (RTL)");
        stHomePage.selectLanguage_RU();

        //Категория "Телефоны"
        stHomePage.navigateToMenuPhones();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("2110 ColorScheme, General, Var1 - Category page");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot("2115 ColorScheme, General, Var1 - QuickView");
        stCategoryPage.clickButton_WriteReview();
        takeScreenShot("2120 ColorScheme, General, Var1 - Write review");
        stCategoryPage.closeWriteReview.click();
        stCategoryPage.clickCloseQuickView();
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("2125 ColorScheme, General, Var1 - Category page (RTL)");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot("2130 ColorScheme, General, Var1 - QuickView (RTL)");
        stCategoryPage.clickButton_WriteReview();
        takeScreenShot("2135 ColorScheme, General, Var1 - Write review (RTL)");
    }
}