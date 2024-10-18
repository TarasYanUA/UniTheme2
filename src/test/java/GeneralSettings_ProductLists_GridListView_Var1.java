import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.ThemeSettings_ProductLists;
import taras.adminPanel.ThemeSettings_ShowMore;
import taras.constants.DriverProvider;
import taras.adminPanel.CsCartSettings;
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
Отображать логотип бренда               -- да
Показывать галерею мини-иконок товара в товарном списке --	Не отображать
Переключать изображение товара при движении мышки       -- с полосками

Вкладка "Показать ещё" -- Разрешить для товарных списков-- нет
*/

public class GeneralSettings_ProductLists_GridListView_Var1 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductLists_GridListView_Var1() {
        //Настраиваем макет для тест-кейса
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_WebsiteLayouts();
        csCartSettings.layout_Lightv2.click();
        csCartSettings.setLayoutAsDefault();

        //Работаем с настройками характеристики Бренд
        csCartSettings.navigateToSection_Features();
        csCartSettings.clickFeatureBrand();
        WebElement checkboxShowInProductList = csCartSettings.showInProductList;
        if (!checkboxShowInProductList.isSelected()) {
            checkboxShowInProductList.click();
        }
        csCartSettings.clickSaveButtonOfSettings();

        //Работаем с настройками темы
        ThemeSettings_ProductLists themeSettingsProductLists = csCartSettings.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.clickTabProductLists();
        WebElement checkboxProductRating = themeSettingsProductLists.settingEmptyStarsOfProductRating;
        if (!checkboxProductRating.isSelected()) {
            checkboxProductRating.click();
        }
        WebElement checkboxSettingCommonValueOfProductRating = themeSettingsProductLists.settingCommonValueOfProductRating;
        if (checkboxSettingCommonValueOfProductRating.isSelected()) {
            checkboxSettingCommonValueOfProductRating.click();
        }
        themeSettingsProductLists.selectSettingShowYouSave("short");
        themeSettingsProductLists.clickAndTypeSettingProductIconWidth("200");
        themeSettingsProductLists.clickAndTypeSettingProductIconHeight("200");
        WebElement checkboxSettingShowProductCode = themeSettingsProductLists.settingShowProductCode;
        if (!checkboxSettingShowProductCode.isSelected()) {
            checkboxSettingShowProductCode.click();
        }
        WebElement checkboxSettingDisplayAvailabilityStatus = themeSettingsProductLists.settingDisplayAvailabilityStatus;
        if (!checkboxSettingDisplayAvailabilityStatus.isSelected()) {
            checkboxSettingDisplayAvailabilityStatus.click();
        }
        WebElement checkboxSettingShowQuantityChanger = themeSettingsProductLists.settingShowQuantityChanger;
        if (!checkboxSettingShowQuantityChanger.isSelected()) {
            checkboxSettingShowQuantityChanger.click();
        }
        themeSettingsProductLists.selectSettingShowAddToCartButton("icon_and_text");
        themeSettingsProductLists.selectSettingAdditionalProductInformation("features_and_description");
        WebElement checkboxSettingShowAdditionalInformationOnHover = themeSettingsProductLists.settingShowAdditionalInformationOnHover;
        if (!checkboxSettingShowAdditionalInformationOnHover.isSelected()) {
            checkboxSettingShowAdditionalInformationOnHover.click();
        }
        WebElement checkboxSettingShowBrandLogo = themeSettingsProductLists.settingShowBrandLogo;
        if (!checkboxSettingShowBrandLogo.isSelected()) {
            checkboxSettingShowBrandLogo.click();
        }
        themeSettingsProductLists.selectSetting_ShowGalleryOfMiniIcons("N");
        themeSettingsProductLists.selectSetting_SwitchProductImageWhenHovering("lines");
        ThemeSettings_ShowMore themeSettings_showMore = new ThemeSettings_ShowMore();
        themeSettings_showMore.navigateTo_ThemeSettings_tabShowMore();
        if (themeSettings_showMore.setting_AllowForProductLists.isSelected())
            themeSettings_showMore.setting_AllowForProductLists.click();
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_GridListView_Var1")
    public void checkProductLists_GridListView_Var1() {
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        DriverProvider.getDriver().findElement(By.xpath("//span[@class='ty-tabs__span'][text()='Распродажа']")).click();
        SoftAssert softAssert = new SoftAssert();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что код товара присутствует
        softAssert.assertTrue(!assertsOnStorefront.productCode.isEmpty(),
                "There is no product code in the product block!");

        //Проверяем, что статус наличия присутствует
        softAssert.assertTrue(!assertsOnStorefront.availabilityStatus.isEmpty(),
                "There is no availability status in the product block!");

        //Проверяем, что модификатор количества присутствует
        softAssert.assertTrue(!assertsOnStorefront.quantityChanger.isEmpty(),
                "There is no quantity Changer in the product block!");

        //Проверяем, что дополнительная информация отображается при наведении
        softAssert.assertTrue(!assertsOnStorefront.gridList__AdditionalInformationOnHover.isEmpty(),
                "Additional information is displayed without mouse hover in the product block!");

        //Проверяем, что логотип бренда присутствует
        softAssert.assertTrue(!assertsOnStorefront.gridList__BrandLogo.isEmpty(),
                "There is no brand logo in the product block!");

        //Проверяем, что текст "Вы экономите" присутствует и "Сокращенный вид"
        softAssert.assertTrue(!assertsOnStorefront.text_YouSave_Short.isEmpty(),
                "The text 'You save' is not Short or missed in the product block!");

        //Проверяем, что переключатель изображений товара присутствует и он в виде Полосок
        softAssert.assertTrue(!assertsOnStorefront.gridList__SwitchProductImage_WithStripes.isEmpty(),
                "Switch is not with stripes or there is no Switch at all in the product block!");

        takeScreenShot("300 GS_ProductLists_GridListView_Var1 - BlockWithProducts");
        stHomePage.selectLanguage_RTL();
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("305 GS_ProductLists_GridListView_Var1 - BlockWithProducts (RTL)");
        stHomePage.selectLanguage_RU();

        //Категория "Телефоны"
        stHomePage.navigateToHorizontalMenu_Phones();
        //Проверяем, что код товара присутствует
        softAssert.assertTrue(!assertsOnStorefront.productCode.isEmpty(),
                "There is no product code on the category page!");

        //Проверяем, что статус наличия присутствует
        softAssert.assertTrue(!assertsOnStorefront.availabilityStatus.isEmpty(),
                "There is no availability status on the category page!");

        //Проверяем, что модификатор количества присутствует
        softAssert.assertTrue(!assertsOnStorefront.quantityChanger.isEmpty(),
                "There is no quantity Changer on the category page!");

        //Проверяем, что дополнительная информация отображается при наведении
        softAssert.assertTrue(!assertsOnStorefront.gridList__AdditionalInformationOnHover.isEmpty(),
                "Additional information is displayed without mouse hover on the category page!");

        //Проверяем, что логотип бренда присутствует
        softAssert.assertTrue(!assertsOnStorefront.gridList__BrandLogo.isEmpty(),
                "There is no brand logo on the category page!");

        //Проверяем, что текст "Вы экономите" присутствует и "Сокращенный вид"
        softAssert.assertTrue(!assertsOnStorefront.text_YouSave_Short.isEmpty(),
                "The text 'You save' is not Short or missed on the category page!");

        //Проверяем, что переключатель изображений товара присутствует и он в виде Полосок
        softAssert.assertTrue(!assertsOnStorefront.gridList__SwitchProductImage_WithStripes.isEmpty(),
                "Switch is not with stripes or there is no Switch at all on the category page!");

        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot_withScroll("310 GS_ProductLists_GridListView_Var1 - PhoneCategory");

        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot_withScroll("315 GS_ProductLists_GridListView_Var1 - PhoneCategory (RTL)");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot_withScroll("320 GS_ProductLists_GridListView_Var1 - QuickView (RTL)");
        stCategoryPage.clickCloseQuickView();
        stHomePage.selectLanguage_RU();
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot_withScroll("325 GS_ProductLists_GridListView_Var1 - QuickView");
        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductLists_GridListView_Var1 passed successfully!");
    }
}