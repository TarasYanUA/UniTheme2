import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ProductSettings;
import taras.adminPanel.ThemeSettings_Product;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.ProductPage;
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

public class GeneralSettings_ProductPage_Cascade_Var3 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductPage_Cascade_Var3(){
        //Настраиваем CS-Cart настройки
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToAppearanceSettings();
        if(csCartSettings.setting_ThumbnailsGallery.isSelected()){
            csCartSettings.setting_ThumbnailsGallery.click();
        }
        if(csCartSettings.setting_NumberOfAvailableProducts.isSelected()){
            csCartSettings.setting_NumberOfAvailableProducts.click();
        }
        csCartSettings.selectSetting_ProductPageView("abt__ut2_cascade_gallery_template");
        if(csCartSettings.setting_ProductDetailsInTab.isSelected()){
            csCartSettings.setting_ProductDetailsInTab.click();
        }
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем UniTheme настройки
        ThemeSettings_Product themeSettingsProduct = csCartSettings.navigateTo_ThemeSettings_tabProduct();
        themeSettingsProduct.clickAndTypeSetting_CustomBlockID("");
        if(themeSettingsProduct.setting_ShowQuantityChanger.isSelected()){
            themeSettingsProduct.setting_ShowQuantityChanger.click();
        }
        if(!themeSettingsProduct.setting_ShowProductCode.isSelected()){
            themeSettingsProduct.setting_ShowProductCode.click();
        }
        if(themeSettingsProduct.setting_ShowProductFeatures.isSelected()){
            themeSettingsProduct.setting_ShowProductFeatures.click();
        }
        if(!themeSettingsProduct.setting_FeaturesInTwoColumns.isSelected()){
            themeSettingsProduct.setting_FeaturesInTwoColumns.click();
        }
        if(themeSettingsProduct.setting_ShowShortDescription.isSelected()){
            themeSettingsProduct.setting_ShowShortDescription.click();
        }
        themeSettingsProduct.selectSetting_ShowProductBrand("none");
        themeSettingsProduct.selectSetting_CombinationsOfProductGalleryImageFormations("3");
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем страницу товара
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Titan");
        productSettings.chooseAnyProduct();
        productSettings.clickAndTypeField_Price("10000");
        productSettings.clickAndTypeField_InStock("0");
        productSettings.selectSetting_OutOfStockActions("S");
        productSettings.hoverAndTypeField_ShortDescription("");
        productSettings.hoverAndTypeField_PromoText("");
        Actions actions = new Actions(DriverProvider.getDriver());
        actions.moveToElement(productSettings.tab_RewardPoints).build().perform();
        productSettings.tab_RewardPoints.click();
        if(!productSettings.setting_AllowPaymentByPoints.isSelected()){
            productSettings.setting_AllowPaymentByPoints.click();
        }
        productSettings.tab_QuantityDiscounts.click();
        if(DriverProvider.getDriver().findElements(By.cssSelector("#content_qty_discounts  .cm-row-item")).size() < 2){
            productSettings.clickAndType_field_Quantity("3");
            productSettings.clickAndType_field_Value("22000");
        }
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductPage_Cascade_Var3")
    public void checkSettingsOnProductPage_Cascade_Var3() {
        CsCartSettings csCartSettings = new CsCartSettings();
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Titan");
        productSettings.chooseAnyProduct();
        ProductPage productPage = productSettings.navigateToProductPage();
        focusBrowserTab(1);
        productPage.cookie.click();
        productPage.shiftLanguage_EN();
        StHomePage stHomePage = new StHomePage();
        stHomePage.logOutOnStorefront();
        productPage.checkbox_NotifyMe.click();

        SoftAssert softAssert = new SoftAssert();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что мини-иконки в виде галереи отсутствуют в шаблоне "Каскадная галерея"
        softAssert.assertFalse(!assertsOnStorefront.miniThumbnailImages_Disabled.isEmpty(),
                "There is a mini-icons gallery but shouldn't!");

        //Проверяем, что информация о товаре отображается не во вкладках
        softAssert.assertTrue(!assertsOnStorefront.displayProductDetailsInTabs_Disabled.isEmpty(),
                "Product information is displayed in tabs but shouldn't!");

        //Проверяем, что название и логотип "Бренд" отсутствует
        softAssert.assertFalse(!assertsOnStorefront.showProductBrandInformation_Name.isEmpty()
                && !assertsOnStorefront.showProductBrandInformation_Logo.isEmpty(),
                "There is a Brand name or Brand logo but shouldn't!");

        //Проверяем, что характеристика "Бренд" отсутствует в заголовке карточки товара
        softAssert.assertFalse(!assertsOnStorefront.showInHeaderOnProductPage_Brand.isEmpty(),
                "There is a feature Brand on the feature list but shouldn't!");

        //Проверяем, что Код товара присутствует
        softAssert.assertTrue(!assertsOnStorefront.showProductCode.isEmpty(),
                "There is no product code!");

        //Проверяем, что Действие при отсутствии товара в наличии - Подписаться на уведомления
        softAssert.assertTrue(!assertsOnStorefront.outOfStockActions_SignUpForNotification.isEmpty(),
                "There is no field 'Sign up for notification'!");

        //Проверяем, что Бонусные баллы присутствуют
        softAssert.assertTrue(!assertsOnStorefront.product_allowPaymentByPoints.isEmpty(),
                "There is no Reward points!");

        takeScreenShot_withScroll("Cascade3.10 GS_ProductPage_Cascade_Var3 - Cascade template");
        productPage.checkbox_NotifyMe.click();
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,550);");
        takeScreenShot("Cascade3.15 GS_ProductPage_Cascade_Var3 - Checkbox 'Notify me'");
        productPage.scrollToAndClickTab_FeaturesForNonTabs();

        //Проверяем, что характеристики расположены в две колонки
        softAssert.assertTrue(!assertsOnStorefront.showFeaturesInTwoColumns_Enabled.isEmpty(),
                "Features are located in one column instead of two!");
        takeScreenShot("Cascade3.20 GS_ProductPage_Cascade_Var3 - Product features, two columns");

        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("Cascade3.25 GS_ProductPage_Var3 - Cascade template (RTL)");
        productPage.checkbox_NotifyMe.click();
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,550);");
        takeScreenShot_withScroll("Cascade3.30 GS_ProductPage_Cascade_Var3 - Checkbox 'Notify me' (RTL)");
        productPage.scrollToAndClickTab_FeaturesForNonTabs();
        takeScreenShot("Cascade3.35 GS_ProductPage_Cascade_Var3 - Product features, two columns (RTL)");

        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductPage_Cascade_Var3 passed successfully!");
    }
}