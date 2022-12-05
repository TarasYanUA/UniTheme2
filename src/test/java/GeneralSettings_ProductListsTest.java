import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import taras.DriverProvider;
import taras.workPages.AdminPanel;
import taras.workPages.StorefrontMainPage;

public class GeneralSettings_ProductListsTest extends TestRunner{
    @Test
    public void checkGeneralSettingsOfProductLists_DefaultValues() {
        AdminPanel adminPanel = new AdminPanel();
        adminPanel.navigateToAddonsPage(adminPanel);
        adminPanel.clickThemeSectionsOnManagementPage();
        adminPanel.navigateToThemeSettings();
        adminPanel.clickTabProductLists();
        //Работаем с настройками темы - Вариант "По умолчанию"
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
