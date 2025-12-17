package productPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.asserts.Asserts_CsCartSettings;
import taras.asserts.Asserts_ProductPage;
import taras.asserts.Asserts_ThemeSettings_Product;
import taras.storefront.StProductPage;
import testRunner.TestRunner;

/*
- Настройки CS-Cart "Настройки -> Внешний вид":
    * Показывать мини-иконки в виде галереи --  откл
    * Показывать количество доступных товаров -- вкл
    * Показывать информацию о товаре во вкладках -- вкл
- Настраиваем характеристики:
    * Бренд -- включить настройку "Показывать во вкладке «Характеристики» карточки товара"
- Настраиваем UniTheme настройки:
    * ID пользовательского блока --  106
    * Отображать модификатор количества --  да
    * Отображать код товара --  да
    * Отображать характеристики товара -- да
    * Отображать характеристики в две колонки --    да
    * Отображать краткое описание --    да
    * Отображать информацию о бренде товара --  Название бренда товара
    * Комбинации формаций изображений галереи товара -- 2
- Настраиваем товар Phone Titan:
    * Действие при нулевой цене --  Попросить покупателя ввести цену
    * Цена за единицу --  нет (по причине нулевой цены)
    * Действие при отсутствии товара в наличии --   Предзаказ
    * шаблон страницы товара -- только Каскад
    * Краткое описание --   да
    * Промо-текст -- да
    * Бонусные баллы --  нет (по причине нулевой цены)
    * Оптовые цены -- нет
*/

public class GeneralSettings_St_ProductPage_Cascade_Var2 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductPage_CascadeGallery_Var2(){
        //Настраиваем CS-Cart настройки
        BasicPage basicPage = new BasicPage();
        CsCartSettings csCartSettings = basicPage.navigateToAppearanceSettings();
        UtilsAdm.setCheckboxState(csCartSettings.setting_ThumbnailsGallery, false);
        UtilsAdm.setCheckboxState(csCartSettings.setting_NumberOfAvailableProducts, true);
        new Select(csCartSettings.setting_ProductPageView).selectByValue("abt__ut2_cascade_gallery_template");
        UtilsAdm.setCheckboxState(csCartSettings.setting_ProductDetailsInTab, true);
        basicPage.clickSaveButtonOfSettings();

        //Работаем с настройками характеристики Бренд
        FeaturePage featurePage = basicPage.navigateToSection_Features();
        featurePage.featureBrand.click();
        WebElement checkbox_ShowOnFeaturesTab_Brand = featurePage.showOnFeaturesTab_Brand;
        if (!checkbox_ShowOnFeaturesTab_Brand.isSelected()) {
            checkbox_ShowOnFeaturesTab_Brand.click();
            basicPage.clickSaveButtonOfSettings();
        }

        //Настраиваем UniTheme настройки
        ThemeSettings_Product themeSettingsProduct = basicPage.navigateTo_ThemeSettings_tabProduct();
        UtilsAdm.clickAndType(themeSettingsProduct.setting_CustomBlockID, "109");
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowQuantityChanger, true);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowProductCode, true);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowProductFeatures, true);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_FeaturesInTwoColumns, true);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowShortDescription, true);
        new Select(themeSettingsProduct.setting_ShowProductBrand).selectByValue("name");
        new Select(themeSettingsProduct.setting_CombinationsOfProductGalleryImageFormations).selectByValue("2");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем страницу товара
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Titan");
        UtilsAdm.clickAndType(productSettings.field_Price,"0");
        UtilsAdm.clickAndType(productSettings.field_InStock,"0");
        new Select(productSettings.setting_ZeroPriceAction).selectByValue("A");
        new Select(productSettings.setting_OutOfStockActions).selectByValue("B");
        productSettings.hoverAndTypeField_ShortDescription("Здесь написано краткое описание товара!");
        productSettings.hoverAndTypeField_PromoText("Только до конца недели! Выберите диск с игрой в подарок!");
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductPage_CascadeGallery_Var2")
    public void checkSettingsOnProductPage_CascadeGallery_Var2() {
        BasicPage basicPage = new BasicPage();
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Titan");
        StProductPage stProductPage = productSettings.navigateToProductPage();
        focusBrowserTab(1);
        stProductPage.cookie.click();
        stProductPage.selectLanguage("en");

        Asserts_ThemeSettings_Product asserts_product = new Asserts_ThemeSettings_Product();
        Asserts_CsCartSettings asserts_csCartSettings = new Asserts_CsCartSettings();
        Asserts_ProductPage asserts_productPage = new Asserts_ProductPage();

        //Проверяем, что присутствует ID пользовательского блока
        asserts_product.assertElementPresence(
                "",
                asserts_product.customBlockID,
                "on the product page 'Titan'!",
                true);

        //Проверяем, что Модификатор количества присутствует
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.quantityChanger,
                "on the product page 'Titan'!",
                true);

        //Проверяем, что код товара присутствует
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.productCode,
                "on the product page 'Titan'!",
                true);

        //Проверяем, что название характеристики "Бренд" присутствует
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.productBrandInformation_Name,
                "on the product page 'Titan'!",
                true);

        //Проверяем, что мини-иконки в виде галереи отсутствуют в шаблоне "Каскадная галерея"
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.productPage,
                asserts_csCartSettings.miniThumbnailImagesAsGallery_Disabled,
                "on the product page 'Titan'!",
                true);

        //Проверяем, что информация о товаре отображается во вкладках
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.productPage,
                asserts_csCartSettings.displayProductDetailsInTabs_Enabled,
                "on the product page 'Titan'!",
                true);

        //Проверяем, что характеристика "Бренд" присутствует в заголовке карточки товара
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.productPage,
                asserts_csCartSettings.showInHeaderOnProductPage_Brand,
                "on the product page 'Titan'!",
                true);

        //Проверяем, что Промо-текст присутствует
        asserts_productPage.assertElementPresence(
                Asserts_ProductPage.productPage,
                asserts_productPage.promoText,
                "on the product page 'Titan'!",
                true);

        //Проверяем, что Действие при нулевой цене -- Попросить покупателя ввести цену
        asserts_productPage.assertElementPresence(
                Asserts_ProductPage.productPage,
                asserts_productPage.zeroPriceAction_AskCustomerToEnterPrice,
                "on the product page 'Titan'!",
                true);

        //Проверяем, что настройка "Действие при отсутствии товара в наличии -- Предзаказ"
        asserts_productPage.assertElementPresence(
                Asserts_ProductPage.productPage,
                asserts_productPage.outOfStockActions_BuyInAdvance,
                "on the product page 'Titan'!",
                true);

        //Проверяем, что присутствует Краткое описание товара
        asserts_productPage.assertElementPresence(
                Asserts_ProductPage.productPage,
                asserts_productPage.shortDescription,
                "on the product page 'Titan'!",
                true);

        takeScreenShot_withScroll("Cascade2.10 GS_ProductPage_Var2 - Cascade template");
        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("Cascade2.15 GS_ProductPage_Var2 - Cascade template (RTL)");

        //Проверяем, что характеристики товара расположены в две колонки
        stProductPage.scrollToAndClickTab_Features();
        asserts_product.assertElementPresence(
                "",
                asserts_product.featuresInTwoColumns_Enabled,
                "on the product page 'Titan'!",
                true);

        takeScreenShot("Cascade2.20 GS_ProductPage_Var2 - Product features, two columns (RTL)");
        stProductPage.selectLanguage("en");
        stProductPage.scrollToAndClickTab_Features();
        takeScreenShot("Cascade2.25 GS_ProductPage_Var2 - Product features, two columns");

        System.out.println("GeneralSettings_ProductPage_Cascade_Var2 passed successfully!");
    }
}