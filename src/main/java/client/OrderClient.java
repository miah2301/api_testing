package client;

import config.*;
import emity.*;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.StringUtils;
import utils.*;
import client.*;

import static io.restassured.RestAssured.given;

public class OrderClient extends UserClient {

    public ValidatableResponse getOrderResponse(Order order) {
        return given()
                .spec(getBaseSpec())
                .and()
                .body(order)
                .when()
                .post(API_ORDERS)
                .then();
    }

    public ValidatableResponse getAllOrdersLoginUser() {

        ValidatableResponse getToken = loginUser(new Login(EMAIL_TEST,PASSWORD_TEST));
        String accessToken = getToken.extract().path("accessToken");

        return given()
                .spec(getBaseSpec())
                .header("Authorization", "Bearer " + StringUtils.substringAfter(accessToken, " "))
                .when()
                .get(API_ORDERS)
                .then();
    }

    public ValidatableResponse getAllOrdersLogoutUser() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(API_ORDERS)
                .then();
    }
}