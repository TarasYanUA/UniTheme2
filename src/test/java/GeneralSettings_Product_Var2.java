import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ProductSettings;
import taras.adminPanel.ThemeSettings_Product;
import taras.constants.DriverProvider;
import taras.storefront.ProductPage;
import java.io.IOException;
import java.time.Duration;

/*
- Настройки CS-Cart "Настройки -> Внешний вид":
    * Показывать мини-иконки в виде галереи --  откл
    * Показывать количество доступных товаров -- вкл
    * Показывать информацию о товаре во вкладках -- вкл
- Настраиваем характеристики:
    * Бренд -- включить настройку "Показывать в заголовке карточки товара"
    * Жесткий диск -- включить настройку "Показывать в заголовке карточки товара" и задать Описание
- Настраиваем UniTheme настройки:
    * ID пользовательского блока --  93
    * Отображать модификатор количества --  да
    * Отображать код товара --  да
    * Отображать характеристики товара -- да
    * Отображать характеристики в две колонки --    да
    * Отображать краткое описание --    да
    * Отображать информацию о бренде товара --  Название бренда товара
- Настраиваем товар X-Box 360:
    * Действие при нулевой цене --  Попросить покупателя ввести цену
    * Цена за единицу --  нет (по причине нулевой цены)
    * Действие при отсутствии товара в наличии --   Предзаказ
    * шаблон страницы товара -- все 4 шт
    * Краткое описание --   да
    * Промо-текст -- да
    * Бонусные баллы --  нет (по причине нулевой цены)
*/

public class GeneralSettings_Product_Var2 extends TestRunner{
    @Test(priority = 1)
    public void setConfigurationsForProductPage_Var2(){
        //Настраиваем CS-Cart настройки
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToAppearanceSettingsOfCsCart();
        if(csCartSettings.setting_ThumbnailsGallery.isSelected()){
            csCartSettings.setting_ThumbnailsGallery.click();
        }
        if(!csCartSettings.setting_NumberOfAvailableProducts.isSelected()){
            csCartSettings.setting_NumberOfAvailableProducts.click();
        }
        if(!csCartSettings.setting_ProductDetailsInTab.isSelected()){
            csCartSettings.setting_ProductDetailsInTab.click();
        }
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем UniTheme настройки
        csCartSettings.navigateToAddonsPage();
        csCartSettings.clickThemeSectionsOnManagementPage();
        csCartSettings.navigateToThemeSettings();
        ThemeSettings_Product themeSettingsProduct = new ThemeSettings_Product();
        themeSettingsProduct.tab_Product.click();
        if(!themeSettingsProduct.setting_ShowQuantityChanger.isSelected()){
            themeSettingsProduct.setting_ShowQuantityChanger.click();
        }
        if(!themeSettingsProduct.setting_ShowProductCode.isSelected()){
            themeSettingsProduct.setting_ShowProductCode.click();
        }
        if(!themeSettingsProduct.setting_ShowProductFeatures.isSelected()){
            themeSettingsProduct.setting_ShowProductFeatures.click();
        }
        if(!themeSettingsProduct.setting_FeaturesInTwoColumns.isSelected()){
            themeSettingsProduct.setting_FeaturesInTwoColumns.click();
        }
        if(!themeSettingsProduct.setting_ShowShortDescription.isSelected()){
            themeSettingsProduct.setting_ShowShortDescription.click();
        }
        themeSettingsProduct.selectSetting_ShowProductBrand("name");
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем страницу товара
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        productSettings.chooseAnyProduct.click();
        productSettings.clickAndTypeField_Price("0");
        productSettings.clickAndTypeField_InStock("0");
        productSettings.selectSetting_ZeroPriceAction("A");
        productSettings.selectSetting_OutOfStockActions("B");
        productSettings.selectSetting_ProductTemplate("default_template");
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2)
    public void checkSettingsOnProductPage_Var2() throws IOException {
        CsCartSettings csCartSettings = new CsCartSettings();
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        productSettings.chooseAnyProduct.click();
        ProductPage productPage = productSettings.navigateToProductPage();
        focusBrowserTab(1);
        productPage.shiftLanguage_RU();
        //Проверяем, что мини-иконки не в виде галереи
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-product-thumbnails.ty-center.cm-image-gallery")).size() >=1,
                "Mini-icons are in view of gallery but shouldn't!");
        //Проверяем, что количество доступных товаров присутствует
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("#qty_in_stock_248")).size() >=1,
                "There is no number of available products!");
        //Проверяем, что информация о товаре отображается не во вкладках
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[class='ty-tabs cm-j-tabs  clearfix'] ul[class='ty-tabs__list']"))
                .size() >=1, "Product information is displayed in tabs but shouldn't!");
        //Проверяем, что название характеристики "Бренд" присутствует
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__product-brand-name")).size() >=1,
                "There is no Brand name on product page!");
        //Проверяем, что характеристика "Бренд" присутствует в заголовке карточки товара
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.xpath("//div[@class='ty-features-list']//em[text()='Бренд']"))
                        .size() >=1,"There is no feature Brand on the feature list!");
        //Проверяем, что характеристика "Жесткий диск" присутствует в заголовке карточки товара
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.xpath("//div[@class='ty-features-list']//em[text()='Жесткий диск']"))
                .size() >=1,"There is no feature Hard drive on the feature list!");
        //Проверяем, что присутствует ID пользовательского блока
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".col-right .ut2-settings-desktop")).size() >=1,
                "There is no Custom block in the right column!");
        //Проверяем, что модификатор количества присутствует
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-qty#qty_248")).size() >=1,
                "There is no Quantity changer!");
        //Проверяем, что Код товара присутствует
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__sku")).size() >=1,
                "There is no product code!");
        //Проверяем, что присутствует Краткое описание товара
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__short-descr")).size() >=1,
                "There is no product Short description!");
        //Проверяем, что Действие при нулевой цене -- Попросить покупателя ввести цену
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-price-curency__input")).size() >=1,
                "There is no field 'Enter your price'!");
        //Проверяем, что Действие при отсутствии товара в наличии - Предзаказ
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".on_backorder")).size() >=1,
                "There is no field 'Backorder'!");
        //Проверяем, что Промо-текст присутствует
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__note")).size() >=1,
                "There is no Promo-text!");
        takeScreenShot("200 Product page, Default template, Var2");
        productPage.shiftLanguage_RTL();
        makePause();
        takeScreenShot("205 Product page, Default template, Var2 (RTL)");

        //Проверяем характеристики
        productPage.scrollToAndClickTab_Features();
        //Проверяем, что характеристики расположены в две колонки
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".fg-two-col']"))
                .size() >=1, "Features are located in one column instead of two!");
        takeScreenShot("210 Product features, two columns (RTL)");
        productPage.shiftLanguage_RU();
        productPage.scrollToAndClickTab_Features();
        takeScreenShot("215 Product features, two columns");
        productPage.featureDescription.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-titlebar")));
        takeScreenShot("220 Feature description, two columns");

        //Другие шаблоны страницы товара
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("bigpicture_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(2);
        takeScreenShot("225 Template - Big picture, Var2");
        productPage.shiftLanguage_RTL();
        takeScreenShot("230 Template - Big picture, Var2 (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_flat_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(3);
        takeScreenShot("235 Template - Big picture flat, Var2");
        productPage.shiftLanguage_RTL();
        takeScreenShot("240 Template - Big picture flat, Var2 (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_three_columns_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(4);
        takeScreenShot("245 Template - Three columned, Var2");
        productPage.shiftLanguage_RTL();
        takeScreenShot("250 Template - Three columned, Var2 (RTL)");
    }
}