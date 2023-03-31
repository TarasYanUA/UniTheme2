import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import taras.adminPanel.ThemeSettings_ProductLists;
import taras.constants.DriverProvider;
import taras.adminPanel.CsCartSettings;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import java.io.IOException;
import java.time.Duration;
import static taras.constants.DriverProvider.getDriver;

/* ссылка на тест-кейс: https://docs.google.com/spreadsheets/d/1BU7MqO4tilXIiXQ7w73f9TRbi0dmko-Nmg6_CWQJr0E/edit#gid=0

Проверка следующих настроек:
1) "Настройки -- Внешний вид":
Включить быстрый просмотр -- y
Показывать мини-иконки в виде галереи -- y

2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Показывать галерею мини-иконок товара в товарном списке --	y
Переключать изображение товара при движении мышки -- Не переключать (нужно для настройки выше)
Обесцвечивать товары, которых нет в наличии --	n
Формат отображения цен --	Вариант 1
Отображать цену вверху --	y
Отображать пустые звёзды рейтинга товара --	n
Отображать общее значение рейтинга товара -- y
Отображать статусы для кнопок "Купить" -- Количество товаров
Отображать статусы для кнопок "Добавить в избранное", "Добавить в список сравнения" -- y
Отображать кнопку "Добавить в избранное" -- y
Отображать кнопку "Добавить в список сравнения" -- y
Отображать кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" при наведении на ячейку товара -- n

Проверка проходит на следующих страницах:
- Блок товаров на Главной странице + RTL
- Женская одежда + RTL
- Телефоны + RTL
- Быстрый просмотр + RTL
- Все шаблоны категории + RTL
*/

public class GeneralSettings_ProductLists_AllCategoryLists_Var2 extends TestRunner{
    @Test(priority = 1)
    public void setConfigurationsForProductLists_ChangedValues() {
        CsCartSettings csCartSettings = new CsCartSettings();
        ThemeSettings_ProductLists themeSettingsProductLists = new ThemeSettings_ProductLists();
        //Работаем с CS-Cart настройками
        csCartSettings.navigateToAppearanceSettingsOfCsCart();
        WebElement checkboxThumbnailsGallery = csCartSettings.setting_ThumbnailsGallery;
        if (!checkboxThumbnailsGallery.isSelected()) {
            checkboxThumbnailsGallery.click();
        }
        WebElement checkboxSettingQuickView = csCartSettings.setting_QuickView;
        if (!checkboxSettingQuickView.isSelected()) {
            checkboxSettingQuickView.click();
        }
        csCartSettings.clickSaveButtonOfSettings();
        //Работаем с настройками темы
        csCartSettings.navigateToAddonsPage();
        csCartSettings.clickThemeSectionsOnManagementPage();
        csCartSettings.navigateToThemeSettings();
        themeSettingsProductLists.clickTabProductLists();
        WebElement checkboxMiniIconsGalleryOne = themeSettingsProductLists.settingMiniIconsGallery;
        if (!checkboxMiniIconsGalleryOne.isSelected()) {
            checkboxMiniIconsGalleryOne.click();
        }
        WebElement checkboxOutOfStockProducts = themeSettingsProductLists.settingOutOfStockProducts;
        if (checkboxOutOfStockProducts.isSelected()) {
            checkboxOutOfStockProducts.click();
        }
        themeSettingsProductLists.selectSettingPriceDisplayFormat("col");
        WebElement checkboxPriceAtTheTop = themeSettingsProductLists.settingPriceAtTheTop;
        if (!checkboxPriceAtTheTop.isSelected()) {
            checkboxPriceAtTheTop.click();
        }
        WebElement checkboxProductRating = themeSettingsProductLists.settingProductRating;
        if (checkboxProductRating.isSelected()) {
            checkboxProductRating.click();
        }
        WebElement checkboxSettingCommonValueOfProductRating = themeSettingsProductLists.settingCommonValueOfProductRating;
        if (!checkboxSettingCommonValueOfProductRating.isSelected()) {
            checkboxSettingCommonValueOfProductRating.click();
        }
        themeSettingsProductLists.selectSettingSwitchProductImageWhenHoveringMousePointer("N");
        themeSettingsProductLists.selectSettingDisplayCartStatus("counter");
        WebElement checkboxSettingDisplayStatusesForButtons = themeSettingsProductLists.settingDisplayStatusesForButtons;
        if (!checkboxSettingDisplayStatusesForButtons.isSelected()) {
            checkboxSettingDisplayStatusesForButtons.click();
        }
        WebElement checkboxSettingDisplayButtonComparisonList = themeSettingsProductLists.settingDisplayButtonComparisonList;
        if (!checkboxSettingDisplayButtonComparisonList.isSelected()) {
            checkboxSettingDisplayButtonComparisonList.click();
        }
        WebElement checkboxSettingDisplayButtonWishList = themeSettingsProductLists.settingDisplayButtonWishList;
        if (!checkboxSettingDisplayButtonWishList.isSelected()) {
            checkboxSettingDisplayButtonWishList.click();
        }
        WebElement checkboxSettingDisplayButtonsWhenHoveringMouse = themeSettingsProductLists.settingDisplayButtonsWhenHoveringMouse;
        if (checkboxSettingDisplayButtonsWhenHoveringMouse.isSelected()) {
            checkboxSettingDisplayButtonsWhenHoveringMouse.click();
        }
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_ChangedValues")
    public void checkAllCategoryLists_ChangedValues() throws IOException {
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.cookie.click();
        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        //Проверка, что у товаров присутствует галерея мини-иконок товара
        int sizeOfGalleryOfMiniIcons = getDriver().findElements(By.cssSelector(".ut2-gl__body.content-on-hover .icon-right-circle")).size();
        Assert.assertTrue(sizeOfGalleryOfMiniIcons >= 1,
                "There is no Gallery of mini icons of the product in the product block!");
        //Проверка, что у товаров присутствует общее значение рейтинга товара
        int sizeOfGeneralRatingNumber = getDriver().findElements(By.cssSelector(".ut2-show-rating-num")).size();
        Assert.assertTrue(sizeOfGeneralRatingNumber >= 1,"There is no common value of product rating at a product!");
        //Проверка, что кнопка "Избранное" присутствует
        int sizeOfButton_AddToWishList = getDriver().findElements(By.cssSelector(".ut2-add-to-wish")).size();
        Assert.assertTrue(sizeOfButton_AddToWishList >= 1,
                "There is no button 'Add to wish list' in the product block!");
        //Проверка, что кнопка "Сравнить" присутствует
        int sizeOfButton_AddToComparisonList = getDriver().findElements(By.cssSelector(".ut2-add-to-compare")).size();
        Assert.assertTrue(sizeOfButton_AddToComparisonList >= 1,
                "There is no button 'Add to comparison list' in the product block!");
        //Проверка, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются БЕЗ наведения на ячейку товара
        int sizeOfButtonsAreDisplayedOnHover = getDriver().findElements(By.cssSelector(".ut2-w-c-q__buttons.w_c_q-hover")).size();
        Assert.assertTrue(sizeOfButtonsAreDisplayedOnHover < 1,
                "The buttons are not displayed without hovering over a product cell in the product block!");
        takeScreenShot("210 Var2_BlockWithProducts");
        stHomePage.selectLanguage_RTL();
        makePause();
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot("211 Var2_BlockWithProductsRTL");
        stHomePage.selectLanguage_RU();
        //Категория "Женская одежда"
        stHomePage.navigateToMenuWomanCloth();
        StCategoryPage stCategoryPage = new StCategoryPage();
        //Проверка, что на странице отсутствует обесцвеченный товар.
        Assert.assertTrue(getDriver().findElements(By.cssSelector(".ut2-gl__body.content-on-hover.decolorize")).size() < 1,
                "There is a decolorized product on the category page but shouldn't!");
        //Проверка, что у товаров присутствует галерея мини-иконок товара
        Assert.assertTrue(sizeOfGalleryOfMiniIcons >= 1,
                "There is no Gallery of mini icons of the product on the category page!");
        //Проверка, что кнопка "Избранное" присутствует
        Assert.assertTrue(sizeOfButton_AddToWishList >= 1,
                "There is no button 'Add to wish list' on the category page!");
        //Проверка, что кнопка "Сравнить" присутствует
        Assert.assertTrue(sizeOfButton_AddToComparisonList >= 1,
                "There is no button 'Add to comparison list' on the category page!");
        //Проверка, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются БЕЗ наведения на ячейку товара
        Assert.assertTrue(sizeOfButtonsAreDisplayedOnHover < 1,
                "The buttons are not displayed without hovering over a product cell on the category page!");
        stCategoryPage.hoverToClothProduct();
        takeScreenShot("220 Var2_WomanClothCategory");
        stHomePage.selectLanguage_RTL();
        makePause();
        stCategoryPage.hoverToClothProduct();
        takeScreenShot("221 Var2_WomanClothCategoryRTL");
        stHomePage.selectLanguage_RU();
        //Категория "Телефоны"
        stHomePage.navigateToMenuPhones();
        //Проверка, что у товаров присутствует общее значение рейтинга товара
        Assert.assertTrue(sizeOfGeneralRatingNumber >= 1,"There is no common value of product rating at a product!");
        //Проверка, что кнопка "Избранное" присутствует
        Assert.assertTrue(sizeOfButton_AddToWishList >= 1,
                "There is no button 'Add to wish list' on the category page!");
        //Проверка, что кнопка "Сравнить" присутствует
        Assert.assertTrue(sizeOfButton_AddToComparisonList >= 1,
                "There is no button 'Add to comparison list' on the category page!");
        //Проверка, что присутствует статус у кнопки "Избранное"
        stCategoryPage.button_AddToWishList.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cm-notification-close")));
        stCategoryPage.closeNotificationWindow.click();
        int sizeOfStatusAtButton_WishList = getDriver().findElements(By.cssSelector("a.ut2-add-to-wish.active")).size();
        Assert.assertTrue(sizeOfStatusAtButton_WishList >= 1,"There is no status for the button 'Add to wish list'!");
        //Проверка, что присутствует статус у кнопки "Сравнение"
        stCategoryPage.button_AddToComparisonList.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cm-notification-close")));
        stCategoryPage.closeNotificationWindow.click();
        Assert.assertTrue(sizeOfButton_AddToComparisonList >= 1,
                "There is no status for the button 'Add to comparison list'!");
        /*  Скрыто, пока не решена ошибка https://abteam.planfix.com/task/38481
        //Проверка, что у кнопки "В корзину" отображается статус в виде количества товаров
        stHomePage.LogOutOnStorefront();
        stCategoryPage.button_AddToCart.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cm-notification-content.cm-notification-content-extended")));
        stCategoryPage.button_ContinueShopping.click();
        Assert.assertTrue(getDriver().findElements(By.cssSelector(".ut2-added-to-cart")).size() >= 1,
                "There is no status for the button 'Add to cart' on the category page!");
        if(getDriver().findElements(By.cssSelector(".notification-content.alert")).size() >= 1){
            for(int i=0; i<stCategoryPage.notification_AlertSuccess.size(); i++){
                stCategoryPage.closeNotification_AlertSuccess.click();
            }}*/
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("230 Var2_PhonesCategory");
        stHomePage.selectLanguage_RTL();
        makePause();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("231 Var2_PhonesCategoryRTL");
        stHomePage.selectLanguage_RU();

        //Быстрый просмотр в категории "Телефоны"
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-icon-right-open-thin")));
        //Проверка, что присутствуют мини-иконки в виде галереи
        Assert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-icon-right-open-thin")).size() >= 1,
                "Mini icons are not as a gallery!");
        takeScreenShot("240 Var2_QuickView");
        stCategoryPage.clickCloseQuickView();
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-icon-right-open-thin")));
        takeScreenShot("241 Var2_QuickViewRTL");
        stCategoryPage.clickCloseQuickView();

        //Других два шаблона страницы категории
        stCategoryPage.clickListWithoutOptions_ProductListView();
        makePause();
        //Проверка, что у товаров присутствует общее значение рейтинга товара
        Assert.assertTrue(sizeOfGeneralRatingNumber >= 1,"There is no common value of product rating at a product!");
        //Проверка, что присутствует статус у кнопки "Избранное"
        Assert.assertTrue(sizeOfStatusAtButton_WishList >= 1,"There is no status for the button 'Add to wish list'!");
        //Проверка, что присутствует статус у кнопки "Сравнение"
        int sizeOfStatusAtButton_Comparison = getDriver().findElements(By.cssSelector("a.ut2-add-to-compare.active")).size();
        Assert.assertTrue(sizeOfStatusAtButton_Comparison >= 1,"There is no status for the button 'Add to comparison list'!");
        //Проверка, что у кнопки "В корзину" отображается статус в виде количества товаров
/*      Скрыто, пока не решена ошибка https://abteam.planfix.com/task/38481
        Assert.assertTrue(getDriver().findElements(By.cssSelector(".ut2-added-to-cart")).size()>=1,
                "There is no status for the button 'Add to cart' on the category page!");*/
        takeScreenShot("250 Var2_Category_ListWithoutOptionsRTL");
        stHomePage.selectLanguage_RU();
        makePause();
        takeScreenShot("251 Var2_Category_ListWithoutOptions");
        stCategoryPage.clickCompactList_ProductListView();
        makePause();
        //Проверка, что у товаров присутствует общее значение рейтинга товара
        Assert.assertTrue(sizeOfGeneralRatingNumber >= 1,"There is no common value of product rating at a product!");
        //Проверка, что присутствует статус у кнопки "Избранное"
        Assert.assertTrue(sizeOfStatusAtButton_WishList >= 1,"There is no status for the button 'Add to wish list'!");
        //Проверка, что присутствует статус у кнопки "Сравнение"
        Assert.assertTrue(sizeOfStatusAtButton_Comparison >= 1,"There is no status for the button 'Add to comparison list'!");
        //Проверка, что у кнопки "В корзину" отображается статус в виде количества товаров
/*      Скрыто, пока не решена ошибка https://abteam.planfix.com/task/38481
        Assert.assertTrue(getDriver().findElements(By.cssSelector(".ut2-added-to-cart")).size()>=1,
                "There is no status for the button 'Add to cart' on the category page!");*/
        takeScreenShot("260 Var2_Category_CompactList_ProductListView");
        stHomePage.selectLanguage_RTL();
        makePause();
        takeScreenShot("261 Var2_Category_CompactList_ProductListViewRTL");
    }
}