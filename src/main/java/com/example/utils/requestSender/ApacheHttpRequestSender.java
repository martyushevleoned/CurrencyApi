package com.example.utils.requestSender;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

public class ApacheHttpRequestSender implements RequestSender {

    private HttpClient client = new HttpClient();

    public ApacheHttpRequestSender() {
//        client.ha
    }

    @Override
    public String send(String url) {
        HttpMethod method = new GetMethod(url);
        method.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0");
        byte[] responseBody = null;

        try {
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }
            responseBody = method.getResponseBody();

        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
        } finally {
            method.releaseConnection();
        }

        assert responseBody != null;
        return new String(responseBody);
    }
}
