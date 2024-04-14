package com.example.api;

import java.time.Duration;
import java.util.Set;

public interface CurrencyApi {

    String getName();

    String getDescription();

    Duration getUpdateDuration();

    Set<String> getCurrencies();

    double getPrice(String currency);
}
