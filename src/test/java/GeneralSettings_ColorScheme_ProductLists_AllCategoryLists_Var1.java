import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.ColorSchemeSettings;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ThemeSettings_ProductLists;
import taras.constants.DriverProvider;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import java.time.Duration;
import static taras.constants.DriverProvider.getDriver;

/* ссылка на тест-кейс: https://docs.google.com/spreadsheets/d/1YPAkjqk12kPh7LBDU1tq7qdwLmCo-Rly00TdfW8h-Wo/edit#gid=718159332

1) CS-Cart настройки -- Внешний вид:
Включить быстрый просмотр -- y
Показывать мини-иконки в виде галереи -- n

2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
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
Переключать изображение товара при движении мышки (Сетка и Список без опций) -- С полосками

3) UniTheme2 -- Настройки цветосхемы -- вкладка "Списки товаров":
Тип обрамления товара в сетке --	Без рамки
Добавить фон/маску для изображений товара --	нет
Использовать выравнивание элементов в товарной сетке --	да
Эффект увеличения ячейки при наведении --	да
Насыщенность шрифта для названия товара --	Нормальный

Проверка проходит на следующих страницах:
- Блок товаров на Главной странице + RTL
- Женская одежда + RTL
- Телефоны + RTL
- Быстрый просмотр + RTL
- Все шаблоны категории + RTL
*/

public class GeneralSettings_ColorScheme_ProductLists_AllCategoryLists_Var1 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductLists_AllCategoryLists_Var1() {
        //Работаем с CS-Cart настройками
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToAppearanceSettingsOfCsCart();
        WebElement checkboxThumbnailsGallery = csCartSettings.setting_ThumbnailsGallery;
        if (checkboxThumbnailsGallery.isSelected()) {
            checkboxThumbnailsGallery.click();
        }
        WebElement checkboxSettingQuickView = csCartSettings.setting_QuickView;
        if (!checkboxSettingQuickView.isSelected()) {
            checkboxSettingQuickView.click();
        }
        csCartSettings.clickSaveButtonOfSettings();

        //Работаем с настройками темы (в основном идут по умолчанию)
        ThemeSettings_ProductLists themeSettingsProductLists = csCartSettings.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.clickTabProductLists();
        WebElement checkboxOutOfStockProducts = themeSettingsProductLists.settingOutOfStockProducts;
        if (!checkboxOutOfStockProducts.isSelected()) {
            checkboxOutOfStockProducts.click();
        }
        themeSettingsProductLists.selectSettingPriceDisplayFormat("row-mix");
        WebElement checkboxPriceAtTheTop = themeSettingsProductLists.settingPriceAtTheTop;
        if (checkboxPriceAtTheTop.isSelected()) {
            checkboxPriceAtTheTop.click();
        }
        WebElement checkboxProductRating = themeSettingsProductLists.settingProductRating;
        if (!checkboxProductRating.isSelected()) {
            checkboxProductRating.click();
        }
        WebElement checkboxSettingCommonValueOfProductRating = themeSettingsProductLists.settingCommonValueOfProductRating;
        if (checkboxSettingCommonValueOfProductRating.isSelected()) {
            checkboxSettingCommonValueOfProductRating.click();
        }
        themeSettingsProductLists.selectSettingDisplayCartStatus("check-icon");
        WebElement checkboxSettingDisplayStatusesForButtons = themeSettingsProductLists.settingDisplayStatusesForButtons;
        if (checkboxSettingDisplayStatusesForButtons.isSelected()) {
            checkboxSettingDisplayStatusesForButtons.click();
        }
        WebElement checkboxSettingDisplayButtonComparisonList = themeSettingsProductLists.settingDisplayButtonComparisonList;
        if (!checkboxSettingDisplayButtonComparisonList.isSelected()) {
            checkboxSettingDisplayButtonComparisonList.click();
        }
        WebElement checkboxSettingDisplayButtonWishList = themeSettingsProductLists.settingDisplayButtonWishList;
        if (!checkboxSettingDisplayButtonWishList.isSelected()) {
            checkboxSettingDisplayButtonWishList.click();
        }
        WebElement checkboxSettingDisplayButtonsWhenHoveringMouse = themeSettingsProductLists.settingDisplayButtonsWhenHoveringMouse;
        if (!checkboxSettingDisplayButtonsWhenHoveringMouse.isSelected()) {
            checkboxSettingDisplayButtonsWhenHoveringMouse.click();
        }
        themeSettingsProductLists.selectSetting_SwitchProductImageWhenHovering("lines");
        themeSettingsProductLists.selectWithoutOptions_SwitchProductImageWhenHovering("lines");
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Списки товаров"
        ColorSchemeSettings colorSchemeSettings = csCartSettings.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.fieldOfActiveColorScheme.click();
        colorSchemeSettings.activeColorScheme.click();
        makePause();
        colorSchemeSettings.tab_ProductLists.click();
        colorSchemeSettings.selectSetting_FrameType("none");
        if(colorSchemeSettings.setting_ProductLists_MaskForProductImages.isSelected()){
            colorSchemeSettings.setting_ProductLists_MaskForProductImages.click();
        }
        colorSchemeSettings.selectSetting_ProductLists_ElementsAlignment("use");
        if(!colorSchemeSettings.setting_ProductLists_ExpandGridItemOnHover.isSelected()){
            colorSchemeSettings.setting_ProductLists_ExpandGridItemOnHover.click();
        }
        colorSchemeSettings.selectSetting_ProductLists_FontWeightForProductName("normal");
        csCartSettings.clickSaveButtonOfSettings();
    }

        @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_AllCategoryLists_Var1")
        public void checkProductLists_AllCategoryLists_Var1() {
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        StCategoryPage stCategoryPage = new StCategoryPage();
        focusBrowserTab(1);
        stHomePage.cookie.click();
        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        SoftAssert softAssert = new SoftAssert();
        //Проверка, что у товаров переключатель изображений с полосками
        int sizeOfHoverGalleryInLines = getDriver().findElements(By.cssSelector(".abt__ut2_hover_gallery.lines")).size();
        softAssert.assertTrue(sizeOfHoverGalleryInLines >= 1,"Gallery of product images is not with lines");
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        int sizeOfEmptyReviewsStars = getDriver().findElements(By
                .cssSelector("div[class*='ty-product-review-reviews-stars'][data-ca-product-review-reviews-stars-full=\"0\"]")).size();
        softAssert.assertTrue(sizeOfEmptyReviewsStars >= 1,"There is no empty stars at a product!");
        //Проверка, что кнопка "Избранное" присутствует
        int sizeOfButton_AddToWishList = getDriver().findElements(By.cssSelector(".ut2-add-to-wish")).size();
        softAssert.assertTrue(sizeOfButton_AddToWishList >= 1,"There is no button 'Add to wish list' in the product block!");
        //Проверка, что кнопка "Сравнить" присутствует
        int sizeOfButton_AddToComparisonList = getDriver().findElements(By.cssSelector(".ut2-add-to-compare")).size();
        softAssert.assertTrue(sizeOfButton_AddToComparisonList >= 1,
                "There is no button 'Add to comparison list' in the product block!");
        //Проверка, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются при наведении на ячейку товара
        int sizeOfButtonsAreDisplayedOnHover = getDriver().findElements(By.cssSelector(".ut2-w-c-q__buttons.w_c_q-hover")).size();
        softAssert.assertTrue(sizeOfButtonsAreDisplayedOnHover >= 1,
                "Buttons are not displayed when hovering over a product cell in the product block!");
        stHomePage.hoverToProductInProductBlock();
        takeScreenShot_withoutScroll("100 GS_CS_ProductLists_AllCategoryLists_Var1 - BlockWithProducts");
        stHomePage.selectLanguage_RTL();
        stHomePage.scrollToBlockWithProducts();
        stHomePage.hoverToProductInProductBlock();
        takeScreenShot_withoutScroll("105 GS_CS_ProductLists_AllCategoryLists_Var1 - BlockWithProducts (RTL)");
        stHomePage.selectLanguage_RU();
        
        //Категория "Женская одежда"
        stHomePage.navigateToMenuWomanCloth();
        //Проверка, что на странице присутствует обесцвеченный товар.
        softAssert.assertTrue(getDriver().findElements(By.cssSelector(".ut2-gl__body.content-on-hover.decolorize")).size() >= 1,
                "There is no decolorized product on the category page!");
        //Проверка, что у товаров переключатель изображений с полосками
        softAssert.assertTrue(sizeOfHoverGalleryInLines >= 1,"Gallery of product images is not with stripes on the category page!");
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        softAssert.assertTrue(sizeOfEmptyReviewsStars >= 1,"There is no empty stars at a product!");
        //Проверка, что кнопка "Избранное" присутствует
        softAssert.assertTrue(sizeOfButton_AddToWishList >= 1,"There is no button 'Add to wish list' on the category page!");
        //Проверка, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(sizeOfButton_AddToComparisonList >= 1,
                "There is no button 'Add to comparison list' on the category page!");
        //Проверка, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются при наведении на ячейку товара
        softAssert.assertTrue(sizeOfButtonsAreDisplayedOnHover >= 1);
        stCategoryPage.hoverToClothProduct();
        takeScreenShot("110 GS_CS_ProductLists_AllCategoryLists_Var1 - WomanClothCategory");
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToClothProduct();
        takeScreenShot("115 GS_CS_ProductLists_AllCategoryLists_Var1 - WomanClothCategory (RTL)");
        stHomePage.selectLanguage_RU();

        //Категория "Телефоны"
        stHomePage.navigateToMenuPhones();
        //Проверка, что у товаров переключатель изображений с полосками
        softAssert.assertTrue(sizeOfHoverGalleryInLines >= 1,"Gallery of product images is not with stripes on the category page!");
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        softAssert.assertTrue(sizeOfEmptyReviewsStars >= 1,"There is no empty rating stars at a product!");
        //Проверка, что кнопка "Избранное" присутствует
        softAssert.assertTrue(sizeOfButton_AddToWishList >= 1,"There is no button 'Add to wish list' on the category page!");
        //Проверка, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(sizeOfButton_AddToComparisonList >= 1,
                "There is no button 'Add to comparison list' on the category page!");
        //Проверка, что у кнопки "В корзину" отображается статус в виде иконки
        stHomePage.LogOutOnStorefront();
        stCategoryPage.button_AddToCart.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cm-notification-content.cm-notification-content-extended")));
        stCategoryPage.button_ContinueShopping.click();
        int sizeOfStatusAtButton_AddToCart = getDriver().findElements(By.cssSelector(".ut2-added-to-cart")).size();
        softAssert.assertTrue(sizeOfStatusAtButton_AddToCart >= 1,
                "There is no status for the button 'Add to cart' on the category page!");
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("120 GS_CS_ProductLists_AllCategoryLists_Var1 - PhonesCategory");
        if(getDriver().findElements(By.cssSelector(".notification-content.alert")).size() >= 1){
            for(int i=0; i<stCategoryPage.notification_AlertSuccess.size(); i++){
                stCategoryPage.closeNotification_AlertSuccess.click();
            }}
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("125 GS_CS_ProductLists_AllCategoryLists_Var1 - PhonesCategory (RTL)");
        stHomePage.selectLanguage_RU();

        //Быстрый просмотр в категории "Телефоны"
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot("130 GS_CS_ProductLists_AllCategoryLists_Var1 - QuickView");
        stCategoryPage.clickCloseQuickView();
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot("135 GS_CS_ProductLists_AllCategoryLists_Var1 - QuickView (RTL)");
        stCategoryPage.clickCloseQuickView();

        //Других два шаблона страницы категории
        stCategoryPage.clickListWithoutOptions_ProductListView();
        //Проверка, что у товаров переключатель изображений с полосками
        softAssert.assertTrue(sizeOfHoverGalleryInLines >= 1,"Gallery of product images is not with lines");
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        softAssert.assertTrue(sizeOfEmptyReviewsStars >= 1,"There is no empty stars at a product!");
        //Проверка, что кнопка "Избранное" присутствует
        softAssert.assertTrue(sizeOfButton_AddToWishList >= 1,"There is no button 'Add to wish list' in the product block!");
        //Проверка, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(sizeOfButton_AddToComparisonList >= 1,
                "There is no button 'Add to comparison list' in the product block!");
        //Проверка, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются при наведении на ячейку товара
        softAssert.assertTrue(sizeOfButtonsAreDisplayedOnHover >= 1);
        //Проверка, что у кнопки "В корзину" отображается статус в виде иконки
        softAssert.assertTrue(sizeOfStatusAtButton_AddToCart >= 1,
                "There is no status for the button 'Add to cart' on the category page!");
        takeScreenShot("140 GS_CS_ProductLists_AllCategoryLists_Var1 - ListWithoutOptions (RTL)");
        Actions actions = new Actions(DriverProvider.getDriver());
        actions.moveToElement(DriverProvider.getDriver().findElement(By.cssSelector(".ty-select-wrapper"))).build().perform();
        makePause();
        stHomePage.selectLanguage_RU();
        takeScreenShot("145 GS_CS_ProductLists_AllCategoryLists_Var1 - ListWithoutOptions");
        stCategoryPage.clickCompactList_ProductListView();
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        softAssert.assertTrue(sizeOfEmptyReviewsStars >= 1,"There is no empty stars at a product!");
        //Проверка, что кнопка "Избранное" присутствует
        softAssert.assertTrue(sizeOfButton_AddToWishList >= 1,"There is no button 'Add to wish list' in the product block!");
        //Проверка, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(sizeOfButton_AddToComparisonList >= 1,
                "There is no button 'Add to comparison list' in the product block!");
        //Проверка, что у кнопки "В корзину" отображается статус в виде иконки
        softAssert.assertTrue(sizeOfStatusAtButton_AddToCart >= 1,
                "There is no status for the button 'Add to cart' on the category page!");
        takeScreenShot("150 GS_CS_ProductLists_AllCategoryLists_Var1 - CompactList_ProductListView");
        stHomePage.selectLanguage_RTL();
        takeScreenShot("155 GS_CS_ProductLists_AllCategoryLists_Var1 - CompactList_ProductListView (RTL)");
        softAssert.assertAll();
        System.out.println("GeneralSettings_ColorScheme_ProductLists_AllCategoryLists_Var1 passed successfully!");
    }
}