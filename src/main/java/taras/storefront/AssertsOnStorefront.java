package taras.storefront;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.util.List;

public class AssertsOnStorefront extends AbstractPage {
    public AssertsOnStorefront() {
        super();
    }

    //Настройки темы -- вкладка "Списки товаров"

    @FindBy(css = ".ut2-gl__body.content-on-hover.decolorize")
    public List<WebElement> decolorizeOutOfStockProducts;   //Настройка "Обесцвечивать товары, которых нет в наличии"

    @FindBy(css = "div[class*='ty-product-review-reviews-stars'][data-ca-product-review-reviews-stars-full=\"0\"]")
    public List<WebElement> emptyStarsOfProductRating;  //Настройка "Отображать пустые звёзды рейтинга товара"

    @FindBy(css = ".ut2-show-rating-num")
    public List<WebElement> commonValueOfProductRating; //Настройка "Отображать общее значение рейтинга товара"

    @FindBy(css = "a.ut2-added-to-cart")
    //Устарело. Смотреть в текст-кейсе "GeneralSettings_ColorScheme_ProductLists_AllCategoryLists_Var1"
    public List<WebElement> statusesForButton_AddToCart_Icon;   //Настройка "Отображать статусы для кнопок "Купить" -- Иконка"

    @FindBy(css = ".ut2-added-to-cart[data-added-amount='1']")
    public List<WebElement> statusesForButton_AddToCart_Number; //Настройка "Отображать статусы для кнопок "Купить" -- Количество товаров"

    @FindBy(css = "a.ut2-add-to-wish.active")
    public List<WebElement> statusesForButton_AddToWishList;        //Настройка "Отображать статусы для кнопок... "Добавить в избранное"

    @FindBy(css = "a.ut2-add-to-wish.active")
    public List<WebElement> statusesForButton_AddToComparisonList;  //Настройка "Отображать статусы для кнопок... "Добавить в список сравнения"

    @FindBy(css = ".ut2-add-to-wish")
    public List<WebElement> button_AddToWishList;       //Настройка "Отображать кнопку "Добавить в избранное""

    @FindBy(css = ".ut2-add-to-compare")
    public List<WebElement> button_AddToComparisonList; //Настройка "Отображать кнопку "Добавить в список сравнения""

    @FindBy(css = ".ut2-w-c-q__buttons.w_c_q-hover")
    public List<WebElement> buttonsAreDisplayedOnHover; //Настройка "Отображать кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" при наведении на ячейку товара"

    @FindBy(css = "span.ty-save-price")
    public List<WebElement> text_YouSave_Full;       //Настройка "Отображать "Вы экономите -- Полный вид"

    @FindBy(css = ".ut2-sld-short span.ty-save-price")
    public List<WebElement> text_YouSave_Short;       //Настройка "Отображать "Вы экономите -- Сокращенный вид"

    @FindBy(css = "label[id*='sku_']")
    public List<WebElement> productCode;        //Настройка "Отображать код товара"

    @FindBy(css = ".ty-qty-in-stock.ty-control-group__item")
    public List<WebElement> availabilityStatus; //Настройка "Отображать статус наличия"

    // Динамический поиск по переменной blockID
    public List<WebElement> getAvailabilityStatus(String blockID) {
        return DriverProvider.getDriver().findElements(By.cssSelector(
                "div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] .ty-qty-in-stock.ty-control-group__item"));
    }

    @FindBy(css = "div[class='ty-center ty-value-changer cm-value-changer']")
    public List<WebElement> quantityChanger;    //Настройка "Отображать модификатор количества"

    @FindBy(css = ".ut2-icon-use_icon_cart")
    public List<WebElement> gridList__ShowAddToCartButton_IconOnly; //Настройка "Вид списка "Сетка" -- Отображать кнопку "Купить" -- Только иконка корзины

    @FindBy(css = ".ty-btn__primary.ty-btn__add-to-cart.cm-form-dialog-closer")
    public List<WebElement> gridList__ShowAddToCartButton_TextOnly; //Настройка "Вид списка "Сетка" -- Отображать кнопку "Купить" -- Только текст

    @FindBy(css = "div[class='ut2-gl__body content-on-hover']")
    public List<WebElement> additionalInformationOnHover;   //Настройка "Отображать дополнительную информацию при наведении"

    @FindBy(css = ".brand-img")
    public List<WebElement> brandLogo;          //Настройка "Отображать логотип бренда"

    @FindBy(css = ".owl-pagination")
    public List<WebElement> galleryOgMiniIcons_Dots;    //Настройка "Отображать стандартную галерею изображений -- Навигация точками"

    @FindBy(css = ".ut2-gl__body.content-on-hover .icon-right-circle")
    public List<WebElement> galleryOgMiniIcons_Arrows;  //Настройка "Отображать стандартную галерею изображений -- Навигация стрелками"

    @FindBy(css = "div[class='cm-ab-hover-gallery abt__ut2_hover_gallery lines']")
    public List<WebElement> switchProductImage_WithStripes; //Настройка "Переключать изображение товара при движении мышки -- с полосками"


    //Настройки темы -- вкладка "Товар"

    @FindBy(css = ".ut2-pb__product-brand-name")
    public List<WebElement> showProductBrandInformation_Name;   //Настройка "Отображать информацию о бренде товара -- Отображать название бренда товара"

    @FindBy(css = ".images-2")
    public List<WebElement> numberOfDisplayedImagesOfProductGallery_2;  //Настройка "Количество отображаемых изображений галереи товара"


    //Настройки -- Общие настройки -- Внешний вид

    @FindBy(css = "span[id*='line_product_price_']")
    public List<WebElement> pricesWithTaxes; //Текст налога "[цена налога] + Вкл налог". Настройка "Показывать цены с налогом на страницах категорий и товаров"

    @FindBy(css = ".ty-icon-right-open-thin")
    public List<WebElement> miniThumbnailImagesAsGallery;   //Настройка "Показывать мини-иконки в виде галереи" ВКЛ.

    @FindBy(css = ".ty-product-thumbnails")
    public List<WebElement> miniThumbnailImages_NotGallery; //Настройка "Показывать мини-иконки в виде галереи" ОТКЛ.


    //Настройки на странице редактирования товара

    @FindBy(css = ".ut2-pb__short-descr")
    public List<WebElement> product_ShortDescription;    //Настройка "Краткое описание"

    @FindBy(css = ".ut2-pb__note")
    public List<WebElement> product_PromoText;          //Настройка "Промо-текст"

    @FindBy(css = ".ty-reward-group")
    public List<WebElement> product_allowPaymentByPoints;   //Вкладка "Бонусные баллы" -- Настройка "Разрешить оплату баллами"

}