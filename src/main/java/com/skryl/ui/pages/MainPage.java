package com.skryl.ui.pages;

import com.microsoft.playwright.Page;

/**
 * @author Skryl D.V. on 2022-04-16
 */
public class MainPage{
    private final Page page;

    public MainPage(Page page) {
        this.page = page;
    }

    public enum BookCategory {
        TechnicalBook("Technical Book"), Magazine("Magazine"), Novel("Novel");
        private final String category;

        public String getCategoryName() {
            return category;
        }

        BookCategory(String category) {
            this.category = category;
        }
    }

    public enum BookFormat {
        PaperBook("Paper Book"), eBook("e-Book");
        private final String format;

        public String getFormatName() {
            return format;
        }

        BookFormat(String format) {
            this.format = format;
        }
    }

    public MainPage addNewBook() {
        page.click("button:has-text(\"add\")");
        return this;
    }

    public MainPage enterBookName(String bookName) {
        String selector = ".md-field:has-text(\"Title\") > input";
        page.locator(selector).click();
        page.locator(selector).fill(bookName);
        return this;
    }

    public MainPage enterISBNnumber(String number) {
        String selector = ".md-field:has-text(\"ISBN\") > input";
        page.click(selector);
        page.fill(selector, number);
        return this;
    }

    public MainPage chooseCategory(BookCategory bookCategory) {
        page.click("text=Category Technical Book Magazine Novel >> input[type=\"text\"]");
        page.click("button:has-text(\""+bookCategory.getCategoryName()+"\")");
        return this;
    }

    public MainPage chooseFormat(BookFormat bookFormat) {
        page.click("text=Format Paper Book e-Book >> input[type=\"text\"]");
        page.click("button:has-text(\""+bookFormat.getFormatName()+"\")");
        return this;
    }

    public MainPage createBook() {
        page.click("button:has-text(\"Create\")");
        page.click(":nth-match(button:has-text(\"OK\"), 4)");
        return this;
    }

    public MainPage startEdit() {
        page.click("button:has-text(\"Edit\")");
        return this;
    }

    public MainPage stopEdit() {
        page.click("button:has-text(\"Edit\")");
        page.click(":nth-match(button:has-text(\"OK\"), 4)");
        return this;
    }

    public MainPage searchBook(String bookName) {
        page.click("input[type=\"text\"]");
        page.fill("input[type=\"text\"]", bookName);
        page.press("input[type=\"text\"]", "Enter");
        return this;
    }

}
