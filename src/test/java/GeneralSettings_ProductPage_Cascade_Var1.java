import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ProductSettings;
import taras.adminPanel.ThemeSettings_Product;
import taras.constants.DriverProvider;
import taras.storefront.ProductPage;

import java.time.Duration;

/*
ссылка на чеклист: https://docs.google.com/spreadsheets/d/19qsT6Hm83Kdt1Fh1WMS96sBfyp3wouEMEv17FyEglh0/edit#gid=0
- Настройки CS-Cart "Настройки -> Внешний вид":
    * Показывать мини-иконки в виде галереи --  вкл
    * Показывать количество доступных товаров -- откл
    * Показывать информацию о товаре во вкладках -- вкл
- Настраиваем характеристики:
    * Бренд -- включить настройку "Показывать в заголовке карточки товара"
    * Жесткий диск -- включить настройку "Показывать в заголовке карточки товара" и задать Описание
- Настраиваем UniTheme настройки:
    * ID пользовательского блока --  106
    * Отображать модификатор количества --  нет
    * Отображать код товара --  нет
    * Отображать характеристики товара -- да
    * Отображать характеристики в две колонки --    нет
    * Отображать краткое описание --    да
    * Отображать информацию о бренде товара --  Логотип бренда товара
- Настраиваем товар Camera NX200:
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

        //Работаем с настройками характеристик Жесткий диск и Бренд
        csCartSettings.navigateToSection_Features();
        csCartSettings.feature_HardDrive.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        csCartSettings.clickAndTypeField_DescriptionOfFeature("Для характеристики, которая просто позволяет указать какое-нибудь дополнительное свойство товара. Например, у футболок это может быть \"Ткань\". Если вы создадите фильтр по этой характеристике, покупатели увидят, что она есть, и смогут легко найти по ней нужный товар.");
        if(!csCartSettings.showInHeaderOnProductPage_HardDisk.isSelected()){
            csCartSettings.showInHeaderOnProductPage_HardDisk.click();
        }
        csCartSettings.button_SaveFeature.click();
        csCartSettings.clickFeatureBrand();
        WebElement checkbox_ShowInHeaderOnProductPage = csCartSettings.showInHeaderOnProductPage_Brand;
        if(!checkbox_ShowInHeaderOnProductPage.isSelected()){
            checkbox_ShowInHeaderOnProductPage.click();
            csCartSettings.clickSaveButtonOfSettings();
        }

        //Настраиваем страницу товара
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("NX200");
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
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        productSettings.chooseAnyProduct();
        ProductPage productPage = productSettings.navigateToProductPage();
        focusBrowserTab(1);
        productPage.cookie.click();
        productPage.shiftLanguage_EN();
        SoftAssert softAssert = new SoftAssert();
        //Проверяем, что мини-иконки в виде галереи
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector("div[class*='thumbnails_gallery']")).isEmpty(),
                "Mini-icons are not in view of gallery!");
        //Проверяем, что информация о товаре отображается во вкладках
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector("div[class='ty-tabs cm-j-tabs  clearfix'] ul[class='ty-tabs__list']")).isEmpty(),
                "Product information is displayed not in tabs!");
        //Проверяем, что логотип характеристики "Бренд" присутствует
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__product-brand")).isEmpty(),
                "There is no Brand logo on product page!");
        //Проверяем, что характеристика "Бренд" присутствует в заголовке карточки товара
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.xpath("//div[@class='ty-features-list']//em[text()='Brand']")).isEmpty(),
                "There is no feature Brand on the feature list!");
        //Проверяем, что характеристика "Жесткий диск" присутствует в заголовке карточки товара
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.xpath("//div[@class='ty-features-list']//em[text()='Hard drive']")).isEmpty(),
                "There is no feature Hard drive on the feature list!");
        //Проверяем, что присутствует ID пользовательского блока
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__custom-block")).isEmpty(),
                "There is no Custom block!");
        //Проверяем, что присутствует Краткое описание товара
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__short-descr")).isEmpty(),
                "There is no product Short description!");
        //Проверяем, что присутствует Цена за единицу
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ty-price-per-unit ")).isEmpty(),
                "There is no Price per unit!");
        //Проверяем, что Промо-текст присутствует
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__note")).isEmpty(),
                "There is no Promo-text!");
        //Проверяем, что Бонусные баллы присутствуют
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ty-reward-group")).isEmpty(),
                "There is no Reward points!");
        takeScreenShot_withScroll("Cascade1 10 GS_ProductPage_Cascade_Var1 - Default template");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("Cascade1 15 GS_ProductPage_Cascade_Var1 - Default template (RTL)");

        //Проверяем характеристики
        productPage.scrollToAndClickTab_Features();
        //Проверяем, что характеристики расположены в одну колонку
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector("div[class='cm-ab-similar-filter-container ']")).isEmpty(),
                "Features are located in two columns instead of one!");
        takeScreenShot("Cascade1 20 GS_ProductPage_Cascade_Var1 - Product features, one column (RTL)");
        productPage.shiftLanguage_EN();
        productPage.scrollToAndClickTab_Features();
        takeScreenShot("Cascade1 25 GS_ProductPage_Cascade_Var1 - Product features, one column");
        productPage.featureDescription.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-titlebar")));
        takeScreenShot("Cascade1 30 GS_ProductPage_Cascade_Var1 - Feature description, one column");
        softAssert.assertAll();
    }
}