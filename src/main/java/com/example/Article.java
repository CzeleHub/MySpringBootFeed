package com.example;

import java.time.ZonedDateTime;

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
}
