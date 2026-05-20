package com.framework.tests;

import com.framework.basetest.BaseTest;
import com.framework.responsemodel.User;
import com.framework.utility.testdata.CreateUserPayload;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import tools.jackson.databind.ObjectMapper;
import java.util.List;

public class GetUsersTest extends BaseTest {

    @Test
    public void getUsers() {

        // POST user details -> GET user details
        ObjectMapper mapper = new ObjectMapper();

        //Request body
        com.framework.requestmodel.User requestUser = CreateUserPayload.createUser();

        APIResponse apiPostResponse = apiRequestContextThreadLocal.get().post("https://gorest.co.in/public/v2/users",
                RequestOptions.create().setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer dfc49a493d3b5d3b458a2d14195499462b9f9c536ce7f123a70135d1aef45963")
                        .setHeader("Accept", "application/json")
                        .setData(requestUser));

        //De-serealize JSON -> Java
        com.framework.responsemodel.User userPostResponse = mapper.readValue(apiPostResponse.text(), com.framework.responsemodel.User.class);

        System.out.println(userPostResponse);

        //Get user id
        long userId = userPostResponse.getId();

        //Get all user details - GET API
        APIResponse getUsersResponse = apiRequestContextThreadLocal.get().get("https://gorest.co.in/public/v2/users",
                RequestOptions.create().setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer dfc49a493d3b5d3b458a2d14195499462b9f9c536ce7f123a70135d1aef45963"));

        //De-serialise JSON -> JAVA
        String getUsersResponseText = getUsersResponse.text();
        List<User> allUsers = List.of(mapper.readValue(getUsersResponseText, User[].class));

        System.out.println(allUsers);

        //Validate status code
        Assert.assertEquals(getUsersResponse.status(), 200);

        //Validate status text
        Assert.assertEquals(getUsersResponse.statusText(), "OK");

        //Validate fields created or not
        boolean isUserIdExists = allUsers.stream()
                .anyMatch((user) -> user.getId() == userId);

        Assert.assertTrue(isUserIdExists);
    }
}
