package productBlocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
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
        disableLazyLoadFromSection("Распродажа");   //Выключаем LazyLoad в секции с блоком
        UtilsAdm.makePause(2000);
        blockID = getBlockID("Распродажа");  //Получаем ID нужного блока товаров
        basicPage.navigateToBlockSettings("Распродажа");
        basicPage.selectSetting_BlockTemplate("blocks/products/ab__grid_list.tpl");
        UtilsAdm.makePause(2000);
        basicPage.button_SettingsOfTemplate.click();
        if (basicPage.checkbox_ShowItemNumber.isSelected())
            basicPage.checkbox_ShowItemNumber.click();
        basicPage.clickAndType_Field_NumberOfColumnsInList("5");
        basicPage.selectSetting_LoadingType("onclick");
        basicPage.tabOfBlock_Content.click();
        basicPage.selectSetting_Filling("on_sale");
        basicPage.clickAndType_Field_Limit("17");
        basicPage.tabOfBlock_BlockSettings.click();
        if (basicPage.checkbox_HideAddToCartButton.isSelected())
            basicPage.checkbox_HideAddToCartButton.click();
        basicPage.button_saveBlock.click();

        //Работаем с настройками характеристики Бренд
        csCartSettings.navigateToSection_Features();
        csCartSettings.clickFeatureBrand();
        WebElement checkboxShowInProductList = csCartSettings.showInProductList;
        if (!checkboxShowInProductList.isSelected())
            checkboxShowInProductList.click();
        csCartSettings.clickSaveButtonOfSettings();

        //Работаем с настройками темы п.2.1
        ThemeSettings_ProductLists themeSettingsProductLists = csCartSettings.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.selectSettingPriceDisplayFormat("row-mix");
        WebElement checkboxPriceAtTheTop = themeSettingsProductLists.settingPriceAtTheTop;
        if (checkboxPriceAtTheTop.isSelected())
            checkboxPriceAtTheTop.click();
        WebElement checkboxProductRating = themeSettingsProductLists.settingEmptyStarsOfProductRating;
        if (!checkboxProductRating.isSelected())
            checkboxProductRating.click();
        WebElement checkboxSettingCommonValueOfProductRating = themeSettingsProductLists.settingCommonValueOfProductRating;
        if (checkboxSettingCommonValueOfProductRating.isSelected())
            checkboxSettingCommonValueOfProductRating.click();
        WebElement checkboxSettingDisplayButtonComparisonList = themeSettingsProductLists.settingDisplayButtonComparisonList;
        if (!checkboxSettingDisplayButtonComparisonList.isSelected())
            checkboxSettingDisplayButtonComparisonList.click();
        WebElement checkboxSettingDisplayButtonWishList = themeSettingsProductLists.settingDisplayButtonWishList;
        if (!checkboxSettingDisplayButtonWishList.isSelected())
            checkboxSettingDisplayButtonWishList.click();
        if(!themeSettingsProductLists.settingDisplayButtonsWhenHoveringMouse.isSelected())
            themeSettingsProductLists.settingDisplayButtonsWhenHoveringMouse.click();
        themeSettingsProductLists.selectSettingShowYouSave("short");

        //Работаем с настройками темы п.2.2
        themeSettingsProductLists.selectGrid_NumberOfLinesInProductName("1");
        WebElement checkboxSettingShowProductCode = themeSettingsProductLists.settingShowProductCode;
        if (!checkboxSettingShowProductCode.isSelected())
            checkboxSettingShowProductCode.click();
        WebElement checkboxSettingDisplayAvailabilityStatus = themeSettingsProductLists.settingDisplayAvailabilityStatus;
        if (!checkboxSettingDisplayAvailabilityStatus.isSelected())
            checkboxSettingDisplayAvailabilityStatus.click();
        WebElement checkboxSettingShowQuantityChanger = themeSettingsProductLists.settingShowQuantityChanger;
        if (!checkboxSettingShowQuantityChanger.isSelected())
            checkboxSettingShowQuantityChanger.click();
        themeSettingsProductLists.selectSettingShowAddToCartButton("icon_and_text");
        themeSettingsProductLists.selectSettingAdditionalProductInformation("features_and_description");
        WebElement checkboxSettingShowAdditionalInformationOnHover = themeSettingsProductLists.settingShowAdditionalInformationOnHover;
        if (!checkboxSettingShowAdditionalInformationOnHover.isSelected())
            checkboxSettingShowAdditionalInformationOnHover.click();
        themeSettingsProductLists.selectSettingShowBrand("logo");
        themeSettingsProductLists.selectSetting_ShowGalleryOfMiniIcons("arrows");
        themeSettingsProductLists.selectSetting_SwitchProductImageWhenHovering("N");
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем UniTheme цветосхему, вкладка "Списки товаров"
        ColorSchemeSettings colorSchemeSettings = csCartSettings.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.selectActiveColorScheme();
        colorSchemeSettings.tab_ProductLists.click();
        colorSchemeSettings.selectSetting_FrameType("solid_with_margins");
        if (colorSchemeSettings.setting_ProductLists_MaskForProductImages.isSelected())
            colorSchemeSettings.setting_ProductLists_MaskForProductImages.click();
        colorSchemeSettings.selectSetting_ProductLists_ElementsAlignment("use");
        if (!colorSchemeSettings.setting_ProductLists_ExpandGridItemOnHover.isSelected())
            colorSchemeSettings.setting_ProductLists_ExpandGridItemOnHover.click();
        colorSchemeSettings.selectSetting_ProductLists_FontWeightForProductName("normal");
        csCartSettings.clickSaveButtonOfSettings();

        //Настраиваем налог для всех товаров
        csCartSettings.setTaxesForAllProducts();

        //Добавляем Краткое описание товару Ice Queen
        ProductSettings productSettings = basicPage.navigateToSection_Products();
        productSettings.clickAndType_SearchFieldOfProduct("Ice Queen");
        productSettings.chooseAnyProduct();
        UtilsAdm.closeAllNotifications();
        productSettings.hoverAndTypeField_ShortDescription(
                "Рюкзак Camelbak - женский рюкзак идеально подходящий для активного отдыха в зимний период времени. Общий объём 16,4 литра, что позволяет вместить необходимый багаж. В этом рюкзаке есть отделение для воды с системой, не позволяющей ей замерзнуть. Резервуар для воды 2 литра.");
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
        WebElement tab_OnSale = DriverProvider.getDriver().findElement(By.xpath("//span[@class='ty-tabs__span'][text()='Распродажа']"));
        tab_OnSale.click();
        UtilsAdm.makePause(2000);

        //Проверяем, что у блока товаров 5 колонок. Настройка блока "Количество колонок в списке -- 5"
        softAssert.assertEquals(DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ty-column5")).size(), 5,
                "Number of columns is not equal 5 in the product block!");

        int num = 1;
        while (true) {
            List<WebElement> buttons = DriverProvider.getDriver().findElements(By.cssSelector("span[id*='ut2_load_more_block_" + blockID + "']"));
            if (!buttons.isEmpty() && buttons.getFirst().isDisplayed()) {
                WebElement button_ShowMore = buttons.getFirst(); // Берем первый элемент из списка

                Actions scroll = new Actions(DriverProvider.getDriver());
                scroll.moveToElement(tab_OnSale);
                scroll.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(button_ShowMore), 0, 500);
                scroll.perform();
                button_ShowMore.click();

                takeScreenShot("ProductBlock_GridMore_Var1 - ProductBlock " + num);
                num++;
            } else {
                break;
            }
        }

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
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID +"'] .product-description")).isEmpty()
                        && !DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ut2-gl__feature")).isEmpty(),
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
        WebElement tab_OnSaleRTL = DriverProvider.getDriver().findElement(By.xpath("//span[@class='ty-tabs__span'][text()='On Sale']"));
        tab_OnSaleRTL.click();
        UtilsAdm.makePause(2000);

        int numRTL = 1;
        while (true) {
            List<WebElement> buttons = DriverProvider.getDriver().findElements(By.cssSelector("span[id*='ut2_load_more_block_" + blockID + "']"));
            if (!buttons.isEmpty() && buttons.getFirst().isDisplayed()) {
                WebElement button_ShowMore = buttons.getFirst(); // Берем первый элемент из списка

                Actions scroll = new Actions(DriverProvider.getDriver());
                scroll.moveToElement(tab_OnSaleRTL);
                scroll.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(button_ShowMore), 0, 600);
                scroll.perform();
                button_ShowMore.click();

                takeScreenShot("ProductBlock_GridMore_Var1 - ProductBlock (RTL) " + numRTL);
                numRTL++;
            } else {
                break;
            }
        }

        softAssert.assertAll();
        System.out.println("ProductBlock_GridMore_Var1 passed successfully!");
    }
}