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
1) "Настройки -- Внешний вид":
Включить быстрый просмотр -- y
Показывать мини-иконки в виде галереи -- n

2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Показывать галерею мини-иконок товара в товарном списке --	n
Переключать изображение товара при движении мышки -- с полосками (нужно для настройки выше)
Обесцвечивать товары, которых нет в наличии --	y
Формат отображения цен --	Вариант 4
Отображать цену вверху --	n
Отображать пустые звёзды рейтинга товара --	y
Отображать общее значение рейтинга товара -- n

Проверка проходит на следующих страницах:
Блок товаров на Главной странице + RTL
Женская одежда + RTL
Телефоны + RTL
Быстрый просмотр + RTL
Все шаблоны категории + RTL
*/

public class GeneralSettings_ProductLists_Var1 extends TestRunner {
    @Test
    public void checkGeneralSettingsOfProductLists_DefaultValues() throws IOException {
        AdminPanel adminPanel = new AdminPanel();
        //Работаем с CS-Cart настройками
        adminPanel.navigateToAppearanceSettingsOfCsCart();
        WebElement checkboxSettingQuickView = adminPanel.settingQuickView;
        if(!checkboxSettingQuickView.isSelected()){
            checkboxSettingQuickView.click();
        }
        WebElement checkboxThumbnailsGallery = adminPanel.settingThumbnailsGallery;
        if(checkboxThumbnailsGallery.isSelected()){
            checkboxThumbnailsGallery.click();
        }
        adminPanel.clickSaveButtonOfSettings();
        //Работаем с настройками темы (идут по умолчанию)
        adminPanel.navigateToAddonsPage();
        adminPanel.clickThemeSectionsOnManagementPage();
        adminPanel.navigateToThemeSettings();
        adminPanel.clickTabProductLists();
        WebElement checkboxMiniIconsGallery = adminPanel.settingMiniIconsGallery;
        if(checkboxMiniIconsGallery.isSelected()){
            checkboxMiniIconsGallery.click();
        }
        adminPanel.selectSettingSwitchProductImageWhenHoveringMousePointer("lines");
        WebElement checkboxOutOfStickProducts = adminPanel.settingOutOfStockProducts;
        if (!checkboxOutOfStickProducts.isSelected()){
            checkboxOutOfStickProducts.click();
        }
        adminPanel.selectSettingPriceDisplayFormat("row-mix");
        WebElement checkboxPriceAtTheTop = adminPanel.settingPriceAtTheTop;
        if(checkboxPriceAtTheTop.isSelected()){
            checkboxPriceAtTheTop.click();
        }
        WebElement checkboxProductRating = adminPanel.settingProductRating;
        if(!checkboxProductRating.isSelected()){
            checkboxProductRating.click();
        }
        WebElement checkboxSettingCommonValueOfProductRating = adminPanel.settingCommonValueOfProductRating;
        if(checkboxSettingCommonValueOfProductRating.isSelected()){
            checkboxProductRating.click();
        }
        adminPanel.clickSaveButtonOfSettings();

        //Работаем с витриной
        MainPage mainPage = adminPanel.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className(".cookie-notice")));
        mainPage.closeCookieNoticeOnStorefront();
        //Блок товаров на главной странице
        mainPage.scrollToBlockWithProducts();
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        Assert.assertTrue(DriverProvider.getDriver().findElement(By
                .cssSelector("div[class='ty-product-review-reviews-stars'][data-ca-product-review-reviews-stars-full=\"0\"]")).isEnabled());
        takeScreenShot("110 Var1_BlockWithProducts");
        mainPage.scrollToMenuApparel();
        mainPage.changeLanguageByIndex(1);
        makePause();
        mainPage.scrollToBlockWithProducts();
        takeScreenShot("111 Var1_BlockWithProductsRTL");
        mainPage.changeLanguageByIndex(2);
        //Категория "Женская одежда"
        mainPage.navigateToMenuWomanCloth();
        CategoryPage categoryPage = new CategoryPage();
        //Проверка, что на странице присутствует обесцвеченный товар.
        Assert.assertTrue(DriverProvider.getDriver().findElement(By.cssSelector(".ut2-gl__body.content-on-hover.decolorize")).isEnabled());
        categoryPage.hoverToClothProduct();
        takeScreenShot("120 Var1_WomanClothCategory");
        mainPage.changeLanguageByIndex(1);
        makePause();
        categoryPage.hoverToClothProduct();
        takeScreenShot("121 Var1_WomanClothCategoryRTL");
        mainPage.changeLanguageByIndex(2);
        //Категория "Телефоны"
        mainPage.navigateToMenuPhones();
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        Assert.assertTrue(DriverProvider.getDriver().findElement(By
                .cssSelector("div[class='ty-product-review-reviews-stars'][data-ca-product-review-reviews-stars-full=\"0\"]")).isEnabled());
        categoryPage.hoverToPhoneProduct();
        takeScreenShot("130 Var1_PhonesCategory");
        mainPage.changeLanguageByIndex(1);
        makePause();
        categoryPage.hoverToPhoneProduct();
        takeScreenShot("131 Var1_PhonesCategoryRTL");
        mainPage.changeLanguageByIndex(2);
        //Быстрый просмотр в категории "Телефоны"
        categoryPage.hoverToPhoneProduct();
        categoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("140 Var1_QuickView");
        categoryPage.clickCloseQuickView();
        mainPage.changeLanguageByIndex(1);
        categoryPage.hoverToPhoneProduct();
        categoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("141 Var1_QuickViewRTL");
        categoryPage.clickCloseQuickView();
        //Других два шаблона страницы категории
        categoryPage.clickListWithoutOptions_ProductListView();
        makePause();
        takeScreenShot("150 Var1_Category_ListWithoutOptionsRTL");
        mainPage.changeLanguageByIndex(2);
        makePause();
        takeScreenShot("151 Var1_Category_ListWithoutOptions");
        categoryPage.clickCompactList_ProductListView();
        makePause();
        takeScreenShot("160 Var1_Category_CompactList_ProductListView");
        mainPage.changeLanguageByIndex(1);
        makePause();
        takeScreenShot("161 Var1_Category_CompactList_ProductListViewRTL");
    }
}