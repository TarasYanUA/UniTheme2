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
1) "Товары -- Характеристики -- Бренд":
- включаем "Показывать в списке товаров"

2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров" -- Настройки для вида списка товаров "Список без опций":
Ширина иконки товара    -- 400
Высота иконки товара    -- 300
Отображать код товара   -- да
Отображать статус наличия   -- да
Отображать модификатор количества   -- да
Содержимое под описанием    -- Список характеристик
Отображать опции товара -- да
Отображать логотип бренда   -- да
Переключать изображение товара при движении мышки   -- с полосками
*/

public class GeneralSettings_ProductLists_ListWithoutOptions_Var2 extends TestRunner {
    @Test
    public void checkGeneralSettings_ProductLists_ListWithoutOptions_Var2() throws IOException {
        CsCartSettings csCartSettings = new CsCartSettings();
        //Работаем с настройками характеристики Бренд
        csCartSettings.hoverToProductMenu();
        csCartSettings.navigateMenuFeatures();
        csCartSettings.clickFeatureBrand();
        WebElement checkboxShowInProductList = csCartSettings.showInProductList;
        if(!checkboxShowInProductList.isSelected()){
            csCartSettings.showInProductList.click();
        }
        csCartSettings.clickSaveButtonOfSettings();
        //Работаем с настройками темы
        csCartSettings.navigateToAddonsPage();
        csCartSettings.clickThemeSectionsOnManagementPage();
        ThemeSettings themeSettings = csCartSettings.navigateToThemeSettings();
        themeSettings.clickTabProductLists();
        themeSettings.clickAndTypeWithoutOptionsIconWidth("400");
        themeSettings.clickAndTypeWithoutOptionsIconHeight("200");
        WebElement checkboxProductCode = themeSettings.withoutOptionsProductCode;
        if(!checkboxProductCode.isSelected()){
            themeSettings.withoutOptionsProductCode.click();
        }
        WebElement checkboxAmountStatus = themeSettings.withoutOptionsAmountStatus;
        if(!checkboxAmountStatus.isSelected()){
            themeSettings.withoutOptionsAmountStatus.click();
        }
        WebElement checkboxShowQuantity = themeSettings.withoutOptionsShowQuantity;
        if(!checkboxShowQuantity.isSelected()){
            themeSettings.withoutOptionsShowQuantity.click();
        }
        themeSettings.selectWithoutOptionsContentUnderDescription("features");
        WebElement checkboxShowProductOptions = themeSettings.withoutOptionsShowProductOptions;
        if(!checkboxShowProductOptions.isSelected()){
            themeSettings.withoutOptionsShowProductOptions.click();
        }
        WebElement checkboxBrandLogo = themeSettings.settingShowBrandLogo;
        if(!checkboxBrandLogo.isSelected()){
            themeSettings.settingShowBrandLogo.click();
        }
        themeSettings.selectWithoutOptionsHoverGallery("lines");
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
        //Проверяем, что код товара присутствует
        int sizeOfProductCodes = DriverProvider.getDriver().findElements(By.cssSelector("span[id*='product_code']")).size();
        Assert.assertTrue(sizeOfProductCodes > 1, "There is no product code on the product cell!");
        //Проверяем, что статус наличия присутствует
        int sizeOfAvailabilityStatus = DriverProvider.getDriver().findElements(By.cssSelector(".ty-qty-in-stock.ty-control-group__item")).size();
        Assert.assertTrue(sizeOfAvailabilityStatus > 1, "There is no availability status on the product cell!");
        //Проверяем, что модификатор количества присутствует
        int sizeOfQuantityCharger = DriverProvider.getDriver().findElements(By.cssSelector("div[class*='ty-value-changer']")).size();
        Assert.assertTrue(sizeOfQuantityCharger > 1, "There is no quantity charger on the product cell!");
        //Проверяем, что содержимое под описанием это список характеристик
        int sizeOfContentUnderDescription = DriverProvider.getDriver().findElements(By.cssSelector(".ut2-pl__feature")).size();
        Assert.assertTrue(sizeOfContentUnderDescription > 1, "The content under description is not a feature list!");
        //Проверяем, что опции товара присутствуют
        int sizeOfProductOptions = DriverProvider.getDriver().findElements(By.cssSelector(".cm-picker-product-options")).size();
        Assert.assertTrue(sizeOfProductOptions > 1, "There is no product options on the product cell!");
        //Проверяем, что логотип бренда присутствует
        int sizeOfBrandLogo = DriverProvider.getDriver().findElements(By.cssSelector(".brand-img")).size();
        Assert.assertTrue(sizeOfBrandLogo > 1, "There is no product logo on the product cell!");
        //Проверяем, что переключатель изображений товара в виде полосок
        int sizeOfMousePointersAsPoints = DriverProvider.getDriver().findElements(By.cssSelector("div[class='cm-ab-hover-gallery abt__ut2_hover_gallery lines']")).size();
        Assert.assertTrue(sizeOfMousePointersAsPoints > 1, "Image switcher is not with lines!");
        takeScreenShot("610 ListWithoutOptions_MenClothCategory_Var2");
        stHomePage.changeLanguageByIndex(1);
        makePause();
        takeScreenShot("611 ListWithoutOptions_MenClothCategory_Var2(RTL)");
    }
}