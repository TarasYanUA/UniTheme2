import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

import java.time.Duration;

import static taras.constants.DriverProvider.getDriver;

/* ссылка на тест-кейс: https://docs.google.com/spreadsheets/d/1YPAkjqk12kPh7LBDU1tq7qdwLmCo-Rly00TdfW8h-Wo/edit#gid=718159332

Проверяем следующих настроек:
1) "Настройки -- Внешний вид":
Показывать цены с налогом на страницах категорий и товаров --   y
Показывать мини-иконки в виде галереи --    y
Включить быстрый просмотр --    y

2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Обесцвечивать товары, которых нет в наличии --	n
Формат отображения цен --	Вариант 1
Отображать цену вверху --	y
Отображать пустые звёзды рейтинга товара --	n
Отображать общее значение рейтинга товара -- y
Отображать статусы для кнопок "Купить" -- Количество товаров
Отображать статусы для кнопок "Добавить в избранное", "Добавить в список сравнения" -- y
Отображать кнопку "Добавить в избранное" -- y
Отображать кнопку "Добавить в список сравнения" -- y
Отображать кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" при наведении на ячейку товара -- n
Отображать стандартную галерею изображений (Сетка и Список без опций) -- Стрелками
Переключать изображение товара при движении мышки (Сетка и Список без опций) -- Не переключать (нужно для настройки выше)

3) - UniTheme2 -- Настройки цветосхемы -- вкладка "Списки товаров":
Тип обрамления товара в сетке --	Рамка с внешними отступами
Добавить фон/маску для изображений товара --	да
Использовать выравнивание элементов в товарной сетке --	нет
Эффект увеличения ячейки при наведении --	нет
Насыщенность шрифта для названия товара --	Жирный
    * Раздел Модуль "Вариации товаров":
Максимальное количество отображаемых вариаций товара -- 10
Тип отображения вариаций -- Миниатюры

4) Настраиваем налог для всех товаров

5) Настраиваем характеристику "Цвет" с типом отображения вариаций как "Цвет":
* На странице редактирования характеристики:
    Вариации как один товар
    Внешний вид - Изображения
    Тип фильтра - Цвет
* Работаем с товарами "Apple - iPhone 5c 32GB Cell Phone" и "Droid 3"

Проверяем проходит на следующих страницах:
- Блок товаров на Главной странице + RTL
- Женская одежда + RTL
- Телефоны + RTL
- Быстрый просмотр + RTL
- Все шаблоны категории + RTL
*/

public class GeneralSettings_ColorScheme_ProductLists_AllCategoryLists_Var2 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductLists_AllCategoryLists_Var2() {
        //Настраиваем макет для тест-кейса
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Lightv2.click();
        layoutPage.setLayoutAsDefault();

        //Работаем с CS-Cart настройками
        CsCartSettings csCartSettings = basicPage.navigateToAppearanceSettings();
        UtilsAdm.setCheckboxState(csCartSettings.setting_DisplayPricesWithTaxesOnCategoryAndProductPages, true);
        UtilsAdm.setCheckboxState(csCartSettings.setting_ThumbnailsGallery, true);
        UtilsAdm.setCheckboxState(csCartSettings.setting_QuickView, true);
        basicPage.clickSaveButtonOfSettings();

        //Работаем с настройками темы
        ThemeSettings_ProductLists themeSettingsProductLists = basicPage.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.tabProductLists.click();
        WebElement checkboxOutOfStockProducts = themeSettingsProductLists.setting_OutOfStockProducts;
        if (checkboxOutOfStockProducts.isSelected())
            checkboxOutOfStockProducts.click();
        new Select(themeSettingsProductLists.setting_PriceDisplayFormat).selectByValue("col");
        WebElement checkboxPriceAtTheTop = themeSettingsProductLists.setting_PriceAtTheTop;
        if (!checkboxPriceAtTheTop.isSelected())
            checkboxPriceAtTheTop.click();
        WebElement checkboxProductRating = themeSettingsProductLists.setting_EmptyStarsOfProductRating;
        if (checkboxProductRating.isSelected())
            checkboxProductRating.click();
        WebElement checkboxSettingCommonValueOfProductRating = themeSettingsProductLists.setting_CommonValueOfProductRating;
        if (!checkboxSettingCommonValueOfProductRating.isSelected())
            checkboxSettingCommonValueOfProductRating.click();
        new Select(themeSettingsProductLists.setting_DisplayCartStatus).selectByValue("counter");
        WebElement checkboxSettingDisplayStatusesForButtons = themeSettingsProductLists.setting_DisplayStatusesForButtons;
        if (!checkboxSettingDisplayStatusesForButtons.isSelected())
            checkboxSettingDisplayStatusesForButtons.click();
        WebElement checkboxSettingDisplayButtonComparisonList = themeSettingsProductLists.setting_DisplayButtonComparisonList;
        if (!checkboxSettingDisplayButtonComparisonList.isSelected())
            checkboxSettingDisplayButtonComparisonList.click();
        WebElement checkboxSettingDisplayButtonWishList = themeSettingsProductLists.setting_DisplayButtonWishList;
        if (!checkboxSettingDisplayButtonWishList.isSelected())
            checkboxSettingDisplayButtonWishList.click();
        WebElement checkboxSettingDisplayButtonsWhenHoveringMouse = themeSettingsProductLists.setting_DisplayButtonsWhenHoveringMouse;
        if (checkboxSettingDisplayButtonsWhenHoveringMouse.isSelected())
            checkboxSettingDisplayButtonsWhenHoveringMouse.click();
        new Select(themeSettingsProductLists.setting_ShowStandardImageGallery_Grid).selectByValue("arrows");
        new Select(themeSettingsProductLists.withoutOptions_ShowStandardImageGallery).selectByValue("arrows");
        new Select(themeSettingsProductLists.setting_SwitchProductImageWhenHovering).selectByValue("N");
        new Select(themeSettingsProductLists.withoutOptions_SwitchProductImageWhenHovering).selectByValue("N");
        themeSettingsProductLists.setProductVariations_MaximumQuantityOfProductsVariations("10");
        new Select(themeSettingsProductLists.productVariations_TypeOfVariationsView).selectByValue("thumbnails");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Списки товаров"
        ColorSchemeSettings colorSchemeSettings = basicPage.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.fieldOfActiveColorScheme.click();
        colorSchemeSettings.activeColorScheme.click();
        makePause();
        colorSchemeSettings.tab_ProductLists.click();
        new Select(colorSchemeSettings.setting_FrameType).selectByValue("none");
        if (!colorSchemeSettings.setting_ProductLists_MaskForProductImages.isSelected())
            colorSchemeSettings.setting_ProductLists_MaskForProductImages.click();
        new Select(colorSchemeSettings.setting_ProductLists_ElementsAlignment).selectByValue("do_not_use");
        if (colorSchemeSettings.setting_ProductLists_ExpandGridItemOnHover.isSelected())
            colorSchemeSettings.setting_ProductLists_ExpandGridItemOnHover.click();
        new Select(colorSchemeSettings.setting_ProductLists_FontWeightForProductName).selectByValue("bold");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем налог для всех товаров
        csCartSettings.setTaxesForAllProducts();

        //Настраиваем характеристику "Цвет" с типом отображения вариаций как "Цвет"
        FeaturePage featuresPage = basicPage.navigateToSection_Features();
        featuresPage.setFeatureColorForVariations();
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Apple - iPhone 5c 32GB Cell Phone");
        productSettings.chooseAnyProduct();
        UtilsAdm.clickAndType(productSettings.field_ProductName, "Apple - iPhone 5c 32GB Cell Phone");
        productSettings.selectAllVariations();
        basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Droid 3");
        productSettings.chooseAnyProduct();
        productSettings.selectAllVariations();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_AllCategoryLists_Var2")
    public void checkProductLists_AllCategoryLists_Var2() {
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();

        SoftAssert softAssert = new SoftAssert();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что у товаров присутствует галерея изображений и она стрелками
        softAssert.assertTrue(!assertsOnStorefront.gridList__ShowStandardImageGallery_Arrows.isEmpty(),
                "Image gallery of the product is not with arrows navigation in the product block!");

        //Проверяем, что у товаров присутствует общее значение рейтинга товара
        softAssert.assertTrue(!assertsOnStorefront.commonValueOfProductRating().isEmpty(),
                "There is no common value of product rating in the product block!");

        //Проверяем, что кнопка "Избранное" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToWishList.isEmpty(),
                "There is no button 'Add to wish list' in the product block!");

        //Проверяем, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToComparisonList.isEmpty(),
                "There is no button 'Add to comparison list' in the product block!");

        //Проверяем, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются СРАЗУ, а не при наведении на ячейку товара
        softAssert.assertFalse(!assertsOnStorefront.buttonsAreDisplayedOnHover.isEmpty(),
                "Buttons are not displayed at once, but only when hovering over a product cell in the product block!");

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!assertsOnStorefront.pricesWithTaxes().isEmpty(),
                "There is no text of a product tax in the product block!");

        takeScreenShot("200 GS_CS_ProductLists_AllCategoryLists_Var2 - BlockWithProducts");
        stHomePage.selectLanguage_RTL();
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("205 GS_CS_ProductLists_AllCategoryLists_Var2 - BlockWithProducts (RTL)");
        stHomePage.selectLanguage_RU();

        //Категория "Женская одежда"
        stHomePage.navigateToHorizontalMenu_WomanCloth();
        StCategoryPage stCategoryPage = new StCategoryPage();

        //Проверяем, что на странице отсутствует обесцвеченный товар.
        softAssert.assertFalse(!assertsOnStorefront.decolorizeOutOfStockProducts.isEmpty(),
                "There is a decolorized product on the category 'Woman cloth' but shouldn't!");

        //Проверяем, что у товаров присутствует галерея изображений и она стрелками
        softAssert.assertTrue(!assertsOnStorefront.gridList__ShowStandardImageGallery_Arrows.isEmpty(),
                "Image gallery of the product is not with arrows navigation on the category 'Woman cloth'!");

        //Проверяем, что кнопка "Избранное" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToWishList.isEmpty(),
                "There is no button 'Add to wish list' on the category 'Woman cloth'!");

        //Проверяем, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToComparisonList.isEmpty(),
                "There is no button 'Add to comparison list' on the category 'Woman cloth'!");

        //Проверяем, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются при наведении на ячейку товара
        softAssert.assertFalse(!assertsOnStorefront.buttonsAreDisplayedOnHover.isEmpty(),
                "Buttons are not displayed at once but only when hovering over a product cell on the category 'Woman cloth'!");

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!assertsOnStorefront.pricesWithTaxes().isEmpty(),
                "There is no text of a product tax on the category 'Woman cloth'!");

        stCategoryPage.hoverToClothProduct();
        takeScreenShot_withScroll("210 GS_CS_ProductLists_AllCategoryLists_Var2 - WomanClothCategory");
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToClothProduct();
        takeScreenShot_withScroll("215 GS_CS_ProductLists_AllCategoryLists_Var2 - WomanClothCategory (RTL)");
        stHomePage.selectLanguage_RU();

        //Категория "Телефоны"
        stHomePage.navigateToHorizontalMenu_Phones();

        //Проверяем, что у товаров присутствует общее значение рейтинга товара
        softAssert.assertTrue(!assertsOnStorefront.commonValueOfProductRating().isEmpty(),
                "There is no common value of product rating on the category 'Phones'!");

        //Проверяем, что кнопка "Избранное" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToWishList.isEmpty(),
                "There is no button 'Add to wish list' on the category 'Phones'!");

        //Проверяем, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToComparisonList.isEmpty(),
                "There is no button 'Add to comparison list' on the category 'Phones'!");

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!assertsOnStorefront.pricesWithTaxes().isEmpty(),
                "There is no text of a product tax on the category 'Phones'!");

        //Проверяем, что присутствует статус у кнопки "Избранное"
        if (assertsOnStorefront.statusesForButton_AddToWishList.isEmpty()) {
            stCategoryPage.button_AddToWishList.click();
            (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cm-notification-close")));
            stCategoryPage.closeNotificationWindow.click();
        }
        softAssert.assertTrue(!assertsOnStorefront.statusesForButton_AddToWishList.isEmpty(),
                "There is no status for the button 'Add to wish list'!");

        //Проверяем, что присутствует статус у кнопки "Сравнение"
        if (assertsOnStorefront.statusesForButton_AddToComparisonList.isEmpty()) {
            stCategoryPage.button_AddToComparisonList.click();
            (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cm-notification-close")));
            stCategoryPage.closeNotificationWindow.click();
        }
        softAssert.assertTrue(!assertsOnStorefront.statusesForButton_AddToComparisonList.isEmpty(),
                "There is no status for the button 'Add to comparison list'!");

        //Проверяем, что у товаров присутствует новый вид Вариаций
        softAssert.assertTrue(!assertsOnStorefront.gridList__prodVar_MaximumQuantityOfProductsVariations().isEmpty(),
                "There is no new view of product variations on the category 'Phones', Grid list!");

        //Проверяем, что тип отображения новых вариаций -- Миниатюры
        softAssert.assertTrue(!assertsOnStorefront.gridList__prodVar_TypeOfVariationsView_Thumbnails().isEmpty(),
                "Product variations are not with Thumbnails on the category 'Phones', Grid list!");

        //Проверяем, что у кнопки "В корзину" отображается статус в виде количества товаров
        stHomePage.logOutOnStorefront();
        stCategoryPage.hoverToButtonAddToCart();
        stCategoryPage.button_GeneralAddToCart.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(8)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cm-notification-content.cm-notification-content-extended")));
        stCategoryPage.button_ContinueShopping.click();

        softAssert.assertTrue(!assertsOnStorefront.statusesForButton_AddToCart_Number.isEmpty(),
                "There is no status for the button 'Add to cart' as 'Number of products' on the category page!");

        if (!getDriver().findElements(By.cssSelector(".notification-content.alert")).isEmpty()) {
            for (int i = 0; i < stCategoryPage.notification_AlertSuccess.size(); i++) {
                stCategoryPage.closeNotification_AlertSuccess.click();
            }
        }
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("220 GS_CS_ProductLists_AllCategoryLists_Var2 - PhonesCategory");
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("225 GS_CS_ProductLists_AllCategoryLists_Var2 - PhonesCategory (RTL)");
        stHomePage.selectLanguage_RU();

        //Быстрый просмотр в категории "Телефоны"
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-icon-right-open-thin")));

        //Проверяем, что присутствуют мини-иконки в виде галереи
        softAssert.assertTrue(!assertsOnStorefront.miniThumbnailImagesAsGallery_Enabled.isEmpty(),
                "Mini icons are not as a gallery!");

        takeScreenShot("230 GS_CS_ProductLists_AllCategoryLists_Var2 - QuickView");
        stCategoryPage.clickCloseQuickView();
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-icon-right-open-thin")));
        stCategoryPage.hoverCloseQuickView();
        takeScreenShot("235 GS_CS_ProductLists_AllCategoryLists_Var2 - QuickView (RTL)");
        stCategoryPage.clickCloseQuickView();

        //Других два шаблона страницы категории
        stCategoryPage.clickListWithoutOptions_ProductListView();

        //Проверяем, что у товаров присутствует общее значение рейтинга товара
        softAssert.assertTrue(!assertsOnStorefront.commonValueOfProductRating().isEmpty(),
                "There is no common value of product rating on the category 'List without options'!");

        //Проверяем, что присутствует статус у кнопки "Избранное"
        softAssert.assertTrue(!assertsOnStorefront.statusesForButton_AddToWishList.isEmpty(),
                "There is no status for the button 'Add to wish list' on the category 'List without options'!");

        //Проверяем, что присутствует статус у кнопки "Сравнение"
        softAssert.assertTrue(!assertsOnStorefront.statusesForButton_AddToComparisonList.isEmpty(),
                "There is no status for the button 'Add to comparison list' on the category 'List without options'!");

        //Проверяем, что у кнопки "В корзину" отображается статус в виде количества товаров
        softAssert.assertTrue(!assertsOnStorefront.statusesForButton_AddToCart_Number.isEmpty(),
                "There is no status for the button 'Add to cart' on the category 'List without options'!");

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!assertsOnStorefront.pricesWithTaxes().isEmpty(),
                "There is no text of a product tax on the category 'List without options'!");

        //Проверяем, что у товаров присутствует новый вид Вариаций
        softAssert.assertTrue(!assertsOnStorefront.listWithoutOptions__prodVar_MaximumQuantityOfProductsVariations().isEmpty(),
                "There is no new view of product variations on the category 'Phones', ListWithoutOptions!");

        //Проверяем, что тип отображения новых вариаций -- Миниатюры
        softAssert.assertTrue(!assertsOnStorefront.listWithoutOptions__prodVar_TypeOfVariationsView_Thumbnails().isEmpty(),
                "Product variations are not with Thumbnails on the category 'Phones', ListWithoutOptions!");

        makePause();
        takeScreenShot_withScroll("240 GS_CS_ProductLists_AllCategoryLists_Var2 - ListWithoutOptions (RTL)");
        stHomePage.selectLanguage_RU();
        takeScreenShot_withScroll("245 GS_CS_ProductLists_AllCategoryLists_Var2 - ListWithoutOptions");
        stCategoryPage.clickCompactList_ProductListView();

        //Проверяем, что у товаров присутствует общее значение рейтинга товара
        softAssert.assertTrue(!assertsOnStorefront.commonValueOfProductRating().isEmpty(),
                "There is no common value of product rating on the category 'Compact list'!");

        //Проверяем, что присутствует статус у кнопки "Избранное"
        softAssert.assertTrue(!assertsOnStorefront.statusesForButton_AddToWishList.isEmpty(),
                "There is no status for the button 'Add to wish list' on the category 'Compact list'!");

        //Проверяем, что присутствует статус у кнопки "Сравнение"
        softAssert.assertTrue(!assertsOnStorefront.statusesForButton_AddToComparisonList.isEmpty(),
                "There is no status for the button 'Add to comparison list' on the category 'Compact list'!");

        //Проверяем, что у кнопки "В корзину" отображается статус в виде количества товаров
        softAssert.assertTrue(!assertsOnStorefront.statusesForButton_AddToCart_Number.isEmpty(),
                "There is no status for the button 'Add to cart' on the category 'Compact list'!");

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!assertsOnStorefront.pricesWithTaxes().isEmpty(),
                "There is no text of a product tax on the category 'Compact list'!");

        takeScreenShot_withScroll("250 GS_CS_ProductLists_AllCategoryLists_Var2 - CompactList_ProductListView");
        stHomePage.selectLanguage_RTL();
        takeScreenShot_withScroll("255 GS_CS_ProductLists_AllCategoryLists_Var2 - CompactList_ProductListView (RTL)");
        softAssert.assertAll();
        System.out.println("GeneralSettings_ColorScheme_ProductLists_AllCategoryLists_Var2 passed successfully!");
    }
}