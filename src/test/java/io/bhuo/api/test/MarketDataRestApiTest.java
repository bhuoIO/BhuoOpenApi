package io.bhuo.api.test;

import io.buo.api.client.BHexApiClientFactory;
import io.buo.api.client.BHexApiRestClient;
import io.bhuo.api.test.constant.Constants;
import io.buo.api.client.domain.market.BookTicker;
import io.buo.api.client.domain.market.TickerPrice;

public class MarketDataRestApiTest {


    public static void main(String[] args) {

        BHexApiClientFactory factory = BHexApiClientFactory.newInstance(Constants.ACCESS_KEY, Constants.SECRET_KEY);
        BHexApiRestClient client = factory.newRestClient();
        String symbol = "WATBETH";


//        System.out.println("\n ------Ping-----");
//        client.ping();
//
//        System.out.println("\n ------serverTime-----");
//        long serverTime = client.getServerTime();
//        System.out.println(serverTime);
//
//        System.out.println("\n ------get order book-----");
//        OrderBook orderBook = client.getOrderBook(symbol, 100);
//        System.out.println(orderBook);
//
//        System.out.println("\n ------get recent trades-----");
//        List<TradeHistoryItem> tradeHistoryItemList = client.getTrades(symbol, 10);
//        System.out.println(tradeHistoryItemList);
//
//        System.out.println("\n ------get klines-----");
//        List<Candlestick> candlestickList = client.getCandlestickBars(symbol, CandlestickInterval.ONE_MINUTE, 0l, 0L, 0);
//        System.out.println(candlestickList);
//
//        System.out.println("\n ------get 24h ticker-----");
//        TickerStatistics tickerStatistics = client.get24HrPriceStatistics(symbol);
//        System.out.println(tickerStatistics);

        System.out.println("\n ------get ticker price-----");
        TickerPrice tickerPrice = client.getPrice(symbol);
        System.out.println(tickerPrice);

        System.out.println("\n ------get book ticker-----");
        BookTicker bookTickerList = client.getBookTicker(symbol);
        System.out.println(bookTickerList);

    }
}
