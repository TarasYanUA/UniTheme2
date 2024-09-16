import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ThemeSettings_ProductLists;
import taras.constants.DriverProvider;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

import static taras.constants.DriverProvider.getDriver;

/*
1) "Товары -- Характеристики -- Бренд":
- включаем "Показывать в списке товаров"

2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Список без опций":
Ширина иконки товара    -- 400
Высота иконки товара    -- 300
Отображать код товара   -- да
Отображать статус наличия   -- да
Отображать модификатор количества   -- да
Отображать кнопку "Купить"  -- Иконка корзины и текст
Содержимое под описанием    -- Список характеристик
Отображать опции товара -- да
Отображать логотип бренда   -- да
Показывать галерею мини-иконок товара в товарном списке --  С точками
Переключать изображение товара при движении мышки   -- Не переключать
*/

public class GeneralSettings_ProductLists_ListWithoutOptions_Var2 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductLists_ListWithoutOptions_Var2() {
        //Работаем с настройками характеристики Бренд
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_Features();
        csCartSettings.clickFeatureBrand();
        WebElement checkboxShowInProductList = csCartSettings.showInProductList;
        if (!checkboxShowInProductList.isSelected()) {
            checkboxShowInProductList.click();
            csCartSettings.clickSaveButtonOfSettings();
        }

        //Работаем с настройками темы
        ThemeSettings_ProductLists themeSettingsProductLists = csCartSettings.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.clickTabProductLists();
        themeSettingsProductLists.clickAndTypeWithoutOptionsIconWidth("400");
        themeSettingsProductLists.clickAndTypeWithoutOptionsIconHeight("200");
        WebElement checkboxProductCode = themeSettingsProductLists.withoutOptionsProductCode;
        if (!checkboxProductCode.isSelected()) {
            checkboxProductCode.click();
        }
        WebElement checkboxAmountStatus = themeSettingsProductLists.withoutOptionsAmountStatus;
        if (!checkboxAmountStatus.isSelected()) {
            checkboxAmountStatus.click();
        }
        WebElement checkboxShowQuantity = themeSettingsProductLists.withoutOptionsShowQuantity;
        if (!checkboxShowQuantity.isSelected()) {
            checkboxShowQuantity.click();
        }
        themeSettingsProductLists.selectWithoutOptions_ShowButtonAddToCart("icon_and_text");
        themeSettingsProductLists.selectWithoutOptionsContentUnderDescription("features");
        WebElement checkboxShowProductOptions = themeSettingsProductLists.withoutOptionsShowProductOptions;
        if (!checkboxShowProductOptions.isSelected()) {
            checkboxShowProductOptions.click();
        }
        WebElement checkboxBrandLogo = themeSettingsProductLists.settingShowBrandLogo_ListWithoutOptions;
        if (!checkboxBrandLogo.isSelected()) {
            checkboxBrandLogo.click();
        }
        themeSettingsProductLists.selectWithoutOptions_ShowStandardImageGallery("points");
        themeSettingsProductLists.selectWithoutOptions_SwitchProductImageWhenHovering("N");
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_ListWithoutOptions_Var2")
    public void checkProductLists_ListWithoutOptions_Var2() {
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Категория "Мужская одежда"
        stHomePage.navigateToHorizontalMenu_MenCloth();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.clickListWithoutOptions_ProductListView();

        SoftAssert softAssert = new SoftAssert();
        //Проверяем, что код товара присутствует
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ty-product-list div[id*='product_code']")).isEmpty(),
                "There is no product code on the category page 'ListWithoutOptions'!");

        //Проверяем, что статус наличия присутствует
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ty-qty-in-stock.ty-control-group__item")).isEmpty(),
                "There is no availability status on the category page 'ListWithoutOptions'!");

        //Проверяем, что модификатор количества присутствует
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector("div[class*='ty-value-changer']")).isEmpty(),
                "There is no quantity charger on the category page 'ListWithoutOptions'!");

        //Проверяем, что содержимое под описанием это список характеристик
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pl__feature")).isEmpty(),
                "The content under description is not a feature list!");

        //Проверяем, что опции товара присутствуют
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".cm-picker-product-options")).isEmpty(),
                "There is no product options on the category page 'ListWithoutOptions'!");

        //Проверяем, что логотип бренда присутствует
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".brand-img")).isEmpty(),
                "There is no product logo on the category page 'ListWithoutOptions'!");

        //Проверяем, что переключатель изображений товара в виде полосок
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".owl-pagination")).isEmpty(),
                "Image switcher is not with points on the category page 'ListWithoutOptions'!");

        //Проверяем, что кнопка "Купить" в виде "Иконка корзины и текст"
        softAssert.assertTrue(!getDriver().findElements(By.cssSelector(".ut2-icon-use_icon_cart")).isEmpty()
                && !getDriver().findElements(By.cssSelector(".ty-btn__primary.ty-btn__add-to-cart.cm-form-dialog-closer")).isEmpty(),
                "The button 'Add to cart' does not have a view 'Icon of the Cart and text'!");

        takeScreenShot_withScroll("600 GS_ProductLists_ListWithoutOptions_Var2 - MenClothCategory");
        stHomePage.selectLanguage_RTL();
        takeScreenShot_withScroll("605 GS_ProductLists_ListWithoutOptions_Var2 - MenClothCategory (RTL)");
        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductLists_ListWithoutOptions_Var2 passed successfully!");
    }
}