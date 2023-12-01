import  org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.constants.DriverProvider;
import org.openqa.selenium.interactions.Actions;
import taras.storefront.ProductPage;
import java.time.Duration;

/*
ссылка на чеклист: https://docs.google.com/spreadsheets/d/1YPAkjqk12kPh7LBDU1tq7qdwLmCo-Rly00TdfW8h-Wo/edit#gid=2110746700
- CS-Cart "Настройки -> Внешний вид":
    * Показывать мини-иконки в виде галереи --  нет
- UniTheme2 -- Настройки темы -- вкладка "Товар":
    * ID пользовательского блока --  нет
    * Отображать модификатор количества --  да
    * Отображать код товара --  да
    * Отображать краткое описание --    да
    * Отображать информацию о бренде товара --  Название бренда товара
    * Количество отображаемых изображений галереи товара (для всех шаблонов страницы товара) -- 2
- UniTheme2 -- Настройки цветосхемы -- вкладка "Товар":
    * Добавить фон/маску для изображений товара --  да
    * Добавить обрамление для изображений товара -- да
- Товар Samsung NX200:
    * Цена за единицу --  33000
    * Наличие --    10
    * Промо-текст -- да
    * Бонусные баллы --  да
    * Оптовые цены -- да
    * Краткое описание --   да
    * шаблон страницы товара -- все 5 шт
*/

public class ColorSchemeSettings_Product_Var1 extends TestRunner{
    @Test(priority = 1)
    public void setConfigurations_ColorSchemeSettings_Product_Var1() {
        //Настраиваем CS-Cart настройки
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToAppearanceSettingsOfCsCart();
        if(csCartSettings.setting_ThumbnailsGallery.isSelected()){
            csCartSettings.setting_ThumbnailsGallery.click();
            csCartSettings.clickSaveButtonOfSettings();
        }

        //Настраиваем UniTheme настройки, вкладка "Товар"
        ThemeSettings_Product themeSettingsProduct = csCartSettings.navigateTo_ThemeSettings_tabProduct();
        themeSettingsProduct.clickAndTypeSetting_CustomBlockID("");
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
        themeSettingsProduct.selectSetting_NumberOfDisplayedImages_DefaultTemplate("2");
        themeSettingsProduct.selectSetting_NumberOfDisplayedImages_BigPictureTemplate("2");
        themeSettingsProduct.selectSetting_NumberOfDisplayedImages_BigPictureFlatTemplate("2");
        themeSettingsProduct.selectSetting_NumberOfDisplayedImages_GalleryTemplate("2");
        themeSettingsProduct.selectSetting_NumberOfDisplayedImages_ThreeColumnsTemplate("2");
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем UniTheme настройки, вкладка "Списки товаров"
        ThemeSettings_ProductLists themeSettingsProductLists = new ThemeSettings_ProductLists();
        themeSettingsProductLists.clickTabProductLists();
        if(!themeSettingsProductLists.setting_AllowToSelectVariationsAndOptions.isSelected()){
            themeSettingsProductLists.setting_AllowToSelectVariationsAndOptions.click();
            csCartSettings.clickSaveButtonOfSettings();
        }

        //Настраиваем UniTheme цветосхему, вкладка "Товар"
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
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем страницу товара
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("NX200");
        productSettings.chooseAnyProduct.click();
        if(!DriverProvider.getDriver().findElements(By.cssSelector(".cm-notification-close")).isEmpty()){
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

    @Test(priority = 2, dependsOnMethods = "setConfigurations_ColorSchemeSettings_Product_Var1")
    public void checkColorSchemeSettings_Product_Var1() {
        CsCartSettings csCartSettings = new CsCartSettings();
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("NX200");
        productSettings.chooseAnyProduct.click();
        ProductPage productPage = productSettings.navigateToProductPage();
        focusBrowserTab(1);
        productPage.cookie.click();
        productPage.shiftLanguage_EN();
        SoftAssert softAssert = new SoftAssert();
        //Проверяем, что мини-иконки не в виде галереи
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ty-product-thumbnails.ty-center")).isEmpty(),
        "Mini-icons are in view of gallery but shouldn't!");
        //Проверяем, что название характеристики "Бренд" присутствует
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__product-brand-name")).isEmpty(),
                "There is no Brand name on product page!");
        //Проверяем, что присутствует Краткое описание товара
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__short-descr")).isEmpty(),
                "There is no product Short description!");
        //Проверяем, что Промо-текст присутствует
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pb__note")).isEmpty(),
                "There is no Promo-text!");
        //Проверяем, что Бонусные баллы присутствуют
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ty-reward-group")).isEmpty(),
                "There is no Reward points!");
        //Проверяем, что Количество отображаемых изображений галереи товара - 2
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".images-2")).isEmpty(),
                "Number of displayed images of the product gallery is not 2!");
        takeScreenShot_withScroll("1200 ColorSchemeSettings_Product_Var1 - Default template");
        productPage.hoverToBlockWithProducts();
        makePause();
        productPage.buttonAddToCart_ProductWithOptions.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        //Проверяем, что модификатор количества присутствует во всплывающем окне покупки товара с опциями
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ut2_select_variation__buttons .ty-value-changer")).isEmpty(),
        "There is no quantity changer in pop-up window of the product with options!");
        takeScreenShot("1202 ColorSchemeSettings_Product_Var1 - Pop-up window of product with options");
        productPage.closePopUpWindow.click();
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("1205 ColorSchemeSettings_Product_Var1 - Default template (RTL)");
        productPage.hoverToBlockWithProducts();
        makePause();
        productPage.buttonAddToCart_ProductWithOptions.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        takeScreenShot("1207 ColorSchemeSettings_Product_Var1 - Pop-up window of product with options (RTL)");
        productPage.closePopUpWindow.click();

        //Другие шаблоны страницы товара
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("bigpicture_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(2);
        takeScreenShot_withScroll("1210 ColorSchemeSettings_Product_Var1 - Big picture");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("1215 ColorSchemeSettings_Product_Var1 - Big picture (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_flat_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(3);
        takeScreenShot_withScroll("1220 ColorSchemeSettings_Product_Var1 - Big picture flat");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("1225 ColorSchemeSettings_Product_Var1 - Big picture flat (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_three_columns_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(4);
        takeScreenShot_withScroll("1230 ColorSchemeSettings_Product_Var1 - Three columned");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("1235 ColorSchemeSettings_Product_Var1 - Three columned (RTL)");
        focusBrowserTab(0);
        productSettings.selectSetting_ProductTemplate("abt__ut2_bigpicture_gallery_template");
        csCartSettings.clickSaveButtonOfSettings();
        productSettings.navigateToProductPage();
        focusBrowserTab(5);
        takeScreenShot_withScroll("1240 ColorSchemeSettings_Product_Var1 - Gallery template");
        productPage.shiftLanguage_RTL();
        takeScreenShot_withScroll("1245 ColorSchemeSettings_Product_Var1 - Gallery template (RTL)");
        System.out.println("ColorSchemeSettings_Product_Var1 passed successfully!");
        softAssert.assertAll();
    }
}