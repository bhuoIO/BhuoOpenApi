package io.bhuo.api.test;

import io.buo.api.client.BHexApiClientFactory;
import io.buo.api.client.BHexApiRestClient;
import io.buo.api.client.domain.account.NewOrder;
import io.buo.api.client.domain.account.NewOrderResponse;
import io.buo.api.client.domain.account.TimeInForce;
import io.bhuo.api.test.constant.Constants;

import java.math.BigDecimal;

/**
 * author:zhiwei
 * <p>
 * email:597384114@qq.com
 * <p>
 * time:2019/1/22
 **/

public class KRobot {


    public static void main(String[] args) throws InterruptedException {

        BHexApiClientFactory factory = BHexApiClientFactory.newInstance(Constants.ACCESS_KEY, Constants.SECRET_KEY);
        BHexApiRestClient client = factory.newRestClient();

        BigDecimal minPrice = new BigDecimal("0.00014");
        BigDecimal maxPrice = new BigDecimal("0.000218");

        String symbol = "WATBETH";

        BigDecimal oldPrice = new BigDecimal(client.getPrice(symbol).getPrice());

        while (true) {

            Thread.sleep(600000);

            BigDecimal newPrice = new BigDecimal(client.getPrice(symbol).getPrice());

            if(newPrice.compareTo(oldPrice)>=0) {

                oldPrice = newPrice;
                //上升态势
                if(newPrice.compareTo(maxPrice)<0) {

                    NewOrderResponse response2 = client.newOrder(NewOrder.limitBuy("WATBETH", TimeInForce.GTC, "173.3", newPrice.add(new BigDecimal("0.000008")).toPlainString()));

                    //NewOrderResponse response3 = client.newOrder(NewOrder.limitBuy("WATBETH", TimeInForce.GTC, "80.9", newPrice.subtract(new BigDecimal("0.000003")).toPlainString()));
                    NewOrderResponse response1 = client.newOrder(NewOrder.limitSell("WATBETH", TimeInForce.GTC, "83.3", newPrice.add(new BigDecimal("0.000007")).toPlainString()));

                } else {

                    NewOrderResponse response3 = client.newOrder(NewOrder.limitSell("WATBETH", TimeInForce.GTC, "363.3", maxPrice.subtract(new BigDecimal("0.000019")).toPlainString()));



                    //  NewOrderResponse response1 = client.newOrder(NewOrder.limitSell("WATBETH", TimeInForce.GTC, "84.5", maxPrice.subtract(new BigDecimal("0.000004")).toPlainString()));
                    NewOrderResponse response2 = client.newOrder(NewOrder.limitBuy("WATBETH", TimeInForce.GTC, "83.3", maxPrice.subtract(new BigDecimal("0.000008")).toPlainString()));

                }





            } else {

                oldPrice = newPrice;

                // 下降
                if(newPrice.compareTo(minPrice)>0) {


                    NewOrderResponse response1 = client.newOrder(NewOrder.limitSell("WATBETH", TimeInForce.GTC, "163.3", newPrice.subtract(new BigDecimal("0.000005")).toPlainString()));

                    NewOrderResponse response2 = client.newOrder(NewOrder.limitBuy("WATBETH", TimeInForce.GTC, "83.3", newPrice.subtract(new BigDecimal("0.00004")).toPlainString()));



                } else {

                    NewOrderResponse response2 = client.newOrder(NewOrder.limitBuy("WATBETH", TimeInForce.GTC, "186.3", newPrice.add(new BigDecimal("0.000013")).toPlainString()));


                    NewOrderResponse response1 = client.newOrder(NewOrder.limitSell("WATBETH", TimeInForce.GTC, "83.3", newPrice.add(new BigDecimal("0.000005")).toPlainString()));
                }

            }




        }

    }


}
