package com.framework.basetest;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected static final ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    protected static final ThreadLocal<APIRequest> apiRequestThreadLocal = new ThreadLocal<>();
    protected static final ThreadLocal<APIRequestContext> apiRequestContextThreadLocal = new ThreadLocal<>();

    @BeforeTest
    public void setup() {
        playwrightThreadLocal.set(Playwright.create());
        apiRequestThreadLocal.set(playwrightThreadLocal.get().request());
        apiRequestContextThreadLocal.set(apiRequestThreadLocal.get().newContext());
    }

    @AfterTest
    public void teardown() {
        apiRequestContextThreadLocal.get().dispose();
        playwrightThreadLocal.get().close();
    }
}
