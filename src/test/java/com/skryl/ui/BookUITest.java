package com.skryl.ui;

import com.microsoft.playwright.*;
import com.skryl.configuration.ApplicationConfig;
import com.skryl.ui.pages.LoginPage;
import com.skryl.ui.pages.MainPage;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

@Slf4j
public class BookUITest {
    static Playwright playwright;
    static Browser browser;

    BrowserContext context;
    Page page;

    @BeforeAll
    static void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false).setSlowMo(150));
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page = context.newPage();
    }

    @AfterEach
    void closeContext(TestInfo testInfo) {
        var playwrightTestResultFolder = System.getProperty("user.dir") + "/build/playwright-results";
        var traceZip = "%s/%s.zip".formatted(playwrightTestResultFolder, testInfo.getDisplayName().replace(" ", "_"));
        log.info("Trace zip file path: %s".formatted(traceZip));
        context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get(traceZip)));
        context.close();
    }

    @Test
    @DisplayName("Test #1 User login and create book")
    void userLoginAndCreateBook() {
        var config = ConfigFactory.create(ApplicationConfig.class);
        var baseUrl = config.uiUrl();
        new LoginPage(page)
                .goToLoginPage(baseUrl)
                .enterUserName("test")
                .enterPassword("test")
                .clickLogin()
                .addNewBook()
                .enterBookName("Book1")
                .enterISBNnumber("11111-11111")
                .chooseCategory(MainPage.BookCategory.Magazine)
                .chooseFormat(MainPage.BookFormat.eBook)
                .createBook();
    }
}
