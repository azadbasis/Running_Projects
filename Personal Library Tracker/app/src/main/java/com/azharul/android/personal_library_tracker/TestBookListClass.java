package com.azharul.android.personal_library_tracker;

/**
 * Created by Anup on 17-Mar-16.
 */
public class TestBookListClass {

    private String bookName;
    private String bookCategory;

    public TestBookListClass(String bookName, String bookCategory) {
        this.bookName = bookName;
        this.bookCategory = bookCategory;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }
}
