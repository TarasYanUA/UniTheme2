import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.adminPanel.ThemeSettings;
import taras.constants.DriverProvider;
import taras.adminPanel.CsCartSettings;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import java.io.IOException;
import java.time.Duration;

/*
Проверка следующих настроек:
1) UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Отображать пустые звёзды рейтинга товара -- нет
Отображать общее значение рейтинга товара -- да

Минимальная высота для ячейки товара (по умолчанию пусто) -- 300
Ширина иконки товара (по умолчанию 200) --	400
Высота иконки товара (по умолчанию 200) --	200

Отображать код товара -- нет
Отображать статус наличия -- нет
Отображать модификатор количества -- нет
Отображать кнопку "Купить" -- Только Иконка корзины (упрощенный вариант)
Дополнительная информация о товаре -- Список характеристик и вариаций
Отображать дополнительную информацию при наведении -- да
Отображать логотип бренда -- да
Отображать "Вы экономите" -- да
Переключать изображение товара при движении мышки -- с точками
*/

public class GeneralSettings_ProductLists_GridListView_Var2 extends TestRunner {
    @Test
    public void checkGeneralSettings_ProductLists_GridListView_Var2() throws IOException {
        CsCartSettings csCartSettings = new CsCartSettings();
        ThemeSettings themeSettings = new ThemeSettings();
        //Работаем с настройками темы
        csCartSettings.navigateToAddonsPage();
        csCartSettings.clickThemeSectionsOnManagementPage();
        csCartSettings.navigateToThemeSettings();
        themeSettings.clickTabProductLists();
        WebElement checkboxProductRating = themeSettings.settingProductRating;
        if(checkboxProductRating.isSelected()){
            themeSettings.settingProductRating.click();
        }
        WebElement checkboxSettingCommonValueOfProductRating = themeSettings.settingCommonValueOfProductRating;
        if(!checkboxSettingCommonValueOfProductRating.isSelected()){
            themeSettings.settingCommonValueOfProductRating.click();
        }
        themeSettings.clickAndTypeSettingMinHeightForProductCell("300");
        themeSettings.clickAndTypeSettingProductIconWidth("400");
        themeSettings.clickAndTypeSettingProductIconHeight("200");
        WebElement checkboxSettingShowProductCode = themeSettings.settingShowProductCode;
        if(checkboxSettingShowProductCode.isSelected()){
            themeSettings.settingShowProductCode.click();
        }
        WebElement checkboxSettingDisplayAvailabilityStatus = themeSettings.settingDisplayAvailabilityStatus;
        if(checkboxSettingDisplayAvailabilityStatus.isSelected()){
            themeSettings.settingDisplayAvailabilityStatus.click();
        }
        WebElement checkboxSettingShowQuantityChanger = themeSettings.settingShowQuantityChanger;
        if(checkboxSettingShowQuantityChanger.isSelected()){
            themeSettings.settingShowQuantityChanger.click();
        }
        themeSettings.selectSettingShowAddToCartButton("icon");
        themeSettings.selectSettingAdditionalProductInformation("features_and_variations");
        WebElement checkboxSettingShowAdditionalInformationOnHover = themeSettings.settingShowAdditionalInformationOnHover;
        if(!checkboxSettingShowAdditionalInformationOnHover.isSelected()){
            themeSettings.settingShowAdditionalInformationOnHover.click();
        }
        WebElement checkboxSettingShowBrandLogo = themeSettings.settingShowBrandLogo;
        if(!checkboxSettingShowBrandLogo.isSelected()){
            themeSettings.settingShowBrandLogo.click();
        }
        WebElement checkboxSettingShowYouSave = themeSettings.settingShowYouSave;
        if(!checkboxSettingShowYouSave.isSelected()){
            themeSettings.settingShowYouSave.click();
        }
        themeSettings.selectSettingSwitchProductImageWhenHovering("points");
        csCartSettings.clickSaveButtonOfSettings();

        //Работаем с витриной
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.cookie-notice")));
        stHomePage.closeCookieNoticeOnStorefront();
        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        //Проверяем, что дополнительная информация отображается при наведении
        int sizeOfAdditionalInformationOnHover = DriverProvider.getDriver().findElements(By.cssSelector("div[class='ut2-gl__body content-on-hover']")).size();
        Assert.assertTrue(sizeOfAdditionalInformationOnHover > 1, "Buttons are displayed without mouse hover on the product block!");
        //Проверяем, что логотип присутствует
        int sizeOfLogo = DriverProvider.getDriver().findElements(By.cssSelector(".brand-img")).size();
        Assert.assertTrue(sizeOfLogo > 2, "There is no product logo on the product block!");
        //Проверяем, что текст "Вы экономите" присутствует
        int sizeOfYouSave = DriverProvider.getDriver().findElements(By.cssSelector("span[class='ty-list-price ty-save-price ty-nowrap']")).size();
        Assert.assertTrue(sizeOfYouSave > 1, "There is no text 'You save' on the product block!");
        //Проверяем, что переключатель изображений товара присутсттвует и он в виде точек
        int sizeOfSwitchWithStripes = DriverProvider.getDriver().findElements(By.cssSelector("div[class='cm-ab-hover-gallery abt__ut2_hover_gallery points']")).size();
        Assert.assertTrue(sizeOfSwitchWithStripes > 1, "Switch is not with points or there is no Switch at all on the product block!");
        takeScreenShot("410 GridListView_BlockWithProducts");
        stHomePage.changeLanguageByIndex(2);
        makePause();
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("411 GridListView_BlockWithProductsRTL");
        stHomePage.changeLanguageByIndex(1);

        //Категория "Мужская одежда"
        stHomePage.navigateToMenuMenCloth();
        //Проверяем, что дополнительная информация отображается при наведении
        Assert.assertTrue(sizeOfAdditionalInformationOnHover > 1, "Buttons are displayed without mouse hover on the product block!");
        //Проверяем, что логотип присутствует
        Assert.assertTrue(sizeOfLogo > 2, "There is no product logo on the product block!");
        //Проверяем, что текст "Вы экономите" присутствует
        Assert.assertTrue(sizeOfYouSave > 1, "There is no text 'You save' on the product block!");
        //Проверяем, что переключатель изображений товара присутсттвует и он в виде полосок
        Assert.assertTrue(sizeOfSwitchWithStripes > 1, "Switch is not with stripes or there is no Switch at all on the product block!");
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.hoverToMenClothProduct();
        takeScreenShot("420 GridListView_MenClothCategory");
        stHomePage.changeLanguageByIndex(2);
        makePause();
        stCategoryPage.hoverToMenClothProduct();
        takeScreenShot("421 GridListView_MenClothCategoryRTL");
        stCategoryPage.clickQuickViewOfMenClothProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("430 GridListView_QuickViewRTL");
        stCategoryPage.clickCloseQuickView();
        stHomePage.changeLanguageByIndex(1);
        stCategoryPage.hoverToMenClothProduct();
        stCategoryPage.clickQuickViewOfMenClothProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("431 GridListView_QuickView");
        stCategoryPage.clickCloseQuickView();
    }
}