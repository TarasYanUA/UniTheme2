import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import taras.adminPanel.ColorSchemeSettings;
import taras.adminPanel.CsCartSettings;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;

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
        //Настраиваем CS-Cart настройки
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToAppearanceSettings();
        WebElement checkboxThumbnailsGallery = csCartSettings.setting_ThumbnailsGallery;
        if (!checkboxThumbnailsGallery.isSelected()) {
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
        colorSchemeSettings.selectSetting_General_RoundCornersForElements("do_not_use");
        if(colorSchemeSettings.setting_General_RoundCornersOfBlocks.isSelected()){
            colorSchemeSettings.setting_General_RoundCornersOfBlocks.click();
        }
        if(colorSchemeSettings.setting_General_DisplayHeadersInCapitalLetters.isSelected()){
            colorSchemeSettings.setting_General_DisplayHeadersInCapitalLetters.click();
        }
        colorSchemeSettings.selectSetting_General_ButtonsStyle("use_border");
        if(colorSchemeSettings.setting_General_DisplayTextInCapitalLetters.isSelected()){
            colorSchemeSettings.setting_General_DisplayTextInCapitalLetters.click();
        }
        if(!colorSchemeSettings.setting_General_AddShadow.isSelected()){
            colorSchemeSettings.setting_General_AddShadow.click();
        }
        if(!colorSchemeSettings.setting_General_AddBulk.isSelected()){
            colorSchemeSettings.setting_General_AddBulk.click();
        }
        colorSchemeSettings.selectSetting_General_CartIcon("type7");
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsFor_ColorSchemeSettings_General_Var2",
    description = "Здесь проверок нет, так как настройки цветосхемы отсутствуют в коде")
    public void checkColorSchemeSettings_General_Var2(){
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        stHomePage.hoverToProductInProductBlock();
        takeScreenShot("1400 ColorSchemeSettings_General_Var2 - Block with products");
        stHomePage.selectLanguage_RTL();
        stHomePage.scrollToBlockWithProducts();
        stHomePage.hoverToProductInProductBlock();
        takeScreenShot("1405 ColorSchemeSettings_General_Var2 - Block with products (RTL)");
        stHomePage.selectLanguage_RU();

        //Категория "Телефоны"
        stHomePage.navigateToHorizontalMenu_Phones();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("1410 ColorSchemeSettings_General_Var2 - Category page");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot_withScroll("1415 ColorSchemeSettings_General_Var2 - QuickView");
        stCategoryPage.clickButton_WriteReview();
        takeScreenShot_withScroll("1420 ColorSchemeSettings_General_Var2 - Write review");
        stCategoryPage.closeWriteReview.click();
        stCategoryPage.clickCloseQuickView();
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("1425 ColorSchemeSettings_General_Var2 - Category page (RTL)");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot_withScroll("1430 ColorSchemeSettings_General_Var2 - QuickView (RTL)");
        stCategoryPage.clickButton_WriteReview();
        takeScreenShot_withScroll("1435 ColorSchemeSettings_General_Var2 - Write review (RTL)");
    }
}