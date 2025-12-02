package productLists;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

import java.time.Duration;

/*
1) "Настройки -- Внешний вид":
Включить быстрый просмотр   -- да

2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Компактный список":
Отображать пустые звёзды рейтинга товара	-- нет
Отображать общее значение рейтинга товара	-- нет
Отображать код товара	    -- нет
Отображать статус наличия	-- нет
Отображать модификатор количества	-- да
Отображать кнопку "Купить"	-- Только Иконка корзины (упрощенный вариант)

3) Проверка проходит на странице категории "Игровые приставки"
*/

public class GeneralSettings_ProductLists_CompactList_Var1 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductLists_CompactList_Var1() {
        //Настраиваем макет для тест-кейса
        BasicPage basicPage = new BasicPage();
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_Lightv2.click();
        layoutPage.setLayoutAsDefault();

        //Работаем с настройками CS-Cart
        CsCartSettings csCartSettings = basicPage.navigateToAppearanceSettings();
        if (!csCartSettings.setting_QuickView.isSelected()) {
            csCartSettings.setting_QuickView.click();
            basicPage.clickSaveButtonOfSettings();
        }

        //Работаем с настройками темы
        ThemeSettings_ProductLists themeSettingsProductLists = basicPage.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.tabProductLists.click();
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_EmptyStarsOfProductRating, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_CommonValueOfProductRating, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.compactList_productCode, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.compactList_availabilityStatus, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.compactList_quantityChanger, true);
        new Select(themeSettingsProductLists.compactList_buttonAddToCart).selectByValue("icon");
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_CompactList_Var1")
    public void checkProductLists_CompactList_Var1() {
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();
        stHomePage.navigateToHorizontalMenu_GameConsoles();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.selectProductListView(stCategoryPage.compactList_ProductListView);

        SoftAssert softAssert = new SoftAssert();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что модификатор количества присутствует
        softAssert.assertTrue(!assertsOnStorefront.quantityChanger_CompactList().isEmpty(),
                "There is no quantity changer on the category page 'Compact list'!");

        //Проверяем, что Быстрый просмотр присутствует
        softAssert.assertTrue(!assertsOnStorefront.enableQuickView.isEmpty(),
                "There is no button 'Quick view' on the category page 'Compact list'!");
        UtilsAdm.hoverOverElement(stCategoryPage.button_GeneralAddToCart);

        //Проверяем, что кнопка "Купить" присутствует
        softAssert.assertTrue(!assertsOnStorefront.compactList__ShowAddToCartButton_IconOnly().isEmpty(),
                "There is no button 'Add to cart' on the category page 'Compact list'!");

        takeScreenShot_withScroll("700 GS_ProductLists_CompactList_Var1");
        stCategoryPage.clickButtonQuickView();
        takeScreenShot_withScroll("705 GS_ProductLists_CompactList_Var1 - QuickView");
        UtilsAdm.hoverNavigateAndClick(stCategoryPage.closeQuickView);
        stHomePage.selectLanguage("ar");
        UtilsAdm.hoverOverElement(stCategoryPage.button_GeneralAddToCart);
        takeScreenShot_withScroll("710 GS_ProductLists_CompactList_Var1 (RTL)");
        stCategoryPage.clickButtonQuickView();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot_withScroll("715 GS_ProductLists_CompactList_Var1 - QuickView (RTL)");
        softAssert.assertAll();
        System.out.println("productLists.GeneralSettings_ProductLists_CompactList_Var1 passed successfully!");
    }
}