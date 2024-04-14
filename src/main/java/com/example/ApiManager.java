package com.example;

import com.example.api.CoinCapApi;
import com.example.api.CurrencyApi;
import com.example.model.Request;
import com.example.model.Response;
import com.example.utils.jsonParser.JsonParser;
import com.example.utils.jsonParser.JsonSimpleParser;
import com.example.utils.requestSender.ApacheHttpRequestSender;
import com.example.utils.requestSender.RequestSender;

import java.util.*;

public class ApiManager {

    private final RequestSender requestSender = new ApacheHttpRequestSender();
    private final JsonParser jsonParser = new JsonSimpleParser();

    private final Map<String, CurrencyApi> currencyApiMap = new HashMap<>() {{
        CurrencyApi coinCap = new CoinCapApi(requestSender, jsonParser);
        put(coinCap.getName(), coinCap);
    }};

    private final Set<Request> requestList = new LinkedHashSet<>() {{
        currencyApiMap.forEach((apiName, api) ->
                api.getCurrencies().forEach(currency ->
                        add(new Request(apiName, currency))
                )
        );
    }};

    public Set<String> getAllApi() {
        return currencyApiMap.keySet();
    }

    public Set<Request> getPossibleRequests() {
        return requestList;
    }

    public Response getPrice(Request request) {
        assert requestList.contains(request);
        CurrencyApi currencyApi = currencyApiMap.get(request.getApi());
        double price = currencyApi.getPrice(request.getCurrency());
        return new Response(price + " " + request.getCurrency(), new Date());
    }
}
