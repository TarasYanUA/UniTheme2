package taras.asserts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.util.List;
import java.util.Map;

public class Asserts_Banners extends AbstractPage {
    public Asserts_Banners() {super();}

    private SoftAssert getSoftAssert() {
        return CollectAssertMessages.getSoftAssertions();
    }


    private String blockID;

    public void setBlockID(String blockID) {this.blockID = blockID;}

    public String getProductBlockSelector() {
        return "//div[contains(@id, 'products') and contains(@id, '" + blockID + "')]/..";
    }


    public String templateOfProducts_Grid = "/div[contains(@class, 'grid-list')]";
    public String templateOfProducts_SmallElements = "//ul[contains(@class, 'ut2-template-small')]";
    public String columnsOfProducts_Grid = "//div[contains(@class, 'ty-column";
    public String columnsOfProducts_SmallElements = "//ul[contains(@style, '--si-columns: ";
    public String bannerTitle = "/..//div[contains(@class, 'ut2-a__title')]";
    public String productScroller = "//button[contains(@class, 'ut2-scroll-right') and not(contains(@style, 'display: none'))]";
    public String quantityOfProducts_Grid = "//div[contains(@class, 'ty-column')]";
    public String quantityOfProducts_SmallElements = "//li[contains(@class, 'ut2-template-small__item')]";


    public void assertElementPresence(String selector) {
        Map<String, String> messages = Map.ofEntries(
                Map.entry(templateOfProducts_Grid, "Template of products is not 'Grid'!"),
                Map.entry(templateOfProducts_SmallElements, "Template of products is not 'Small elements'!"),
                Map.entry(bannerTitle, "There is no Title of banner!"),
                Map.entry(productScroller, "There is no product Scroller!")
        );

        String finalSelector = getProductBlockSelector() + selector;

        String finalMessage = messages.get(selector);
        if (finalMessage == null)
            getSoftAssert().fail("No assert message found for selector: " + selector);

        getSoftAssert().assertTrue(!DriverProvider.getDriver().findElements(By
                        .xpath(finalSelector)).isEmpty(),
                finalMessage + "\n" + finalSelector
        );
    }

    public void assertNumberOfColumns(String selector, int columns) {
        Map<String, String> selectorMessages = Map.ofEntries(
                Map.entry(columnsOfProducts_Grid, "Number of product columns in 'Grid' template is not " + columns),
                Map.entry(columnsOfProducts_SmallElements, "Number of product columns in 'Small elements' template is not " + columns)
        );

        String message = selectorMessages.get(selector);
        if (message == null)
            getSoftAssert().fail("No assert message found for selector: " + selector);

        String finalSelector = getProductBlockSelector() + selector + columns + "')]";

        getSoftAssert().assertTrue(
                !DriverProvider.getDriver().findElements(By.xpath(finalSelector)).isEmpty(),
                message + "\n" + finalSelector
        );
    }

    public void assertNumberOfProducts(String selector, int size) {
        Map<String, String> messages = Map.ofEntries(
                Map.entry(quantityOfProducts_Grid, "Number of products in 'Grid' template is not " + size),
                Map.entry(quantityOfProducts_SmallElements, "Number of products in 'Small elements' template is not " + size)
        );

        List<WebElement> finalSelector = DriverProvider.getDriver().findElements(By
                .xpath(getProductBlockSelector() + selector));

        String finalMessage = messages.get(selector);
        if (finalMessage == null)
            getSoftAssert().fail("No assert message found for selector: " + selector);

        getSoftAssert().assertEquals(finalSelector.size(), size,
                finalMessage + size);
    }
}