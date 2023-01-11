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
Включить быстрый просмотр -- y

2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Компактный список":
Отображать пустые звёзды рейтинга товара	-- нет
Отображать общее значение рейтинга товара	-- нет
Отображать код товара	-- нет
Отображать статус наличия	-- нет
Отображать модификатор количества	-- да
Отображать кнопку "Купить"	-- да
Отображать кнопку "Быстрый просмотр"	-- да
Отображать кнопку "Добавить в избранное"	-- да
Отображать кнопку "Добавить к сравнению"	-- да
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
        WebElement checkboxQuickView = themeSettings.compactList_quickView;
        if(!checkboxQuickView.isSelected()){
            themeSettings.compactList_quickView.click();
        }
        WebElement checkboxWishList = themeSettings.compactList_buttonWishList;
        if(!checkboxWishList.isSelected()){
            themeSettings.compactList_buttonWishList.click();
        }
        WebElement checkboxComparisonList = themeSettings.compactList_buttonComparisonList;
        if(!checkboxComparisonList.isSelected()){
            themeSettings.compactList_buttonComparisonList.click();
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
        int sizeOfButtonCart = DriverProvider.getDriver().findElements(By.cssSelector("button[id*='button_cart']")).size();
        Assert.assertTrue(sizeOfButtonCart > 1, "There is no button 'Add to cart'!");
        //Проверяем, что модификатор количества присутствует
        int sizeOfQuantityCharger = DriverProvider.getDriver().findElements(By.cssSelector("div[class*='cm-value-changer']")).size();
        Assert.assertTrue(sizeOfQuantityCharger > 1, "There is no quantity charger!");
        //Проверяем, что Быстрый просмотр присутствует
        int sizeOfQuickView = DriverProvider.getDriver().findElements(By.cssSelector("a[class*='ut2-quick-view-button']")).size();
        Assert.assertTrue(sizeOfQuickView > 1, "There is no button 'Quick view'!");
        //Проверяем, что кнопка "Избранное" присутствует
        int sizeOfWishList = DriverProvider.getDriver().findElements(By.cssSelector("div.ut2-cl-bt .ut2-icon-baseline-favorite-border")).size();
        Assert.assertTrue(sizeOfWishList > 1, "There is no button 'Wish list'!");
        //Проверяем, что кнопка "Сравнение" присутствует
        int sizeOfComparisonList = DriverProvider.getDriver().findElements(By.cssSelector(".ut2-icon-addchart")).size();
        Assert.assertTrue(sizeOfComparisonList > 1, "There is no button 'Comparison list'!");
        stCategoryPage.hoverToButtonAddToCart();
        takeScreenShot("710 ProductLists_CompactLists_Var1");
        stCategoryPage.clickCompactList_buttonQuickView();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("720 ProductLists_CompactLists_QuickView");
        stCategoryPage.clickCloseQuickView();
        stHomePage.changeLanguageByIndex(1);
        makePause();
        stCategoryPage.hoverToButtonAddToCart();
        takeScreenShot("720 ProductLists_CompactLists_Var1(RTL)");
        stCategoryPage.clickCompactList_buttonQuickView();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("721 ProductLists_CompactLists_QuickView(RTL)");
    }
}