package com.example;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.example.article.Article;
import com.example.phoronix.PhoronixSiteParser;

public class FeedReader {
    public List<Article> readRssFeed(String url) {
        Document doc;
        try {
            doc = (Document) Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Article>();
        }
        
        Elements items = doc.select("item");

        List<Article> feed = new RssParser().parseItems(items);
        
        return feed;
    }

    public List<Article> readSiteFeed(String url) {
        Document doc;
        try {
            // doc = Jsoup.parse(new File("src/main/resources/test/example_feed.html"));
            doc = (Document) Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Article>();
        }
        
        // TODO enums
        Elements articles = doc.select("article");

        List<Article> feed = new PhoronixSiteParser().parseArticles(articles);
        
        return feed;
    }
}
