import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import taras.adminPanel.ColorSchemeSettings;
import taras.adminPanel.CsCartSettings;

/*
1) "Настройки -- Внешний вид":
Включить быстрый просмотр -- да
Показывать мини-иконки в виде галереи --  нет

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
}