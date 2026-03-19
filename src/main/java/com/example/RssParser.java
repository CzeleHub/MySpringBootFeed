package com.example;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.example.article.Article;
import com.example.article.ArticleBuilder;

class RssParser {
    public List<Article> parseItems(Elements items) {
        List<Article> feed = Collections.synchronizedList(new ArrayList<Article>());

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (Element item : items) {
                executor.submit(() -> {
                    parseItem(item).ifPresent((article) -> feed.add(article));
                    return null;
                });
            }
        }

        feed.sort((a1, a2) -> {
            return a2.getZonedDateTimePublicationDate().compareTo(a1.getZonedDateTimePublicationDate());
        });

        return feed;
    }

    private Optional<Article> parseItem(Element item) {
        Element publicationDateElement = item.selectFirst("pubDate");
        Element titleElement = item.selectFirst("title");
        Element linkElement = item.selectFirst("link");
        Element descriptionElement = item.selectFirst("description");

        if (Stream.of(publicationDateElement, titleElement, linkElement, descriptionElement)
                .anyMatch(Objects::isNull)) {
            return Optional.empty();
        }

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("EEE, dd MMM yyyy HH:mm:ss Z")
                .toFormatter(Locale.ENGLISH);

        ZonedDateTime publicationDate = ZonedDateTime.parse(publicationDateElement.text(), formatter);

        String title = titleElement.text();
        String description = Jsoup.parse(Parser.unescapeEntities(descriptionElement.text(), false)).text();
        String link = linkElement.text();

        return Optional.of(new ArticleBuilder()
                .setPublicationDate(publicationDate)
                .setTitle(title)
                .setDescription(description)
                .setLink(link)
                .Build());
    }

}