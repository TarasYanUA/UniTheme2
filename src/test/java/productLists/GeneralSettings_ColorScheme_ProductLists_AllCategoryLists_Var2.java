package productLists;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.adminPanel.themeSettings.ThemeSettings_ProductLists;
import taras.asserts.Asserts_CsCartSettings;
import taras.asserts.Asserts_ThemeSettings_ProductLists;
import taras.constants.DriverProvider;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

import java.time.Duration;

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
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_OutOfStockProducts, false);
        new Select(themeSettingsProductLists.setting_PriceDisplayFormat).selectByValue("col");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_PriceAtTheTop, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_EmptyStarsOfProductRating, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_CommonValueOfProductRating, true);
        new Select(themeSettingsProductLists.setting_DisplayCartStatus).selectByValue("counter");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayStatusesForButtons, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayButtonComparisonList, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayButtonWishList, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayButtonsWhenHoveringMouse, false);
        new Select(themeSettingsProductLists.setting_ShowStandardImageGallery_Grid).selectByValue("arrows");
        new Select(themeSettingsProductLists.withoutOptions_ShowStandardImageGallery).selectByValue("arrows");
        new Select(themeSettingsProductLists.setting_SwitchProductImageWhenHovering).selectByValue("N");
        new Select(themeSettingsProductLists.withoutOptions_SwitchProductImageWhenHovering).selectByValue("N");
        new Select(themeSettingsProductLists.compactList_buttonAddToCart).selectByValue("icon");
        themeSettingsProductLists.setProductVariations_MaximumQuantityOfProductsVariations("10");
        new Select(themeSettingsProductLists.productVariations_TypeOfVariationsView).selectByValue("thumbnails");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Списки товаров"
        ColorSchemeSettings colorSchemeSettings = basicPage.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.selectActiveColorScheme();
        colorSchemeSettings.tab_ProductLists.click();
        new Select(colorSchemeSettings.setting_FrameType).selectByValue("none");
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_ProductLists_MaskForProductImages, true);
        new Select(colorSchemeSettings.setting_ProductLists_ElementsAlignment).selectByValue("do_not_use");
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_ProductLists_ExpandGridItemOnHover, false);
        new Select(colorSchemeSettings.setting_ProductLists_FontWeightForProductName).selectByValue("bold");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем налог для всех товаров
        csCartSettings.setTaxesForAllProducts();

        //Настраиваем характеристику "Цвет" с типом отображения вариаций как "Цвет"
        FeaturePage featurePage = basicPage.navigateToSection_Features();
        featurePage.setFeatureColorForVariations();
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Apple - iPhone 5c 32GB Cell Phone");
        UtilsAdm.clickAndType(productSettings.field_ProductName, "Apple - iPhone 5c 32GB Cell Phone");
        productSettings.selectAllVariations();
        basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Droid 3");
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

        Asserts_ThemeSettings_ProductLists asserts_productLists = new Asserts_ThemeSettings_ProductLists();
        Asserts_CsCartSettings asserts_csCartSettings = new Asserts_CsCartSettings();

        //Проверяем, что у товаров присутствует общее значение рейтинга товара
        asserts_productLists.assertElementPresence(
                "",
                asserts_productLists.commonValueOfProductRating,
                "in the product block!",
                true);

        //Проверяем, что кнопка "Избранное" присутствует
        asserts_productLists.assertElementPresence(
                "",
                asserts_productLists.button_AddToWishList,
                "in the product block!",
                true);

        //Проверяем, что кнопка "Сравнить" присутствует
        asserts_productLists.assertElementPresence(
                "",
                asserts_productLists.button_AddToComparisonList,
                "in the product block!",
                true);

        //Проверяем, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются СРАЗУ, а не при наведении на ячейку товара
        asserts_productLists.assertElementPresence(
                "",
                asserts_productLists.buttonsAreDisplayedOnHover,
                "in the product block!",
                false);

        //Проверяем, что у товаров присутствует галерея изображений и она стрелками
        asserts_productLists.assertElementPresence(
                "",
                asserts_productLists.standardImageGallery_Arrows,
                "in the product block!",
                true);

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        asserts_csCartSettings.assertElementPresence(
                "",
                asserts_csCartSettings.pricesWithTaxes,
                "in the product block!",
                true);

        takeScreenShot("200 GS_CS_ProductLists_AllCategoryLists_Var2 - BlockWithProducts");
        stHomePage.selectLanguage("ar");
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("205 GS_CS_ProductLists_AllCategoryLists_Var2 - BlockWithProducts (RTL)");
        stHomePage.selectLanguage("ru");

        //Категория "Женская одежда"
        stHomePage.navigateToHorizontalMenu_WomanCloth();
        StCategoryPage stCategoryPage = new StCategoryPage();

        //Проверяем, что на странице отсутствует обесцвеченный товар
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.decolorizeOutOfStockProducts,
                "on the category 'Woman cloth', 'Grid list'!",
                false);

        //Проверяем, что кнопка "Избранное" присутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.button_AddToWishList,
                "on the category 'Woman cloth', 'Grid list'!",
                true);

        //Проверяем, что кнопка "Сравнить" присутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.button_AddToComparisonList,
                "on the category 'Woman cloth', 'Grid list'!",
                true);

        //Проверяем, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются СРАЗУ, а не при наведении на ячейку товара
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.buttonsAreDisplayedOnHover,
                "on the category 'Woman cloth', 'Grid list'!",
                false);

        //Проверяем, что у товаров присутствует галерея изображений и она стрелками
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.standardImageGallery_Arrows,
                "on the category 'Woman cloth', 'Grid list'!",
                true);

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.gridList,
                asserts_csCartSettings.pricesWithTaxes,
                "on the category 'Woman cloth', 'Grid list'!",
                true);

        stCategoryPage.hoverToProduct("Женская майка Nike");
        takeScreenShot_withScroll("210 GS_CS_ProductLists_AllCategoryLists_Var2 - WomanClothCategory");
        stHomePage.selectLanguage("ar");
        stCategoryPage.hoverToProduct("Tank Top");
        takeScreenShot_withScroll("215 GS_CS_ProductLists_AllCategoryLists_Var2 - WomanClothCategory (RTL)");
        stHomePage.selectLanguage("ru");

        //Категория "Телефоны"
        stHomePage.navigateToHorizontalMenu_Phones();

        //Проверяем, что у товаров присутствует общее значение рейтинга товара
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.commonValueOfProductRating,
                "on the category 'Phones', 'Grid list'!",
                true);

        //Проверяем, что кнопка "Избранное" присутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.button_AddToWishList,
                "on the category 'Phones', 'Grid list'!",
                true);

        //Проверяем, что кнопка "Сравнить" присутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.button_AddToComparisonList,
                "on the category 'Phones', 'Grid list'!",
                true);

        //Проверяем, что присутствует статус у кнопки "Избранное"
        stCategoryPage.addProductToWishList();
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.statusesForButton_AddToWishList,
                "on the category 'Phones', 'Grid list'!",
                true);

        //Проверяем, что присутствует статус у кнопки "Сравнение"
        stCategoryPage.addProductToComparisonList();
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.statusesForButton_AddToComparisonList,
                "on the category 'Phones', 'Grid list'!",
                true);

        //Проверяем, что у кнопки "В корзину" отображается статус в виде количества товаров
        stHomePage.logOutOnStorefront();
        stCategoryPage.buyGeneralProduct();
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.statusesForButton_AddToCart_Number,
                "on the category 'Phones', 'Grid list'!",
                true);

        //Проверяем, что у товаров присутствует новый вид Вариаций
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.prodVar_NewProductVariations,
                "on the category 'Phones', 'Grid list'!",
                true);

        //Проверяем, что тип отображения новых вариаций -- Миниатюры
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.prodVar_TypeOfVariationsView_Thumbnails,
                "on the category 'Phones', 'Grid list'!",
                true);

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.gridList,
                asserts_csCartSettings.pricesWithTaxes,
                "on the category 'Phones', 'Grid list'!",
                true);

        stHomePage.closeAllNotifications();
        stCategoryPage.hoverToProduct("Droid 3");
        takeScreenShot("220 GS_CS_ProductLists_AllCategoryLists_Var2 - PhonesCategory");
        stHomePage.selectLanguage("ar");
        stCategoryPage.hoverToProduct("Droid 3");
        takeScreenShot("225 GS_CS_ProductLists_AllCategoryLists_Var2 - PhonesCategory (RTL)");
        stHomePage.selectLanguage("ru");

        //Быстрый просмотр в категории "Телефоны"
        stCategoryPage.hoverToProduct("Droid 3");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-icon-right-open-thin")));

        //Проверяем, что присутствуют мини-иконки в виде галереи
        asserts_csCartSettings.assertElementPresence(
                "",
                asserts_csCartSettings.miniThumbnailImagesAsGallery,
                "in the quick view window!",
                true);

        takeScreenShot("230 GS_CS_ProductLists_AllCategoryLists_Var2 - QuickView");
        UtilsAdm.hoverNavigateAndClick(stCategoryPage.closeQuickView);
        stHomePage.selectLanguage("ar");
        stCategoryPage.hoverToProduct("Droid 3");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-icon-right-open-thin")));
        takeScreenShot("235 GS_CS_ProductLists_AllCategoryLists_Var2 - QuickView (RTL)");
        UtilsAdm.hoverNavigateAndClick(stCategoryPage.closeQuickView);

        //Других два шаблона страницы категории
        stCategoryPage.selectProductListView(stCategoryPage.listWithoutOptions_ProductListView);

        //Проверяем, что у товаров присутствует общее значение рейтинга товара
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.listWO,
                asserts_productLists.commonValueOfProductRating,
                "on the category 'Phones', 'List without options'!",
                true);

        //Проверяем, что присутствует статус у кнопки "Избранное"
        stCategoryPage.addProductToWishList();
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.listWO,
                asserts_productLists.statusesForButton_AddToWishList,
                "on the category 'Phones', 'List without options'!",
                true);

        //Проверяем, что присутствует статус у кнопки "Сравнение"
        stCategoryPage.addProductToComparisonList();
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.listWO,
                asserts_productLists.statusesForButton_AddToComparisonList,
                "on the category 'Phones', 'List without options'!",
                true);

        //Проверяем, что у кнопки "В корзину" отображается статус в виде количества товаров
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.listWO,
                asserts_productLists.statusesForButton_AddToCart_Number,
                "on the category 'Phones', 'List without options'!",
                true);

        //Проверяем, что у товаров присутствует новый вид Вариаций
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.listWO,
                asserts_productLists.prodVar_NewProductVariations,
                "on the category 'Phones', 'List without options'!",
                true);

        //Проверяем, что тип отображения новых вариаций -- Миниатюры
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.listWO,
                asserts_productLists.prodVar_TypeOfVariationsView_Thumbnails,
                "on the category 'Phones', 'List without options'!",
                true);

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.listWO,
                asserts_csCartSettings.pricesWithTaxes,
                "on the category 'Phones', 'List without options'!",
                true);

        UtilsAdm.makePause(2000);
        takeScreenShot_withScroll("240 GS_CS_ProductLists_AllCategoryLists_Var2 - ListWithoutOptions (RTL)");
        stHomePage.selectLanguage("ru");
        takeScreenShot_withScroll("245 GS_CS_ProductLists_AllCategoryLists_Var2 - ListWithoutOptions");
        stCategoryPage.selectProductListView(stCategoryPage.compactList_ProductListView);

        //Проверяем, что у товаров присутствует общее значение рейтинга товара
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.compactList,
                asserts_productLists.commonValueOfProductRating,
                "on the category 'Phones', 'Compact list'!",
                true);

        //Проверяем, что присутствует статус у кнопки "Избранное"
        stCategoryPage.addProductToWishList();
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.compactList,
                asserts_productLists.statusesForButton_AddToWishList,
                "on the category 'Phones', 'Compact list'!",
                true);

        //Проверяем, что присутствует статус у кнопки "Сравнение"
        stCategoryPage.addProductToComparisonList();
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.compactList,
                asserts_productLists.statusesForButton_AddToComparisonList,
                "on the category 'Phones', 'Compact list'!",
                true);

        //Проверяем, что у кнопки "В корзину" отображается статус в виде количества товаров
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.compactList,
                asserts_productLists.statusesForButton_AddToCart_Number,
                "on the category 'Phones', 'Compact list'!",
                true);

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.compactList,
                asserts_csCartSettings.pricesWithTaxes,
                "on the category 'Phones', 'Compact list'!",
                true);

        takeScreenShot_withScroll("250 GS_CS_ProductLists_AllCategoryLists_Var2 - CompactList_ProductListView");
        stHomePage.selectLanguage("ar");
        takeScreenShot_withScroll("255 GS_CS_ProductLists_AllCategoryLists_Var2 - CompactList_ProductListView (RTL)");

        System.out.println("productLists.GeneralSettings_ColorScheme_ProductLists_AllCategoryLists_Var2 passed successfully!");
    }
}