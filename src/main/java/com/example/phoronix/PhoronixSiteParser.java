package com.example.phoronix;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.article.Article;
import com.example.article.ArticleBuilder;

public class PhoronixSiteParser {
    public List<Article> parseArticles(Elements articles) {
        List<Article> feed = new ArrayList<Article>();

        for (Element article : articles) {

            Element commentElement = article.selectFirst("span.comments");
            Element headerElement = article.selectFirst("header");
            Element imageElement = article.selectFirst("img");
            Element descriptionElement = article.selectFirst("p");
            Element aArticleElement = article.selectFirst("a");

            if (Stream.of(commentElement, headerElement, imageElement, descriptionElement, aArticleElement)
                    .anyMatch(Objects::isNull)) {
                continue;
            }

            int comments = getCommentValue(commentElement.text());
            String title = headerElement.text();
            String image = imageElement.attr("src");
            String description = descriptionElement.text();
            String link = "https://www.phoronix.com/" + aArticleElement.attr("href");

            feed.add(new ArticleBuilder()
                    .setTitle(title)
                    .setImage(image)
                    .setDescription(description)
                    .setLink(link)
                    .setComments(comments)
                    .Build());
        }
        return feed;
    }

    private Integer getCommentValue(String comment) {
        return Stream.of(comment.split(" ")).map(s -> {
            try {
                return Optional.of(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                return Optional.<Integer>empty();
            }
        }).flatMap(Optional::stream).findAny().orElse(0);
    }
}
