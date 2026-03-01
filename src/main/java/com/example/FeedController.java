package com.example;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeedController {
    
    @GetMapping("/feed")
    public String getFeed(Model model) {
        List<PhoronixArticle> feed = new FeedReader().readFeed();
        model.addAttribute("feed", feed);
        return "phoronix_feed";
    }

}
