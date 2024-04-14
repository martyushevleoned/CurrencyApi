package com.example.utils.requestSender;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpRequestSender implements RequestSender {

    private final OkHttpClient client = new OkHttpClient().newBuilder().build();

    @Override
    public String send(String url) {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
