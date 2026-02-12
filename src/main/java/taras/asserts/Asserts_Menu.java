package taras.asserts;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.util.Map;

public class Asserts_Menu extends AbstractPage {
    public Asserts_Menu() {super();}

    private SoftAssert getSoftAssert() {
        return CollectAssertMessages.getSoftAssertions();
    }

    //Настройка "Способ заполнения -- Строчное"
    public String rowFilling = ".ut2-menu__submenu__carrier.row-filling";

    //Настройка "Показывать иконки для пунктов меню второго уровня"
    public String iconsOfSecondLevel = ".ut2-menu__2nd-list .ut2-mwi-icon-wrap .ut2-mwi-icon";

    //Настройка "Столбцов в строке"
    public String columnsPerRow = "ul[style='--menu-columns: ";

    //Настройка "Количество видимых элементов в третьем уровне меню"
    public String numberOfVisibleElementsIn_ThirdLevel = "div[style*='--menu-items:";

    //Настройка "Элементы второго уровня" в категории "Электроника"
    public String numberOfElements_SecondLevel = ".ty-menu-item__electronics div[data-elem-index]";

    //Настройка "Элементы третьего уровня" в категории "Электроника"
    public String numberOfElements_ThirdLevel_Electronics = ".ty-menu-item__electronics div[data-elem-index='0'] .ut2-menu__3rd-item";

    //Настройка "Элементы третьего уровня" в категории "Все товары -- Электроника"
    public String numberOfElements_ThirdLevel_AllProducts = ".ty-menu-item__products div[data-elem-index='0'] .ut2-menu__3rd-item";

    //Настройка "Элементы третьего уровня" для 3-х уровневого меню (Каскадный тип меню)
    public String cascadeMenu_elementsInThirdLevel = "li.ty-menu-item__electronics div[data-elem-index='1'] .ut2-menu__3rd-item";

    //Кнопка "Ещё" у элементов во 2-м уровне меню
    public String button_More_InElementsOfSecondLevel = ".ut2-menu .ut2-more-btn";

    //Кнопка "Больше [категория] во 2-м уровне меню
    public String button_MoreCategory_InSecondLevel = ".ut2-menu__more-cat-link";

    //Кнопка "Больше [категория]" для 3-х уровневого меню (Каскадный тип меню)
    public String cascadeMenu_MoreCategory = ".ut2-menu__2nd-submenu__wrapper .ut2-menu__more-cat-link__in";

    //Баннер на третьем уровне меню
    public String banner_InThirdLevel = ".ut2-menu__2nd-submenu img[src$='sports-bg-menu.jpg']";


    //Список настроек FLY menu

    //Иконки у меню второго уровня
    public String flyMenu_iconsOfSecondLevel = ".img .ut2-lfl-icon";

    //Элементы во 2-м уровне
    public String flyMenu_NumberOfElements_SecondLevel = ".ut2-lfl.ty-menu-item__electronics .ut2-tlw";

    //Элементы в 3-м уровне
    public String flyMenu_NumberOfElements_ThirdLevel = ".ty-menu-item__electronics .ut2-tlw a[href*='kompyutery']";

    //Количество видимых элементов в третьем уровне меню
    public String flyMenu_numberOfVisibleElements = "//p//a[contains(@href, 'elektronika/kompyutery/')]/../..//div[@class='ut2-tlw']//a[contains(@href, 'elektronika/kompyutery/') and not(contains(@class, 'hidden'))]";

    //Кнопка "Ещё" у элементов во 2-м уровне меню
    public String flyMenu_ButtonMore = ".ut2-lsl__more-link";

    //Кнопка "Больше [категория]
    public String flyMenu_ButtonMoreCategories = ".ty-menu__submenu-alt-link";


    private String resolveAssertMessage(String selector,
                                        boolean elementsAreEmpty,
                                        Map<String, String> presenceMessages,
                                        Map<String, String> absenceMessages) {

        String message = elementsAreEmpty
                ? presenceMessages.get(selector)
                : absenceMessages.get(selector);

        if (message == null)
            getSoftAssert().fail("No assert message found for selector: " + selector);

        return message;
    }

    public void assertElementPresence(String selector, boolean elementExists) {
        Map<String, String> presenceMessages = Map.ofEntries(
                Map.entry(rowFilling, "Menu filling is not Row!"),
                Map.entry(iconsOfSecondLevel, "There are no icons in the second level of menu!"),
                Map.entry(button_More_InElementsOfSecondLevel, "There are no buttons 'More' in the elements of the second level of menu!"),
                Map.entry(button_MoreCategory_InSecondLevel, "There are no buttons 'More [category]' in the second level of menu!"),
                Map.entry(cascadeMenu_MoreCategory, "There are no buttons 'More [category]' in the third level of Cascade menu!"),
                Map.entry(banner_InThirdLevel, "There is no banner in the third level of menu!"),
                Map.entry(flyMenu_iconsOfSecondLevel, "There are no icons in the second level of Fly menu!"),
                Map.entry(flyMenu_ButtonMore, "There are no buttons 'More' in the elements of the 2-level of Fly menu!"),
                Map.entry(flyMenu_ButtonMoreCategories, "There are no buttons 'More [category]' in the second level of Fly menu!")
        );

        Map<String, String> absenceMessages = Map.ofEntries(
                Map.entry(rowFilling, "Menu filling is not Column!"),
                Map.entry(iconsOfSecondLevel, "There are icons in the second level of menu but shouldn't!"),
                Map.entry(button_More_InElementsOfSecondLevel, "There are buttons 'More' in the second level of menu but shouldn't!"),
                Map.entry(numberOfElements_ThirdLevel_Electronics, "There are elements of the third level of menu but shouldn't!"),
                Map.entry(flyMenu_iconsOfSecondLevel, "There are icons in the second level of Fly menu but shouldn't!")
        );

        String message = resolveAssertMessage(selector, elementExists, presenceMessages, absenceMessages);
        if (message == null)
            return;

        getSoftAssert().assertTrue(
                elementExists == !DriverProvider.getDriver().findElements(By.cssSelector(selector)).isEmpty(),
                message + "\n" + selector
        );
    }

    public void assertNumberOfElements(String selector, int quantity) {
        Map<String, String> selectorMessages = Map.ofEntries(
                Map.entry(columnsPerRow, "Menu columns are not equal " + quantity),
                Map.entry(numberOfVisibleElementsIn_ThirdLevel, "'Number of visible elements in the 3-level menu' is not " + quantity)
        );

        String message = selectorMessages.get(selector);
        if (message == null)
            getSoftAssert().fail("No assert message found for selector: " + selector);

        String finalSelector = selector + quantity + "']";

        getSoftAssert().assertTrue(
                !DriverProvider.getDriver().findElements(By.cssSelector(finalSelector)).isEmpty(),
                message + "\n" + finalSelector
        );
    }

    public void assertSizeOfElements(String selector, int size) {
        Map<String, String> selectorMessages = Map.ofEntries(
                Map.entry(numberOfElements_SecondLevel, "Number of elements of the second level is not equal " + size),
                Map.entry(numberOfElements_ThirdLevel_Electronics,
                        "Number of elements of the third level in the category 'Electronics' is not equal " + size),
                Map.entry(numberOfElements_ThirdLevel_AllProducts,
                        "Number of elements of the third level in the category 'All products - Electronics' is not equal " + size),
                Map.entry(button_More_InElementsOfSecondLevel, "In the elements of the second level of menu, buttons 'More' are not equal " + size),
                Map.entry(cascadeMenu_elementsInThirdLevel, "In Cascade menu 'Third level elements' are not equal " + size),
                Map.entry(flyMenu_numberOfVisibleElements, "In Cascade menu 'Number of visible elements' is not equal " + size),
                Map.entry(flyMenu_NumberOfElements_SecondLevel, "In Fly menu Number of elements of the second level is not equal " + size),
                Map.entry(flyMenu_NumberOfElements_ThirdLevel, "In Fly menu Number of elements of the third level is not equal " + size)
        );

        String message = selectorMessages.get(selector);
        if (message == null)
            getSoftAssert().fail("No assert message found for selector: " + selector);

        getSoftAssert().assertEquals(
                DriverProvider.getDriver().findElements(By.cssSelector(selector)).size(), size,
                message + "\n" + selector
        );
    }

    public void assertMoreOrEqual(String selector, int quantity) {
        Map<String, String> selectorMessages = Map.ofEntries(
                Map.entry(button_More_InElementsOfSecondLevel,
                        "In the elements of the second level of menu, buttons 'More' are less than " + quantity),
                Map.entry(numberOfElements_SecondLevel,
                        "Number of elements of the second level is less than " + quantity),
                Map.entry(numberOfElements_ThirdLevel_Electronics,
                        "Number of elements of the third level in the category 'Electronics' is lass than " + quantity),
                Map.entry(cascadeMenu_elementsInThirdLevel,
                        "In Cascade menu 'Third level elements' are are less than " + quantity)
        );

        String message = selectorMessages.get(selector);
        if (message == null)
            getSoftAssert().fail("No assert message found for selector: " + selector);

        getSoftAssert().assertTrue(
                DriverProvider.getDriver().findElements(By.cssSelector(selector)).size() >= quantity,
                message + "\n" + selector
        );
    }
}