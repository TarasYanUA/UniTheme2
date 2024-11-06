package productBlocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.ColorSchemeSettings;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.DisableLazyLoadFromSection;
import taras.adminPanel.ThemeSettings_ProductLists;
import taras.constants.DriverProvider;
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

2.1) UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Формат отображения цен                          -- Вариант 4
Отображать цену вверху                          -- n
Отображать пустые звёзды рейтинга товара        -- y
Отображать общее значение рейтинга товара       -- n
Отображать "Вы экономите"                       -- Полный вид

2.2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Мелкие элементы"
Отображать код товара                           -- y
Отображать статус наличия                       -- y
Отображать модификатор количества               -- y

3) UniTheme2 -- Настройки цветосхемы            -- вкладка "Списки товаров":
Тип обрамления товара в сетке                   -- Рамка с внешними отступами
Добавить фон/маску для изображений товара       -- n
Использовать выравнивание элементов в товарной сетке --	y
Эффект увеличения ячейки при наведении          -- y
Насыщенность шрифта для названия товара         -- Нормальный

4) Настраиваем налог для всех товаров
*/

public class ProductBlock_SmallItems_Var1 extends TestRunner implements DisableLazyLoadFromSection {
    String blockID;

    @Test(priority = 1)
    public void setConfigurationsFor_ProductBlock_SmallElements_Var1() {
        CsCartSettings csCartSettings = new CsCartSettings();

        //Настраиваем блок товаров "Распродажа"
        disableLazyLoadFromSection("Распродажа");   //Выключаем LazyLoad в секции с блоком
        makePause();
        blockID = getBlockID("Распродажа");  //Получаем ID нужного блока товаров
        csCartSettings.navigateToBlockSettings("Распродажа");
        csCartSettings.selectSetting_BlockTemplate("blocks/products/products_small_items.tpl");
        makePause();
        csCartSettings.button_SettingsOfTemplate.click();
        if (csCartSettings.checkbox_ShowItemNumber.isSelected())
            csCartSettings.checkbox_ShowItemNumber.click();
        csCartSettings.tabOfBlock_Content.click();
        csCartSettings.selectSetting_Filling("on_sale");
        csCartSettings.clickAndType_Field_Limit("17");
        csCartSettings.tabOfBlock_BlockSettings.click();
        if (csCartSettings.checkbox_HideAddToCartButton.isSelected())
            csCartSettings.checkbox_HideAddToCartButton.click();
        csCartSettings.button_saveBlock.click();

        //Работаем с настройками темы
        ThemeSettings_ProductLists themeSettingsProductLists = csCartSettings.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.selectSettingPriceDisplayFormat("row-mix");

        if (themeSettingsProductLists.settingPriceAtTheTop.isSelected())
            themeSettingsProductLists.settingPriceAtTheTop.click();
        if (!themeSettingsProductLists.settingEmptyStarsOfProductRating.isSelected())
            themeSettingsProductLists.settingEmptyStarsOfProductRating.click();
        if (themeSettingsProductLists.settingCommonValueOfProductRating.isSelected())
            themeSettingsProductLists.settingCommonValueOfProductRating.click();
        themeSettingsProductLists.selectSettingShowYouSave("full");

        Actions scroll = new Actions(DriverProvider.getDriver());
        scroll.scrollToElement(themeSettingsProductLists.smallItems_NumberOfLinesInProductName);
        scroll.perform();
        themeSettingsProductLists.selectSmallItems_NumberOfLinesInProductName("2");
        if (!themeSettingsProductLists.smallItems_ProductCode.isSelected())
            themeSettingsProductLists.smallItems_ProductCode.click();
        if (!themeSettingsProductLists.smallItems_AvailabilityStatus.isSelected())
            themeSettingsProductLists.smallItems_AvailabilityStatus.click();
        if (!themeSettingsProductLists.smallItems_QuantityChanger.isSelected())
            themeSettingsProductLists.smallItems_QuantityChanger.click();
        themeSettingsProductLists.selectSmallItems_AddToCartButton("icon_and_text");
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Списки товаров"
        ColorSchemeSettings colorSchemeSettings = csCartSettings.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.fieldOfActiveColorScheme.click();
        colorSchemeSettings.activeColorScheme.click();
        makePause();
        colorSchemeSettings.tab_ProductLists.click();
        colorSchemeSettings.selectSetting_FrameType("solid_with_margins");
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
        csCartSettings.setTaxesForAllProducts();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsFor_ProductBlock_SmallElements_Var1")
    public void checkProductBlock_SmallElements_Var1(){
        CsCartSettings csCartSettings = new CsCartSettings();
        SoftAssert softAssert = new SoftAssert();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        StHomePage stHomePage = csCartSettings.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Блок товаров "Распродажа" на главной странице
        stHomePage.scrollToBlockWithProducts();
        WebElement tab_OnSale = DriverProvider.getDriver().findElement(By.xpath("//span[@class='ty-tabs__span'][text()='Распродажа']"));
        tab_OnSale.click();

        //Проверяем, что у товаров присутствуют пустые звёздочки рейтинга
        softAssert.assertTrue(!assertsOnStorefront.getEmptyStarsOfProductRating(blockID).isEmpty(),
                "There are no empty stars in the product block!");

        //Проверяем, что у товаров отсутствует общее значение рейтинга товара
        softAssert.assertFalse(!assertsOnStorefront.getCommonValueOfProductRating(blockID).isEmpty(),
                "There is common value of product rating but shouldn't in the product block!");

        //Проверяем, что текст "Вы экономите" присутствует и "Полный вид"
        softAssert.assertTrue(!assertsOnStorefront.getText_YouSave_Full(blockID).isEmpty(),
                "The text 'You save' is not Full or missed in the product block!");

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!assertsOnStorefront.getPricesWithTaxes(blockID).isEmpty(),
                "There is no text of a product tax in the product block!");

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
        stHomePage.selectLanguage_RTL();
        stHomePage.scrollToBlockWithProducts();
        WebElement tab_OnSaleRTL = DriverProvider.getDriver().findElement(By.xpath("//span[@class='ty-tabs__span'][text()='On Sale']"));
        tab_OnSaleRTL.click();
        takeScreenShot("ProductBlock_SmallItems_Var1 (RTL)");

        softAssert.assertAll();
        System.out.println("ProductBlock_SmallItems_Var1 passed successfully!");
    }
}