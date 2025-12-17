package productBlocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.*;
import taras.asserts.Asserts_CsCartSettings;
import taras.asserts.Asserts_ThemeSettings_ProductLists;
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
Скруглить углы для элементов интерфейса         -- Не использовать
Скруглить углы блоков, окон, баннеров           -- Не использовать
Тип обрамления товара в сетке                   -- Рамка без внешних отступов
Добавить фон/маску для изображений товара       -- y
Использовать выравнивание элементов в товарной сетке --	n
Эффект увеличения ячейки при наведении          -- n
Насыщенность шрифта для названия товара         -- Жирный

4) Настраиваем налог для всех товаров
*/

public class ProductBlock_GridMore_Var3 extends TestRunner implements DisableLazyLoadFromSection {
    String blockID;
    Asserts_ThemeSettings_ProductLists asserts_productLists = new Asserts_ThemeSettings_ProductLists();
    Asserts_CsCartSettings asserts_csCartSettings = new Asserts_CsCartSettings();

    @Test(priority = 1)
    public void setConfigurationsForProductBlock_GridMore_Var3() {
        BasicPage basicPage = new BasicPage();

        //Настраиваем блок товаров "Распродажа"
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        disableLazyLoadFromSection("Распродажа");   //Выключаем LazyLoad в секции с блоком
        blockID = getBlockID("Распродажа");         //Получаем ID нужного блока товаров
        asserts_productLists.setBlockID(blockID);             //Передаём blockID в класс с проверками
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
        FeaturePage featurePage = basicPage.navigateToSection_Features();
        featurePage.featureBrand.click();
        WebElement checkboxShowInProductList = featurePage.showInProductList;
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
        new Select(colorSchemeSettings.setting_General_RoundCornersForElements).selectByValue("do_not_use");
        new Select(colorSchemeSettings.setting_General_RoundCornersOfBlocks).selectByValue("do_not_use");
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
        asserts_csCartSettings.assertNumberOfElements(
                Asserts_CsCartSettings.productBlock,
                asserts_csCartSettings.block_NumberOfColumnsInList,
                6,
                "in the product block!");

        clickButton_ShowMore("ProductBlock_GridMore_Var3 - ProductBlock ", "Распродажа");

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

        //Проверяем, что кнопка "Избранное" присутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.button_AddToWishList,
                "in the product block!",
                true);

        //Проверяем, что кнопка "Сравнить" присутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.button_AddToComparisonList,
                "in the product block!",
                true);

        //Проверяем, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются СРАЗУ, а не при наведении на ячейку товара
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.buttonsAreDisplayedOnHover,
                "in the product block!",
                false);

        //Проверяем, что текст "Вы экономите" присутствует и "Полный вид"
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.text_YouSave_Full,
                "in the product block!",
                true);

        //Проверяем, что Количество строк в названии товара -- 4
        asserts_productLists.assertNumberOfElements(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.numberOfLinesInProductName,
                4,
                "in the product block!");

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

        //Проверяем, что дополнительная информация отображается при наведении
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.additionalInformationOnHover,
                "in the product block!",
                true);

        //Проверяем, что логотип и название бренда отсутствуют
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.brandLogo,
                "in the product block!",
                false);
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.brandName,
                "in the product block!",
                false);

        //Проверяем настройку "Дополнительная информация о товаре -- Список характеристик и вариаций"
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.additionalProductInformation_Features,
                "in the product block!",
                true);
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.productBlock,
                asserts_productLists.additionalProductInformation_Variations,
                "in the product block!",
                true);

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        asserts_csCartSettings.assertElementPresence(
                Asserts_CsCartSettings.productBlock,
                asserts_csCartSettings.pricesWithTaxes,
                "in the product block!",
                true);

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