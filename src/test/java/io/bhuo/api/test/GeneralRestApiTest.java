package io.bhuo.api.test;

import io.bhuo.api.test.constant.Constants;
import io.buo.api.client.BHexApiClientFactory;
import io.buo.api.client.BHexApiRestClient;
import io.buo.api.client.domain.general.BrokerInfo;

public class GeneralRestApiTest {

    public static void main(String[] args) {

        BHexApiClientFactory factory = BHexApiClientFactory.newInstance(Constants.ACCESS_KEY, Constants.SECRET_KEY);
        BHexApiRestClient client = factory.newRestClient();

        System.out.println("\n ------BrokerInfo-----");
        BrokerInfo brokerInfo = client.getBrokerInfo();
        System.out.println(brokerInfo);

    }


}
