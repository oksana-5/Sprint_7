package steps;

import builder.OrderBuilder;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderSteps {

    public static final String PATH_CREATE_ORDER = "/api/v1/orders";
    public static final String PATH_CANCEL_ORDER = "/api/v1/orders/cancel?track=";
    public static final String PATH_GET_LIST_OF_ORDERS = "/api/v1/orders";

    @Step("Create order")
    public static Response createOrder(OrderBuilder orderBuilder) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(orderBuilder)
                .when()
                .post(PATH_CREATE_ORDER)
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Cancel order")
    public static Response cancelOrder(String track) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .put(PATH_CANCEL_ORDER + track)
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Get list of orders")
    public static Response getListOfOrders() {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(PATH_GET_LIST_OF_ORDERS)
                .then()
                .log().all()
                .extract().response();
    }
}
