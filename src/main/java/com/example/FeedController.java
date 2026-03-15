package com.example;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.article.Article;

@Controller
public class FeedController {

    @GetMapping("/rss")
    public String getGenericFeed(Model model, @RequestParam String url) {
        List<Article> feed = new FeedReader().readRssFeed(url);
        model.addAttribute("feed", feed);
        return "feed";
    }
    
    @GetMapping("/site")
    public String getSiteFeed(Model model, @RequestParam String url) {
        List<Article> feed = new FeedReader().readSiteFeed(url);
        model.addAttribute("feed", feed);
        return "feed";
    }
}
