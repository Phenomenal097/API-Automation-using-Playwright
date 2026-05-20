package com.framework.utility.testdata;

import com.framework.requestmodel.User;
import com.framework.utility.enums.Gender;
import com.framework.utility.enums.Status;
import java.util.UUID;

public class CreateUserPayload {

    public static User createUser() {
        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        return User.builder()
                .name("User" + uniqueId)
                .email("User" + uniqueId + "@gmail.com")
                .gender(Gender.MALE.getValue())
                .status(Status.ACTIVE.getValue())
                .build();
    }
}
