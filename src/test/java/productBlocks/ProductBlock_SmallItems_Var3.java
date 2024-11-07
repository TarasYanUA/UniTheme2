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

    @Test(priority = 1)
    public void setConfigurationsFor_ProductBlock_SmallItems_Var3() {
        CsCartSettings csCartSettings = new CsCartSettings();

        //Настраиваем блок товаров "Распродажа"
        disableLazyLoadFromSection("Распродажа");   //Выключаем LazyLoad в секции с блоком
        makePause();
        blockID = getBlockID("Распродажа");  //Получаем ID нужного блока товаров
        csCartSettings.navigateToBlockSettings("Распродажа");
        csCartSettings.selectSetting_BlockTemplate("blocks/products/products_small_items.tpl");
        makePause();
        csCartSettings.button_SettingsOfTemplate.click();
        if (!csCartSettings.checkbox_ShowItemNumber.isSelected())
            csCartSettings.checkbox_ShowItemNumber.click();
        csCartSettings.tabOfBlock_Content.click();
        csCartSettings.selectSetting_Filling("on_sale");
        csCartSettings.clickAndType_Field_Limit("13");
        csCartSettings.tabOfBlock_BlockSettings.click();
        if (csCartSettings.checkbox_HideAddToCartButton.isSelected())
            csCartSettings.checkbox_HideAddToCartButton.click();
        csCartSettings.button_saveBlock.click();

        //Работаем с настройками темы
        ThemeSettings_ProductLists themeSettingsProductLists = csCartSettings.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.selectSettingPriceDisplayFormat("col");

        if (!themeSettingsProductLists.settingPriceAtTheTop.isSelected())
            themeSettingsProductLists.settingPriceAtTheTop.click();
        if (!themeSettingsProductLists.settingEmptyStarsOfProductRating.isSelected())
            themeSettingsProductLists.settingEmptyStarsOfProductRating.click();
        if (!themeSettingsProductLists.settingCommonValueOfProductRating.isSelected())
            themeSettingsProductLists.settingCommonValueOfProductRating.click();
        themeSettingsProductLists.selectSettingShowYouSave("short");

        Actions scroll = new Actions(DriverProvider.getDriver());
        scroll.scrollToElement(themeSettingsProductLists.smallItems_NumberOfLinesInProductName);
        scroll.perform();
        themeSettingsProductLists.selectSmallItems_NumberOfLinesInProductName("4");
        if (themeSettingsProductLists.smallItems_ProductCode.isSelected())
            themeSettingsProductLists.smallItems_ProductCode.click();
        if (themeSettingsProductLists.smallItems_AvailabilityStatus.isSelected())
            themeSettingsProductLists.smallItems_AvailabilityStatus.click();
        if (themeSettingsProductLists.smallItems_QuantityChanger.isSelected())
            themeSettingsProductLists.smallItems_QuantityChanger.click();
        themeSettingsProductLists.selectSmallItems_AddToCartButton("text");
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Списки товаров"
        ColorSchemeSettings colorSchemeSettings = csCartSettings.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.fieldOfActiveColorScheme.click();
        colorSchemeSettings.activeColorScheme.click();
        makePause();
        colorSchemeSettings.tab_ProductLists.click();
        colorSchemeSettings.selectSetting_FrameType("solid_without_margins");
        if (!colorSchemeSettings.setting_ProductLists_MaskForProductImages.isSelected()) {
            colorSchemeSettings.setting_ProductLists_MaskForProductImages.click();
        }
        colorSchemeSettings.selectSetting_ProductLists_FontWeightForProductName("bold");
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем налог для всех товаров
        csCartSettings.setTaxesForAllProducts();

        //Задаём товару "Wildwood city classic" длинное название
        ProductSettings productSettings = csCartSettings.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Wildwood city classic");
        productSettings.chooseAnyProduct();
        if (!DriverProvider.getDriver().findElements(By.cssSelector(".cm-notification-close")).isEmpty()) {
            DriverProvider.getDriver().findElement(By.cssSelector(".cm-notification-close")).click();
        }
        productSettings.clickAndTypeField_ProductName("Wildwood city classic - Мы завезли настоящую американскую классику! Круизеры Drifter. Lorem Ipsum используют потому, что тот обеспечивает более или менее стандартное заполнение шаблона");
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsFor_ProductBlock_SmallItems_Var3")
    public void checkProductBlock_SmallItems_Var3(){
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

        //Проверяем, что номера элементов отображаются в блоке товаров
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ut2-hit")).isEmpty(),
                "There are no item numbers in the product block!");

        //Проверяем, что у товаров присутствуют пустые звёздочки рейтинга
        softAssert.assertTrue(!assertsOnStorefront.getEmptyStarsOfProductRating(blockID).isEmpty(),
                "There are no empty rating stars in the product block!");

        //Проверяем, что у товаров присутствует общее значение рейтинга товара
        softAssert.assertTrue(!assertsOnStorefront.getCommonValueOfProductRating(blockID).isEmpty(),
                "There is no common value of product rating in the product block!");

        //Проверяем, что текст "Вы экономите" присутствует и "Сокращенный вид"
        softAssert.assertTrue(!assertsOnStorefront.getText_YouSave_Short(blockID).isEmpty(),
                "The text 'You save' is not Short or missed in the product block!");

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!assertsOnStorefront.getPricesWithTaxes(blockID).isEmpty(),
                "There is no text of a product tax in the product block!");

        //Проверяем, что код товара отсутствует
        softAssert.assertFalse(!assertsOnStorefront.getProductCode(blockID).isEmpty(),
                "There is a product code but shouldn't in the product block!");

        //Проверяем, что статус наличия отсутствует
        List<WebElement> availabilityStatus = assertsOnStorefront.getAvailabilityStatus(blockID);
        softAssert.assertFalse(!availabilityStatus.isEmpty(),
                "There is an availability status but shouldn't in the product block ID " + blockID);

        //Проверяем, что модификатор количества отсутствует
        softAssert.assertFalse(!assertsOnStorefront.getQuantityChanger(blockID).isEmpty(),
                "There is a quantity Changer but shouldn't in the product block!");

        //Проверяем, что кнопка "Купить" в виде "Только текст"
        softAssert.assertTrue(!assertsOnStorefront.getShowAddToCartButton_TextOnly(blockID).isEmpty(),
                "The button 'Add to cart' is not as 'Text only' or even missed in the product block!");

        takeScreenShot("ProductBlock_SmallItems_Var3");
        stHomePage.selectLanguage_RTL();
        stHomePage.scrollToBlockWithProducts();
        WebElement tab_OnSaleRTL = DriverProvider.getDriver().findElement(By.xpath("//span[@class='ty-tabs__span'][text()='On Sale']"));
        tab_OnSaleRTL.click();
        takeScreenShot("ProductBlock_SmallItems_Var3 (RTL)");

        softAssert.assertAll();
        System.out.println("ProductBlock_SmallItems_Var3 has passed successfully!");
    }
}