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

    public static final String gridList = ".grid-list ";
    public static final String listWO = ".ty-product-list ";
    public static final String compactList = ".ty-compact-list__item ";


    //Настройка "Обесцвечивать товары, которых нет в наличии"
    public String decolorizeOutOfStockProducts = ".ut2-gl__body.decolorize";

    //Настройка "Отображать пустые звёзды рейтинга товара"
    public String emptyStarsOfProductRating = "div[class*='ty-product-review-reviews-stars'][data-ca-product-review-reviews-stars-full='0']";

    //Настройка "Отображать общее значение рейтинга товара"
    String commonValueOfProductRating = ".ut2-show-rating-num";


    public void assertElementPresence(String list, String selector, String location, boolean shouldExist) {
        Map<String, String> presenceMessages = Map.ofEntries(
                Map.entry(decolorizeOutOfStockProducts, "There are no decolorized products "),
                Map.entry(emptyStarsOfProductRating, "There are no empty stars ")

        );

        Map<String, String> absenceMessages = Map.ofEntries(
                Map.entry(decolorizeOutOfStockProducts, "There are decolorized products but shouldn't "),
                Map.entry(emptyStarsOfProductRating, "There are empty stars but shouldn't ")

        );

        String message = shouldExist
                ? presenceMessages.get(selector)
                : absenceMessages.get(selector);

        if (message == null) {
            softAssert.fail("No assert message found for selector: " + selector + location);
            return;
        }

        String finalSelector = switch (list) {
            case gridList -> gridList + selector;
            case listWO -> listWO + selector;
            case compactList -> compactList + selector;
            default -> selector;
        };

        softAssert.assertTrue(
                !shouldExist == DriverProvider.getDriver().findElements(By.cssSelector(finalSelector)).isEmpty(),
                message + location
        );
    }

    public void assertElementInProductBlock(String selector, String blockID, boolean shouldExist) {
        Map<String, String> presenceMessages = Map.ofEntries(
                Map.entry(emptyStarsOfProductRating, "There are no empty stars in the product block!")
        );

        Map<String, String> absenceMessages = Map.ofEntries(
                Map.entry(emptyStarsOfProductRating, "There are empty stars but shouldn't in the product block!")
        );

        String message = shouldExist
                ? presenceMessages.get(selector)
                : absenceMessages.get(selector);

        if (message == null)
            throw new IllegalArgumentException("No assert message found for selector: " + selector);

        softAssert.assertTrue(!shouldExist == DriverProvider.getDriver()
                .findElements(By.cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] " + selector))
                .isEmpty(), message);
    }
}