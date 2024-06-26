import Clients.CouriersClient;
import data.Login;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginCourierTest extends DeleteAndCreateMethods {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        createAccount();
    }

    @Test
        @DisplayName("Check status 200 and successful login")
             @Description("Проверка что статус - код: 200 и курьер успешно залогинился")

    public void testSuccessfulLogin() {
        CouriersClient couriersClient = new CouriersClient();
        ValidatableResponse dataCourier = couriersClient.getLoginResponse(
                new Login(EXISTING_LOGIN,EXISTING_PASSWORD));
        dataCourier
                .statusCode(200);
        assertThat("id", notNullValue());
    }
    @Test
        @DisplayName("Check status code 400 and error message in request without login field")
            @Description("Проверка что статус - код: 400, и ошибка при отправке запроса с пустым полем \"login\"")
    public void testRequestWithoutLogin() {
        CouriersClient couriersClient = new CouriersClient();
        ValidatableResponse dataCourierWithoutLogin = couriersClient.getLoginResponse(
                new Login(null, EXISTING_PASSWORD)
        );
        dataCourierWithoutLogin
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
        @DisplayName("Check status code 400 and error message in request without password field")
            @Description("\"Проверка что статус - код: 400, и ошибка при отправке запроса с пустым полем \"password\"")
    public void testRequestWithoutPassword() {
        CouriersClient courierClient = new CouriersClient();
        ValidatableResponse dataCourierWithoutLogin = courierClient.getLoginResponse(
                new Login(EXISTING_LOGIN, ""));
        dataCourierWithoutLogin
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
        @DisplayName("Check status code 404 and error message in non-existent data")
            @Description("\"Проверка что статус - код: 404, и ошибка при отправке запроса с несуществующими данными")
    public void testRequestNonExistentData() {
        CouriersClient courierClient = new CouriersClient();
        ValidatableResponse nonExistentData = courierClient.getLoginResponse(
                Login.returnRandomLogin());
        nonExistentData
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    public void tearDown() {
        deleteAccount();
    }
}