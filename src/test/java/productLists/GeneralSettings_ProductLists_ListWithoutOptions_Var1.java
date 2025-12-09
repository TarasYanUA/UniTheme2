package productLists;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.asserts.Asserts_ThemeSettings_ProductLists;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

/*
UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Список без опций":
Отображать код товара       -- нет
Отображать статус наличия   -- нет
Отображать модификатор количества   -- да
Отображать кнопку "Купить"  -- Только Иконка корзины
Содержимое под описанием    -- Список вариаций
Отображать опции товара     -- нет
Отображать бренд            -- Название
Отображать стандартную галерею изображений -- Не отображать
Переключать изображение товара при движении мышки       -- С точками
*/

public class GeneralSettings_ProductLists_ListWithoutOptions_Var1 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductLists_ListWithoutOptions_Var1() {
        //Настраиваем макет для тест-кейса
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Lightv2.click();
        layoutPage.setLayoutAsDefault();

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
        UtilsAdm.setCheckboxState(themeSettingsProductLists.withoutOptions_ProductCode, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.withoutOptions_AmountStatus, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.withoutOptions_ShowQuantity, true);
        new Select(themeSettingsProductLists.withoutOptions_ShowButtonAddToCart).selectByValue("icon_button");
        new Select(themeSettingsProductLists.withoutOptions_ContentUnderDescription).selectByValue("variations");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.withoutOptions_ShowProductOptions, false);
        new Select(themeSettingsProductLists.setting_ShowBrandLogo_ListWithoutOptions).selectByValue("name");
        new Select(themeSettingsProductLists.withoutOptions_ShowStandardImageGallery).selectByValue("N");
        new Select(themeSettingsProductLists.withoutOptions_SwitchProductImageWhenHovering).selectByValue("points");
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_ListWithoutOptions_Var1")
    public void checkProductLists_ListWithoutOptions_Var1() {
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Категория "Мужская одежда"
        stHomePage.navigateToHorizontalMenu_MenCloth();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.selectProductListView(stCategoryPage.listWithoutOptions_ProductListView);
        stCategoryPage.selectProductListView(stCategoryPage.listWithoutOptions_ProductListView); //Второе нажатие необходимо, чтобы на скриншоте увидеть нужные товары

        SoftAssert softAssert = new SoftAssert();
        Asserts_ThemeSettings_ProductLists asserts_productLists = new Asserts_ThemeSettings_ProductLists();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что модификатор количества присутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.listWO,
                asserts_productLists.quantityChanger,
                "on the category page 'List without options'!",
                true);

        //Проверяем, что кнопка "Купить" в виде "Только иконка корзины"
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.listWO,
                asserts_productLists.showAddToCartButton_IconOnly,
                "on the category page 'List without options'!",
                true);

        //Проверяем, что логотип бренда присутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.listWO,
                asserts_productLists.brandName,
                "on the category page 'List without options'!",
                true);

        //Проверяем, что содержимое под описанием это список вариаций
        softAssert.assertTrue(!assertsOnStorefront.listWithoutOptions__ContentUnderDescription_VariationList.isEmpty(),
                "The content under description is not a Variation list on the category page 'ListWithoutOptions'!");

        //Проверяем, что переключатель изображений товара в виде точек
        softAssert.assertTrue(!assertsOnStorefront.listWithoutOptions__SwitchProductImageWhenHoveringMousePointer_Dots.isEmpty(),
                "Image switcher is not with Dots on the category page 'ListWithoutOptions'!");

        takeScreenShot_withScroll("500 GS_ProductLists_ListWithoutOptions_Var1 - MenClothCategory");
        stHomePage.selectLanguage("ar");
        takeScreenShot_withScroll("505 GS_ProductLists_ListWithoutOptions_Var1 - MenClothCategory (RTL)");

        softAssert.assertAll();
        System.out.println("productLists.GeneralSettings_ProductLists_ListWithoutOptions_Var1 passed successfully!");
    }
}