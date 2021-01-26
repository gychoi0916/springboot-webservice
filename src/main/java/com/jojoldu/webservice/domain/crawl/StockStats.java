package com.jojoldu.webservice.domain.crawl;

import lombok.ToString;
import lombok.Getter;
import lombok.Builder;

@ToString
@Builder
@Getter
public class StockStats {
    private String url;

    private String name;

    private int presentPrice;

    private int diffFromPrevDay;

    private int marketPrice;

    private int high;

    private int low;
//    private int diffFromPrevDay; // 전일대비확진환자증감
//
//    private int total; // 확진환자수
//
//    private int death; // 사망자수

//    private double incidence; // 발병률

//    private int inspection; // 일일 검사환자 수

}
