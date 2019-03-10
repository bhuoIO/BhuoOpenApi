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

/**
 * author:zhiwei
 * <p>
 * email:597384114@qq.com
 * <p>
 * time:2019/1/21
 **/

public class Robot {


    public static void main(String[] args) {

        BHexApiClientFactory factory = BHexApiClientFactory.newInstance(Constants.ACCESS_KEY, Constants.SECRET_KEY);
        BHexApiRestClient client = factory.newRestClient();

      //  NewOrderResponse response2 = client.newOrder(NewOrder.limitSell("WATBETH", TimeInForce.GTC, "1000", "0.000127"));

        while (true) {

            try {
                Thread.sleep(300000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Account account = client.getAccount(BHexConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
            AssetBalance eth = account.getAssetBalance("ETH");
            AssetBalance watb = account.getAssetBalance("WATB");

            BigDecimal eb = new BigDecimal(eth.getFree());
            BigDecimal wb = new BigDecimal(watb.getFree());

            OrderBook orderBook = client.getOrderBook("WATBETH", 20);

            List<OrderBookEntry> asks = orderBook.getAsks();

            List<OrderBookEntry> bids = orderBook.getBids();

            BigDecimal minSell = new BigDecimal(asks.get(0).getPrice());
            BigDecimal maxBuy = new BigDecimal(bids.get(0).getPrice());

            System.out.println(maxBuy);

            if(eb.compareTo(new BigDecimal("0.1"))<0) {
                NewOrderResponse response3 = client.newOrder(NewOrder.limitSell("WATBETH", TimeInForce.GTC, "1000", bids.get(5).getPrice()));
                continue;
            }


            if(wb.compareTo(new BigDecimal("100"))>0) {

                NewOrderResponse response1 = client.newOrder(NewOrder.limitSell("WATBETH", TimeInForce.GTC, "83.3", maxBuy.toPlainString()));

                System.out.println("response1" + response1);

            }

            if(eb.compareTo(new BigDecimal("0.1"))>0) {

                NewOrderResponse response3 = client.newOrder(NewOrder.limitBuy("WATBETH", TimeInForce.GTC, new BigDecimal(new Random().nextInt(200)+1633).divide(new BigDecimal(10)).setScale(1).toPlainString(), minSell.toPlainString()));

                System.out.println("response2" + response3);

            }



        }

    }

}
