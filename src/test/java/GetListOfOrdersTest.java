import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.notNullValue;
import static steps.OrderSteps.getListOfOrders;

public class GetListOfOrdersTest extends BaseAPITest {

    @Test
    public void getListOfOrdersSuccess() {
        getListOfOrders()
                .then()
                .statusCode(HTTP_OK)
                .body("orders", notNullValue());
    }
}
