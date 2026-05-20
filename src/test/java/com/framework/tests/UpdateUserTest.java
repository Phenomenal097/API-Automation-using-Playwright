package com.framework.tests;

import com.framework.basetest.BaseTest;
import com.framework.responsemodel.User;
import com.framework.utility.enums.Gender;
import com.framework.utility.enums.Status;
import com.framework.utility.testdata.CreateUserPayload;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import tools.jackson.databind.ObjectMapper;

public class UpdateUserTest  extends BaseTest {

    @Test
    public void updateUser() {
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

        requestUser.setGender(Gender.FEMALE.getValue());
        requestUser.setEmail("User" + userId + "@gmail.com");
        requestUser.setStatus("Inactive");

        //Update user API
        APIResponse apiPutResponse = apiRequestContextThreadLocal.get().put("https://gorest.co.in/public/v2/users/" + userId,
                RequestOptions.create().setHeader("Content-Type", "application/json")
                        .setHeader("Accept", "application/json")
                        .setHeader("Authorization", "Bearer dfc49a493d3b5d3b458a2d14195499462b9f9c536ce7f123a70135d1aef45963")
                        .setData(requestUser));

        //De-serealize JSON -> Java
        com.framework.responsemodel.User userPutResponse = mapper.readValue(apiPutResponse.text(), com.framework.responsemodel.User.class);

        //Print updated response
        System.out.println(userPutResponse);

        //Assert updated values or values meant to be same
        Assert.assertEquals(userPostResponse.getId(), userId);
        Assert.assertEquals(userPutResponse.getName(), userPostResponse.getName());
        Assert.assertEquals(userPutResponse.getEmail(), "User" + userId + "@gmail.com");
        Assert.assertEquals(userPutResponse.getGender(), Gender.FEMALE.getValue());
        Assert.assertEquals(userPutResponse.getStatus(), Status.INACTIVE.getValue());
    }
}