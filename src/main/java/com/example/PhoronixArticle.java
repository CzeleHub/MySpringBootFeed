package com.example;

public class PhoronixArticle {
    String title;
    int comments;
    String img;
    String details;
    String href;

    PhoronixArticle(String title, int comments, String img, String details, String href) {
        this.title = title;
        this.comments = comments;
        this.img = img;
        this.details = details;
        this.href = "https://www.phoronix.com" + href;
    }

    public String getTitle() {
        return this.title;
    }

    public int getComments() {
        return this.comments;
    }

    public String getDetails() {
        return this.details;
    }

    public String getImg() {
        return this.img;
    }

    public String getHref() {
        return this.href;
    }
}
