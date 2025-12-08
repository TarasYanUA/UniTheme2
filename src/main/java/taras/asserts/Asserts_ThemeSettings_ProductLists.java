package taras.asserts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import taras.adminPanel.UtilsAdm;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.util.List;
import java.util.Map;

public class Asserts_ThemeSettings_ProductLists extends AbstractPage {
    public Asserts_ThemeSettings_ProductLists() {
        super();
    }

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


    public static final String gridList = ".grid-list ";
    public static final String listWO = ".ty-product-list ";
    public static final String compactList = ".ty-compact-list__item ";
    public static final String productBlock = "productBlock";
    public static final String productPage = ".ty-product-block";


    //Настройка "Обесцвечивать товары, которых нет в наличии"
    public String decolorizeOutOfStockProducts = ".ut2-gl__body.decolorize";

    //Настройка "Отображать пустые звёзды рейтинга товара"
    public String emptyStarsOfProductRating = "div[class*='ty-product-review-reviews-stars'][data-ca-product-review-reviews-stars-full='0']";

    //Настройка "Отображать общее значение рейтинга товара"
    public String commonValueOfProductRating = ".ut2-show-rating-num";

    //Настройка "Отображать статусы для кнопок "Купить" -- Иконка"
    public String getStatusesForButtonAddToCartIcon() {
        UtilsAdm.makePause(2000);
        JavascriptExecutor js = (JavascriptExecutor) DriverProvider.getDriver();

        List<WebElement> cartButtons = DriverProvider.getDriver()
                .findElements(By.cssSelector("button[id*='button_cart']"));

        if (!cartButtons.isEmpty() && cartButtons.getFirst().isEnabled()) {
            return (String) js.executeScript(
                    "return window.getComputedStyle(arguments[0], '::before')"
                            + ".getPropertyValue('content');",
                    cartButtons.getFirst());
        }

        List<WebElement> buttonsWithOptions = DriverProvider.getDriver()
                .findElements(By.cssSelector("a[id*='opener_ut2_select_options_']"));

        if (!buttonsWithOptions.isEmpty() && buttonsWithOptions.getFirst().isEnabled()) {
            return (String) js.executeScript(
                    "return window.getComputedStyle(arguments[0], '::before')"
                            + ".getPropertyValue('content');",
                    buttonsWithOptions.getFirst());
        }

        return "";
    }


    //Настройка "Отображать статусы для кнопок "Купить" -- Количество товаров"
    public String statusesForButton_AddToCart_Number = ".ut2-added-to-cart[data-added-amount='1']";

    //Настройка "Отображать статусы для кнопок... "Добавить в избранное"
    public String statusesForButton_AddToWishList = "a.ut2-add-to-wish.active";

    //Настройка "Отображать статусы для кнопок... "Добавить в список сравнения"
    public String statusesForButton_AddToComparisonList = "a.ut2-add-to-compare.active";

    //Настройка "Отображать кнопку "Добавить в избранное"
    public String button_AddToWishList = ".ut2-add-to-wish";

    //Настройка "Отображать кнопку "Добавить в список сравнения""
    public String button_AddToComparisonList = ".ut2-add-to-compare";

    //Настройка "Отображать кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" при наведении на ячейку товара"
    public String buttonsAreDisplayedOnHover = ".ut2-w-c-q__buttons.w_c_q-hover";

    //Настройка "Отображать "Вы экономите -- Полный вид"
    public String text_YouSave_Full = ".ty-save-price:not(.ut2-sld-short .ty-save-price)";

    //Настройка "Отображать "Вы экономите -- Сокращенный вид"
    public String text_YouSave_Short = ".ut2-sld-short .ty-save-price";

    //Настройка "Количество строк в названии товара"
    public String numberOfLinesInProductName_Grid = "div[style^='--gl-lines-in-name-product: ";


    private String resolveAssertMessage(String selector,
                                        boolean elementsAreEmpty,
                                        Map<String, String> presenceMessages,
                                        Map<String, String> absenceMessages) {

        String message = elementsAreEmpty
                ? presenceMessages.get(selector)
                : absenceMessages.get(selector);

        if (message == null) {
            getSoftAssert().fail("No assert message found for selector: " + selector);
        }

        return message;
    }

    public void assertElementPresence(String list, String selector, String location, boolean elementsAreEmpty) {
        Map<String, String> presenceMessages = Map.ofEntries(
                Map.entry(decolorizeOutOfStockProducts, "There are no decolorized products "),
                Map.entry(emptyStarsOfProductRating, "There are no empty stars "),
                Map.entry(commonValueOfProductRating, "There are no common values of product rating "),
                Map.entry(getStatusesForButtonAddToCartIcon(), "There is no status as 'Icon' for the button 'Add to cart' "),
                Map.entry(statusesForButton_AddToCart_Number, "There is no status as 'Number of products' for the button 'Add to cart' "),
                Map.entry(statusesForButton_AddToWishList, "There is no status for the button 'Add to wish list' "),
                Map.entry(statusesForButton_AddToComparisonList, "There is no status for the button 'Add to comparison list' "),
                Map.entry(button_AddToWishList, "There are no buttons 'Add to wish list' "),
                Map.entry(button_AddToComparisonList, "There are no buttons 'Add to comparison list' "),
                Map.entry(buttonsAreDisplayedOnHover, "Buttons are not displayed when hovering over a product cell "),
                Map.entry(text_YouSave_Full, "The text 'You save' is not 'Full' or missed "),
                Map.entry(text_YouSave_Short, "The text 'You save' is not 'Short' or missed ")

        );

        Map<String, String> absenceMessages = Map.ofEntries(
                Map.entry(decolorizeOutOfStockProducts, "There are decolorized products but shouldn't "),
                Map.entry(emptyStarsOfProductRating, "There are empty stars but shouldn't "),
                Map.entry(commonValueOfProductRating, "There are common values of product rating but shouldn't "),
                Map.entry(getStatusesForButtonAddToCartIcon(), "There is a status as 'Icon' for button 'Add to cart' but shouldn't "),
                Map.entry(statusesForButton_AddToCart_Number, "There is a status as 'Number of products' for button 'Add to cart' but shouldn't "),
                Map.entry(buttonsAreDisplayedOnHover, "Buttons are displayed when hovering over a product cell but should be displayed at once "),
                Map.entry(text_YouSave_Full, "There is a text 'You save' as 'Full' but shouldn't "),
                Map.entry(text_YouSave_Short, "There is a text 'You save' as 'Short' but shouldn't ")

        );

        String message = resolveAssertMessage(selector, elementsAreEmpty, presenceMessages, absenceMessages);
        if (message == null)
            return;

        String finalSelector = switch (list) {
            case gridList -> gridList + selector;
            case listWO -> listWO + selector;
            case compactList -> compactList + selector;
            case productBlock -> getProductBlockSelector() + selector;
            default -> selector;
        };

        getSoftAssert().assertTrue(
                !elementsAreEmpty == DriverProvider.getDriver().findElements(By.cssSelector(finalSelector)).isEmpty(),
                message + location + "\n" + finalSelector
        );
    }
}