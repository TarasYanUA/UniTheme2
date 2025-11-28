package productLists;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.BasicPage;
import taras.adminPanel.FeaturePage;
import taras.adminPanel.ThemeSettings_ProductLists;
import taras.adminPanel.UtilsAdm;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

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
Отображать бренд        -- Логотип
Отображать стандартную галерею изображений --  Навигация точками
Переключать изображение товара при движении мышки   -- Не переключать
*/

public class GeneralSettings_ProductLists_ListWithoutOptions_Var2 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductLists_ListWithoutOptions_Var2() {
        BasicPage basicPage = new BasicPage();

        //Работаем с настройками характеристики Бренд
        FeaturePage featurePage = basicPage.navigateToSection_Features();
        featurePage.featureBrand.click();
        WebElement checkboxShowInProductList = featurePage.showInProductList;
        if (!checkboxShowInProductList.isSelected()) {
            checkboxShowInProductList.click();
            basicPage.clickSaveButtonOfSettings();
        }

        //Работаем с настройками темы
        ThemeSettings_ProductLists themeSettingsProductLists = basicPage.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.tabProductLists.click();
        UtilsAdm.clickAndType(themeSettingsProductLists.withoutOptions_IconWidth, "400");
        UtilsAdm.clickAndType(themeSettingsProductLists.withoutOptions_IconHeight, "200");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.withoutOptions_ProductCode, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.withoutOptions_AmountStatus, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.withoutOptions_ShowQuantity, true);
        new Select(themeSettingsProductLists.withoutOptions_ShowButtonAddToCart).selectByValue("icon_and_text");
        new Select(themeSettingsProductLists.withoutOptions_ContentUnderDescription).selectByValue("features");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.withoutOptions_ShowProductOptions, true);
        new Select(themeSettingsProductLists.setting_ShowBrandLogo_ListWithoutOptions).selectByValue("logo");
        new Select(themeSettingsProductLists.withoutOptions_ShowStandardImageGallery).selectByValue("points");
        new Select(themeSettingsProductLists.withoutOptions_SwitchProductImageWhenHovering).selectByValue("N");
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_ListWithoutOptions_Var2")
    public void checkProductLists_ListWithoutOptions_Var2() {
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Категория "Мужская одежда"
        stHomePage.navigateToHorizontalMenu_MenCloth();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.selectProductListView(stCategoryPage.listWithoutOptions_ProductListView);

        SoftAssert softAssert = new SoftAssert();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что код товара присутствует
        softAssert.assertTrue(!assertsOnStorefront.productCode_ListWithoutOptions().isEmpty(),
                "There is no product code on the category page 'ListWithoutOptions'!");

        //Проверяем, что статус наличия присутствует
        softAssert.assertTrue(!assertsOnStorefront.availabilityStatus_ListWithoutOptions().isEmpty(),
                "There is no availability status on the category page 'ListWithoutOptions'!");

        //Проверяем, что модификатор количества присутствует
        softAssert.assertTrue(!assertsOnStorefront.quantityChanger_ListWithoutOptions().isEmpty(),
                "There is no quantity charger on the category page 'ListWithoutOptions'!");

        //Проверяем, что содержимое под описанием это список характеристик
        softAssert.assertTrue(!assertsOnStorefront.listWithoutOptions__ContentUnderDescription_FeatureList.isEmpty(),
                "The content under description is not a Feature list on the category page 'ListWithoutOptions'!");

        //Проверяем, что опции товара присутствуют
        softAssert.assertTrue(!assertsOnStorefront.listWithoutOptions__ShowProductOptions.isEmpty(),
                "There is no product options on the category page 'ListWithoutOptions'!");

        //Проверяем, что логотип бренда присутствует
        softAssert.assertTrue(!assertsOnStorefront.listWithoutOptions__BrandLogo().isEmpty(),
                "There is no brand logo on the category page 'ListWithoutOptions'!");

        //Проверяем, что стандартная галерея изображений товара с навигацией точками
        softAssert.assertTrue(!assertsOnStorefront.listWithoutOptions__ShowStandardImageGallery().isEmpty(),
                "Gallery of mini icons is not with points on the category page 'ListWithoutOptions'!");

        //Проверяем, что кнопка "Купить" в виде "Иконка корзины и текст"
        softAssert.assertTrue(!assertsOnStorefront.listWithoutOptions__ShowAddToCartButton_IconOnly().isEmpty()
                && !assertsOnStorefront.listWithoutOptions__ShowAddToCartButton_TextOnly().isEmpty(),
                "The button 'Add to cart' does not have a view 'Icon of the Cart and text'!");

        takeScreenShot_withScroll("600 GS_ProductLists_ListWithoutOptions_Var2 - MenClothCategory");
        stHomePage.selectLanguage("ar");
        takeScreenShot_withScroll("605 GS_ProductLists_ListWithoutOptions_Var2 - MenClothCategory (RTL)");

        softAssert.assertAll();
        System.out.println("productLists.GeneralSettings_ProductLists_ListWithoutOptions_Var2 passed successfully!");
    }
}