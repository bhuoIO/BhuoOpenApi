package io.buo.api.client.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.buo.api.client.constant.BHexConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@JsonDeserialize(using = TradeEventDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeEvent {

    private String symbol;

    public List<TradeItem> itemList;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<TradeItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<TradeItem> itemList) {
        this.itemList = itemList;
    }

    public String toString() {
        return new ToStringBuilder(this, BHexConstants.TO_STRING_BUILDER_STYLE)
                .append("symbol", symbol)
                .append("itemList", itemList)
                .toString();

    }
}
