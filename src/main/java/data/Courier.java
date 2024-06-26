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

    public static Courier returnRandomCourier () {
        return new Courier(RandomStringUtils.randomAlphabetic(10) + "", "123456", "Harry");
    }
}
