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
        softAssert.assertTrue(!assertsOnStorefront.commonValueOfProductRating().isEmpty(),
                "There is no common value of product rating on the category page 'CompactList'!");

        //Проверяем, что код товара присутствует
        softAssert.assertTrue(!assertsOnStorefront.productCode_CompactList.isEmpty(),
                "There is no product code on the category page 'CompactList'!");

        //Проверяем, что статус товара присутствует
        softAssert.assertTrue(!assertsOnStorefront.availabilityStatus_CompactList.isEmpty(),
                "There is no availability status on the category page 'CompactList'!");

        //Проверяем, что модификатор количества отсутствует по причине отсутствия кнопки "Купить"
        softAssert.assertFalse(!assertsOnStorefront.quantityChanger_CompactList().isEmpty(),
                "There is a quantity charger but shouldn't on the category page 'CompactList'!");

        //Проверяем, что кнопка "Купить" отсутствует
        softAssert.assertFalse(!assertsOnStorefront.compactList__ShowAddToCartButton_IconOnly().isEmpty()
                && !assertsOnStorefront.compactList__ShowAddToCartButton_TextOnly().isEmpty(),
                "There is the button 'Add to cart' but shouldn't on the category page 'CompactList'!");

        takeScreenShot_withScroll("800 GS_ProductLists_CompactList_Var2");
        stHomePage.selectLanguage("ar");
        takeScreenShot_withScroll("805 GS_ProductLists_CompactLists_Var2 (RTL)");
        softAssert.assertAll();
        System.out.println("productLists.GeneralSettings_ProductLists_CompactList_Var2 passed successfully!");
    }
}