import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.adminPanel.AdmHomePage;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import java.io.IOException;
import java.time.Duration;
import static taras.constants.DriverProvider.getDriver;

/*
Проверка следующих настроек:
1) "Настройки -- Внешний вид":
Включить быстрый просмотр -- y
Показывать мини-иконки в виде галереи -- n

2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Показывать галерею мини-иконок товара в товарном списке --	n
Переключать изображение товара при движении мышки -- с полосками
Обесцвечивать товары, которых нет в наличии --	y
Формат отображения цен --	Вариант 4
Отображать цену вверху --	n
Отображать пустые звёзды рейтинга товара --	y
Отображать общее значение рейтинга товара -- n

Проверка проходит на следующих страницах:
- Блок товаров на Главной странице + RTL
- Женская одежда + RTL
- Телефоны + RTL
- Быстрый просмотр + RTL
- Все шаблоны категории + RTL
*/

public class GeneralSettings_ProductLists_Var1 extends TestRunner {
    @Test
    public void checkGeneralSettingsOfProductLists_DefaultValues() throws IOException {
        AdmHomePage admHomePage = new AdmHomePage();
        //Работаем с CS-Cart настройками
        admHomePage.navigateToAppearanceSettingsOfCsCart();
        WebElement checkboxThumbnailsGallery = admHomePage.settingThumbnailsGallery;
        if(checkboxThumbnailsGallery.isSelected()){
            admHomePage.settingThumbnailsGallery.click();
        }
        WebElement checkboxSettingQuickView = admHomePage.settingQuickView;
        if(!checkboxSettingQuickView.isSelected()){
            admHomePage.settingQuickView.click();
        }
        admHomePage.clickSaveButtonOfSettings();
        //Работаем с настройками темы (идут по умолчанию)
        admHomePage.navigateToAddonsPage();
        admHomePage.clickThemeSectionsOnManagementPage();
        admHomePage.navigateToThemeSettings();
        admHomePage.clickTabProductLists();
        WebElement checkboxMiniIconsGallery = admHomePage.settingMiniIconsGallery;
        if(checkboxMiniIconsGallery.isSelected()){
            admHomePage.settingMiniIconsGallery.click();
        }
        WebElement checkboxOutOfStickProducts = admHomePage.settingOutOfStockProducts;
        if (!checkboxOutOfStickProducts.isSelected()){
            admHomePage.settingOutOfStockProducts.click();
        }
        admHomePage.selectSettingPriceDisplayFormat("row-mix");
        WebElement checkboxPriceAtTheTop = admHomePage.settingPriceAtTheTop;
        if(checkboxPriceAtTheTop.isSelected()){
            admHomePage.settingPriceAtTheTop.click();
        }
        WebElement checkboxProductRating = admHomePage.settingProductRating;
        if(!checkboxProductRating.isSelected()){
            admHomePage.settingProductRating.click();
        }
        WebElement checkboxSettingCommonValueOfProductRating = admHomePage.settingCommonValueOfProductRating;
        if(checkboxSettingCommonValueOfProductRating.isSelected()){
            admHomePage.settingCommonValueOfProductRating.click();
        }
        admHomePage.selectSettingSwitchProductImageWhenHoveringMousePointer("lines");
        admHomePage.clickSaveButtonOfSettings();

        //Работаем с витриной
        StHomePage stHomePage = admHomePage.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.cookie-notice")));
        stHomePage.closeCookieNoticeOnStorefront();
        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        Assert.assertTrue(getDriver().findElement(By
                .cssSelector("div[class*='ty-product-review-reviews-stars'][data-ca-product-review-reviews-stars-full=\"0\"]")).isEnabled());
        takeScreenShot("110 Var1_BlockWithProducts");
        stHomePage.scrollToMenuApparel();
        stHomePage.changeLanguageByIndex(1);
        makePause();
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("111 Var1_BlockWithProductsRTL");
        stHomePage.changeLanguageByIndex(2);
        //Категория "Женская одежда"
        stHomePage.navigateToMenuWomanCloth();
        StCategoryPage stCategoryPage = new StCategoryPage();
        //Проверка, что на странице присутствует обесцвеченный товар.
        Assert.assertTrue(getDriver().findElement(By.cssSelector(".ut2-gl__body.content-on-hover.decolorize")).isEnabled());
        stCategoryPage.hoverToClothProduct();
        takeScreenShot("120 Var1_WomanClothCategory");
        stHomePage.changeLanguageByIndex(1);
        makePause();
        stCategoryPage.hoverToClothProduct();
        takeScreenShot("121 Var1_WomanClothCategoryRTL");
        stHomePage.changeLanguageByIndex(2);
        //Категория "Телефоны"
        stHomePage.navigateToMenuPhones();
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        Assert.assertTrue(getDriver().findElement(By
                .cssSelector("div[class*='ty-product-review-reviews-stars'][data-ca-product-review-reviews-stars-full=\"0\"]")).isEnabled());
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("130 Var1_PhonesCategory");
        stHomePage.changeLanguageByIndex(1);
        makePause();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("131 Var1_PhonesCategoryRTL");
        stHomePage.changeLanguageByIndex(2);
        //Быстрый просмотр в категории "Телефоны"
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("140 Var1_QuickView");
        stCategoryPage.clickCloseQuickView();
        stHomePage.changeLanguageByIndex(1);
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("141 Var1_QuickViewRTL");
        stCategoryPage.clickCloseQuickView();
        //Других два шаблона страницы категории
        stCategoryPage.clickListWithoutOptions_ProductListView();
        makePause();
        takeScreenShot("150 Var1_Category_ListWithoutOptionsRTL");
        stHomePage.changeLanguageByIndex(2);
        makePause();
        takeScreenShot("151 Var1_Category_ListWithoutOptions");
        stCategoryPage.clickCompactList_ProductListView();
        makePause();
        takeScreenShot("160 Var1_Category_CompactList_ProductListView");
        stHomePage.changeLanguageByIndex(1);
        makePause();
        takeScreenShot("161 Var1_Category_CompactList_ProductListViewRTL");
    }
}