import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.DriverProvider;
import taras.workPages.AdminPanel;
import taras.workPages.CategoryPage;
import taras.workPages.MainPage;
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
        AdminPanel adminPanel = new AdminPanel();
        //Работаем с настройками темы
        adminPanel.navigateToAddonsPage();
        adminPanel.clickThemeSectionsOnManagementPage();
        adminPanel.navigateToThemeSettings();
        adminPanel.clickTabProductLists();
        WebElement checkboxProductRating = adminPanel.settingProductRating;
        if(checkboxProductRating.isSelected()){
            adminPanel.settingProductRating.click();
        }
        WebElement checkboxSettingCommonValueOfProductRating = adminPanel.settingCommonValueOfProductRating;
        if(!checkboxSettingCommonValueOfProductRating.isSelected()){
            adminPanel.settingCommonValueOfProductRating.click();
        }
        adminPanel.clickAndTypeSettingMinHeightForProductCell("300");
        adminPanel.clickAndTypeSettingProductIconWidth("400");
        adminPanel.clickAndTypeSettingProductIconHeight("200");
        WebElement checkboxSettingShowProductCode = adminPanel.settingShowProductCode;
        if(checkboxSettingShowProductCode.isSelected()){
            adminPanel.settingShowProductCode.click();
        }
        WebElement checkboxSettingDisplayAvailabilityStatus = adminPanel.settingDisplayAvailabilityStatus;
        if(checkboxSettingDisplayAvailabilityStatus.isSelected()){
            adminPanel.settingDisplayAvailabilityStatus.click();
        }
        WebElement checkboxSettingShowQuantityChanger = adminPanel.settingShowQuantityChanger;
        if(checkboxSettingShowQuantityChanger.isSelected()){
            adminPanel.settingShowQuantityChanger.click();
        }
        adminPanel.selectSettingShowAddToCartButton("icon");
        adminPanel.selectSettingAdditionalProductInformation("features_and_variations");
        WebElement checkboxSettingShowAdditionalInformationOnHover = adminPanel.settingShowAdditionalInformationOnHover;
        if(!checkboxSettingShowAdditionalInformationOnHover.isSelected()){
            adminPanel.settingShowAdditionalInformationOnHover.click();
        }
        WebElement checkboxSettingShowBrandLogo = adminPanel.settingShowBrandLogo;
        if(!checkboxSettingShowBrandLogo.isSelected()){
            adminPanel.settingShowBrandLogo.click();
        }
        WebElement checkboxSettingShowYouSave = adminPanel.settingShowYouSave;
        if(!checkboxSettingShowYouSave.isSelected()){
            adminPanel.settingShowYouSave.click();
        }
        adminPanel.selectSettingSwitchProductImageWhenHovering("points");
        adminPanel.clickSaveButtonOfSettings();

        //Работаем с витриной
        MainPage mainPage = adminPanel.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cookie-notice")));
        mainPage.closeCookieNoticeOnStorefront();
        //Блок товаров на главной странице
        mainPage.scrollToBlockWithProducts();
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
        mainPage.changeLanguageByIndex(1);
        makePause();
        mainPage.scrollToBlockWithProducts();
        takeScreenShot("411 GridListView_BlockWithProductsRTL");
        mainPage.changeLanguageByIndex(2);

        //Категория "Мужская одежда"
        mainPage.navigateToMenuMenCloth();
        //Проверяем, что дополнительная информация отображается при наведении
        Assert.assertTrue(sizeOfAdditionalInformationOnHover > 1, "Buttons are displayed without mouse hover on the product block!");
        //Проверяем, что логотип присутствует
        Assert.assertTrue(sizeOfLogo > 2, "There is no product logo on the product block!");
        //Проверяем, что текст "Вы экономите" присутствует
        Assert.assertTrue(sizeOfYouSave > 1, "There is no text 'You save' on the product block!");
        //Проверяем, что переключатель изображений товара присутсттвует и он в виде полосок
        Assert.assertTrue(sizeOfSwitchWithStripes > 1, "Switch is not with stripes or there is no Switch at all on the product block!");
        CategoryPage categoryPage = new CategoryPage();
        categoryPage.hoverToMenClothProduct();
        takeScreenShot("420 GridListView_MenClothCategory");
        mainPage.changeLanguageByIndex(1);
        makePause();
        categoryPage.hoverToMenClothProduct();
        takeScreenShot("421 GridListView_MenClothCategoryRTL");
        categoryPage.clickQuickViewOfMenClothProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("430 GridListView_QuickViewRTL");
        categoryPage.clickCloseQuickView();
        mainPage.changeLanguageByIndex(2);
        categoryPage.hoverToMenClothProduct();
        categoryPage.clickQuickViewOfMenClothProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("431 GridListView_QuickView");
        categoryPage.clickCloseQuickView();
    }
}