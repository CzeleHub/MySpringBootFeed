package com.example;

import lombok.Getter;

@Getter
public class PhoronixArticle {
    private String title;
    private int comments;
    private String img;
    private String details;
    private String href;

    PhoronixArticle(String title, int comments, String img, String details, String href) {
        this.title = title;
        this.comments = comments;
        this.img = img;
        this.details = details;
        this.href = "https://www.phoronix.com" + href;
    }
}
