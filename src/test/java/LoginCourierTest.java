import builder.CourierBuilder;
import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static data.TestData.*;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static steps.CourierSteps.*;

public class LoginCourierTest extends BaseAPITest {

    @Before
    public void createCourierSuccess() {
        CourierBuilder courier = new CourierBuilder.Builder()
                .withLogin(LOGIN)
                .withPassword(PASSWORD)
                .withFirstName(FIRST_NAME)
                .build();

        createCourier(courier);
    }

    @Test
    public void loginCourierSuccessTest() {
        CourierBuilder courier = new CourierBuilder.Builder()
                .withLogin(LOGIN)
                .withPassword(PASSWORD)
                .build();

        loginCourier(courier)
                .then()
                .statusCode(HTTP_OK)
                .body("id", notNullValue());
    }

    @Test
    public void loginCourierWithoutPasswordReturnsErrorTest() {
        CourierBuilder courier = new CourierBuilder.Builder()
                .withLogin(LOGIN)
                .withPassword("")
                .build();

        loginCourier(courier)
                .then()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void loginCourierWithoutLoginReturnsErrorTest() {
        CourierBuilder courier = new CourierBuilder.Builder()
                .withLogin("")
                .withPassword(PASSWORD)
                .build();

        loginCourier(courier)
                .then()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void impossibleToLoginUncreatedCourierTest() {
        Faker faker = new Faker();
        CourierBuilder courier = new CourierBuilder.Builder()
                .withLogin(faker.name().username() + System.currentTimeMillis() + "_test")
                .withPassword(faker.regexify("[0-9]{4}"))
                .build();

        loginCourier(courier)
                .then()
                .statusCode(HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void impossibleToLoginCourierWithWrongLoginTest() {
        CourierBuilder courier = new CourierBuilder.Builder()
                .withLogin(LOGIN + "_test")
                .withPassword(PASSWORD)
                .build();

        loginCourier(courier)
                .then()
                .statusCode(HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void impossibleToLoginCourierWithWrongPasswordTest() {
        CourierBuilder courier = new CourierBuilder.Builder()
                .withLogin(LOGIN)
                .withPassword(PASSWORD + "0")
                .build();

        loginCourier(courier)
                .then()
                .statusCode(HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    public void tearDown() {
        String courierId = loginCourier(new CourierBuilder.Builder()
                .withLogin(LOGIN)
                .withPassword(PASSWORD)
                .build())
                .then()
                .extract()
                .path("id")
                .toString();

        deleteCourier(courierId);
    }
}
