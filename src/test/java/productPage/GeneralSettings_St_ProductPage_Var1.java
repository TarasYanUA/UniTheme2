package productPage;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.adminPanel.themeSettings.ThemeSettings_Product;
import taras.asserts.Asserts_CsCartSettings;
import taras.asserts.Asserts_ProductPage;
import taras.asserts.Asserts_ThemeSettings_Product;
import taras.storefront.StProductPage;
import testRunner.TestRunner;

/*
ссылка на чеклист: https://docs.google.com/spreadsheets/d/19qsT6Hm83Kdt1Fh1WMS96sBfyp3wouEMEv17FyEglh0/edit#gid=0
- Настройки CS-Cart "Настройки -> Внешний вид":
    * Показывать мини-иконки в виде галереи     -- вкл
    * Показывать количество доступных товаров   -- откл
    * Показывать информацию о товаре во вкладках-- вкл
- Настраиваем характеристики:
    * Жесткий диск -- включить настройку "Показывать в заголовке карточки товара" и задать Описание
- Настраиваем UniTheme настройки:
    * ID пользовательского блока                -- 109
    * Отображать модификатор количества         -- нет
    * Отображать код товара                     -- нет
    * Отображать характеристики товара          -- да
    * Отображать характеристики в две колонки   -- нет
    * Отображать краткое описание               -- да
    * Отображать "Вы экономите"                 -- Сокращенный вид
    * Отображать информацию о бренде товара     -- Логотип бренда товара
- Настраиваем товар X-Box 360:
    * Действие при нулевой цене                 -- Не разрешать добавлять товар в корзину
    * Цена за единицу                           -- да
    * Действие при отсутствии товара в наличии  -- Не выбрано
    * шаблон страницы товара                    -- 5 шт (кроме Каскада)
    * Краткое описание                          -- да
    * Промо-текст                               -- да
    * Бонусные баллы                            -- да
    * Оптовые цены                              -- нет
*/

public class GeneralSettings_St_ProductPage_Var1 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsFor_GeneralSettings_ProductPage_Var1(){
        //Настраиваем макет для тест-кейса
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Lightv2.click();
        layoutPage.setLayoutAsDefault();

        //Настраиваем CS-Cart настройки
        CsCartSettings csCartSettings = basicPage.navigateToAppearanceSettings();
        UtilsAdm.setCheckboxState(csCartSettings.setting_ThumbnailsGallery, true);
        UtilsAdm.setCheckboxState(csCartSettings.setting_NumberOfAvailableProducts, false);
        UtilsAdm.setCheckboxState(csCartSettings.setting_ProductDetailsInTab, true);
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme настройки
        basicPage.navigateTo_ThemeSettings_tabProductLists();
        ThemeSettings_Product themeSettingsProduct = new ThemeSettings_Product();
        themeSettingsProduct.tab_Product.click();
        UtilsAdm.clickAndType(themeSettingsProduct.setting_CustomBlockID, "109");
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowQuantityChanger, false);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowProductCode, false);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowProductFeatures, true);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_FeaturesInTwoColumns, false);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowShortDescription, true);
        new Select(themeSettingsProduct.setting_ShowYouSave).selectByValue("short");
        new Select(themeSettingsProduct.setting_ShowProductBrand).selectByValue("logo");
        basicPage.clickSaveButtonOfSettings();

        //Работаем с настройками характеристик Жесткий диск и Бренд
        FeaturePage featurePage = basicPage.navigateToSection_Features();
        featurePage.feature_HardDrive.click();
        UtilsAdm.waitForPopUpWindow();
        featurePage.clickAndTypeField_DescriptionOfFeature("Для характеристики, которая просто позволяет указать какое-нибудь дополнительное свойство товара. Например, у футболок это может быть \"Ткань\". Если вы создадите фильтр по этой характеристике, покупатели увидят, что она есть, и смогут легко найти по ней нужный товар.");
        UtilsAdm.setCheckboxState(featurePage.showInHeaderOnProductPage_HardDisk, true);
        featurePage.button_SaveFeature.click();

        //Настраиваем страницу товара
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        UtilsAdm.clickAndType(productSettings.field_Price,"10000.00");
        UtilsAdm.clickAndType(productSettings.field_InStock,"20");
        new Select(productSettings.setting_ZeroPriceAction).selectByValue("R");
        productSettings.setPricePerUnit("игровые приставки", "3", "3");
        new Select(productSettings.setting_OutOfStockActions).selectByValue("N");
        productSettings.selectSetting_ProductTemplate("default_template");
        productSettings.hoverAndTypeField_ShortDescription("Здесь написано краткое описание товара!");
        productSettings.hoverAndTypeField_PromoText("Только до конца недели! Выберите диск с игрой в подарок!");
        UtilsAdm.hoverNavigateAndClick(productSettings.tab_RewardPoints);
        UtilsAdm.setCheckboxState(productSettings.setting_AllowPaymentByPoints, true);
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsFor_GeneralSettings_ProductPage_Var1")
    public void checkGeneralSettings_ProductPage_Var1() {
        BasicPage basicPage = new BasicPage();
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        UtilsAdm.clickAndType(productSettings.field_ListPrice,"15000");
        basicPage.clickSaveButtonOfSettings();
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
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что логотип характеристики "Бренд" присутствует
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.productBrandInformation_Logo,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что текст "Вы экономите" присутствует и "Сокращенный вид"
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.text_YouSave_Short,
                "on the product page 'Titan'!",
                true);

        //Проверяем, что мини-иконки в виде галереи
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.productPage,
                asserts_csCartSettings.miniThumbnailImagesAsGallery,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что информация о товаре отображается во вкладках
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.productPage,
                asserts_csCartSettings.displayProductDetailsInTabs_Enabled,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что характеристика "Бренд" присутствует в заголовке карточки товара
        asserts_csCartSettings.assertsForXpath(
                Asserts_CsCartSettings.productPage,
                asserts_csCartSettings.showInHeaderOnProductPage_Brand,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что характеристика "Жесткий диск" присутствует в заголовке карточки товара
        asserts_csCartSettings.assertsForXpath(
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

        //Проверяем, что присутствует Цена за единицу
        asserts_productPage.assertElementPresence(
                Asserts_ProductPage.productPage,
                asserts_productPage.pricePerUnit,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что присутствует Краткое описание товара
        asserts_productPage.assertElementPresence(
                Asserts_ProductPage.productPage,
                asserts_productPage.shortDescription,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что Бонусные баллы присутствуют
        asserts_productPage.assertElementPresence(
                Asserts_ProductPage.productPage,
                asserts_productPage.allowPaymentByPoints,
                "on the product page 'X-Box 360'!",
                true);

        takeScreenShot_withScroll("900 GS_ProductPage_Var1 - Default template");
        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("905 GS_ProductPage_Var1 - Default template (RTL)");

        //Проверяем, что характеристики товара расположены в одну колонку
        stProductPage.scrollToAndClickTab_Features();
        asserts_product.assertElementPresence(
                "",
                asserts_product.featuresInTwoColumns_Disabled,
                "on the product page 'X-Box 360'!",
                true);

        takeScreenShot("910 GS_ProductPage_Var1 - Product features, one column (RTL)");
        stProductPage.selectLanguage("en");
        stProductPage.scrollToAndClickTab_Features();
        takeScreenShot("915 GS_ProductPage_Var1 - Product features, one column");
        stProductPage.featureDescription.click();
        UtilsAdm.waitForTitleBarWindow();
        takeScreenShot("920 GS_ProductPage_Var1 - Feature description, one column");

        //Другие шаблоны страницы товара
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("bigpicture_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(2);
        takeScreenShot_withScroll("925 GS_ProductPage_Var1 - Big picture");
        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("930 GS_ProductPage_Var1 - Big picture (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_flat_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(3);
        takeScreenShot_withScroll("935 GS_ProductPage_Var1 - Big picture flat");
        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("940 GS_ProductPage_Var1 - Big picture flat (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_three_columns_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(4);
        takeScreenShot_withScroll("945 GS_ProductPage_Var1 - Three columned");
        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("950 GS_ProductPage_Var1 - Three columned (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_gallery_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(5);
        takeScreenShot_withScroll("955 GS_ProductPage_Var1 - Gallery template");
        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("960 GS_ProductPage_Var1 - Gallery template (RTL)");

        System.out.println("GeneralSettings_ProductPage_Var1 passed successfully!");
    }
}