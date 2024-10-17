package com.skryl.api.book;

import com.google.gson.JsonObject;
import com.skryl.model.book.LoggedInUserResponse;
import io.restassured.http.Cookies;

public class BookApiStep {
    private BookApi bookAppApi;
    private Cookies cookies;

    public BookApiStep(BookApi bookAppApi) {
        this.bookAppApi = bookAppApi;
    }

    public LoggedInUserResponse login(String username, String password) {
        var body = new JsonObject();
        body.addProperty("password", password);
        body.addProperty("username", username);
        var response = bookAppApi.login(body);
        response.then().statusCode(200);
        cookies = response.getDetailedCookies();
        return response.as(LoggedInUserResponse.class);
    }

    public Boolean loginAccount() {
        var response = bookAppApi.loginAccount(cookies);
        response.then().statusCode(200);
        return response.getBody().as(Boolean.class);
    }

    public Boolean loginStatus() {
        var response = bookAppApi.loginStatus(cookies);
        response.then().statusCode(200);
        return response.getBody().as(Boolean.class);
    }

    public void logout() {
        var response = bookAppApi.logout(cookies);
        response.then().statusCode(200);
    }

}
