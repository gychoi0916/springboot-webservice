package com.jojoldu.webservice.service;

import com.jojoldu.webservice.domain.crawl.StockStats;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlService {

//    @PostConstruct
    public static StockStats addStock(String code) throws IOException {

        String SAMSUNG_SISE_URL = "https://finance.naver.com/item/sise.nhn?code="+code;
//        List<StockStats> stockStatsList = new ArrayList<>();
//        List<StockStats> stockStatsList = new ArrayList<StockStats>();
//        Document doc = Jsoup.connect(SAMSUNG_CHART_URL).get();
        Document doc2 = Jsoup.connect(SAMSUNG_SISE_URL).get();
        Elements img = doc2.select("div[class=chart] img");
        Elements company = doc2.select("div[class=wrap_company] h2 a");
        Elements contents = doc2.select("table[class=type2 type_tax] tbody");
//        Elements tdContents = valselect("td");
//        System.out.println(contents);
//        System.out.println(contents.select("td"));
        Elements tdContents = contents.select("td");
//            System.out.println(tdContents);
        Elements flag = contents.select("em");
        int sign = 1;
        if (flag.attr("class").contains("dn")) sign = -1;
        StockStats stockStats = StockStats.builder()
                .name(company.text())
                .presentPrice(Integer.parseInt(tdContents.get(0).text().replaceAll("[^0-9]", "")))
                .diffFromPrevDay(sign*Integer.parseInt(tdContents.get(2).text().replaceAll("[^0-9]","")))
                .marketPrice(Integer.parseInt(tdContents.get(7).text().replaceAll("[^0-9]", "")))
                .high(Integer.parseInt(tdContents.get(9).text().replaceAll("[^0-9]","")))
                .low(Integer.parseInt(tdContents.get(11).text().replaceAll("[^0-9]","")))
                .url(img.attr("src"))
                .code(code)
                .build();
//            System.out.println(stockStats);
//        System.out.println(stockStats);
//        stockStatsList.add(stockStats);
//        for(Element content : contents){
////            System.out.println(content);
//            Elements tdContents = content.select("td");
////            System.out.println(tdContents);
//            Elements flag = content.select("em");
//            int sign = 1;
//            if (flag.attr("class").contains("dn")) sign = -1;
//            StockStats stockStats = StockStats.builder()
//                    .Name(company.text())
//                    .diffFromPrevDay(Integer.parseInt(tdContents.get(2).text().replaceAll("[^0-9]","")))
//                    .high(Integer.parseInt(tdContents.get(9).text().replaceAll("[^0-9]","")))
//                    .low(Integer.parseInt(tdContents.get(11).text().replaceAll("[^0-9]","")))
//                    .url(img.attr("src"))
//                    .build();
////            System.out.println(stockStats);
//            stockStatsList.add(stockStats);
//        }
//        for(Element content : contents){
//            Elements tdContents = content.select("td");
//
//            StockStats koreaStats = StockStats.builder()
////                    .country(content.select("th").text())
////                    .diffFromPrevDay(Integer.parseInt(tdContents.get(0).text().replaceAll("[^0-9]","")))
////                    .total(Integer.parseInt(tdContents.get(3).text().replaceAll("[^0-9]","")))
////                    .death(Integer.parseInt(tdContents.get(6).text().replaceAll("[^0-9]","")))
////                    .incidence(Double.parseDouble(tdContents.get(7).text().replaceAll("[^0-9]","")))
////                    .inspection(Integer.parseInt(tdContents.get(4).text().replaceAll("[^0-9]","")))
//                    .build();
////            System.out.println(koreaStats.toString());
//            koreaStatsList.add(koreaStats);
//        }
        return stockStats;
    }
    public static List<StockStats> getStockData() throws IOException {
        List<StockStats> stocksList = new ArrayList<>();
        String companys[] = {"005930","035420","003550"};
        for(int i = 0; i<companys.length; i++){
            stocksList.add(addStock(companys[i]));
        }
        return stocksList;
    }
    public static List<StockStats> getStock(String code) throws IOException {
        List<StockStats> stockList = new ArrayList<>();
        stockList.add(addStock(code));
        return stockList;
    }
}
