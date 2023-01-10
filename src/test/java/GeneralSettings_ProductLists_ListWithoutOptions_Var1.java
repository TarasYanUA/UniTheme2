/*
1) "Настройки -- Внешний вид":
- включаем Быстрый просмотр

2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Список без опций":
Отображать код товара   -- нет
Отображать статус наличия   -- нет
Отображать модификатор количества   -- да
Содержимое под описанием    -- Список вариаций
Отображать опции товара -- нет
Отображать логотип бренда   -- нет
Переключать изображение товара при движении мышки   -- с точками
*/

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

public class GeneralSettings_ProductLists_ListWithoutOptions_Var1 extends TestRunner {
    @Test
    public void checkGeneralSettings_ProductLists_ListWithoutOptions_Var1() throws IOException {
        CsCartSettings csCartSettings = new CsCartSettings();
        //Работаем с настройками темы
        csCartSettings.navigateToAddonsPage();
        csCartSettings.clickThemeSectionsOnManagementPage();
        ThemeSettings themeSettings = csCartSettings.navigateToThemeSettings();
        themeSettings.clickTabProductLists();
        WebElement checkboxProductCode = themeSettings.withoutOptionsProductCode;
        if(checkboxProductCode.isSelected()){
            themeSettings.withoutOptionsProductCode.click();
        }
        WebElement checkboxAmountStatus = themeSettings.withoutOptionsAmountStatus;
        if(checkboxAmountStatus.isSelected()){
            themeSettings.withoutOptionsAmountStatus.click();
        }
        WebElement checkboxShowQuantity = themeSettings.withoutOptionsShowQuantity;
        if(!checkboxShowQuantity.isSelected()){
            themeSettings.withoutOptionsShowQuantity.click();
        }
        themeSettings.selectWithoutOptionsContentUnderDescription("variations");
        WebElement checkboxShowProductOptions = themeSettings.withoutOptionsShowProductOptions;
        if(checkboxShowProductOptions.isSelected()){
            themeSettings.withoutOptionsShowProductOptions.click();
        }
        WebElement checkboxBrandLogo = themeSettings.settingShowBrandLogo;
        if(checkboxBrandLogo.isSelected()){
            themeSettings.settingShowBrandLogo.click();
        }
        themeSettings.selectWithoutOptionsHoverGallery("points");
        csCartSettings.clickSaveButtonOfSettings();

        //Работаем с витриной
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.cookie-notice")));
        stHomePage.closeCookieNoticeOnStorefront();
        stHomePage.navigateToMenuMenCloth();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.clickListWithoutOptions_ProductListView();
        makePause();
        //Проверяем, что модификатор количества присутствует
        int sizeOfQuantityCharger = DriverProvider.getDriver().findElements(By.cssSelector("div[class*='ty-value-changer']")).size();
        Assert.assertTrue(sizeOfQuantityCharger > 1, "There is no quantity charger on the product cell!");
        //Проверяем, что переключатель изображений товара в виде точек
        int sizeOfMousePointersAsPoints = DriverProvider.getDriver().findElements(By.cssSelector("div[class='cm-ab-hover-gallery abt__ut2_hover_gallery points']")).size();
        Assert.assertTrue(sizeOfMousePointersAsPoints > 1, "Image switcher is not with dots!");
        takeScreenShot("510 ListWithoutOptions_MenClothCategory");
        stHomePage.changeLanguageByIndex(1);
        makePause();
        takeScreenShot("511 ListWithoutOptions_MenClothCategory_RTL");
    }
}