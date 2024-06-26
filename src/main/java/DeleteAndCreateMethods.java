import data.Constants;
import data.Courier;
import data.Login;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class DeleteAndCreateMethods extends Constants {
public void deleteAccount() {
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
public void createAccount() {
    Courier newCourier = new Courier(EXISTING_LOGIN, EXISTING_PASSWORD, EXISTING_FIRSTNAME);
    given()
            .header("Content-type", "application/json")
            .and()
            .body(newCourier)
            .when()
            .post(API_COURIER);
}
    }
