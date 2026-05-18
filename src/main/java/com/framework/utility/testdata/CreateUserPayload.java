package com.framework.utility.testdata;

import com.framework.requestmodel.User;
import com.framework.utility.enums.Gender;
import com.framework.utility.enums.Status;
import java.util.Random;

public class CreateUserPayload {
    static Random random = new Random();

    public static User createUser() {
        return User.builder().name("User" + System.currentTimeMillis())
                .email("User" + System.currentTimeMillis() + "@gmail.com")
                .gender(Gender.MALE.getValue())
                .status(Status.ACTIVE.getValue()).build();
    }
}
