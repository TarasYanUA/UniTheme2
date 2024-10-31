import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ProductSettings;
import taras.adminPanel.ThemeSettings_Product;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.ProductPage;
import testRunner.TestRunner;

/*
- Настройки CS-Cart "Настройки -> Внешний вид":
    * Показывать мини-иконки в виде галереи --  вкл
    * Показывать количество доступных товаров -- откл
    * Показывать информацию о товаре во вкладках -- вкл
- Настраиваем характеристики:
    * Бренд -- включить настройку "Показывать в заголовке карточки товара"
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

public class GeneralSettings_ProductPage_Cascade_Var1 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductPage_CascadeGallery_Var1(){
        //Настраиваем макет для тест-кейса
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_WebsiteLayouts();
        csCartSettings.layout_Lightv2.click();
        csCartSettings.setLayoutAsDefault();

        //Настраиваем CS-Cart настройки
        csCartSettings.navigateToAppearanceSettings();
        if(!csCartSettings.setting_ThumbnailsGallery.isSelected()){
            csCartSettings.setting_ThumbnailsGallery.click();
        }
        if(csCartSettings.setting_NumberOfAvailableProducts.isSelected()){
            csCartSettings.setting_NumberOfAvailableProducts.click();
        }
        csCartSettings.selectSetting_ProductPageView("abt__ut2_cascade_gallery_template");
        if(!csCartSettings.setting_ProductDetailsInTab.isSelected()){
            csCartSettings.setting_ProductDetailsInTab.click();
        }
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем UniTheme настройки
        csCartSettings.navigateTo_ThemeSettings_tabProductLists();
        ThemeSettings_Product themeSettingsProduct = new ThemeSettings_Product();
        themeSettingsProduct.tab_Product.click();
        themeSettingsProduct.clickAndTypeSetting_CustomBlockID("106");
        if(themeSettingsProduct.setting_ShowQuantityChanger.isSelected()){
            themeSettingsProduct.setting_ShowQuantityChanger.click();
        }
        if(themeSettingsProduct.setting_ShowProductCode.isSelected()){
            themeSettingsProduct.setting_ShowProductCode.click();
        }
        if(!themeSettingsProduct.setting_ShowProductFeatures.isSelected()){
            themeSettingsProduct.setting_ShowProductFeatures.click();
        }
        if(themeSettingsProduct.setting_FeaturesInTwoColumns.isSelected()){
            themeSettingsProduct.setting_FeaturesInTwoColumns.click();
        }
        if(!themeSettingsProduct.setting_ShowShortDescription.isSelected()){
            themeSettingsProduct.setting_ShowShortDescription.click();
        }
        themeSettingsProduct.selectSetting_ShowProductBrand("logo");
        themeSettingsProduct.selectSetting_CombinationsOfProductGalleryImageFormations("1");
        csCartSettings.clickSaveButtonOfSettings();

        //Работаем с настройками характеристики Бренд
        csCartSettings.navigateToSection_Features();
        csCartSettings.clickFeatureBrand();
        WebElement checkbox_ShowOnFeaturesTab_Brand = csCartSettings.showOnFeaturesTab_Brand;
        if(!checkbox_ShowOnFeaturesTab_Brand.isSelected()){
            checkbox_ShowOnFeaturesTab_Brand.click();
        }
        WebElement checkbox_ShowInHeaderOnProductPage = csCartSettings.showInHeaderOnProductPage_Brand;
        if(!checkbox_ShowInHeaderOnProductPage.isSelected()){
            checkbox_ShowInHeaderOnProductPage.click();
        }
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем страницу товара
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Titan");
        productSettings.chooseAnyProduct();
        productSettings.clickAndTypeField_Price("10000.00");
        productSettings.clickAndTypeField_InStock("20");
        productSettings.selectSetting_ZeroPriceAction("R");
        productSettings.setPricePerUnit("шт", "3", "1");
        productSettings.selectSetting_OutOfStockActions("N");
        productSettings.hoverAndTypeField_ShortDescription("Здесь написано краткое описание товара!");
        productSettings.hoverAndTypeField_PromoText("Только до конца недели! Выберите диск с игрой в подарок!");
        Actions actions = new Actions(DriverProvider.getDriver());
        actions.moveToElement(productSettings.tab_RewardPoints).build().perform();
        productSettings.tab_RewardPoints.click();
        if(!productSettings.setting_AllowPaymentByPoints.isSelected()){
            productSettings.setting_AllowPaymentByPoints.click();
        }
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductPage_CascadeGallery_Var1")
    public void checkSettingsOnProductPage_CascadeGallery_Var1() {
        CsCartSettings csCartSettings = new CsCartSettings();
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Titan");
        productSettings.chooseAnyProduct();
        ProductPage productPage = productSettings.navigateToProductPage();
        focusBrowserTab(1);
        productPage.cookie.click();
        productPage.shiftLanguage_EN();

        SoftAssert softAssert = new SoftAssert();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что мини-иконки в виде галереи отсутствуют в шаблоне "Каскадная галерея"
        softAssert.assertFalse(!assertsOnStorefront.miniThumbnailImages_Disabled.isEmpty(),
                "There is a mini-icons gallery but shouldn't!");

        //Проверяем, что информация о товаре отображается во вкладках
        softAssert.assertTrue(!assertsOnStorefront.displayProductDetailsInTabs_Enabled.isEmpty(),
                "Product information is displayed not in tabs!");

        //Проверяем, что логотип характеристики "Бренд" присутствует
        softAssert.assertTrue(!assertsOnStorefront.showProductBrandInformation_Logo.isEmpty(),
                "There is no Brand logo on the product page!");

        //Проверяем, что характеристика "Бренд" присутствует в заголовке карточки товара
        softAssert.assertTrue(!assertsOnStorefront.showInHeaderOnProductPage.isEmpty(),
                "There is no feature Brand on the feature list!");

        //Проверяем, что присутствует ID пользовательского блока
        softAssert.assertTrue(!assertsOnStorefront.customBlockID.isEmpty(),
                "There is no Custom block on the product page!");

        //Проверяем, что присутствует Краткое описание товара
        softAssert.assertTrue(!assertsOnStorefront.product_ShortDescription.isEmpty(),
                "There is no product Short description on the product page!");

        //Проверяем, что присутствует Цена за единицу
        softAssert.assertTrue(!assertsOnStorefront.pricePerUnit.isEmpty(),
                "There is no Price per unit on the product page!");

        //Проверяем, что Промо-текст присутствует
        softAssert.assertTrue(!assertsOnStorefront.promoText.isEmpty(),
                "There is no Promo-text on the product page!");

        //Проверяем, что Бонусные баллы присутствуют
        softAssert.assertTrue(!assertsOnStorefront.product_allowPaymentByPoints.isEmpty(),
                "There is no Reward points on the product page!");
        takeScreenShot_withScroll("Cascade1.10 GS_ProductPage_Cascade_Var1 - Cascade template");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("Cascade1.15 GS_ProductPage_Cascade_Var1 - Cascade template (RTL)");

        //Проверяем характеристики
        productPage.scrollToAndClickTab_Features();
        //Проверяем, что характеристики расположены в одну колонку
        softAssert.assertTrue(!assertsOnStorefront.showFeaturesInTwoColumns_Disabled.isEmpty(),
                "Features are located in two columns instead of one!");

        takeScreenShot("Cascade1.20 GS_ProductPage_Cascade_Var1 - Product features, one column (RTL)");
        productPage.shiftLanguage_EN();
        productPage.scrollToAndClickTab_Features();
        takeScreenShot("Cascade1.25 GS_ProductPage_Cascade_Var1 - Product features, one column");

        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductPage_Cascade_Var1 passed successfully!");
    }
}