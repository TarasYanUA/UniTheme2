import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ThemeSettings;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import java.io.IOException;
import java.time.Duration;
import static taras.constants.DriverProvider.getDriver;

/*
1) UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Компактный список":
Отображать пустые звёзды рейтинга товара	-- да
Отображать общее значение рейтинга товара	-- да
Отображать код товара	-- да
Отображать статус наличия	-- да
Отображать модификатор количества	-- да
Отображать кнопку "Купить"	-- да
Отображать кнопку "Быстрый просмотр"	-- нет
Отображать кнопку "Добавить в избранное"	-- нет
Отображать кнопку "Добавить к сравнению"	-- нет

2) Проверка проходит на странице категории "Игровые приставки"
 */

public class GeneralSettings_ProductLists_CompactList_Var2 extends TestRunner {
    @Test
    public void checkGeneralSettings_ProductLists_CompactList_Var1() throws IOException {
        CsCartSettings csCartSettings = new CsCartSettings();
        //Работаем с настройками темы
        csCartSettings.navigateToAddonsPage();
        csCartSettings.clickThemeSectionsOnManagementPage();
        ThemeSettings themeSettings = csCartSettings.navigateToThemeSettings();
        themeSettings.clickTabProductLists();
        WebElement checkboxProductRating = themeSettings.settingProductRating;
        if(!checkboxProductRating.isSelected()){
            themeSettings.settingProductRating.click();
        }
        WebElement checkboxCommonValueOfProductRating = themeSettings.settingCommonValueOfProductRating;
        if(!checkboxCommonValueOfProductRating.isSelected()){
            themeSettings.settingCommonValueOfProductRating.click();
        }
        WebElement checkboxProductCode = themeSettings.compactList_productCode;
        if(!checkboxProductCode.isSelected()){
            themeSettings.compactList_productCode.click();
        }
        WebElement checkboxAvailabilityStatus = themeSettings.compactList_availabilityStatus;
        if(!checkboxAvailabilityStatus.isSelected()){
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
        //Проверяем, что пустые звезды рейтинга присутствуют
        Assert.assertTrue(stCategoryPage.emptyRatingStars.isEnabled(), "There is no empty rating stars!");
        //Проверяем, что общее значение рейтинга присутствует
        Assert.assertTrue(stCategoryPage.commonValueOfProductRating.isEnabled(), "There is no common value of product rating!");
        //Проверяем, что код товара присутствует
        Assert.assertTrue(stCategoryPage.productCode.isEnabled(), "There is no product code!");
        //Проверяем, что статус товара присутствует
        Assert.assertTrue(stCategoryPage.availabilityStatus.isEnabled(), "There is no availability status!");
        //Проверяем, что кнопка "Купить" присутствует
        Assert.assertTrue(stCategoryPage.buttonAddToCart.isEnabled(), "There is no button 'Add to cart'!");
        //Проверяем, что модификатор количества присутствует
        Assert.assertTrue(stCategoryPage.quantityCharger.isEnabled(), "There is no quantity charger!");
        stCategoryPage.hoverToButtonAddToCart();
        takeScreenShot("810 ProductLists_CompactLists_Var2");
        stHomePage.changeLanguageByIndex(1);
        makePause();
        stCategoryPage.hoverToButtonAddToCart();
        takeScreenShot("820 ProductLists_CompactLists_Var2(RTL)");
    }
}