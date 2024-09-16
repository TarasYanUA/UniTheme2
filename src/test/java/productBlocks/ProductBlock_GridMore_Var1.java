package productBlocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import taras.adminPanel.ColorSchemeSettings;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ThemeSettings_ProductLists;
import taras.constants.DriverProvider;
import testRunner.TestRunner;

import java.time.Duration;

/*
1) CS-Cart настройки -- Внешний вид:
Показывать цены с налогом на страницах категорий и товаров --   y

2.1) UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Формат отображения цен --	Вариант 4
Отображать цену вверху --	n
Отображать пустые звёзды рейтинга товара        -- y
Отображать общее значение рейтинга товара       -- n
Отображать кнопку "Добавить в избранное"        -- y
Отображать кнопку "Добавить в список сравнения" -- y
Отображать кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" при наведении на ячейку товара -- y
Отображать "Вы экономите"                       -- Сокращенный вид

2.2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Сетка"
Отображать код товара -- да
Отображать статус наличия -- да
Отображать модификатор количества -- да
Отображать кнопку "Купить" -- Иконка корзины и текст
Дополнительная информация о товаре -- Краткое описание и характеристики
Отображать дополнительную информацию при наведении -- да
Отображать логотип бренда -- да

3) UniTheme2 -- Настройки цветосхемы -- вкладка "Списки товаров":
Тип обрамления товара в сетке --	Без рамки
Добавить фон/маску для изображений товара --	нет
Использовать выравнивание элементов в товарной сетке --	да
Эффект увеличения ячейки при наведении --	да
Насыщенность шрифта для названия товара --	Нормальный

4) Настраиваем налог для всех товаров
*/

public class ProductBlock_GridMore_Var1 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductBlock_GridMore_Var1() {
        CsCartSettings csCartSettings = new CsCartSettings();

        //Работаем с CS-Cart настройками
        csCartSettings.navigateToAppearanceSettings();
        WebElement checkboxDisplayPricesWithTaxesOnCategoryAndProductPages = csCartSettings.setting_DisplayPricesWithTaxesOnCategoryAndProductPages;
        if (!checkboxDisplayPricesWithTaxesOnCategoryAndProductPages.isSelected()) {
            checkboxDisplayPricesWithTaxesOnCategoryAndProductPages.click();
            csCartSettings.clickSaveButtonOfSettings();
        }

        //Работаем с настройками темы
        ThemeSettings_ProductLists themeSettingsProductLists = csCartSettings.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.selectSettingPriceDisplayFormat("row-mix");
        WebElement checkboxPriceAtTheTop = themeSettingsProductLists.settingPriceAtTheTop;
        if (checkboxPriceAtTheTop.isSelected()) {
            checkboxPriceAtTheTop.click();
        }
        WebElement checkboxProductRating = themeSettingsProductLists.settingProductRating;
        if (!checkboxProductRating.isSelected()) {
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
        themeSettingsProductLists.selectSettingShowYouSave("short");

        //п.2.2
        WebElement checkboxSettingShowProductCode = themeSettingsProductLists.settingShowProductCode;
        if (!checkboxSettingShowProductCode.isSelected()) {
            checkboxSettingShowProductCode.click();
        }
        WebElement checkboxSettingDisplayAvailabilityStatus = themeSettingsProductLists.settingDisplayAvailabilityStatus;
        if (!checkboxSettingDisplayAvailabilityStatus.isSelected()) {
            checkboxSettingDisplayAvailabilityStatus.click();
        }
        WebElement checkboxSettingShowQuantityChanger = themeSettingsProductLists.settingShowQuantityChanger;
        if (!checkboxSettingShowQuantityChanger.isSelected()) {
            checkboxSettingShowQuantityChanger.click();
        }
        themeSettingsProductLists.selectSettingShowAddToCartButton("icon_and_text");
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
        colorSchemeSettings.selectSetting_FrameType("none");
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
    }
}