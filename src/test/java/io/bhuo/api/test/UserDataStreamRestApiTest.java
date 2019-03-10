package io.bhuo.api.test;

import io.bhuo.api.test.constant.Constants;
import io.buo.api.client.BHexApiClientFactory;
import io.buo.api.client.BHexApiRestClient;
import io.buo.api.client.constant.BHexConstants;

public class UserDataStreamRestApiTest {

    public static void main(String[] args) {

        BHexApiClientFactory factory = BHexApiClientFactory.newInstance(Constants.ACCESS_KEY, Constants.SECRET_KEY);
        BHexApiRestClient client = factory.newRestClient();

        System.out.println("\n ------start user data stream-----");
        String listenKey = client.startUserDataStream(BHexConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
        System.out.println(listenKey);

        System.out.println("\n ------keepAlive user data stream-----");
        client.keepAliveUserDataStream(listenKey, BHexConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());

        System.out.println("\n ------close user data stream-----");
        client.closeUserDataStream(listenKey, BHexConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());

    }

}
