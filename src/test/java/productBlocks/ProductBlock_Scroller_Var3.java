package productBlocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
Формат отображения цен                          -- Вариант 1
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
Отображать кнопку "Купить"                      -- Только иконка корзины

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
        CsCartSettings csCartSettings = new CsCartSettings();

        //Настраиваем блок товаров "Распродажа"
        disableLazyLoadFromSection("Распродажа");   //Выключаем LazyLoad в секции с блоком
        makePause();
        blockID = getBlockID("Распродажа");  //Получаем ID нужного блока товаров
        csCartSettings.navigateToBlockSettings("Распродажа");
        csCartSettings.selectSetting_BlockTemplate("blocks/products/products_scroller.tpl");
        makePause();
        csCartSettings.button_SettingsOfTemplate.click();
        if(!csCartSettings.checkbox_ShowPrice.isSelected())
            csCartSettings.checkbox_ShowPrice.click();
        if(csCartSettings.checkbox_EnableQuickView.isSelected())
            csCartSettings.checkbox_EnableQuickView.click();
        if(!csCartSettings.checkbox_DoNotScrollAutomatically.isSelected())
            csCartSettings.checkbox_DoNotScrollAutomatically.click();
        csCartSettings.clickAndType_Field_ItemQuantity("4");
        if(!csCartSettings.checkbox_OutsideNavigation.isSelected())
            csCartSettings.checkbox_OutsideNavigation.click();
        Actions scroll = new Actions(DriverProvider.getDriver());
        scroll.scrollToElement(csCartSettings.tabOfBlock_Content);
        scroll.perform();
        csCartSettings.tabOfBlock_Content.click();
        csCartSettings.selectSetting_Filling("on_sale");
        csCartSettings.clickAndType_Field_Limit("13");
        csCartSettings.tabOfBlock_BlockSettings.click();
        if (csCartSettings.checkbox_HideAddToCartButton.isSelected())
            csCartSettings.checkbox_HideAddToCartButton.click();
        csCartSettings.button_saveBlock.click();

        //Работаем с настройками темы п.2.1
        ThemeSettings_ProductLists themeSettingsProductLists = csCartSettings.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.selectSettingPriceDisplayFormat("col");
        WebElement checkboxPriceAtTheTop = themeSettingsProductLists.settingPriceAtTheTop;
        if (!checkboxPriceAtTheTop.isSelected()) {
            checkboxPriceAtTheTop.click();
        }
        WebElement checkboxProductRating = themeSettingsProductLists.settingEmptyStarsOfProductRating;
        if (!checkboxProductRating.isSelected()) {
            checkboxProductRating.click();
        }
        WebElement checkboxSettingCommonValueOfProductRating = themeSettingsProductLists.settingCommonValueOfProductRating;
        if (!checkboxSettingCommonValueOfProductRating.isSelected()) {
            checkboxSettingCommonValueOfProductRating.click();
        }
        WebElement checkboxSettingDisplayButtonComparisonList = themeSettingsProductLists.settingDisplayButtonComparisonList;
        if (!checkboxSettingDisplayButtonComparisonList.isSelected()) {
            checkboxSettingDisplayButtonComparisonList.click();
        }
        WebElement checkboxSettingDisplayButtonWishList = themeSettingsProductLists.settingDisplayButtonWishList;
        if (!checkboxSettingDisplayButtonWishList.isSelected()) {
            checkboxSettingDisplayButtonWishList.click();
        }
        if(themeSettingsProductLists.settingDisplayButtonsWhenHoveringMouse.isSelected())
            themeSettingsProductLists.settingDisplayButtonsWhenHoveringMouse.click();
        themeSettingsProductLists.selectSettingShowYouSave("full");

        //Работаем с настройками темы п.2.2
        Actions scrolling = new Actions(DriverProvider.getDriver());
        scrolling.scrollToElement(themeSettingsProductLists.scroller_AvailabilityStatus);
        scrolling.perform();
        themeSettingsProductLists.selectScroller_NumberOfLinesInProductName("3");
        if(themeSettingsProductLists.scroller_AvailabilityStatus.isSelected())
            themeSettingsProductLists.scroller_AvailabilityStatus.click();
        if(themeSettingsProductLists.scroller_QuantityChanger.isSelected())
            themeSettingsProductLists.scroller_QuantityChanger.click();
        if(themeSettingsProductLists.scroller_QuickViewButton.isSelected())
            themeSettingsProductLists.scroller_QuickViewButton.click();
        themeSettingsProductLists.selectScroller_AddToCartButton("icon_button");
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Списки товаров"
        ColorSchemeSettings colorSchemeSettings = csCartSettings.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.fieldOfActiveColorScheme.click();
        colorSchemeSettings.activeColorScheme.click();
        makePause();
        colorSchemeSettings.tab_ProductLists.click();
        colorSchemeSettings.selectSetting_FrameType("solid_without_margins");
        if (!colorSchemeSettings.setting_ProductLists_MaskForProductImages.isSelected())
            colorSchemeSettings.setting_ProductLists_MaskForProductImages.click();
        colorSchemeSettings.selectSetting_ProductLists_ElementsAlignment("do_not_use");
        colorSchemeSettings.selectSetting_ProductLists_FontWeightForProductName("bold");
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем налог для всех товаров
        csCartSettings.setTaxesForAllProducts();

        //Задаём товару "Wildwood city classic" длинное название
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Wildwood city classic");
        productSettings.chooseAnyProduct();
        csCartSettings.closeNotificationIfPresent();
        productSettings.clickAndTypeField_ProductName("Wildwood city classic - Мы завезли настоящую американскую классику! Круизеры Drifter. Lorem Ipsum используют потому, что тот обеспечивает более или менее стандартное заполнение шаблона");
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductBlock_Scroller_Var3")
    public void checkProductBlock_Scroller_Var3() {
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
        stHomePage.selectLanguage_RTL();
        stHomePage.scrollToBlockWithProducts();
        WebElement tab_OnSaleRTL = DriverProvider.getDriver().findElement(By.xpath("//span[@class='ty-tabs__span'][text()='On Sale']"));
        tab_OnSaleRTL.click();
        takeScreenShot("ProductBlock_Scroller_Var3 (RTL)");

        softAssert.assertAll();
        System.out.println("ProductBlock_Scroller_Var3 has passed successfully!");
    }
}