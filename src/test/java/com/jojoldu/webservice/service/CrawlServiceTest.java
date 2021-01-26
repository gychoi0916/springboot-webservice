package com.jojoldu.webservice.service;

import com.jojoldu.webservice.domain.crawl.StockStats;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlServiceTest {
    @Autowired
    CrawlService crawlService;

    @Test
    public void getStock_동작테스트() throws IOException{

        //given
        List<StockStats> stockList = new ArrayList<>();

        //when
        stockList = crawlService.getStock();

        //then
        assertThat(stockList.get(0).getHigh()).isGreaterThan(0);
        assertThat(stockList.get(0).getLow()).isGreaterThan(0);
        assertThat(stockList.get(0).getDiffFromPrevDay()).isGreaterThan(0);
    }
}
