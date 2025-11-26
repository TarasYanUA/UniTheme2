package productBlocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
Шаблон                      -- AB: Сетка (с кнопкой "Показать ещё")
Показать номер элемента     -- n
Количество колонок в списке -- 6
Тип загрузки                -- По клику
Заполнение                  -- Товары со скидкой
Макс. число элементов       -- 13
Спрятать кнопку добавления товара в корзину     -- n

2.1) UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Формат отображения цен                          -- Вариант 8
Отображать цену вверху                          -- y
Отображать пустые звёзды рейтинга товара        -- y
Отображать общее значение рейтинга товара       -- y
Отображать кнопку "Добавить в избранное"        -- y
Отображать кнопку "Добавить в список сравнения" -- y
Отображать кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" при наведении на ячейку товара -- n
Отображать "Вы экономите"                       -- Полный вид

2.2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Сетка"
Количество строк в названии товара              -- 4
Отображать код товара                           -- n
Отображать статус наличия                       -- n
Отображать модификатор количества               -- n
Отображать кнопку "Купить"                      -- Только текст
Дополнительная информация о товаре              -- Список характеристик и вариаций
Отображать дополнительную информацию при наведении -- y
Отображать бренд                                -- Не отображать
Отображать стандартную галерею изображений      -- Не отображать

3) UniTheme2 -- Настройки цветосхемы            -- вкладка "Списки товаров":
Тип обрамления товара в сетке                   -- Рамка без внешних отступов
Добавить фон/маску для изображений товара       -- y
Использовать выравнивание элементов в товарной сетке --	n
Эффект увеличения ячейки при наведении          -- n
Насыщенность шрифта для названия товара         -- Жирный

4) Настраиваем налог для всех товаров
*/

public class ProductBlock_GridMore_Var3 extends TestRunner implements DisableLazyLoadFromSection {
    String blockID;

    @Test(priority = 1)
    public void setConfigurationsForProductBlock_GridMore_Var3() {
        BasicPage basicPage = new BasicPage();

        //Настраиваем блок товаров "Распродажа"
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        disableLazyLoadFromSection("Распродажа");   //Выключаем LazyLoad в секции с блоком
        blockID = getBlockID("Распродажа");  //Получаем ID нужного блока товаров
        layoutPage.navigateTo_BlockSettings("Распродажа");
        new Select(layoutPage.setting_BlockTemplate).selectByValue("blocks/products/ab__grid_list.tpl");
        UtilsAdm.makePause(1000);
        layoutPage.button_SettingsOfTemplate.click();
        UtilsAdm.setCheckboxState(layoutPage.checkbox_ShowItemNumber, false);
        UtilsAdm.clickAndType(layoutPage.field_NumberOfColumnsInList, "6");
        new Select(layoutPage.setting_LoadingType).selectByValue("onclick");
        layoutPage.tabOfBlock_Content.click();
        new Select(layoutPage.setting_Filling).selectByValue("on_sale");
        UtilsAdm.clickAndType(layoutPage.field_Limit, "13");
        layoutPage.tabOfBlock_Settings.click();
        UtilsAdm.setCheckboxState(layoutPage.checkbox_HideAddToCartButton, false);
        layoutPage.button_saveBlock.click();

        //Работаем с настройками характеристики Бренд
        FeaturePage featuresPage = basicPage.navigateToSection_Features();
        featuresPage.featureBrand.click();
        WebElement checkboxShowInProductList = featuresPage.showInProductList;
        if (!checkboxShowInProductList.isSelected()) {
            checkboxShowInProductList.click();
            basicPage.clickSaveButtonOfSettings();
        }

        //Работаем с настройками темы п.2.1
        ThemeSettings_ProductLists themeSettingsProductLists = basicPage.navigateTo_ThemeSettings_tabProductLists();
        new Select(themeSettingsProductLists.setting_PriceDisplayFormat).selectByValue("row-os-fill");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_PriceAtTheTop, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_EmptyStarsOfProductRating, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_CommonValueOfProductRating, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayButtonComparisonList, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayButtonWishList, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayButtonsWhenHoveringMouse, false);
        new Select(themeSettingsProductLists.setting_ShowYouSave).selectByValue("full");

        //Работаем с настройками темы п.2.2
        new Select(themeSettingsProductLists.grid_NumberOfLinesInProductName).selectByValue("4");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_ShowProductCode, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayAvailabilityStatus, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_ShowQuantityChanger, false);
        new Select(themeSettingsProductLists.setting_ShowAddToCartButton).selectByValue("text");
        new Select(themeSettingsProductLists.setting_AdditionalProductInformation).selectByValue("features_and_variations");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_ShowAdditionalInformationOnHover, true);
        new Select(themeSettingsProductLists.setting_ShowBrand).selectByValue("none");
        new Select(themeSettingsProductLists.setting_ShowStandardImageGallery_Grid).selectByValue("N");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Списки товаров"
        ColorSchemeSettings colorSchemeSettings = basicPage.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.selectActiveColorScheme();
        colorSchemeSettings.tab_ProductLists.click();
        new Select(colorSchemeSettings.setting_FrameType).selectByValue("solid_without_margins");
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_ProductLists_MaskForProductImages, true);
        new Select(colorSchemeSettings.setting_ProductLists_ElementsAlignment).selectByValue("do_not_use");
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_ProductLists_ExpandGridItemOnHover, false);
        new Select(colorSchemeSettings.setting_ProductLists_FontWeightForProductName).selectByValue("bold");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем налог для всех товаров
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.setTaxesForAllProducts();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductBlock_GridMore_Var3")
    public void checkProductBlock_GridMore_Var3() {
        BasicPage basicPage = new BasicPage();
        SoftAssert softAssert = new SoftAssert();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Блок товаров "Распродажа" на главной странице
        stHomePage.scrollToBlockWithProducts();
        stHomePage.openProductBlock("Распродажа");

        //Проверяем, что у блока товаров 6 колонок. Настройка блока "Количество колонок в списке -- 6"
        softAssert.assertEquals(DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ty-column6")).size(), 6,
                "Number of columns is not equal 6 in the product block!");

        clickButton_ShowMore("ProductBlock_GridMore_Var3 - ProductBlock ", "Распродажа");

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

        //Проверяем, что Количество строк в названии товара -- 4
        softAssert.assertTrue(!assertsOnStorefront.getNumberOfLinesInProductName_Grid(blockID, 4).isEmpty(),
                "Number of lines in the product name is not 4!");

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

        //Проверяем настройку "Дополнительная информация о товаре -- Список характеристик и вариаций"
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ut2-gl__feature")).isEmpty()
                && !DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ut2-lv__item-features")).isEmpty(),
                "Additional information about products is not 'Features and Variations list' in the block!");

        //Проверяем, что дополнительная информация отображается при наведении
        softAssert.assertTrue(!assertsOnStorefront.gridList__AdditionalInformationOnHover.isEmpty(),
                "Additional information is displayed without mouse hover on the category page!");

        //Проверяем, что логотип и название бренда отсутствуют
        softAssert.assertFalse(!assertsOnStorefront.getBrandLogo(blockID).isEmpty()
                && !assertsOnStorefront.getBrandName(blockID).isEmpty(),
                "There is a brand logo or brand name but shouldn't in the product block!");

        //Проверяем, что Максимальное число элементов -- 13 (не превышает это значение)
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ut2-gl__item")).size() <= 13,
                "Max number of products increases 13 products in the block!");

        stHomePage.selectLanguage("ar");
        stHomePage.openProductBlock("On Sale");
        clickButton_ShowMore("ProductBlock_GridMore_Var3 - ProductBlock (RTL) ", "On Sale");
        softAssert.assertAll();
        System.out.println("ProductBlock_GridMore_Var3 passed successfully!");
    }

    void clickButton_ShowMore(String screenName, String blockName) {
        int num = 1;
        while (true) {
            List<WebElement> buttons = DriverProvider.getDriver().findElements(By.cssSelector("span[id*='ut2_load_more_block_" + blockID + "']"));
            if (!buttons.isEmpty() && buttons.getFirst().isDisplayed()) {
                WebElement button_ShowMore = buttons.getFirst();
                UtilsAdm.scrollToElementAndScrollBelow(button_ShowMore, 30);
                takeScreenShot(screenName + num);
                UtilsAdm.hoverOverElement(DriverProvider.getDriver().findElement(By
                        .xpath("//span[@class='ty-tabs__span'][text()='" + blockName + "']")));
                button_ShowMore.click();
                UtilsAdm.waitForSpinnerDisappear();
                num++;
            } else {
                takeScreenShot(screenName + "final");
                break;
            }
        }
    }
}