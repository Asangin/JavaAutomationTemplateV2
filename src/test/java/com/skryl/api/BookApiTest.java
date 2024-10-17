package com.skryl.api;

import com.skryl.api.book.BookApi;
import com.skryl.api.book.BookApiStep;
import com.skryl.configuration.ApplicationConfig;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class BookApiTest {
    private static BookApiStep bookApiStep;

    @BeforeAll
    static void restAssuredSetup() {
        var config = ConfigFactory.create(ApplicationConfig.class);
        var baseUrl = config.uiUrl();
        bookApiStep = new BookApiStep(new BookApi(baseUrl));
    }

    @Test
    @DisplayName("[API] User Login To BookApp")
    void userLoginToBookApp() {
        log.info("Test started");
        var user = bookApiStep.login("test", "test");
        assertThat(user.getId()).isEqualTo(1);

        var actualStatus = bookApiStep.loginStatus();
        assertThat(actualStatus)
                .as("User should be logged in")
                .isTrue();
    }
}
