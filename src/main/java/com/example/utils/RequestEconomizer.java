package com.example.utils;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RequestEconomizer {

    private final long updateTime;
    private final Map<String, ResponseDto> history = new HashMap<>();

    public RequestEconomizer(Duration updateTime) {
        this.updateTime = updateTime.toMillis();
    }

    public boolean isEconomizable(String request) {
        if (!history.containsKey(request))
            return false;
        return new Date().getTime() - history.get(request).timestamp < updateTime;
    }

    public void save(String request, String response) {
        history.put(request, new ResponseDto(response, new Date().getTime()));
    }

    public String getFromCache(String request) {
        return history.get(request).response;
    }

    private class ResponseDto {
        final long timestamp;
        final String response;

        public ResponseDto(String response, long timestamp) {
            this.response = response;
            this.timestamp = timestamp;
        }
    }
}
