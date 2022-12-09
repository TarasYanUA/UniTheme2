import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.DriverProvider;
import taras.workPages.AdminPanel;
import taras.workPages.CategoryPage;
import taras.workPages.MainPage;
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
Отображать рейтинг товара --	n

Проверка проходит на следующих страницах:
Блок товаров на Главной странице + RTL
Женская одежда + RTL
Телефоны + RTL
Быстрый просмотр + RTL
Все шаблоны категории + RTL
*/

public class GeneralSettings_ProductLists_Var2 extends TestRunner{
    @Test
    public void checkGeneralSettingsOfProductLists_ChangedValues() throws IOException {
        AdminPanel adminPanel = new AdminPanel();
        //Работаем с CS-Cart настройками
        adminPanel.navigateToAppearanceSettingsOfCsCart();
        WebElement checkboxSettingQuickView = adminPanel.settingQuickView;
        if(!checkboxSettingQuickView.isSelected()){
            checkboxSettingQuickView.click();
        }
        WebElement checkboxThumbnailsGallery = adminPanel.settingThumbnailsGallery;
        if(!checkboxThumbnailsGallery.isSelected()){
            checkboxThumbnailsGallery.click();
        }
        adminPanel.clickSaveButtonOfSettings();
        //Работаем с настройками темы
        adminPanel.navigateToAddonsPage();
        adminPanel.clickThemeSectionsOnManagementPage();
        adminPanel.navigateToThemeSettings();
        adminPanel.clickTabProductLists();
        WebElement checkboxMiniIconsGalleryOne = adminPanel.settingMiniIconsGallery;
        if(!checkboxMiniIconsGalleryOne.isSelected()){
            checkboxMiniIconsGalleryOne.click();
        }
        adminPanel.selectSettingSwitchProductImageWhenHoveringMousePointer("N");
        WebElement checkboxOutOfStockProducts = adminPanel.settingOutOfStockProducts;
        if (checkboxOutOfStockProducts.isSelected()){
            checkboxOutOfStockProducts.click();
        }
        adminPanel.selectSettingPriceDisplayFormat("col");
        WebElement checkboxPriceAtTheTop = adminPanel.settingPriceAtTheTop;
        if(!checkboxPriceAtTheTop.isSelected()){
            checkboxPriceAtTheTop.click();
        }
        WebElement checkboxProductRating = adminPanel.settingProductRating;
        if(checkboxProductRating.isSelected()){
            checkboxProductRating.click();
        }
        adminPanel.clickSaveButtonOfSettings();

        //Работаем с витриной
        MainPage mainPage = adminPanel.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className(".cookie-notice")));
        mainPage.closeCookieNoticeOnStorefront();
        //Блок товаров на главной странице
        mainPage.scrollToBlockWithProducts();
        takeScreenShot("210 Var2_BlockWithProducts");
        mainPage.scrollToMenuApparel();
        mainPage.changeLanguageByIndex(1);
        makePause();
        mainPage.scrollToBlockWithProducts();
        takeScreenShot("211 Var2_BlockWithProductsRTL");
        mainPage.changeLanguageByIndex(2);
        //Категория "Женская одежда"
        mainPage.navigateToMenuWomanCloth();
        CategoryPage categoryPage = new CategoryPage();
        categoryPage.hoverToClothProduct();
        takeScreenShot("220 Var2_WomanClothCategory");
        mainPage.changeLanguageByIndex(1);
        makePause();
        categoryPage.hoverToClothProduct();
        takeScreenShot("221 Var2_WomanClothCategoryRTL");
        mainPage.changeLanguageByIndex(2);
        //Категория "Телефоны"
        mainPage.navigateToMenuPhones();
        categoryPage.hoverToPhoneProduct();
        takeScreenShot("230 Var2_PhonesCategory");
        mainPage.changeLanguageByIndex(1);
        makePause();
        categoryPage.hoverToPhoneProduct();
        takeScreenShot("231 Var2_PhonesCategoryRTL");
        mainPage.changeLanguageByIndex(2);
        //Быстрый просмотр в категории "Телефоны"
        categoryPage.hoverToPhoneProduct();
        categoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='ty-icon icon-right-open-thin ty-icon-right-open-thin']")));
        //Проверка, что присутствуют мини-иконки в виде галереи
        Assert.assertTrue(DriverProvider.getDriver().findElement(By.xpath("//span[@class='ty-icon icon-right-open-thin ty-icon-right-open-thin']")).isEnabled());
        takeScreenShot("240 Var2_QuickView");
        categoryPage.clickCloseQuickView();
        mainPage.changeLanguageByIndex(1);
        categoryPage.hoverToPhoneProduct();
        categoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='ty-icon icon-right-open-thin ty-icon-right-open-thin']")));
        takeScreenShot("241 Var2_QuickViewRTL");
        categoryPage.clickCloseQuickView();
        //Других два шаблона страницы категории
        categoryPage.clickListWithoutOptions_ProductListView();
        makePause();
        takeScreenShot("250 Var2_Category_ListWithoutOptionsRTL");
        mainPage.changeLanguageByIndex(2);
        makePause();
        takeScreenShot("251 Var2_Category_ListWithoutOptions");
        categoryPage.clickCompactList_ProductListView();
        makePause();
        takeScreenShot("260 Var2_Category_CompactList_ProductListView");
        mainPage.changeLanguageByIndex(1);
        makePause();
        takeScreenShot("261 Var2_Category_CompactList_ProductListViewRTL");
    }
}
