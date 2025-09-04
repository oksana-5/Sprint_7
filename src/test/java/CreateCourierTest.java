import builder.CourierBuilder;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static data.TestData.*;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static steps.CourierSteps.*;

public class CreateCourierTest extends BaseAPITest {

    @Test
    @DisplayName("Create courier success test")
    @Description("Valid courier creation returns 201")
    public void createCourierSuccessTest() {

        CourierBuilder courier = new CourierBuilder.Builder()
                .withLogin(LOGIN)
                .withPassword(PASSWORD)
                .withFirstName(FIRST_NAME)
                .build();

        createCourier(courier)
                .then()
                .statusCode(HTTP_CREATED)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Impossible to create two identical couriers test")
    @Description("Duplicate login returns 409 conflict")
    public void impossibleToCreateTwoIdenticalCouriersTest() {

        CourierBuilder courier = new CourierBuilder.Builder()
                .withLogin(LOGIN)
                .withPassword(PASSWORD)
                .withFirstName(FIRST_NAME)
                .build();

        createCourier(courier);

        createCourier(courier)
                .then()
                .statusCode(HTTP_CONFLICT)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Create courier without password returns error test")
    @Description("Missing password returns 400 error")
    public void createCourierWithoutPasswordReturnsErrorTest() {
        CourierBuilder courier = new CourierBuilder.Builder()
                .withLogin(LOGIN)
                .build();

        createCourier(courier)
                .then()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Create courier without login returns error test")
    @Description("Missing login returns 400 error")
    public void createCourierWithoutLoginReturnsErrorTest() {
        CourierBuilder courier = new CourierBuilder.Builder()
                .withPassword(PASSWORD)
                .build();

        createCourier(courier)
                .then()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void tearDown() {
        cleanupTestData();
    }

    private void cleanupTestData() {
        try {
            Response loginResponse = loginCourier(new CourierBuilder.Builder()
                    .withLogin(LOGIN)
                    .withPassword(PASSWORD)
                    .build()
            );

            if (loginResponse.getStatusCode() == HTTP_OK) {
                String courierId = loginResponse.jsonPath().getString("id");
                if (courierId != null) {
                    deleteCourier(courierId);
                }
            }
        } catch (Exception e) {
        }
    }

}
