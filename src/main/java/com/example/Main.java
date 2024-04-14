package com.example;

import com.example.model.Request;

public class Main {
    public static void main(String[] args) {

        ApiManager apiManager = new ApiManager();
        apiManager.getAllApi().forEach(System.out::println);
        apiManager.getPossibleRequests().forEach(System.out::println);
        System.out.println(apiManager.getPrice(new Request("CoinCap","USDT")));
    }
}