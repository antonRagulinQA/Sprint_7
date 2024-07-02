import clients.CouriersClient;
import data.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static clients.CouriersClient.deleteAccount;
import static data.Courier.*;
import static org.hamcrest.Matchers.equalTo;


public class CreateCourierTest  {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        CouriersClient.createAccount();
    }

    @Test
    @DisplayName("Create identical couriers")
    @Description("Проверка что статус-код: 409, при содании двух одинаковых аккаунов")
    public void testErrorMessageForDuplicateLogin() {
        CouriersClient couriersClient = new CouriersClient();
        ValidatableResponse duplicateLogin =
                couriersClient.getCourierResponse(new Courier
                        (EXISTING_LOGIN, EXISTING_PASSWORD, EXISTING_FIRSTNAME));
        duplicateLogin
                .statusCode(409)
                .assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

    }

    @Test
    @DisplayName("No login authorization")
    @Description("Проверка ошибки и статус - кода: 400, если поле \"login\" - пустое")
    public void testErrorMessageForRequestWithoutLogin() {
        CouriersClient couriersClient = new CouriersClient();
        ValidatableResponse emptyLoginField = couriersClient.
                getCourierResponse(new Courier
                        (null, EXISTING_PASSWORD, EXISTING_FIRSTNAME));
        emptyLoginField
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

    }

    @Test
    @DisplayName("Create valid fields courier")
    @Description("Проверка что аккаунт создан и статус - код: 201 с сообщением: ОК")
    public void testCreateAccountAndMessageOk() {
        CouriersClient couriersClient = new CouriersClient();
        ValidatableResponse crateRandomCourier = couriersClient.getCourierResponse(
                Courier.returnRandomCourier());
        crateRandomCourier
                .statusCode(201)
                .assertThat()
                .body("ok", equalTo(true));
    }

    @After
    public void tearDown() {
        deleteAccount();
    }
}
