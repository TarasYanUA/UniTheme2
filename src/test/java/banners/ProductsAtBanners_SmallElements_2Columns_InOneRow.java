package banners;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import taras.adminPanel.BannerPage;
import taras.adminPanel.BasicPage;
import taras.adminPanel.LayoutPage;
import taras.adminPanel.UtilsAdm;
import taras.asserts.Asserts_Banners;
import taras.constants.DriverProvider;
import taras.storefront.StHomePage;
import testRunner.TestRunner;

public class ProductsAtBanners_SmallElements_2Columns_InOneRow extends TestRunner {
    String bannerName = "Auto banner with products - SmallElements_2Columns_InOneRow";
    String blockID;
    Asserts_Banners assertsBanners = new Asserts_Banners();

    @Test(priority = 1)
    public void setProductsAtBanners_SmallElements_2Columns_InOneRow() {
        BasicPage basicPage = new BasicPage();

        //Создаём баннер
        BannerPage bannerPage = basicPage.navigateToSection_Banners();
        bannerPage.createNewAdvancedBannerWithProducts(
                bannerName,
                "small_items",
                "2",
                "1",
                "afd8fd");

        //Работаем на странице "Макеты"
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_TabHomePage.click();
        String sectionID = layoutPage.getSectionIDByBlockName("<mark>Возможно,</mark> вас это заинтересует");
        if (DriverProvider.getDriver().findElements(By.cssSelector("div[title='" + bannerName + "']")).isEmpty()) {
            layoutPage.switchOffAllBlocksAtSection(sectionID);
            layoutPage.createNewBlockWithBannerAtSection(sectionID, bannerName);
        }
        blockID = getBlockID(bannerName);           //Получаем ID блока с баннером
        assertsBanners.setBlockID(blockID);         //Передаём blockID в класс с проверками
    }

    @Test(priority = 2, dependsOnMethods = "setProductsAtBanners_SmallElements_2Columns_InOneRow")
    public void checkProductsAtBanners_SmallElements_2Columns_InOneRow() {
        BasicPage basicPage = new BasicPage();

        By bannerLocator = By.cssSelector(
                ".ut2-a__products-banner div[id*='" + blockID + "']"
        );

        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        UtilsAdm.scrollIntoCenter(DriverProvider.getDriver().findElement(bannerLocator));
        takeScreenShot(bannerName + " 01");

        //Проверяем, что шаблон у товаров "Мелкие элементы"
        assertsBanners.assertElementPresence(assertsBanners.templateOfProducts_SmallElements);

        //Проверяем, что у баннера 2 колонки
        assertsBanners.assertNumberOfColumns(assertsBanners.columnsOfProducts_SmallElements, 2);

        //Проверяем у баннера наличие названия
        assertsBanners.assertElementPresence(assertsBanners.bannerTitle);

        //Проверяем у баннера наличие 7 товаров
        assertsBanners.assertNumberOfProducts(assertsBanners.quantityOfProducts_SmallElements, 7);

        //Проверяем у баннера наличие скроллера
        assertsBanners.assertElementPresence(assertsBanners.productScroller);

        stHomePage.selectLanguage("ar");
        UtilsAdm.scrollIntoCenter(DriverProvider.getDriver().findElement(bannerLocator));
        takeScreenShot(bannerName + " 02(RTL)");
    }
}