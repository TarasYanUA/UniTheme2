import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
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
    * Показывать мини-иконки в виде галереи --  вкл
    * Показывать количество доступных товаров -- откл
    * Показывать информацию о товаре во вкладках -- вкл
- Настраиваем характеристики:
    * Бренд -- включить настройку "Показывать в заголовке карточки товара"
    * Жесткий диск -- включить настройку "Показывать в заголовке карточки товара" и задать Описание
- Настраиваем UniTheme настройки:
    * ID пользовательского блока --  84
    * Отображать модификатор количества --  нет
    * Отображать код товара --  нет
    * Отображать характеристики товара -- да
    * Отображать характеристики в две колонки --    нет
    * Отображать краткое описание --    да
    * Отображать информацию о бренде товара --  Логотип бренда товара
- Настраиваем товар X-Box 360:
    * Действие при нулевой цене --  Не разрешать добавлять товар в корзину
    * Цена за единицу --  да
    * Действие при отсутствии товара в наличии --   Не выбрано
    * шаблон страницы товара -- все 5 шт
    * Краткое описание --   да
    * Промо-текст -- да
    * Бонусные баллы --  да
    * Оптовые цены -- нет
*/

public class GeneralSettings_ProductPage_Var1 extends TestRunner{
    @Test(priority = 1)
    public void setConfigurationsForProductPage_Var1(){
        //Настраиваем CS-Cart настройки
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToAppearanceSettingsOfCsCart();
        if(!csCartSettings.setting_ThumbnailsGallery.isSelected()){
            csCartSettings.setting_ThumbnailsGallery.click();
        }
        if(csCartSettings.setting_NumberOfAvailableProducts.isSelected()){
            csCartSettings.setting_NumberOfAvailableProducts.click();
        }
        if(!csCartSettings.setting_ProductDetailsInTab.isSelected()){
            csCartSettings.setting_ProductDetailsInTab.click();
        }
        csCartSettings.clickSaveButtonOfSettings();
        //Работаем с настройками характеристик Жесткий диск и Бренд
        csCartSettings.hoverToProductMenu();
        csCartSettings.navigateToSection_Features();
        csCartSettings.feature_HardDrive.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        csCartSettings.scrollToFeatureDescription();
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

        //Настраиваем UniTheme настройки
        csCartSettings.navigateTo_ThemeSettings_tabProductLists();
        ThemeSettings_Product themeSettingsProduct = new ThemeSettings_Product();
        themeSettingsProduct.tab_Product.click();
        themeSettingsProduct.clickAndTypeSetting_CustomBlockID("84");
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
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем страницу товара
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        productSettings.chooseAnyProduct.click();
        productSettings.clickAndTypeField_Price("10000.00");
        productSettings.clickAndTypeField_InStock("20");
        productSettings.selectSetting_ZeroPriceAction("R");
        productSettings.setPricePerUnit("игровые приставки", "3", "3");
        productSettings.selectSetting_OutOfStockActions("N");
        productSettings.selectSetting_ProductTemplate("default_template");
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

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductPage_Var1")
    public void checkSettingsOnProductPage_Var1() {
        CsCartSettings csCartSettings = new CsCartSettings();
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        productSettings.chooseAnyProduct.click();
        ProductPage productPage = productSettings.navigateToProductPage();
        focusBrowserTab(1);
        productPage.cookie.click();
        productPage.shiftLanguage_EN();
        SoftAssert softAssert = new SoftAssert();
        //Проверяем, что мини-иконки в виде галереи
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[class*='thumbnails_gallery']")).size() >=1,
                "Mini-icons are not in view of gallery!");
        //Проверяем, что информация о товаре отображается во вкладках
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[class='ty-tabs cm-j-tabs  clearfix'] ul[class='ty-tabs__list']"))
                .size() >=1, "Product information is displayed not in tabs!");
        //Проверяем, что логотип характеристики "Бренд" присутствует
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__product-brand")).size() >=1,
                "There is no Brand logo on product page!");
        //Проверяем, что характеристика "Бренд" присутствует в заголовке карточки товара
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.xpath("//div[@class='ty-features-list']//em[text()='Brand']"))
                        .size() >=1,"There is no feature Brand on the feature list!");
        //Проверяем, что характеристика "Жесткий диск" присутствует в заголовке карточки товара
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.xpath("//div[@class='ty-features-list']//em[text()='Hard drive']"))
                .size() >=1,"There is no feature Hard drive on the feature list!");
        //Проверяем, что присутствует ID пользовательского блока
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".col-right .ut2-settings-desktop")).size() >=1,
                "There is no Custom block in the right column!");
        //Проверяем, что присутствует Краткое описание товара
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__short-descr")).size() >=1,
                "There is no product Short description!");
        //Проверяем, что присутствует Цена за единицу
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-price-per-unit ")).size() >=1,
                "There is no Price per unit!");
        //Проверяем, что Промо-текст присутствует
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__note")).size() >=1,
                "There is no Promo-text!");
        //Проверяем, что Бонусные баллы присутствуют
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-reward-group")).size() >=1,
                "There is no Reward points!");
        takeScreenShot_withScroll("900 GS_ProductPage_Var1 - Default template");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("905 GS_ProductPage_Var1 - Default template (RTL)");

        //Проверяем характеристики
        productPage.scrollToAndClickTab_Features();
        //Проверяем, что характеристики расположены в одну колонку
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[class='cm-ab-similar-filter-container ']"))
                .size() >=1, "Features are located in two columns instead of one!");
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
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(2);
        takeScreenShot_withScroll("925 GS_ProductPage_Var1 - Big picture");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("930 GS_ProductPage_Var1 - Big picture (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_flat_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(3);
        takeScreenShot_withScroll("935 GS_ProductPage_Var1 - Big picture flat");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("940 GS_ProductPage_Var1 - Big picture flat (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_three_columns_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(4);
        takeScreenShot_withScroll("945 GS_ProductPage_Var1 - Three columned");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("950 GS_ProductPage_Var1 - Three columned (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_gallery_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(5);
        takeScreenShot_withScroll("955 GS_ProductPage_Var1 - Gallery template");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("960 GS_ProductPage_Var1 - Gallery template (RTL)");
        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductPage_Var1 passed successfully!");
    }
}