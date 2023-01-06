import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.constants.DriverProvider;
import taras.adminPanel.AdmHomePage;
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
        AdmHomePage admHomePage = new AdmHomePage();
        //Работаем с настройками характеристики Бренд
        admHomePage.hoverToProductMenu();
        admHomePage.navigateMenuFeatures();
        admHomePage.clickFeatureBrand();
        WebElement checkboxShowInProductList = admHomePage.showInProductList;
        if(!checkboxShowInProductList.isSelected()){
            admHomePage.showInProductList.click();
        }
        admHomePage.clickSaveButtonOfSettings();
        //Работаем с настройками темы
        admHomePage.navigateToAddonsPage();
        admHomePage.clickThemeSectionsOnManagementPage();
        admHomePage.navigateToThemeSettings();
        admHomePage.clickTabProductLists();
        WebElement checkboxProductRating = admHomePage.settingProductRating;
        if(!checkboxProductRating.isSelected()){
            admHomePage.settingProductRating.click();
        }
        WebElement checkboxSettingCommonValueOfProductRating = admHomePage.settingCommonValueOfProductRating;
        if(checkboxSettingCommonValueOfProductRating.isSelected()){
            admHomePage.settingCommonValueOfProductRating.click();
        }
        admHomePage.clickAndTypeSettingMinHeightForProductCell("490");
        admHomePage.clickAndTypeSettingProductIconWidth("200");
        admHomePage.clickAndTypeSettingProductIconHeight("350");
        WebElement checkboxSettingShowProductCode = admHomePage.settingShowProductCode;
        if(!checkboxSettingShowProductCode.isSelected()){
            admHomePage.settingShowProductCode.click();
        }
        WebElement checkboxSettingDisplayAvailabilityStatus = admHomePage.settingDisplayAvailabilityStatus;
        if(!checkboxSettingDisplayAvailabilityStatus.isSelected()){
            admHomePage.settingDisplayAvailabilityStatus.click();
        }
        WebElement checkboxSettingShowQuantityChanger = admHomePage.settingShowQuantityChanger;
        if(!checkboxSettingShowQuantityChanger.isSelected()){
            admHomePage.settingShowQuantityChanger.click();
        }
        admHomePage.selectSettingShowAddToCartButton("icon_and_text");
        admHomePage.selectSettingAdditionalProductInformation("features_and_description");
        WebElement checkboxSettingShowAdditionalInformationOnHover = admHomePage.settingShowAdditionalInformationOnHover;
        if(!checkboxSettingShowAdditionalInformationOnHover.isSelected()){
            admHomePage.settingShowAdditionalInformationOnHover.click();
        }
        WebElement checkboxSettingShowBrandLogo = admHomePage.settingShowBrandLogo;
        if(!checkboxSettingShowBrandLogo.isSelected()){
            admHomePage.settingShowBrandLogo.click();
        }
        WebElement checkboxSettingShowYouSave = admHomePage.settingShowYouSave;
        if(!checkboxSettingShowYouSave.isSelected()){
            admHomePage.settingShowYouSave.click();
        }
        admHomePage.selectSettingSwitchProductImageWhenHovering("lines");
        admHomePage.clickSaveButtonOfSettings();

        //Работаем с витриной
        StHomePage stHomePage = admHomePage.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.cookie-notice")));
        stHomePage.closeCookieNoticeOnStorefront();
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
        stHomePage.changeLanguageByIndex(1);
        makePause();
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("311 GridListView_BlockWithProductsRTL");
        stHomePage.changeLanguageByIndex(2);

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
        stHomePage.changeLanguageByIndex(1);
        makePause();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("321 GridListView_PhoneCategoryRTL");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("330 GridListView_QuickViewRTL");
        stCategoryPage.clickCloseQuickView();
        stHomePage.changeLanguageByIndex(2);
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("331 GridListView_QuickView");
        stCategoryPage.clickCloseQuickView();
    }
}