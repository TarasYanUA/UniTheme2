import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ThemeSettings_ProductLists;
import taras.constants.DriverProvider;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;

/*
UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Список без опций":
Отображать код товара   -- нет
Отображать статус наличия   -- нет
Отображать модификатор количества   -- да
Содержимое под описанием    -- Список вариаций
Отображать опции товара -- нет
Отображать логотип бренда   -- нет
Показывать галерею мини-иконок товара в товарном списке --  Не отображать
Переключать изображение товара при движении мышки   -- с точками
*/

public class GeneralSettings_ProductLists_ListWithoutOptions_Var1 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductLists_ListWithoutOptions_Var1() {
        //Работаем с настройками характеристики Бренд
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.hoverToProductMenu();
        csCartSettings.navigateToSection_Features();
        csCartSettings.clickFeatureBrand();
        WebElement checkboxShowInProductList = csCartSettings.showInProductList;
        if (!checkboxShowInProductList.isSelected()) {
            checkboxShowInProductList.click();
        }
        csCartSettings.clickSaveButtonOfSettings();

        //Работаем с настройками темы
        ThemeSettings_ProductLists themeSettingsProductLists = csCartSettings.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.clickTabProductLists();
        WebElement checkboxProductCode = themeSettingsProductLists.withoutOptionsProductCode;
        if (checkboxProductCode.isSelected()) {
            checkboxProductCode.click();
        }
        WebElement checkboxAmountStatus = themeSettingsProductLists.withoutOptionsAmountStatus;
        if (checkboxAmountStatus.isSelected()) {
            checkboxAmountStatus.click();
        }
        WebElement checkboxShowQuantity = themeSettingsProductLists.withoutOptionsShowQuantity;
        if (!checkboxShowQuantity.isSelected()) {
            checkboxShowQuantity.click();
        }
        themeSettingsProductLists.selectWithoutOptionsContentUnderDescription("variations");
        WebElement checkboxShowProductOptions = themeSettingsProductLists.withoutOptionsShowProductOptions;
        if (checkboxShowProductOptions.isSelected()) {
            checkboxShowProductOptions.click();
        }
        WebElement checkboxBrandLogo = themeSettingsProductLists.withoutOptionsBrandLogo;
        if (checkboxBrandLogo.isSelected()) {
            checkboxBrandLogo.click();
        }
        themeSettingsProductLists.selectWithoutOptions_ShowGalleryOfMiniIcons("N");
        themeSettingsProductLists.selectWithoutOptionsHoverGallery("points");
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_ListWithoutOptions_Var1")
    public void checkProductLists_ListWithoutOptions_Var1() {
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.cookie.click();
        stHomePage.navigateToMenuMenCloth();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.clickListWithoutOptions_ProductListView();
        makePause();
        stCategoryPage.clickListWithoutOptions_ProductListView(); //Второе нажатие необходимо, чтобы на скриншоте увидеть нужные товары
        SoftAssert softAssert = new SoftAssert();
        //Проверяем, что модификатор количества присутствует
        int sizeOfQuantityCharger = DriverProvider.getDriver().findElements(By.cssSelector("div[class*='ty-value-changer']")).size();
        softAssert.assertTrue(sizeOfQuantityCharger > 1, "There is no quantity charger on the product cell!");
        //Проверяем, что содержимое под описанием это список вариаций
        int sizeOfContentUnderDescription = DriverProvider.getDriver().findElements(By.cssSelector(".ut2-lv__item-features")).size();
        softAssert.assertTrue(sizeOfContentUnderDescription > 1, "The content under description is not a variation list!");
        //Проверяем, что переключатель изображений товара в виде точек
        int sizeOfMousePointersAsPoints = DriverProvider.getDriver().findElements(By.cssSelector("div[class='cm-ab-hover-gallery abt__ut2_hover_gallery points']")).size();
        softAssert.assertTrue(sizeOfMousePointersAsPoints > 1, "Image switcher is not with dots!");
        takeScreenShot("510 ListWithoutOptions_MenClothCategory_Var1");
        stHomePage.selectLanguage_RTL();
        takeScreenShot("515 ListWithoutOptions_MenClothCategory_Var1(RTL)");
        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductLists_ListWithoutOptions_Var1 passed successfully!");
    }
}