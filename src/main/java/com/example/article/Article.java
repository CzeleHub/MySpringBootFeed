package com.example.article;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Article {
    private ZonedDateTime publicationDate;
    private String title;
    private String link;
    private String description;
    private String image;
    private Integer comments;

    public String getPublicationDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "EEE, dd MMM yyyy",
                Locale.ENGLISH);

        return publicationDate.format(formatter);
    }
}
