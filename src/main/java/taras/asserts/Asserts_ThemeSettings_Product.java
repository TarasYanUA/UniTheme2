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
    public String text_YouSave_Full = ".ty-save-price:not(.ut2-sld-short .ty-save-price)";

    //Настройка "Отображать "Вы экономите -- Сокращенный вид"
    public String text_YouSave_Short = ".ut2-sld-short .ty-save-price";

    //Настройка "Отображать информацию о бренде товара -- Отображать название бренда товара"
    public String productBrandInformation_Name = ".ut2-pb__product-brand-name";

    //Настройка "Отображать информацию о бренде товара -- Отображать логотип бренда товара"
    public String productBrandInformation_Logo = ".ut2-pb__product-brand";

    //Настройка "Количество отображаемых изображений галереи товара -- 2"
    public String numberOfDisplayedImagesOfProductGallery_2 = ".images-2";


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

    public void assertElementPresence(String list, String selector, String location, boolean elementExists) {
        Map<String, String> presenceMessages = Map.ofEntries(
                Map.entry(customBlockID, "There is no Custom block "),
                Map.entry(quantityChanger, "There is no Quantity changer "),
                Map.entry(productCode, "There is no Product Code "),
                Map.entry(featuresInTwoColumns_Enabled, "Features are shown in one column instead of two "),
                Map.entry(featuresInTwoColumns_Disabled, "Features are shown in two columns instead of one "),
                Map.entry(text_YouSave_Full, "The text 'You save' is not 'Full' or missed "),
                Map.entry(text_YouSave_Short, "The text 'You save' is not 'Short' or missed "),
                Map.entry(productBrandInformation_Name, "The feature Brand is not as 'Name' or missed "),
                Map.entry(productBrandInformation_Logo, "The feature Brand is not as 'Logo' or missed "),
                Map.entry(numberOfDisplayedImagesOfProductGallery_2, "Number of displayed images of the product gallery is not 2 ")
        );

        Map<String, String> absenceMessages = Map.ofEntries(
                Map.entry(text_YouSave_Full, "There is a text 'You save' as 'Full' but shouldn't "),
                Map.entry(text_YouSave_Short, "There is a text 'You save' as 'Short' but shouldn't "),
                Map.entry(productBrandInformation_Name, "The feature Brand is as 'Name' but shouldn't "),
                Map.entry(productBrandInformation_Logo, "The feature Brand is as 'Logo' but shouldn't ")
        );

        String message = resolveAssertMessage(selector, elementExists, presenceMessages, absenceMessages);
        if (message == null)
            return;

        String finalSelector = switch (list) {
            case productPage -> productPage + selector;
            default -> selector;
        };

        getSoftAssert().assertTrue(
                elementExists == !DriverProvider.getDriver().findElements(By.cssSelector(finalSelector)).isEmpty(),
                message + location + "\n" + finalSelector
        );
    }
}