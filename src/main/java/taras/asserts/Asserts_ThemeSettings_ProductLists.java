package taras.asserts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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
    public String numberOfLinesInProductName = "div[style^='--gl-lines-in-name-product: ";

    //Настройка "Вид списка товаров "Мелкие элементы" -- Количество строк в названии товара"
    public String smallItems_NumberOfLinesInProductName = "ul[style^='--si-lines-in-name-product: ";

    //Настройка "Вид списка товаров "Скроллер" -- Количество строк в названии товара"
    public String scroller_NumberOfLinesInProductName = "div[style^='--sl-lines-in-name-product: ";

    //Настройка "Отображать код товара"
    public String productCode = "div[id*='product_code_']";

    //Настройка "Отображать статус наличия"
    public String availabilityStatus = ".ty-qty-in-stock.ty-control-group__item";

    //Настройка "Отображать модификатор количества"
    public String quantityChanger = "div[class='ty-center ty-value-changer cm-value-changer']";

    //Настройка "Отображать кнопку "Купить" -- Только иконка корзины"
    public String showAddToCartButton_IconOnly = ".ut2-icon-use_icon_cart";

    //Настройка "Отображать кнопку "Купить" -- Только текст"
    public String showAddToCartButton_TextOnly = ".ty-btn__primary.ty-btn__add-to-cart.cm-form-dialog-closer";

    //Настройка "Вид списка "Сетка" -- "Отображать дополнительную информацию при наведении"
    public String additionalInformationOnHover = "div[class='ut2-gl__item content-on-hover']";

    //Проверяем настройку "Дополнительная информация о товаре -- Описание"
    public String additionalProductInformation_Description = ".ut2-product-description";

    //Проверяем настройку "Дополнительная информация о товаре -- Список характеристик"
    public String additionalProductInformation_Features = ".ut2-features-list";

    //Проверяем настройку "Дополнительная информация о товаре -- Список вариаций"
    public String additionalProductInformation_Variations = ".ut2-lv__item-features";

    //Настройка "Отображать бренд -- Логотип"
    public String brandLogo = ".brand-img";

    //Настройка "Отображать бренд -- Название"
    public String brandName = ".brand-name";

    //Настройка "Отображать стандартную галерею изображений -- Навигация точками"
    public String standardImageGallery_Dots = ".owl-pagination";

    //Настройка "Отображать стандартную галерею изображений -- Навигация стрелками"
    public String standardImageGallery_Arrows = ".ut2-gl__image .icon-right-circle";

    //Настройка "Переключать изображение товара при движении мышки -- с полосками"
    public String switchProductImage_withStripes = "div[class='cm-ab-hover-gallery abt__ut2_hover_gallery lines']";

    //Настройка "Переключать изображение товара при движении мышки -- с точками"
    public String switchProductImage_withDots = "div[class='cm-ab-hover-gallery abt__ut2_hover_gallery points']";

    //Настройка "Вид списка "Список без опций" -- Отображать опции товара"
    public String listWithoutOptions__ShowProductOptions = ".cm-picker-product-options";

    //Новый вид вариаций для модуля "Вариации товаров"
    public String prodVar_NewProductVariations = "div[class*='ut2-lv__features-item lv-hover-items']";

    //Настройка "Модуль "Вариации товаров" -- Тип отображения вариаций -- Цвета"
    public String prodVar_TypeOfVariationsView_Colors = "[data-display='color']";

    //Настройка "Модуль "Вариации товаров" -- Тип отображения вариаций -- Миниатюры"
    public String prodVar_TypeOfVariationsView_Thumbnails = "[data-display='thumbnails']";


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
                Map.entry(text_YouSave_Short, "The text 'You save' is not 'Short' or missed "),
                Map.entry(productCode, "There are no product codes "),
                Map.entry(availabilityStatus, "There are no availability statuses "),
                Map.entry(quantityChanger, "There are no Quantity changers "),
                Map.entry(showAddToCartButton_IconOnly, "The buttons 'Add to cart' are not as 'Icon only' or missed "),
                Map.entry(showAddToCartButton_TextOnly, "The buttons 'Add to cart' are not as 'Text only' or missed "),
                Map.entry(additionalInformationOnHover, "Additional information is displayed without mouse hover "),
                Map.entry(additionalProductInformation_Description, "Additional information about products is not 'Description' "),
                Map.entry(additionalProductInformation_Features, "Additional information about products is not 'Features list' "),
                Map.entry(additionalProductInformation_Variations, "Additional information about products is not 'Variations list' "),
                Map.entry(brandLogo, "There is no brand logo "),
                Map.entry(brandName, "There is no brand name "),
                Map.entry(standardImageGallery_Dots, "Gallery of mini icons is not with 'Points' navigation "),
                Map.entry(standardImageGallery_Arrows, "Gallery of mini icons is not with 'Arrows' navigation "),
                Map.entry(switchProductImage_withStripes, "Gallery of product images is not with 'Stripes' "),
                Map.entry(switchProductImage_withDots, "Image switcher is not with 'Dots' "),
                Map.entry(listWithoutOptions__ShowProductOptions, "There are no product options "),
                Map.entry(prodVar_TypeOfVariationsView_Colors, "Product variations are not as 'Colors' "),
                Map.entry(prodVar_TypeOfVariationsView_Thumbnails, "Product variations are not as 'Thumbnails' ")
        );

        Map<String, String> absenceMessages = Map.ofEntries(
                Map.entry(decolorizeOutOfStockProducts, "There are decolorized products but shouldn't "),
                Map.entry(emptyStarsOfProductRating, "There are empty stars but shouldn't "),
                Map.entry(commonValueOfProductRating, "There are common values of product rating but shouldn't "),
                Map.entry(getStatusesForButtonAddToCartIcon(), "There is a status as 'Icon' for button 'Add to cart' but shouldn't "),
                Map.entry(statusesForButton_AddToCart_Number, "There is a status as 'Number of products' for button 'Add to cart' but shouldn't "),
                Map.entry(buttonsAreDisplayedOnHover, "Buttons are displayed when hovering over a product cell but should be displayed at once "),
                Map.entry(text_YouSave_Full, "There is a text 'You save' as 'Full' but shouldn't "),
                Map.entry(text_YouSave_Short, "There is a text 'You save' as 'Short' but shouldn't "),
                Map.entry(productCode, "There are product codes but shouldn't "),
                Map.entry(availabilityStatus, "There are availability statuses but shouldn't "),
                Map.entry(quantityChanger, "There are Quantity changers but shouldn't "),
                Map.entry(showAddToCartButton_IconOnly, "The buttons 'Add to cart' are as 'Icon only' but shouldn't "),
                Map.entry(showAddToCartButton_TextOnly, "The buttons 'Add to cart' are as 'Text only' but shouldn't "),
                Map.entry(brandLogo, "There is a brand logo but shouldn't "),
                Map.entry(brandName, "There is a brand name but shouldn't ")
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

    public void assertNumberOfElements(String list, String selector, int quantity, String location) {
        Map<String, String> selectorMessages = Map.ofEntries(
                Map.entry(numberOfLinesInProductName, "Number of lines in the product name is not " + quantity),
                Map.entry(smallItems_NumberOfLinesInProductName, "Number of lines in the product name is not " + quantity),
                Map.entry(scroller_NumberOfLinesInProductName, "Number of lines in the product name is not " + quantity)

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
}