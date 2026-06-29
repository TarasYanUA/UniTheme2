package taras.adminPanel.themeSettings;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.adminPanel.UtilsAdm;
import taras.constants.AbstractPage;

public class ThemeSettings_ProductLists extends AbstractPage {
    public ThemeSettings_ProductLists() {
        super();
    }

    //Настройки темы, вкладка "Списки товаров"
    @FindBy(css = "#product_list")
    public WebElement tabProductLists;

    @FindBy(css = "input[id='settings.abt__ut2.product_list.decolorate_out_of_stock_products']")
    public WebElement setting_OutOfStockProducts;

    @FindBy(id = "settings.abt__ut2.product_list.price_display_format")
    public WebElement setting_PriceDisplayFormat;

    @FindBy(css = "input[id='settings.abt__ut2.product_list.price_position_top']")
    public WebElement setting_PriceAtTheTop;

    @FindBy(css = "input[id='settings.abt__ut2.product_list.show_rating']")
    public WebElement setting_EmptyStarsOfProductRating;

    @FindBy(css = "input[id='settings.abt__ut2.product_list.show_rating_num']")
    public WebElement setting_CommonValueOfProductRating;

    @FindBy(id = "settings.abt__ut2.product_list.show_cart_status")
    public WebElement setting_DisplayCartStatus;

    @FindBy(id = "settings.abt__ut2.product_list.show_favorite_compare_status")
    public WebElement setting_DisplayStatusesForButtons;

    @FindBy(id = "settings.abt__ut2.product_list.button_wish_list_view.desktop")
    public WebElement setting_DisplayButtonWishList;

    @FindBy(id = "settings.abt__ut2.product_list.button_compare_view.desktop")
    public WebElement setting_DisplayButtonComparisonList;

    @FindBy(id = "settings.abt__ut2.product_list.hover_buttons_w_c_q.desktop")
    public WebElement setting_DisplayButtonsWhenHoveringMouse;

    @FindBy(id = "settings.abt__ut2.product_list.show_you_save.desktop")
    public WebElement setting_ShowYouSave;


    //Настройки для вида списка товаров "Сетка"
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.image_width.desktop']")
    public WebElement setting_ProductIconWidth;

    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.image_height.desktop']")
    public WebElement setting_ProductIconHeight;

    @FindBy(id = "settings.abt__ut2.product_list.products_multicolumns.lines_number_in_name_product.desktop")
    public WebElement grid_NumberOfLinesInProductName;

    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.show_sku.desktop']")
    public WebElement setting_ShowProductCode;

    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.show_amount.desktop']")
    public WebElement setting_DisplayAvailabilityStatus;

    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.show_qty.desktop']")
    public WebElement setting_ShowQuantityChanger;

    @FindBy(css = "select[id='settings.abt__ut2.product_list.products_multicolumns.show_button_add_to_cart.desktop']")
    public WebElement setting_ShowAddToCartButton;

    @FindBy(css = "select[id='settings.abt__ut2.product_list.products_multicolumns.grid_item_bottom_content.desktop']")
    public WebElement setting_AdditionalProductInformation;

    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_multicolumns.show_content_on_hover.desktop']")
    public WebElement setting_ShowAdditionalInformationOnHover;

    @FindBy(id = "settings.abt__ut2.product_list.products_multicolumns.show_brand.desktop")
    public WebElement setting_ShowBrand;

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_brand.desktop")
    public WebElement setting_ShowBrandLogo_ListWithoutOptions;

    @FindBy(css = "select[id='settings.abt__ut2.product_list.products_multicolumns.enable_hover_gallery.desktop']")
    public WebElement setting_SwitchProductImageWhenHovering;

    @FindBy(id = "settings.abt__ut2.product_list.products_multicolumns.show_gallery.desktop")
    public WebElement setting_ShowStandardImageGallery_Grid;

    @FindBy(id = "settings.abt__ut2.product_list.product_variations.allow_variations_selection.desktop")
    public WebElement setting_AllowToSelectVariationsAndOptions;


    //Настройки для вида списка товаров "Список без опций"
    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_without_options.image_width.desktop']")
    public WebElement withoutOptions_IconWidth;

    @FindBy(css = "input[id='settings.abt__ut2.product_list.products_without_options.image_height.desktop']")
    public WebElement withoutOptions_IconHeight;

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_sku.desktop")
    public WebElement withoutOptions_ProductCode;

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_amount.desktop")
    public WebElement withoutOptions_AmountStatus;

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_qty.desktop")
    public WebElement withoutOptions_ShowQuantity;

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_button_add_to_cart.desktop")
    public WebElement withoutOptions_ShowButtonAddToCart;

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.grid_item_bottom_content.desktop")
    public WebElement withoutOptions_ContentUnderDescription;

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_options.desktop")
    public WebElement withoutOptions_ShowProductOptions;

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_brand_logo.desktop")
    public WebElement withoutOptions_BrandLogo;

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.show_gallery.desktop")
    public WebElement withoutOptions_ShowStandardImageGallery;

    @FindBy(id = "settings.abt__ut2.product_list.products_without_options.enable_hover_gallery.desktop")
    public WebElement withoutOptions_SwitchProductImageWhenHovering;


    //Настройки для вида списка товаров "Компактный список"
    @FindBy(id = "settings.abt__ut2.product_list.short_list.show_sku.desktop")
    public WebElement compactList_productCode;

    @FindBy(id = "settings.abt__ut2.product_list.short_list.show_amount.desktop")
    public WebElement compactList_availabilityStatus;

    @FindBy(id = "settings.abt__ut2.product_list.short_list.show_qty.desktop")
    public WebElement compactList_quantityChanger;

    @FindBy(id = "settings.abt__ut2.product_list.short_list.show_button_add_to_cart.desktop")
    public WebElement compactList_buttonAddToCart;


    //Настройки для вида списка товаров "Мелкие элементы"
    @FindBy(id = "settings.abt__ut2.product_list.small_items.lines_number_in_name_product.desktop")
    public WebElement smallItems_NumberOfLinesInProductName;

    @FindBy(id = "settings.abt__ut2.product_list.small_items.show_sku.desktop")
    public WebElement smallItems_ProductCode;

    @FindBy(id = "settings.abt__ut2.product_list.small_items.show_amount.desktop")
    public WebElement smallItems_AvailabilityStatus;

    @FindBy(id = "settings.abt__ut2.product_list.small_items.show_qty.desktop")
    public WebElement smallItems_QuantityChanger;

    @FindBy(id = "settings.abt__ut2.product_list.small_items.show_button_add_to_cart.desktop")
    public WebElement smallItems_AddToCartButton;


    //Настройки для вида списка товаров "Скроллер"
    @FindBy(id = "settings.abt__ut2.product_list.products_scroller.lines_number_in_name_product.desktop")
    public WebElement scroller_NumberOfLinesInProductName;

    @FindBy(id = "settings.abt__ut2.product_list.products_scroller.show_amount.desktop")
    public WebElement scroller_AvailabilityStatus;

    @FindBy(id = "settings.abt__ut2.product_list.products_scroller.show_qty.desktop")
    public WebElement scroller_QuantityChanger;

    @FindBy(id = "settings.abt__ut2.product_list.products_scroller.show_quick_view_button.desktop")
    public WebElement scroller_QuickViewButton;

    @FindBy(id = "settings.abt__ut2.product_list.products_scroller.show_button_add_to_cart.desktop")
    public WebElement scroller_AddToCartButton;


    //Настройки для модуля "Вариации товаров"
    @FindBy(id = "settings.abt__ut2.product_list.product_variations.limit")
    WebElement productVariations_MaximumQuantityOfProductsVariations;

    @FindBy(id = "settings.abt__ut2.product_list.product_variations.display_color_separately")
    public WebElement productVariations_TypeOfVariationsView;


    public void setProductVariations_MaximumQuantityOfProductsVariations(String number) {
        UtilsAdm.scrollIntoCenter(productVariations_MaximumQuantityOfProductsVariations);
        UtilsAdm.clickAndType(productVariations_MaximumQuantityOfProductsVariations, number);
    }
}