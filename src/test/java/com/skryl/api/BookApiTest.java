package com.skryl.api;

import com.skryl.api.book.BookApi;
import com.skryl.api.book.BookApiStep;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookApiTest {
    private static BookApiStep bookApiStep;

    @BeforeAll
    static void restAssuredSetup() {
        var baseUrl = "http://localhost:8081";
        bookApiStep = new BookApiStep(new BookApi(baseUrl));
    }

    @Test
    void userLoginToBookApp() {
        var user = bookApiStep.login("test", "test");
        assertThat(user.getId()).isEqualTo(1);

        var actualStatus = bookApiStep.loginStatus();
        assertThat(actualStatus)
                .as("User should be logged in")
                .isTrue();
    }
}
