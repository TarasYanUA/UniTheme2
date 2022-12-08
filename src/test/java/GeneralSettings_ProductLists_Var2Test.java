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

public class GeneralSettings_ProductLists_Var2Test extends TestRunner{
    @Test
    public void checkGeneralSettingsOfProductLists_ChangedValues() throws IOException {
        AdminPanel adminPanel = new AdminPanel();
        //Работаем с CS-Cart настройками
        adminPanel.navigateToAppearanceSettingsOfCsCart();
        WebElement checkboxSettingQuickView = DriverProvider.getDriver().findElement(By.cssSelector("input[id*='field___enable_quick_view']"));
        if(!checkboxSettingQuickView.isSelected()){
            checkboxSettingQuickView.click();
        }
        WebElement checkboxThumbnailsGallery = DriverProvider.getDriver().findElement(By.cssSelector("input[id*='field___thumbnails_gallery']"));
        if(!checkboxThumbnailsGallery.isSelected()){
            checkboxThumbnailsGallery.click();
        }
        adminPanel.clickSaveButtonOfSettings();
        //Работаем с настройками темы - Вариант с изменениями
        adminPanel.navigateToAddonsPage();
        adminPanel.clickThemeSectionsOnManagementPage();
        adminPanel.navigateToThemeSettings();
        adminPanel.clickTabProductLists();
        WebElement checkboxMiniIconsGallery = DriverProvider.getDriver().findElement(By.cssSelector("input[id='settings.abt__ut2.product_list.show_gallery']"));
        if(!checkboxMiniIconsGallery.isSelected()){
            checkboxMiniIconsGallery.click();
        }
        WebElement checkboxOutOfStickProducts = DriverProvider.getDriver().findElement(By.cssSelector("input[id=\"settings.abt__ut2.product_list.decolorate_out_of_stock_products\"]"));
        if (checkboxOutOfStickProducts.isSelected()){
            checkboxOutOfStickProducts.click();
        }
        adminPanel.selectSettingPriceDisplayFormat("col");
        WebElement checkboxPriceAtTheTop = DriverProvider.getDriver().findElement(By.cssSelector("input[id='settings.abt__ut2.product_list.price_position_top']"));
        if(!checkboxPriceAtTheTop.isSelected()){
            checkboxPriceAtTheTop.click();
        }
        WebElement checkboxProductRating = DriverProvider.getDriver().findElement(By.cssSelector("input[id='settings.abt__ut2.product_list.show_rating']"));
        if(checkboxProductRating.isSelected()){
            checkboxProductRating.click();
        }
        adminPanel.clickSaveButtonOfSettings();

        //Работаем с витриной
        adminPanel.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        MainPage mainPage = new MainPage();
        //Блок товаров на главной странице
        mainPage.scrollToBlockWithProducts();
        takeScreenShot("210 Var2_BlockWithProducts");
        mainPage.scrollToMenuApparel();
        mainPage.selectLanguageByIndex(1);
        makePause();
        mainPage.scrollToBlockWithProducts();
        takeScreenShot("211 Var2_BlockWithProductsRTL");
        mainPage.selectLanguageByIndex(2);
        //Категория "Женская одежда"
        mainPage.navigateToMenuWomanCloth();
        CategoryPage categoryPage = new CategoryPage();
        categoryPage.hoverToClothProduct();
        takeScreenShot("220 Var2_WomanClothCategory");
        mainPage.selectLanguageByIndex(1);
        makePause();
        categoryPage.hoverToClothProduct();
        takeScreenShot("221 Var2_WomanClothCategoryRTL");
        mainPage.selectLanguageByIndex(2);
        //Категория "Телефоны"
        mainPage.navigateToMenuPhones();
        categoryPage.hoverToPhoneProduct();
        takeScreenShot("230 Var2_PhonesCategory");
        mainPage.selectLanguageByIndex(1);
        makePause();
        categoryPage.hoverToPhoneProduct();
        takeScreenShot("231 Var2_PhonesCategoryRTL");
        mainPage.selectLanguageByIndex(2);
        //Быстрый просмотр в категории "Телефоны"
        categoryPage.hoverToPhoneProduct();
        categoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='ty-icon icon-right-open-thin ty-icon-right-open-thin']")));
        //Проверка, что присутствуют мини-иконки в виде галереи
        Assert.assertTrue(DriverProvider.getDriver().findElement(By.xpath("//span[@class='ty-icon icon-right-open-thin ty-icon-right-open-thin']")).isEnabled());
        takeScreenShot("240 Var2_QuickView");
        categoryPage.clickCloseQuickView();
        mainPage.selectLanguageByIndex(1);
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
        mainPage.selectLanguageByIndex(2);
        makePause();
        takeScreenShot("251 Var2_Category_ListWithoutOptions");
        categoryPage.clickCompactList_ProductListView();
        makePause();
        takeScreenShot("260 Var2_Category_CompactList_ProductListView");
        mainPage.selectLanguageByIndex(1);
        makePause();
        takeScreenShot("261 Var2_Category_CompactList_ProductListViewRTL");
    }
}
