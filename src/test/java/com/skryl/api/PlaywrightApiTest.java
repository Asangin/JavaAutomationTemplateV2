package com.skryl.api;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import com.skryl.configuration.ApplicationConfig;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlaywrightApiTest {

    private Playwright playwright;
    private APIRequestContext request;

    void createPlaywright() {
        playwright = Playwright.create();
    }

    void createAPIRequestContext() {
        var config = ConfigFactory.create(ApplicationConfig.class);
        Map<String, String> headers = new HashMap<>();
        // We set this header per GitHub guidelines.
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");

        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                // All requests we send go to this API endpoint.
                .setBaseURL(config.uiUrl())
                .setExtraHTTPHeaders(headers));
    }

    @BeforeAll
    void beforeAll() {
        createPlaywright();
        createAPIRequestContext();
    }

    void disposeAPIRequestContext() {
        if (request != null) {
            request.dispose();
            request = null;
        }
    }

    void closePlaywright() {
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }

    @AfterAll
    void afterAll() {
        disposeAPIRequestContext();
        closePlaywright();
    }

    @Test
    @DisplayName("[UI] Login to Book App")
    void loginToBookStore() {
        var data = Map.of(
                "username", "test",
                "password", "test"
        );
        APIResponse loginResponse = request.post( "/api/auth/login", RequestOptions.create().setData(data));
        log.info("Login response %s".formatted(loginResponse.text()));
        assertThat(loginResponse.ok()).isTrue();

        log.info("Request state storage: %s".formatted(request.storageState()));
        APIResponse loginAccountResponse = request.get("/auth/loginAccount");
        log.info("Login account response: %s".formatted(loginAccountResponse.text()));
        assertThat(loginResponse.ok()).isTrue();
    }

}
