import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ProductSettings;
import taras.adminPanel.ThemeSettings_Product;
import taras.constants.DriverProvider;
import taras.storefront.ProductPage;
import java.time.Duration;

/*
ссылка на чеклист: https://docs.google.com/spreadsheets/d/19qsT6Hm83Kdt1Fh1WMS96sBfyp3wouEMEv17FyEglh0/edit#gid=0
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
    * Количество отображаемых изображений галереи товара (для всех шаблонов страницы товара) -- 3
- Настраиваем товар X-Box 360:
    * Действие при нулевой цене --  Не отображать
    * Цена за единицу --  да
    * Действие при отсутствии товара в наличии --   Подписаться на уведомления
    * шаблон страницы товара -- все 5 шт
    * Краткое описание --   нет
    * Промо-текст -- нет
    * Бонусные баллы --  да
    * Оптовые цены -- да
*/

public class GeneralSettings_ProductPage_Var3 extends TestRunner{
    @Test(priority = 1)
    public void setConfigurationsForProductPage_Var3(){
        //Настраиваем CS-Cart настройки
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToAppearanceSettingsOfCsCart();
        if(csCartSettings.setting_ThumbnailsGallery.isSelected()){
            csCartSettings.setting_ThumbnailsGallery.click();
        }
        if(csCartSettings.setting_NumberOfAvailableProducts.isSelected()){
            csCartSettings.setting_NumberOfAvailableProducts.click();
        }
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
        themeSettingsProduct.selectSetting_NumberOfDisplayedImages_DefaultTemplate("3");
        themeSettingsProduct.selectSetting_NumberOfDisplayedImages_BigPictureTemplate("3");
        themeSettingsProduct.selectSetting_NumberOfDisplayedImages_BigPictureFlatTemplate("3");
        themeSettingsProduct.selectSetting_NumberOfDisplayedImages_GalleryTemplate("3");
        themeSettingsProduct.selectSetting_NumberOfDisplayedImages_ThreeColumnsTemplate("3");
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем страницу товара
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        productSettings.chooseAnyProduct.click();
        if(DriverProvider.getDriver().findElements(By.cssSelector(".cm-notification-close")).size() >= 1){
            DriverProvider.getDriver().findElement(By.cssSelector(".cm-notification-close")).click();
        }
        productSettings.clickAndTypeField_Price("10000");
        productSettings.clickAndTypeField_InStock("0");
        productSettings.selectSetting_OutOfStockActions("S");
        productSettings.selectSetting_ProductTemplate("default_template");
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

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductPage_Var3")
    public void checkSettingsOnProductPage_Var3() {
        CsCartSettings csCartSettings = new CsCartSettings();
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        productSettings.chooseAnyProduct.click();
        ProductPage productPage = productSettings.navigateToProductPage();
        focusBrowserTab(1);
        productPage.cookie.click();
        productPage.shiftLanguage_EN();
        SoftAssert softAssert = new SoftAssert();
        //Проверяем, что мини-иконки не в виде галереи
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-product-thumbnails.ty-center")).size() >=1,
                "Mini-icons are in view of gallery but shouldn't!");
        //Проверяем, что информация о товаре отображается не во вкладках
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".tab-list-title")).size() >=1,
                "Product information is displayed in tabs but shouldn't!");
        //Проверяем, что логтип "Бренд" отсутствует
        softAssert.assertFalse(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__product-brand-name")).size() >=1,
                "There is a Brand logo but shouldn't!");
        //Проверяем, что характеристика "Бренд" отсутствует в заголовке карточки товара
        softAssert.assertFalse(DriverProvider.getDriver().findElements(By.xpath("//div[@class='ty-features-list']//em[text()='Brand']"))
                        .size() >=1,"There is a feature Brand on the feature list but shouldn't!");
        //Проверяем, что Код товара присутствует
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__sku")).size() >=1,
                "There is no product code!");
        //Проверяем, что Действие при отсутствии товара в наличии - Подписаться на уведомления
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("#label_sw_product_notify_248")).size() >=1,
                "There is no field 'Sign up for notification'!");
        //Проверяем, что Бонусные баллы присутствуют
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-reward-group")).size() >=1,
                "There is no Reward points!");
        //Проверяем, что Количество отображаемых изображений галереи товара - 3
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".images-3")).size() >=1,
                "Number of displayed images of the product gallery is not 3!");
        takeScreenShot_withScroll("1100 GS_ProductPage_Var3 - Default template");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("1105 GS_ProductPage_Var3 - Default template (RTL)");

        //Проверяем характеристики
        productPage.scrollToAndClickTab_FeaturesForNonTabs();
        //Проверяем, что характеристики расположены в две колонки
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".fg-two-col"))
                .size() >=1, "Features are located in one column instead of two!");
        takeScreenShot("1110 GS_ProductPage_Var3 - Product features, two columns (RTL)");
        productPage.shiftLanguage_EN();
        productPage.scrollToAndClickTab_FeaturesForNonTabs();
        takeScreenShot("1115 GS_ProductPage_Var3 - Product features, two columns");
        productPage.featureDescription.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-titlebar")));
        takeScreenShot("1120 GS_ProductPage_Var3 - Feature description, two columns");

        //Другие шаблоны страницы товара
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("bigpicture_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(2);
        takeScreenShot_withScroll("1125 GS_ProductPage_Var3 - Big picture");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("1130 GS_ProductPage_Var3 - Big picture (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_flat_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(3);
        takeScreenShot_withScroll("1135 GS_ProductPage_Var3 - Big picture flat");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("1140 GS_ProductPage_Var3 - Big picture flat (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_three_columns_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(4);
        takeScreenShot_withScroll("1145 GS_ProductPage_Var3 - Three columned");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("1150 GS_ProductPage_Var3 - Three columned (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_gallery_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(5);
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,550);");
        takeScreenShot_withScroll("1155 GS_ProductPage_Var3 - Gallery template");
        productPage.shiftLanguage_RTL();
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,550);");
        takeScreenShot_withScroll("1160 GS_ProductPage_Var3 - Gallery template (RTL)");
        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductPage_Var3 passed successfully!");
    }
}