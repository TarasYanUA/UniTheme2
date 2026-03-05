package productLists;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.asserts.Asserts_CsCartSettings;
import taras.asserts.Asserts_ThemeSettings_ProductLists;
import taras.constants.DriverProvider;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

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
    * Раздел Модуль "Вариации товаров":
Максимальное количество отображаемых вариаций товара -- 10
Тип отображения вариаций -- Цвета

3) UniTheme2 -- Настройки цветосхемы -- вкладка "Списки товаров":
Тип обрамления товара в сетке --	Без рамки
Добавить фон/маску для изображений товара --	нет
Использовать выравнивание элементов в товарной сетке --	да
Эффект увеличения ячейки при наведении --	да
Насыщенность шрифта для названия товара --	Нормальный

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

public class GeneralSettings_ColorScheme_ProductLists_AllCategoryLists_Var1 extends TestRunner {

    @Test(priority = 1)
    public void setConfigurationsForProductLists_AllCategoryLists_Var1() {
        //Настраиваем макет для тест-кейса
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Lightv2.click();
        layoutPage.setLayoutAsDefault();

        //Работаем с CS-Cart настройками
        CsCartSettings csCartSettings = basicPage.navigateToAppearanceSettings();
        UtilsAdm.setCheckboxState(csCartSettings.setting_DisplayPricesWithTaxesOnCategoryAndProductPages, true);
        UtilsAdm.setCheckboxState(csCartSettings.setting_ThumbnailsGallery, false);
        UtilsAdm.setCheckboxState(csCartSettings.setting_QuickView, true);
        basicPage.clickSaveButtonOfSettings();

        //Работаем с настройками темы (в основном идут по умолчанию)
        ThemeSettings_ProductLists themeSettingsProductLists = basicPage.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.tabProductLists.click();
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_OutOfStockProducts, true);
        new Select(themeSettingsProductLists.setting_PriceDisplayFormat).selectByValue("row-mix");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_PriceAtTheTop, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_EmptyStarsOfProductRating, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_CommonValueOfProductRating, false);
        new Select(themeSettingsProductLists.setting_DisplayCartStatus).selectByValue("check-icon");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayStatusesForButtons, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayButtonComparisonList, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayButtonWishList, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayButtonsWhenHoveringMouse, true);
        new Select(themeSettingsProductLists.setting_SwitchProductImageWhenHovering).selectByValue("lines");
        new Select(themeSettingsProductLists.withoutOptions_SwitchProductImageWhenHovering).selectByValue("lines");
        new Select(themeSettingsProductLists.compactList_buttonAddToCart).selectByValue("icon");
        themeSettingsProductLists.setProductVariations_MaximumQuantityOfProductsVariations("10");
        new Select(themeSettingsProductLists.productVariations_TypeOfVariationsView).selectByValue("color");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Списки товаров"
        ColorSchemeSettings colorSchemeSettings = basicPage.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.selectActiveColorScheme();
        colorSchemeSettings.tab_ProductLists.click();
        new Select(colorSchemeSettings.setting_FrameType).selectByValue("none");
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_ProductLists_MaskForProductImages, false);
        new Select(colorSchemeSettings.setting_ProductLists_ElementsAlignment).selectByValue("use");
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_ProductLists_ExpandGridItemOnHover, true);
        new Select(colorSchemeSettings.setting_ProductLists_FontWeightForProductName).selectByValue("normal");
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
        UtilsAdm.clickAndType(productSettings.field_ProductName, "Droid 3");
        productSettings.selectAllVariations();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_AllCategoryLists_Var1")
    public void checkProductLists_AllCategoryLists_Var1() {
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        StCategoryPage stCategoryPage = new StCategoryPage();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();

        Asserts_ThemeSettings_ProductLists asserts_productLists = new Asserts_ThemeSettings_ProductLists();
        Asserts_CsCartSettings asserts_csCartSettings = new Asserts_CsCartSettings();

        //Проверяем, что у товаров присутствуют пустые звёздочки рейтинга
        asserts_productLists.assertElementPresence(
                "",
                asserts_productLists.emptyStarsOfProductRating,
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

        //Проверяем, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются при наведении на ячейку товара
        asserts_productLists.assertElementPresence(
                "",
                asserts_productLists.buttonsAreDisplayedOnHover,
                "in the product block!",
                true);

        //Проверяем, что у товаров переключатель изображений с полосками
        asserts_productLists.assertElementPresence(
                "",
                asserts_productLists.switchProductImage_withStripes,
                "in the product block!",
                true);

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        asserts_csCartSettings.assertElementPresence(
                "",
                asserts_csCartSettings.pricesWithTaxes,
                "in the product block!",
                true);

        takeScreenShot("100 GS_CS_ProductLists_AllCategoryLists_Var1 - BlockWithProducts");
        stHomePage.selectLanguage("ar");
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("105 GS_CS_ProductLists_AllCategoryLists_Var1 - BlockWithProducts (RTL)");
        stHomePage.selectLanguage("ru");

        //Категория "Женская одежда"
        stHomePage.navigateToHorizontalMenu_WomanCloth();

        //Проверяем, что на странице присутствует обесцвеченный товар
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.decolorizeOutOfStockProducts,
                "on the category 'Woman cloth', 'Grid list'!",
                true);

        //Проверяем, что у товаров присутствуют пустые звёздочки рейтинга
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.emptyStarsOfProductRating,
                "on the category 'Woman cloth', 'Grid list'!",
                true);

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

        //Проверяем, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются при наведении на ячейку товара
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.buttonsAreDisplayedOnHover,
                "on the category 'Woman cloth', 'Grid list'!",
                true);

        //Проверяем, что у товаров переключатель изображений с полосками
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.switchProductImage_withStripes,
                "on the category 'Woman cloth', 'Grid list'!",
                true);

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.gridList,
                asserts_csCartSettings.pricesWithTaxes,
                "on the category 'Woman cloth', 'Grid list'!",
                true);

        stCategoryPage.hoverToProduct("Женская майка Nike");
        takeScreenShot_withScroll("110 GS_CS_ProductLists_AllCategoryLists_Var1 - WomanClothCategory");
        stHomePage.selectLanguage("ar");
        stCategoryPage.hoverToProduct("Tank Top");
        takeScreenShot_withScroll("115 GS_CS_ProductLists_AllCategoryLists_Var1 - WomanClothCategory (RTL)");
        stHomePage.selectLanguage("ru");

        //Категория "Телефоны"
        stHomePage.navigateToHorizontalMenu_Phones();

        //Проверяем, что у товаров присутствуют пустые звёздочки рейтинга
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.emptyStarsOfProductRating,
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

        //Проверяем, что у товаров переключатель изображений с полосками
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.switchProductImage_withStripes,
                "on the category 'Phones', 'Grid list'!",
                true);

        //Проверяем, что у товаров присутствует новый вид Вариаций
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.prodVar_NewProductVariations,
                "on the category 'Phones', 'Grid list'!",
                true);

        //Проверяем, что тип отображения новых вариаций -- Цвета
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.gridList,
                asserts_productLists.prodVar_TypeOfVariationsView_Colors,
                "on the category 'Phones', 'Grid list'!",
                true);

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.gridList,
                asserts_csCartSettings.pricesWithTaxes,
                "on the category 'Phones', 'Grid list'!",
                true);

        //Проверяем, что у кнопки "В корзину" отображается статус в виде иконки
        stHomePage.logOutOnStorefront();
        stCategoryPage.buyGeneralProduct();
        asserts_productLists.assertElementPresence(
                "",
                asserts_productLists.getStatusesForButtonAddToCartIcon(),
                "on the category 'Phones', 'Grid list'!",
                true);

        stHomePage.closeNotification_AlertSuccess();
        stCategoryPage.hoverToProduct("Droid 3");
        takeScreenShot("120 GS_CS_ProductLists_AllCategoryLists_Var1 - PhonesCategory");
        stHomePage.selectLanguage("ar");
        stCategoryPage.hoverToProduct("Droid 3");
        takeScreenShot("125 GS_CS_ProductLists_AllCategoryLists_Var1 - PhonesCategory (RTL)");
        stHomePage.selectLanguage("ru");

        //Быстрый просмотр в категории "Телефоны"
        stCategoryPage.hoverToProduct("Droid 3");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot_withScroll("130 GS_CS_ProductLists_AllCategoryLists_Var1 - QuickView");
        UtilsAdm.hoverNavigateAndClick(stCategoryPage.closeQuickView);
        stHomePage.selectLanguage("ar");
        stCategoryPage.hoverToProduct("Droid 3");
        stCategoryPage.clickQuickViewOfPhoneProduct();
        takeScreenShot_withScroll("135 GS_CS_ProductLists_AllCategoryLists_Var1 - QuickView (RTL)");
        UtilsAdm.hoverNavigateAndClick(stCategoryPage.closeQuickView);

        //Других два шаблона страницы категории "Телефоны"
        stCategoryPage.selectProductListView(stCategoryPage.listWithoutOptions_ProductListView);

        //Проверяем, что у товаров присутствуют пустые звёздочки рейтинга
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.listWO,
                asserts_productLists.emptyStarsOfProductRating,
                "on the category 'Phones', 'List without options'!",
                true);

        //Проверяем, что кнопка "Избранное" присутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.listWO,
                asserts_productLists.button_AddToWishList,
                "on the category 'Phones', 'List without options'!",
                true);

        //Проверяем, что кнопка "Сравнить" присутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.listWO,
                asserts_productLists.button_AddToComparisonList,
                "on the category 'Phones', 'List without options'!",
                true);

        //Проверяем, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются при наведении на ячейку товара
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.listWO,
                asserts_productLists.buttonsAreDisplayedOnHover,
                "on the category 'Phones', 'List without options'!",
                true);

        //Проверяем, что у товаров переключатель изображений с полосками
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.listWO,
                asserts_productLists.switchProductImage_withStripes,
                "on the category 'Phones', 'List without options'!",
                true);

        //Проверяем, что у товаров присутствует новый вид Вариаций
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.listWO,
                asserts_productLists.prodVar_NewProductVariations,
                "on the category 'Phones', 'List without options'!",
                true);

        //Проверяем, что тип отображения новых вариаций -- Цвета
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.listWO,
                asserts_productLists.prodVar_TypeOfVariationsView_Colors,
                "on the category 'Phones', 'List without options'!",
                true);

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.listWO,
                asserts_csCartSettings.pricesWithTaxes,
                "on the category 'Phones', 'List without options'!",
                true);

        //Проверяем, что у кнопки "В корзину" отображается статус в виде иконки
        asserts_productLists.assertElementPresence(
                "",
                asserts_productLists.getStatusesForButtonAddToCartIcon(),
                "on the category 'Phones', 'List without options'!",
                true);

        takeScreenShot_withScroll("140 GS_CS_ProductLists_AllCategoryLists_Var1 - ListWithoutOptions (RTL)");
        UtilsAdm.hoverOverElement(DriverProvider.getDriver().findElement(By.cssSelector(".ty-select-wrapper")));
        UtilsAdm.makePause(2000);
        stHomePage.selectLanguage("ru");
        takeScreenShot_withScroll("145 GS_CS_ProductLists_AllCategoryLists_Var1 - ListWithoutOptions");
        stCategoryPage.selectProductListView(stCategoryPage.compactList_ProductListView);

        //Проверяем, что у товаров присутствуют пустые звёздочки рейтинга
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.compactList,
                asserts_productLists.emptyStarsOfProductRating,
                "on the category 'Phones', 'Compact list'!",
                true);

        //Проверяем, что кнопка "Избранное" присутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.compactList,
                asserts_productLists.button_AddToWishList,
                "on the category 'Phones', 'Compact list'!",
                true);

        //Проверяем, что кнопка "Сравнить" присутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.compactList,
                asserts_productLists.button_AddToComparisonList,
                "on the category 'Phones', 'Compact list'!",
                true);

        //Проверяем, что у кнопки "В корзину" отображается статус в виде иконки
        asserts_productLists.assertElementPresence(
                "",
                asserts_productLists.getStatusesForButtonAddToCartIcon(),
                "on the category 'Phones', 'Compact list'!",
                true);

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.compactList,
                asserts_csCartSettings.pricesWithTaxes,
                "on the category 'Phones', 'Compact list'!",
                true);

        takeScreenShot_withScroll("150 GS_CS_ProductLists_AllCategoryLists_Var1 - CompactList_ProductListView");
        stHomePage.selectLanguage("ar");
        takeScreenShot_withScroll("155 GS_CS_ProductLists_AllCategoryLists_Var1 - CompactList_ProductListView (RTL)");

        System.out.println("productLists.GeneralSettings_ColorScheme_ProductLists_AllCategoryLists_Var1 passed successfully!");
    }
}