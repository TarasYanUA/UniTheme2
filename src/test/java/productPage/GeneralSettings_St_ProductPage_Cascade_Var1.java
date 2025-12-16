package productPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.*;
import taras.asserts.Asserts_CsCartSettings;
import taras.asserts.Asserts_ProductPage;
import taras.asserts.Asserts_ThemeSettings_Product;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.StProductPage;
import testRunner.TestRunner;

/*
- Настройки CS-Cart "Настройки -> Внешний вид":
    * Показывать мини-иконки в виде галереи --  вкл
    * Показывать количество доступных товаров -- откл
    * Показывать информацию о товаре во вкладках -- вкл
- Настраиваем характеристики:
    * Бренд -- включить настройку "Показывать во вкладке «Характеристики» карточки товара"
- Настраиваем UniTheme настройки:
    * ID пользовательского блока --  106
    * Отображать модификатор количества --  нет
    * Отображать код товара --  нет
    * Отображать характеристики товара -- да
    * Отображать характеристики в две колонки --    нет
    * Отображать краткое описание --    да
    * Отображать информацию о бренде товара --  Логотип бренда товара
    * Комбинации формаций изображений галереи товара -- 1
- Настраиваем товар Phone Titan:
    * Действие при нулевой цене --  Не разрешать добавлять товар в корзину
    * Цена за единицу --  да
    * Действие при отсутствии товара в наличии --   Не выбрано
    * шаблон страницы товара -- только Каскад
    * Краткое описание --   да
    * Промо-текст -- да
    * Бонусные баллы --  да
    * Оптовые цены -- нет
*/

public class GeneralSettings_St_ProductPage_Cascade_Var1 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductPage_CascadeGallery_Var1(){
        //Настраиваем макет для тест-кейса
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Lightv2.click();
        layoutPage.setLayoutAsDefault();

        //Настраиваем CS-Cart настройки
        CsCartSettings csCartSettings = basicPage.navigateToAppearanceSettings();
        UtilsAdm.setCheckboxState(csCartSettings.setting_ThumbnailsGallery, true);
        UtilsAdm.setCheckboxState(csCartSettings.setting_NumberOfAvailableProducts, false);
        new Select(csCartSettings.setting_ProductPageView).selectByValue("abt__ut2_cascade_gallery_template");
        UtilsAdm.setCheckboxState(csCartSettings.setting_ProductDetailsInTab, true);
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme настройки
        basicPage.navigateTo_ThemeSettings_tabProductLists();
        ThemeSettings_Product themeSettingsProduct = new ThemeSettings_Product();
        themeSettingsProduct.tab_Product.click();
        UtilsAdm.clickAndType(themeSettingsProduct.setting_CustomBlockID, "106");
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowQuantityChanger, false);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowProductCode, false);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowProductFeatures, true);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_FeaturesInTwoColumns, false);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowShortDescription, true);
        new Select(themeSettingsProduct.setting_ShowProductBrand).selectByValue("logo");
        new Select(themeSettingsProduct.setting_CombinationsOfProductGalleryImageFormations).selectByValue("1");
        basicPage.clickSaveButtonOfSettings();

        //Работаем с настройками характеристики Бренд
        FeaturePage featurePage = basicPage.navigateToSection_Features();
        featurePage.featureBrand.click();
        WebElement checkbox_ShowOnFeaturesTab_Brand = featurePage.showOnFeaturesTab_Brand;
        if (!checkbox_ShowOnFeaturesTab_Brand.isSelected()) {
            checkbox_ShowOnFeaturesTab_Brand.click();
            basicPage.clickSaveButtonOfSettings();
        }

        //Настраиваем страницу товара
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Titan");
        UtilsAdm.clickAndType(productSettings.field_Price,"10000.00");
        UtilsAdm.clickAndType(productSettings.field_InStock,"20");
        new Select(productSettings.setting_ZeroPriceAction).selectByValue("R");
        productSettings.setPricePerUnit("шт", "3", "1");
        new Select(productSettings.setting_OutOfStockActions).selectByValue("N");
        productSettings.hoverAndTypeField_ShortDescription("Здесь написано краткое описание товара!");
        productSettings.hoverAndTypeField_PromoText("Только до конца недели! Выберите диск с игрой в подарок!");
        UtilsAdm.hoverNavigateAndClick(productSettings.tab_RewardPoints);
        UtilsAdm.setCheckboxState(productSettings.setting_AllowPaymentByPoints, true);
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductPage_CascadeGallery_Var1")
    public void checkSettingsOnProductPage_CascadeGallery_Var1() {
        BasicPage basicPage = new BasicPage();
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Titan");
        StProductPage stProductPage = productSettings.navigateToProductPage();
        focusBrowserTab(1);
        stProductPage.cookie.click();
        stProductPage.selectLanguage("en");

        SoftAssert softAssert = new SoftAssert();
        Asserts_ThemeSettings_Product asserts_product = new Asserts_ThemeSettings_Product();
        Asserts_CsCartSettings asserts_csCartSettings = new Asserts_CsCartSettings();
        Asserts_ProductPage asserts_productPage = new Asserts_ProductPage();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что присутствует ID пользовательского блока
        asserts_product.assertElementPresence(
                "",
                asserts_product.customBlockID,
                "on the product page 'Titan'!",
                true);

        //Проверяем, что логотип характеристики "Бренд" присутствует
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.productBrandInformation_Logo,
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

        //Проверяем, что присутствует Цена за единицу
        asserts_productPage.assertElementPresence(
                Asserts_ProductPage.productPage,
                asserts_productPage.pricePerUnit,
                "on the product page 'Titan'!",
                true);

        //Проверяем, что присутствует Краткое описание товара
        asserts_productPage.assertElementPresence(
                Asserts_ProductPage.productPage,
                asserts_productPage.shortDescription,
                "on the product page 'Titan'!",
                true);

        //Проверяем, что Бонусные баллы присутствуют
        asserts_productPage.assertElementPresence(
                Asserts_ProductPage.productPage,
                asserts_productPage.allowPaymentByPoints,
                "on the product page 'Titan'!",
                true);

        takeScreenShot_withScroll("Cascade1.10 GS_ProductPage_Cascade_Var1 - Cascade template");
        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("Cascade1.15 GS_ProductPage_Cascade_Var1 - Cascade template (RTL)");

        //Проверяем, что характеристики товара расположены в одну колонку
        stProductPage.scrollToAndClickTab_Features();
        asserts_product.assertElementPresence(
                "",
                asserts_product.featuresInTwoColumns_Disabled,
                "on the product page 'Titan'!",
                true);

        takeScreenShot("Cascade1.20 GS_ProductPage_Cascade_Var1 - Product features, one column (RTL)");
        stProductPage.selectLanguage("en");
        stProductPage.scrollToAndClickTab_Features();
        takeScreenShot("Cascade1.25 GS_ProductPage_Cascade_Var1 - Product features, one column");

        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductPage_Cascade_Var1 passed successfully!");
    }
}