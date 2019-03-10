package io.buo.api.client.domain.account.request;

import io.buo.api.client.constant.BHexConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class HistoryOrderRequest extends OrderRequest {

    private Long orderId;

    private Long startTime;

    private Long endTime;

    private Integer limit;

    public HistoryOrderRequest() {
    }

    public HistoryOrderRequest(Integer limit) {
        super();
        this.limit = limit;
    }

    public HistoryOrderRequest(Long orderId, Integer limit) {
        super();
        this.orderId = orderId;
        this.limit = limit;
    }

    public HistoryOrderRequest(Long orderId, Long startTime, Long endTime, Integer limit) {
        super();
        this.orderId = orderId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.limit = limit;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String toString() {
        return new ToStringBuilder(this, BHexConstants.TO_STRING_BUILDER_STYLE)
                .append("orderId", orderId)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .append("limit", limit)
                .toString();
    }
}
