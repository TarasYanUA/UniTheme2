import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.adminPanel.CsCartSettings;
import taras.adminPanel.ThemeSettings_ProductLists;
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

2) Проверка проходит на странице категории "Игровые приставки"
 */

public class GeneralSettings_ProductLists_CompactList_Var2 extends TestRunner {
    @Test
    public void checkGeneralSettings_ProductLists_CompactList_Var1() throws IOException {
        CsCartSettings csCartSettings = new CsCartSettings();
        //Работаем с настройками темы
        csCartSettings.navigateToAddonsPage();
        csCartSettings.clickThemeSectionsOnManagementPage();
        ThemeSettings_ProductLists themeSettingsProductLists = csCartSettings.navigateToThemeSettings();
        themeSettingsProductLists.clickTabProductLists();
        WebElement checkboxProductRating = themeSettingsProductLists.settingProductRating;
        if(!checkboxProductRating.isSelected()){
            themeSettingsProductLists.settingProductRating.click();
        }
        WebElement checkboxCommonValueOfProductRating = themeSettingsProductLists.settingCommonValueOfProductRating;
        if(!checkboxCommonValueOfProductRating.isSelected()){
            themeSettingsProductLists.settingCommonValueOfProductRating.click();
        }
        WebElement checkboxProductCode = themeSettingsProductLists.compactList_productCode;
        if(!checkboxProductCode.isSelected()){
            themeSettingsProductLists.compactList_productCode.click();
        }
        WebElement checkboxAvailabilityStatus = themeSettingsProductLists.compactList_availabilityStatus;
        if(!checkboxAvailabilityStatus.isSelected()){
            themeSettingsProductLists.compactList_availabilityStatus.click();
        }
        WebElement checkboxQuantityModifier = themeSettingsProductLists.compactList_quantityCharger;
        if(!checkboxQuantityModifier.isSelected()){
            themeSettingsProductLists.compactList_quantityCharger.click();
        }
        WebElement checkboxButtonAddToCart = themeSettingsProductLists.compactList_buttonAddToCart;
        if(!checkboxButtonAddToCart.isSelected()){
            themeSettingsProductLists.compactList_buttonAddToCart.click();
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
        stHomePage.selectLanguage_RTL();
        makePause();
        stCategoryPage.hoverToButtonAddToCart();
        takeScreenShot("820 ProductLists_CompactLists_Var2(RTL)");
    }
}