package taras.asserts;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.util.Map;

public class Asserts_ProductPage extends AbstractPage {
    public Asserts_ProductPage() {super();}

    private SoftAssert getSoftAssert() {
        return CollectAssertMessages.getSoftAssertions();
    }

    public static final String productPage = ".ty-product-block ";


    //Настройка "Промо-текст"
    public String promoText = ".ut2-pb__note";

    //Настройка "Цена за единицу"
    public String pricePerUnit = ".ty-price-per-unit";

    //Настройка "Действие при нулевой цене -- Попросить покупателя ввести цену"
    public String zeroPriceAction_AskCustomerToEnterPrice = ".ty-price-curency__input";

    //Настройка "Действие при отсутствии товара в наличии -- Предзаказ"
    public String outOfStockActions_BuyInAdvance = ".on_backorder";

    //Настройка "Действие при отсутствии товара в наличии -- Подписаться на уведомления"
    public String outOfStockActions_SignUpForNotification = "label[id*='label_sw_product_notify_']";

    //Настройка "Краткое описание"
    public String shortDescription = ".ut2-pb__short-descr";

    //Вкладка "Бонусные баллы" -- Настройка "Разрешить оплату баллами"
    public String allowPaymentByPoints = ".ty-reward-group";

    //Модификатор количества во всплывающем окне покупки товара с опциями
    public String quantityChangerInOptionWindow = ".ut2_select_variation__buttons .ty-value-changer";


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

    public void assertElementPresence(String list, String selector, String location, boolean elementExists) {
        Map<String, String> presenceMessages = Map.ofEntries(
                Map.entry(promoText, "There is no Promo-text "),
                Map.entry(pricePerUnit,"There is no Price per unit "),
                Map.entry(zeroPriceAction_AskCustomerToEnterPrice, "There is no field 'Enter your price' "),
                Map.entry(outOfStockActions_BuyInAdvance, "Out of stock action is not 'Buy in advance' "),
                Map.entry(outOfStockActions_SignUpForNotification, "There is no field 'Sign up for notification' "),
                Map.entry(shortDescription, "There is no product Short description "),
                Map.entry(allowPaymentByPoints, "There are no Reward points "),
                Map.entry(quantityChangerInOptionWindow, "There is no quantity changer in pop-up window of the product with options!")
        );

        Map<String, String> absenceMessages = Map.ofEntries(
                Map.entry(promoText, "There is a Promo-text but shouldn't ")
        );

        String message = resolveAssertMessage(selector, elementExists, presenceMessages, absenceMessages);
        if (message == null)
            return;

        String finalSelector = switch (list) {
            case productPage -> productPage + selector;
            default -> selector;
        };

        getSoftAssert().assertTrue(
                elementExists == !DriverProvider.getDriver().findElements(By.cssSelector(finalSelector)).isEmpty(),
                message + location + "\n" + finalSelector
        );
    }
}
