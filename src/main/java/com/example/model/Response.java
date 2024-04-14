package com.example.model;

import java.util.Date;

public class Response {
    private final String response;
    private final Date timestamp;

    public Response(String response, Date timestamp) {
        this.response = response;
        this.timestamp = timestamp;
    }

    public String getResponse() {
        return response;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Response{" +
                "response='" + response + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
