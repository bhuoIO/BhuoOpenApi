package io.bhuo.api.test;

import io.buo.api.client.BHexApiClientFactory;
import io.buo.api.client.BHexApiRestClient;
import io.buo.api.client.constant.BHexConstants;
import io.buo.api.client.domain.account.*;
import io.buo.api.client.domain.market.OrderBook;
import io.buo.api.client.domain.market.OrderBookEntry;
import io.bhuo.api.test.constant.Constants;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class AccountRestApiTest {


    public static void main(String[] args) throws InterruptedException {

        BHexApiClientFactory factory = BHexApiClientFactory.newInstance(Constants.ACCESS_KEY, Constants.SECRET_KEY);
        BHexApiRestClient client = factory.newRestClient();

//        System.out.println("\n ------limit buy-----");
//        NewOrderResponse response1 = client.newOrder(NewOrder.limitBuy("BTCUSDT", TimeInForce.GTC, "0.01", "5678.9"));
//        System.out.println(response1);
////
//        System.out.println("\n ------limit sell-----");
//        NewOrderResponse response2 = client.newOrder(NewOrder.limitSell("BTCUSDT", TimeInForce.GTC, "0.01", "1001"));
//        System.out.println(response2);
//
//        System.out.println("\n ------market buy-----");
//        NewOrderResponse response3 = client.newOrder(NewOrder.marketBuy("BTCUSDT", "10"));
//        System.out.println(response3);
//
//        System.out.println("\n ------market sell-----");
//        NewOrderResponse response4 = client.newOrder(NewOrder.marketSell("BTCUSDT", "0.01"));
//        System.out.println(response4);
//
//        System.out.println("\n ------get order status-----");
//        Order order = client.getOrderStatus(new OrderStatusRequest(response1.getOrderId()));
//        System.out.println(order);
//
//        System.out.println("\n ------cancel order-----");
//        CancelOrderResponse cancelOrderResponse = client.cancelOrder(new CancelOrderRequest(response1.getOrderId()));
//        System.out.println(cancelOrderResponse);

//        System.out.println("\n ------get open orders-----");
//        List<Order> openOrderList = client.getOpenOrders(new OpenOrderRequest("WATBETH", 5));
//        System.out.println(openOrderList);
////
//        System.out.println("\n ------get history orders-----");
//        List<Order> historyOrderList = client.getHistoryOrders(new HistoryOrderRequest());
//        System.out.println(historyOrderList);

        System.out.println("\n ------get account information-----");
        Account account1 = client.getAccount(BHexConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
        System.out.println(account1);
        System.out.println(account1.getBalances());
        System.out.println(account1.getAssetBalance("ETH"));
//
//        System.out.println("\n ------get trades -----");
//        List<Trade> tradeList = client.getMyTrades(new MyTradeRequest(2));
//        System.out.println(tradeList);
//
//        System.out.println("\n ------get deposit order -----");
//        List<DepositOrder> list = client.getDepositOrders(new DepositOrderRequest());
//        System.out.println(list);


        while (true) {

            Thread.sleep(30000);

            Account account = client.getAccount(BHexConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
            AssetBalance eth = account.getAssetBalance("ETH");
            AssetBalance watb = account.getAssetBalance("WATB");
            AssetBalance hb = account.getAssetBalance("BH");

            BigDecimal eb = new BigDecimal(eth.getFree());
            BigDecimal wb = new BigDecimal(watb.getFree());

            OrderBook orderBook = client.getOrderBook("WATBETH", 20);

            List<OrderBookEntry> asks = orderBook.getAsks();

            List<OrderBookEntry> bids = orderBook.getBids();

            BigDecimal minSell = new BigDecimal(asks.get(0).getPrice());
            BigDecimal maxBuy = new BigDecimal(bids.get(0).getPrice());

            System.out.println(maxBuy);

            if(eb.compareTo(new BigDecimal("0.1"))<0) {
                continue;
            }

            while (minSell.subtract(maxBuy).compareTo(new BigDecimal("0.00001"))>0) {

                maxBuy = maxBuy.add(new BigDecimal("0.00001"));

                NewOrderResponse response1 = client.newOrder(NewOrder.limitBuy("WATBETH", TimeInForce.GTC, new BigDecimal(new Random().nextInt(200)+800).divide(new BigDecimal(10)).setScale(1).toPlainString(), maxBuy.toPlainString()));

                System.out.println("response1" + response1);


            }

        }




    }

}
