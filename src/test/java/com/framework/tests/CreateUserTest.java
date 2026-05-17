package com.framework.tests;

import com.framework.basetest.BaseTest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.Test;

public class CreateUserTest extends BaseTest {

    @Test
    public void createUser() {
        APIResponse apiResponse = apiRequestContext.post("https://gorest.co.in/public/v2/users",
                RequestOptions.create().setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer dfc49a493d3b5d3b458a2d14195499462b9f9c536ce7f123a70135d1aef45963")
                        .setHeader("Accept", "application/json"));
    }
}
