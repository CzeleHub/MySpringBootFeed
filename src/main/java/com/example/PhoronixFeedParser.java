package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PhoronixFeedParser {
    public List<PhoronixArticle> parseArticles(Elements articles) {
        List<PhoronixArticle> feed = new ArrayList<PhoronixArticle>();

        for (Element article : articles) {

            Element comment = article.selectFirst("span.comments");
            Element header = article.selectFirst("header");
            Element img = article.selectFirst("img");
            Element details = article.selectFirst("p");
            Element a_article = article.selectFirst("a");

            if (Stream.of(comment, header, img, details, a_article).anyMatch(Objects::isNull)) {
                continue;
            }

            int comment_val = getCommentValue(comment.text());
            String header_s = header.text();
            String img_s = img.attr("src");
            String details_s = details.text();
            String ahref = a_article.attr("href");

            feed.add(new PhoronixArticle(header_s, comment_val, img_s, details_s, ahref));
        }
        return feed;
    }

    private int getCommentValue(String comment) {
        return Stream.of(comment.split(" ")).map(s -> {
            try {
                return Optional.of(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                return Optional.<Integer>empty();
            }
        }).flatMap(Optional::stream).findAny().orElse(0);
    }
}
