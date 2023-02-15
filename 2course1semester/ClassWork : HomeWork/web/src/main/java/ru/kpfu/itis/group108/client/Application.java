package ru.kpfu.itis.group108.client;

import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();

        Map<String, String> getParams = new HashMap<>();
        Map<String, String> getHeaders = new HashMap<>();
        Map<String, String> postParams = new HashMap<>();
        Map<String, String> postHeaders = new HashMap<>();

        getParams.put("back", "ground");
        postParams.put("up", "lich");
        getHeaders.put("Content-Type", "application/json");
        postHeaders.put("Content-Type", "application/x-www-form-urlencoded");
        postHeaders.put("User-Agent", "Mozilla/5.0");

        httpClient.get ("https://postman-echo.com/get", getHeaders, postParams) ;
        httpClient.post ("https://postman-echo.com/post", postHeaders, postParams);
    }
}
