import org.openqa.selenium.*;
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
UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Список без опций":
Отображать код товара       -- нет
Отображать статус наличия   -- нет
Отображать модификатор количества   -- да
Отображать кнопку "Купить"  -- Только Иконка корзины
Содержимое под описанием    -- Список вариаций
Отображать опции товара     -- нет
Отображать логотип бренда   -- нет
Показывать галерею мини-иконок товара в товарном списке -- Не отображать
Переключать изображение товара при движении мышки       -- с точками
*/

public class GeneralSettings_ProductLists_ListWithoutOptions_Var1 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductLists_ListWithoutOptions_Var1() {
        //Настраиваем макет для тест-кейса
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_WebsiteLayouts();
        csCartSettings.layout_Lightv2.click();
        csCartSettings.setLayoutAsDefault();

        //Работаем с настройками характеристики Бренд
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
        themeSettingsProductLists.selectWithoutOptions_ShowButtonAddToCart("icon_button");
        themeSettingsProductLists.selectWithoutOptionsContentUnderDescription("variations");
        WebElement checkboxShowProductOptions = themeSettingsProductLists.withoutOptionsShowProductOptions;
        if (checkboxShowProductOptions.isSelected()) {
            checkboxShowProductOptions.click();
        }
        WebElement checkboxBrandLogo = themeSettingsProductLists.withoutOptionsBrandLogo;
        if (checkboxBrandLogo.isSelected()) {
            checkboxBrandLogo.click();
        }
        themeSettingsProductLists.selectWithoutOptions_ShowStandardImageGallery("N");
        themeSettingsProductLists.selectWithoutOptions_SwitchProductImageWhenHovering("points");
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_ListWithoutOptions_Var1")
    public void checkProductLists_ListWithoutOptions_Var1() {
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Категория "Мужская одежда"
        stHomePage.navigateToHorizontalMenu_MenCloth();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.clickListWithoutOptions_ProductListView();
        makePause();
        stCategoryPage.clickListWithoutOptions_ProductListView(); //Второе нажатие необходимо, чтобы на скриншоте увидеть нужные товары

        SoftAssert softAssert = new SoftAssert();
        //Проверяем, что модификатор количества присутствует
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector("div[class*='ty-value-changer']")).isEmpty(), 
                "There is no quantity charger on the product cell on the category page 'ListWithoutOptions'!");

        //Проверяем, что содержимое под описанием это список вариаций
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector(".ut2-lv__item-features")).isEmpty(), 
                "The content under description is not a variation list on the category page 'ListWithoutOptions'!");

        //Проверяем, что переключатель изображений товара в виде точек
        softAssert.assertTrue(!DriverProvider.getDriver().findElements(By.cssSelector("div[class='cm-ab-hover-gallery abt__ut2_hover_gallery points']")).isEmpty(),
                "Image switcher is not with dots on the category page 'ListWithoutOptions'!");

        //Проверяем, что кнопка "Купить" только иконкой
        softAssert.assertTrue(!getDriver().findElements(By.cssSelector(".ut2-icon-use_icon_cart")).isEmpty(),
                "The button 'Add to cart' is not Icon only or even missed on the category page 'ListWithoutOptions'!");

        takeScreenShot_withScroll("500 GS_ProductLists_ListWithoutOptions_Var1 - MenClothCategory");
        stHomePage.selectLanguage_RTL();
        takeScreenShot_withScroll("505 GS_ProductLists_ListWithoutOptions_Var1 - MenClothCategory (RTL)");
        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductLists_ListWithoutOptions_Var1 passed successfully!");
    }
}