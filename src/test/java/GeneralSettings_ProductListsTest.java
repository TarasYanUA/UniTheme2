import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import taras.DriverProvider;
import taras.workPages.AdminPanel;
import taras.workPages.CategoryPage;
import taras.workPages.MainPage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class GeneralSettings_ProductListsTest extends TestRunner {
    @Test
    public void checkGeneralSettingsOfProductLists_DefaultValues() {
        AdminPanel adminPanel = new AdminPanel();
        //Работаем с CS-Cart настройками
/*        adminPanel.navigateToAppearanceSettingsOfCsCart();
        WebElement checkboxSettingQuickView = DriverProvider.getDriver().findElement(By.cssSelector("input[id*='field___enable_quick_view']"));
        if(!checkboxSettingQuickView.isSelected()){
            checkboxSettingQuickView.click();
        }
        WebElement checkboxThumbnailsGallery = DriverProvider.getDriver().findElement(By.cssSelector("input[id*='field___thumbnails_gallery']"));
        if(checkboxThumbnailsGallery.isSelected()){
            checkboxThumbnailsGallery.click();
        }
        adminPanel.clickSaveButtonOfSettings();
        //Работаем с настройками темы - Вариант "По умолчанию"
        adminPanel.navigateToAddonsPage(adminPanel);
        adminPanel.clickThemeSectionsOnManagementPage();
        adminPanel.navigateToThemeSettings();
        adminPanel.clickTabProductLists();
        WebElement checkboxMiniIconsGallery = DriverProvider.getDriver().findElement(By.cssSelector("input[id='settings.abt__ut2.product_list.show_gallery']"));
        if(checkboxMiniIconsGallery.isSelected()){
            checkboxMiniIconsGallery.click();
        }
        WebElement checkboxOutOfStickProducts = DriverProvider.getDriver().findElement(By.cssSelector("input[id=\"settings.abt__ut2.product_list.decolorate_out_of_stock_products\"]"));
        if (!checkboxOutOfStickProducts.isSelected()){
            checkboxOutOfStickProducts.click();
        }
        adminPanel.selectSettingPriceDisplayFormat("row-mix");
        WebElement checkboxPriceAtTheTop = DriverProvider.getDriver().findElement(By.cssSelector("input[id='settings.abt__ut2.product_list.price_position_top']"));
        if(checkboxPriceAtTheTop.isSelected()){
            checkboxPriceAtTheTop.click();
        }
        WebElement checkboxProductRating = DriverProvider.getDriver().findElement(By.cssSelector("input[id='settings.abt__ut2.product_list.show_rating']"));
        if(!checkboxProductRating.isSelected()){
            checkboxProductRating.click();
        }
        adminPanel.clickSaveButtonOfSettings();
 */
        //Работаем с витриной
        MainPage mainPage = adminPanel.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        //Категория "Женская одежда"
        mainPage.navigateToMenuWomanCloth();
        CategoryPage categoryPage = new CategoryPage();
        categoryPage.hoverToClothProduct();
        try {
            takeScreenShot("110 Var1_WomanClothCategory");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainPage.selectLanguageByIndex(1);
        makePause();
        categoryPage.hoverToClothProduct();
        try {
            takeScreenShot("111 Var1_WomanClothCategoryRTL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainPage.selectLanguageByIndex(2);
        //Категория "Телефоны"
        mainPage.navigateToMenuPhones();
        categoryPage.hoverToPhoneProduct();
        try {
            takeScreenShot("120 Var1_PhonesCategory");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainPage.selectLanguageByIndex(1);
        makePause();
        categoryPage.hoverToPhoneProduct();
        try {
            takeScreenShot("121 Var1_PhonesCategoryRTL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainPage.selectLanguageByIndex(2);
        //Быстрый просмотр в категории "Телефоны"
        categoryPage.hoverToPhoneProduct();
        categoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        try {
            takeScreenShot("130 Var1_QuickView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        categoryPage.clickCloseQuickView();
        mainPage.selectLanguageByIndex(1);
        categoryPage.hoverToPhoneProduct();
        categoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
        try {
            takeScreenShot("131 Var1_QuickViewRTL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        categoryPage.clickCloseQuickView();

        //Других два шаблона страницы категории
        categoryPage.clickListWithoutOptions_ProductListView();
        makePause();
        try {
            takeScreenShot("140 Var1_Category_ListWithoutOptionsRTL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainPage.selectLanguageByIndex(2);
        makePause();
        try {
            takeScreenShot("141 Var1_Category_ListWithoutOptions");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        categoryPage.clickCompactList_ProductListView();
        makePause();
        try {
            takeScreenShot("150 Var1_Category_CompactList_ProductListView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainPage.selectLanguageByIndex(1);
        makePause();
        try {
            takeScreenShot("151 Var1_Category_CompactList_ProductListViewRTL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
