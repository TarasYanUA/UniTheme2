package banners;

import org.testng.annotations.Test;
import taras.adminPanel.BannerPage;
import taras.adminPanel.BasicPage;
import testRunner.TestRunner;

public class ProductsAtBanners_Grid_1Column extends TestRunner {
    String blockID;

    @Test(priority = 1)
    public void setProductsAtBanners_Grid_1Column() {
        BasicPage basicPage = new BasicPage();

        //Создаём баннер
        BannerPage bannerPage = basicPage.navigateToSection_Banners();
        bannerPage.createNewAdvancedBannerWithProducts(
                "Auto banner with products - Grid_1Column",
                "ccffcc");
    }
}