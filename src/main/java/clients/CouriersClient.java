package clients;

import data.Constants;
import data.Courier;
import data.Login;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class CouriersClient implements Constants {
    public ValidatableResponse getCourierResponse(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(API_COURIER)
                .then();

    }

    @Step("Get response for Login")
    public ValidatableResponse getLoginResponse(Login login) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(login)
                .when()
                .post(API_LOGIN)
                .then();
    }
    public static void deleteAccount() {
    Login login = new Login(EXISTING_LOGIN, EXISTING_PASSWORD);
    Response response =
            given()
                    .header("Content-type", "application/json")
                    .and()
                    .body(login)
                    .when()
                    .post(API_LOGIN);
        String id = response.jsonPath().getString("id");
        given().
                when()
                .delete(API_LOGIN + id);
}
public static void createAccount() {
    Courier newCourier = new Courier(EXISTING_LOGIN, EXISTING_PASSWORD, EXISTING_FIRSTNAME);
    given()
            .header("Content-type", "application/json")
            .and()
            .body(newCourier)
            .when()
            .post(API_COURIER);
}
}
