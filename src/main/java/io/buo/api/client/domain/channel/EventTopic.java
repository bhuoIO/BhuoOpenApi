package io.buo.api.client.domain.channel;

public enum EventTopic {

    REALTIMES("realtimes"),
    TRADES("trade"),
    KLINE("kline_%s"),
    DEPTH("depth"),

    ORDER("order"),
    ;

    private String topic;

    EventTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public String getTopic(String interval) {
        return String.format(this.getTopic(), interval);
    }
}
