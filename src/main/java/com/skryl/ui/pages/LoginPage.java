package com.skryl.ui.pages;

import com.microsoft.playwright.Page;

/**
 * @author Skryl D.V. on 2022-04-16
 */
public class LoginPage {
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public LoginPage goToLoginPage(String url) {
        page.navigate(url + "/#/login");
        return this;
    }

    public LoginPage enterUserName(String name) {
        page.click("input[type=\"text\"]");
        page.fill("input[type=\"text\"]", name);
        return this;
    }

    public LoginPage enterPassword(String password) {
        page.click("input[type=\"password\"]");
        page.fill("input[type=\"password\"]", password);
        return this;
    }

    public MainPage clickLogin() {
        page.click("button:has-text(\"Log in\")");
        return new MainPage(page);
    }
}
