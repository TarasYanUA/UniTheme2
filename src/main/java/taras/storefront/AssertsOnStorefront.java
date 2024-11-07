package taras.storefront;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.time.Duration;
import java.util.List;

public class AssertsOnStorefront extends AbstractPage {
    public AssertsOnStorefront() {
        super();
    }

    //Настройки темы -- вкладка "Списки товаров"

    String gridList = ".ut2-gl__body";
    String listWithoutOptions = ".ty-product-list";
    String compactList = ".ty-compact-list__content";

    @FindBy(css = ".ut2-gl__body.content-on-hover.decolorize")
    public List<WebElement> decolorizeOutOfStockProducts;   //Настройка "Обесцвечивать товары, которых нет в наличии"

    //Настройка "Отображать пустые звёзды рейтинга товара"
    String emptyStarsOfProductRating = "div[class*='ty-product-review-reviews-stars'][data-ca-product-review-reviews-stars-full=\"0\"]";

    public List<WebElement> emptyStarsOfProductRating(){
        return DriverProvider.getDriver().findElements(By.cssSelector(emptyStarsOfProductRating));
    }

    public List<WebElement> getEmptyStarsOfProductRating(String blockID) {
        return DriverProvider.getDriver().findElements(By.cssSelector(
                "div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] " + emptyStarsOfProductRating));
    }

    //Настройка "Отображать общее значение рейтинга товара"
    String commonValueOfProductRating = ".ut2-show-rating-num";

    public List<WebElement> commonValueOfProductRating(){
        return DriverProvider.getDriver().findElements(By.cssSelector(commonValueOfProductRating));
    }

    public List<WebElement> getCommonValueOfProductRating(String blockID) {
        return DriverProvider.getDriver().findElements(By.cssSelector(
                "div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] " + commonValueOfProductRating));
    }

    //Настройка "Отображать статусы для кнопок "Купить" -- Иконка"
    public String getStatusesForButtonAddToCartIcon() {
        String cssSelector = "button[id*='button_cart']";
        // Ожидаем появления элемента на странице
        WebDriverWait wait = new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(4));
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
        // Выполняем JavaScript для получения стиля псевдоэлемента ::before
        JavascriptExecutor js = (JavascriptExecutor) DriverProvider.getDriver();
        return (String) js.executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", button);
    }

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


    //Настройка "Отображать "Вы экономите -- Полный вид"
    String text_YouSave_Full = "span.ty-save-price";
    public List<WebElement> text_YouSave_Full(){
        return DriverProvider.getDriver().findElements(By.cssSelector(text_YouSave_Full));
    }

    // Динамический поиск по переменной blockID
    public List<WebElement> getText_YouSave_Full(String blockID) {
        return DriverProvider.getDriver().findElements(By.cssSelector(
                "div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] " + text_YouSave_Full));
    }


    //Настройка "Отображать "Вы экономите -- Сокращенный вид"
    String text_YouSave_Short = ".ut2-sld-short span.ty-save-price";

    public List<WebElement> text_YouSave_Short(){
        return DriverProvider.getDriver().findElements(By.cssSelector(text_YouSave_Short));
    }

    public List<WebElement> getText_YouSave_Short(String blockID) {
        return DriverProvider.getDriver().findElements(By.cssSelector(
                "div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] " + text_YouSave_Short));
    }


    String productCode = " div[id*='product_code_']";    //Настройка "Отображать код товара"

    public List<WebElement> productCode(){
        return DriverProvider.getDriver().findElements(By.cssSelector(productCode));
    }

    public List<WebElement> productCode_GridList(){
        return DriverProvider.getDriver().findElements(By.cssSelector(gridList + productCode));
    }

    public List<WebElement> productCode_ListWithoutOptions(){
        return DriverProvider.getDriver().findElements(By.cssSelector(listWithoutOptions + productCode));
    }

    @FindBy(css = ".ty-compact-list__sku")
    public List<WebElement> productCode_CompactList;

    public List<WebElement> getProductCode(String blockID) {
        return DriverProvider.getDriver().findElements(By.cssSelector("div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "']" + productCode));
    }


    String availabilityStatus = " .ty-qty-in-stock.ty-control-group__item"; //Настройка "Отображать статус наличия"

    public List<WebElement> availabilityStatus_GridList(){
        return DriverProvider.getDriver().findElements(By.cssSelector(gridList + availabilityStatus));
    }

    public List<WebElement> availabilityStatus_ListWithoutOptions(){
        return DriverProvider.getDriver().findElements(By.cssSelector(listWithoutOptions + availabilityStatus));
    }

    @FindBy(css = ".ty-compact-list__amount")
    public List<WebElement> availabilityStatus_CompactList;

    // Динамический поиск по переменной blockID
    public List<WebElement> getAvailabilityStatus(String blockID) {
        return DriverProvider.getDriver().findElements(By.cssSelector(
                "div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "']" + availabilityStatus));
    }


   String quantityChanger = " div[class='ty-center ty-value-changer cm-value-changer']";    //Настройка "Отображать модификатор количества"

    public List<WebElement> quantityChanger_GridList(){
        return DriverProvider.getDriver().findElements(By.cssSelector(gridList + quantityChanger));
    }

    public List<WebElement> quantityChanger_ListWithoutOptions(){
        return DriverProvider.getDriver().findElements(By.cssSelector(listWithoutOptions + quantityChanger));
    }

    public List<WebElement> quantityChanger_CompactList(){
        return DriverProvider.getDriver().findElements(By.cssSelector(compactList + quantityChanger));
    }

    public List<WebElement> getQuantityChanger(String blockID) {
        return DriverProvider.getDriver().findElements(By.cssSelector(
                "div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "']" + quantityChanger));
    }


    String showAddToCartButton_IconOnly = " .ut2-icon-use_icon_cart";    //Настройка "Отображать кнопку "Купить" -- Только иконка корзины"

    public List<WebElement> gridList__ShowAddToCartButton_IconOnly(){
        return DriverProvider.getDriver().findElements(By.cssSelector(gridList + showAddToCartButton_IconOnly));
    }

    public List<WebElement> listWithoutOptions__ShowAddToCartButton_IconOnly(){
        return DriverProvider.getDriver().findElements(By.cssSelector(listWithoutOptions + showAddToCartButton_IconOnly));
    }

    public List<WebElement> compactList__ShowAddToCartButton_IconOnly(){
        return DriverProvider.getDriver().findElements(By.cssSelector(compactList + showAddToCartButton_IconOnly));
    }

    public List<WebElement> getShowAddToCartButton_IconOnly(String blockID) {
        return DriverProvider.getDriver().findElements(By.cssSelector(
                "div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "']" + showAddToCartButton_IconOnly));
    }


    String showAddToCartButton_TextOnly = " .ty-btn__primary.ty-btn__add-to-cart.cm-form-dialog-closer";   //Настройка "Отображать кнопку "Купить" -- Только текст"

    public List<WebElement> listWithoutOptions__ShowAddToCartButton_TextOnly(){
        return DriverProvider.getDriver().findElements(By.cssSelector(listWithoutOptions + showAddToCartButton_TextOnly));
    }

    public List<WebElement> compactList__ShowAddToCartButton_TextOnly(){
        return DriverProvider.getDriver().findElements(By.cssSelector(compactList + showAddToCartButton_TextOnly));
    }

    public List<WebElement> getShowAddToCartButton_TextOnly(String blockID) {
        return DriverProvider.getDriver().findElements(By.cssSelector(
                "div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "']" + showAddToCartButton_TextOnly));
    }


    @FindBy(css = "div[class='ut2-gl__body content-on-hover']")
    public List<WebElement> gridList__AdditionalInformationOnHover;   //Настройка "Вид списка "Сетка" -- "Отображать дополнительную информацию при наведении"


    String brandLogo = " .brand-img"; //Настройка "Отображать логотип бренда"

    public List<WebElement> gridList__BrandLogo(){
        return DriverProvider.getDriver().findElements(By.cssSelector(gridList + brandLogo));
    }

    public List<WebElement> listWithoutOptions__BrandLogo(){
        return DriverProvider.getDriver().findElements(By.cssSelector(listWithoutOptions + brandLogo));
    }

    public List<WebElement> getBrandLogo(String blockID) {
        return DriverProvider.getDriver().findElements(By.cssSelector(
                "div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "']" + brandLogo));
    }


    String showStandardImageGallery = " .owl-pagination";   //Настройка "Отображать стандартную галерею изображений -- Навигация точками"

    public List<WebElement> gridList__ShowStandardImageGallery_Dots(){
        return DriverProvider.getDriver().findElements(By.cssSelector(gridList + showStandardImageGallery));
    }

    public List<WebElement> listWithoutOptions__ShowStandardImageGallery(){
        return DriverProvider.getDriver().findElements(By.cssSelector(listWithoutOptions + showStandardImageGallery));
    }


    @FindBy(css = ".ut2-gl__body.content-on-hover .icon-right-circle")
    public List<WebElement> gridList__ShowStandardImageGallery_Arrows;//Настройка "Вид списка "Сетка" -- "Отображать стандартную галерею изображений -- Навигация стрелками"


    @FindBy(css = "div[class='cm-ab-hover-gallery abt__ut2_hover_gallery lines']")
    public List<WebElement> gridList__SwitchProductImage_WithStripes; //Настройка "Вид списка "Сетка" -- "Переключать изображение товара при движении мышки -- с полосками"


    @FindBy(css = ".ut2-lv__item-features")
    public List<WebElement> listWithoutOptions__ContentUnderDescription_VariationList;  //Настройка "Вид списка "Список без опций" -- Содержимое под описанием -- Список вариаций

    @FindBy(css = ".ut2-pl__feature")
    public List<WebElement> listWithoutOptions__ContentUnderDescription_FeatureList;  //Настройка "Вид списка "Список без опций" -- Содержимое под описанием -- Список характеристик


    @FindBy(css = ".cm-picker-product-options")
    public List<WebElement> listWithoutOptions__ShowProductOptions; //Настройка "Вид списка "Список без опций" -- Отображать опции товара"

    @FindBy(css = "div[class='cm-ab-hover-gallery abt__ut2_hover_gallery points']") //Настройка "Вид списка "Список без опций" -- Переключать изображение товара при движении мышки -- С точками
    public List<WebElement> listWithoutOptions__SwitchProductImageWhenHoveringMousePointer_Dots;



    //Настройки темы -- вкладка "Товар"

    @FindBy(css = ".ut2-pb__custom-block")
    public List<WebElement> customBlockID;  //Настройка "ID пользовательского блока"

    @FindBy(css = "div.ty-qty[id*='qty_']")
    public List<WebElement> showQuantityChanger;    //Настройка "Отображать модификатор количества"

    @FindBy(css = ".ut2-pb__sku")
    public List<WebElement> showProductCode;    //Настройка "Отображать код товара"

    @FindBy(css = ".fg-two-col")
    public List<WebElement> showFeaturesInTwoColumns_Enabled;   //Настройка "Отображать характеристики в две колонки" ВКЛ.

    @FindBy(css = "div[class='cm-ab-similar-filter-container ']")
    public List<WebElement> showFeaturesInTwoColumns_Disabled;   //Настройка "Отображать характеристики в две колонки" ОТКЛ.

    @FindBy(css = ".ut2-pb__product-brand-name")
    public List<WebElement> showProductBrandInformation_Name;   //Настройка "Отображать информацию о бренде товара -- Отображать название бренда товара"

    @FindBy(css = ".ut2-pb__product-brand")
    public List<WebElement> showProductBrandInformation_Logo;   //Настройка "Отображать информацию о бренде товара -- Отображать логотип бренда товара"

    @FindBy(css = ".images-2")
    public List<WebElement> numberOfDisplayedImagesOfProductGallery_2;  //Настройка "Количество отображаемых изображений галереи товара -- 2"



    //Настройки -- Общие настройки -- Внешний вид

    //Текст налога "[цена налога] + Вкл налог". Настройка "Показывать цены с налогом на страницах категорий и товаров"
    String pricesWithTaxes = "span[id*='line_product_price_']";

    public List<WebElement> pricesWithTaxes(){
        return DriverProvider.getDriver().findElements(By.cssSelector(pricesWithTaxes));
    }

    public List<WebElement> getPricesWithTaxes(String blockID) {
        return DriverProvider.getDriver().findElements(By.cssSelector(
                "div[id^='content_abt__ut2_grid_tab_'][id$='" + blockID + "'] " + pricesWithTaxes));
    }

    @FindBy(css = ".ty-product-thumbnails_gallery")
    public List<WebElement> miniThumbnailImagesAsGallery_Enabled;   //Настройка "Показывать мини-иконки в виде галереи" ВКЛ.

    @FindBy(css = ".ty-product-thumbnails")
    public List<WebElement> miniThumbnailImages_Disabled;           //Настройка "Показывать мини-иконки в виде галереи" ОТКЛ.

    @FindBy(css = "div[class='ty-tabs cm-j-tabs  clearfix'] ul[class='ty-tabs__list']")
    public List<WebElement> displayProductDetailsInTabs_Enabled;    //Настройка "Показывать информацию о товаре во вкладках" ВКЛ.

    @FindBy(css = ".tab-list-title")
    public List<WebElement> displayProductDetailsInTabs_Disabled;    //Настройка "Показывать информацию о товаре во вкладках" ОТКЛ.

    @FindBy(css = "a[class*='ut2-quick-view-button']")
    public List<WebElement> enableQuickView;    //Настройка "Включить быстрый просмотр"



    //Настройки на странице редактирования товара

    @FindBy(css = ".ut2-pb__note")
    public List<WebElement> product_PromoText;  //Настройка "Промо-текст"

    @FindBy(css = ".ty-price-per-unit")
    public List<WebElement> pricePerUnit;   //Настройка "Цена за единицу"

    @FindBy(css = ".ty-price-curency__input")
    public List<WebElement> zeroPriceAction_AskCustomerToEnterPrice;    //Настройка "Действие при нулевой цене -- Попросить покупателя ввести цену"

    @FindBy(css = ".on_backorder")
    public List<WebElement> outOfStockActions_BuyInAdvance;             //Настройка "Действие при отсутствии товара в наличии -- Предзаказ"

    @FindBy(css = "label[id*='label_sw_product_notify_']")
    public List<WebElement> outOfStockActions_SignUpForNotification;    //Настройка "Действие при отсутствии товара в наличии -- Подписаться на уведомления"

    @FindBy(css = ".ut2-pb__short-descr")
    public List<WebElement> product_ShortDescription;   //Настройка "Краткое описание"

    @FindBy(css = ".ut2-pb__note")
    public List<WebElement> promoText;  //Настройка "Промо текст"


    @FindBy(css = ".ty-reward-group")
    public List<WebElement> product_allowPaymentByPoints;   //Вкладка "Бонусные баллы" -- Настройка "Разрешить оплату баллами"


    //ДРУГОЕ
    @FindBy(xpath = "//div[@class='ty-features-list']//em[text()='Brand']")
    public List<WebElement> showInHeaderOnProductPage_Brand;  //Настройка "Товары -- Характеристики -- Бренд -- Показывать в заголовке карточки товара"

    @FindBy(xpath = "//div[@class='ty-features-list']//em[text()='Hard drive']")
    public List<WebElement> showInHeaderOnProductPage_HardDrive;  //Настройка "Товары -- Характеристики -- Жесткий диск -- Показывать в заголовке карточки товара"

}