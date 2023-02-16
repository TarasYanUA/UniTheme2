import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ThemeSettings;
import taras.constants.DriverProvider;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import java.io.IOException;
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
    @Test
    public void checkGeneralSettings_ProductLists_CompactList_Var1() throws IOException {
        CsCartSettings csCartSettings = new CsCartSettings();
        //Работаем с настройками CS-Cart
        csCartSettings.navigateToAppearanceSettingsOfCsCart();
        WebElement checkboxSettingQuickView = csCartSettings.settingQuickView;
        if(!checkboxSettingQuickView.isSelected()){
            csCartSettings.settingQuickView.click();
        }
        csCartSettings.clickSaveButtonOfSettings();
        //Работаем с настройками темы
        csCartSettings.navigateToAddonsPage();
        csCartSettings.clickThemeSectionsOnManagementPage();
        ThemeSettings themeSettings = csCartSettings.navigateToThemeSettings();
        themeSettings.clickTabProductLists();
        WebElement checkboxProductRating = themeSettings.settingProductRating;
        if(checkboxProductRating.isSelected()){
            themeSettings.settingProductRating.click();
        }
        WebElement checkboxCommonValueOfProductRating = themeSettings.settingCommonValueOfProductRating;
        if(checkboxCommonValueOfProductRating.isSelected()){
            themeSettings.settingCommonValueOfProductRating.click();
        }
        WebElement checkboxProductCode = themeSettings.compactList_productCode;
        if(checkboxProductCode.isSelected()){
            themeSettings.compactList_productCode.click();
        }
        WebElement checkboxAvailabilityStatus = themeSettings.compactList_availabilityStatus;
        if(checkboxAvailabilityStatus.isSelected()){
            themeSettings.compactList_availabilityStatus.click();
        }
        WebElement checkboxQuantityModifier = themeSettings.compactList_quantityCharger;
        if(!checkboxQuantityModifier.isSelected()){
            themeSettings.compactList_quantityCharger.click();
        }
        WebElement checkboxButtonAddToCart = themeSettings.compactList_buttonAddToCart;
        if(!checkboxButtonAddToCart.isSelected()){
            themeSettings.compactList_buttonAddToCart.click();
        }
        csCartSettings.clickSaveButtonOfSettings();

        //Работаем с витриной
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.cookie-notice")));
        stHomePage.closeCookieNoticeOnStorefront();
        stHomePage.navigateToMenuGameConsoles();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.clickCompactList_ProductListView();
        makePause();
        //Проверяем, что кнопка "Купить" присутствует
        Assert.assertTrue(stCategoryPage.buttonAddToCart.isEnabled(), "There is no button 'Add to cart'!");
        //Проверяем, что модификатор количества присутствует
        Assert.assertTrue(stCategoryPage.quantityCharger.isEnabled(), "There is no quantity charger!");
        //Проверяем, что Быстрый просмотр присутствует
        Assert.assertTrue(stCategoryPage.buttonQuickView.isEnabled(), "There is no button 'Quick view'!");
        stCategoryPage.hoverToButtonAddToCart();
        takeScreenShot("710 ProductLists_CompactLists_Var1");
        stCategoryPage.clickButtonQuickView();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("720 ProductLists_CompactLists_QuickView");
        stCategoryPage.clickCloseQuickView();
        stHomePage.changeLanguageByIndex(1);
        makePause();
        stCategoryPage.hoverToButtonAddToCart();
        takeScreenShot("730 ProductLists_CompactLists_Var1(RTL)");
        stCategoryPage.clickButtonQuickView();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("740 ProductLists_CompactLists_QuickView(RTL)");
    }
}