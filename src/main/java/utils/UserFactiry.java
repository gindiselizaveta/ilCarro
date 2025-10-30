package utils;

import dto.User;
import net.datafaker.Faker;

public class UserFactiry {
    static Faker faker = new Faker();

    public static User positiveUser() {
        User user = User.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .username(faker.internet().emailAddress())
                .password("wertY!23")
                .build();
        return user;
    }
}
