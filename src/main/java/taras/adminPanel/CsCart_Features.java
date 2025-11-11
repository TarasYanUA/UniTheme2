package taras.adminPanel;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class CsCart_Features extends AbstractPage {
    public CsCart_Features() {
        super();
    }

    //Меню "Товары -- Характеристики"
    @FindBy(id = "products_features")
    WebElement section_Features;

    @FindBy(css = "a[data-ca-external-click-id=\"opener_group18\"]")
    WebElement featureBrand;

    @FindBy(css = "a[data-ca-external-click-id=\"opener_group23\"]")
    public WebElement feature_HardDrive;

    @FindBy(css = "a[data-ca-external-click-id=\"opener_group549\"]")
    WebElement featureColor;

    @FindBy(css = "label[for=\"elm_feature_purpose_549_group_variation_catalog_item\"] input")
    WebElement setting_VariationsAsOneProduct;

    @FindBy(id = "elm_feature_feature_style_549")
    WebElement setting_FeatureStyle;

    @FindBy(id = "elm_feature_filter_style_549")
    WebElement setting_FilterType;

    @FindBy(css = "label[for='elm_feature_description_23']")
    WebElement field_FeatureDescription_HardDrive;

    @FindBy(css = ".re-button.re-html.re-button-icon")
    WebElement button_Html_HardDrive;

    @FindBy(css = ".cm-skip-check-item.open")
    WebElement field_HtmlDescriptionOfFeature;

    @FindBy(css = "input[id='elm_feature_display_on_catalog_18']")
    public WebElement showInProductList;

    @FindBy(css = "input[id='elm_feature_display_on_catalog_549']")
    public WebElement showInProductList_Color;

    @FindBy(id = "elm_feature_display_on_product_18")
    public WebElement showOnFeaturesTab_Brand;

    @FindBy(id = "elm_feature_display_on_header_23")
    public WebElement showInHeaderOnProductPage_HardDisk;

    @FindBy(css = ".buttons-container-picker input[value='Сохранить']")
    public WebElement button_SaveFeature;

    @FindBy(css = ".buttons-container-picker input[name='dispatch[product_features.update]']")
    WebElement saveColorFeatureSettings;

    @FindBy(id = "tab_feature_variants_549")
    WebElement tab_Variants;

    @FindBy(css = "button[id='549'].variants-list__btn-add")
    WebElement button_AddVariantList;


    public void clickAndTypeField_DescriptionOfFeature(String value) {
        field_FeatureDescription_HardDrive.click();
        new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(field_FeatureDescription_HardDrive));
        UtilsAdm.scrollToElementAndBelow(field_FeatureDescription_HardDrive, 50);
        button_Html_HardDrive.click();
        UtilsAdm.clickAndType(field_HtmlDescriptionOfFeature, value);
    }

    public void clickFeatureBrand() {
        featureBrand.click();
    }

    public void setFeatureColorForVariations() {
        featureColor.click();
        UtilsAdm.waitForPopUpWindow();
        setting_VariationsAsOneProduct.click();
        new Select(setting_FeatureStyle).selectByValue("dropdown_images");
        new Select(setting_FilterType).selectByValue("color");
        UtilsAdm.setCheckboxState(showInProductList_Color, true);
        tab_Variants.click();

        setColorIfDefault("Green", "#00ff00");
        setColorIfDefault("Blue", "#0000ff");
        setColorIfDefault("Black", "#000000");
        setColorIfDefault("White", "#f3f3f3");
        tab_Variants.click();
        button_AddVariantList.click();
        setNewColor("Red", "#ff0000");
        setNewColor("Pink", "#ff00ff");
        setNewColor("Yellow", "#ffff00");

        saveColorFeatureSettings.click();

        setMulticolor("Red-Black", "#ff0000", "#000000");
        setMulticolor("Blue-Yellow", "#4a86e8", "#ffff00");

        setThumbnail("Ceramic tiles", "https://i.artfile.ru/s/20946_250911_41_ArtFile_ru.jpg");
        setThumbnail("Marbled", "https://i.artfile.ru/s/222879_130313_96_ArtFile_ru.jpg");
        setThumbnail("Tigrine", "https://i.artfile.ru/s/282194_011011_53_ArtFile_ru.jpg");
    }

    void setColorIfDefault(String color, String dataColor) {
        String actualColor = "//input[@value='" + color + "']/..";

        if (!DriverProvider.getDriver().findElements(By
                .xpath(actualColor + "//div[@style='background-color: rgb(255, 255, 255);']")
        ).isEmpty()) {
            DriverProvider.getDriver().findElement(By.xpath(actualColor + "//div[@class=\"sp-dd\"]")).click();
            selectColor(dataColor);
        }
    }

    void selectColor(String dataColor) {
        List<WebElement> allPaletteContainers = DriverProvider.getDriver()
                .findElements(By.cssSelector(".sp-palette span[title='" + dataColor + "']"));

        if (allPaletteContainers.isEmpty()) {
            throw new NoSuchElementException("No palette containers found for color: " + dataColor);
        }

        WebElement targetColor = DriverProvider.getDriver().findElement(By.xpath(
                "//div[contains(@class, 'sp-active')]/..//div[@class='sp-palette-container']//span[@title='" + dataColor + "']"));
        new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(targetColor)).click();

        WebElement buttonChoose = DriverProvider.getDriver().findElement(By
                .xpath("//div[contains(@class, 'sp-active')]/..//button[@class='sp-choose']"));
        buttonChoose.click();
    }

    void setNewColor(String color, String dataColor) {
        String lastPicker = "//input[@id='feature_value_color_picker_" + findLastColorPickerNumber() + "']/..";
        DriverProvider.getDriver().findElement(By.xpath(lastPicker + "//div[@class='sp-dd']")).click();
        selectColor(dataColor);

        WebElement fieldName = DriverProvider.getDriver().findElement(By
                .xpath(lastPicker + "/../..//input[contains(@name, '[variant]')]"));
        UtilsAdm.clickAndType(fieldName, color);

        DriverProvider.getDriver().findElement(By
                .xpath("//tr[@id='extra_feature_549_" + findLastColorPickerNumber() + "']/..//a[@name='add']")).click();
    }

    @Nullable
    private String findLastColorPickerNumber() {
        List<WebElement> colorPickers = DriverProvider.getDriver()
                .findElements(By.cssSelector("input[id*='feature_value_color_picker_']"));

        colorPickers.sort(Comparator.comparing(el -> {
            String id = el.getAttribute("id");
            String numberPart = id.replaceAll(".*feature_value_color_picker_", ""); // получаем всё после 'feature_value_color_picker_'
            return Long.parseLong(numberPart.split("_")[0]); // сортируем по первой части числа
        }));

        String lastId = colorPickers.getLast().getAttribute("id");
        return lastId.replaceAll(".*feature_value_color_picker_", ""); // извлекаем все числа после 'feature_value_color_picker_'
    }

    void setMulticolor(String color, String dataColorOne, String dataColorTwo) {
        featureColor.click();
        UtilsAdm.waitForPopUpWindow();
        tab_Variants.click();
        button_AddVariantList.click();

        String lastPicker = "//tbody[@id='box_add_variants_for_existing_549']";

        new Select(DriverProvider.getDriver().findElement(By
                .xpath(lastPicker + "//select[contains(@name, '[abt__ut2_color_style]')]")))
                .selectByValue("multicolor");

        WebElement firstColorPicker = DriverProvider.getDriver().findElement(By
                .xpath(lastPicker + "//div[contains(@class, 'first-color')]//div[@class='sp-dd']"));
        firstColorPicker.click();
        selectColor(dataColorOne);

        WebElement secondColorPicker = DriverProvider.getDriver().findElement(By
                .xpath(lastPicker + "//div[contains(@class, 'second-color')]//div[@class='sp-dd']"));
        secondColorPicker.click();
        selectColor(dataColorTwo);

        WebElement fieldName = DriverProvider.getDriver().findElement(By
                .xpath(lastPicker + "//input[contains(@name, '[variant]')]"));
        UtilsAdm.clickAndType(fieldName, color);

        saveColorFeatureSettings.click();
    }

    void setThumbnail(String name, String imageUrl) {
        featureColor.click();
        UtilsAdm.waitForPopUpWindow();
        tab_Variants.click();
        button_AddVariantList.click();

        String lastPicker = "//tbody[@id='box_add_variants_for_existing_549']";

        WebElement fieldName = DriverProvider.getDriver().findElement(By
                .xpath(lastPicker + "//input[contains(@name, '[variant]')]"));
        UtilsAdm.clickAndType(fieldName, name);

        new Select(DriverProvider.getDriver().findElement(By
                .xpath(lastPicker + "//select[contains(@name, '[abt__ut2_color_style]')]")))
                .selectByValue("thumbnail");

        WebElement button_Url = DriverProvider.getDriver().findElement(By
                .xpath(lastPicker + "//a[contains(@id, 'url_')]"));
        button_Url.click();
        WebDriverWait wait = new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(8));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(imageUrl);
        alert.accept();

        saveColorFeatureSettings.click();
    }
}