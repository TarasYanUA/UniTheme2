package productBlocks;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.asserts.Asserts_CsCartSettings;
import taras.asserts.Asserts_ThemeSettings_ProductLists;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

/*
1) Настройки блока товаров "Распродажа"
Шаблон                      -- Мелкие элементы
Показать номер элемента     -- y
Заполнение                  -- Товары со скидкой
Макс. число элементов       -- 13
Спрятать кнопку добавления товара в корзину     -- n

2.1. UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Формат отображения цен                          -- Вариант 1
Отображать цену вверху                          -- y
Отображать пустые звёзды рейтинга товара        -- y
Отображать общее значение рейтинга товара       -- y
Отображать "Вы экономите"                       -- Сокращенный вид

2.2. UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Мелкие элементы"
Количество строк в названии товара              -- 4
Отображать код товара                           -- n
Отображать статус наличия                       -- n
Отображать модификатор количества               -- n
Отображать кнопку "Купить"                      -- Только текст

3) UniTheme2 -- Настройки цветосхемы            -- вкладка "Списки товаров":
Тип обрамления товара в сетке                   -- Рамка без внешних отступов
Добавить фон/маску для изображений товара       -- y
Насыщенность шрифта для названия товара         -- Жирный

4) Настраиваем налог для всех товаров

5) Задаём товару "Wildwood city classic" длинное название
*/

public class ProductBlock_SmallItems_Var3 extends TestRunner implements DisableLazyLoadFromSection {
    String blockID;
    Asserts_ThemeSettings_ProductLists asserts_productLists = new Asserts_ThemeSettings_ProductLists();
    Asserts_CsCartSettings asserts_csCartSettings = new Asserts_CsCartSettings();

    @Test(priority = 1)
    public void setConfigurationsFor_ProductBlock_SmallItems_Var3() {
        BasicPage basicPage = new BasicPage();

        //Настраиваем блок товаров "Распродажа"
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        disableLazyLoadFromSection("Распродажа");   //Выключаем LazyLoad в секции с блоком
        blockID = getBlockID("Распродажа");         //Получаем ID нужного блока товаров
        asserts_productLists.setBlockID(blockID);             //Передаём blockID в класс с проверками
        asserts_csCartSettings.setBlockID(blockID);           //Передаём blockID в класс с проверками
        layoutPage.navigateTo_BlockSettings("Распродажа");
        new Select(layoutPage.setting_BlockTemplate).selectByValue("blocks/products/products_small_items.tpl");
        UtilsAdm.makePause(1000);
        layoutPage.button_SettingsOfTemplate.click();
        UtilsAdm.setCheckboxState(layoutPage.checkbox_ShowItemNumber, true);
        UtilsAdm.hoverNavigateAndClick(layoutPage.tabOfBlock_Content);
        new Select(layoutPage.setting_Filling).selectByValue("on_sale");
        UtilsAdm.clickAndType(layoutPage.field_Limit, "13");
        UtilsAdm.hoverNavigateAndClick(layoutPage.tabOfBlock_Settings);
        UtilsAdm.setCheckboxState(layoutPage.checkbox_HideAddToCartButton, false);
        layoutPage.button_saveBlock.click();

        //Работаем с настройками темы
        ThemeSettings_ProductLists themeSettingsProductLists = basicPage.navigateTo_ThemeSettings_tabProductLists();
        new Select(themeSettingsProductLists.setting_PriceDisplayFormat).selectByValue("col");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_PriceAtTheTop, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_EmptyStarsOfProductRating, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_CommonValueOfProductRating, true);
        new Select(themeSettingsProductLists.setting_ShowYouSave).selectByValue("short");
        UtilsAdm.hoverOverElement(themeSettingsProductLists.smallItems_NumberOfLinesInProductName);
        new Select(themeSettingsProductLists.smallItems_NumberOfLinesInProductName).selectByValue("4");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.smallItems_ProductCode, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.smallItems_AvailabilityStatus, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.smallItems_QuantityChanger, false);
        new Select(themeSettingsProductLists.smallItems_AddToCartButton).selectByValue("text");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Списки товаров"
        ColorSchemeSettings colorSchemeSettings = basicPage.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.selectActiveColorScheme();
        colorSchemeSettings.tab_ProductLists.click();
        new Select(colorSchemeSettings.setting_FrameType).selectByValue("solid_with_margins");
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_ProductLists_MaskForProductImages, true);
        new Select(colorSchemeSettings.setting_ProductLists_FontWeightForProductName).selectByValue("bold");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем налог для всех товаров
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.setTaxesForAllProducts();

        //Задаём товару "Wildwood city classic" длинное название
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Wildwood city classic");
        UtilsAdm.clickAndType(productSettings.field_ProductName,
                "Wildwood city classic - Мы завезли настоящую американскую классику! Круизеры Drifter. Lorem Ipsum используют потому, что тот обеспечивает более или менее стандартное заполнение шаблона");
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsFor_ProductBlock_SmallItems_Var3")
    public void checkProductBlock_SmallItems_Var3(){
        BasicPage basicPage = new BasicPage();

        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Блок товаров "Распродажа" на главной странице
        stHomePage.scrollToBlockWithProducts();
        stHomePage.openProductBlock("Распродажа");

        //Проверяем, что номера элементов отображаются в блоке товаров
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.productBlock,
                asserts_csCartSettings.block_ShowItemNumber,
                "in the product block!",
                true);

        //Проверяем, что у товаров присутствуют пустые звёздочки рейтинга
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.emptyStarsOfProductRating,
                "in the product block!",
                true);

        //Проверяем, что у товаров присутствует общее значение рейтинга товара
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.commonValueOfProductRating,
                "in the product block!",
                true);

        //Проверяем, что текст "Вы экономите" присутствует и "Сокращенный вид"
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.text_YouSave_Short,
                "in the product block!",
                true);

        //Проверяем, что код товара отсутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.productCode,
                "in the product block!",
                false);

        //Проверяем, что статус наличия отсутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.availabilityStatus,
                "in the product block!",
                false);

        //Проверяем, что модификатор количества отсутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.quantityChanger,
                "in the product block!",
                false);

        //Проверяем, что кнопка "Купить" в виде "Только текст"
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.showAddToCartButton_TextOnly,
                "in the product block!",
                true);

        //Проверяем, что Количество строк в названии товара -- 4
        asserts_productLists.assertNumberOfElements(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.smallItems_NumberOfLinesInProductName,
                4,
                "in the product block!");

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.productBlock,
                asserts_csCartSettings.pricesWithTaxes,
                "in the product block!",
                true);

        takeScreenShot("ProductBlock_SmallItems_Var3");
        stHomePage.selectLanguage("ar");
        stHomePage.scrollToBlockWithProducts();
        stHomePage.openProductBlock("On Sale");
        takeScreenShot("ProductBlock_SmallItems_Var3 (RTL)");

        System.out.println("ProductBlock_SmallItems_Var3 has passed successfully!");
    }
}