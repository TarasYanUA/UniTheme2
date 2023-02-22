package taras.adminPanel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import taras.constants.AbstractPage;

public class ThemeSettings_ProductLists extends AbstractPage {
    public ThemeSettings_ProductLists(){super();}

    //Настройки темы, вкладка "Списки товаров"
    @FindBy(css = "#product_list")
    private WebElement tabProductLists;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.show_gallery']")
    public WebElement settingMiniIconsGallery;
    @FindBy(css = "input[id=\"settings.abt__ut2.product_list.decolorate_out_of_stock_products\"]")
    public WebElement settingOutOfStockProducts;
    @FindBy(id = "settings.abt__ut2.product_list.price_display_format")
    private WebElement settingPriceDisplayFormat;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.price_position_top']")
    public WebElement settingPriceAtTheTop;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.show_rating']")
    public WebElement settingProductRating;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.show_rating_num']")
    public WebElement settingCommonValueOfProductRating;
    @FindBy(id = "settings.abt__ut2.product_list.show_cart_status")
    private WebElement settingDisplayCartStatus;
    @FindBy(id = "settings.abt__ut2.product_list.show_favorite_compare_status")
    public WebElement settingDisplayStatusesForButtons;
    @FindBy(id = "settings.abt__ut2.product_list.button_wish_list_view.desktop")
    public WebElement settingDisplayButtonWishList;
    @FindBy(id = "settings.abt__ut2.product_list.button_compare_view.desktop")
    public WebElement settingDisplayButtonComparisonList;
    @FindBy(id = "settings.abt__ut2.product_list.hover_buttons_w_c_q.desktop")
    public WebElement settingDisplayButtonsWhenHoveringMouse;
    @FindBy(css = "select[id='settings.abt__ut2.product_list.products_multicolumns.enable_hover_gallery.desktop']")
    private WebElement settingSwitchProductImageWhenHoveringMousePointer;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.grid_item_height.desktop']")
    private WebElement settingMinHeightForProductCell;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.image_width.desktop']")
    private WebElement settingProductIconWidth;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.image_height.desktop']")
    private WebElement settingProductIconHeight;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.show_sku.desktop']")
    public WebElement settingShowProductCode;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.show_amount.desktop']")
    public WebElement settingDisplayAvailabilityStatus;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.show_qty.desktop']")
    public WebElement settingShowQuantityChanger;
    @FindBy(css = "select[id='settings.abt__ut2.product_list.products_multicolumns.show_button_add_to_cart.desktop']")
    private WebElement settingShowAddToCartButton;
    @FindBy(css = "select[id='settings.abt__ut2.product_list.products_multicolumns.grid_item_bottom_content.desktop']")
    private WebElement settingAdditionalProductInformation;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.show_content_on_hover.desktop']")
    public WebElement settingShowAdditionalInformationOnHover;
    @FindBy(id = "settings.abt__ut2.product_list.products_multicolumns.show_brand_logo.desktop")
    public WebElement settingShowBrandLogo;
    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_brand_logo.desktop")
    public WebElement settingShowBrandLogo_ListWithoutOptions;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.show_you_save.desktop']")
    public WebElement settingShowYouSave;
    @FindBy(css = "select[id='settings.abt__ut2.product_list.products_multicolumns.enable_hover_gallery.desktop']")
    private WebElement settingSwitchProductImageWhenHovering;


    public void clickTabProductLists(){
        tabProductLists.click();
    }

    public Select getSettingPriceDisplayFormat(){
        return new Select(settingPriceDisplayFormat);
    }
    public void selectSettingPriceDisplayFormat(String value){
        getSettingPriceDisplayFormat().selectByValue(value);
    }
    public Select getSettingSwitchProductImageWhenHoveringMousePointer(){
        return new Select(settingSwitchProductImageWhenHoveringMousePointer);
    }
    public void selectSettingSwitchProductImageWhenHoveringMousePointer(String value){
        getSettingSwitchProductImageWhenHoveringMousePointer().selectByValue(value);
    }

    public void clickAndTypeSettingMinHeightForProductCell(String value){
        settingMinHeightForProductCell.click();
        settingMinHeightForProductCell.clear();
        settingMinHeightForProductCell.sendKeys(value);
    }
    public void clickAndTypeSettingProductIconWidth(String value){
        settingProductIconWidth.click();
        settingProductIconWidth.clear();
        settingProductIconWidth.sendKeys(value);
    }
    public void clickAndTypeSettingProductIconHeight(String value){
        settingProductIconHeight.click();
        settingProductIconHeight.clear();
        settingProductIconHeight.sendKeys(value);
    }

    public Select getSettingShowAddToCartButton(){
        return new Select(settingShowAddToCartButton);
    }
    public void selectSettingShowAddToCartButton(String value){
        getSettingShowAddToCartButton().selectByValue(value);
    }
    public Select getSettingAdditionalProductInformation(){
        return new Select(settingAdditionalProductInformation);
    }
    public void selectSettingAdditionalProductInformation(String value){
        getSettingAdditionalProductInformation().selectByValue(value);
    }
    public Select getSettingSwitchProductImageWhenHovering(){
        return new Select(settingSwitchProductImageWhenHovering);
    }
    public void selectSettingSwitchProductImageWhenHovering(String value){
        getSettingSwitchProductImageWhenHovering().selectByValue(value);
    }
    public Select getSettingDisplayCartStatus(){return new Select(settingDisplayCartStatus);}
    public void selectSettingDisplayCartStatus(String value){
        getSettingDisplayCartStatus().selectByValue(value);
    }

    //Настройки для вида списка товаров "Список без опций"
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_without_options.image_width.desktop']")
    private WebElement withoutOptionsIconWidth;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_without_options.image_height.desktop']")
    private WebElement withoutOptionsIconHeight;
    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_sku.desktop")
    public WebElement withoutOptionsProductCode;
    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_amount.desktop")
    public WebElement withoutOptionsAmountStatus;
    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_qty.desktop")
    public WebElement withoutOptionsShowQuantity;
    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.grid_item_bottom_content.desktop")
    private WebElement withoutOptionsContentUnderDescription;
    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_options.desktop")
    public WebElement withoutOptionsShowProductOptions;
    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_brand_logo.desktop")
    public WebElement withoutOptionsBrandLogo;
    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.enable_hover_gallery.desktop")
    private WebElement withoutOptionsHoverGallery;


    public void clickAndTypeWithoutOptionsIconWidth (String value){
        withoutOptionsIconWidth.click();
        withoutOptionsIconWidth.clear();
        withoutOptionsIconWidth.sendKeys(value);
    }
    public void clickAndTypeWithoutOptionsIconHeight (String value){
        withoutOptionsIconHeight.click();
        withoutOptionsIconHeight.clear();
        withoutOptionsIconHeight.sendKeys(value);
    }
    public Select getWithoutOptionsContentUnderDescription(){
        return new Select(withoutOptionsContentUnderDescription);
    }
    public void selectWithoutOptionsContentUnderDescription(String value){
        getWithoutOptionsContentUnderDescription().selectByValue(value);
    }
    public Select getWithoutOptionsHoverGallery(){
        return new Select(withoutOptionsHoverGallery);
    }
    public void selectWithoutOptionsHoverGallery(String value){
        getWithoutOptionsHoverGallery().selectByValue(value);
    }

    //Настройки для вида списка товаров "Компактный список"
    @FindBy(id = "settings.abt__ut2.product_list.short_list.image_width.desktop")
    private WebElement  compactList_productIconWidth;
    @FindBy(id = "settings.abt__ut2.product_list.short_list.image_height.desktop")
    private WebElement compactList_productIconHeight;
    @FindBy(id = "settings.abt__ut2.product_list.short_list.show_sku.desktop")
    public WebElement compactList_productCode;
    @FindBy(id = "settings.abt__ut2.product_list.short_list.show_amount.desktop")
    public WebElement compactList_availabilityStatus;
    @FindBy(id = "settings.abt__ut2.product_list.short_list.show_qty.desktop")
    public WebElement compactList_quantityCharger;
    @FindBy(id = "settings.abt__ut2.product_list.short_list.show_button.desktop")
    public WebElement compactList_buttonAddToCart;

}