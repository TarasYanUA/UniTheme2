package productLists;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

import java.time.Duration;

/*
Проверка настроек UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Отображать пустые звёзды рейтинга товара    -- да
Отображать общее значение рейтинга товара   -- нет
Отображать "Вы экономите"                   -- Сокращенный вид

Ширина иконки товара (по умолчанию 240) --	200
Высота иконки товара (по умолчанию 290) --	200

Отображать код товара                   -- да
Отображать статус наличия               -- да
Отображать модификатор количества       -- да
Отображать кнопку "Купить"              -- Иконка корзины и текст
Дополнительная информация о товаре      -- Краткое описание и характеристики
Отображать дополнительную информацию при наведении -- да
Отображать бренд                        -- Логотип
Показывать галерею мини-иконок товара в товарном списке --	Не отображать
Переключать изображение товара при движении мышки       -- с полосками

Вкладка "Показать ещё" -- Разрешить для товарных списков-- нет
*/

public class GeneralSettings_ProductLists_GridListView_Var1 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductLists_GridListView_Var1() {
        //Настраиваем макет для тест-кейса
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Lightv2.click();
        layoutPage.setLayoutAsDefault();

        //Работаем с настройками характеристики Бренд
        FeaturePage featuresPage = basicPage.navigateToSection_Features();
        featuresPage.featureBrand.click();
        WebElement checkboxShowInProductList = featuresPage.showInProductList;
        if (!checkboxShowInProductList.isSelected()) {
            checkboxShowInProductList.click();
            basicPage.clickSaveButtonOfSettings();
        }

        //Работаем с CS-Cart настройками
        CsCartSettings csCartSettings = basicPage.navigateToAppearanceSettings();
        UtilsAdm.setCheckboxState(csCartSettings.setting_QuickView, true);
        basicPage.clickSaveButtonOfSettings();

        //Работаем с настройками темы
        ThemeSettings_ProductLists themeSettingsProductLists = basicPage.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.tabProductLists.click();
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_EmptyStarsOfProductRating, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_CommonValueOfProductRating, false);
        new Select(themeSettingsProductLists.setting_ShowYouSave).selectByValue("short");
        UtilsAdm.clickAndType(themeSettingsProductLists.setting_ProductIconWidth, "200");
        UtilsAdm.clickAndType(themeSettingsProductLists.setting_ProductIconHeight, "200");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_ShowProductCode, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayAvailabilityStatus, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_ShowQuantityChanger, true);
        new Select(themeSettingsProductLists.setting_ShowAddToCartButton).selectByValue("icon_and_text");
        new Select(themeSettingsProductLists.setting_AdditionalProductInformation).selectByValue("features_and_description");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_ShowAdditionalInformationOnHover, true);
        new Select(themeSettingsProductLists.setting_ShowBrand).selectByValue("logo");
        new Select(themeSettingsProductLists.setting_ShowStandardImageGallery_Grid).selectByValue("N");
        new Select(themeSettingsProductLists.setting_SwitchProductImageWhenHovering).selectByValue("lines");
        ThemeSettings_ShowMore themeSettings_showMore = new ThemeSettings_ShowMore();
        UtilsAdm.hoverNavigateAndClick(themeSettings_showMore.tab_ShowMore);
        UtilsAdm.setCheckboxState(themeSettings_showMore.setting_AllowForProductLists, false);
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_GridListView_Var1")
    public void checkProductLists_GridListView_Var1() {
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        DriverProvider.getDriver().findElement(By.xpath("//span[@class='ty-tabs__span'][text()='Распродажа']")).click();
        SoftAssert softAssert = new SoftAssert();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что код товара присутствует
        softAssert.assertTrue(!assertsOnStorefront.productCode().isEmpty(),
                "There is no product code in the product block!");

        //Проверяем, что статус наличия присутствует
        softAssert.assertTrue(!assertsOnStorefront.availabilityStatus_GridList().isEmpty(),
                "There is no availability status in the product block!");

        //Проверяем, что модификатор количества присутствует
        softAssert.assertTrue(!assertsOnStorefront.quantityChanger_GridList().isEmpty(),
                "There is no quantity Changer in the product block!");

        //Проверяем, что дополнительная информация отображается при наведении
        softAssert.assertTrue(!assertsOnStorefront.gridList__AdditionalInformationOnHover.isEmpty(),
                "Additional information is displayed without mouse hover in the product block!");

        //Проверяем, что логотип бренда присутствует
        softAssert.assertTrue(!assertsOnStorefront.gridList__BrandLogo().isEmpty(),
                "There is no brand logo in the product block!");

        //Проверяем, что текст "Вы экономите" присутствует и "Сокращенный вид"
        softAssert.assertTrue(!assertsOnStorefront.text_YouSave_Short().isEmpty(),
                "The text 'You save' is not Short or missed in the product block!");

        //Проверяем, что переключатель изображений товара присутствует и он в виде Полосок
        softAssert.assertTrue(!assertsOnStorefront.gridList__SwitchProductImage_WithStripes.isEmpty(),
                "Switch is not with stripes or there is no Switch at all in the product block!");

        takeScreenShot("300 GS_ProductLists_GridListView_Var1 - BlockWithProducts");
        stHomePage.selectLanguage("ar");
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("305 GS_ProductLists_GridListView_Var1 - BlockWithProducts (RTL)");
        stHomePage.selectLanguage("ru");

        //Категория "Телефоны"
        stHomePage.navigateToHorizontalMenu_Phones();

        //Проверяем, что код товара присутствует
        softAssert.assertTrue(!assertsOnStorefront.productCode_GridList().isEmpty(),
                "There is no product code on the category page!");

        //Проверяем, что статус наличия присутствует
        softAssert.assertTrue(!assertsOnStorefront.availabilityStatus_GridList().isEmpty(),
                "There is no availability status on the category page!");

        //Проверяем, что модификатор количества присутствует
        softAssert.assertTrue(!assertsOnStorefront.quantityChanger_GridList().isEmpty(),
                "There is no quantity Changer on the category page!");

        //Проверяем, что дополнительная информация отображается при наведении
        softAssert.assertTrue(!assertsOnStorefront.gridList__AdditionalInformationOnHover.isEmpty(),
                "Additional information is displayed without mouse hover on the category page!");

        //Проверяем, что логотип бренда присутствует
        softAssert.assertTrue(!assertsOnStorefront.gridList__BrandLogo().isEmpty(),
                "There is no brand logo on the category page!");

        //Проверяем, что текст "Вы экономите" присутствует и "Сокращенный вид"
        softAssert.assertTrue(!assertsOnStorefront.text_YouSave_Short().isEmpty(),
                "The text 'You save' is not Short or missed on the category page!");

        //Проверяем, что переключатель изображений товара присутствует и он в виде Полосок
        softAssert.assertTrue(!assertsOnStorefront.gridList__SwitchProductImage_WithStripes.isEmpty(),
                "Switch is not with stripes or there is no Switch at all on the category page!");

        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.hoverToProduct("Droid 3");
        takeScreenShot("310 GS_ProductLists_GridListView_Var1 - PhoneCategory");

        stHomePage.selectLanguage("ar");
        stCategoryPage.hoverToProduct("Droid 3");
        takeScreenShot("315 GS_ProductLists_GridListView_Var1 - PhoneCategory (RTL)");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("320 GS_ProductLists_GridListView_Var1 - QuickView (RTL)");
        UtilsAdm.hoverNavigateAndClick(stCategoryPage.closeQuickView);
        stHomePage.selectLanguage("ru");
        stCategoryPage.hoverToProduct("Droid 3");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot("325 GS_ProductLists_GridListView_Var1 - QuickView");

        softAssert.assertAll();
        System.out.println("productLists.GeneralSettings_ProductLists_GridListView_Var1 passed successfully!");
    }
}