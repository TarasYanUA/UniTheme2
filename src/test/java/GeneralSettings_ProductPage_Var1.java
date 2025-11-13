import org.openqa.selenium.By;
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
import testRunner.TestRunner;

import java.time.Duration;

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

public class GeneralSettings_ProductPage_Var1 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsFor_GeneralSettings_ProductPage_Var1(){
        //Настраиваем макет для тест-кейса
        BasicPage basicPage = new BasicPage();
        basicPage.navigateToSection_WebsiteLayouts();
        basicPage.layout_Lightv2.click();
        basicPage.setLayoutAsDefault();

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
        new Select(themeSettingsProduct.setting_ShowYouSave).selectByValue("short");
        new Select(themeSettingsProduct.setting_ShowProductBrand).selectByValue("logo");
        basicPage.clickSaveButtonOfSettings();

        //Работаем с настройками характеристик Жесткий диск и Бренд
        FeaturePage featuresPage = basicPage.navigateToSection_Features();
        featuresPage.feature_HardDrive.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        featuresPage.clickAndTypeField_DescriptionOfFeature("Для характеристики, которая просто позволяет указать какое-нибудь дополнительное свойство товара. Например, у футболок это может быть \"Ткань\". Если вы создадите фильтр по этой характеристике, покупатели увидят, что она есть, и смогут легко найти по ней нужный товар.");
        if (!featuresPage.showInHeaderOnProductPage_HardDisk.isSelected())
            featuresPage.showInHeaderOnProductPage_HardDisk.click();
        featuresPage.button_SaveFeature.click();

        //Настраиваем страницу товара
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        productSettings.chooseAnyProduct();
        UtilsAdm.clickAndType(productSettings.field_Price,"10000.00");
        UtilsAdm.clickAndType(productSettings.field_InStock,"20");
        new Select(productSettings.setting_ZeroPriceAction).selectByValue("R");
        productSettings.setPricePerUnit("игровые приставки", "3", "3");
        new Select(productSettings.setting_OutOfStockActions).selectByValue("N");
        productSettings.selectSetting_ProductTemplate("default_template");
        productSettings.hoverAndTypeField_ShortDescription("Здесь написано краткое описание товара!");
        productSettings.hoverAndTypeField_PromoText("Только до конца недели! Выберите диск с игрой в подарок!");
        Actions actions = new Actions(DriverProvider.getDriver());
        actions.moveToElement(productSettings.tab_RewardPoints).build().perform();
        productSettings.tab_RewardPoints.click();
        if(!productSettings.setting_AllowPaymentByPoints.isSelected()){
            productSettings.setting_AllowPaymentByPoints.click();
        }
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsFor_GeneralSettings_ProductPage_Var1")
    public void checkGeneralSettings_ProductPage_Var1() {
        BasicPage basicPage = new BasicPage();
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        productSettings.chooseAnyProduct();
        UtilsAdm.clickAndType(productSettings.field_ListPrice,"15000");
        basicPage.clickSaveButtonOfSettings();
        ProductPage productPage = productSettings.navigateToProductPage();
        focusBrowserTab(1);
        productPage.cookie.click();
        productPage.shiftLanguage_EN();

        SoftAssert softAssert = new SoftAssert();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что мини-иконки в виде галереи
        softAssert.assertTrue(!assertsOnStorefront.miniThumbnailImagesAsGallery_Enabled.isEmpty(),
                "Mini-icons are not as a gallery!");

        //Проверяем, что информация о товаре отображается во вкладках
        softAssert.assertTrue(!assertsOnStorefront.displayProductDetailsInTabs_Enabled.isEmpty(),
                "Product information is displayed not in tabs!");

        //Проверяем, что логотип характеристики "Бренд" присутствует
        softAssert.assertTrue(!assertsOnStorefront.showProductBrandInformation_Logo.isEmpty(),
                "There is no Brand logo on product page!");

        //Проверяем, что характеристика "Бренд" присутствует в заголовке карточки товара
        softAssert.assertTrue(!assertsOnStorefront.showInHeaderOnProductPage_Brand.isEmpty(),
                "There is no feature Brand on the feature list!");

        //Проверяем, что характеристика "Жесткий диск" присутствует в заголовке карточки товара
        softAssert.assertTrue(!assertsOnStorefront.showInHeaderOnProductPage_HardDrive.isEmpty(),
                "There is no feature Hard drive on the feature list!");

        //Проверяем, что присутствует ID пользовательского блока
        softAssert.assertTrue(!assertsOnStorefront.customBlockID.isEmpty(),
                "There is no Custom block on the product page!");

        //Проверяем, что присутствует Краткое описание товара
        softAssert.assertTrue(!assertsOnStorefront.product_ShortDescription.isEmpty(),
                "There is no product Short description!");

        //Проверяем, что текст "Вы экономите" присутствует и  "Сокращенный вид"
        softAssert.assertTrue(!assertsOnStorefront.text_YouSave_Short().isEmpty(),
                "The text 'You save' is not Short or missed on the product page!");

        //Проверяем, что присутствует Цена за единицу
        softAssert.assertTrue(!assertsOnStorefront.pricePerUnit.isEmpty(),
                "There is no Price per unit on the product page!");

        //Проверяем, что Промо-текст присутствует
        softAssert.assertTrue(!assertsOnStorefront.product_PromoText.isEmpty(),
                "There is no Promo-text on the product page!");

        //Проверяем, что Бонусные баллы присутствуют
        softAssert.assertTrue(!assertsOnStorefront.product_allowPaymentByPoints.isEmpty(),
                "There is no Reward points!");

        takeScreenShot_withScroll("900 GS_ProductPage_Var1 - Default template");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("905 GS_ProductPage_Var1 - Default template (RTL)");

        //Проверяем, что характеристики расположены в одну колонку
        productPage.scrollToAndClickTab_Features();
        softAssert.assertTrue(!assertsOnStorefront.showFeaturesInTwoColumns_Disabled.isEmpty(),
                "Features are located in two columns instead of one!");

        takeScreenShot("910 GS_ProductPage_Var1 - Product features, one column (RTL)");
        productPage.shiftLanguage_EN();
        productPage.scrollToAndClickTab_Features();
        takeScreenShot("915 GS_ProductPage_Var1 - Product features, one column");
        productPage.featureDescription.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-titlebar")));
        takeScreenShot("920 GS_ProductPage_Var1 - Feature description, one column");

        //Другие шаблоны страницы товара
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("bigpicture_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(2);
        takeScreenShot_withScroll("925 GS_ProductPage_Var1 - Big picture");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("930 GS_ProductPage_Var1 - Big picture (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_flat_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(3);
        takeScreenShot_withScroll("935 GS_ProductPage_Var1 - Big picture flat");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("940 GS_ProductPage_Var1 - Big picture flat (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_three_columns_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(4);
        takeScreenShot_withScroll("945 GS_ProductPage_Var1 - Three columned");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("950 GS_ProductPage_Var1 - Three columned (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_gallery_template");
        basicPage.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(5);
        takeScreenShot_withScroll("955 GS_ProductPage_Var1 - Gallery template");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("960 GS_ProductPage_Var1 - Gallery template (RTL)");

        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductPage_Var1 passed successfully!");
    }
}