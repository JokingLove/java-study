package com.baomidou.mybatisplus.samples.crud.model;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.samples.crud.spider.entity.Stock;
import com.baomidou.mybatisplus.samples.crud.spider.entity.StockPrice;
import com.baomidou.mybatisplus.samples.crud.util.StringUtil;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotEmpty;
import static com.baomidou.mybatisplus.samples.crud.util.StringUtil.*;

/**
 * @author joking
 * <p>
 * 0：”大秦铁路”，股票名字；
 * 1：”27.55″，今日开盘价；
 * 2：”27.25″，昨日收盘价；
 * 3：”26.91″，当前价格；
 * 4：”27.55″，今日最高价；
 * 5：”26.20″，今日最低价；
 * 6：”26.91″，竞买价，即“买一”报价；
 * 7：”26.92″，竞卖价，即“卖一”报价；
 * 8：”22114263″，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
 * 9：”589824680″，成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
 * 10：”4695″，“买一”申请4695股，即47手；
 * 11：”26.91″，“买一”报价；
 * 12：”57590″，“买二”
 * 13：”26.90″，“买二”
 * 14：”14700″，“买三”
 * 15：”26.89″，“买三”
 * 16：”14300″，“买四”
 * 17：”26.88″，“买四”
 * 18：”15100″，“买五”
 * 19：”26.87″，“买五”
 * 20：”3100″，“卖一”申报3100股，即31手；
 * 21：”26.92″，“卖一”报价
 * (22, 23), (24, 25), (26,27), (28, 29)分别为“卖二”至“卖四的情况”
 * 30：”2008-01-11″，日期；
 * 31：”15:05:32″，时间；
 */
public class SinaRealTimeResult {

    Pattern pattern = Pattern.compile("var.*([0-9]{6})=\"(.*)\";");

    private String message;
    private List<List<String>> result;

    public SinaRealTimeResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<List<String>> getResult() {
        if (result == null) {
            if (isNotEmpty(message)) {
                this.result = new ArrayList<>();
                Matcher matcher = pattern.matcher(message);
                while (matcher.find()) {
                    String stockCode = matcher.group(1);
                    String group = matcher.group(2);
                    String[] split = group.split(",");
                    if (split.length > 1) {
                        List<String> list = new ArrayList<>(Arrays.asList(split));
                        list.add(0, stockCode);
                        this.result.add(list);
                    }
                }
            }
        }
        return result;
    }

    private String getStockCode(String group) {
        if (StringUtils.isNotEmpty(group)) {

        } else {
            throw new RuntimeException("解析 stockCode 出错！");
        }
        return null;
    }

    /*

     * 0 位置已经占位  stockCode 了
     * 0：”大秦铁路”，股票名字；
     * 1：”27.55″，今日开盘价；
     * 2：”27.25″，昨日收盘价；
     * 3：”26.91″，当前价格；
     * 4：”27.55″，今日最高价；
     * 5：”26.20″，今日最低价；
     * 6：”26.91″，竞买价，即“买一”报价；
     * 7：”26.92″，竞卖价，即“卖一”报价；
     * 8：”22114263″，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
     * 9：”589824680″，成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
     * 10：”4695″，“买一”申请4695股，即47手；
     * 11：”26.91″，“买一”报价；
     * 12：”57590″，“买二”
     * 13：”26.90″，“买二”
     * 14：”14700″，“买三”
     * 15：”26.89″，“买三”
     * 16：”14300″，“买四”
     * 17：”26.88″，“买四”
     * 18：”15100″，“买五”
     * 19：”26.87″，“买五”
     * 20：”3100″，“卖一”申报3100股，即31手；
     * 21：”26.92″，“卖一”报价
     * (22, 23), (24, 25), (26,27), (28, 29)分别为“卖二”至“卖四的情况”
     * 30：”2008-01-11″，日期；
     * 31：”15:05:32″，时间；
     */
    public static StockPrice buildStockPrice(List<String> list) {
        if (list != null && !list.isEmpty()) {
            StockPrice stockPrice = new StockPrice();
            stockPrice.setPriceDate(stringToLocalDate(list.get(31)));
            stockPrice.setStockCode(list.get(0));
            stockPrice.setOpenPrice(stringToFloat(list.get(2)));
            stockPrice.setClosePrice(stringToFloat(list.get(4)));
            stockPrice.setHighstPrice(stringToFloat(list.get(5)));
            stockPrice.setLowstPrice(stringToFloat(list.get(6)));
            stockPrice.setVolume(stringToBigDecimal(list.get(9)));

            stockPrice.setUpdateTime(ZonedDateTime.now().toLocalDateTime());
            return stockPrice;
        }
        return null;
    }

    @Override
    public String toString() {
        return "SinaRealTimeResult{" +
                "message='" + message + '\'' +
                '}';
    }

    public static void main(String[] args) {
        String str = "var hq_str_sh601006=\"大秦铁路,7.550,7.550,7.520,7.570,7.520,7.520,7.530,2356600,17759973.000,355500,7.520,362700,7.510,463500,7.500,262500,7.490,294000,7.480,37700,7.530,172099,7.540,300700,7.550,123200,7.560,263245,7.570,2019-10-09,09:50:16,00,\";";
        String aa = "var hq_str_sh601007=\"金陵饭店,9.660,9.680,9.610,9.660,9.550,9.600,9.610,427800,4101254.000,1000,9.600,3200,9.590,3200,9.580,10800,9.570,31000,9.560,1500,9.610,2400,9.620,21000,9.630,1800,9.640,1400,9.650,2019-10-09,14:00:05,00,\";";
        String text = "var hq_str_sh601007=\"金陵饭店,9.660,9.680,9.600,9.660,9.550,9.590,9.620,450600,4320158.000,7800,9.590,7200,9.580,15400,9.570,28300,9.560,20700,9.550,6400,9.620,21000,9.630,1400,9.650,22600,9.670,43800,9.680,2019-10-09,14:19:23,00,\";\n" +
                "var hq_str_sh601006=\"大秦铁路,7.550,7.550,7.600,7.610,7.520,7.600,7.610,10324439,77986250.000,691200,7.600,270500,7.590,72500,7.580,172400,7.570,165200,7.560,359020,7.610,559100,7.620,543695,7.630,216000,7.640,312300,7.650,2019-10-09,14:19:20,00,\";";
        SinaRealTimeResult sinaRealTimeResult = new SinaRealTimeResult(text);
        List<List<String>> result = sinaRealTimeResult.getResult();
        result.forEach(System.out::println);
        String collect = result.stream()
                .map(item -> {
                    return item.get(0);
                })
                .collect(Collectors.joining(","));
        System.out.println(collect);
    }


}
