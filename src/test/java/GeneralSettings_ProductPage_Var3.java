import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.ProductPage;
import taras.storefront.StHomePage;
import taras.storefront.UtilsStorefront;
import testRunner.TestRunner;

import java.time.Duration;

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

public class GeneralSettings_ProductPage_Var3 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductPage_Var3() {
        //Настраиваем CS-Cart настройки
        BasicPage basicPage = new BasicPage();
        CsCartSettings csCartSettings = basicPage.navigateToAppearanceSettings();
        UtilsAdm.setCheckboxState(csCartSettings.setting_ThumbnailsGallery, false);
        UtilsAdm.setCheckboxState(csCartSettings.setting_NumberOfAvailableProducts, false);
        UtilsAdm.setCheckboxState(csCartSettings.setting_ProductDetailsInTab, false);
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme настройки
        ThemeSettings_Product themeSettingsProduct = basicPage.navigateTo_ThemeSettings_tabProduct();
        themeSettingsProduct.clickAndTypeSetting_CustomBlockID("");
        if (themeSettingsProduct.setting_ShowQuantityChanger.isSelected())
            themeSettingsProduct.setting_ShowQuantityChanger.click();
        if (!themeSettingsProduct.setting_ShowProductCode.isSelected())
            themeSettingsProduct.setting_ShowProductCode.click();
        if (themeSettingsProduct.setting_ShowProductFeatures.isSelected())
            themeSettingsProduct.setting_ShowProductFeatures.click();
        if (!themeSettingsProduct.setting_FeaturesInTwoColumns.isSelected())
            themeSettingsProduct.setting_FeaturesInTwoColumns.click();
        if (themeSettingsProduct.setting_ShowShortDescription.isSelected())
            themeSettingsProduct.setting_ShowShortDescription.click();
        themeSettingsProduct.selectSetting_ShowYouSave("none");
        themeSettingsProduct.selectSetting_ShowProductBrand("none");
        themeSettingsProduct.selectSetting_NumberOfDisplayedImages_DefaultTemplate("2");
        themeSettingsProduct.selectSetting_NumberOfDisplayedImages_BigPictureTemplate("2");
        themeSettingsProduct.selectSetting_NumberOfDisplayedImages_BigPictureFlatTemplate("2");
        themeSettingsProduct.selectSetting_NumberOfDisplayedImages_GalleryTemplate("2");
        themeSettingsProduct.selectSetting_NumberOfDisplayedImages_ThreeColumnsTemplate("2");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем страницу товара
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        productSettings.chooseAnyProduct();
        UtilsAdm.clickAndType(productSettings.field_Price, "10000.00");
        UtilsAdm.clickAndType(productSettings.field_InStock, "0");
        new Select(productSettings.setting_OutOfStockActions).selectByValue("S");
        productSettings.selectSetting_ProductTemplate("default_template");
        productSettings.hoverAndTypeField_ShortDescription("");
        productSettings.hoverAndTypeField_PromoText("");
        Actions actions = new Actions(DriverProvider.getDriver());
        actions.moveToElement(productSettings.tab_RewardPoints).build().perform();
        productSettings.tab_RewardPoints.click();
        if (!productSettings.setting_AllowPaymentByPoints.isSelected())
            productSettings.setting_AllowPaymentByPoints.click();
        productSettings.tab_QuantityDiscounts.click();
        if (DriverProvider.getDriver().findElements(By.cssSelector("#content_qty_discounts  .cm-row-item")).size() < 2) {
            UtilsAdm.clickAndType(productSettings.field_Quantity, "3");
            UtilsAdm.clickAndType(productSettings.field_Value, "22000");
        }
        basicPage.clickSaveButtonOfSettings();
    }

    //(priority = 2, dependsOnMethods = "setConfigurationsForProductPage_Var3")
    @Test
    public void checkSettingsOnProductPage_Var3() {
        BasicPage basicPage = new BasicPage();
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        productSettings.chooseAnyProduct();
        UtilsAdm.clickAndType(productSettings.field_ListPrice, "15000");
        basicPage.clickSaveButtonOfSettings();
        ProductPage productPage = productSettings.navigateToProductPage();
        focusBrowserTab(1);
        productPage.cookie.click();
        productPage.shiftLanguage_EN();
        UtilsStorefront.closeNotificationIfExists();
        StHomePage stHomePage = new StHomePage();
        stHomePage.logOutOnStorefront();

        SoftAssert softAssert = new SoftAssert();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что мини-иконки не в виде галереи
        softAssert.assertTrue(!assertsOnStorefront.miniThumbnailImagesAsGallery_Disabled.isEmpty(),
                "Mini-icons are as a gallery but shouldn't!");

        //Проверяем, что информация о товаре отображается не во вкладках
        softAssert.assertTrue(!assertsOnStorefront.displayProductDetailsInTabs_Disabled.isEmpty(),
                "Product information is displayed in tabs but shouldn't!");

        //Проверяем, что логотип и название "Бренд" отсутствуют
        softAssert.assertFalse(!assertsOnStorefront.showProductBrandInformation_Name.isEmpty()
                        && assertsOnStorefront.showProductBrandInformation_Logo.isEmpty(),
                "There is a Brand logo or Brand name but shouldn't!");

        //Проверяем, что характеристика "Бренд" отсутствует в заголовке карточки товара
        softAssert.assertFalse(!assertsOnStorefront.showInHeaderOnProductPage_Brand.isEmpty(),
                "There is a feature Brand on the feature list but shouldn't!");

        //Проверяем, что Код товара присутствует
        softAssert.assertTrue(!assertsOnStorefront.showProductCode.isEmpty(),
                "There is no product code!");

        //Проверяем, что текст "Вы экономите" отсутствует
        softAssert.assertFalse(!assertsOnStorefront.text_YouSave_Full().isEmpty()
                        && !assertsOnStorefront.text_YouSave_Short().isEmpty(),
                "There is a text 'You save' but shouldn't on the product page!");

        //Проверяем, что Действие при отсутствии товара в наличии - Подписаться на уведомления
        softAssert.assertTrue(!assertsOnStorefront.outOfStockActions_SignUpForNotification.isEmpty(),
                "There is no field 'Sign up for notification'!");

        //Проверяем, что Бонусные баллы присутствуют
        softAssert.assertTrue(!assertsOnStorefront.product_allowPaymentByPoints.isEmpty(),
                "There is no Reward points!");

        //Проверяем, что Количество отображаемых изображений галереи товара - 2
        softAssert.assertTrue(!assertsOnStorefront.numberOfDisplayedImagesOfProductGallery_2.isEmpty(),
                "Number of displayed images of the product gallery is not 2!");

        takeScreenShot_withScroll("1100 GS_ProductPage_Var3 - Default template");
        productPage.shiftLanguage_RTL();
        UtilsStorefront.closeNotificationIfExists();
        takeScreenShot_withScroll("1105 GS_ProductPage_Var3 - Default template (RTL)");

        //Проверяем, что характеристики расположены в две колонки
        productPage.scrollToAndClickTab_FeaturesForNonTabs();
        softAssert.assertTrue(!assertsOnStorefront.showFeaturesInTwoColumns_Enabled.isEmpty(),
                "Features are located in one column instead of two!");

        takeScreenShot("1110 GS_ProductPage_Var3 - Product features, two columns (RTL)");
        productPage.shiftLanguage_EN();
        UtilsStorefront.closeNotificationIfExists();
        productPage.scrollToAndClickTab_FeaturesForNonTabs();
        takeScreenShot("1115 GS_ProductPage_Var3 - Product features, two columns");
        if (!DriverProvider.getDriver().findElements(By.cssSelector("#content_features .ab-smc")).isEmpty())
            DriverProvider.getDriver().findElement(By.cssSelector("#content_features .ab-smc")).click();
        productPage.featureDescription.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-titlebar")));
        takeScreenShot("1120 GS_ProductPage_Var3 - Feature description, two columns");

        //Другие шаблоны страницы товара
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("bigpicture_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(2);
        takeScreenShot_withScroll("1125 GS_ProductPage_Var3 - Big picture");
        productPage.shiftLanguage_RTL();
        UtilsStorefront.closeNotificationIfExists();
        takeScreenShot_withScroll("1130 GS_ProductPage_Var3 - Big picture (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_flat_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(3);
        takeScreenShot_withScroll("1135 GS_ProductPage_Var3 - Big picture flat");
        productPage.shiftLanguage_RTL();
        UtilsStorefront.closeNotificationIfExists();
        takeScreenShot_withScroll("1140 GS_ProductPage_Var3 - Big picture flat (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_three_columns_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(4);
        takeScreenShot_withScroll("1145 GS_ProductPage_Var3 - Three columned");
        productPage.shiftLanguage_RTL();
        UtilsStorefront.closeNotificationIfExists();
        takeScreenShot_withScroll("1150 GS_ProductPage_Var3 - Three columned (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_gallery_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(5);
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,550);");
        takeScreenShot("1155 GS_ProductPage_Var3 - Gallery template");
        productPage.shiftLanguage_RTL();
        UtilsStorefront.closeNotificationIfExists();
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,550);");
        takeScreenShot("1160 GS_ProductPage_Var3 - Gallery template (RTL)");

        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductPage_Var3 passed successfully!");
    }
}