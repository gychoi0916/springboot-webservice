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
//    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
//    private static String KOREA_COVID_DATAS_URL = "http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=13";
//    private static String SAMSUNG_CHART_URL = "https://finance.naver.com/item/main.nhn?code=005930";
    private static String SAMSUNG_SISE_URL = "https://finance.naver.com/item/sise.nhn?code=005930";

    @PostConstruct
    public List<StockStats> getStock() throws IOException {

        List<StockStats> stockStatsList = new ArrayList<>();
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
                .build();
//            System.out.println(stockStats);
        System.out.println(stockStats);
        stockStatsList.add(stockStats);
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
        return stockStatsList;
    }
}
