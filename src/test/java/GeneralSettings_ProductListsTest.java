import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import taras.DriverProvider;
import taras.workPages.AdminPanel;
import taras.workPages.StorefrontMainPage;

import java.sql.Driver;

public class GeneralSettings_ProductListsTest extends TestRunner{
    @Test
    public void checkGeneralSettingsOfProductLists_DefaultValues() {
        AdminPanel adminPanel = new AdminPanel();
        //Работаем с CS-Cart настройками
        adminPanel.navigateToAppearanceSettingsOfCsCart();
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
        //Работаем с витриной
        StorefrontMainPage storefrontMainPage = adminPanel.navigateToStorefrontMainPage();
        focusBrowserTab(1);
    }
}
