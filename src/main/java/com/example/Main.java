package com.example;

import com.example.api.CoinCapApi;
import com.example.api.CurrencyApi;
import com.example.utils.jsonParser.JsonParser;
import com.example.utils.jsonParser.JsonSimpleParser;
import com.example.utils.requestSender.ApacheHttpRequestSender;
import com.example.utils.requestSender.RequestSender;

public class Main {
    public static void main(String[] args) {

        RequestSender requestSender = new ApacheHttpRequestSender();
        JsonParser jsonParser = new JsonSimpleParser();

        CurrencyApi currencyApi = new CoinCapApi(requestSender, jsonParser);
    }
}