package productPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.asserts.Asserts_ThemeSettings_Product;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.StProductPage;
import testRunner.TestRunner;

/*
ссылка на чеклист: https://docs.google.com/spreadsheets/d/1YPAkjqk12kPh7LBDU1tq7qdwLmCo-Rly00TdfW8h-Wo/edit#gid=2110746700
- CS-Cart "Настройки -> Внешний вид":
    * Показывать мини-иконки в виде галереи --  нет
- UniTheme2 -- Настройки темы -- вкладка "Товар":
    * ID пользовательского блока --  нет
    * Отображать модификатор количества --  да
    * Отображать код товара --  да
    * Отображать краткое описание --    да
    * Отображать информацию о бренде товара --  Название бренда товара
    * Количество отображаемых изображений галереи товара (для всех шаблонов страницы товара) -- 2
- UniTheme2 -- Настройки цветосхемы -- вкладка "Товар":
    * Добавить фон/маску для изображений товара --  да
    * Добавить обрамление для изображений товара -- да
- Товар Samsung NX200:
    * Цена за единицу --  33000
    * Наличие --    10
    * Промо-текст -- да
    * Бонусные баллы --  да
    * Оптовые цены -- да
    * Краткое описание --   да
    * шаблон страницы товара -- 5 шт (кроме Каскадной галереи)
*/

public class ColorSchemeSettings_Product_Var1 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurations_ColorSchemeSettings_Product_Var1() {
        //Настраиваем CS-Cart настройки
        BasicPage basicPage = new BasicPage();
        CsCartSettings csCartSettings = basicPage.navigateToAppearanceSettings();
        if (csCartSettings.setting_ThumbnailsGallery.isSelected()) {
            csCartSettings.setting_ThumbnailsGallery.click();
            basicPage.clickSaveButtonOfSettings();
        }

        //Настраиваем UniTheme настройки, вкладка "Товар"
        ThemeSettings_Product themeSettingsProduct = basicPage.navigateTo_ThemeSettings_tabProduct();
        UtilsAdm.clickAndType(themeSettingsProduct.setting_CustomBlockID, "");
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowQuantityChanger, true);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowProductCode, true);
        UtilsAdm.setCheckboxState(themeSettingsProduct.setting_ShowShortDescription, true);
        new Select(themeSettingsProduct.setting_ShowProductBrand).selectByValue("name");
        new Select(themeSettingsProduct.setting_NumberOfDisplayedImages_DefaultTemplate).selectByValue("2");
        new Select(themeSettingsProduct.setting_NumberOfDisplayedImages_BigPictureTemplate).selectByValue("2");
        new Select(themeSettingsProduct.setting_NumberOfDisplayedImages_BigPictureFlatTemplate).selectByValue("2");
        new Select(themeSettingsProduct.setting_NumberOfDisplayedImages_GalleryTemplate).selectByValue("2");
        new Select(themeSettingsProduct.setting_NumberOfDisplayedImages_ThreeColumnsTemplate).selectByValue("2");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme настройки, вкладка "Списки товаров"
        ThemeSettings_ProductLists themeSettingsProductLists = new ThemeSettings_ProductLists();
        themeSettingsProductLists.tabProductLists.click();
        if (!themeSettingsProductLists.setting_AllowToSelectVariationsAndOptions.isSelected()) {
            themeSettingsProductLists.setting_AllowToSelectVariationsAndOptions.click();
            basicPage.clickSaveButtonOfSettings();
        }

        //Настраиваем UniTheme цветосхему, вкладка "Товар"
        ColorSchemeSettings colorSchemeSettings = basicPage.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.selectActiveColorScheme();
        colorSchemeSettings.tab_Product.click();
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_ProductMaskForProductImages, true);
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_ProductBorderForProductImages, true);
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем страницу товара
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("NX200");
        UtilsAdm.clickAndType(productSettings.field_Price, "33000.00");
        UtilsAdm.clickAndType(productSettings.field_InStock, "20");
        productSettings.selectSetting_ProductTemplate("default_template");
        productSettings.hoverAndTypeField_ShortDescription("Здесь написано краткое описание товара!");
        productSettings.hoverAndTypeField_PromoText("Только до конца недели! Выберите диск с игрой в подарок!");
        UtilsAdm.hoverNavigateAndClick(productSettings.tab_RewardPoints);
        UtilsAdm.setCheckboxState(productSettings.setting_AllowPaymentByPoints, true);
        productSettings.tab_QuantityDiscounts.click();
        if (DriverProvider.getDriver().findElements(By.cssSelector("#content_qty_discounts  .cm-row-item")).size() < 2) {
            UtilsAdm.clickAndType(productSettings.field_Quantity, "3");
            UtilsAdm.clickAndType(productSettings.field_Value, "70200");
        }
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_ColorSchemeSettings_Product_Var1")
    public void checkColorSchemeSettings_Product_Var1() {
        BasicPage basicPage = new BasicPage();
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("NX200");
        StProductPage stProductPage = productSettings.navigateToProductPage();
        focusBrowserTab(1);
        stProductPage.cookie.click();
        stProductPage.selectLanguage("en");

        SoftAssert softAssert = new SoftAssert();
        Asserts_ThemeSettings_Product asserts_product = new Asserts_ThemeSettings_Product();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что название характеристики "Бренд" присутствует
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.productBrandInformation_Name,
                "on the product page 'NX200'!",
                true);

        //Проверяем, что Количество отображаемых изображений галереи товара - 2
        asserts_product.assertElementPresence(
                Asserts_ThemeSettings_Product.productPage,
                asserts_product.numberOfDisplayedImagesOfProductGallery_2,
                "on the product page 'NX200'!",
                true);

        //Проверяем, что мини-иконки не в виде галереи
        softAssert.assertTrue(!assertsOnStorefront.miniThumbnailImagesAsGallery_Disabled.isEmpty(),
                "Mini-icons are as a gallery but shouldn't on the product page!");

        //Проверяем, что присутствует Краткое описание товара
        softAssert.assertTrue(!assertsOnStorefront.product_ShortDescription.isEmpty(),
                "There is no product Short description on the product page!");

        //Проверяем, что Промо-текст присутствует
        softAssert.assertTrue(!assertsOnStorefront.product_PromoText.isEmpty(),
                "There is no Promo-text on the product page!");

        //Проверяем, что Бонусные баллы присутствуют
        softAssert.assertTrue(!assertsOnStorefront.product_allowPaymentByPoints.isEmpty(),
                "There is no Reward points on the product page!");

        takeScreenShot_withScroll("1200 productPage.ColorSchemeSettings_Product_Var1 - Default template");
        UtilsAdm.scrollToElementAndScrollBelow(stProductPage.blockWithProducts_MostPopular, 100);
        UtilsAdm.makePause(2000);
        stProductPage.buttonAddToCart_ProductWithOptions.click();
        UtilsAdm.waitForPopUpWindow();

        //Проверяем, что модификатор количества присутствует во всплывающем окне покупки товара с опциями
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ut2_select_variation__buttons .ty-value-changer")).isEmpty(),
                "There is no quantity changer in pop-up window of the product with options on the product page!");

        takeScreenShot("1202 productPage.ColorSchemeSettings_Product_Var1 - Pop-up window of product with options");
        stProductPage.closePopUpWindow.click();
        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("1205 productPage.ColorSchemeSettings_Product_Var1 - Default template (RTL)");
        UtilsAdm.scrollToElementAndScrollBelow(stProductPage.blockWithProducts_MostPopular, 100);
        UtilsAdm.makePause(2000);
        stProductPage.buttonAddToCart_ProductWithOptions.click();
        UtilsAdm.waitForPopUpWindow();
        takeScreenShot("1207 productPage.ColorSchemeSettings_Product_Var1 - Pop-up window of product with options (RTL)");
        stProductPage.closePopUpWindow.click();

        //Другие шаблоны страницы товара
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("bigpicture_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(2);
        takeScreenShot_withScroll("1210 productPage.ColorSchemeSettings_Product_Var1 - Big picture");
        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("1215 productPage.ColorSchemeSettings_Product_Var1 - Big picture (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_flat_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(3);
        takeScreenShot_withScroll("1220 productPage.ColorSchemeSettings_Product_Var1 - Big picture flat");
        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("1225 productPage.ColorSchemeSettings_Product_Var1 - Big picture flat (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_three_columns_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(4);
        takeScreenShot_withScroll("1230 productPage.ColorSchemeSettings_Product_Var1 - Three columned");
        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("1235 productPage.ColorSchemeSettings_Product_Var1 - Three columned (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_gallery_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(5);
        takeScreenShot_withScroll("1240 productPage.ColorSchemeSettings_Product_Var1 - Gallery template");
        stProductPage.selectLanguage("ar");
        takeScreenShot_withScroll("1245 productPage.ColorSchemeSettings_Product_Var1 - Gallery template (RTL)");
        softAssert.assertAll();
        System.out.println("productPage.ColorSchemeSettings_Product_Var1 passed successfully on the product page!");
    }
}