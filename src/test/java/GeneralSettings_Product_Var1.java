import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ThemeSettings_Product;

/*
- Настраиваем CS-Cart настройки:
    * Показывать мини-иконки в виде галереи --  вкл
    * Показывать цену с налогами -- откл
    * Показывать количество доступных товаров -- откл
    * Показывать информацию о товаре во вкладках -- вкл
- Настраиваем UniTheme настройки:
    * ID пользовательского блока --  93
    * Отображать модификатор количества --  да
    * Отображать код товара --  нет
    * Отображать характеристики товара -- да
    * Отображать характеристики в две колонки --    нет
    * Отображать краткое описание --    да --   задать товару краткое описание!!!!!
    * Отображать информацию о бренде товара --  да
*/

public class GeneralSettings_Product_Var1 extends TestRunner{
    @Test
    public void setConfigurationsForProductPage(){
        //Настраиваем CS-Cart настройки
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToAppearanceSettingsOfCsCart();
        if(!csCartSettings.setting_ThumbnailsGallery.isSelected()){
            csCartSettings.setting_ThumbnailsGallery.click();
        }
        if(csCartSettings.setting_PriceWithTaxes.isSelected()){
            csCartSettings.setting_PriceWithTaxes.click();
        }
        if(csCartSettings.setting_NumberOfAvailableProducts.isSelected()){
            csCartSettings.setting_NumberOfAvailableProducts.click();
        }
        if(!csCartSettings.setting_ProductDetailsInTab.isSelected()){
            csCartSettings.setting_ProductDetailsInTab.click();
        }
        csCartSettings.clickSaveButtonOfSettings();
        //Работаем с настройками характеристики Бренд
        csCartSettings.hoverToProductMenu();
        csCartSettings.navigateMenuFeatures();
        csCartSettings.clickFeatureBrand();
        WebElement checkboxShowInProductList = csCartSettings.showInProductList;
        if(!checkboxShowInProductList.isSelected()){
            csCartSettings.showInProductList.click();
        }
        //Настраиваем UniTheme настройки
        csCartSettings.navigateToThemeSettings();
        ThemeSettings_Product themeSettingsProduct = new ThemeSettings_Product();
        themeSettingsProduct.tab_Product.click();
    }

    @Test
    public void checkSettingsOnProductPage(){

    }
}