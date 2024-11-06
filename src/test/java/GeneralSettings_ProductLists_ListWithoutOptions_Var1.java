import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ThemeSettings_ProductLists;
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
Отображать логотип бренда   -- нет
Отображать стандартную галерею изображений -- Не отображать
Переключать изображение товара при движении мышки       -- С точками
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
        WebElement checkboxProductCode = themeSettingsProductLists.withoutOptions_ProductCode;
        if (checkboxProductCode.isSelected()) {
            checkboxProductCode.click();
        }
        WebElement checkboxAmountStatus = themeSettingsProductLists.withoutOptions_AmountStatus;
        if (checkboxAmountStatus.isSelected()) {
            checkboxAmountStatus.click();
        }
        WebElement checkboxShowQuantity = themeSettingsProductLists.withoutOptions_ShowQuantity;
        if (!checkboxShowQuantity.isSelected()) {
            checkboxShowQuantity.click();
        }
        themeSettingsProductLists.selectWithoutOptions_ShowButtonAddToCart("icon_button");
        themeSettingsProductLists.selectWithoutOptionsContentUnderDescription("variations");
        WebElement checkboxShowProductOptions = themeSettingsProductLists.withoutOptions_ShowProductOptions;
        if (checkboxShowProductOptions.isSelected()) {
            checkboxShowProductOptions.click();
        }
        WebElement checkboxBrandLogo = themeSettingsProductLists.withoutOptions_BrandLogo;
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
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что модификатор количества присутствует
        softAssert.assertTrue(!assertsOnStorefront.quantityChanger_ListWithoutOptions().isEmpty(),
                "There is no quantity charger on the product cell on the category page 'ListWithoutOptions'!");

        //Проверяем, что кнопка "Купить" в виде "Только иконка корзины"
        softAssert.assertTrue(!assertsOnStorefront.listWithoutOptions__ShowAddToCartButton_IconOnly().isEmpty(),
                "The button 'Add to cart' is not Icon only or even missed on the category page 'ListWithoutOptions'!");

        //Проверяем, что содержимое под описанием это список вариаций
        softAssert.assertTrue(!assertsOnStorefront.listWithoutOptions__ContentUnderDescription_VariationList.isEmpty(),
                "The content under description is not a Variation list on the category page 'ListWithoutOptions'!");

        //Проверяем, что переключатель изображений товара в виде точек
        softAssert.assertTrue(!assertsOnStorefront.listWithoutOptions__SwitchProductImageWhenHoveringMousePointer_Dots.isEmpty(),
                "Image switcher is not with Dots on the category page 'ListWithoutOptions'!");

        takeScreenShot_withScroll("500 GS_ProductLists_ListWithoutOptions_Var1 - MenClothCategory");
        stHomePage.selectLanguage_RTL();
        takeScreenShot_withScroll("505 GS_ProductLists_ListWithoutOptions_Var1 - MenClothCategory (RTL)");

        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductLists_ListWithoutOptions_Var1 passed successfully!");
    }
}