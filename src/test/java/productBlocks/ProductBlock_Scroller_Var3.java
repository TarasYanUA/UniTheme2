package productBlocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.*;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

import java.util.List;

/*
1) Настройки блока товаров "Распродажа"
Шаблон                      -- Скроллер
Показывать цену             -- y
Включить быстрый просмотр   -- n
Не прокручивать автоматически   -- y
Количество элементов        -- 4
Внешняя навигация           -- y
Заполнение                  -- Товары со скидкой
Макс. число элементов       -- 13
Спрятать кнопку добавления товара в корзину     -- n

2.1. UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Формат отображения цен                          -- Вариант 7
Отображать цену вверху                          -- y
Отображать пустые звёзды рейтинга товара        -- y
Отображать общее значение рейтинга товара       -- y
Отображать кнопку "Добавить в избранное"        -- y
Отображать кнопку "Добавить в список сравнения" -- y
Отображать кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" при наведении на ячейку товара -- n
Отображать "Вы экономите"                       -- Полный вид

2.2. UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Скроллер"
Количество строк в названии товара              -- 3
Отображать статус наличия                       -- n
Отображать модификатор количества               -- n
Отображать кнопку "Быстрый просмотр"            -- n
Отображать кнопку "Купить"                      -- Только текст

3) UniTheme2 -- Настройки цветосхемы -- вкладка "Списки товаров":
Тип обрамления товара в сетке                   -- Рамка без внешних отступов
Добавить фон/маску для изображений товара       -- y
Использовать выравнивание элементов в товарной сетке --	n
Насыщенность шрифта для названия товара         -- Жирный

4) Настраиваем налог для всех товаров

5) CS-Cart настройки:
Включить быстрый просмотр                       -- y
*/

public class ProductBlock_Scroller_Var3 extends TestRunner implements DisableLazyLoadFromSection {
    String blockID;

    @Test(priority = 1)
    public void setConfigurationsForProductBlock_Scroller_Var3() {
        BasicPage basicPage = new BasicPage();

        //Настраиваем блок товаров "Распродажа"
        disableLazyLoadFromSection("Распродажа");   //Выключаем LazyLoad в секции с блоком
        makePause();
        blockID = getBlockID("Распродажа");  //Получаем ID нужного блока товаров
        basicPage.navigateToBlockSettings("Распродажа");
        basicPage.selectSetting_BlockTemplate("blocks/products/products_scroller.tpl");
        makePause();
        basicPage.button_SettingsOfTemplate.click();
        if(!basicPage.checkbox_ShowPrice.isSelected())
            basicPage.checkbox_ShowPrice.click();
        if(basicPage.checkbox_EnableQuickView.isSelected())
            basicPage.checkbox_EnableQuickView.click();
        if(!basicPage.checkbox_DoNotScrollAutomatically.isSelected())
            basicPage.checkbox_DoNotScrollAutomatically.click();
        basicPage.clickAndType_Field_ItemQuantity("4");
        if(!basicPage.checkbox_OutsideNavigation.isSelected())
            basicPage.checkbox_OutsideNavigation.click();
        Actions scroll = new Actions(DriverProvider.getDriver());
        scroll.scrollToElement(basicPage.tabOfBlock_Content);
        scroll.perform();
        basicPage.tabOfBlock_Content.click();
        basicPage.selectSetting_Filling("on_sale");
        basicPage.clickAndType_Field_Limit("13");
        basicPage.tabOfBlock_BlockSettings.click();
        if (basicPage.checkbox_HideAddToCartButton.isSelected())
            basicPage.checkbox_HideAddToCartButton.click();
        basicPage.button_saveBlock.click();

        //Работаем с настройками темы п.2.1
        ThemeSettings_ProductLists themeSettingsProductLists = basicPage.navigateTo_ThemeSettings_tabProductLists();
        new Select(themeSettingsProductLists.setting_PriceDisplayFormat).selectByValue("col-os-fill");
        WebElement checkboxPriceAtTheTop = themeSettingsProductLists.setting_PriceAtTheTop;
        if (!checkboxPriceAtTheTop.isSelected()) {
            checkboxPriceAtTheTop.click();
        }
        WebElement checkboxProductRating = themeSettingsProductLists.setting_EmptyStarsOfProductRating;
        if (!checkboxProductRating.isSelected()) {
            checkboxProductRating.click();
        }
        WebElement checkboxSettingCommonValueOfProductRating = themeSettingsProductLists.setting_CommonValueOfProductRating;
        if (!checkboxSettingCommonValueOfProductRating.isSelected()) {
            checkboxSettingCommonValueOfProductRating.click();
        }
        WebElement checkboxSettingDisplayButtonComparisonList = themeSettingsProductLists.setting_DisplayButtonComparisonList;
        if (!checkboxSettingDisplayButtonComparisonList.isSelected()) {
            checkboxSettingDisplayButtonComparisonList.click();
        }
        WebElement checkboxSettingDisplayButtonWishList = themeSettingsProductLists.setting_DisplayButtonWishList;
        if (!checkboxSettingDisplayButtonWishList.isSelected()) {
            checkboxSettingDisplayButtonWishList.click();
        }
        if(themeSettingsProductLists.setting_DisplayButtonsWhenHoveringMouse.isSelected())
            themeSettingsProductLists.setting_DisplayButtonsWhenHoveringMouse.click();
        new Select(themeSettingsProductLists.setting_ShowYouSave).selectByValue("full");

        //Работаем с настройками темы п.2.2
        Actions scrolling = new Actions(DriverProvider.getDriver());
        scrolling.scrollToElement(themeSettingsProductLists.scroller_AvailabilityStatus);
        scrolling.perform();
        new Select(themeSettingsProductLists.scroller_NumberOfLinesInProductName).selectByValue("3");
        if(themeSettingsProductLists.scroller_AvailabilityStatus.isSelected())
            themeSettingsProductLists.scroller_AvailabilityStatus.click();
        if(themeSettingsProductLists.scroller_QuantityChanger.isSelected())
            themeSettingsProductLists.scroller_QuantityChanger.click();
        if(themeSettingsProductLists.scroller_QuickViewButton.isSelected())
            themeSettingsProductLists.scroller_QuickViewButton.click();
        new Select(themeSettingsProductLists.scroller_AddToCartButton).selectByValue("text");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Списки товаров"
        ColorSchemeSettings colorSchemeSettings = basicPage.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.fieldOfActiveColorScheme.click();
        colorSchemeSettings.activeColorScheme.click();
        makePause();
        colorSchemeSettings.tab_ProductLists.click();
        new Select(colorSchemeSettings.setting_FrameType).selectByValue("solid_with_margins");
        if (!colorSchemeSettings.setting_ProductLists_MaskForProductImages.isSelected())
            colorSchemeSettings.setting_ProductLists_MaskForProductImages.click();
        new Select(colorSchemeSettings.setting_ProductLists_ElementsAlignment).selectByValue("do_not_use");
        new Select(colorSchemeSettings.setting_ProductLists_FontWeightForProductName).selectByValue("bold");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем налог для всех товаров
        basicPage.setTaxesForAllProducts();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductBlock_Scroller_Var3")
    public void checkProductBlock_Scroller_Var3() {
        BasicPage basicPage = new BasicPage();
        SoftAssert softAssert = new SoftAssert();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Блок товаров "Распродажа" на главной странице
        stHomePage.scrollToBlockWithProducts();
        WebElement tab_OnSale = DriverProvider.getDriver().findElement(By.xpath("//span[@class='ty-tabs__span'][text()='Распродажа']"));
        tab_OnSale.click();
        makePause();

        //Проверяем, что у блока товаров "Количество элементов -- 4"
        softAssert.assertEquals(DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .owl-item.active")).size(), 4,
                "Number of elements is not equal 4 in the product block!");

        //Проверяем, что внешняя навигация присутствует
        softAssert.assertTrue(!assertsOnStorefront.getOutsideNavigation(blockID).isEmpty(),
                "There is no outside navigation in the product block!");

        //Проверяем, что у товаров присутствуют пустые звёздочки рейтинга
        softAssert.assertTrue(!assertsOnStorefront.getEmptyStarsOfProductRating(blockID).isEmpty(),
                "There are no empty rating stars in the product block!");

        //Проверяем, что у товаров присутствует общее значение рейтинга товара
        softAssert.assertTrue(!assertsOnStorefront.getCommonValueOfProductRating(blockID).isEmpty(),
                "There is no common value of product rating in the product block!");

        //Проверяем, что кнопка "Избранное" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToWishList.isEmpty(),
                "There is no button 'Add to wish list' in the product block!");

        //Проверяем, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToComparisonList.isEmpty(),
                "There is no button 'Add to comparison list' in the product block!");

        //Проверяем, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются СРАЗУ, а не при наведении на ячейку товара
        softAssert.assertFalse(!assertsOnStorefront.buttonsAreDisplayedOnHover.isEmpty(),
                "Buttons are displayed when hovering over a product cell but should be displayed at once in the product block!");

        //Проверяем, что текст "Вы экономите" присутствует и "Полный вид"
        softAssert.assertTrue(!assertsOnStorefront.getText_YouSave_Full(blockID).isEmpty(),
                "The text 'You save' is not Full or missed in the product block!");

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!assertsOnStorefront.getPricesWithTaxes(blockID).isEmpty(),
                "There is no text of a product tax in the product block!");

        //Проверяем, что Количество строк в названии товара -- 3
        softAssert.assertTrue(!assertsOnStorefront.getNumberOfLinesInProductName_Scroller(blockID, 3).isEmpty(),
                "Number of lines in the product name is not 3!");

        //Проверяем, что статус наличия отсутствует
        List<WebElement> availabilityStatus = assertsOnStorefront.getAvailabilityStatus(blockID);
        softAssert.assertFalse(!availabilityStatus.isEmpty(),
                "There is an availability status but shouldn't in the product block ID " + blockID);

        //Проверяем, что модификатор количества отсутствует
        softAssert.assertFalse(!assertsOnStorefront.getQuantityChanger(blockID).isEmpty(),
                "There is a quantity Changer but shouldn't in the product block!");

        //Проверяем, что быстрый просмотр отсутствует
        softAssert.assertFalse(!assertsOnStorefront.getQuickViewButton(blockID).isEmpty(),
                "There is a Quick view button but shouldn't in the product block!");

        //Проверяем, что кнопка "Купить" в виде "Только текст"
        softAssert.assertTrue(!assertsOnStorefront.getShowAddToCartButton_TextOnly(blockID).isEmpty(),
                "The button 'Add to cart' is not as 'Text only' or even missed in the product block!");

        takeScreenShot("ProductBlock_Scroller_Var3");
        stHomePage.selectLanguage("ar");
        stHomePage.scrollToBlockWithProducts();
        WebElement tab_OnSaleRTL = DriverProvider.getDriver().findElement(By.xpath("//span[@class='ty-tabs__span'][text()='On Sale']"));
        tab_OnSaleRTL.click();
        takeScreenShot("ProductBlock_Scroller_Var3 (RTL)");

        softAssert.assertAll();
        System.out.println("ProductBlock_Scroller_Var3 has passed successfully!");
    }
}