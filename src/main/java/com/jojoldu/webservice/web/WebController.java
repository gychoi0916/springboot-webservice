package com.jojoldu.webservice.web;

import com.jojoldu.webservice.domain.crawl.StockStats;
import com.jojoldu.webservice.service.CrawlService;
import com.jojoldu.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2017. 12. 31.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "main";
    }
    private final CrawlService crawlService;

    @GetMapping("/stock")
    public String stock(Model model) throws IOException {
        List<StockStats> stockStatsList = crawlService.getStock();
//        model.addAttribute("stocks",stockStatsList);
        model.addAttribute("stocks",stockStatsList);
//        System.out.println(stockStatsList.size());
        return "stock";
    }
}
