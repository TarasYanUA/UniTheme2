package productLists;

import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.BasicPage;
import taras.adminPanel.ThemeSettings_ProductLists;
import taras.adminPanel.UtilsAdm;
import taras.asserts.Asserts_ThemeSettings_ProductLists;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

/*
1) UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Компактный список":
Отображать пустые звёзды рейтинга товара	-- да
Отображать общее значение рейтинга товара	-- да
Отображать код товара	    -- да
Отображать статус наличия	-- да
Отображать модификатор количества	-- да (но на витрине не будет по причине настройки ниже)
Отображать кнопку "Купить"	-- Не отображать

2) Проверка проходит на странице категории "Игровые приставки"
 */

public class GeneralSettings_ProductLists_CompactList_Var2 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductLists_CompactList_Var2() {
        //Работаем с настройками темы
        BasicPage basicPage = new BasicPage();
        ThemeSettings_ProductLists themeSettingsProductLists = basicPage.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.tabProductLists.click();
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_EmptyStarsOfProductRating, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_CommonValueOfProductRating, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.compactList_productCode, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.compactList_availabilityStatus, true);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.compactList_quantityChanger, true);
        new Select(themeSettingsProductLists.compactList_buttonAddToCart).selectByValue("none");
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_CompactList_Var2")
    public void checkProductLists_CompactList_Var2() {
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();
        stHomePage.navigateToHorizontalMenu_GameConsoles();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.selectProductListView(stCategoryPage.compactList_ProductListView);

        SoftAssert softAssert = new SoftAssert();
        Asserts_ThemeSettings_ProductLists asserts_productLists = new Asserts_ThemeSettings_ProductLists();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что пустые звезды рейтинга присутствуют
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.compactList,
                asserts_productLists.emptyStarsOfProductRating,
                "on the category 'Compact list'!", true);

        //Проверяем, что общее значение рейтинга присутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.compactList,
                asserts_productLists.commonValueOfProductRating,
                "on the category 'Compact list'!",
                true);

        //Проверяем, что код товара присутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.compactList,
                asserts_productLists.productCode,
                "on the category page 'Compact list'!",
                true);

        //Проверяем, что статус товара присутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.compactList,
                asserts_productLists.availabilityStatus,
                "on the category page 'Compact list'!",
                true);

        //Проверяем, что модификатор количества отсутствует по причине отсутствия кнопки "Купить"
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.compactList,
                asserts_productLists.quantityChanger,
                "on the category page 'Compact list'!",
                false);

        //Проверяем, что кнопка "Купить" отсутствует
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.compactList,
                asserts_productLists.showAddToCartButton_IconOnly,
                "on the category page 'Compact list'!",
                false);
        asserts_productLists.assertElementPresence(
                Asserts_ThemeSettings_ProductLists.compactList,
                asserts_productLists.showAddToCartButton_TextOnly,
                "on the category page 'Compact list'!",
                false);

        takeScreenShot_withScroll("800 GS_ProductLists_CompactList_Var2");
        stHomePage.selectLanguage("ar");
        takeScreenShot_withScroll("805 GS_ProductLists_CompactLists_Var2 (RTL)");
        softAssert.assertAll();
        System.out.println("productLists.GeneralSettings_ProductLists_CompactList_Var2 passed successfully!");
    }
}