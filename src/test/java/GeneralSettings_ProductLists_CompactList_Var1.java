import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ThemeSettings_ProductLists;
import taras.constants.DriverProvider;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import java.time.Duration;
import static taras.constants.DriverProvider.getDriver;

/*
1) "Настройки -- Внешний вид":
Включить быстрый просмотр -- да

2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Компактный список":
Отображать пустые звёзды рейтинга товара	-- нет
Отображать общее значение рейтинга товара	-- нет
Отображать код товара	-- нет
Отображать статус наличия	-- нет
Отображать модификатор количества	-- да
Отображать кнопку "Купить"	-- да

3) Проверка проходит на странице категории "Игровые приставки"
*/

public class GeneralSettings_ProductLists_CompactList_Var1 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductLists_CompactList_Var1() {
        //Работаем с настройками CS-Cart
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_DesignLayouts();
        csCartSettings.layout_Light.click();
        csCartSettings.setLayoutAsDefault();
        csCartSettings.navigateToAppearanceSettingsOfCsCart();
        WebElement checkboxSettingQuickView = csCartSettings.setting_QuickView;
        if (!checkboxSettingQuickView.isSelected()) {
            checkboxSettingQuickView.click();
        }
        csCartSettings.clickSaveButtonOfSettings();
        //Работаем с настройками темы
        ThemeSettings_ProductLists themeSettingsProductLists = csCartSettings.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.clickTabProductLists();
        WebElement checkboxProductRating = themeSettingsProductLists.settingProductRating;
        if (checkboxProductRating.isSelected()) {
            checkboxProductRating.click();
        }
        WebElement checkboxCommonValueOfProductRating = themeSettingsProductLists.settingCommonValueOfProductRating;
        if (checkboxCommonValueOfProductRating.isSelected()) {
            checkboxCommonValueOfProductRating.click();
        }
        WebElement checkboxProductCode = themeSettingsProductLists.compactList_productCode;
        if (checkboxProductCode.isSelected()) {
            checkboxProductCode.click();
        }
        WebElement checkboxAvailabilityStatus = themeSettingsProductLists.compactList_availabilityStatus;
        if (checkboxAvailabilityStatus.isSelected()) {
            checkboxAvailabilityStatus.click();
        }
        WebElement checkboxQuantityModifier = themeSettingsProductLists.compactList_quantityCharger;
        if (!checkboxQuantityModifier.isSelected()) {
            checkboxQuantityModifier.click();
        }
        WebElement checkboxButtonAddToCart = themeSettingsProductLists.compactList_buttonAddToCart;
        if (!checkboxButtonAddToCart.isSelected()) {
            checkboxButtonAddToCart.click();
        }
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_CompactList_Var1")
    public void checkProductLists_CompactList_Var1() {
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.cookie.click();
        stHomePage.navigateToVerticalMenu_GameConsoles();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.clickCompactList_ProductListView();
        SoftAssert softAssert = new SoftAssert();
        //Проверяем, что кнопка "Купить" присутствует
        softAssert.assertTrue(!getDriver().findElements(By.cssSelector("button[id*='button_cart']")).isEmpty(),
                "There is no button 'Add to cart'!");
        //Проверяем, что модификатор количества присутствует
        softAssert.assertTrue(!getDriver().findElements(By.cssSelector("div[class*='cm-value-changer']")).isEmpty(),
                "There is no quantity charger!");
        //Проверяем, что Быстрый просмотр присутствует
        softAssert.assertTrue(!getDriver().findElements(By.cssSelector("a[class*='ut2-quick-view-button']")).isEmpty(),
                "There is no button 'Quick view'!");
        stCategoryPage.hoverToButtonAddToCart();
        takeScreenShot_withScroll("700 GS_ProductLists_CompactList_Var1");
        stCategoryPage.clickButtonQuickView();
        takeScreenShot_withScroll("705 GS_ProductLists_CompactList_Var1 - QuickView");
        stCategoryPage.clickCloseQuickView();
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToButtonAddToCart();
        takeScreenShot_withScroll("710 GS_ProductLists_CompactList_Var1 (RTL)");
        stCategoryPage.clickButtonQuickView();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot_withScroll("715 GS_ProductLists_CompactList_Var1 - QuickView (RTL)");
        System.out.println("GeneralSettings_ProductLists_CompactList_Var1 passed successfully!");
        softAssert.assertAll();
    }
}