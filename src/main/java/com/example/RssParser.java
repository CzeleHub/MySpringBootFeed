package com.example;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.article.Article;
import com.example.article.ArticleBuilder;

class RssParser {
    public List<Article> parseItems(Elements items) {
        List<Article> feed = new ArrayList<Article>();

        for (Element item : items) {
            Element publicationDateElement = item.selectFirst("pubDate");
            Element titleElement = item.selectFirst("title");
            Element linkElement = item.selectFirst("link");
            Element descriptionElement = item.selectFirst("description");
            
            if (Stream.of(publicationDateElement, titleElement, linkElement, descriptionElement).anyMatch(Objects::isNull)) {
                System.out.println("empty");
                continue;
            }

            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("EEE, dd MMM yyyy HH:mm:ss Z")
                    .toFormatter(Locale.ENGLISH);

            ZonedDateTime publicationDate = ZonedDateTime.parse(publicationDateElement.text(), formatter);
            String title = titleElement.text();
            String description = descriptionElement.text();
            String link = linkElement.text();

            feed.add(new ArticleBuilder()
                    .setPublicationDate(publicationDate)
                    .setTitle(title)
                    .setDescription(description)
                    .setLink(link)
                    .Build());
        }

        return feed;
    }
}