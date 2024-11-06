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

    public void clickTabProductLists(){
        tabProductLists.click();
    }

    @FindBy(css = "input[id='settings.abt__ut2.product_list.decolorate_out_of_stock_products']")
    public WebElement settingOutOfStockProducts;
    @FindBy(id = "settings.abt__ut2.product_list.price_display_format")
    private WebElement settingPriceDisplayFormat;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.price_position_top']")
    public WebElement settingPriceAtTheTop;
    @FindBy(css = "input[id='settings.abt__ut2.product_list.show_rating']")
    public WebElement settingEmptyStarsOfProductRating;
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

    @FindBy(id = "settings.abt__ut2.product_list.show_you_save.desktop")
    public WebElement settingShowYouSave;
    public void selectSettingShowYouSave(String value) {
        new Select(settingShowYouSave).selectByValue(value);
    }


    //Настройки для вида списка товаров "Сетка"
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
    @FindBy(css = "select[id='settings.abt__ut2.product_list.products_multicolumns.enable_hover_gallery.desktop']")
    private WebElement settingSwitchProductImageWhenHovering;
    @FindBy(id = "settings.abt__ut2.product_list.products_multicolumns.show_gallery.desktop")
    public WebElement setting_ShowStandardImageGallery_Grid;
    private Select getSetting_ShowStandardImageGallery_Grid(){return new Select(setting_ShowStandardImageGallery_Grid);}
    public void selectSetting_ShowGalleryOfMiniIcons(String value){
        getSetting_ShowStandardImageGallery_Grid().selectByValue(value);
    }

    @FindBy(id = "settings.abt__ut2.product_list.product_variations.allow_variations_selection.desktop")
    public WebElement setting_AllowToSelectVariationsAndOptions;


    private Select getSettingPriceDisplayFormat(){
        return new Select(settingPriceDisplayFormat);
    }
    public void selectSettingPriceDisplayFormat(String value){
        getSettingPriceDisplayFormat().selectByValue(value);
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

    private Select getSettingShowAddToCartButton(){
        return new Select(settingShowAddToCartButton);
    }
    public void selectSettingShowAddToCartButton(String value){
        getSettingShowAddToCartButton().selectByValue(value);
    }

    private Select getSettingAdditionalProductInformation(){
        return new Select(settingAdditionalProductInformation);
    }
    public void selectSettingAdditionalProductInformation(String value){
        getSettingAdditionalProductInformation().selectByValue(value);
    }

    private Select getSettingSwitchProductImageWhenHovering(){
        return new Select(settingSwitchProductImageWhenHovering);
    }
    public void selectSetting_SwitchProductImageWhenHovering(String value){
        getSettingSwitchProductImageWhenHovering().selectByValue(value);
    }

    private Select getSettingDisplayCartStatus(){return new Select(settingDisplayCartStatus);}
    public void selectSettingDisplayCartStatus(String value){
        getSettingDisplayCartStatus().selectByValue(value);
    }


    //Настройки для вида списка товаров "Список без опций"
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_without_options.image_width.desktop']")
    private WebElement withoutOptions_IconWidth;

    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_without_options.image_height.desktop']")
    private WebElement withoutOptions_IconHeight;

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_sku.desktop")
    public WebElement withoutOptions_ProductCode;

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_amount.desktop")
    public WebElement withoutOptions_AmountStatus;

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_qty.desktop")
    public WebElement withoutOptions_ShowQuantity;

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_button_add_to_cart.desktop")
    private WebElement withoutOptions_ShowButtonAddToCart;
    public void selectWithoutOptions_ShowButtonAddToCart(String value) {
        new Select(withoutOptions_ShowButtonAddToCart).selectByValue(value);
    }

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.grid_item_bottom_content.desktop")
    private WebElement withoutOptions_ContentUnderDescription;
    public void selectWithoutOptionsContentUnderDescription(String value){
        new Select(withoutOptions_ContentUnderDescription).selectByValue(value);
    }

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_options.desktop")
    public WebElement withoutOptions_ShowProductOptions;

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_brand_logo.desktop")
    public WebElement withoutOptions_BrandLogo;

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_gallery.desktop")
    public WebElement withoutOptions_ShowStandardImageGallery;
    public void selectWithoutOptions_ShowStandardImageGallery(String value){
        new Select(withoutOptions_ShowStandardImageGallery).selectByValue(value);
    }

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.enable_hover_gallery.desktop")
    private WebElement withoutOptions_SwitchProductImageWhenHovering;
    public void selectWithoutOptions_SwitchProductImageWhenHovering(String value){
        new Select(withoutOptions_SwitchProductImageWhenHovering).selectByValue(value);
    }

    public void clickAndTypeWithoutOptionsIconWidth (String value){
        withoutOptions_IconWidth.click();
        withoutOptions_IconWidth.clear();
        withoutOptions_IconWidth.sendKeys(value);
    }
    public void clickAndTypeWithoutOptionsIconHeight (String value){
        withoutOptions_IconHeight.click();
        withoutOptions_IconHeight.clear();
        withoutOptions_IconHeight.sendKeys(value);
    }


    //Настройки для вида списка товаров "Компактный список"
    @FindBy(id = "settings.abt__ut2.product_list.short_list.show_sku.desktop")
    public WebElement compactList_productCode;

    @FindBy(id = "settings.abt__ut2.product_list.short_list.show_amount.desktop")
    public WebElement compactList_availabilityStatus;

    @FindBy(id = "settings.abt__ut2.product_list.short_list.show_qty.desktop")
    public WebElement compactList_quantityChanger;

    @FindBy(id = "settings.abt__ut2.product_list.short_list.show_button_add_to_cart.desktop")
    private WebElement compactList_buttonAddToCart;
    public void selectCompactList_buttonAddToCart (String value){
        new Select(compactList_buttonAddToCart).selectByValue(value);
    }


    //Настройки для вида списка товаров "Мелкие элементы"
    @FindBy(id = "settings.abt__ut2.product_list.small_items.lines_number_in_name_product.desktop")
    public WebElement smallItems_NumberOfLinesInProductName;
    public void selectSmallItems_NumberOfLinesInProductName(String value) {
        new Select(smallItems_NumberOfLinesInProductName).selectByValue(value);
    }

    @FindBy(id = "settings.abt__ut2.product_list.small_items.show_sku.desktop")
    public WebElement smallItems_ProductCode;

    @FindBy(id = "settings.abt__ut2.product_list.small_items.show_amount.desktop")
    public WebElement smallItems_AvailabilityStatus;

    @FindBy(id = "settings.abt__ut2.product_list.small_items.show_qty.desktop")
    public WebElement smallItems_QuantityChanger;

    @FindBy(id = "settings.abt__ut2.product_list.small_items.show_button_add_to_cart.desktop")
    private WebElement smallItems_AddToCartButton;
    public void selectSmallItems_AddToCartButton (String value){
        new Select(smallItems_AddToCartButton).selectByValue(value);
    }
}