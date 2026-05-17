package com.framework.basetest;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected Playwright playwright;
    protected APIRequest apiRequest;
    protected APIRequestContext apiRequestContext;

    @BeforeTest
    public void setup() {
        playwright = Playwright.create();
        apiRequest = playwright.request();
        apiRequestContext = apiRequest.newContext();
    }

    @AfterTest
    public void teardown() {
        playwright.close();
    }
}
