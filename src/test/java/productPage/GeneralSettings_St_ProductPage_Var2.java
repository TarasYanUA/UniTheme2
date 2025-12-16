package productPage;

import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.asserts.Asserts_CsCartSettings;
import taras.asserts.Asserts_ProductPage;
import taras.asserts.Asserts_ThemeSettings_Product;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.StProductPage;
import testRunner.TestRunner;

/*
ссылка на чеклист: https://docs.google.com/spreadsheets/d/19qsT6Hm83Kdt1Fh1WMS96sBfyp3wouEMEv17FyEglh0/edit#gid=0
- Настройки CS-Cart "Настройки -> Внешний вид":
    * Показывать мини-иконки в виде галереи     -- откл
    * Показывать количество доступных товаров   -- вкл
    * Показывать информацию о товаре во вкладках-- вкл
- Настраиваем характеристики:
    * Жесткий диск -- включить настройку "Показывать в заголовке карточки товара" и задать Описание
- Настраиваем UniTheme настройки:
    * ID пользовательского блока        -- 109
    * Отображать модификатор количества -- да
    * Отображать код товара             -- да
    * Отображать характеристики товара  -- да
    * Отображать характеристики в две колонки   -- да
    * Отображать краткое описание       -- да
    * Отображать "Вы экономите"         -- Не отображать (по причине нулевой цены)
    * Отображать информацию о бренде товара     -- Название бренда товара
- Настраиваем товар X-Box 360:
    * Действие при нулевой цене         -- Попросить покупателя ввести цену
    * Цена за единицу                   -- нет (по причине нулевой цены)
    * Действие при отсутствии товара в наличии  -- Предзаказ
    * шаблон страницы товара            -- 5 шт (кроме Каскада)
    * Краткое описание                  -- да
    * Промо-текст                       -- да
    * Бонусные баллы                    -- нет (по причине нулевой цены)
    * Оптовые цены                      -- нет
*/

public class GeneralSettings_St_ProductPage_Var2 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductPage_Var2(){
        //Настраиваем CS-Cart настройки
        BasicPage basicPage = new BasicPage();
        CsCartSettings csCartSettings = basicPage.navigateToAppearanceSettings();
        UtilsAdm.setCheckboxState(csCartSettings.setting_ThumbnailsGallery, false);
        UtilsAdm.setCheckboxState(csCartSettings.setting_NumberOfAvailableProducts, true);
        UtilsAdm.setCheckboxState(csCartSettings.setting_ProductDetailsInTab, true);
        basicPage.clickSaveButtonOfSettings();

        //Работаем с настройками характеристик Жесткий диск и Бренд
        FeaturePage featurePage = basicPage.navigateToSection_Features();
        featurePage.feature_HardDrive.click();
        UtilsAdm.waitForPopUpWindow();
        featurePage.clickAndTypeField_DescriptionOfFeature("Для характеристики, которая просто позволяет указать какое-нибудь дополнительное свойство товара. Например, у футболок это может быть \"Ткань\". Если вы создадите фильтр по этой характеристике, покупатели увидят, что она есть, и смогут легко найти по ней нужный товар.");
        UtilsAdm.setCheckboxState(featurePage.showInHeaderOnProductPage_HardDisk, true);
        featurePage.button_SaveFeature.click();

        //Настраиваем UniTheme настройки
        ThemeSettings_Product themeSettingsProduct = basicPage.navigateTo_ThemeSettings_tabProduct();
        UtilsAdm.clickAndType(themeSettingsProduct.setting_CustomBlockID, "109");
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowQuantityChanger, true);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowProductCode, true);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowProductFeatures, true);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_FeaturesInTwoColumns, true);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowShortDescription, true);
        new Select(themeSettingsProduct.setting_ShowYouSave).selectByValue("full");
        new Select(themeSettingsProduct.setting_ShowProductBrand).selectByValue("name");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем страницу товара
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        UtilsAdm.clickAndType(productSettings.field_Price,"0");
        UtilsAdm.clickAndType(productSettings.field_InStock,"0");
        new Select(productSettings.setting_ZeroPriceAction).selectByValue("A");
        new Select(productSettings.setting_OutOfStockActions).selectByValue("B");
        productSettings.selectSetting_ProductTemplate("default_template");
        productSettings.hoverAndTypeField_ShortDescription("Здесь написано краткое описание товара!");
        productSettings.hoverAndTypeField_PromoText("Только до конца недели! Выберите диск с игрой в подарок!");
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductPage_Var2")
    public void checkSettingsOnProductPage_Var2() {
        BasicPage basicPage = new BasicPage();
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
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
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что модификатор количества присутствует
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.quantityChanger,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что код товара присутствует
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.productCode,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что название характеристики "Бренд" присутствует
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.productBrandInformation_Name,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что текст "Вы экономите" отсутствует по причине нулевой цены
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.text_YouSave_Full,
                "on the product page!",
                false);
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.text_YouSave_Short,
                "on the product page!",
                false);

        //Проверяем, что мини-иконки не в виде галереи
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.productPage,
                asserts_csCartSettings.miniThumbnailImagesAsGallery_Disabled,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что информация о товаре отображается во вкладках
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.productPage,
                asserts_csCartSettings.displayProductDetailsInTabs_Enabled,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что характеристика "Бренд" присутствует в заголовке карточки товара
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.productPage,
                asserts_csCartSettings.showInHeaderOnProductPage_Brand,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что характеристика "Жесткий диск" присутствует в заголовке карточки товара
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.productPage,
                asserts_csCartSettings.showInHeaderOnProductPage_HardDrive,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что Промо-текст присутствует
        asserts_productPage.assertElementPresence(
                Asserts_ProductPage.productPage,
                asserts_productPage.promoText,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что Действие при нулевой цене -- Попросить покупателя ввести цену
        asserts_productPage.assertElementPresence(
                Asserts_ProductPage.productPage,
                asserts_productPage.zeroPriceAction_AskCustomerToEnterPrice,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что настройка "Действие при отсутствии товара в наличии -- Предзаказ"
        asserts_productPage.assertElementPresence(
                Asserts_ProductPage.productPage,
                asserts_productPage.outOfStockActions_BuyInAdvance,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что присутствует Краткое описание товара
        asserts_productPage.assertElementPresence(
                Asserts_ProductPage.productPage,
                asserts_productPage.shortDescription,
                "on the product page 'X-Box 360'!",
                true);

        takeScreenShot_withScroll("1000 GS_ProductPage_Var2 - Default template");
        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("1005 GS_ProductPage_Var2 - Default template (RTL)");

        //Проверяем, что характеристики товара расположены в две колонки
        stProductPage.scrollToAndClickTab_Features();
        asserts_product.assertElementPresence(
                "",
                asserts_product.featuresInTwoColumns_Enabled,
                "on the product page 'X-Box 360'!",
                true);

        takeScreenShot("1010 GS_ProductPage_Var2 - Product features, two columns (RTL)");
        stProductPage.selectLanguage("en");
        stProductPage.scrollToAndClickTab_Features();
        takeScreenShot("1015 GS_ProductPage_Var2 - Product features, two columns");
        stProductPage.featureDescription.click();
        UtilsAdm.waitForTitleBarWindow();
        takeScreenShot("1020 GS_ProductPage_Var2 - Feature description, two columns");

        //Другие шаблоны страницы товара
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("bigpicture_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(2);
        takeScreenShot_withScroll("1025 GS_ProductPage_Var2 - Big picture");
        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("1030 GS_ProductPage_Var2 - Big picture (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_flat_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(3);
        takeScreenShot_withScroll("1035 GS_ProductPage_Var2 - Big picture flat");
        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("1040 GS_ProductPage_Var2 - Big picture flat (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_three_columns_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(4);
        takeScreenShot_withScroll("1045 GS_ProductPage_Var2 - Three columned");
        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("1050 GS_ProductPage_Var2 - Three columned (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_gallery_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(5);
        takeScreenShot_withScroll("1055 GS_ProductPage_Var2 - Gallery template");
        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("1060 GS_ProductPage_Var2 - Gallery template (RTL)");

        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductPage_Var2 passed successfully!");
    }
}