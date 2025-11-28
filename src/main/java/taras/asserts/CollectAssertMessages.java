package taras.asserts;

import org.testng.asserts.SoftAssert;
import taras.constants.AbstractPage;

public class CollectAssertMessages extends AbstractPage {

    private static final ThreadLocal<SoftAssert> threadLocal = new ThreadLocal<>();

    public static SoftAssert getSoftAssertions() {
        return threadLocal.get();
    }

    public static void setSoftAssertions(SoftAssert softAssert) {
        threadLocal.set(softAssert);
    }
}
