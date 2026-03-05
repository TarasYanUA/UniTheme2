package taras.asserts;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.util.Map;

public class Asserts_CsCartSettings extends AbstractPage {
    public Asserts_CsCartSettings() {super();}

    private SoftAssert getSoftAssert() {
        return CollectAssertMessages.getSoftAssertions();
    }


    private String blockID;

    public void setBlockID(String blockID) {
        this.blockID = blockID;
    }

    public String getProductBlockSelector() {
        return "div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] ";
    }


    public static final String productPage = ".ty-product-block ";
    public static final String productPage_xpath = "//div[@class='ut2-pb ty-product-block ut2-big-image'] ";
    public static final String gridList = ".grid-list ";
    public static final String listWO = ".ty-product-list ";
    public static final String compactList = ".ty-compact-list__item ";
    public static final String productBlock = "productBlock";


    //Текст налога "[цена налога] + Вкл налог". Настройка "Показывать цены с налогом на страницах категорий и товаров"
    public String pricesWithTaxes = "span[id*='line_product_price_']";

    //Настройка "Показывать мини-иконки в виде галереи" ВКЛ.
    public String miniThumbnailImagesAsGallery = ".ty-product-thumbnails_gallery";

    //Настройка "Показывать информацию о товаре во вкладках" ВКЛ.
    public String displayProductDetailsInTabs_Enabled = ".ut2-pb__tabs .ty-tabs.cm-j-tabs";

    //Настройка "Показывать информацию о товаре во вкладках" ОТКЛ.
    public String displayProductDetailsInTabs_Disabled = ".tab-list-title";

    //Настройка "Включить быстрый просмотр"
    public String enableQuickView = "a[class*='ut2-quick-view-button']";

    //Настройка "Товары -- Характеристики -- Бренд -- Показывать в заголовке карточки товара"
    public String showInHeaderOnProductPage_Brand = "//div[@class='ut2-features-list']//em[text()='Brand']";

    //Настройка "Товары -- Характеристики -- Жесткий диск -- Показывать в заголовке карточки товара"
    public String showInHeaderOnProductPage_HardDrive = "//div[@class='ut2-features-list']//em[text()='Hard drive']";

    //Настройка "Внешняя навигация" в настройках блока
    public String outsideNavigation = ".owl-theme.ty-owl-controls";


    //Настройки блоков

    //Настройка блока "Количество колонок в списке"
    public String block_NumberOfColumnsInList = "div[class='ty-column";

    //Настройка блока "Количество элементов"
    public String block_NumberOfElements = ".owl-item.active";

    //Настройка блока "Показать номер элемента"
    public String block_ShowItemNumber = ".ut2-hit";


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
                Map.entry(pricesWithTaxes, "There is no text of a product tax "),
                Map.entry(miniThumbnailImagesAsGallery, "Mini-icons are not as a 'Gallery' "),
                Map.entry(displayProductDetailsInTabs_Enabled, "Product information is not displayed in tabs "),
                Map.entry(displayProductDetailsInTabs_Disabled, "Product information is displayed in tabs but shouldn't "),
                Map.entry(enableQuickView, "There is no button 'Quick view' "),
                Map.entry(showInHeaderOnProductPage_Brand, "There is no feature 'Brand' in the product header "),
                Map.entry(showInHeaderOnProductPage_HardDrive, "There is no feature 'Hard drive' in the product header "),
                Map.entry(outsideNavigation, "There is no outside navigation "),
                Map.entry(block_ShowItemNumber, "There are no item numbers ")
        );

        Map<String, String> absenceMessages = Map.ofEntries(
                Map.entry(miniThumbnailImagesAsGallery, "Mini-icons are as a 'Gallery' but shouldn't "),
                Map.entry(enableQuickView, "There is a 'Quick view' button but shouldn't "),
                Map.entry(showInHeaderOnProductPage_Brand, "There is a feature 'Brand' in the product header but shouldn't ")
        );

        String message = resolveAssertMessage(selector, elementExists, presenceMessages, absenceMessages);
        if (message == null)
            return;

        String finalSelector = switch (list) {
            case productPage -> productPage + selector;
            case gridList -> gridList + selector;
            case listWO -> listWO + selector;
            case compactList -> compactList + selector;
            case productBlock -> getProductBlockSelector() + selector;
            default -> selector;
        };

        getSoftAssert().assertTrue(
                elementExists == !DriverProvider.getDriver().findElements(By.cssSelector(finalSelector)).isEmpty(),
                message + location + "\n" + finalSelector
        );
    }

    public void assertNumberOfElements(String list, String selector, int quantity, String location) {
        Map<String, String> selectorMessages = Map.ofEntries(
                Map.entry(block_NumberOfColumnsInList, "Number of columns is not equal " + quantity)
        );

        String message = selectorMessages.get(selector);
        if (message == null)
            getSoftAssert().fail("No assert message found for selector: " + selector);

        String finalSelector = switch (list) {
            case gridList -> gridList + selector + quantity + "']";
            case listWO -> listWO + selector + quantity + "']";
            case compactList -> compactList + selector + quantity + "']";
            case productBlock -> getProductBlockSelector() + selector + quantity + "']";
            default -> selector + quantity + "']";
        };

        getSoftAssert().assertTrue(
                !DriverProvider.getDriver().findElements(By.cssSelector(finalSelector)).isEmpty(),
                message + " " + location + "\n" + finalSelector
        );
    }

    public void assertSizeOfElements(String list, String selector, int size, String location) {
        Map<String, String> selectorMessages = Map.ofEntries(
                Map.entry(block_NumberOfElements, "Number of elements is not equal " + size)
        );

        String message = selectorMessages.get(selector);
        if (message == null)
            getSoftAssert().fail("No assert message found for selector: " + selector);

        String finalSelector = switch (list) {
            case gridList -> gridList + selector;
            case listWO -> listWO + selector;
            case compactList -> compactList + selector;
            case productBlock -> getProductBlockSelector() + selector;
            default -> selector;
        };

        getSoftAssert().assertEquals(
                DriverProvider.getDriver().findElements(By.cssSelector(finalSelector)).size(), size,
                message + " " + location + "\n" + finalSelector
        );
    }

    public void assertsForXpath(String list, String selector, String location, boolean elementExists) {
        Map<String, String> presenceMessages = Map.ofEntries(
                Map.entry(showInHeaderOnProductPage_Brand, "There is no feature 'Brand' in the product header "),
                Map.entry(showInHeaderOnProductPage_HardDrive, "There is no feature 'Hard drive' in the product header ")
                );

        Map<String, String> absenceMessages = Map.ofEntries(
                Map.entry(showInHeaderOnProductPage_Brand, "There is a feature 'Brand' in the product header but shouldn't ")
        );

        String message = resolveAssertMessage(selector, elementExists, presenceMessages, absenceMessages);
        if (message == null)
            return;

        String finalSelector = switch (list) {
            case productPage_xpath -> productPage_xpath + selector;
            case gridList -> gridList + selector;
            case listWO -> listWO + selector;
            case compactList -> compactList + selector;
            case productBlock -> getProductBlockSelector() + selector;
            default -> selector;
        };

        getSoftAssert().assertTrue(
                elementExists == !DriverProvider.getDriver().findElements(By.xpath(finalSelector)).isEmpty(),
                message + location + "\n" + finalSelector
        );
    }
}