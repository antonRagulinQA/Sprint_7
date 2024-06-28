package clients;
import data.Constants;
import data.Order;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrdersClient implements Constants {

    @Step ("Получаем ответ от созданного заказа")
    public ValidatableResponse getOrdersResponse (Order order) {
        return  given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(API_ORDER)
                .then();
    }
}
