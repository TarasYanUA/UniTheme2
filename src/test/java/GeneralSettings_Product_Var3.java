import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ProductSettings;
import taras.adminPanel.ThemeSettings_Product;
import taras.constants.DriverProvider;
import taras.storefront.ProductPage;
import java.io.IOException;
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
- Настраиваем товар X-Box 360:
    * Действие при нулевой цене --  Не отображать
    * Цена за единицу --  да
    * Действие при отсутствии товара в наличии --   Подписаться на уведомления
    * шаблон страницы товара -- все 5 шт
    * Краткое описание --   нет
    * Промо-текст -- нет
    * Бонусные баллы --  да
*/

public class GeneralSettings_Product_Var3 extends TestRunner{
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
        csCartSettings.navigateToAddonsPage();
        csCartSettings.clickThemeSectionsOnManagementPage();
        csCartSettings.navigateToThemeSettings();
        ThemeSettings_Product themeSettingsProduct = new ThemeSettings_Product();
        themeSettingsProduct.tab_Product.click();
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
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем страницу товара
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        productSettings.chooseAnyProduct.click();
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
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2)
    public void checkSettingsOnProductPage_Var3() throws IOException {
        CsCartSettings csCartSettings = new CsCartSettings();
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        productSettings.chooseAnyProduct.click();
        ProductPage productPage = productSettings.navigateToProductPage();
        focusBrowserTab(1);
        productPage.cookie.click();
        productPage.shiftLanguage_RU();
        //Проверяем, что мини-иконки не в виде галереи
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-product-thumbnails.ty-center.cm-image-gallery")).size() >=1,
                "Mini-icons are in view of gallery but shouldn't!");
        //Проверяем, что информация о товаре отображается не во вкладках
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".tab-list-title")).size() >=1,
                "Product information is displayed in tabs but shouldn't!");
        //Проверяем, что логтип "Бренд" отсутствует
        Assert.assertFalse(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__product-brand-name")).size() >=1,
                "There is a Brand logo but shouldn't!");
        //Проверяем, что характеристика "Бренд" отсутствует в заголовке карточки товара
        Assert.assertFalse(DriverProvider.getDriver().findElements(By.xpath("//div[@class='ty-features-list']//em[text()='Бренд']"))
                        .size() >=1,"There is a feature Brand on the feature list but shouldn't!");
        //Проверяем, что Код товара присутствует
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__sku")).size() >=1,
                "There is no product code!");
        /////////Проверяем, что Действие при отсутствии товара в наличии - Подписаться на уведомления
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("#label_sw_product_notify_248")).size() >=1,
                "There is no field 'Sign up for notification'!");
        //Проверяем, что Бонусные баллы присутствуют
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-reward-group")).size() >=1,
                "There is no Reward points!");
        takeScreenShot("1300 Product page, Default template, Var3");
        productPage.shiftLanguage_RTL();
        makePause();
        takeScreenShot("1305 Product page, Default template, Var3 (RTL)");

        //Проверяем характеристики
        productPage.scrollToAndClickTab_FeaturesForNonTabs();
        //Проверяем, что характеристики расположены в две колонки
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".fg-two-col"))
                .size() >=1, "Features are located in one column instead of two!");
        takeScreenShot("1310 Product features, two columns (RTL)");
        productPage.shiftLanguage_RU();
        productPage.scrollToAndClickTab_FeaturesForNonTabs();
        takeScreenShot("1315 Product features, two columns");
        productPage.featureDescription.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-titlebar")));
        takeScreenShot("1320 Feature description, two columns");

        //Другие шаблоны страницы товара
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("bigpicture_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(2);
        takeScreenShot("1325 Template - Big picture, Var3");
        productPage.shiftLanguage_RTL();
        takeScreenShot("1330 Template - Big picture, Var3 (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_flat_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(3);
        takeScreenShot("1335 Template - Big picture flat, Var3");
        productPage.shiftLanguage_RTL();
        takeScreenShot("1340 Template - Big picture flat, Var3 (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_three_columns_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(4);
        takeScreenShot("1345 Template - Three columned, Var3");
        productPage.shiftLanguage_RTL();
        takeScreenShot("1350 Template - Three columned, Var3 (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_gallery_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(5);
        takeScreenShot("1355 Template - Gallery, Var3");
        productPage.shiftLanguage_RTL();
        takeScreenShot("1360 Template - Gallery, Var3 (RTL)");
    }
}