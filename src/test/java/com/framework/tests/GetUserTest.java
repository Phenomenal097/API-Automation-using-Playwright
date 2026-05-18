package com.framework.tests;

import com.framework.basetest.BaseTest;
import com.framework.requestmodel.User;
import com.framework.utility.testdata.CreateUserPayload;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import tools.jackson.databind.ObjectMapper;

public class GetUserTest extends BaseTest {

    @Test
    public void getUser() {
        // POST user details -> GET user details
        ObjectMapper mapper = new ObjectMapper();

        //Request body
        User requestUser = CreateUserPayload.createUser();

        APIResponse apiPostResponse = apiRequestContext.post("https://gorest.co.in/public/v2/users",
                RequestOptions.create().setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer dfc49a493d3b5d3b458a2d14195499462b9f9c536ce7f123a70135d1aef45963")
                        .setHeader("Accept", "application/json")
                        .setData(requestUser));

        //De-serealize JSON -> Java
        com.framework.responsemodel.User userPostResponse = mapper.readValue(apiPostResponse.text(), com.framework.responsemodel.User.class);

        System.out.println(userPostResponse);

        //Get user id
        long userId = userPostResponse.getId();

        //Get user details - GET API
        APIResponse apiGetResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users/" + userId,
                RequestOptions.create().setHeader("Content-Type", "application/json")
                .setHeader("Authorization", "Bearer dfc49a493d3b5d3b458a2d14195499462b9f9c536ce7f123a70135d1aef45963"));

        //De-serealize JSON -> Java
        com.framework.responsemodel.User userGetResponse = mapper.readValue(apiGetResponse.text(), com.framework.responsemodel.User.class);

        System.out.println(userGetResponse);

        //Validate status codes and text
        Assert.assertEquals(apiGetResponse.status(), 200);
        Assert.assertEquals(apiGetResponse.statusText(), "OK");

        //Validate fields created or not
        Assert.assertEquals(userId, userGetResponse.getId());
        Assert.assertEquals(userPostResponse.getName(), userGetResponse.getName());
        Assert.assertEquals(userPostResponse.getEmail(), userGetResponse.getEmail());
        Assert.assertEquals(userPostResponse.getGender(), userGetResponse.getGender());
        Assert.assertEquals(userPostResponse.getStatus(), userGetResponse.getStatus());
    }
}
