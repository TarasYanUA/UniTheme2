import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ThemeSettings_ProductLists;
import taras.constants.DriverProvider;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;

/*
1) "Товары -- Характеристики -- Бренд":
- включаем "Показывать в списке товаров"

2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Список без опций":
Ширина иконки товара    -- 400
Высота иконки товара    -- 300
Отображать код товара   -- да
Отображать статус наличия   -- да
Отображать модификатор количества   -- да
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
        int sizeOfProductCodes = DriverProvider.getDriver().findElements(By.cssSelector(".ty-product-list div[id*='product_code']")).size();
        softAssert.assertTrue(sizeOfProductCodes > 1, "There is no product code on the category page 'ListWithoutOptions'!");
        //Проверяем, что статус наличия присутствует
        int sizeOfAvailabilityStatus = DriverProvider.getDriver().findElements(By.cssSelector(".ty-qty-in-stock.ty-control-group__item")).size();
        softAssert.assertTrue(sizeOfAvailabilityStatus > 1, "There is no availability status on the category page 'ListWithoutOptions'!");
        //Проверяем, что модификатор количества присутствует
        int sizeOfQuantityCharger = DriverProvider.getDriver().findElements(By.cssSelector("div[class*='ty-value-changer']")).size();
        softAssert.assertTrue(sizeOfQuantityCharger > 1, "There is no quantity charger on the category page 'ListWithoutOptions'!");
        //Проверяем, что содержимое под описанием это список характеристик
        int sizeOfContentUnderDescription = DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pl__feature")).size();
        softAssert.assertTrue(sizeOfContentUnderDescription > 1, "The content under description is not a feature list!");
        //Проверяем, что опции товара присутствуют
        int sizeOfProductOptions = DriverProvider.getDriver().findElements(By.cssSelector(".cm-picker-product-options")).size();
        softAssert.assertTrue(sizeOfProductOptions > 1, "There is no product options on the category page 'ListWithoutOptions'!");
        //Проверяем, что логотип бренда присутствует
        int sizeOfBrandLogo = DriverProvider.getDriver().findElements(By.cssSelector(".brand-img")).size();
        softAssert.assertTrue(sizeOfBrandLogo > 1, "There is no product logo on the category page 'ListWithoutOptions'!");
        //Проверяем, что переключатель изображений товара в виде полосок
        int sizeOfMousePointersAsPoints = DriverProvider.getDriver().findElements(By.cssSelector(".owl-pagination")).size();
        softAssert.assertTrue(sizeOfMousePointersAsPoints > 1, "Image switcher is not with points on the category page 'ListWithoutOptions'!");
        takeScreenShot_withScroll("600 GS_ProductLists_ListWithoutOptions_Var2 - MenClothCategory");
        stHomePage.selectLanguage_RTL();
        takeScreenShot_withScroll("605 GS_ProductLists_ListWithoutOptions_Var2 - MenClothCategory (RTL)");
        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductLists_ListWithoutOptions_Var2 passed successfully!");
    }
}