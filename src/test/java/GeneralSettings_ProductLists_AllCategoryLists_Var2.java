import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.adminPanel.ThemeSettings_ProductLists;
import taras.constants.DriverProvider;
import taras.adminPanel.CsCartSettings;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import java.io.IOException;
import java.time.Duration;

/*
Проверка следующих настроек:
1) "Настройки -- Внешний вид":
Включить быстрый просмотр -- y
Показывать мини-иконки в виде галереи -- y

2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Показывать галерею мини-иконок товара в товарном списке --	y
Переключать изображение товара при движении мышки -- Не переключать (нужно для настройки выше)
Обесцвечивать товары, которых нет в наличии --	n
Формат отображения цен --	Вариант 1
Отображать цену вверху --	y
Отображать пустые звёзды рейтинга товара --	n
Отображать общее значение рейтинга товара -- y
* Отображать статусы для кнопок "Купить" -- Количество товаров
* Отображать статусы для кнопок "Добавить в избранное", "Добавить в список сравнения" -- y
* Отображать кнопку "Добавить в избранное" -- y
* Отображать кнопку "Добавить в список сравнения" -- y
* Отображать кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" при наведении на ячейку товара -- n

Проверка проходит на следующих страницах:
- Блок товаров на Главной странице + RTL
- Женская одежда + RTL
- Телефоны + RTL
- Быстрый просмотр + RTL
- Все шаблоны категории + RTL
*/

public class GeneralSettings_ProductLists_AllCategoryLists_Var2 extends TestRunner{
    @Test
    public void checkGeneralSettingsOfProductLists_ChangedValues() throws IOException {
        CsCartSettings csCartSettings = new CsCartSettings();
        ThemeSettings_ProductLists themeSettingsProductLists = new ThemeSettings_ProductLists();
        //Работаем с CS-Cart настройками
        csCartSettings.navigateToAppearanceSettingsOfCsCart();
        WebElement checkboxThumbnailsGallery = csCartSettings.settingThumbnailsGallery;
        if(!checkboxThumbnailsGallery.isSelected()){
            csCartSettings.settingThumbnailsGallery.click();
        }
        WebElement checkboxSettingQuickView = csCartSettings.settingQuickView;
        if(!checkboxSettingQuickView.isSelected()){
            csCartSettings.settingQuickView.click();
        }
        csCartSettings.clickSaveButtonOfSettings();
        //Работаем с настройками темы
        csCartSettings.navigateToAddonsPage();
        csCartSettings.clickThemeSectionsOnManagementPage();
        csCartSettings.navigateToThemeSettings();
        themeSettingsProductLists.clickTabProductLists();
        WebElement checkboxMiniIconsGalleryOne = themeSettingsProductLists.settingMiniIconsGallery;
        if(!checkboxMiniIconsGalleryOne.isSelected()){
            themeSettingsProductLists.settingMiniIconsGallery.click();
        }
        WebElement checkboxOutOfStockProducts = themeSettingsProductLists.settingOutOfStockProducts;
        if (checkboxOutOfStockProducts.isSelected()){
            themeSettingsProductLists.settingOutOfStockProducts.click();
        }
        themeSettingsProductLists.selectSettingSwitchProductImageWhenHoveringMousePointer("N");
        themeSettingsProductLists.selectSettingPriceDisplayFormat("col");
        WebElement checkboxPriceAtTheTop = themeSettingsProductLists.settingPriceAtTheTop;
        if(!checkboxPriceAtTheTop.isSelected()){
            themeSettingsProductLists.settingPriceAtTheTop.click();
        }
        WebElement checkboxProductRating = themeSettingsProductLists.settingProductRating;
        if(checkboxProductRating.isSelected()){
            themeSettingsProductLists.settingProductRating.click();
        }
        WebElement checkboxSettingCommonValueOfProductRating = themeSettingsProductLists.settingCommonValueOfProductRating;
        if(!checkboxSettingCommonValueOfProductRating.isSelected()){
            themeSettingsProductLists.settingCommonValueOfProductRating.click();
        }
        csCartSettings.clickSaveButtonOfSettings();

        //Работаем с витриной
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.cookie-notice")));
        stHomePage.closeCookieNoticeOnStorefront();
        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("210 Var2_BlockWithProducts");
        stHomePage.changeLanguageByIndex(2);
        makePause();
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("211 Var2_BlockWithProductsRTL");
        stHomePage.changeLanguageByIndex(1);
        //Категория "Женская одежда"
        stHomePage.navigateToMenuWomanCloth();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.hoverToClothProduct();
        takeScreenShot("220 Var2_WomanClothCategory");
        stHomePage.changeLanguageByIndex(2);
        makePause();
        stCategoryPage.hoverToClothProduct();
        takeScreenShot("221 Var2_WomanClothCategoryRTL");
        stHomePage.changeLanguageByIndex(1);
        //Категория "Телефоны"
        stHomePage.navigateToMenuPhones();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("230 Var2_PhonesCategory");
        stHomePage.changeLanguageByIndex(2);
        makePause();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("231 Var2_PhonesCategoryRTL");
        stHomePage.changeLanguageByIndex(1);
        //Быстрый просмотр в категории "Телефоны"
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-icon-right-open-thin")));
        //Проверка, что присутствуют мини-иконки в виде галереи
        Assert.assertTrue(DriverProvider.getDriver().findElement(By.cssSelector(".ty-icon-right-open-thin")).isEnabled(),
                "Mini icons are not as a gallery!");
        takeScreenShot("240 Var2_QuickView");
        stCategoryPage.clickCloseQuickView();
        stHomePage.changeLanguageByIndex(2);
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.ty-icon-right-open-thin")));
        takeScreenShot("241 Var2_QuickViewRTL");
        stCategoryPage.clickCloseQuickView();
        //Других два шаблона страницы категории
        stCategoryPage.clickListWithoutOptions_ProductListView();
        makePause();
        takeScreenShot("250 Var2_Category_ListWithoutOptionsRTL");
        stHomePage.changeLanguageByIndex(1);
        makePause();
        takeScreenShot("251 Var2_Category_ListWithoutOptions");
        stCategoryPage.clickCompactList_ProductListView();
        makePause();
        takeScreenShot("260 Var2_Category_CompactList_ProductListView");
        stHomePage.changeLanguageByIndex(2);
        makePause();
        takeScreenShot("261 Var2_Category_CompactList_ProductListViewRTL");
    }
}
