package productPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.*;
import taras.asserts.Asserts_ThemeSettings_Product;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.StProductPage;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

/*
- Настройки CS-Cart "Настройки -> Внешний вид":
    * Показывать мини-иконки в виде галереи --  откл
    * Показывать количество доступных товаров -- откл (по причине нулевого наличия)
    * Показывать информацию о товаре во вкладках -- откл
- Настраиваем UniTheme настройки:
    * ID пользовательского блока --  нет
    * Отображать модификатор количества --  нет (по причине нулевого наличия)
    * Отображать код товара --  да
    * Отображать характеристики товара -- нет
    * Отображать характеристики в две колонки --    да
    * Отображать краткое описание --    нет
    * Отображать информацию о бренде товара --  Не отображать
    * Комбинации формаций изображений галереи товара -- 3
- Настраиваем товар Phone Titan:
    * Действие при нулевой цене --  Не отображать
    * Цена за единицу --  да
    * Действие при отсутствии товара в наличии --   Подписаться на уведомления
    * шаблон страницы товара -- только Каскад
    * Краткое описание --   нет
    * Промо-текст -- нет
    * Бонусные баллы --  да
    * Оптовые цены -- да
*/

public class GeneralSettings_St_ProductPage_Cascade_Var3 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductPage_Cascade_Var3() {
        //Настраиваем CS-Cart настройки
        BasicPage basicPage = new BasicPage();
        CsCartSettings csCartSettings = basicPage.navigateToAppearanceSettings();
        UtilsAdm.setCheckboxState(csCartSettings.setting_ThumbnailsGallery, false);
        UtilsAdm.setCheckboxState(csCartSettings.setting_NumberOfAvailableProducts, false);
        new Select(csCartSettings.setting_ProductPageView).selectByValue("abt__ut2_cascade_gallery_template");
        UtilsAdm.setCheckboxState(csCartSettings.setting_ProductDetailsInTab, false);
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme настройки
        ThemeSettings_Product themeSettingsProduct = basicPage.navigateTo_ThemeSettings_tabProduct();
        UtilsAdm.clickAndType(themeSettingsProduct.setting_CustomBlockID, "");
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowQuantityChanger, false);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowProductCode, true);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowProductFeatures, false);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_FeaturesInTwoColumns, true);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowShortDescription, false);
        new Select(themeSettingsProduct.setting_ShowProductBrand).selectByValue("none");
        new Select(themeSettingsProduct.setting_CombinationsOfProductGalleryImageFormations).selectByValue("3");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем страницу товара
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Titan");
        UtilsAdm.clickAndType(productSettings.field_Price, "10000.00");
        UtilsAdm.clickAndType(productSettings.field_InStock, "0");
        new Select(productSettings.setting_OutOfStockActions).selectByValue("S");
        productSettings.hoverAndTypeField_ShortDescription("");
        productSettings.hoverAndTypeField_PromoText("");
        UtilsAdm.hoverNavigateAndClick(productSettings.tab_RewardPoints);
        UtilsAdm.setCheckboxState(productSettings.setting_AllowPaymentByPoints, true);
        productSettings.tab_QuantityDiscounts.click();
        if (DriverProvider.getDriver().findElements(By.cssSelector("#content_qty_discounts  .cm-row-item")).size() < 2) {
            UtilsAdm.clickAndType(productSettings.field_Quantity, "3");
            UtilsAdm.clickAndType(productSettings.field_Value, "22000");
        }
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductPage_Cascade_Var3")
    public void checkSettingsOnProductPage_Cascade_Var3() {
        BasicPage basicPage = new BasicPage();
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Titan");
        StProductPage stProductPage = productSettings.navigateToProductPage();
        focusBrowserTab(1);
        stProductPage.cookie.click();
        stProductPage.selectLanguage("en");
        StHomePage stHomePage = new StHomePage();
        stHomePage.logOutOnStorefront();
        stProductPage.checkbox_NotifyMe.click();

        SoftAssert softAssert = new SoftAssert();
        Asserts_ThemeSettings_Product asserts_product = new Asserts_ThemeSettings_Product();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что код товара присутствует
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.productCode,
                "on the product page 'Titan'!",
                true);

        //Проверяем, что логотип и название "Бренд" отсутствуют
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.productBrandInformation_Name,
                "on the product page 'Titan'!",
                false);
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.productBrandInformation_Logo,
                "on the product page 'Titan'!",
                false);

        //Проверяем, что мини-иконки в виде галереи отсутствуют в шаблоне "Каскадная галерея"
        softAssert.assertFalse(!assertsOnStorefront.miniThumbnailImagesAsGallery_Disabled.isEmpty(),
                "There is a mini-icons gallery but shouldn't!");

        //Проверяем, что информация о товаре отображается не во вкладках
        softAssert.assertTrue(!assertsOnStorefront.displayProductDetailsInTabs_Disabled.isEmpty(),
                "Product information is displayed in tabs but shouldn't!");

        //Проверяем, что характеристика "Бренд" отсутствует в заголовке карточки товара
        softAssert.assertFalse(!assertsOnStorefront.showInHeaderOnProductPage_Brand.isEmpty(),
                "There is a feature Brand on the feature list but shouldn't!");

        //Проверяем, что Действие при отсутствии товара в наличии - Подписаться на уведомления
        softAssert.assertTrue(!assertsOnStorefront.outOfStockActions_SignUpForNotification.isEmpty(),
                "There is no field 'Sign up for notification'!");

        //Проверяем, что Бонусные баллы присутствуют
        softAssert.assertTrue(!assertsOnStorefront.product_allowPaymentByPoints.isEmpty(),
                "There is no Reward points!");

        takeScreenShot_withScroll("Cascade3.10 GS_ProductPage_Cascade_Var3 - Cascade template");
        stProductPage.checkbox_NotifyMe.click();
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,550);");
        takeScreenShot("Cascade3.15 GS_ProductPage_Cascade_Var3 - Checkbox 'Notify me'");
        stProductPage.scrollToAndClickTab_FeaturesForNonTabs();

        //Проверяем, что характеристики товара расположены в две колонки
        stProductPage.scrollToAndClickTab_Features();
        asserts_product.assertElementPresence(
                "",
                asserts_product.featuresInTwoColumns_Enabled,
                "on the product page 'Titan'!",
                true);

        takeScreenShot("Cascade3.20 GS_ProductPage_Cascade_Var3 - Product features, two columns");

        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("Cascade3.25 GS_ProductPage_Var3 - Cascade template (RTL)");
        stProductPage.checkbox_NotifyMe.click();
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,550);");
        takeScreenShot_withScroll("Cascade3.30 GS_ProductPage_Cascade_Var3 - Checkbox 'Notify me' (RTL)");
        stProductPage.scrollToAndClickTab_FeaturesForNonTabs();
        takeScreenShot("Cascade3.35 GS_ProductPage_Cascade_Var3 - Product features, two columns (RTL)");

        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductPage_Cascade_Var3 passed successfully!");
    }
}