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
Показывать цены с налогом на страницах категорий и товаров --   y
Показывать мини-иконки в виде галереи --    n
Включить быстрый просмотр --    y

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

4) Настраиваем налог для всех товаров

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
        //Настраиваем макет для тест-кейса
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_WebsiteLayouts();
        csCartSettings.layout_Lightv2.click();
        csCartSettings.setLayoutAsDefault();

        //Работаем с CS-Cart настройками
        csCartSettings.navigateToAppearanceSettings();
        WebElement checkboxDisplayPricesWithTaxesOnCategoryAndProductPages = csCartSettings.setting_DisplayPricesWithTaxesOnCategoryAndProductPages;
        if (!checkboxDisplayPricesWithTaxesOnCategoryAndProductPages.isSelected()) {
            checkboxDisplayPricesWithTaxesOnCategoryAndProductPages.click();
        }
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
        if (colorSchemeSettings.setting_ProductLists_MaskForProductImages.isSelected()) {
            colorSchemeSettings.setting_ProductLists_MaskForProductImages.click();
        }
        colorSchemeSettings.selectSetting_ProductLists_ElementsAlignment("use");
        if (!colorSchemeSettings.setting_ProductLists_ExpandGridItemOnHover.isSelected()) {
            colorSchemeSettings.setting_ProductLists_ExpandGridItemOnHover.click();
        }
        colorSchemeSettings.selectSetting_ProductLists_FontWeightForProductName("normal");
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем налог для всех товаров
        csCartSettings.navigateToCheckoutSettings();
        csCartSettings.selectSetting_TaxCalculationMethodBasedOn("unit_price");
        csCartSettings.clickSaveButtonOfSettings();
        csCartSettings.navigateToTaxes();
        WebElement checkboxPriceIncludesTax = csCartSettings.setting_priceIncludesTax;
        if (checkboxPriceIncludesTax.isSelected()) {
            checkboxPriceIncludesTax.click();
            csCartSettings.button_saveTaxes.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            csCartSettings.vat20.click();
            (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn-group.bulk-edit__wrapper")));
            csCartSettings.button_Actions.click();
            csCartSettings.button_ApplySelectedTaxesToAllProducts.click();
        }
    }


    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_AllCategoryLists_Var1")
    public void checkProductLists_AllCategoryLists_Var1() {
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefront();
        StCategoryPage stCategoryPage = new StCategoryPage();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        SoftAssert softAssert = new SoftAssert();
        //Проверка, что у товаров переключатель изображений с полосками
        boolean exist_HoverGalleryInLines = getDriver().findElements(By.cssSelector(".abt__ut2_hover_gallery.lines")).isEmpty();
        softAssert.assertTrue(!exist_HoverGalleryInLines, "Gallery of product images is not with lines in the product block");
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        boolean exist_EmptyReviewsStars = getDriver().findElements(By
                .cssSelector("div[class*='ty-product-review-reviews-stars'][data-ca-product-review-reviews-stars-full=\"0\"]")).isEmpty();
        softAssert.assertTrue(!exist_EmptyReviewsStars, "There are no empty stars in the product block!");
        //Проверка, что кнопка "Избранное" присутствует
        boolean exist_Button_AddToWishList = getDriver().findElements(By.cssSelector(".ut2-add-to-wish")).isEmpty();
        softAssert.assertTrue(!exist_Button_AddToWishList, "There is no button 'Add to wish list' in the product block!");
        //Проверка, что кнопка "Сравнить" присутствует
        boolean exist_Button_AddToComparisonList = getDriver().findElements(By.cssSelector(".ut2-add-to-compare")).isEmpty();
        softAssert.assertTrue(!exist_Button_AddToComparisonList,
                "There is no button 'Add to comparison list' in the product block!");
        //Проверка, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются при наведении на ячейку товара
        boolean exist_ButtonsAreDisplayedOnHover = getDriver().findElements(By.cssSelector(".ut2-w-c-q__buttons.w_c_q-hover")).isEmpty();
        softAssert.assertTrue(!exist_ButtonsAreDisplayedOnHover,
                "Buttons are not displayed when hovering over a product cell in the product block!");
        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        boolean exist_TaxText = getDriver().findElements(By.cssSelector("span[id*='line_product_price_']")).isEmpty();
        softAssert.assertTrue(!exist_TaxText,
                "There is no text of a product tax in the product block!");
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("100 GS_CS_ProductLists_AllCategoryLists_Var1 - BlockWithProducts");
        stHomePage.selectLanguage_RTL();
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("105 GS_CS_ProductLists_AllCategoryLists_Var1 - BlockWithProducts (RTL)");
        stHomePage.selectLanguage_RU();

        //Категория "Женская одежда"
        stHomePage.navigateToHorizontalMenu_WomanCloth();
        //Проверка, что на странице присутствует обесцвеченный товар.
        softAssert.assertTrue(!getDriver().findElements(By.cssSelector(".ut2-gl__body.content-on-hover.decolorize")).isEmpty(),
                "There is no decolorized product on the category 'Woman cloth'!");
        //Проверка, что у товаров переключатель изображений с полосками
        softAssert.assertTrue(!exist_HoverGalleryInLines, "Gallery of product images is not with stripes on the category 'Woman cloth'!");
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        softAssert.assertTrue(!exist_EmptyReviewsStars, "There are no empty stars on the category 'Woman cloth'!");
        //Проверка, что кнопка "Избранное" присутствует
        softAssert.assertTrue(!exist_Button_AddToWishList, "There is no button 'Add to wish list' on the category 'Woman cloth'!");
        //Проверка, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(!exist_Button_AddToComparisonList,
                "There is no button 'Add to comparison list' on the category 'Woman cloth'!");
        //Проверка, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются при наведении на ячейку товара
        softAssert.assertTrue(!exist_ButtonsAreDisplayedOnHover);
        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!exist_TaxText,
                "There is no text of a product tax on the category 'Woman cloth'!");
        stCategoryPage.hoverToClothProduct();
        takeScreenShot_withScroll("110 GS_CS_ProductLists_AllCategoryLists_Var1 - WomanClothCategory");
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToClothProduct();
        takeScreenShot_withScroll("115 GS_CS_ProductLists_AllCategoryLists_Var1 - WomanClothCategory (RTL)");
        stHomePage.selectLanguage_RU();

        //Категория "Телефоны"
        stHomePage.navigateToHorizontalMenu_Phones();
        //Проверка, что у товаров переключатель изображений с полосками
        softAssert.assertTrue(!exist_HoverGalleryInLines, "Gallery of product images is not with stripes on the category 'Phones'!");
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        softAssert.assertTrue(!exist_EmptyReviewsStars, "There are no empty rating stars at a product on the category 'Phones'!");
        //Проверка, что кнопка "Избранное" присутствует
        softAssert.assertTrue(!exist_Button_AddToWishList, "There is no button 'Add to wish list' on the category 'Phones'!");
        //Проверка, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(!exist_Button_AddToComparisonList,
                "There is no button 'Add to comparison list' on the category 'Phones'!");
        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!exist_TaxText,
                "There is no text of a product tax on the category 'Phones'!");
        //Проверка, что у кнопки "В корзину" отображается статус в виде иконки
        stHomePage.logOutOnStorefront();
        stCategoryPage.button_AddToCart.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cm-notification-content.cm-notification-content-extended")));
        stCategoryPage.button_ContinueShopping.click();
        boolean exist_StatusAtButton_AddToCart = getDriver().findElements(By.cssSelector(".ut2-added-to-cart")).isEmpty();
        softAssert.assertTrue(!exist_StatusAtButton_AddToCart,
                "There is no status for the button 'Add to cart' on the category 'Phones'!");
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("120 GS_CS_ProductLists_AllCategoryLists_Var1 - PhonesCategory");
        if (!getDriver().findElements(By.cssSelector(".notification-content.alert")).isEmpty()) {
            for (int i = 0; i < stCategoryPage.notification_AlertSuccess.size(); i++) {
                stCategoryPage.closeNotification_AlertSuccess.click();
            }
        }
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("125 GS_CS_ProductLists_AllCategoryLists_Var1 - PhonesCategory (RTL)");
        stHomePage.selectLanguage_RU();

        //Быстрый просмотр в категории "Телефоны"
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot_withScroll("130 GS_CS_ProductLists_AllCategoryLists_Var1 - QuickView");
        stCategoryPage.clickCloseQuickView();
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot_withScroll("135 GS_CS_ProductLists_AllCategoryLists_Var1 - QuickView (RTL)");
        stCategoryPage.clickCloseQuickView();

        //Других два шаблона страницы категории
        stCategoryPage.clickListWithoutOptions_ProductListView();
        //Проверка, что у товаров переключатель изображений с полосками
        softAssert.assertTrue(!exist_HoverGalleryInLines, "Gallery of product images is not with lines on the category 'List without options'");
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        softAssert.assertTrue(!exist_EmptyReviewsStars, "There are no empty stars on the category 'List without options'!");
        //Проверка, что кнопка "Избранное" присутствует
        softAssert.assertTrue(!exist_Button_AddToWishList, "There is no button 'Add to wish list' on the category 'List without options'!");
        //Проверка, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(!exist_Button_AddToComparisonList,
                "There is no button 'Add to comparison list' on the category 'List without options'!");
        //Проверка, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются при наведении на ячейку товара
        softAssert.assertTrue(!exist_ButtonsAreDisplayedOnHover);
        //Проверка, что у кнопки "В корзину" отображается статус в виде иконки
        softAssert.assertTrue(!exist_StatusAtButton_AddToCart,
                "There is no status for the button 'Add to cart' on the category 'List without options'!");
        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!exist_TaxText,
                "There is no text of a product tax on the category 'List without options'!");
        takeScreenShot_withScroll("140 GS_CS_ProductLists_AllCategoryLists_Var1 - ListWithoutOptions (RTL)");
        Actions actions = new Actions(DriverProvider.getDriver());
        actions.moveToElement(DriverProvider.getDriver().findElement(By.cssSelector(".ty-select-wrapper"))).build().perform();
        makePause();
        stHomePage.selectLanguage_RU();
        takeScreenShot_withScroll("145 GS_CS_ProductLists_AllCategoryLists_Var1 - ListWithoutOptions");
        stCategoryPage.clickCompactList_ProductListView();
        //Проверка, что у товаров присутствуют пустые звёздочки рейтинга
        softAssert.assertTrue(!exist_EmptyReviewsStars, "There are no empty stars on the category 'Compact list'!");
        //Проверка, что кнопка "Избранное" присутствует
        softAssert.assertTrue(!exist_Button_AddToWishList, "There is no button 'Add to wish list' on the category 'Compact list'!");
        //Проверка, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(!exist_Button_AddToComparisonList,
                "There is no button 'Add to comparison list' on the category 'Compact list'!");
        //Проверка, что у кнопки "В корзину" отображается статус в виде иконки
        softAssert.assertTrue(!exist_StatusAtButton_AddToCart,
                "There is no status for the button 'Add to cart' on the category 'Compact list'!");
        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!exist_TaxText,
                "There is no text of a product tax on the category 'Compact list'!");
        takeScreenShot_withScroll("150 GS_CS_ProductLists_AllCategoryLists_Var1 - CompactList_ProductListView");
        stHomePage.selectLanguage_RTL();
        takeScreenShot_withScroll("155 GS_CS_ProductLists_AllCategoryLists_Var1 - CompactList_ProductListView (RTL)");
        softAssert.assertAll();
        System.out.println("GeneralSettings_ColorScheme_ProductLists_AllCategoryLists_Var1 passed successfully!");
    }
}