package taras.adminPanel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import taras.constants.AbstractPage;

public class ColorSchemeSettings extends AbstractPage {
    public ColorSchemeSettings(){super();}

    @FindBy(css = "a[id^='sw_select_'][id$='_wrap_currency']")
    public WebElement fieldOfActiveColorScheme;
    @FindBy(xpath = "//div[@class=\"language-wrap\"]//a[contains(text(),\"CS-Cart\")]")
    public WebElement activeColorScheme;

    @FindBy(id = "general")
    public WebElement tab_General;
    @FindBy(id = "settings.abt__ut2.general.use_rounding")
    private WebElement setting_General_RoundCornersForElements;
    @FindBy(id = "settings.abt__ut2.general.use_rounding_blocks")
    public WebElement setting_General_RoundCornersOfBlocks;
    @FindBy(id = "settings.abt__ut2.general.use_titles_uppercase")
    public WebElement setting_General_DisplayHeadersInCapitalLetters;
    @FindBy(id = "settings.abt__ut2.general.buttons.style")
    private WebElement setting_General_ButtonsStyle;
    @FindBy(id = "settings.abt__ut2.general.buttons.use_text_uppercase")
    public WebElement setting_General_DisplayTextInCapitalLetters;
    @FindBy(id = "settings.abt__ut2.general.buttons.use_shadow")
    public WebElement setting_General_AddShadow;
    @FindBy(id = "settings.abt__ut2.general.buttons.use_gradient")
    public WebElement setting_General_AddBulk;
    @FindBy(id = "settings.abt__ut2.general.buttons.use_icon_cart")
    private WebElement setting_General_CartIcon;

    private Select getSetting_General_RoundCornersForElements(){return new Select(setting_General_RoundCornersForElements);}
    public void selectSetting_General_RoundCornersForElements (String value){
        getSetting_General_RoundCornersForElements().selectByValue(value);
    }
    private Select getSetting_General_ButtonsStyle(){return new Select(setting_General_ButtonsStyle);}
    public void selectSetting_General_ButtonsStyle (String value){
        getSetting_General_ButtonsStyle().selectByValue(value);
    }
    private Select getSetting_General_CartIcon(){return new Select(setting_General_CartIcon);}
    public void selectSetting_General_CartIcon (String value){
        getSetting_General_CartIcon().selectByValue(value);
    }

    @FindBy(css = ".nav-tabs #product_list")
    public WebElement tab_ProductLists;
    @FindBy(id = "settings.abt__ut2.product_list.show_grid_border")
    private WebElement setting_FrameType;
    @FindBy(id = "settings.abt__ut2.product_list.mask_images_gallery")
    public WebElement setting_ProductLists_MaskForProductImages;
    @FindBy(id = "settings.abt__ut2.product_list.use_elements_alignment")
    private WebElement setting_ProductLists_ElementsAlignment;
    @FindBy(id = "settings.abt__ut2.product_list.extend_grid_item_on_hover")
    public WebElement setting_ProductLists_ExpandGridItemOnHover;
    @FindBy(id = "settings.abt__ut2.product_list.grid-list.product_name_font_weight")
    private WebElement setting_ProductLists_FontWeightForProductName;

    public Select getSetting_FrameType(){return new Select(setting_FrameType);}
    public void selectSetting_FrameType(String value){
        getSetting_FrameType().selectByValue(value);
    }
    public Select getSetting_ProductLists_ElementsAlignment(){return new Select(setting_ProductLists_ElementsAlignment);}
    public void selectSetting_ProductLists_ElementsAlignment(String value){getSetting_ProductLists_ElementsAlignment().selectByValue(value);}
    public Select getSetting_ProductLists_FontWeightForProductName(){return new Select(setting_ProductLists_FontWeightForProductName);}
    public void selectSetting_ProductLists_FontWeightForProductName(String value){getSetting_ProductLists_FontWeightForProductName().selectByValue(value);}

    @FindBy(css = ".nav-tabs #products")
    public WebElement tab_Product;
    @FindBy(id = "settings.abt__ut2.products.mask_images_gallery")
    public WebElement setting_ProductMaskForProductImages;
    @FindBy(id = "settings.abt__ut2.products.bordered_images_gallery")
    public WebElement setting_ProductBorderForProductImages;



/*    public void chooseActiveColorScheme(){
        //fieldOfActiveColorScheme.click();


        for (int i=0; i<listOfColorSchemeNames.size(); i++){
            System.out.println(listOfColorSchemeNames.get(i).getText());

        }
    }*/
}