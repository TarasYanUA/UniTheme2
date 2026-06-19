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

public class ProductsAtBanners_Grid_3Columns extends TestRunner {
    String bannerName = "Auto banner with products - Grid_3Columns";
    String blockID;
    Asserts_Banners assertsBanners = new Asserts_Banners();

    @Test(priority = 1)
    public void setProductsAtBanners_Grid_3Columns() {
        BasicPage basicPage = new BasicPage();

        //Создаём баннер
        BannerPage bannerPage = basicPage.navigateToSection_Banners();
        bannerPage.createNewAdvancedBannerWithProducts(
                bannerName,
                "grid_items",
                "3",
                "ead1dc");

        //Работаем на странице "Макеты"
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_TabHomePage.click();
        String layoutID = layoutPage.getLayoutIDByBlockName("<mark>Возможно,</mark> вас это заинтересует");
        if (DriverProvider.getDriver().findElements(By.cssSelector("div[title='" + bannerName + "']")).isEmpty()) {
            layoutPage.switchOffAllBlocksAtLayout(layoutID);
            layoutPage.createNewBlockWithBannerAtLayout(layoutID, bannerName);
        }
        blockID = getBlockID(bannerName);           //Получаем ID блока с баннером
        assertsBanners.setBlockID(blockID);         //Передаём blockID в класс с проверками
    }

    @Test(priority = 2, dependsOnMethods = "setProductsAtBanners_Grid_3Columns")
    public void checkProductsAtBanners_Grid_3Columns() {
        BasicPage basicPage = new BasicPage();

        By bannerLocator = By.cssSelector(
                ".ut2-a__products-banner div[id*='" + blockID + "']"
        );

        StHomePage stHomePage = basicPage.navigateToStorefront();
        focusBrowserTab(1);
        stHomePage.cookie.click();

        UtilsAdm.scrollToElementAndScrollBelow(DriverProvider.getDriver().findElement(bannerLocator), 100);
        takeScreenShot(bannerName + " 01");

        //Проверяем, что у баннера 3 колонки
        assertsBanners.assertElementPresence(assertsBanners.columnsOfProducts_3);

        //Проверяем у баннера наличие названия
        assertsBanners.assertElementPresence(assertsBanners.bannerTitle);

        //Проверяем у баннера наличие 7 товаров
        assertsBanners.assertNumberOfProducts(7);

        //Проверяем у баннера наличие скроллера
        assertsBanners.assertElementPresence(assertsBanners.productScroller);

        stHomePage.selectLanguage("ar");
        UtilsAdm.scrollToElementAndScrollBelow(DriverProvider.getDriver().findElement(bannerLocator), 100);
        takeScreenShot(bannerName + " 02(RTL)");
    }
}