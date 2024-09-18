package taras.storefront;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;

import java.util.List;

public class AssertsOnStorefront extends AbstractPage {
    public AssertsOnStorefront(){super();}

    //Настройки темы -- вкладка "Списки товаров"

    @FindBy(css = ".ut2-add-to-wish")
    public List<WebElement> button_AddToWishList;

    @FindBy(css = ".ut2-add-to-compare")
    public List<WebElement> button_AddToComparisonList;

    @FindBy(css = ".ut2-w-c-q__buttons.w_c_q-hover")
    public List<WebElement> buttonsAreDisplayedOnHover;  //Настройка "Отображать кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" при наведении на ячейку товара"

    @FindBy(css = "span.ty-save-price")
    public List<WebElement> text_YouSave;  //Настройка "Отображать "Вы экономите"

    @FindBy(css = ".ty-control-group__label")
    public List<WebElement> productCode;
    
    @FindBy(css = ".ty-qty-in-stock.ty-control-group__item")
    public List<WebElement> availabilityStatus;  //Настройка "Отображать статус наличия"
    
    @FindBy(css = "div[class='ty-center ty-value-changer cm-value-changer']")
    public List<WebElement> quantityChanger;    //Настройка "Отображать модификатор количества"
    
    @FindBy(css = "div[class='ut2-gl__body content-on-hover']")
    public List<WebElement> additionalInformationOnHover;  //Настройка "Отображать дополнительную информацию при наведении"

    @FindBy(css = ".brand-img")
    public List<WebElement> brandLogo;

    @FindBy(css = "div[class='cm-ab-hover-gallery abt__ut2_hover_gallery lines']")
    public List<WebElement> switchProductImage_WithStripes; //Настройка "Переключать изображение товара при движении мышки -- с полосками"
    
    
    
    
    
    
    @FindBy(css = "span[id*='line_product_price_']")
    public List<WebElement> text_Tax; //Текст налога "[цена налога] + Вкл налог"
}