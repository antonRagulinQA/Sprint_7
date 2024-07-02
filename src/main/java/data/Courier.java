package data;

import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Courier {
    private String login;
    private String password;
    private String firstName;
    public static final String API_COURIER = "/api/v1/courier";
    public static final String API_LOGIN = "/api/v1/courier/login";
    public static final String EXISTING_LOGIN = "kotvaskya";
    public static final String EXISTING_PASSWORD = "123456";
    public static final String EXISTING_FIRSTNAME = "anton";

    public static Courier returnRandomCourier() {
        return new Courier(RandomStringUtils.randomAlphabetic(10) + "", "123456", "Harry");
    }
}
