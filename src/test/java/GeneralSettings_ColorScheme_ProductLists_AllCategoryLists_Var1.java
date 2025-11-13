import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
        WebElement checkboxOutOfStockProducts = themeSettingsProductLists.setting_OutOfStockProducts;
        if (!checkboxOutOfStockProducts.isSelected())
            checkboxOutOfStockProducts.click();
        new Select(themeSettingsProductLists.setting_PriceDisplayFormat).selectByValue("row-mix");
        WebElement checkboxPriceAtTheTop = themeSettingsProductLists.setting_PriceAtTheTop;
        if (checkboxPriceAtTheTop.isSelected())
            checkboxPriceAtTheTop.click();
        WebElement checkboxProductRating = themeSettingsProductLists.setting_EmptyStarsOfProductRating;
        if (!checkboxProductRating.isSelected())
            checkboxProductRating.click();
        WebElement checkboxSettingCommonValueOfProductRating = themeSettingsProductLists.setting_CommonValueOfProductRating;
        if (checkboxSettingCommonValueOfProductRating.isSelected())
            checkboxSettingCommonValueOfProductRating.click();
        new Select(themeSettingsProductLists.setting_DisplayCartStatus).selectByValue("check-icon");
        WebElement checkboxSettingDisplayStatusesForButtons = themeSettingsProductLists.setting_DisplayStatusesForButtons;
        if (checkboxSettingDisplayStatusesForButtons.isSelected())
            checkboxSettingDisplayStatusesForButtons.click();
        WebElement checkboxSettingDisplayButtonComparisonList = themeSettingsProductLists.setting_DisplayButtonComparisonList;
        if (!checkboxSettingDisplayButtonComparisonList.isSelected())
            checkboxSettingDisplayButtonComparisonList.click();
        WebElement checkboxSettingDisplayButtonWishList = themeSettingsProductLists.setting_DisplayButtonWishList;
        if (!checkboxSettingDisplayButtonWishList.isSelected())
            checkboxSettingDisplayButtonWishList.click();
        WebElement checkboxSettingDisplayButtonsWhenHoveringMouse = themeSettingsProductLists.setting_DisplayButtonsWhenHoveringMouse;
        if (!checkboxSettingDisplayButtonsWhenHoveringMouse.isSelected())
            checkboxSettingDisplayButtonsWhenHoveringMouse.click();
        new Select(themeSettingsProductLists.setting_SwitchProductImageWhenHovering).selectByValue("lines");
        new Select(themeSettingsProductLists.withoutOptions_SwitchProductImageWhenHovering).selectByValue("lines");
        themeSettingsProductLists.setProductVariations_MaximumQuantityOfProductsVariations("10");
        new Select(themeSettingsProductLists.productVariations_TypeOfVariationsView).selectByValue("color");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Списки товаров"
        ColorSchemeSettings colorSchemeSettings = basicPage.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.fieldOfActiveColorScheme.click();
        colorSchemeSettings.activeColorScheme.click();
        makePause();
        colorSchemeSettings.tab_ProductLists.click();
        new Select(colorSchemeSettings.setting_FrameType).selectByValue("none");
        if (colorSchemeSettings.setting_ProductLists_MaskForProductImages.isSelected())
            colorSchemeSettings.setting_ProductLists_MaskForProductImages.click();
        new Select(colorSchemeSettings.setting_ProductLists_ElementsAlignment).selectByValue("use");
        if (!colorSchemeSettings.setting_ProductLists_ExpandGridItemOnHover.isSelected())
            colorSchemeSettings.setting_ProductLists_ExpandGridItemOnHover.click();
        new Select(colorSchemeSettings.setting_ProductLists_FontWeightForProductName).selectByValue("normal");
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

        SoftAssert softAssert = new SoftAssert();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что у товаров переключатель изображений с полосками
        softAssert.assertTrue(!assertsOnStorefront.gridList__SwitchProductImage_WithStripes.isEmpty(),
                "Gallery of product images is not with Stripes in the product block!");

        //Проверяем, что у товаров присутствуют пустые звёздочки рейтинга
        softAssert.assertTrue(!assertsOnStorefront.emptyStarsOfProductRating().isEmpty(),
                "There are no empty stars in the product block!");

        //Проверяем, что кнопка "Избранное" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToWishList.isEmpty(),
                "There is no button 'Add to wish list' in the product block!");

        //Проверяем, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToComparisonList.isEmpty(),
                "There is no button 'Add to comparison list' in the product block!");

        //Проверяем, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются при наведении на ячейку товара
        softAssert.assertTrue(!assertsOnStorefront.buttonsAreDisplayedOnHover.isEmpty(),
                "Buttons are not displayed when hovering over a product cell in the product block!");

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!assertsOnStorefront.pricesWithTaxes().isEmpty(),
                "There is no text of a product tax in the product block!");

        takeScreenShot("100 GS_CS_ProductLists_AllCategoryLists_Var1 - BlockWithProducts");
        stHomePage.selectLanguage_RTL();
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("105 GS_CS_ProductLists_AllCategoryLists_Var1 - BlockWithProducts (RTL)");
        stHomePage.selectLanguage_RU();

        //Категория "Женская одежда"
        stHomePage.navigateToHorizontalMenu_WomanCloth();

        //Проверяем, что на странице присутствует обесцвеченный товар
        softAssert.assertTrue(!assertsOnStorefront.decolorizeOutOfStockProducts.isEmpty(),
                "There is no decolorized product on the category 'Woman cloth'!");

        //Проверяем, что у товаров переключатель изображений с полосками
        softAssert.assertTrue(!assertsOnStorefront.gridList__SwitchProductImage_WithStripes.isEmpty(),
                "Gallery of product images is not with Stripes on the category 'Woman cloth'!");

        //Проверяем, что у товаров присутствуют пустые звёздочки рейтинга
        softAssert.assertTrue(!assertsOnStorefront.emptyStarsOfProductRating().isEmpty(),
                "There are no empty stars on the category 'Woman cloth'!");

        //Проверяем, что кнопка "Избранное" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToWishList.isEmpty(),
                "There is no button 'Add to wish list' on the category 'Woman cloth'!!");

        //Проверяем, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToComparisonList.isEmpty(),
                "There is no button 'Add to comparison list' on the category 'Woman cloth'!!");

        //Проверяем, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются при наведении на ячейку товара
        softAssert.assertTrue(!assertsOnStorefront.buttonsAreDisplayedOnHover.isEmpty(),
                "Buttons are not displayed when hovering over a product cell on the category 'Woman cloth'!");

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!assertsOnStorefront.pricesWithTaxes().isEmpty(),
                "There is no text of a product tax on the category 'Woman cloth'!");

        stCategoryPage.hoverToClothProduct();
        takeScreenShot_withScroll("110 GS_CS_ProductLists_AllCategoryLists_Var1 - WomanClothCategory");
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToClothProduct();
        takeScreenShot_withScroll("115 GS_CS_ProductLists_AllCategoryLists_Var1 - WomanClothCategory (RTL)");
        stHomePage.selectLanguage_RU();

        //Категория "Телефоны"
        stHomePage.navigateToHorizontalMenu_Phones();

        //Проверяем, что у товаров переключатель изображений с полосками
        softAssert.assertTrue(!assertsOnStorefront.gridList__SwitchProductImage_WithStripes.isEmpty(),
                "Gallery of product images is not with Stripes on the category 'Phones'!");

        //Проверяем, что у товаров присутствуют пустые звёздочки рейтинга
        softAssert.assertTrue(!assertsOnStorefront.emptyStarsOfProductRating().isEmpty(),
                "There are no empty stars on the category 'Phones'!");

        //Проверяем, что кнопка "Избранное" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToWishList.isEmpty(),
                "There is no button 'Add to wish list' on the category 'Phones'!");

        //Проверяем, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToComparisonList.isEmpty(),
                "There is no button 'Add to comparison list' on the category 'Phones'!");

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!assertsOnStorefront.pricesWithTaxes().isEmpty(),
                "There is no text of a product tax on the category 'Phones'!");

        //Проверяем, что у товаров присутствует новый вид Вариаций
        softAssert.assertTrue(!assertsOnStorefront.gridList__prodVar_MaximumQuantityOfProductsVariations().isEmpty(),
                "There is no new view of product variations on the category 'Phones', Grid list!");

        //Проверяем, что тип отображения новых вариаций -- Цвета
        softAssert.assertTrue(!assertsOnStorefront.gridList__prodVar_TypeOfVariationsView_Colors().isEmpty(),
                "Product variations are not with Colors on the category 'Phones', Grid list!");

        //Проверяем, что у кнопки "В корзину" отображается статус в виде иконки
        stHomePage.logOutOnStorefront();
        stCategoryPage.hoverToButtonAddToCart();
        stCategoryPage.button_GeneralAddToCart.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(8)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cm-notification-content.cm-notification-content-extended")));
        stCategoryPage.button_ContinueShopping.click();

        softAssert.assertTrue(!assertsOnStorefront.getStatusesForButtonAddToCartIcon().isEmpty(),
                "There is no status as 'Icon' for the button 'Add to cart' on the category 'Phones'!");

        if (!getDriver().findElements(By.cssSelector(".notification-content.alert")).isEmpty()) {
            for (int i = 0; i < stCategoryPage.notification_AlertSuccess.size(); i++) {
                stCategoryPage.closeNotification_AlertSuccess.click();
            }
        }
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("120 GS_CS_ProductLists_AllCategoryLists_Var1 - PhonesCategory");
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("125 GS_CS_ProductLists_AllCategoryLists_Var1 - PhonesCategory (RTL)");
        stHomePage.selectLanguage_RU();

        //Быстрый просмотр в категории "Телефоны"
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        stCategoryPage.hoverCloseQuickView();
        takeScreenShot_withScroll("130 GS_CS_ProductLists_AllCategoryLists_Var1 - QuickView");
        stCategoryPage.clickCloseQuickView();
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        stCategoryPage.hoverCloseQuickView();
        takeScreenShot_withScroll("135 GS_CS_ProductLists_AllCategoryLists_Var1 - QuickView (RTL)");
        stCategoryPage.clickCloseQuickView();

        //Других два шаблона страницы категории
        stCategoryPage.clickListWithoutOptions_ProductListView();

        //Проверяем, что у товаров переключатель изображений с полосками
        softAssert.assertTrue(!assertsOnStorefront.gridList__SwitchProductImage_WithStripes.isEmpty(),
                "Gallery of product images is not with Stripes on the category 'List without options'!");

        //Проверяем, что у товаров присутствуют пустые звёздочки рейтинга
        softAssert.assertTrue(!assertsOnStorefront.emptyStarsOfProductRating().isEmpty(),
                "There are no empty stars on the category 'List without options'!");

        //Проверяем, что кнопка "Избранное" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToWishList.isEmpty(),
                "There is no button 'Add to wish list' on the category 'List without options'!");

        //Проверяем, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToComparisonList.isEmpty(),
                "There is no button 'Add to comparison list' on the category 'List without options'!");

        //Проверяем, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются при наведении на ячейку товара
        softAssert.assertTrue(!assertsOnStorefront.buttonsAreDisplayedOnHover.isEmpty(),
                "Buttons are not displayed when hovering over a product cell on the category 'List without options'!");

        //Проверяем, что у кнопки "В корзину" отображается статус в виде иконки
        softAssert.assertTrue(!assertsOnStorefront.getStatusesForButtonAddToCartIcon().isEmpty(),
                "There is no status as 'Icon' for the button 'Add to cart' on the category 'List without options'!");

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!assertsOnStorefront.pricesWithTaxes().isEmpty(),
                "There is no text of a product tax on the category 'List without options'!");

        //Проверяем, что у товаров присутствует новый вид Вариаций
        softAssert.assertTrue(!assertsOnStorefront.listWithoutOptions__prodVar_MaximumQuantityOfProductsVariations().isEmpty(),
                "There is no new view of product variations on the category 'Phones', ListWithoutOptions!");

        //Проверяем, что тип отображения новых вариаций -- Цвета
        softAssert.assertTrue(!assertsOnStorefront.listWithoutOptions__prodVar_TypeOfVariationsView_Colors().isEmpty(),
                "Product variations are not with Colors on the category 'Phones', ListWithoutOptions!");

        takeScreenShot_withScroll("140 GS_CS_ProductLists_AllCategoryLists_Var1 - ListWithoutOptions (RTL)");
        Actions actions = new Actions(DriverProvider.getDriver());
        actions.moveToElement(DriverProvider.getDriver().findElement(By.cssSelector(".ty-select-wrapper"))).build().perform();
        makePause();
        stHomePage.selectLanguage_RU();
        takeScreenShot_withScroll("145 GS_CS_ProductLists_AllCategoryLists_Var1 - ListWithoutOptions");
        stCategoryPage.clickCompactList_ProductListView();

        //Проверяем, что у товаров присутствуют пустые звёздочки рейтинга
        softAssert.assertTrue(!assertsOnStorefront.emptyStarsOfProductRating().isEmpty(),
                "There are no empty stars on the category 'Compact list'!");

        //Проверяем, что кнопка "Избранное" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToWishList.isEmpty(),
                "There is no button 'Add to wish list' on the category 'Compact list'!");

        //Проверяем, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToComparisonList.isEmpty(),
                "There is no button 'Add to comparison list' on the category 'Compact list'!");

        //Проверяем, что у кнопки "В корзину" отображается статус в виде иконки
        softAssert.assertTrue(!assertsOnStorefront.getStatusesForButtonAddToCartIcon().isEmpty(),
                "There is no status as 'Icon' for the button 'Add to cart' on the category 'Compact list'!");

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!assertsOnStorefront.pricesWithTaxes().isEmpty(),
                "There is no text of a product tax on the category 'Compact list'!");

        takeScreenShot_withScroll("150 GS_CS_ProductLists_AllCategoryLists_Var1 - CompactList_ProductListView");
        stHomePage.selectLanguage_RTL();
        takeScreenShot_withScroll("155 GS_CS_ProductLists_AllCategoryLists_Var1 - CompactList_ProductListView (RTL)");
        softAssert.assertAll();
        System.out.println("GeneralSettings_ColorScheme_ProductLists_AllCategoryLists_Var1 passed successfully!");
    }
}