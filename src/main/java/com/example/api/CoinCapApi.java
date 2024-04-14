package com.example.api;

import com.example.utils.RequestEconomizer;
import com.example.utils.jsonParser.JsonParser;
import com.example.utils.requestSender.RequestSender;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CoinCapApi implements CurrencyApi {

    private final RequestSender requestSender;
    private final JsonParser jsonParser;

    private final Map<String, String> currencyUrlMap = new LinkedHashMap<>() {{
        put("BTC", "https://api.coincap.io/v2/rates/bitcoin");
        put("ETH", "https://api.coincap.io/v2/rates/ethereum");
        put("BCH", "https://api.coincap.io/v2/rates/bitcoin-cash");
        put("EOS", "https://api.coincap.io/v2/rates/eos");
        put("LTC", "https://api.coincap.io/v2/rates/litecoin");
        put("DOGE", "https://api.coincap.io/v2/rates/dogecoin");
        put("USDT", "https://api.coincap.io/v2/rates/tether");
    }};

    private final Duration updateDuration = Duration.ofMinutes(5);
    private final RequestEconomizer economizer = new RequestEconomizer(updateDuration);

    public CoinCapApi(RequestSender requestSender, JsonParser jsonParser) {
        this.requestSender = requestSender;
        this.jsonParser = jsonParser;
    }

    @Override
    public String getName() {
        return "CoinCap";
    }

    @Override
    public String getDescription() {
        return "официальный API сервиса https://coincap.io/";
    }

    @Override
    public Duration getUpdateDuration() {
        return updateDuration;
    }

    public Set<String> getCurrencies() {
        return currencyUrlMap.keySet();
    }

    public String getPrice(String currency) {
        assert !currencyUrlMap.containsKey(currency);
        String url = currencyUrlMap.get(currency);
        if (economizer.isEconomizable(url))
            return economizer.getFromCache(url);
        String response = requestSender.send(url);
        String value = jsonParser.parse(response, "data.rateUsd");
        economizer.save(url, value);
        return value;
    }
}
