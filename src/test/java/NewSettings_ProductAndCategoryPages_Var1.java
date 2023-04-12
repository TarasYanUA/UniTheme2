import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.adminPanel.ColorSchemeSettings;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ProductSettings;
import taras.adminPanel.ThemeSettings_Product;
import taras.constants.DriverProvider;
import org.openqa.selenium.interactions.Actions;
import taras.storefront.ProductPage;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import java.io.IOException;
import java.time.Duration;
import static taras.constants.DriverProvider.getDriver;

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

public class NewSettings_ProductAndCategoryPages_Var1 extends TestRunner{
    @Test(priority = 1)
    public void setConfigurations_NewSettings_Var1() {
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

        //Настраиваем страницу товара
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("NX200");
        productSettings.chooseAnyProduct.click();
        if(DriverProvider.getDriver().findElements(By.cssSelector(".cm-notification-close")).size() >= 1){
            DriverProvider.getDriver().findElement(By.cssSelector(".cm-notification-close")).click();
        }
        productSettings.clickAndTypeField_Price("33000.00");
        productSettings.clickAndTypeField_InStock("20");
        productSettings.selectSetting_ProductTemplate("default_template");
        productSettings.hoverAndTypeField_ShortDescription("Здесь написано краткое описание товара!");
        productSettings.hoverAndTypeField_PromoText("Только до конца недели! Выберите диск с игрой в подарок!");
        Actions actions = new Actions(DriverProvider.getDriver());
        actions.moveToElement(productSettings.tab_RewardPoints).build().perform();
        productSettings.tab_RewardPoints.click();
        if(!productSettings.setting_AllowPaymentByPoints.isSelected()){
            productSettings.setting_AllowPaymentByPoints.click();
        }
        productSettings.tab_QuantityDiscounts.click();
        if(DriverProvider.getDriver().findElements(By.cssSelector("#content_qty_discounts  .cm-row-item")).size() < 2){
            productSettings.clickAndType_field_Quantity("3");
            productSettings.clickAndType_field_Value("55200");
        }
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurations_NewSettings_Var1")
    public void checkNewSettingsOnProductAndCategoryPages_Var1() throws IOException {
        CsCartSettings csCartSettings = new CsCartSettings();
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("NX200");
        productSettings.chooseAnyProduct.click();
        ProductPage productPage = productSettings.navigateToProductPage();
        focusBrowserTab(1);
        productPage.cookie.click();
        productPage.shiftLanguage_RU();
        //Проверяем, что мини-иконки не в виде галереи
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-product-thumbnails.ty-center")).size() >=1,
        "Mini-icons are in view of gallery but shouldn't!");
        //Проверяем, что название характеристики "Бренд" присутствует
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__product-brand-name")).size() >=1,
                "There is no Brand name on product page!");
        //Проверяем, что присутствует Краткое описание товара
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__short-descr")).size() >=1,
                "There is no product Short description!");
        //Проверяем, что Промо-текст присутствует
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__note")).size() >=1,
                "There is no Promo-text!");
        //Проверяем, что Бонусные баллы присутствуют
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-reward-group")).size() >=1,
                "There is no Reward points!");
        //Проверяем, что Количество отображаемых изображений галереи товара - 2
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".images-2")).size() >=1,
                "Number of displayed images of the product gallery is not 2!");
        takeScreenShot("1400 Product page - Default template, Var1");
        productPage.shiftLanguage_RTL();
        takeScreenShot("1405 Product page - Default template, Var1 (RTL)");

        //Другие шаблоны страницы товара
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("bigpicture_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(2);
        takeScreenShot("1425 Product page - Big picture, Var1");
        productPage.shiftLanguage_RTL();
        takeScreenShot("1430 Product page - Big picture, Var1 (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_flat_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(3);
        takeScreenShot("1435 Product page - Big picture flat, Var1");
        productPage.shiftLanguage_RTL();
        takeScreenShot("1440 Product page - Big picture flat, Var1 (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_three_columns_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(4);
        takeScreenShot("1445 Product page - Three columned, Var1");
        productPage.shiftLanguage_RTL();
        takeScreenShot("1450 Product page - Three columned, Var1 (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_gallery_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(5);
        takeScreenShot("1455 Product page - Gallery, Var1");
        productPage.shiftLanguage_RTL();
        takeScreenShot("1460 Product page - Gallery, Var1 (RTL)");

        //Работаем на странице категории
        StHomePage stHomePage = new StHomePage();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stHomePage.navigateToMenuPhones();
        takeScreenShot("1465 Category - Grid, Var1 (RTL)");
        //Быстрый просмотр в категории "Телефоны"
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("1470 QuickView, Var1 (RTL)");
        stCategoryPage.clickCloseQuickView();
        stCategoryPage.clickListWithoutOptions_ProductListView();
        takeScreenShot("1475 Category - List without options, Var1 (RTL)");
        stCategoryPage.clickCompactList_ProductListView();
        takeScreenShot("1480 Category - Compact list, Var1 (RTL)");
        stHomePage.selectLanguage_RU();
        takeScreenShot("1485 Category - Compact list, Var1");
        stCategoryPage.clickListWithoutOptions_ProductListView();
        takeScreenShot("1490 Category - List without options, Var1");
        stCategoryPage.clickGrid_ProductListView();
        takeScreenShot("1495 Category - Grid, Var1");
        //Быстрый просмотр в категории "Телефоны"
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("1498 QuickView, Var1");
    }
}