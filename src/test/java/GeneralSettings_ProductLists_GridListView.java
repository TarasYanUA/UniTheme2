import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import taras.DriverProvider;
import taras.workPages.AdminPanel;
import taras.workPages.MainPage;
import java.io.IOException;
import java.time.Duration;

public class GeneralSettings_ProductLists_GridListView extends TestRunner {
    @Test
    public void checkGridListView_BigProductCell() throws IOException {
        AdminPanel adminPanel = new AdminPanel();
        //Работаем с настройками темы
        adminPanel.navigateToAddonsPage();
        adminPanel.clickThemeSectionsOnManagementPage();
        adminPanel.navigateToThemeSettings();
        adminPanel.clickTabProductLists();
        adminPanel.clickAndTypeSettingMinHeightForProductCell("490");
        adminPanel.clickAndTypeSettingProductIconWidth("300");
        adminPanel.clickAndTypeSettingProductIconHeight("250");
        WebElement checkboxsettingShowProductCode = adminPanel.settingShowProductCode;
        if(!checkboxsettingShowProductCode.isSelected()){
            adminPanel.settingShowProductCode.click();
        }
        WebElement checkboxSettingDisplayAvailabilityStatus = adminPanel.settingDisplayAvailabilityStatus;
        if(!checkboxSettingDisplayAvailabilityStatus.isSelected()){
            adminPanel.settingDisplayAvailabilityStatus.click();
        }
        WebElement checkboxSettingShowQuantityChanger = adminPanel.settingShowQuantityChanger;
        if(!checkboxSettingShowQuantityChanger.isSelected()){
            adminPanel.settingShowQuantityChanger.click();
        }
        adminPanel.selectSettingShowAddToCartButton("icon_and_text");
        WebElement checkboxSettingShowBrandLogo = adminPanel.settingShowBrandLogo;
        if(!checkboxSettingShowBrandLogo.isSelected()){
            adminPanel.settingShowBrandLogo.click();
        }
        WebElement checkboxSettingShowYouSave = adminPanel.settingShowYouSave;
        if(!checkboxSettingShowYouSave.isSelected()){
            adminPanel.settingShowYouSave.click();
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
        takeScreenShot("310 GridListView_BlockWithProducts");
        mainPage.scrollToMenuApparel();
        mainPage.changeLanguageByIndex(1);
        makePause();
        mainPage.scrollToBlockWithProducts();
        takeScreenShot("311 GridListView_BlockWithProductsRTL");
        mainPage.changeLanguageByIndex(2);
        //Категория
    }
}