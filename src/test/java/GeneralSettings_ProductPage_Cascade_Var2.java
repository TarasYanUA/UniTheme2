import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ProductSettings;
import taras.adminPanel.ThemeSettings_Product;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.ProductPage;
import testRunner.TestRunner;

/*
- Настройки CS-Cart "Настройки -> Внешний вид":
    * Показывать мини-иконки в виде галереи --  откл
    * Показывать количество доступных товаров -- вкл
    * Показывать информацию о товаре во вкладках -- вкл
- Настраиваем характеристики:
    * Бренд -- включить настройку "Показывать в заголовке карточки товара"
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

public class GeneralSettings_ProductPage_Cascade_Var2 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductPage_CascadeGallery_Var2(){
        //Настраиваем CS-Cart настройки
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToAppearanceSettings();
        if(csCartSettings.setting_ThumbnailsGallery.isSelected()){
            csCartSettings.setting_ThumbnailsGallery.click();
        }
        if(!csCartSettings.setting_NumberOfAvailableProducts.isSelected()){
            csCartSettings.setting_NumberOfAvailableProducts.click();
        }
        csCartSettings.selectSetting_ProductPageView("abt__ut2_cascade_gallery_template");
        if(!csCartSettings.setting_ProductDetailsInTab.isSelected()){
            csCartSettings.setting_ProductDetailsInTab.click();
        }
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

        //Настраиваем UniTheme настройки
        ThemeSettings_Product themeSettingsProduct = csCartSettings.navigateTo_ThemeSettings_tabProduct();
        themeSettingsProduct.clickAndTypeSetting_CustomBlockID("109");
        if(!themeSettingsProduct.setting_ShowQuantityChanger.isSelected()){
            themeSettingsProduct.setting_ShowQuantityChanger.click();
        }
        if(!themeSettingsProduct.setting_ShowProductCode.isSelected()){
            themeSettingsProduct.setting_ShowProductCode.click();
        }
        if(!themeSettingsProduct.setting_ShowProductFeatures.isSelected()){
            themeSettingsProduct.setting_ShowProductFeatures.click();
        }
        if(!themeSettingsProduct.setting_FeaturesInTwoColumns.isSelected()){
            themeSettingsProduct.setting_FeaturesInTwoColumns.click();
        }
        if(!themeSettingsProduct.setting_ShowShortDescription.isSelected()){
            themeSettingsProduct.setting_ShowShortDescription.click();
        }
        themeSettingsProduct.selectSetting_ShowProductBrand("name");
        themeSettingsProduct.selectSetting_CombinationsOfProductGalleryImageFormations("2");
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем страницу товара
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Titan");
        productSettings.chooseAnyProduct();
        productSettings.clickAndTypeField_Price("0");
        productSettings.clickAndTypeField_InStock("0");
        productSettings.selectSetting_ZeroPriceAction("A");
        productSettings.selectSetting_OutOfStockActions("B");
        productSettings.hoverAndTypeField_ShortDescription("Здесь написано краткое описание товара!");
        productSettings.hoverAndTypeField_PromoText("Только до конца недели! Выберите диск с игрой в подарок!");
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductPage_CascadeGallery_Var2")
    public void checkSettingsOnProductPage_CascadeGallery_Var2() {
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

        //Проверяем, что настройка "Действие при отсутствии товара в наличии -- Предзаказ"
        softAssert.assertTrue(!assertsOnStorefront.outOfStockActions_BuyInAdvance.isEmpty(),
                "Out of stock action is not 'Buy in advance'!");

        //Проверяем, что информация о товаре отображается во вкладках
        softAssert.assertTrue(!assertsOnStorefront.displayProductDetailsInTabs.isEmpty(),
                "Product information is not displayed in tabs!");

        //Проверяем, что название характеристики "Бренд" присутствует
        softAssert.assertTrue(!assertsOnStorefront.showProductBrandInformation_Name.isEmpty(),
                "There is no Brand name on product page!");

        //Проверяем, что характеристика "Бренд" присутствует в заголовке карточки товара
        softAssert.assertTrue(!assertsOnStorefront.showInHeaderOnProductPage.isEmpty(),
                "There is no feature Brand on the feature list!");

        //Проверяем, что присутствует ID пользовательского блока
        softAssert.assertTrue(!assertsOnStorefront.customBlockID.isEmpty(),
                "There is no Custom block!");

        //Проверяем, что Модификатор количества присутствует
        softAssert.assertTrue(!assertsOnStorefront.showQuantityChanger.isEmpty(),
                "There is no Quantity changer on the product page!");

        //Проверяем, что Код товара присутствует
        softAssert.assertTrue(!assertsOnStorefront.showProductCode.isEmpty(),
                "There is no product code on the product page!");

        //Проверяем, что присутствует Краткое описание товара
        softAssert.assertTrue(!assertsOnStorefront.product_ShortDescription.isEmpty(),
                "There is no product Short description!");

        //Проверяем, что Действие при нулевой цене -- Попросить покупателя ввести цену
        softAssert.assertTrue(!assertsOnStorefront.zeroPriceAction_AskCustomerToEnterPrice.isEmpty(),
                "There is no field 'Enter your price'!");

        //Проверяем, что Промо-текст присутствует
        softAssert.assertTrue(!assertsOnStorefront.promoText.isEmpty(),
                "There is no Promo-text!");

        takeScreenShot_withScroll("Cascade2.10 GS_ProductPage_Var2 - Cascade template");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("Cascade2.15 GS_ProductPage_Var2 - Cascade template (RTL)");

        //Проверяем характеристики
        productPage.scrollToAndClickTab_Features();
        //Проверяем, что характеристики расположены в две колонки
        softAssert.assertTrue(!assertsOnStorefront.showFeaturesInTwoColumns_Enabled.isEmpty(),
                "Features are located in one column instead of two!");

        takeScreenShot("Cascade2.20 GS_ProductPage_Var2 - Product features, two columns (RTL)");
        productPage.shiftLanguage_EN();
        productPage.scrollToAndClickTab_Features();
        takeScreenShot("Cascade2.25 GS_ProductPage_Var2 - Product features, two columns");

        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductPage_Cascade_Var2 passed successfully!");
    }
}