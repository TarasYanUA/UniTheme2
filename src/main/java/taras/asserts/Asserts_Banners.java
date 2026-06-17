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
        return "//div[contains(@id, 'products') and contains(@id, '" + blockID + "')]/.. ";
    }


    public String columnsOfProducts_1 = "//div[contains(@class, 'ty-column1')]";
    public String columnsOfProducts_2 = "//div[contains(@class, 'ty-column2')]";
    public String columnsOfProducts_3 = "//div[contains(@class, 'ty-column3')]";
    public String bannerTitle = "/..//div[contains(@class, 'ut2-a__title')]";
    public String productScroller = "//div[contains(@class, 'ut2-scroll-container')]";


    public void assertElementPresence(String selector) {
        Map<String, String> messages = Map.ofEntries(
                Map.entry(columnsOfProducts_1, "Number of product columns is not 1!"),
                Map.entry(columnsOfProducts_2, "Number of product columns is not 2!"),
                Map.entry(columnsOfProducts_3, "Number of product columns is not 3!"),
                Map.entry(bannerTitle, "There is no Title of banner!"),
                Map.entry(productScroller, "There is no product Scroller!")
        );

        String finalSelector = getProductBlockSelector() + selector;

        String finalMessage = messages.get(selector);
        if (finalMessage == null)
            getSoftAssert().fail("No assert message found for selector: " + selector);

        getSoftAssert().assertTrue(!DriverProvider.getDriver().findElements(By
                        .xpath(getProductBlockSelector() + finalSelector)).isEmpty(),
                finalMessage + "\n" + finalSelector
        );
    }

    public void assertNumberOfProducts(int size) {
        List<WebElement> finalSelector = DriverProvider.getDriver().findElements(By
                .xpath(getProductBlockSelector() + "//div[contains(@class, 'ty-column')]"));

        getSoftAssert().assertEquals(finalSelector.size(), size,
                "Number of products in the banner is not equal " + size);
    }
}