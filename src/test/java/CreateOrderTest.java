import builder.OrderBuilder;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static org.hamcrest.Matchers.notNullValue;
import static steps.OrderSteps.cancelOrder;
import static steps.OrderSteps.createOrder;

@RunWith(Parameterized.class)
public class CreateOrderTest extends BaseAPITest {

    private String track;
    private final String[] colors;

    public CreateOrderTest(String[] colors) {
        this.colors = colors;
    }

    @Parameterized.Parameters(name = "Colors: {0}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[]{}}
        };
    }

    @Test
    @DisplayName("Create order with different colors test")
    @Description("Order creation with various color options returns 201 and track number")
    public void createOrderWithDifferentColorsTest() {
        OrderBuilder order = new OrderBuilder.Builder()
                .withFirstName("Harry")
                .withLastName("Potter")
                .withAddress("Privet drive, 4")
                .withMetroStation("King's Cross")
                .withPhone("+77777777777")
                .withRentTime(7)
                .withDeliveryDate("2025-07-31")
                .withComment("After all this time")
                .withColor(colors)
                .build();

        track = createOrder(order)
                .then()
                .statusCode(HTTP_CREATED)
                .body("track", notNullValue())
                .extract()
                .path("track")
                .toString();
    }

    @After
    public void tearDown() {
        if (track != null) {
            cancelOrder(track);
        }
    }
}
