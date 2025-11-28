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
Количество колонок в списке -- 4
Тип загрузки                -- По клику
Заполнение                  -- Товары со скидкой
Макс. число элементов       -- 15
Спрятать кнопку добавления товара в корзину     -- n

2.1) UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Формат отображения цен                          -- Вариант 3
Отображать цену вверху                          -- n
Отображать пустые звёзды рейтинга товара        -- n
Отображать общее значение рейтинга товара       -- n
Отображать кнопку "Добавить в избранное"        -- y
Отображать кнопку "Добавить в список сравнения" -- y
Отображать кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" при наведении на ячейку товара -- n
Отображать "Вы экономите"                       -- Полный вид

2.2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Сетка"
Количество строк в названии товара              -- 3
Отображать код товара                           -- y
Отображать статус наличия                       -- y
Отображать модификатор количества               -- n
Отображать кнопку "Купить"                      -- Только иконка корзины
Дополнительная информация о товаре              -- Список характеристик и вариаций
Отображать дополнительную информацию при наведении -- y
Отображать бренд                                -- Название
Показывать галерею мини-иконок товара в товарном списке -- Навигация точками
Переключать изображение товара при движении мышки-- Не переключать (нужно для настройки выше)

3) UniTheme2 -- Настройки цветосхемы            -- вкладка "Списки товаров":
Скруглить углы для элементов интерфейса         -- Небольшая округлость
Скруглить углы блоков, окон, баннеров           -- Небольшая округлость
Тип обрамления товара в сетке                   -- Рамка с внешними отступами
Добавить фон/маску для изображений товара       -- y
Использовать выравнивание элементов в товарной сетке --	y
Эффект увеличения ячейки при наведении          -- n
Насыщенность шрифта для названия товара         -- Жирный

4) Настраиваем налог для всех товаров
*/

public class ProductBlock_GridMore_Var2 extends TestRunner implements DisableLazyLoadFromSection {
    String blockID;

    @Test(priority = 1)
    public void setConfigurationsForProductBlock_GridMore_Var2() {
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
        UtilsAdm.clickAndType(layoutPage.field_NumberOfColumnsInList, "4");
        new Select(layoutPage.setting_LoadingType).selectByValue("onclick");
        layoutPage.tabOfBlock_Content.click();
        new Select(layoutPage.setting_Filling).selectByValue("on_sale");
        UtilsAdm.clickAndType(layoutPage.field_Limit, "15");
        layoutPage.tabOfBlock_Settings.click();
        UtilsAdm.setCheckboxState(layoutPage.checkbox_HideAddToCartButton, false);
        layoutPage.button_saveBlock.click();

        //Работаем с настройками характеристики Бренд
        FeaturePage featurePage = basicPage.navigateToSection_Features();
        featurePage.featureBrand.click();
        WebElement checkboxShowInProductList = featurePage.showInProductList;
        if (!checkboxShowInProductList.isSelected()) {
            checkboxShowInProductList.click();
            basicPage.clickSaveButtonOfSettings();
        }

        //Работаем с настройками темы п.2.1
        ThemeSettings_ProductLists themeSettingsProductLists = basicPage.navigateTo_ThemeSettings_tabProductLists();
        new Select(themeSettingsProductLists.setting_PriceDisplayFormat).selectByValue("mix");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_PriceAtTheTop, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_EmptyStarsOfProductRating, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_CommonValueOfProductRating, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayButtonComparisonList, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayButtonWishList, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayButtonsWhenHoveringMouse, false);
        new Select(themeSettingsProductLists.setting_ShowYouSave).selectByValue("full");

        //Работаем с настройками темы п.2.2
        new Select(themeSettingsProductLists.grid_NumberOfLinesInProductName).selectByValue("3");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_ShowProductCode, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayAvailabilityStatus, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_ShowQuantityChanger, false);
        new Select(themeSettingsProductLists.setting_ShowAddToCartButton).selectByValue("icon_button");
        new Select(themeSettingsProductLists.setting_AdditionalProductInformation).selectByValue("features_and_variations");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_ShowAdditionalInformationOnHover, true);
        new Select(themeSettingsProductLists.setting_ShowBrand).selectByValue("name");
        new Select(themeSettingsProductLists.setting_ShowStandardImageGallery_Grid).selectByValue("points");
        new Select(themeSettingsProductLists.setting_SwitchProductImageWhenHovering).selectByValue("N");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Списки товаров"
        ColorSchemeSettings colorSchemeSettings = basicPage.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.selectActiveColorScheme();
        new Select(colorSchemeSettings.setting_General_RoundCornersForElements).selectByValue("little");
        new Select(colorSchemeSettings.setting_General_RoundCornersOfBlocks).selectByValue("little");
        colorSchemeSettings.tab_ProductLists.click();
        new Select(colorSchemeSettings.setting_FrameType).selectByValue("solid_with_margins");
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_ProductLists_MaskForProductImages, true);
        new Select(colorSchemeSettings.setting_ProductLists_ElementsAlignment).selectByValue("use");
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_ProductLists_ExpandGridItemOnHover, false);
        new Select(colorSchemeSettings.setting_ProductLists_FontWeightForProductName).selectByValue("bold");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем налог для всех товаров
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.setTaxesForAllProducts();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductBlock_GridMore_Var2")
    public void checkProductBlock_GridMore_Var2() {
        BasicPage basicPage = new BasicPage();
        SoftAssert softAssert = new SoftAssert();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Блок товаров "Распродажа" на главной странице
        stHomePage.scrollToBlockWithProducts();
        stHomePage.openProductBlock("Распродажа");

        //Проверяем, что у блока товаров 4 колонки. Настройка блока "Количество колонок в списке -- 4"
        softAssert.assertEquals(DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ty-column4")).size(), 4,
                "Number of columns is not equal 4 in the product block!");

        clickButton_ShowMore("ProductBlock_GridMore_Var2 - ProductBlock ", "Распродажа");

        //Проверяем, что у товаров отсутствуют пустые звёздочки рейтинга
        softAssert.assertFalse(!assertsOnStorefront.getEmptyStarsOfProductRating(blockID).isEmpty(),
                "There are empty stars but shouldn't in the product block!");

        //Проверяем, что у товаров отсутствует общее значение рейтинга товара
        softAssert.assertFalse(!assertsOnStorefront.getCommonValueOfProductRating(blockID).isEmpty(),
                "There is common value of product rating but shouldn't in the product block!");

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
        softAssert.assertTrue(!assertsOnStorefront.getNumberOfLinesInProductName_Grid(blockID, 3).isEmpty(),
                "Number of lines in the product name is not 3!");

        //Проверяем, что код товара присутствует
        softAssert.assertTrue(!assertsOnStorefront.getProductCode(blockID).isEmpty(),
                "There is no product code in the product block!");

        //Проверяем, что статус наличия присутствует
        softAssert.assertTrue(!assertsOnStorefront.getAvailabilityStatus(blockID).isEmpty(),
                "There is no availability status in the product block!");

        //Проверяем, что модификатор количества отсутствует
        softAssert.assertFalse(!assertsOnStorefront.getQuantityChanger(blockID).isEmpty(),
                "There is a quantity Changer but shouldn't in the product block!");

        //Проверяем, что кнопка "Купить" в виде "Только иконка корзины"
        softAssert.assertTrue(!assertsOnStorefront.gridList__ShowAddToCartButton_IconOnly().isEmpty(),
                "The button 'Add to cart' is not as 'Icon only' or even missed in the product block!");

        //Проверяем настройку "Дополнительная информация о товаре -- Список характеристик и вариаций"
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ut2-features-list")).isEmpty()
                && !DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ut2-lv__item-features")).isEmpty(),
                "Additional information about products is not 'Features and Variations list' in the block!");

        //Проверяем, что дополнительная информация отображается при наведении
        softAssert.assertTrue(!assertsOnStorefront.gridList__AdditionalInformationOnHover.isEmpty(),
                "Additional information is displayed without mouse hover in the product block!");

        //Проверяем, что логотип бренда присутствует
        softAssert.assertTrue(!assertsOnStorefront.getBrandName(blockID).isEmpty(),
                "There is no brand name in the product block!");

        //Проверяем, что галерея мини-иконок товара в виде точек
        softAssert.assertTrue(!assertsOnStorefront.gridList__ShowStandardImageGallery_Dots().isEmpty(),
                "Gallery of mini icons is not with points in the product block!");

        //Проверяем, что Максимальное число элементов -- 15 (не превышает это значение)
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ut2-gl__item")).size() <= 15,
                "Max number of products increases 15 products in the block!");

        stHomePage.selectLanguage("ar");
        stHomePage.openProductBlock("On Sale");
        clickButton_ShowMore("ProductBlock_GridMore_Var2 - ProductBlock (RTL) ", "On Sale");
        softAssert.assertAll();
        System.out.println("ProductBlock_GridMore_Var2 passed successfully!");
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