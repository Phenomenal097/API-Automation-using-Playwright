package com.framework.tests;

import com.framework.basetest.BaseTest;
import com.framework.requestmodel.User;
import com.framework.utility.testdata.CreateUserPayload;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class CreateUserTest extends BaseTest {

    @Test
    public void createUser() {

        ObjectMapper mapper = new ObjectMapper();

        //Request body
        User requestUser = CreateUserPayload.createUser();

        APIResponse apiResponse = apiRequestContextThreadLocal.get().post("https://gorest.co.in/public/v2/users",
                RequestOptions.create().setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer dfc49a493d3b5d3b458a2d14195499462b9f9c536ce7f123a70135d1aef45963")
                        .setHeader("Accept", "application/json")
                        .setData(requestUser));

        JsonNode jsonData = mapper.readTree(apiResponse.body());

        //Print JSON response
        System.out.println(jsonData.toPrettyString());

        //De-serealize JSON -> Java
        com.framework.responsemodel.User userResponse = mapper.readValue(apiResponse.text(), com.framework.responsemodel.User.class);

        //Assert status code
        Assert.assertEquals(apiResponse.status(), 201);
        Assert.assertEquals(apiResponse.statusText(), "Created");

        System.out.println(userResponse);
    }
}
