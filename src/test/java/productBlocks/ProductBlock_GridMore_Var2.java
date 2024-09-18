package productBlocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

import java.time.Duration;
import java.util.List;

/*
1) Настройки  блока товаров "Распродажа"
Шаблон                      -- AB: Сетка (с кнопкой "Показать ещё")
Показать номер элемента     -- n
Количество колонок в списке -- 4
Тип загрузки                -- По клику
Заполнение                  -- Товары со скидкой
Макс. число элементов       -- 15
Спрятать кнопку добавления товара в корзину     -- n

2.1) UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Формат отображения цен                          -- Вариант 3
Отображать цену вверху                          -- n    !!!!!!!!!!!изменить в тест-кейсе 3
Отображать пустые звёзды рейтинга товара        -- n
Отображать общее значение рейтинга товара       -- n
Отображать кнопку "Добавить в избранное"        -- y
Отображать кнопку "Добавить в список сравнения" -- y
Отображать кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" при наведении на ячейку товара -- n
Отображать "Вы экономите"                       -- Сокращенный вид

2.2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Сетка"
Отображать код товара                           -- y
Отображать статус наличия                       -- y
Отображать модификатор количества               -- n
Отображать кнопку "Купить"                      -- Только иконка корзины    !!!!!!!!здесь ОСТАНОВИЛСЯ
Дополнительная информация о товаре              -- Краткое описание и характеристики
Отображать дополнительную информацию при наведении -- y
Отображать логотип бренда                       -- y

3) UniTheme2 -- Настройки цветосхемы            -- вкладка "Списки товаров":
Тип обрамления товара в сетке                   -- Рамка с внешними отступами
Добавить фон/маску для изображений товара       -- n
Использовать выравнивание элементов в товарной сетке --	y
Эффект увеличения ячейки при наведении          -- y
Насыщенность шрифта для названия товара         -- Нормальный

4) Настраиваем налог для всех товаров + CS-Cart настройки -- Внешний вид:
Показывать цены с налогом на страницах категорий и товаров-- y
*/

public class ProductBlock_GridMore_Var2 extends TestRunner implements DisableLazyLoadFromSection {
    String blockID;

    @Test(priority = 1)
    public void setConfigurationsForProductBlock_GridMore_Var1() {
        CsCartSettings csCartSettings = new CsCartSettings();

        //Настраиваем блок товаров "Распродажа"
        disableLazyLoadFromSection("Распродажа");   //Выключаем LazyLoad в секции с блоком
        makePause();
        blockID = getBlockID("Распродажа");  //Получаем ID нужного блока товаров
        csCartSettings.navigateToBlockSettings("Распродажа");
        csCartSettings.selectSetting_BlockTemplate("blocks/products/ab__grid_list.tpl");
        csCartSettings.button_SettingsOfTemplate.click();
        if (csCartSettings.checkbox_ShowItemNumber.isSelected())
            csCartSettings.checkbox_ShowItemNumber.click();
        csCartSettings.clickAndType_Field_NumberOfColumnsInList("4");
        csCartSettings.selectSetting_LoadingType("onclick");
        csCartSettings.tabOfBlock_Content.click();
        csCartSettings.selectSetting_Filling("on_sale");
        csCartSettings.clickAndType_Field_Limit("15");
        csCartSettings.tabOfBlock_BlockSettings.click();
        if (csCartSettings.checkbox_HideAddToCartButton.isSelected())
            csCartSettings.checkbox_HideAddToCartButton.click();
        csCartSettings.button_saveBlock.click();

        //Работаем с настройками характеристики Бренд
        csCartSettings.navigateToSection_Features();
        csCartSettings.clickFeatureBrand();
        WebElement checkboxShowInProductList = csCartSettings.showInProductList;
        if (!checkboxShowInProductList.isSelected()) {
            checkboxShowInProductList.click();
        }
        csCartSettings.clickSaveButtonOfSettings();

        //Работаем с настройками темы п.2.1
        ThemeSettings_ProductLists themeSettingsProductLists = csCartSettings.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.selectSettingPriceDisplayFormat("mix");
        WebElement checkboxPriceAtTheTop = themeSettingsProductLists.settingPriceAtTheTop;
        if (checkboxPriceAtTheTop.isSelected()) {
            checkboxPriceAtTheTop.click();
        }
        WebElement checkboxProductRating = themeSettingsProductLists.settingProductRating;
        if (checkboxProductRating.isSelected()) {
            checkboxProductRating.click();
        }
        WebElement checkboxSettingCommonValueOfProductRating = themeSettingsProductLists.settingCommonValueOfProductRating;
        if (checkboxSettingCommonValueOfProductRating.isSelected()) {
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
        themeSettingsProductLists.selectSettingShowYouSave("short");

        //Работаем с настройками темы п.2.2
        WebElement checkboxSettingShowProductCode = themeSettingsProductLists.settingShowProductCode;
        if (!checkboxSettingShowProductCode.isSelected()) {
            checkboxSettingShowProductCode.click();
        }
        WebElement checkboxSettingDisplayAvailabilityStatus = themeSettingsProductLists.settingDisplayAvailabilityStatus;
        if (!checkboxSettingDisplayAvailabilityStatus.isSelected()) {
            checkboxSettingDisplayAvailabilityStatus.click();
        }
        WebElement checkboxSettingShowQuantityChanger = themeSettingsProductLists.settingShowQuantityChanger;
        if (checkboxSettingShowQuantityChanger.isSelected()) {
            checkboxSettingShowQuantityChanger.click();
        }
        themeSettingsProductLists.selectSettingShowAddToCartButton("icon_button");
        themeSettingsProductLists.selectSettingAdditionalProductInformation("features_and_description");
        WebElement checkboxSettingShowAdditionalInformationOnHover = themeSettingsProductLists.settingShowAdditionalInformationOnHover;
        if (!checkboxSettingShowAdditionalInformationOnHover.isSelected()) {
            checkboxSettingShowAdditionalInformationOnHover.click();
        }
        WebElement checkboxSettingShowBrandLogo = themeSettingsProductLists.settingShowBrandLogo;
        if (!checkboxSettingShowBrandLogo.isSelected()) {
            checkboxSettingShowBrandLogo.click();
        }
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

        //п.4.1 Настраиваем налог для всех товаров
        csCartSettings.navigateToCheckoutSettings();
        csCartSettings.selectSetting_TaxCalculationMethodBasedOn("unit_price");
        csCartSettings.clickSaveButtonOfSettings();
        csCartSettings.navigateToTaxes();
        WebElement checkboxPriceIncludesTax = csCartSettings.setting_priceIncludesTax;
        if (checkboxPriceIncludesTax.isSelected()) {
            checkboxPriceIncludesTax.click();
            csCartSettings.button_saveTaxes.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            csCartSettings.vat20.click();
            (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn-group.bulk-edit__wrapper")));
            csCartSettings.button_Actions.click();
            csCartSettings.button_ApplySelectedTaxesToAllProducts.click();
        }

        //п.4.2 Работаем с CS-Cart настройками
        csCartSettings.navigateToAppearanceSettings();
        WebElement checkboxDisplayPricesWithTaxesOnCategoryAndProductPages = csCartSettings.setting_DisplayPricesWithTaxesOnCategoryAndProductPages;
        if (!checkboxDisplayPricesWithTaxesOnCategoryAndProductPages.isSelected()) {
            checkboxDisplayPricesWithTaxesOnCategoryAndProductPages.click();
            csCartSettings.clickSaveButtonOfSettings();
        }
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductBlock_GridMore_Var1")
    public void checkProductBlock_GridMore_Var1() {
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

        //Проверяем, что у блока товаров 4 колонки. Настройка блока "Количество колонок в списке -- 4"
        softAssert.assertEquals(DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ty-column4")).size(), 4,
                "Number of columns is not equal 4 in the product block!");

        int num = 1;
        while (true) {
            List<WebElement> buttons = DriverProvider.getDriver().findElements(By.xpath("(//span[contains(@id, 'ut2_load_more_block_')])[3]"));
            if (!buttons.isEmpty() && buttons.getFirst().isDisplayed()) {
                WebElement button_ShowMore = buttons.getFirst(); // Берем первый элемент из списка

                Actions scroll = new Actions(DriverProvider.getDriver());
                scroll.moveToElement(tab_OnSale);
                scroll.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(button_ShowMore), 0, 400);
                scroll.perform();
                button_ShowMore.click();

                takeScreenShot("ProductBlock_GridMore_Var1 - ProductBlock " + num);
                num++;
            } else {
                break;
            }
        }

        //Проверяем, что у товаров отсутствуют пустые звёздочки рейтинга
        softAssert.assertFalse(!assertsOnStorefront.emptyStarsOfProductRating.isEmpty(),
                "There are empty stars but shouldn't in the product block!");

        //Проверяем, что у товаров отсутствует общее значение рейтинга товара
        softAssert.assertFalse(!assertsOnStorefront.commonValueOfProductRating.isEmpty(),
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

        //Проверяем, что текст "Вы экономите" присутствует
        softAssert.assertTrue(!assertsOnStorefront.text_YouSave.isEmpty(),
                "There is no text 'You save' in the product block!");

        //Проверяем, что у товаров присутствует текст "[цена налога] + Вкл налог"
        softAssert.assertTrue(!assertsOnStorefront.pricesWithTaxes.isEmpty(),
                "There is no text of a product tax in the product block!");

        //Проверяем, что код товара присутствует
        softAssert.assertTrue(!assertsOnStorefront.productCode.isEmpty(),
                "There is no product code in the product block!");

        //Проверяем, что статус наличия присутствует
        softAssert.assertTrue(!assertsOnStorefront.availabilityStatus.isEmpty(),
                "There is no availability status in the product block!");

        //Проверяем, что модификатор количества отсутствует
        softAssert.assertFalse(!assertsOnStorefront.quantityChanger.isEmpty(),
                "There is a quantity Changer but shouldn't in the product block!");

        //Проверяем, что кнопка "Купить" в виде "Только иконка корзины"
        softAssert.assertTrue(!assertsOnStorefront.gridList__ShowAddToCartButton_IconOnly.isEmpty(),
                "The button 'Add to cart' is not as 'Icon only' or even missed in the product block!");

        //Проверяем настройку "Дополнительная информация о товаре -- Краткое описание и характеристики"
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID +"'] .product-description")).isEmpty()
                        && !DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ut2-gl__feature")).isEmpty(),
                "Additional information about products is not 'Short description and features' in the block!");

        //Проверяем, что дополнительная информация отображается при наведении
        softAssert.assertTrue(!assertsOnStorefront.additionalInformationOnHover.isEmpty(),
                "Buttons are displayed without mouse hover in the product block!");

        //Проверяем, что логотип бренда присутствует
        softAssert.assertTrue(!assertsOnStorefront.brandLogo.isEmpty(),
                "There is no product logo in the product block!");

        //Проверяем, что Максимальное число элементов -- 15 (не превышает это значение)
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By
                        .cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ut2-gl__item")).size() <= 15,
                "Max number of products increases 15 products in the block!");

        stHomePage.selectLanguage_RTL();
        WebElement tab_OnSaleRTL = DriverProvider.getDriver().findElement(By.xpath("//span[@class='ty-tabs__span'][text()='On Sale']"));
        tab_OnSaleRTL.click();

        int numRTL = 5;
        while (true) {
            List<WebElement> buttons = DriverProvider.getDriver().findElements(By.xpath("(//span[contains(@id, 'ut2_load_more_block_')])[3]"));
            if (!buttons.isEmpty() && buttons.getFirst().isDisplayed()) {
                WebElement button_ShowMore = buttons.getFirst(); // Берем первый элемент из списка

                Actions scroll = new Actions(DriverProvider.getDriver());
                scroll.moveToElement(tab_OnSaleRTL);
                scroll.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(button_ShowMore), 0, 400);
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