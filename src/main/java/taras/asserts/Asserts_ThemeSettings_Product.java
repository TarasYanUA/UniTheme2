package taras.asserts;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.util.Map;

public class Asserts_ThemeSettings_Product extends AbstractPage {
    public Asserts_ThemeSettings_Product() {super();}

    private SoftAssert getSoftAssert() {
        return CollectAssertMessages.getSoftAssertions();
    }


    public static final String productPage = ".ty-product-block ";


    //Настройка "ID пользовательского блока"
    public String customBlockID = ".ut2-pb__custom-block";

    //Настройка "Отображать модификатор количества"
    public String quantityChanger = "div.ty-qty[id*='qty_'";

    //Настройка "Отображать код товара"
    public String productCode = ".ut2-pb__sku";

    //Настройка "Отображать характеристики в две колонки" ВКЛ.
    public String featuresInTwoColumns_Enabled = ".fg-two-col";

    //Настройка "Отображать характеристики в две колонки" ОТКЛ.
    public String featuresInTwoColumns_Disabled = "div[class='cm-ab-similar-filter-container ']";




    //Настройка "Отображать "Вы экономите -- Полный вид"
    public String text_YouSave_Full = " .ty-save-price:not(.ut2-sld-short .ty-save-price)";

    //Настройка "Отображать "Вы экономите -- Сокращенный вид"
    public String text_YouSave_Short = " .ut2-sld-short .ty-save-price";



    private String resolveAssertMessage(String selector,
                                        boolean elementsAreEmpty,
                                        Map<String, String> presenceMessages,
                                        Map<String, String> absenceMessages) {

        String message = elementsAreEmpty
                ? presenceMessages.get(selector)
                : absenceMessages.get(selector);

        if (message == null)
            getSoftAssert().fail("No assert message found for selector: " + selector);

        return message;
    }

    public void assertElementPresence(String list, String selector, String location, boolean elementsAreEmpty) {
        Map<String, String> presenceMessages = Map.ofEntries(
                Map.entry(customBlockID, "There is no Custom block "),
                Map.entry(quantityChanger, "There is no Quantity changer "),
                Map.entry(productCode, "There is no Product Code "),
                Map.entry(featuresInTwoColumns_Enabled, "Features are shown in one column instead of two "),
                Map.entry(featuresInTwoColumns_Disabled, "Features are shown in two columns instead of one "),


                Map.entry(text_YouSave_Full, "The text 'You save' is not 'Full' or missed "),
                Map.entry(text_YouSave_Short, "The text 'You save' is not 'Short' or missed ")
        );

        Map<String, String> absenceMessages = Map.ofEntries(
                Map.entry(text_YouSave_Full, "There is a text 'You save' as 'Full' but shouldn't "),
                Map.entry(text_YouSave_Short, "There is a text 'You save' as 'Short' but shouldn't ")
        );

        String message = resolveAssertMessage(selector, elementsAreEmpty, presenceMessages, absenceMessages);
        if (message == null)
            return;

        String finalSelector = switch (list) {
            case productPage -> productPage + selector;
            default -> selector;
        };

        getSoftAssert().assertTrue(
                !elementsAreEmpty == DriverProvider.getDriver().findElements(By.cssSelector(finalSelector)).isEmpty(),
                message + location + "\n" + finalSelector
        );
    }
}
