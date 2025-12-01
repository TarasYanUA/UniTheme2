package productBlocks;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.*;
import taras.asserts.Asserts_ThemeSettings_ProductLists;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

/*
1) Настройки блока товаров "Распродажа"
Шаблон                      -- Мелкие элементы
Показать номер элемента     -- n
Заполнение                  -- Товары со скидкой
Макс. число элементов       -- 17
Спрятать кнопку добавления товара в корзину     -- n

2.1. UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Формат отображения цен                          -- Вариант 5
Отображать цену вверху                          -- n
Отображать пустые звёзды рейтинга товара        -- y
Отображать общее значение рейтинга товара       -- n
Отображать "Вы экономите"                       -- Полный вид

2.2. UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Мелкие элементы"
Количество строк в названии товара              -- 2
Отображать код товара                           -- y
Отображать статус наличия                       -- y
Отображать модификатор количества               -- y
Отображать кнопку "Купить"                      -- Иконка корзины и текст

3) UniTheme2 -- Настройки цветосхемы            -- вкладка "Списки товаров":
Тип обрамления товара в сетке                   -- Рамка с внешними отступами
Добавить фон/маску для изображений товара       -- n
Использовать выравнивание элементов в товарной сетке --	y
Эффект увеличения ячейки при наведении          -- y
Насыщенность шрифта для названия товара         -- Нормальный

4) Настраиваем налог для всех товаров

5) Задаём товару "Wildwood city classic" длинное название
*/

public class ProductBlock_SmallItems_Var1 extends TestRunner implements DisableLazyLoadFromSection {
    String blockID;

    @Test(priority = 1)
    public void setConfigurationsFor_ProductBlock_SmallItems_Var1() {
        BasicPage basicPage = new BasicPage();

        //Настраиваем блок товаров "Распродажа"
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        disableLazyLoadFromSection("Распродажа");
        blockID = getBlockID("Распродажа");  //Получаем ID нужного блока товаров
        layoutPage.navigateTo_BlockSettings("Распродажа");
        new Select(layoutPage.setting_BlockTemplate).selectByValue("blocks/products/products_small_items.tpl");
        UtilsAdm.makePause(1000);
        layoutPage.button_SettingsOfTemplate.click();
        UtilsAdm.setCheckboxState(layoutPage.checkbox_ShowItemNumber, false);
        UtilsAdm.hoverNavigateAndClick(layoutPage.tabOfBlock_Content);
        new Select(layoutPage.setting_Filling).selectByValue("on_sale");
        UtilsAdm.clickAndType(layoutPage.field_Limit, "17");
        UtilsAdm.hoverNavigateAndClick(layoutPage.tabOfBlock_Settings);
        UtilsAdm.setCheckboxState(layoutPage.checkbox_HideAddToCartButton, false);
        layoutPage.button_saveBlock.click();

        //Работаем с настройками темы
        ThemeSettings_ProductLists themeSettingsProductLists = basicPage.navigateTo_ThemeSettings_tabProductLists();
        new Select(themeSettingsProductLists.setting_PriceDisplayFormat).selectByValue("col-rev");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_PriceAtTheTop, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_EmptyStarsOfProductRating, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_CommonValueOfProductRating, false);
        new Select(themeSettingsProductLists.setting_ShowYouSave).selectByValue("full");
        UtilsAdm.hoverOverElement(themeSettingsProductLists.smallItems_NumberOfLinesInProductName);
        new Select(themeSettingsProductLists.smallItems_NumberOfLinesInProductName).selectByValue("2");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.smallItems_ProductCode, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.smallItems_AvailabilityStatus, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.smallItems_QuantityChanger, true);
        new Select(themeSettingsProductLists.smallItems_AddToCartButton).selectByValue("icon_and_text");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Списки товаров"
        ColorSchemeSettings colorSchemeSettings = basicPage.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.selectActiveColorScheme();
        colorSchemeSettings.tab_ProductLists.click();
        new Select(colorSchemeSettings.setting_FrameType).selectByValue("solid_with_margins");
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_ProductLists_MaskForProductImages, false);
        new Select(colorSchemeSettings.setting_ProductLists_FontWeightForProductName).selectByValue("normal");
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

    @Test(priority = 2, dependsOnMethods = "setConfigurationsFor_ProductBlock_SmallItems_Var1")
    public void checkProductBlock_SmallItems_Var1() {
        BasicPage basicPage = new BasicPage();
        SoftAssert softAssert = new SoftAssert();
        Asserts_ThemeSettings_ProductLists asserts_productLists = new Asserts_ThemeSettings_ProductLists();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Блок товаров "Распродажа" на главной странице
        stHomePage.scrollToBlockWithProducts();
        stHomePage.openProductBlock("Распродажа");

        //Проверяем, что у товаров присутствуют пустые звёздочки рейтинга
        asserts_productLists.assertElementInProductBlock(asserts_productLists.emptyStarsOfProductRating, blockID, true);

        //Проверяем, что у товаров отсутствует общее значение рейтинга товара
        softAssert.assertFalse(!assertsOnStorefront.getCommonValueOfProductRating(blockID).isEmpty(),
                "There is common value of product rating but shouldn't in the product block!");

        //Проверяем, что текст "Вы экономите" присутствует и "Полный вид"
        softAssert.assertTrue(!assertsOnStorefront.getText_YouSave_Full(blockID).isEmpty(),
                "The text 'You save' is not Full or missed in the product block!");

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!assertsOnStorefront.getPricesWithTaxes(blockID).isEmpty(),
                "There is no text of a product tax in the product block!");

        //Проверяем, что Количество строк в названии товара -- 2
        softAssert.assertTrue(!assertsOnStorefront.getNumberOfLinesInProductName_SmallItems(blockID, 2).isEmpty(),
                "Number of lines in the product name is not 2!");

        //Проверяем, что код товара присутствует
        softAssert.assertTrue(!assertsOnStorefront.getProductCode(blockID).isEmpty(),
                "There is no product code in the product block!");

        //Проверяем, что статус наличия присутствует
        softAssert.assertTrue(!assertsOnStorefront.getAvailabilityStatus(blockID).isEmpty(),
                "There is no availability status in the product block!");

        //Проверяем, что модификатор количества присутствует
        softAssert.assertTrue(!assertsOnStorefront.getQuantityChanger(blockID).isEmpty(),
                "There is no quantity Changer in the product block!");

        //Проверяем, что кнопка "Купить" в виде "Иконка корзины и текст"
        softAssert.assertTrue(!assertsOnStorefront.getShowAddToCartButton_IconOnly(blockID).isEmpty()
                        && !assertsOnStorefront.getShowAddToCartButton_TextOnly(blockID).isEmpty(),
                "The button 'Add to cart' does not have a view 'Icon of the Cart and text'!");

        takeScreenShot("ProductBlock_SmallItems_Var1");
        stHomePage.selectLanguage("ar");
        stHomePage.scrollToBlockWithProducts();
        stHomePage.openProductBlock("On Sale");
        takeScreenShot("ProductBlock_SmallItems_Var1 (RTL)");

        softAssert.assertAll();
        System.out.println("ProductBlock_SmallItems_Var1 has passed successfully!");
    }
}