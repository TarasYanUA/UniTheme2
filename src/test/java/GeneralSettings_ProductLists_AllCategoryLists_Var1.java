import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ThemeSettings_ProductLists;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import java.io.IOException;
import java.time.Duration;
import static taras.constants.DriverProvider.getDriver;

/* ссылка на тест-кейс: https://docs.google.com/spreadsheets/d/1BU7MqO4tilXIiXQ7w73f9TRbi0dmko-Nmg6_CWQJr0E/edit#gid=0

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
Отображать статусы для кнопок "Купить" -- Иконка
Отображать статусы для кнопок "Добавить в избранное", "Добавить в список сравнения" -- n
Отображать кнопку "Добавить в избранное" -- y
Отображать кнопку "Добавить в список сравнения" -- y
Отображать кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" при наведении на ячейку товара -- y

Проверка проходит на следующих страницах:
- Блок товаров на Главной странице + RTL
- Женская одежда + RTL
- Телефоны + RTL
- Быстрый просмотр + RTL
- Все шаблоны категории + RTL
*/

public class GeneralSettings_ProductLists_AllCategoryLists_Var1 extends TestRunner {
    @Test
    public void checkGeneralSettingsOfProductLists_DefaultValues() throws IOException {
        CsCartSettings csCartSettings = new CsCartSettings();
        //Работаем с CS-Cart настройками
        csCartSettings.navigateToAppearanceSettingsOfCsCart();
        WebElement checkboxThumbnailsGallery = csCartSettings.settingThumbnailsGallery;
        if(checkboxThumbnailsGallery.isSelected()){
            checkboxThumbnailsGallery.click();
        }
        WebElement checkboxSettingQuickView = csCartSettings.settingQuickView;
        if(!checkboxSettingQuickView.isSelected()){
            checkboxSettingQuickView.click();
        }
        csCartSettings.clickSaveButtonOfSettings();
        //Работаем с настройками темы (в основном идут по умолчанию)
        csCartSettings.navigateToAddonsPage();
        csCartSettings.clickThemeSectionsOnManagementPage();
        ThemeSettings_ProductLists themeSettingsProductLists = csCartSettings.navigateToThemeSettings();
        themeSettingsProductLists.clickTabProductLists();
        WebElement checkboxMiniIconsGallery = themeSettingsProductLists.settingMiniIconsGallery;
        if(checkboxMiniIconsGallery.isSelected()){
            themeSettingsProductLists.settingMiniIconsGallery.click();
        }
        WebElement checkboxOutOfStickProducts = themeSettingsProductLists.settingOutOfStockProducts;
        if (!checkboxOutOfStickProducts.isSelected()){
            themeSettingsProductLists.settingOutOfStockProducts.click();
        }
        themeSettingsProductLists.selectSettingPriceDisplayFormat("row-mix");
        WebElement checkboxPriceAtTheTop = themeSettingsProductLists.settingPriceAtTheTop;
        if(checkboxPriceAtTheTop.isSelected()){
            themeSettingsProductLists.settingPriceAtTheTop.click();
        }
        WebElement checkboxProductRating = themeSettingsProductLists.settingProductRating;
        if(!checkboxProductRating.isSelected()){
            themeSettingsProductLists.settingProductRating.click();
        }
        WebElement checkboxSettingCommonValueOfProductRating = themeSettingsProductLists.settingCommonValueOfProductRating;
        if(checkboxSettingCommonValueOfProductRating.isSelected()){
            themeSettingsProductLists.settingCommonValueOfProductRating.click();
        }
        themeSettingsProductLists.selectSettingDisplayCartStatus("check-icon");
        WebElement checkboxSettingDisplayStatusesForButtons = themeSettingsProductLists.settingDisplayStatusesForButtons;
        if(checkboxSettingDisplayStatusesForButtons.isSelected()){
            checkboxSettingDisplayStatusesForButtons.click();
        }
        WebElement checkboxSettingDisplayButtonComparisonList = themeSettingsProductLists.settingDisplayButtonComparisonList;
        if(!checkboxSettingDisplayButtonComparisonList.isSelected()){
            checkboxSettingDisplayButtonComparisonList.click();
        }
        WebElement checkboxSettingDisplayButtonWishList = themeSettingsProductLists.settingDisplayButtonWishList;
        if(!checkboxSettingDisplayButtonWishList.isSelected()){
            checkboxSettingDisplayButtonWishList.click();
        }
        WebElement checkboxSettingDisplayButtonsWhenHoveringMouse = themeSettingsProductLists.settingDisplayButtonsWhenHoveringMouse;
        if(!checkboxSettingDisplayButtonsWhenHoveringMouse.isSelected()){
            checkboxSettingDisplayButtonsWhenHoveringMouse.click();
        }
        themeSettingsProductLists.selectSettingSwitchProductImageWhenHoveringMousePointer("lines");
        csCartSettings.clickSaveButtonOfSettings();

        //Работаем с витриной
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        StCategoryPage stCategoryPage = new StCategoryPage();
        focusBrowserTab(1);
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.cookie-notice")));
        stHomePage.closeCookieNoticeOnStorefront();
        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        //Проверка, что у товаров переключатель изображений с полосками
        Assert.assertTrue(getDriver().findElement(By.cssSelector(".abt__ut2_hover_gallery.lines")).isEnabled(),
                "Gallery of product images is not with stripes in the product block!");
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        Assert.assertTrue(getDriver().findElement(By
                .cssSelector("div[class*='ty-product-review-reviews-stars'][data-ca-product-review-reviews-stars-full=\"0\"]")).isEnabled(),
                "There is no empty stars at a product!");
        //Проверка, что кнопка "Избранное" присутствует
        Assert.assertTrue(stCategoryPage.button_AddToWishList.isEnabled(),
                "There is no button 'Add to wish list' in the product block!");
        //Проверка, что кнопка "Сравнить" присутствует
        Assert.assertTrue(stCategoryPage.button_AddToComparisonList.isEnabled(),
                "There is no button 'Add to comparison list' in the product block!");
        //Проверка, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются при наведении на ячейку товара
        Assert.assertTrue(getDriver().findElement(By.cssSelector(".ut2-w-c-q__buttons.w_c_q-hover")).isEnabled(),
                "Buttons are not displayed when hovering over a product cell in the product block!");
        stHomePage.hoverToProductInProductBlock();
        takeScreenShot("110 Var1_BlockWithProducts");
        stHomePage.selectLanguage_RTL();
        makePause();
        stHomePage.scrollToBlockWithProducts();
        stHomePage.hoverToProductInProductBlock();
        takeScreenShot("111 Var1_BlockWithProductsRTL");
        stHomePage.selectLanguage_RU();
        //Категория "Женская одежда"
        stHomePage.navigateToMenuWomanCloth();
        //Проверка, что на странице присутствует обесцвеченный товар.
        Assert.assertTrue(getDriver().findElement(By.cssSelector(".ut2-gl__body.content-on-hover.decolorize")).isEnabled(),
                "There is no decolorized product on the category page!");
        //Проверка, что у товаров переключатель изображений с полосками
        Assert.assertTrue(getDriver().findElement(By.cssSelector(".abt__ut2_hover_gallery.lines")).isEnabled(),
                "Gallery of product images is not with stripes on the category page!");
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        Assert.assertTrue(getDriver().findElement(By
                        .cssSelector("div[class*='ty-product-review-reviews-stars'][data-ca-product-review-reviews-stars-full=\"0\"]")).isEnabled(),
                "There is no empty stars at a product!");
        //Проверка, что кнопка "Избранное" присутствует
        Assert.assertTrue(stCategoryPage.button_AddToWishList.isEnabled(),
                "There is no button 'Add to wish list' on the category page!");
        //Проверка, что кнопка "Сравнить" присутствует
        Assert.assertTrue(stCategoryPage.button_AddToComparisonList.isEnabled(),
                "There is no button 'Add to comparison list' on the category page!");
        //Проверка, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются при наведении на ячейку товара
        Assert.assertTrue(getDriver().findElement(By.cssSelector("div.ut2-w-c-q__buttons.w_c_q-hover")).isEnabled());
        stCategoryPage.hoverToClothProduct();
        takeScreenShot("120 Var1_WomanClothCategory");
        stHomePage.selectLanguage_RTL();
        makePause();
        stCategoryPage.hoverToClothProduct();
        takeScreenShot("121 Var1_WomanClothCategoryRTL");
        stHomePage.selectLanguage_RU();
        //Категория "Телефоны"
        stHomePage.navigateToMenuPhones();
        //Проверка, что у товаров переключатель изображений с полосками
        Assert.assertTrue(getDriver().findElement(By.cssSelector(".abt__ut2_hover_gallery.lines")).isEnabled(),
                "Gallery of product images is not with stripes on the category page!");
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        Assert.assertTrue(getDriver().findElement(By
                .cssSelector("div[class*='ty-product-review-reviews-stars'][data-ca-product-review-reviews-stars-full=\"0\"]")).isEnabled(),
                "There is no empty rating stars at a product!");
        //Проверка, что кнопка "Избранное" присутствует
        Assert.assertTrue(stCategoryPage.button_AddToWishList.isEnabled(),
                "There is no button 'Add to wish list' on the category page!");
        //Проверка, что кнопка "Сравнить" присутствует
        Assert.assertTrue(stCategoryPage.button_AddToComparisonList.isEnabled(),
                "There is no button 'Add to comparison list' on the category page!");
        //Проверка, что у кнопки "В корзину" отображается статус в виде иконки
        stHomePage.LogOutOnStorefront();
        stCategoryPage.button_AddToCart.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cm-notification-content.cm-notification-content-extended")));
        stCategoryPage.button_ContinueShopping.click();
        Assert.assertTrue(getDriver().findElement(By.cssSelector(".ut2-added-to-cart")).isEnabled(),
                "There is no status for the button 'Add to cart' on the category page!");
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("130 Var1_PhonesCategory");
        if(getDriver().findElement(By.cssSelector(".notification-content.alert")).isEnabled()){
            for(int i=0; i<stCategoryPage.notification_AlertSuccess.size(); i++){
                stCategoryPage.closeNotification_AlertSuccess.click();
            }}
        stHomePage.selectLanguage_RTL();
        makePause();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("131 Var1_PhonesCategoryRTL");
        stHomePage.selectLanguage_RU();
        //Быстрый просмотр в категории "Телефоны"
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("140 Var1_QuickView");
        stCategoryPage.clickCloseQuickView();
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("141 Var1_QuickViewRTL");
        stCategoryPage.clickCloseQuickView();
        //Других два шаблона страницы категории
        stCategoryPage.clickListWithoutOptions_ProductListView();
        makePause();
        //Проверка, что у товаров переключатель изображений с полосками
        Assert.assertTrue(getDriver().findElement(By.cssSelector(".abt__ut2_hover_gallery.lines")).isEnabled(),
                "Gallery of product images is not with stripes in the product block!");
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        Assert.assertTrue(getDriver().findElement(By
                        .cssSelector("div[class*='ty-product-review-reviews-stars'][data-ca-product-review-reviews-stars-full=\"0\"]")).isEnabled(),
                "There is no empty stars at a product!");
        //Проверка, что кнопка "Избранное" присутствует
        Assert.assertTrue(stCategoryPage.button_AddToWishList.isEnabled(),
                "There is no button 'Add to wish list' in the product block!");
        //Проверка, что кнопка "Сравнить" присутствует
        Assert.assertTrue(stCategoryPage.button_AddToComparisonList.isEnabled(),
                "There is no button 'Add to comparison list' in the product block!");
        //Проверка, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются при наведении на ячейку товара
        Assert.assertTrue(getDriver().findElement(By.cssSelector(".ut2-w-c-q__buttons.w_c_q-hover")).isEnabled());
        //Проверка, что у кнопки "В корзину" отображается статус в виде иконки
        Assert.assertTrue(getDriver().findElement(By.cssSelector(".ut2-added-to-cart")).isEnabled(),
                "There is no status for the button 'Add to cart' on the category page!");
        takeScreenShot("150 Var1_Category_ListWithoutOptionsRTL");
        stHomePage.selectLanguage_RU();
        makePause();
        takeScreenShot("151 Var1_Category_ListWithoutOptions");
        stCategoryPage.clickCompactList_ProductListView();
        makePause();
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        Assert.assertTrue(getDriver().findElement(By
                        .cssSelector("div[class*='ty-product-review-reviews-stars'][data-ca-product-review-reviews-stars-full=\"0\"]")).isEnabled(),
                "There is no empty stars at a product!");
        //Проверка, что кнопка "Избранное" присутствует
        Assert.assertTrue(stCategoryPage.button_AddToWishList.isEnabled(),
                "There is no button 'Add to wish list' in the product block!");
        //Проверка, что кнопка "Сравнить" присутствует
        Assert.assertTrue(stCategoryPage.button_AddToComparisonList.isEnabled(),
                "There is no button 'Add to comparison list' in the product block!");
        takeScreenShot("160 Var1_Category_CompactList_ProductListView");
        //Проверка, что у кнопки "В корзину" отображается статус в виде иконки
        Assert.assertTrue(getDriver().findElement(By.cssSelector(".ut2-added-to-cart")).isEnabled(),
                "There is no status for the button 'Add to cart' on the category page!");
        takeScreenShot("160 Var1_Category_CompactList_ProductListView");
        stHomePage.selectLanguage_RTL();
        makePause();
        takeScreenShot("161 Var1_Category_CompactList_ProductListViewRTL");
    }
}