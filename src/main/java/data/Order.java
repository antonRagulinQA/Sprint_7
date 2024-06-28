package data;

import java.util.List;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order implements Constants {
        private String firstName;
        private String lastName;
        private String address;
        private int metroStation;
        private String phone;
        private int rentTime;
        private String deliveryDate;
        private String comment;
        private List<String> color;
}
