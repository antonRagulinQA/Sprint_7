package data;

import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Login {
    private String login;
    private String password;

    public static Login returnRandomLogin() {
        return new Login(RandomStringUtils.randomAlphabetic(10) + "",
                RandomStringUtils.randomAlphabetic(10) + ""
        );
    }
}
