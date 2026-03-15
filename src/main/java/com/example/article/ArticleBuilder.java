package com.example.article;

import java.time.ZonedDateTime;

public class ArticleBuilder {
    private ZonedDateTime publicationDate;
    private String title;
    private String link;
    private String description;
    private String image;
    private Integer comments;

    public Article Build() {
        return new Article(this.publicationDate, this.title, this.link, this.description, this.image, this.comments);
    }

    public ArticleBuilder setPublicationDate(ZonedDateTime publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public ArticleBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ArticleBuilder setLink(String link) {
        this.link = link;
        return this;
    }

    public ArticleBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ArticleBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public ArticleBuilder setComments(Integer comments) {
        this.comments = comments;
        return this;
    }
}
