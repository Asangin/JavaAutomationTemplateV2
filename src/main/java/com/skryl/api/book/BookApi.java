package com.skryl.api.book;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.mapper.ObjectMapperType.GSON;

public class BookApi {
    private RequestSpecification requestSpec;

    public BookApi(String baseUrl) {
        requestSpec = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/api")
                .config(new RestAssuredConfig().objectMapperConfig(new ObjectMapperConfig(GSON)))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .filter(new AllureRestAssured());
    }

    public Response login(JsonElement body) {
        return RestAssured.given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(body)
                .post("/auth/login");
    }

    public Response loginAccount(Cookies cookies) {
        return RestAssured.given()
                .spec(requestSpec)
                .cookies(cookies)
                .get("/auth/loginAccount");
    }

    public Response loginStatus(Cookies cookies) {
        return RestAssured.given()
                .spec(requestSpec)
                .cookies(cookies)
                .get("/auth/loginStatus");
    }

    public Response logout(Cookies cookies) {
        return RestAssured.given()
                .spec(requestSpec)
                .cookies(cookies)
                .post("/auth/logout");
    }
}
