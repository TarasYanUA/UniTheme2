package taras.asserts;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.util.Map;

public class Asserts_ThemeSettings_ProductLists extends AbstractPage {
    public Asserts_ThemeSettings_ProductLists() {
        super();
    }

    SoftAssert softAssert = CollectAssertMessages.getSoftAssertions();

    String gridList = ".ut2-gl__body";
    String listWithoutOptions = ".ty-product-list";
    String compactList = ".ty-compact-list__content";


    //Настройка "Обесцвечивать товары, которых нет в наличии"
    public String decolorizeOutOfStockProducts = ".ut2-gl__body.decolorize";

    //Настройка "Отображать пустые звёзды рейтинга товара"
    public String emptyStarsOfProductRating = "div[class*='ty-product-review-reviews-stars'][data-ca-product-review-reviews-stars-full='0']";





    public void assertElementPresence(String selector, String page, boolean shouldExist) {
        Map<String, String> presenceMessages = Map.ofEntries(
                Map.entry(decolorizeOutOfStockProducts, "There are no decolorized products ")
        );

        Map<String, String> absenceMessages = Map.ofEntries(
                Map.entry(decolorizeOutOfStockProducts, "There are decolorized products but shouldn't ")
        );

        String message = shouldExist
                ? presenceMessages.get(selector)
                : absenceMessages.get(selector);

        if (message == null)
            throw new IllegalArgumentException("No assert message found for selector: " + selector);

        softAssert.assertFalse(shouldExist == DriverProvider.getDriver()
                .findElements(By.cssSelector(selector)).isEmpty(), message + page);
    }
}
