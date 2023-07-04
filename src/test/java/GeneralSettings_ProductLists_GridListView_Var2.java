import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.ThemeSettings_ProductLists;
import taras.constants.DriverProvider;
import taras.adminPanel.CsCartSettings;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import java.time.Duration;

/*
Проверка настроек UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Отображать пустые звёзды рейтинга товара -- нет
Отображать общее значение рейтинга товара -- да

Ширина иконки товара (по умолчанию 240) --	400
Высота иконки товара (по умолчанию 290) --	380

Отображать код товара -- нет
Отображать статус наличия -- нет
Отображать модификатор количества -- нет
Отображать кнопку "Купить" -- Только Иконка корзины (упрощенный вариант)
Дополнительная информация о товаре -- Список характеристик и вариаций
-- Переключать изображение товара при движении мышки -- Не переключать (нужно для настройки выше)
Отображать дополнительную информацию при наведении -- да
Отображать логотип бренда -- да
Отображать "Вы экономите" -- да
Показывать галерею мини-иконок товара в товарном списке --  с точками
Переключать изображение товара при движении мышки -- Не переключать
*/

public class GeneralSettings_ProductLists_GridListView_Var2 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductLists_GridListView_Var2() {
        CsCartSettings csCartSettings = new CsCartSettings();
        ThemeSettings_ProductLists themeSettingsProductLists = new ThemeSettings_ProductLists();
        //Работаем с настройками характеристики Бренд
        csCartSettings.hoverToProductMenu();
        csCartSettings.navigateToSection_Features();
        csCartSettings.clickFeatureBrand();
        WebElement checkboxShowInProductList = csCartSettings.showInProductList;
        if (!checkboxShowInProductList.isSelected()) {
            checkboxShowInProductList.click();
        }
        csCartSettings.clickSaveButtonOfSettings();

        //Работаем с настройками темы
        csCartSettings.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.clickTabProductLists();
        WebElement checkboxProductRating = themeSettingsProductLists.settingProductRating;
        if (checkboxProductRating.isSelected()) {
            checkboxProductRating.click();
        }
        WebElement checkboxSettingCommonValueOfProductRating = themeSettingsProductLists.settingCommonValueOfProductRating;
        if (!checkboxSettingCommonValueOfProductRating.isSelected()) {
            checkboxSettingCommonValueOfProductRating.click();
        }
        themeSettingsProductLists.clickAndTypeSettingProductIconWidth("400");
        themeSettingsProductLists.clickAndTypeSettingProductIconHeight("380");
        WebElement checkboxSettingShowProductCode = themeSettingsProductLists.settingShowProductCode;
        if (checkboxSettingShowProductCode.isSelected()) {
            checkboxSettingShowProductCode.click();
        }
        WebElement checkboxSettingDisplayAvailabilityStatus = themeSettingsProductLists.settingDisplayAvailabilityStatus;
        if (checkboxSettingDisplayAvailabilityStatus.isSelected()) {
            checkboxSettingDisplayAvailabilityStatus.click();
        }
        WebElement checkboxSettingShowQuantityChanger = themeSettingsProductLists.settingShowQuantityChanger;
        if (checkboxSettingShowQuantityChanger.isSelected()) {
            checkboxSettingShowQuantityChanger.click();
        }
        themeSettingsProductLists.selectSettingShowAddToCartButton("icon");
        themeSettingsProductLists.selectSettingAdditionalProductInformation("features_and_variations");
        WebElement checkboxSettingShowAdditionalInformationOnHover = themeSettingsProductLists.settingShowAdditionalInformationOnHover;
        if (!checkboxSettingShowAdditionalInformationOnHover.isSelected()) {
            checkboxSettingShowAdditionalInformationOnHover.click();
        }
        WebElement checkboxSettingShowBrandLogo = themeSettingsProductLists.settingShowBrandLogo;
        if (!checkboxSettingShowBrandLogo.isSelected()) {
            checkboxSettingShowBrandLogo.click();
        }
        WebElement checkboxSettingShowYouSave = themeSettingsProductLists.settingShowYouSave;
        if (!checkboxSettingShowYouSave.isSelected()) {
            checkboxSettingShowYouSave.click();
        }
        themeSettingsProductLists.selectSetting_ShowGalleryOfMiniIcons("points");
        themeSettingsProductLists.selectSetting_SwitchProductImageWhenHovering("N");
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_GridListView_Var2")
    public void checkProductLists_GridListView_Var2() {
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.cookie.click();
        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        SoftAssert softAssert = new SoftAssert();
        //Проверяем, что дополнительная информация отображается при наведении
        int sizeOfAdditionalInformationOnHover = DriverProvider.getDriver().findElements(By.cssSelector("div[class='ut2-gl__body content-on-hover']")).size();
        softAssert.assertTrue(sizeOfAdditionalInformationOnHover > 1, "Buttons are displayed without mouse hover on the product block!");
        //Проверяем, что логотип присутствует
        int sizeOfLogo = DriverProvider.getDriver().findElements(By.cssSelector(".brand-img")).size();
        softAssert.assertTrue(sizeOfLogo > 2, "There is no product logo on the product block!");
        //Проверяем, что текст "Вы экономите" присутствует
        int sizeOfYouSave = DriverProvider.getDriver().findElements(By.cssSelector("span[class='ty-list-price ty-save-price ty-nowrap']")).size();
        softAssert.assertTrue(sizeOfYouSave > 1, "There is no text 'You save' on the product block!");
        //Проверяем, что галерея мини-иконок товара в виде точек
        int sizeOfGalleryOfMiniIcons = DriverProvider.getDriver().findElements(By.cssSelector(".owl-pagination")).size();
        softAssert.assertTrue(sizeOfGalleryOfMiniIcons >= 3, "Gallery of mini icons is not with points on the product block!");
        takeScreenShot_withoutScroll("400 ProductLists_GridListView_Var2 - BlockWithProducts");
        stHomePage.selectLanguage_RTL();
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot_withoutScroll("405 ProductLists_GridListView_Var2 - BlockWithProducts (RTL)");
        stHomePage.selectLanguage_RU();

        //Категория "Мужская одежда"
        stHomePage.navigateToMenuMenCloth();
        //Проверяем, что дополнительная информация отображается при наведении
        softAssert.assertTrue(sizeOfAdditionalInformationOnHover > 1, "Buttons are displayed without mouse hover on the category page!");
        //Проверяем, что логотип присутствует
        softAssert.assertTrue(sizeOfLogo > 2, "There is no product logo on the category page!");
        //Проверяем, что текст "Вы экономите" присутствует
        softAssert.assertTrue(sizeOfYouSave > 1, "There is no text 'You save' on the category page!");
        //Проверяем, что галерея мини-иконок в виде точек
        softAssert.assertTrue(sizeOfGalleryOfMiniIcons  >= 3, "Gallery of mini icons is not with points on the category page!");
        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.hoverToMenClothProduct();
        takeScreenShot("410 ProductLists_GridListView_Var2 - MenClothCategory");
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToMenClothProduct();
        takeScreenShot("415 ProductLists_GridListView_Var2 - MenClothCategory (RTL)");
        stCategoryPage.clickQuickViewOfMenClothProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("420 ProductLists_GridListView_Var2 - QuickView (RTL)");
        stCategoryPage.clickCloseQuickView();
        stHomePage.selectLanguage_RU();
        stCategoryPage.hoverToMenClothProduct();
        stCategoryPage.clickQuickViewOfMenClothProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("425 ProductLists_GridListView_Var2 - QuickView");
        softAssert.assertAll();
        System.out.println("GeneralSettings_ProductLists_GridListView_Var2 passed successfully!");
    }
}