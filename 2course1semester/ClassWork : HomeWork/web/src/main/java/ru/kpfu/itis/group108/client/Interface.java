package ru.kpfu.itis.group108.client;

import java.util.Map;

public interface Interface {

    String get(String url, Map<String, String> headers, Map<String, String> params);

    String post(String url, Map<String, String> headers, Map<String, String> params);

//    void head(String Url) throws IOException, InterruptedException;

//    void put(String url) throws InterruptedException;

//    void delete(String Url);
}
