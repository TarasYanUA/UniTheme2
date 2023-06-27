import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.ThemeSettings_ProductLists;
import taras.constants.DriverProvider;
import taras.adminPanel.CsCartSettings;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import java.time.Duration;

/*
Проверка настроек UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Отображать пустые звёзды рейтинга товара -- да
Отображать общее значение рейтинга товара -- нет

Ширина иконки товара (по умолчанию 240) --	200
Высота иконки товара (по умолчанию 290) --	200

Отображать код товара -- да
Отображать статус наличия -- да
Отображать модификатор количества -- да
Отображать кнопку "Купить" -- Иконка корзины и текст
Дополнительная информация о товаре -- Краткое описание и характеристики
Отображать дополнительную информацию при наведении -- да
Отображать логотип бренда -- да
Отображать "Вы экономите" -- да
Показывать галерею мини-иконок товара в товарном списке --	Не отображать
Переключать изображение товара при движении мышки -- с полосками
*/

public class GeneralSettings_ProductLists_GridListView_Var1 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductLists_GridListView_Var1() {
        CsCartSettings csCartSettings = new CsCartSettings();
        ThemeSettings_ProductLists themeSettingsProductLists = new ThemeSettings_ProductLists();
        //Работаем с настройками характеристики Бренд
        csCartSettings.hoverToProductMenu();
        csCartSettings.navigateToSection_Features();
        csCartSettings.clickFeatureBrand();
        WebElement checkboxShowInProductList = csCartSettings.showInProductList;
        if (!checkboxShowInProductList.isSelected()) {
            checkboxShowInProductList.click();
        }
        csCartSettings.clickSaveButtonOfSettings();

        //Работаем с настройками темы
        csCartSettings.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.clickTabProductLists();
        WebElement checkboxProductRating = themeSettingsProductLists.settingProductRating;
        if (!checkboxProductRating.isSelected()) {
            checkboxProductRating.click();
        }
        WebElement checkboxSettingCommonValueOfProductRating = themeSettingsProductLists.settingCommonValueOfProductRating;
        if (checkboxSettingCommonValueOfProductRating.isSelected()) {
            checkboxSettingCommonValueOfProductRating.click();
        }
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
        WebElement checkboxSettingShowYouSave = themeSettingsProductLists.settingShowYouSave;
        if (!checkboxSettingShowYouSave.isSelected()) {
            checkboxSettingShowYouSave.click();
        }
        themeSettingsProductLists.selectSetting_ShowGalleryOfMiniIcons("N");
        themeSettingsProductLists.selectSettingSwitchProductImageWhenHovering("lines");
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_GridListView_Var1")
    public void checkProductLists_GridListView_Var1() {
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.cookie.click();
        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        SoftAssert softAssert = new SoftAssert();
        //Проверяем, что код товара присутствует
        int sizeOfProductCodes = DriverProvider.getDriver().findElements(By.cssSelector(".ty-control-group__label")).size();
        softAssert.assertTrue(sizeOfProductCodes > 1, "There is no product code on the product block!");
        //Проверяем, что статус наличия присутствует
        int sizeOfAvailabilityStatus = DriverProvider.getDriver().findElements(By.cssSelector(".ty-qty-in-stock.ty-control-group__item")).size();
        softAssert.assertTrue(sizeOfAvailabilityStatus > 1, "There is no availability status on the product block!");
        //Проверяем, что модификатор количества присутствует
        int sizeOfQuantityChanger = DriverProvider.getDriver().findElements(By.cssSelector("div[class='ty-center ty-value-changer cm-value-changer']")).size();
        softAssert.assertTrue(sizeOfQuantityChanger > 1, "There is no quantity Changer on the product block!");
        //Проверяем, что дополнительная информация отображается при наведении
        int sizeOfAdditionalInformationOnHover = DriverProvider.getDriver().findElements(By.cssSelector("div[class='ut2-gl__body content-on-hover']")).size();
        softAssert.assertTrue(sizeOfAdditionalInformationOnHover > 1, "Buttons are displayed without mouse hover on the product block!");
        //Проверяем, что логотип присутствует
        int sizeOfLogo = DriverProvider.getDriver().findElements(By.cssSelector(".brand-img")).size();
        softAssert.assertTrue(sizeOfLogo > 2, "There is no product logo on the product block!");
        //Проверяем, что текст "Вы экономите" присутствует
        int sizeOfYouSave = DriverProvider.getDriver().findElements(By.cssSelector("span[class='ty-list-price ty-save-price ty-nowrap']")).size();
        softAssert.assertTrue(sizeOfYouSave > 1, "There is no text 'You save' on the product block!");
        //Проверяем, что переключатель изображений товара присутсттвует и он в виде полосок
        int sizeOfSwitchWithStripes = DriverProvider.getDriver().findElements(By.cssSelector("div[class='cm-ab-hover-gallery abt__ut2_hover_gallery lines']")).size();
        softAssert.assertTrue(sizeOfSwitchWithStripes > 1, "Switch is not with stripes or there is no Switch at all on the product block!");
        takeScreenShot_withoutScroll("300 ProductLists_GridListView_Var1 - BlockWithProducts");
        stHomePage.selectLanguage_RTL();
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot_withoutScroll("305 ProductLists_GridListView_Var1 - BlockWithProducts (RTL)");
        stHomePage.selectLanguage_RU();

        //Категория "Телефоны"
        stHomePage.navigateToMenuPhones();
        //Проверяем, что код товара присутствует
        softAssert.assertTrue(sizeOfProductCodes > 1, "There is no product code on the category page!");
        //Проверяем, что статус наличия присутствует
        softAssert.assertTrue(sizeOfAvailabilityStatus > 1, "There is no availability status on the category page!");
        //Проверяем, что модификатор количества присутствует
        softAssert.assertTrue(sizeOfQuantityChanger > 1, "There is no quantity Changer on the category page!");
        //Проверяем, что дополнительная информация отображается при наведении
        softAssert.assertTrue(sizeOfAdditionalInformationOnHover > 1, "Buttons are displayed without mouse hover on the category page!");
        //Проверяем, что логотип присутствует
        softAssert.assertTrue(sizeOfLogo > 2, "There is no product logo on the category page!");
        //Проверяем, что текст "Вы экономите" присутствует
        softAssert.assertTrue(sizeOfYouSave > 1, "There is no text 'You save' on the category page!");
        //Проверяем, что переключатель изображений товара присутсттвует и он в виде полосок
        softAssert.assertTrue(sizeOfSwitchWithStripes > 1, "Switch is not with stripes or there is no Switch at all on the category page!");
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("310 ProductLists_GridListView_Var1 - PhoneCategory");
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("315 ProductLists_GridListView_Var1 - PhoneCategory (RTL)");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("320 ProductLists_GridListView_Var1 - QuickView (RTL)");
        stCategoryPage.clickCloseQuickView();
        stHomePage.selectLanguage_RU();
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("325 ProductLists_GridListView_Var1 - QuickView");
        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductLists_GridListView_Var1 passed successfully!");
    }
}