package banners;

import org.testng.annotations.Test;
import taras.adminPanel.BannerPage;
import taras.adminPanel.BasicPage;
import taras.adminPanel.LayoutPage;
import testRunner.TestRunner;

public class ProductsAtBanners_Grid_1Column extends TestRunner {
    String bannerName = "Auto banner with products - Grid_1Column";
    String blockID;

    @Test(priority = 1)
    public void setProductsAtBanners_Grid_1Column() {
        BasicPage basicPage = new BasicPage();

        //Создаём баннер
        BannerPage bannerPage = basicPage.navigateToSection_Banners();
        bannerPage.createNewAdvancedBannerWithProducts(
                bannerName,
                "ccffcc");

        //Работаем на странице "Макеты"
        LayoutPage layoutPage = basicPage.navigateToSection_WebsiteLayouts();
        layoutPage.layout_TabHomePage.click();
        String layoutID = layoutPage.getLayoutIDByBlockName("<mark>Возможно,</mark> вас это заинтересует");
        layoutPage.switchOffAllBlocksAtLayout(layoutID);
        layoutPage.createNewBlockWithBannerAtLayout(layoutID, bannerName);
        blockID = getBlockID(bannerName);         //Получаем ID нужного блока с баннером
    }
}