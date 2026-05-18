package com.framework.tests;

import com.framework.basetest.BaseTest;
import com.framework.responsemodel.User;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import tools.jackson.databind.ObjectMapper;
import java.util.List;

public class GetUsersTest extends BaseTest {

    @Test
    public void getUsers() {
        APIResponse getUsersResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users",
                RequestOptions.create().setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer dfc49a493d3b5d3b458a2d14195499462b9f9c536ce7f123a70135d1aef45963"));

        //De-serialise JSON -> JAVA
        String getUsersResponseText = getUsersResponse.text();
        ObjectMapper mapper = new ObjectMapper();
        List<User> allUsers = List.of(mapper.readValue(getUsersResponseText, User[].class));

        //Validate status code
        Assert.assertEquals(getUsersResponse.status(), 200);

        //Validate status text
        Assert.assertEquals(getUsersResponse.statusText(), "OK");
    }
}
