package io.bhuo.api.test;

import io.bhuo.api.test.constant.Constants;
import io.buo.api.client.BHexApiClientFactory;
import io.buo.api.client.BHexApiRestClient;
import io.buo.api.client.BHexApiWebSocketClient;
import io.buo.api.client.constant.BHexConstants;

//@Slf4j
public class UserDataStreamTest {

    public static void main(String[] args) {
//
        BHexApiWebSocketClient client = BHexApiClientFactory.newInstance().newWebSocketClient();
        BHexApiRestClient restClient = BHexApiClientFactory.newInstance(Constants.ACCESS_KEY, Constants.SECRET_KEY).newRestClient();

        System.out.println("\n ------Get Listen Key -----");
        System.out.println();
        String listenKey = restClient.startUserDataStream(BHexConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
        System.out.println("listenKey:" + listenKey);
        // order
        client.onUserEvent(listenKey, response -> System.out.println(response), true);

    }
}
