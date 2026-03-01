package com.example;


import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class FeedReader {
    public List<PhoronixArticle> readFeed() {
        Document doc;
        try {
            // doc = Jsoup.parse(new File("src/main/resources/test/example_feed.html"));
            doc = (Document) Jsoup.connect("https://www.phoronix.com/").get();
        } catch (IOException e) {
            e.printStackTrace();
            doc = Jsoup.parse("");
        }
        
        Elements articles = doc.select("article");

        List<PhoronixArticle> feed = new PhoronixFeedParser().parseArticles(articles);
        
        return feed;
    }
}
