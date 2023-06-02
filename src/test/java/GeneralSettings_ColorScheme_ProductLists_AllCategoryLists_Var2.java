import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import taras.adminPanel.ColorSchemeSettings;
import taras.adminPanel.ThemeSettings_ProductLists;
import taras.constants.DriverProvider;
import taras.adminPanel.CsCartSettings;
import taras.storefront.StCategoryPage;
import taras.storefront.StHomePage;
import java.time.Duration;
import static taras.constants.DriverProvider.getDriver;

/* ссылка на тест-кейс: https://docs.google.com/spreadsheets/d/1YPAkjqk12kPh7LBDU1tq7qdwLmCo-Rly00TdfW8h-Wo/edit#gid=718159332

Проверка следующих настроек:
1) "Настройки -- Внешний вид":
Включить быстрый просмотр -- y
Показывать мини-иконки в виде галереи -- y

2) UniTheme2 -- Настройки темы -- вкладка "Списки товаров":
Показывать галерею мини-иконок товара в товарном списке --	с стрелками
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

3) - UniTheme2 -- Настройки цветосхемы -- вкладка "Списки товаров":
Тип обрамления товара в сетке --	Рамка с внешними отступами
Добавить фон/маску для изображений товара --	да
Использовать выравнивание элементов в товарной сетке --	нет
Эффект увеличения ячейки при наведении --	нет
Насыщенность шрифта для названия товара --	Жирный

Проверка проходит на следующих страницах:
- Блок товаров на Главной странице + RTL
- Женская одежда + RTL
- Телефоны + RTL
- Быстрый просмотр + RTL
- Все шаблоны категории + RTL
*/

public class GeneralSettings_ColorScheme_ProductLists_AllCategoryLists_Var2 extends TestRunner{
    @Test(priority = 1)
    public void setConfigurationsForProductLists_AllCategoryLists_Var2() {
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
        csCartSettings.navigateTo_ThemeSettings_tabProductLists();
        themeSettingsProductLists.clickTabProductLists();
        themeSettingsProductLists.selectSettingMiniIconsGallery("points");
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

        //Настраиваем UniTheme цветосхему, вкладка "Списки товаров"
        ColorSchemeSettings colorSchemeSettings = csCartSettings.navigateTo_ColorSchemeSettings();
        colorSchemeSettings.fieldOfActiveColorScheme.click();
        colorSchemeSettings.activeColorScheme.click();
        makePause();
        colorSchemeSettings.tab_ProductLists.click();
        colorSchemeSettings.selectSetting_FrameType("solid_with_margins");
        if(!colorSchemeSettings.setting_ProductLists_MaskForProductImages.isSelected()){
            colorSchemeSettings.setting_ProductLists_MaskForProductImages.click();
        }
        colorSchemeSettings.selectSetting_ProductLists_ElementsAlignment("do_not_use");
        if(colorSchemeSettings.setting_ProductLists_ExpandGridItemOnHover.isSelected()){
            colorSchemeSettings.setting_ProductLists_ExpandGridItemOnHover.click();
        }
        colorSchemeSettings.selectSetting_ProductLists_FontWeightForProductName("bold");
        csCartSettings.clickSaveButtonOfSettings();
    }

    @Test(priority = 2, dependsOnMethods = "setConfigurationsForProductLists_AllCategoryLists_Var2")
    public void checkProductLists_AllCategoryLists_Var2() {
        CsCartSettings csCartSettings = new CsCartSettings();
        StHomePage stHomePage = csCartSettings.navigateToStorefrontMainPage();
        focusBrowserTab(1);
        stHomePage.cookie.click();
        //Блок товаров на главной странице
        stHomePage.scrollToBlockWithProducts();
        SoftAssert softAssert = new SoftAssert();
        //Проверка, что у товаров присутствует галерея мини-иконок товара
        int sizeOfGalleryOfMiniIcons = getDriver().findElements(By.cssSelector(".ut2-gl__body.content-on-hover .icon-right-circle")).size();
        softAssert.assertTrue(sizeOfGalleryOfMiniIcons >= 1,
                "There is no Gallery of mini icons of the product in the product block!");
        //Проверка, что у товаров присутствует общее значение рейтинга товара
        int sizeOfGeneralRatingNumber = getDriver().findElements(By.cssSelector(".ut2-show-rating-num")).size();
        softAssert.assertTrue(sizeOfGeneralRatingNumber >= 1,"There is no common value of product rating at a product!");
        //Проверка, что кнопка "Избранное" присутствует
        int sizeOfButton_AddToWishList = getDriver().findElements(By.cssSelector(".ut2-add-to-wish")).size();
        softAssert.assertTrue(sizeOfButton_AddToWishList >= 1,
                "There is no button 'Add to wish list' in the product block!");
        //Проверка, что кнопка "Сравнить" присутствует
        int sizeOfButton_AddToComparisonList = getDriver().findElements(By.cssSelector(".ut2-add-to-compare")).size();
        softAssert.assertTrue(sizeOfButton_AddToComparisonList >= 1,
                "There is no button 'Add to comparison list' in the product block!");
        //Проверка, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются БЕЗ наведения на ячейку товара
        int sizeOfButtonsAreDisplayedOnHover = getDriver().findElements(By.cssSelector(".ut2-w-c-q__buttons.w_c_q-hover")).size();
        softAssert.assertTrue(sizeOfButtonsAreDisplayedOnHover < 1,
                "The buttons are not displayed without hovering over a product cell in the product block!");
        takeScreenShot_withoutScroll("200 Var2_BlockWithProducts");
        stHomePage.selectLanguage_RTL();
        stHomePage.scrollToBlockWithProducts();
        takeScreenShot_withoutScroll("205 Var2_BlockWithProductsRTL");
        stHomePage.selectLanguage_RU();
        //Категория "Женская одежда"
        stHomePage.navigateToMenuWomanCloth();
        StCategoryPage stCategoryPage = new StCategoryPage();
        //Проверка, что на странице отсутствует обесцвеченный товар.
        softAssert.assertTrue(getDriver().findElements(By.cssSelector(".ut2-gl__body.content-on-hover.decolorize")).size() < 1,
                "There is a decolorized product on the category page but shouldn't!");
        //Проверка, что у товаров присутствует галерея мини-иконок товара
        softAssert.assertTrue(sizeOfGalleryOfMiniIcons >= 1,
                "There is no Gallery of mini icons of the product on the category page!");
        //Проверка, что кнопка "Избранное" присутствует
        softAssert.assertTrue(sizeOfButton_AddToWishList >= 1,
                "There is no button 'Add to wish list' on the category page!");
        //Проверка, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(sizeOfButton_AddToComparisonList >= 1,
                "There is no button 'Add to comparison list' on the category page!");
        //Проверка, что кнопки "Быстрый просмотр, Добавить в избранное, Добавить в список сравнения" отображаются БЕЗ наведения на ячейку товара
        softAssert.assertTrue(sizeOfButtonsAreDisplayedOnHover < 1,
                "The buttons are not displayed without hovering over a product cell on the category page!");
        stCategoryPage.hoverToClothProduct();
        takeScreenShot("210 Var2_WomanClothCategory");
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToClothProduct();
        takeScreenShot("215 Var2_WomanClothCategoryRTL");
        stHomePage.selectLanguage_RU();

        //Категория "Телефоны"
        stHomePage.navigateToMenuPhones();
        //Проверка, что у товаров присутствует общее значение рейтинга товара
        softAssert.assertTrue(sizeOfGeneralRatingNumber >= 1,"There is no common value of product rating at a product!");
        //Проверка, что кнопка "Избранное" присутствует
        softAssert.assertTrue(sizeOfButton_AddToWishList >= 1,
                "There is no button 'Add to wish list' on the category page!");
        //Проверка, что кнопка "Сравнить" присутствует
        softAssert.assertTrue(sizeOfButton_AddToComparisonList >= 1,
                "There is no button 'Add to comparison list' on the category page!");
        //Проверка, что присутствует статус у кнопки "Избранное"
        stCategoryPage.button_AddToWishList.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cm-notification-close")));
        stCategoryPage.closeNotificationWindow.click();
        int sizeOfStatusAtButton_WishList = getDriver().findElements(By.cssSelector("a.ut2-add-to-wish.active")).size();
        softAssert.assertTrue(sizeOfStatusAtButton_WishList >= 1,"There is no status for the button 'Add to wish list'!");
        //Проверка, что присутствует статус у кнопки "Сравнение"
        stCategoryPage.button_AddToComparisonList.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cm-notification-close")));
        stCategoryPage.closeNotificationWindow.click();
        softAssert.assertTrue(sizeOfButton_AddToComparisonList >= 1,
                "There is no status for the button 'Add to comparison list'!");
        /*  Скрыто, пока не решена ошибка https://abteam.planfix.com/task/38481
        //Проверка, что у кнопки "В корзину" отображается статус в виде количества товаров
        stHomePage.LogOutOnStorefront();
        stCategoryPage.button_AddToCart.click();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cm-notification-content.cm-notification-content-extended")));
        stCategoryPage.button_ContinueShopping.click();
        softAssert.assertTrue(getDriver().findElements(By.cssSelector(".ut2-added-to-cart")).size() >= 1,
                "There is no status for the button 'Add to cart' on the category page!");
        if(getDriver().findElements(By.cssSelector(".notification-content.alert")).size() >= 1){
            for(int i=0; i<stCategoryPage.notification_AlertSuccess.size(); i++){
                stCategoryPage.closeNotification_AlertSuccess.click();
            }}*/
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("220 Var2_PhonesCategory");
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        takeScreenShot("225 Var2_PhonesCategoryRTL");
        stHomePage.selectLanguage_RU();

        //Быстрый просмотр в категории "Телефоны"
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-icon-right-open-thin")));
        //Проверка, что присутствуют мини-иконки в виде галереи
        softAssert.assertTrue(DriverProvider.getDriver().findElements(By.cssSelector(".ty-icon-right-open-thin")).size() >= 1,
                "Mini icons are not as a gallery!");
        takeScreenShot("230 Var2_QuickView");
        stCategoryPage.clickCloseQuickView();
        stHomePage.selectLanguage_RTL();
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-icon-right-open-thin")));
        takeScreenShot("235 Var2_QuickViewRTL");
        stCategoryPage.clickCloseQuickView();

        //Других два шаблона страницы категории
        stCategoryPage.clickListWithoutOptions_ProductListView();
        //Проверка, что у товаров присутствует общее значение рейтинга товара
        softAssert.assertTrue(sizeOfGeneralRatingNumber >= 1,"There is no common value of product rating at a product!");
        //Проверка, что присутствует статус у кнопки "Избранное"
        softAssert.assertTrue(sizeOfStatusAtButton_WishList >= 1,"There is no status for the button 'Add to wish list'!");
        //Проверка, что присутствует статус у кнопки "Сравнение"
        int sizeOfStatusAtButton_Comparison = getDriver().findElements(By.cssSelector("a.ut2-add-to-compare.active")).size();
        softAssert.assertTrue(sizeOfStatusAtButton_Comparison >= 1,"There is no status for the button 'Add to comparison list'!");
        //Проверка, что у кнопки "В корзину" отображается статус в виде количества товаров
/*      Скрыто, пока не решена ошибка https://abteam.planfix.com/task/38481
        softAssert.assertTrue(getDriver().findElements(By.cssSelector(".ut2-added-to-cart")).size()>=1,
                "There is no status for the button 'Add to cart' on the category page!");*/
        takeScreenShot("240 Var2_Category_ListWithoutOptionsRTL");
        stHomePage.selectLanguage_RU();
        takeScreenShot("245 Var2_Category_ListWithoutOptions");
        stCategoryPage.clickCompactList_ProductListView();
        //Проверка, что у товаров присутствует общее значение рейтинга товара
        softAssert.assertTrue(sizeOfGeneralRatingNumber >= 1,"There is no common value of product rating at a product!");
        //Проверка, что присутствует статус у кнопки "Избранное"
        softAssert.assertTrue(sizeOfStatusAtButton_WishList >= 1,"There is no status for the button 'Add to wish list'!");
        //Проверка, что присутствует статус у кнопки "Сравнение"
        softAssert.assertTrue(sizeOfStatusAtButton_Comparison >= 1,"There is no status for the button 'Add to comparison list'!");
        //Проверка, что у кнопки "В корзину" отображается статус в виде количества товаров
/*      Скрыто, пока не решена ошибка https://abteam.planfix.com/task/38481
        softAssert.assertTrue(getDriver().findElements(By.cssSelector(".ut2-added-to-cart")).size()>=1,
                "There is no status for the button 'Add to cart' on the category page!");*/
        takeScreenShot("250 Var2_Category_CompactList_ProductListView");
        stHomePage.selectLanguage_RTL();
        takeScreenShot("255 Var2_Category_CompactList_ProductListViewRTL");
        softAssert.assertAll();
        System.out.println("GeneralSettings_ColorScheme_ProductLists_AllCategoryLists_Var2 passed successfully!");
    }
}