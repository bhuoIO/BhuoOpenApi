package io.buo.api.client;


import io.buo.api.client.impl.BHexApiRestClientImpl;
import io.buo.api.client.impl.BHexApiWebSocketClientImpl;
import io.buo.api.client.impl.BHexApiServiceGenerator;

/**
 * A factory for creating BHexApi client objects.
 */
public class BHexApiClientFactory {

    /**
     * API Key
     */
    private String apiKey;

    /**
     * Secret.
     */
    private String secret;

    /**
     * Instantiates a new BHex api client factory.
     *
     * @param apiKey the API key
     * @param secret the Secret
     */
    private BHexApiClientFactory(String apiKey, String secret) {
        this.apiKey = apiKey;
        this.secret = secret;
    }

    /**
     * New instance.
     *
     * @param apiKey the API key
     * @param secret the Secret
     * @return the BHex api client factory
     */
    public static BHexApiClientFactory newInstance(String apiKey, String secret) {
        return new BHexApiClientFactory(apiKey, secret);
    }

    /**
     * New instance without authentication.
     *
     * @return the BHex api client factory
     */
    public static BHexApiClientFactory newInstance() {
        return new BHexApiClientFactory(null, null);
    }

    /**
     * Creates a new synchronous/blocking REST client.
     */
    public BHexApiRestClient newRestClient() {
        return new BHexApiRestClientImpl(apiKey, secret);
    }


    public BHexApiWebSocketClient newWebSocketClient() {
        return new BHexApiWebSocketClientImpl(BHexApiServiceGenerator.getSharedClient());
    }

}
