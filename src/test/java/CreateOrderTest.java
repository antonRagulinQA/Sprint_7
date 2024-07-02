import clients.OrdersClient;
import data.Order;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;

import lombok.*;

@RunWith(Parameterized.class)
@AllArgsConstructor
public class CreateOrderTest {
    private final String firstNameValue;
    private final String lastNameValue;
    private final String addressValue;
    private final int metroStationValue;
    private final String phoneValue;
    private final int rentTimeValue;
    private final String deliveryDateValue;
    private final String commentValue;
    private final List<String> colorValue;


    @Parameterized.Parameters
    public static Object[][] getTestDataCreateOrder() {
        return new Object[][]{
                {"Anton", "Bekker", "London", 4, "+7 800 123 45 67", 5, "2024-06-06", "Пожалуйста принесите Чёрный", List.of("BLACK")},
                {"Anton", "Bekker", "London", 4, "+7 800 123 45 67", 5, "2024-06-06", "Пожалуйста принесите Серый", List.of("GREY")},
                {"Anton", "Bekker", "London", 4, "+7 800 123 45 67", 5, "2024-06-06", "Цвет не важен", null},
                {"Anton", "Bekker", "London", 4, "+7 800 123 45 67", 5, "2024-06-06", "Хоти все цвета", List.of("GREY", "BLACK")},

        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @DisplayName("Проверка что заказ создан и статус - код: 201 с помощью параметризированных тестов ")
    @Test
    public void testTrackFieldInOrder() {
        OrdersClient ordersClient = new OrdersClient();
        ValidatableResponse emptyPasswordField = ordersClient.getOrdersResponse(
                new Order(firstNameValue, lastNameValue, addressValue,
                        metroStationValue, phoneValue, rentTimeValue, deliveryDateValue, commentValue, colorValue));
        emptyPasswordField
                .statusCode(201);
        MatcherAssert.assertThat("track", notNullValue());
    }
}
