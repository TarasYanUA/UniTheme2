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
Количество колонок в списке -- 5
Тип загрузки                -- По клику
Заполнение                  -- Товары со скидкой
Макс. число элементов       -- 17
Спрятать кнопку добавления товара в корзину     -- n

2.1) UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Формат отображения цен                          -- Вариант 4
Отображать цену вверху                          -- n
Отображать пустые звёзды рейтинга товара        -- y
Отображать общее значение рейтинга товара       -- n
Отображать кнопку "Добавить в избранное"        -- y
Отображать кнопку "Добавить в список сравнения" -- y
Отображать кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" при наведении на ячейку товара -- y
Отображать "Вы экономите"                       -- Сокращенный вид

2.2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Сетка"
Количество строк в названии товара              -- 1
Отображать код товара                           -- y
Отображать статус наличия                       -- y
Отображать модификатор количества               -- y
Отображать кнопку "Купить"                      -- Иконка корзины и текст
Дополнительная информация о товаре              -- Краткое описание и характеристики
Отображать дополнительную информацию при наведении -- y
Отображать бренд                                -- Логотип
Отображать стандартную галерею изображений      -- Навигация стрелками
Переключать изображение товара при движении мышки-- Не переключать (нужно для настройки выше)

3) UniTheme2 -- Настройки цветосхемы            -- вкладка "Списки товаров":
Тип обрамления товара в сетке                   -- Рамка с внешними отступами
Добавить фон/маску для изображений товара       -- n
Использовать выравнивание элементов в товарной сетке --	y
Эффект увеличения ячейки при наведении          -- y
Насыщенность шрифта для названия товара         -- Нормальный

4) Настраиваем налог для всех товаров

5) Добавляем Краткое описание товару Ice Queen
*/

public class ProductBlock_GridMore_Var1 extends TestRunner implements DisableLazyLoadFromSection {
    String blockID;

    @Test(priority = 1)
    public void setConfigurationsForProductBlock_GridMore_Var1() {
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
        UtilsAdm.clickAndType(layoutPage.field_NumberOfColumnsInList, "5");
        new Select(layoutPage.setting_LoadingType).selectByValue("onclick");
        layoutPage.tabOfBlock_Content.click();
        new Select(layoutPage.setting_Filling).selectByValue("on_sale");
        UtilsAdm.clickAndType(layoutPage.field_Limit, "17");
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
        new Select(themeSettingsProductLists.setting_PriceDisplayFormat).selectByValue("row-mix");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_PriceAtTheTop, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_EmptyStarsOfProductRating, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_CommonValueOfProductRating, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayButtonComparisonList, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayButtonWishList, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayButtonsWhenHoveringMouse, true);
        new Select(themeSettingsProductLists.setting_ShowYouSave).selectByValue("short");

        //Работаем с настройками темы п.2.2
        new Select(themeSettingsProductLists.grid_NumberOfLinesInProductName).selectByValue("1");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_ShowProductCode, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayAvailabilityStatus, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_ShowQuantityChanger, true);
        new Select(themeSettingsProductLists.setting_ShowAddToCartButton).selectByValue("icon_and_text");
        new Select(themeSettingsProductLists.setting_AdditionalProductInformation).selectByValue("features_and_description");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_ShowAdditionalInformationOnHover, true);
        new Select(themeSettingsProductLists.setting_ShowBrand).selectByValue("logo");
        new Select(themeSettingsProductLists.setting_ShowStandardImageGallery_Grid).selectByValue("arrows");
        new Select(themeSettingsProductLists.setting_SwitchProductImageWhenHovering).selectByValue("N");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Списки товаров"
        ColorSchemeSettings colorSchemeSettings = basicPage.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.selectActiveColorScheme();
        colorSchemeSettings.tab_ProductLists.click();
        new Select(colorSchemeSettings.setting_FrameType).selectByValue("solid_with_margins");
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_ProductLists_MaskForProductImages, false);
        new Select(colorSchemeSettings.setting_ProductLists_ElementsAlignment).selectByValue("use");
        UtilsAdm.setCheckboxState(colorSchemeSettings.setting_ProductLists_ExpandGridItemOnHover, true);
        new Select(colorSchemeSettings.setting_ProductLists_FontWeightForProductName).selectByValue("normal");
        basicPage.clickSaveButtonOfSettings();

        //Настраиваем налог для всех товаров
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.setTaxesForAllProducts();

        //Добавляем Краткое описание товару Ice Queen
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        basicPage.selectLanguageForAdminElement("ru");
        productSettings.clickAndType_SearchFieldOfProduct("Ice Queen");
        productSettings.hoverAndTypeField_ShortDescription(
                "Рюкзак Camelback - женский рюкзак идеально подходящий для активного отдыха в зимний период времени. Общий объём 16,4 литра, что позволяет вместить необходимый багаж. В этом рюкзаке есть отделение для воды с системой, не позволяющей ей замерзнуть. Резервуар для воды 2 литра.");
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductBlock_GridMore_Var1")
    public void checkProductBlock_GridMore_Var1() {
        BasicPage basicPage = new BasicPage();
        SoftAssert softAssert = new SoftAssert();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Блок товаров "Распродажа" на главной странице
        stHomePage.scrollToBlockWithProducts();
        stHomePage.openProductBlock("Распродажа");

        //Проверяем, что у блока товаров 5 колонок. Настройка блока "Количество колонок в списке -- 5"
        softAssert.assertEquals(DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ty-column5")).size(), 5,
                "Number of columns is not equal 5 in the product block!");

        clickButton_ShowMore( "ProductBlock_GridMore_Var1 - ProductBlock ", "Распродажа");

        //Проверяем, что у товаров присутствуют пустые звёздочки рейтинга
        softAssert.assertTrue(!assertsOnStorefront.getEmptyStarsOfProductRating(blockID).isEmpty(),
                "There are no empty stars in the product block!");

        //Проверяем, что у товаров отсутствует общее значение рейтинга товара
        softAssert.assertFalse(!assertsOnStorefront.getCommonValueOfProductRating(blockID).isEmpty(),
                "There is common value of product rating but shouldn't in the product block!");

        //Проверяем, что кнопка "Избранное" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToWishList.isEmpty(),
                "There is no button 'Add to wish list' in the product block!");

        //Проверяем, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(!assertsOnStorefront.button_AddToComparisonList.isEmpty(),
                "There is no button 'Add to comparison list' in the product block!");

        //Проверяем, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются при наведении на ячейку товара
        softAssert.assertTrue(!assertsOnStorefront.buttonsAreDisplayedOnHover.isEmpty(),
                "Buttons are not displayed when hovering over a product cell in the product block!");

        //Проверяем, что текст "Вы экономите" присутствует и "Сокращенный вид"
        softAssert.assertTrue(!assertsOnStorefront.getText_YouSave_Short(blockID).isEmpty(),
                "The text 'You save' is not Short or missed in the product block!");

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!assertsOnStorefront.getPricesWithTaxes(blockID).isEmpty(),
                "There is no text of a product tax in the product block!");

        //Проверяем, что Количество строк в названии товара -- 1
        softAssert.assertTrue(!assertsOnStorefront.getNumberOfLinesInProductName_Grid(blockID, 1).isEmpty(),
                "Number of lines in the product name is not 1!");

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

        //Проверяем настройку "Дополнительная информация о товаре -- Краткое описание и характеристики"
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ut2-product-description")).isEmpty()
                        && !DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ut2-features-list")).isEmpty(),
                "Additional information about products is not 'Short description and features' in the block!");

        //Проверяем, что дополнительная информация отображается при наведении
        softAssert.assertTrue(!assertsOnStorefront.gridList__AdditionalInformationOnHover.isEmpty(),
                "Additional information is displayed without mouse hover in the product block!");

        //Проверяем, что логотип бренда присутствует
        softAssert.assertTrue(!assertsOnStorefront.getBrandLogo(blockID).isEmpty(),
                "There is no brand logo in the product block!");

        //Проверяем, что у товаров присутствует галерея изображений и она стрелками
        softAssert.assertTrue(!assertsOnStorefront.gridList__ShowStandardImageGallery_Arrows.isEmpty(),
                "Image gallery of the product is not with arrows navigation in the product block!");

        //Проверяем, что Максимальное число элементов -- 17 (не превышает это значение)
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ut2-gl__item")).size() <= 17,
                "Max number of products increases 17 products in the block!");

        stHomePage.selectLanguage("ar");
        stHomePage.openProductBlock("On Sale");
        clickButton_ShowMore("ProductBlock_GridMore_Var1 - ProductBlock (RTL) ", "On Sale");
        softAssert.assertAll();
        System.out.println("ProductBlock_GridMore_Var1 passed successfully!");
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