package productPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.asserts.Asserts_CsCartSettings;
import taras.asserts.Asserts_ProductPage;
import taras.asserts.Asserts_ThemeSettings_Product;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.StProductPage;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

/*
ссылка на чеклист: https://docs.google.com/spreadsheets/d/19qsT6Hm83Kdt1Fh1WMS96sBfyp3wouEMEv17FyEglh0/edit#gid=0
- Настройки CS-Cart "Настройки -> Внешний вид":
    * Показывать мини-иконки в виде галереи     -- откл
    * Показывать количество доступных товаров   -- откл (по причине нулевого наличия)
    * Показывать информацию о товаре во вкладках-- откл
- Настраиваем UniTheme настройки:
    * ID пользовательского блока                -- нет
    * Отображать модификатор количества         -- нет (по причине нулевого наличия)
    * Отображать код товара                     -- да
    * Отображать характеристики товара          -- нет
    * Отображать характеристики в две колонки   -- да
    * Отображать краткое описание               -- нет
    * Отображать "Вы экономите"                 -- Не отображать
    * Отображать информацию о бренде товара     -- Не отображать
    * Количество отображаемых изображений галереи товара (для всех шаблонов страницы товара) -- 2
- Настраиваем товар X-Box 360:
    * Действие при отсутствии товара в наличии  -- Подписаться на уведомления
    * шаблон страницы товара                    -- 5 шт (кроме Каскада)
    * Краткое описание                          -- нет
    * Промо-текст                               -- нет
    * Бонусные баллы                            -- да
    * Оптовые цены                              -- да
*/

public class GeneralSettings_St_ProductPage_Var3 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductPage_Var3() {
        //Настраиваем CS-Cart настройки
        BasicPage basicPage = new BasicPage();
        CsCartSettings csCartSettings = basicPage.navigateToAppearanceSettings();
        taras.adminPanel.UtilsAdm.setCheckboxState(csCartSettings.setting_ThumbnailsGallery, false);
        taras.adminPanel.UtilsAdm.setCheckboxState(csCartSettings.setting_NumberOfAvailableProducts, false);
        taras.adminPanel.UtilsAdm.setCheckboxState(csCartSettings.setting_ProductDetailsInTab, false);
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme настройки
        ThemeSettings_Product themeSettingsProduct = basicPage.navigateTo_ThemeSettings_tabProduct();
        taras.adminPanel.UtilsAdm.clickAndType(themeSettingsProduct.setting_CustomBlockID, "");
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowQuantityChanger, false);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowProductCode, true);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowProductFeatures, false);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_FeaturesInTwoColumns, true);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowShortDescription, false);
        new Select(themeSettingsProduct.setting_ShowYouSave).selectByValue("none");
        new Select(themeSettingsProduct.setting_ShowProductBrand).selectByValue("none");
        new Select(themeSettingsProduct.setting_NumberOfDisplayedImages_DefaultTemplate).selectByValue("2");
        new Select(themeSettingsProduct.setting_NumberOfDisplayedImages_BigPictureTemplate).selectByValue("2");
        new Select(themeSettingsProduct.setting_NumberOfDisplayedImages_BigPictureFlatTemplate).selectByValue("2");
        new Select(themeSettingsProduct.setting_NumberOfDisplayedImages_GalleryTemplate).selectByValue("2");
        new Select(themeSettingsProduct.setting_NumberOfDisplayedImages_ThreeColumnsTemplate).selectByValue("2");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем страницу товара
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        taras.adminPanel.UtilsAdm.clickAndType(productSettings.field_Price, "10000.00");
        taras.adminPanel.UtilsAdm.clickAndType(productSettings.field_InStock, "0");
        new Select(productSettings.setting_OutOfStockActions).selectByValue("S");
        productSettings.selectSetting_ProductTemplate("default_template");
        productSettings.hoverAndTypeField_ShortDescription("");
        productSettings.hoverAndTypeField_PromoText("");
        UtilsAdm.hoverNavigateAndClick(productSettings.tab_RewardPoints);
        UtilsAdm.setCheckboxState(productSettings.setting_AllowPaymentByPoints, true);
        productSettings.tab_QuantityDiscounts.click();
        if (DriverProvider.getDriver().findElements(By.cssSelector("#content_qty_discounts  .cm-row-item")).size() < 2) {
            taras.adminPanel.UtilsAdm.clickAndType(productSettings.field_Quantity, "3");
            taras.adminPanel.UtilsAdm.clickAndType(productSettings.field_Value, "22000");
        }
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductPage_Var3")
    public void checkSettingsOnProductPage_Var3() {
        BasicPage basicPage = new BasicPage();
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        taras.adminPanel.UtilsAdm.clickAndType(productSettings.field_ListPrice, "15000");
        basicPage.clickSaveButtonOfSettings();
        StProductPage stProductPage = productSettings.navigateToProductPage();
        focusBrowserTab(1);
        stProductPage.cookie.click();
        stProductPage.selectLanguage("en");
        UtilsAdm.closeNotificationOnStorefront();
        StHomePage stHomePage = new StHomePage();
        stHomePage.logOutOnStorefront();

        SoftAssert softAssert = new SoftAssert();
        Asserts_ThemeSettings_Product asserts_product = new Asserts_ThemeSettings_Product();
        Asserts_CsCartSettings asserts_csCartSettings = new Asserts_CsCartSettings();
        Asserts_ProductPage asserts_productPage = new Asserts_ProductPage();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что код товара присутствует
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.productCode,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что логотип и название "Бренд" отсутствуют
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.productBrandInformation_Name,
                "on the product page 'X-Box 360'!",
                false);
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.productBrandInformation_Logo,
                "on the product page 'X-Box 360'!",
                false);

        //Проверяем, что текст "Вы экономите" отсутствует
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.text_YouSave_Full,
                "on the product page 'X-Box 360'!",
                false);
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.text_YouSave_Short,
                "on the product page 'X-Box 360'!",
                false);

        //Проверяем, что Количество отображаемых изображений галереи товара - 2
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.numberOfDisplayedImagesOfProductGallery_2,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что мини-иконки не в виде галереи
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.productPage,
                asserts_csCartSettings.miniThumbnailImagesAsGallery_Disabled,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что информация о товаре отображается не во вкладках
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.productPage,
                asserts_csCartSettings.displayProductDetailsInTabs_Disabled,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что характеристика "Бренд" отсутствует в заголовке карточки товара
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.productPage,
                asserts_csCartSettings.showInHeaderOnProductPage_Brand,
                "on the product page 'X-Box 360'!",
                false);

        //Проверяем, что Действие при отсутствии товара в наличии - Подписаться на уведомления
        asserts_productPage.assertElementPresence(
                Asserts_ProductPage.productPage,
                asserts_productPage.outOfStockActions_SignUpForNotification,
                "on the product page 'X-Box 360'!",
                true);

        //Проверяем, что Бонусные баллы присутствуют
        asserts_productPage.assertElementPresence(
                Asserts_ProductPage.productPage,
                asserts_productPage.allowPaymentByPoints,
                "on the product page 'X-Box 360'!",
                true);

        takeScreenShot_withScroll("1100 GS_ProductPage_Var3 - Default template");
        stProductPage.selectLanguage("ar");
        UtilsAdm.closeNotificationOnStorefront();
        takeScreenShot_withScroll("1105 GS_ProductPage_Var3 - Default template (RTL)");

        //Проверяем, что характеристики товара расположены в две колонки
        stProductPage.scrollToAndClickTab_FeaturesForNonTabs();
        asserts_product.assertElementPresence(
                "",
                asserts_product.featuresInTwoColumns_Enabled,
                "on the product page 'Titan'!",
                true);

        takeScreenShot("1110 GS_ProductPage_Var3 - Product features, two columns (RTL)");
        stProductPage.selectLanguage("en");
        UtilsAdm.closeNotificationOnStorefront();
        stProductPage.scrollToAndClickTab_FeaturesForNonTabs();
        takeScreenShot("1115 GS_ProductPage_Var3 - Product features, two columns");
        if (!DriverProvider.getDriver().findElements(By.cssSelector("#content_features .ab-smc")).isEmpty())
            DriverProvider.getDriver().findElement(By.cssSelector("#content_features .ab-smc")).click();
        stProductPage.featureDescription.click();
        UtilsAdm.waitForTitleBarWindow();
        takeScreenShot("1120 GS_ProductPage_Var3 - Feature description, two columns");

        //Другие шаблоны страницы товара
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("bigpicture_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(2);
        takeScreenShot_withScroll("1125 GS_ProductPage_Var3 - Big picture");
        stProductPage.selectLanguage("ar");
        UtilsAdm.closeNotificationOnStorefront();
        takeScreenShot_withScroll("1130 GS_ProductPage_Var3 - Big picture (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_flat_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(3);
        takeScreenShot_withScroll("1135 GS_ProductPage_Var3 - Big picture flat");
        stProductPage.selectLanguage("ar");
        UtilsAdm.closeNotificationOnStorefront();
        takeScreenShot_withScroll("1140 GS_ProductPage_Var3 - Big picture flat (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_three_columns_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(4);
        takeScreenShot_withScroll("1145 GS_ProductPage_Var3 - Three columned");
        stProductPage.selectLanguage("ar");
        UtilsAdm.closeNotificationOnStorefront();
        takeScreenShot_withScroll("1150 GS_ProductPage_Var3 - Three columned (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_gallery_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(5);
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,550);");
        takeScreenShot("1155 GS_ProductPage_Var3 - Gallery template");
        stProductPage.selectLanguage("ar");
        UtilsAdm.closeNotificationOnStorefront();
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,550);");
        takeScreenShot("1160 GS_ProductPage_Var3 - Gallery template (RTL)");

        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductPage_Var3 passed successfully!");
    }
}