package steps;

import builder.CourierBuilder;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierSteps {

    public static final String PATH_CREATE_COURIER = "/api/v1/courier";
    public static final String PATH_LOGIN_COURIER = "/api/v1/courier/login";
    public static final String PATH_DELETE_COURIER = "/api/v1/courier/";

    @Step("Create courier")
    public static Response createCourier(CourierBuilder courierBuilder) {

        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courierBuilder)
                .when()
                .post(PATH_CREATE_COURIER)
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Login courier")
    public static Response loginCourier(CourierBuilder loginData) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(loginData)
                .when()
                .post(PATH_LOGIN_COURIER)
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Delete courier")
    public static Response deleteCourier(String id) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .delete(PATH_DELETE_COURIER + id)
                .then()
                .log().all()
                .extract().response();
    }


}


