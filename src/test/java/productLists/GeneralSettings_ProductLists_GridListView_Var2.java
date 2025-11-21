package productLists;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.*;
import taras.constants.DriverProvider;
import taras.storefront.AssertsOnStorefront;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

import java.time.Duration;

/*
Проверка настроек UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Отображать пустые звёзды рейтинга товара -- нет
Отображать общее значение рейтинга товара -- да
Отображать "Вы экономите" -- Полный вид

Ширина иконки товара (по умолчанию 240) --	400
Высота иконки товара (по умолчанию 290) --	380
Количество строк в названии товара      --  2
Отображать код товара -- нет
Отображать статус наличия -- нет
Отображать модификатор количества -- нет
Отображать кнопку "Купить" -- Только Иконка корзины (упрощенный вариант)
Дополнительная информация о товаре -- Список характеристик и вариаций
Отображать дополнительную информацию при наведении -- да
Отображать бренд -- Название
Показывать галерею мини-иконок товара в товарном списке --  Навигация точками
Переключать изображение товара при движении мышки -- Не переключать (нужно для настройки выше)

Вкладка "Показать ещё" -- Разрешить для товарных списков--  нет
*/

public class GeneralSettings_ProductLists_GridListView_Var2 extends TestRunner {
    @Test(priority = 1)
    public void setConfigurationsForProductLists_GridListView_Var2() {
        BasicPage basicPage = new BasicPage();

        //Работаем с настройками характеристики Бренд
        FeaturePage featuresPage = basicPage.navigateToSection_Features();
        featuresPage.featureBrand.click();
        WebElement checkboxShowInProductList = featuresPage.showInProductList;
        if (!checkboxShowInProductList.isSelected()) {
            checkboxShowInProductList.click();
            basicPage.clickSaveButtonOfSettings();
        }

        //Работаем с настройками темы
        ThemeSettings_ProductLists themeSettingsProductLists = basicPage.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.tabProductLists.click();
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_EmptyStarsOfProductRating, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_CommonValueOfProductRating, true);
        new Select(themeSettingsProductLists.grid_NumberOfLinesInProductName).selectByValue("2");
        new Select(themeSettingsProductLists.setting_ShowYouSave).selectByValue("full");
        UtilsAdm.clickAndType(themeSettingsProductLists.setting_ProductIconWidth, "400");
        UtilsAdm.clickAndType(themeSettingsProductLists.setting_ProductIconHeight, "380");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_ShowProductCode, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_DisplayAvailabilityStatus, false);
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_ShowQuantityChanger, false);
        new Select(themeSettingsProductLists.setting_ShowAddToCartButton).selectByValue("icon");
        new Select(themeSettingsProductLists.setting_AdditionalProductInformation).selectByValue("features_and_variations");
        UtilsAdm.setCheckboxState(themeSettingsProductLists.setting_ShowAdditionalInformationOnHover, true);
        new Select(themeSettingsProductLists.setting_ShowBrand).selectByValue("name");
        new Select(themeSettingsProductLists.setting_ShowStandardImageGallery_Grid).selectByValue("points");
        new Select(themeSettingsProductLists.setting_SwitchProductImageWhenHovering).selectByValue("N");
        ThemeSettings_ShowMore themeSettings_showMore = new ThemeSettings_ShowMore();
        themeSettings_showMore.openTab_ShowMore();
        UtilsAdm.setCheckboxState(themeSettings_showMore.setting_AllowForProductLists, false);
        basicPage.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_GridListView_Var2")
    public void checkProductLists_GridListView_Var2() {
        BasicPage basicPage = new BasicPage();
        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        stHomePage.openProductBlock("Распродажа");

        SoftAssert softAssert = new SoftAssert();
        AssertsOnStorefront assertsOnStorefront = new AssertsOnStorefront();

        //Проверяем, что дополнительная информация отображается при наведении
        softAssert.assertTrue(!assertsOnStorefront.gridList__AdditionalInformationOnHover.isEmpty(),
                "Additional information is displayed without mouse hover in the product block!");

        //Проверяем, что название бренда присутствует
        softAssert.assertTrue(!assertsOnStorefront.gridList__BrandName().isEmpty(),
                "There is no brand name in the product block!");

        //Проверяем, что текст "Вы экономите" присутствует и "Полный вид"
        softAssert.assertTrue(!assertsOnStorefront.text_YouSave_Full().isEmpty()
                && assertsOnStorefront.text_YouSave_Short().isEmpty(),
                "The text 'You save' is not Full or missed in the product block!");

        //Проверяем, что галерея мини-иконок товара в виде точек
        softAssert.assertTrue(!assertsOnStorefront.gridList__ShowStandardImageGallery_Dots().isEmpty(),
                "Gallery of mini icons is not with points in the product block!");

        takeScreenShot("400 GS_ProductLists_GridListView_Var2 - BlockWithProducts");
        stHomePage.selectLanguage("ar");
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("405 GS_ProductLists_GridListView_Var2 - BlockWithProducts (RTL)");
        stHomePage.selectLanguage("ru");

        //Категория "Мужская одежда"
        stHomePage.navigateToHorizontalMenu_MenCloth();

        //Проверяем, что дополнительная информация отображается при наведении
        softAssert.assertTrue(!assertsOnStorefront.gridList__AdditionalInformationOnHover.isEmpty(),
                "Additional information is displayed without mouse hover on the category page 'GridList'!");

        //Проверяем, что название бренда присутствует
        softAssert.assertTrue(!assertsOnStorefront.gridList__BrandName().isEmpty(),
                "There is no brand name on the category page 'GridList'!");

        //Проверяем, что текст "Вы экономите" присутствует и "Полный вид"
        softAssert.assertTrue(!assertsOnStorefront.text_YouSave_Full().isEmpty()
                        && assertsOnStorefront.text_YouSave_Short().isEmpty(),
                "The text 'You save' is not Full or missed on the category page 'GridList'!");

        //Проверяем, что галерея мини-иконок товара в виде точек
        softAssert.assertTrue(!assertsOnStorefront.gridList__ShowStandardImageGallery_Dots().isEmpty(),
                "Gallery of mini icons is not with points on the category page 'GridList'!");

        StCategoryPage stCategoryPage = new StCategoryPage();
        stCategoryPage.hoverToProduct("Футболка, Цвет: Черный");
        takeScreenShot("410 GS_ProductLists_GridListView_Var2 - MenClothCategory");
        stHomePage.selectLanguage("ar");
        stCategoryPage.hoverToProduct("T-shirt, Color: Black");
        takeScreenShot("415 GS_ProductLists_GridListView_Var2 - MenClothCategory (RTL)");
        stCategoryPage.clickQuickViewOfMenClothProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("420 GS_ProductLists_GridListView_Var2 - QuickView (RTL)");
        UtilsAdm.hoverNavigateAndClick(stCategoryPage.closeQuickView);
        stHomePage.selectLanguage("ru");
        stCategoryPage.clickQuickViewOfMenClothProduct();
        takeScreenShot("425 GS_ProductLists_GridListView_Var2 - QuickView");

        softAssert.assertAll();
        System.out.println("productLists.GeneralSettings_ProductLists_GridListView_Var2 passed successfully!");
    }
}