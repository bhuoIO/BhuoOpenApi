package io.buo.api.client.impl;

import io.buo.api.client.BHexApiRestClient;
import io.bhex.api.client.domain.account.*;
import io.bhex.api.client.domain.account.request.*;
import io.buo.api.client.domain.account.*;
import io.buo.api.client.domain.account.request.*;
import io.buo.api.client.domain.general.BrokerInfo;
import io.bhex.api.client.domain.market.*;
import io.buo.api.client.domain.market.*;
import io.buo.api.client.service.BHexApiService;

import java.util.List;

import static io.buo.api.client.impl.BHexApiServiceGenerator.createService;

/**
 * Implementation of BHex's REST API using Retrofit with synchronous/blocking method calls.
 */
public class BHexApiRestClientImpl implements BHexApiRestClient {

    private final BHexApiService bHexApiService;

    public BHexApiRestClientImpl(String apiKey, String secret) {
        bHexApiService = BHexApiServiceGenerator.createService(BHexApiService.class, apiKey, secret);
    }

    // General endpoints

    @Override
    public void ping() {
        BHexApiServiceGenerator.executeSync(bHexApiService.ping());
    }

    @Override
    public Long getServerTime() {
        return BHexApiServiceGenerator.executeSync(bHexApiService.getServerTime()).getServerTime();
    }

    @Override
    public BrokerInfo getBrokerInfo() {
        return BHexApiServiceGenerator.executeSync(bHexApiService.getBrokerInfo());
    }


    public OrderBook getOrderBook(String symbol, Integer limit) {
        return BHexApiServiceGenerator.executeSync(bHexApiService.getOrderBook(symbol, limit));
    }

    public List<TradeHistoryItem> getTrades(String symbol, Integer limit) {
        return BHexApiServiceGenerator.executeSync(bHexApiService.getTrades(symbol, limit));
    }

    public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Long startTime, Long endTime, Integer limit) {
        return BHexApiServiceGenerator.executeSync(bHexApiService.getCandlestickBars(symbol, interval.getIntervalId(), startTime, endTime, limit));
    }

    public TickerStatistics get24HrPriceStatistics(String symbol) {
        return BHexApiServiceGenerator.executeSync(bHexApiService.get24HrPriceStatistics(symbol));
    }

    @Override
    public TickerPrice getPrice(String symbol) {
        return BHexApiServiceGenerator.executeSync(bHexApiService.getLatestPrice(symbol));
    }

    public BookTicker getBookTicker(String symbol) {
        return BHexApiServiceGenerator.executeSync(bHexApiService.getBookTicker(symbol));
    }


    @Override
    public NewOrderResponse newOrder(NewOrder order) {
        return BHexApiServiceGenerator.executeSync(bHexApiService.newOrder(order.getSymbol(), order.getSide(), order.getType(),
                order.getTimeInForce(), order.getQuantity(), order.getPrice(), order.getNewClientOrderId(), order.getStopPrice(),
                order.getIcebergQty(), order.getRecvWindow(), order.getTimestamp()));
    }

    @Override
    public Order getOrderStatus(OrderStatusRequest orderStatusRequest) {
        return BHexApiServiceGenerator.executeSync(bHexApiService.getOrderStatus(orderStatusRequest.getOrderId(), orderStatusRequest.getOrigClientOrderId(),
                orderStatusRequest.getRecvWindow(), orderStatusRequest.getTimestamp()));
    }

    @Override
    public CancelOrderResponse cancelOrder(CancelOrderRequest cancelOrderRequest) {
        return BHexApiServiceGenerator.executeSync(bHexApiService.cancelOrder(cancelOrderRequest.getOrderId(), cancelOrderRequest.getClientOrderId(),
                cancelOrderRequest.getRecvWindow(), cancelOrderRequest.getTimestamp()));
    }

    @Override
    public List<Order> getOpenOrders(OpenOrderRequest orderRequest) {
        return BHexApiServiceGenerator.executeSync(bHexApiService.getOpenOrders(orderRequest.getSymbol(), orderRequest.getLimit(), orderRequest.getRecvWindow(), orderRequest.getTimestamp()));
    }

    @Override
    public List<Order> getHistoryOrders(HistoryOrderRequest orderRequest) {
        return BHexApiServiceGenerator.executeSync(bHexApiService.getHistroyOrders(orderRequest.getOrderId(), orderRequest.getStartTime(), orderRequest.getEndTime(),
                orderRequest.getLimit(), orderRequest.getRecvWindow(), orderRequest.getTimestamp()));
    }

    @Override
    public Account getAccount(Long recvWindow, Long timestamp) {
        return BHexApiServiceGenerator.executeSync(bHexApiService.getAccount(recvWindow, timestamp));
    }

    @Override
    public List<Trade> getMyTrades(MyTradeRequest request) {
        return BHexApiServiceGenerator.executeSync(bHexApiService.getMyTrades(request.getFromId(), request.getStartTime(), request.getEndTime(), request.getLimit(),
                request.getRecvWindow(), request.getTimestamp()));
    }

    @Override
    public List<DepositOrder> getDepositOrders(DepositOrderRequest request) {
        return BHexApiServiceGenerator.executeSync(bHexApiService.getDepositOrders(request.getToken(), request.getStartTime(), request.getEndTime(), request.getFromId(), request.getLimit(),
                request.getRecvWindow(), request.getTimestamp()));
    }

    @Override
    public String startUserDataStream(Long recvWindow, Long timestamp) {
        return BHexApiServiceGenerator.executeSync(bHexApiService.startUserDataStream(recvWindow, timestamp)).toString();
    }

    @Override
    public void keepAliveUserDataStream(String listenKey, Long recvWindow, Long timestamp) {
        BHexApiServiceGenerator.executeSync(bHexApiService.keepAliveUserDataStream(listenKey, recvWindow, timestamp));
    }

    @Override
    public void closeUserDataStream(String listenKey, Long recvWindow, Long timestamp) {
        BHexApiServiceGenerator.executeSync(bHexApiService.closeAliveUserDataStream(listenKey, recvWindow, timestamp));
    }

}
