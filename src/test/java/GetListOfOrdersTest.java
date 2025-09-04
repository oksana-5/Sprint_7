import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.notNullValue;
import static steps.OrderSteps.getListOfOrders;

public class GetListOfOrdersTest extends BaseAPITest {

    @Test
    @DisplayName("Get list of orders success test")
    @Description("Retrieving orders list returns 200 and non-empty response")
    public void getListOfOrdersSuccessTest() {
        getListOfOrders()
                .then()
                .statusCode(HTTP_OK)
                .body("orders", notNullValue());
    }
}
