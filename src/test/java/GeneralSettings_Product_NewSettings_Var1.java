import org.testng.annotations.Test;
import taras.adminPanel.ColorSchemeSettings;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ThemeSettings_Product;

import java.io.IOException;

/*
ссылка на чеклист: https://docs.google.com/spreadsheets/d/1dWaGNOBw-F8WQslHjiRiRSK2QOHiSeRLbxKiAe-h6KQ/edit?usp=sharing
- Настройки CS-Cart "Настройки -> Внешний вид":
    * Включить быстрый просмотр --  да
    * Показывать мини-иконки в виде галереи --  нет
- Настраиваем UniTheme настройки:
    * ID пользовательского блока --  нет
    * Количество отображаемых изображений галереи товара -- 2
    * Отображать модификатор количества --  да
    * Отображать код товара --  да
    * Отображать краткое описание --    да
    * Отображать информацию о бренде товара --  Название бренда товара
- Настраиваем UniTheme цветосхему, вкладка "Товар":
    * Добавить фон/маску для изображений товара --  да
    * Добавить обрамление для изображений товара -- да
- Настраиваем UniTheme цветосхему, вкладка "Списки товаров":
    * Тип обрамления товара в сетке -- Без рамки
    * Добавить фон/маску для изображений товара --  да
- Настраиваем товар Samsung NX200:
    * Цена за единицу --  33000
    * Наличие --    10
    * Промо-текст -- да
    * Бонусные баллы --  да
    * Оптовые цены -- да
    * Краткое описание --   да
    * шаблон страницы товара -- все 5 шт
*/

public class GeneralSettings_Product_NewSettings_Var1 extends TestRunner{
    @Test(priority = 1)
    public void setConfigurationsForProductPage_Var1() throws IOException {
        //Настраиваем CS-Cart настройки
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToAppearanceSettingsOfCsCart();
        if(!csCartSettings.setting_QuickView.isSelected()){
            csCartSettings.setting_QuickView.click();
        }
        if(csCartSettings.setting_ThumbnailsGallery.isSelected()){
            csCartSettings.setting_ThumbnailsGallery.click();
        }
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем UniTheme настройки
        ThemeSettings_Product themeSettingsProduct = csCartSettings.navigateTo_ThemeSettings_tabProduct();
        themeSettingsProduct.clickAndTypeSetting_CustomBlockID("");
        themeSettingsProduct.selectSetting_NumberOfDisplayedImages("2");
        if(!themeSettingsProduct.setting_ShowQuantityChanger.isSelected()){
            themeSettingsProduct.setting_ShowQuantityChanger.click();
        }
        if(!themeSettingsProduct.setting_ShowProductCode.isSelected()){
            themeSettingsProduct.setting_ShowProductCode.click();
        }
        if(!themeSettingsProduct.setting_ShowShortDescription.isSelected()){
            themeSettingsProduct.setting_ShowShortDescription.click();
        }
        themeSettingsProduct.selectSetting_ShowProductBrand("name");
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему
        ColorSchemeSettings colorSchemeSettings = csCartSettings.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.fieldOfActiveColorScheme.click();
        colorSchemeSettings.activeColorScheme.click();
        makePause();
        colorSchemeSettings.tab_Product.click();
        if(!colorSchemeSettings.setting_ProductMaskForProductImages.isSelected()){
            colorSchemeSettings.setting_ProductMaskForProductImages.click();
        }
        if(!colorSchemeSettings.setting_ProductBorderForProductImages.isSelected()){
            colorSchemeSettings.setting_ProductBorderForProductImages.click();
        }
        colorSchemeSettings.tab_ProductLists.click();
        colorSchemeSettings.selectSetting_FrameType("none");
        if(!colorSchemeSettings.setting_ProductListsMaskForProductImages.isSelected()){
        colorSchemeSettings.setting_ProductListsMaskForProductImages.click();
        }
        csCartSettings.clickSaveButtonOfSettings();

/*

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
    public void checkSettingsOnProductPage_Var1() throws IOException {
        CsCartSettings csCartSettings = new CsCartSettings();
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("X-Box 360");
        productSettings.chooseAnyProduct.click();
        ProductPage productPage = productSettings.navigateToProductPage();
        focusBrowserTab(1);
        productPage.cookie.click();
        productPage.shiftLanguage_RU();
        //Проверяем, что мини-иконки в виде галереи
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[class*='thumbnails_gallery']")).size() >=1,
                "Mini-icons are not in view of gallery!");
        //Проверяем, что информация о товаре отображается во вкладках
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[class='ty-tabs cm-j-tabs  clearfix'] ul[class='ty-tabs__list']"))
                .size() >=1, "Product information is displayed not in tabs!");
        //Проверяем, что логотип характеристики "Бренд" присутствует
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__product-brand")).size() >=1,
                "There is no Brand logo on product page!");
        //Проверяем, что характеристика "Бренд" присутствует в заголовке карточки товара
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.xpath("//div[@class='ty-features-list']//em[text()='Бренд']"))
                        .size() >=1,"There is no feature Brand on the feature list!");
        //Проверяем, что характеристика "Жесткий диск" присутствует в заголовке карточки товара
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.xpath("//div[@class='ty-features-list']//em[text()='Жесткий диск']"))
                .size() >=1,"There is no feature Hard drive on the feature list!");
        //Проверяем, что присутствует ID пользовательского блока
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".col-right .ut2-settings-desktop")).size() >=1,
                "There is no Custom block in the right column!");
        //Проверяем, что присутствует Краткое описание товара
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__short-descr")).size() >=1,
                "There is no product Short description!");
        //Проверяем, что присутствует Цена за единицу
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-price-per-unit ")).size() >=1,
                "There is no Price per unit!");
        //Проверяем, что Промо-текст присутствует
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__note")).size() >=1,
                "There is no Promo-text!");
        //Проверяем, что Бонусные баллы присутствуют
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-reward-group")).size() >=1,
                "There is no Reward points!");
        takeScreenShot("100 Product page, Default template, Var1");
        productPage.shiftLanguage_RTL();
        makePause();
        takeScreenShot("105 Product page, Default template, Var1 (RTL)");

        //Проверяем характеристики
        productPage.scrollToAndClickTab_Features();
        //Проверяем, что характеристики расположены в одну колонку
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector("div[class='cm-ab-similar-filter-container ']"))
                .size() >=1, "Features are located in two columns instead of one!");
        takeScreenShot("1110 Product features, one column (RTL)");
        productPage.shiftLanguage_RU();
        productPage.scrollToAndClickTab_Features();
        takeScreenShot("1115 Product features, one column");
        productPage.featureDescription.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-titlebar")));
        takeScreenShot("1120 Feature description, one column");

        //Другие шаблоны страницы товара
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("bigpicture_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(2);
        takeScreenShot("1125 Template - Big picture, Var1");
        productPage.shiftLanguage_RTL();
        takeScreenShot("1130 Template - Big picture, Var1 (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_flat_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(3);
        takeScreenShot("1135 Template - Big picture flat, Var1");
        productPage.shiftLanguage_RTL();
        takeScreenShot("1140 Template - Big picture flat, Var1 (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_three_columns_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(4);
        takeScreenShot("1145 Template - Three columned, Var1");
        productPage.shiftLanguage_RTL();
        takeScreenShot("1150 Template - Three columned, Var1 (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_gallery_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(5);
        takeScreenShot("1155 Template - Gallery, Var1");
        productPage.shiftLanguage_RTL();
        takeScreenShot("1160 Template - Gallery, Var1 (RTL)");*/
    }
}