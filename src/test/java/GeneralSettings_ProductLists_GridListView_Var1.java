import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.adminPanel.ThemeSettings_ProductLists;
import taras.constants.DriverProvider;
import taras.adminPanel.CsCartSettings;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import java.io.IOException;
import java.time.Duration;

/*
Проверка следующих настроек:
1) UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Отображать пустые звёзды рейтинга товара -- да
Отображать общее значение рейтинга товара -- нет

Минимальная высота для ячейки товара (по умолчанию пусто) -- 490
Ширина иконки товара (по умолчанию 200) --	200
Высота иконки товара (по умолчанию 200) --	350

Отображать код товара -- да
Отображать статус наличия -- да
Отображать модификатор количества -- да
Отображать кнопку "Купить" -- Иконка корзины и текст
Дополнительная информация о товаре -- Краткое описание и характеристики
Отображать дополнительную информацию при наведении -- да
Отображать логотип бренда -- да
Отображать "Вы экономите" -- да
Переключать изображение товара при движении мышки -- с полосками
*/

public class GeneralSettings_ProductLists_GridListView_Var1 extends TestRunner {
    @Test
    public void checkGeneralSettings_ProductLists_GridListView_Var1() throws IOException {
        CsCartSettings csCartSettings = new CsCartSettings();
        ThemeSettings_ProductLists themeSettingsProductLists = new ThemeSettings_ProductLists();
        //Работаем с настройками характеристики Бренд
        csCartSettings.hoverToProductMenu();
        csCartSettings.navigateMenuFeatures();
        csCartSettings.clickFeatureBrand();
        WebElement checkboxShowInProductList = csCartSettings.showInProductList;
        if(!checkboxShowInProductList.isSelected()){
            csCartSettings.showInProductList.click();
        }
        csCartSettings.clickSaveButtonOfSettings();
        //Работаем с настройками темы
        csCartSettings.navigateToAddonsPage();
        csCartSettings.clickThemeSectionsOnManagementPage();
        csCartSettings.navigateToThemeSettings();
        themeSettingsProductLists.clickTabProductLists();
        WebElement checkboxProductRating = themeSettingsProductLists.settingProductRating;
        if(!checkboxProductRating.isSelected()){
            themeSettingsProductLists.settingProductRating.click();
        }
        WebElement checkboxSettingCommonValueOfProductRating = themeSettingsProductLists.settingCommonValueOfProductRating;
        if(checkboxSettingCommonValueOfProductRating.isSelected()){
            themeSettingsProductLists.settingCommonValueOfProductRating.click();
        }
        themeSettingsProductLists.clickAndTypeSettingMinHeightForProductCell("490");
        themeSettingsProductLists.clickAndTypeSettingProductIconWidth("200");
        themeSettingsProductLists.clickAndTypeSettingProductIconHeight("350");
        WebElement checkboxSettingShowProductCode = themeSettingsProductLists.settingShowProductCode;
        if(!checkboxSettingShowProductCode.isSelected()){
            themeSettingsProductLists.settingShowProductCode.click();
        }
        WebElement checkboxSettingDisplayAvailabilityStatus = themeSettingsProductLists.settingDisplayAvailabilityStatus;
        if(!checkboxSettingDisplayAvailabilityStatus.isSelected()){
            themeSettingsProductLists.settingDisplayAvailabilityStatus.click();
        }
        WebElement checkboxSettingShowQuantityChanger = themeSettingsProductLists.settingShowQuantityChanger;
        if(!checkboxSettingShowQuantityChanger.isSelected()){
            themeSettingsProductLists.settingShowQuantityChanger.click();
        }
        themeSettingsProductLists.selectSettingShowAddToCartButton("icon_and_text");
        themeSettingsProductLists.selectSettingAdditionalProductInformation("features_and_description");
        WebElement checkboxSettingShowAdditionalInformationOnHover = themeSettingsProductLists.settingShowAdditionalInformationOnHover;
        if(!checkboxSettingShowAdditionalInformationOnHover.isSelected()){
            themeSettingsProductLists.settingShowAdditionalInformationOnHover.click();
        }
        WebElement checkboxSettingShowBrandLogo = themeSettingsProductLists.settingShowBrandLogo;
        if(!checkboxSettingShowBrandLogo.isSelected()){
            themeSettingsProductLists.settingShowBrandLogo.click();
        }
        WebElement checkboxSettingShowYouSave = themeSettingsProductLists.settingShowYouSave;
        if(!checkboxSettingShowYouSave.isSelected()){
            themeSettingsProductLists.settingShowYouSave.click();
        }
        themeSettingsProductLists.selectSettingSwitchProductImageWhenHovering("lines");
        csCartSettings.clickSaveButtonOfSettings();

        //Работаем с витриной
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        //Проверяем, что код товара присутствует
        int sizeOfProductCodes = DriverProvider.getDriver().findElements(By.cssSelector(".ty-control-group__label")).size();
        Assert.assertTrue(sizeOfProductCodes > 1, "There is no product code on the product block!");
        //Проверяем, что статус наличия присутствует
        int sizeOfAvailabilityStatus = DriverProvider.getDriver().findElements(By.cssSelector(".ty-qty-in-stock.ty-control-group__item")).size();
        Assert.assertTrue(sizeOfAvailabilityStatus > 1, "There is no availability status on the product block!");
        //Проверяем, что модификатор количества присутствует
        int sizeOfQuantityChanger = DriverProvider.getDriver().findElements(By.cssSelector("div[class='ty-center ty-value-changer cm-value-changer']")).size();
        Assert.assertTrue(sizeOfQuantityChanger > 1, "There is no quantity Changer on the product block!");
        //Проверяем, что дополнительная информация отображается при наведении
        int sizeOfAdditionalInformationOnHover = DriverProvider.getDriver().findElements(By.cssSelector("div[class='ut2-gl__body content-on-hover']")).size();
        Assert.assertTrue(sizeOfAdditionalInformationOnHover > 1, "Buttons are displayed without mouse hover on the product block!");
        //Проверяем, что логотип присутствует
        int sizeOfLogo = DriverProvider.getDriver().findElements(By.cssSelector(".brand-img")).size();
        Assert.assertTrue(sizeOfLogo > 2, "There is no product logo on the product block!");
        //Проверяем, что текст "Вы экономите" присутствует
        int sizeOfYouSave = DriverProvider.getDriver().findElements(By.cssSelector("span[class='ty-list-price ty-save-price ty-nowrap']")).size();
        Assert.assertTrue(sizeOfYouSave > 1, "There is no text 'You save' on the product block!");
        //Проверяем, что переключатель изображений товара присутсттвует и он в виде полосок
        int sizeOfSwitchWithStripes = DriverProvider.getDriver().findElements(By.cssSelector("div[class='cm-ab-hover-gallery abt__ut2_hover_gallery lines']")).size();
        Assert.assertTrue(sizeOfSwitchWithStripes > 1, "Switch is not with stripes or there is no Switch at all on the product block!");
        takeScreenShot("310 GridListView_BlockWithProducts");
        stHomePage.selectLanguage_RTL();
        makePause();
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("311 GridListView_BlockWithProductsRTL");
        stHomePage.selectLanguage_RU();

        //Категория "Телефоны"
        stHomePage.navigateToMenuPhones();
        //Проверяем, что код товара присутствует
        Assert.assertTrue(sizeOfProductCodes > 1, "There is no product code on the product block!");
        //Проверяем, что статус наличия присутствует
        Assert.assertTrue(sizeOfAvailabilityStatus > 1, "There is no availability status on the product block!");
        //Проверяем, что модификатор количества присутствует
        Assert.assertTrue(sizeOfQuantityChanger > 1, "There is no quantity Changer on the product block!");
        //Проверяем, что дополнительная информация отображается при наведении
        Assert.assertTrue(sizeOfAdditionalInformationOnHover > 1, "Buttons are displayed without mouse hover on the product block!");
        //Проверяем, что логотип присутствует
        Assert.assertTrue(sizeOfLogo > 2, "There is no product logo on the product block!");
        //Проверяем, что текст "Вы экономите" присутствует
        Assert.assertTrue(sizeOfYouSave > 1, "There is no text 'You save' on the product block!");
        //Проверяем, что переключатель изображений товара присутсттвует и он в виде полосок
        Assert.assertTrue(sizeOfSwitchWithStripes > 1, "Switch is not with stripes or there is no Switch at all on the product block!");
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("320 GridListView_PhoneCategory");
        stHomePage.selectLanguage_RTL();
        makePause();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("321 GridListView_PhoneCategoryRTL");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("330 GridListView_QuickViewRTL");
        stCategoryPage.clickCloseQuickView();
        stHomePage.selectLanguage_RU();
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("331 GridListView_QuickView");
        stCategoryPage.clickCloseQuickView();
    }
}