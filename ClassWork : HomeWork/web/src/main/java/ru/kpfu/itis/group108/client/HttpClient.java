package ru.kpfu.itis.group108.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpClient implements Interface {

    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (String params2 : params.keySet()) {
                URL url2 = new URL(url + "?" + params2 + "=" + params.get(params2));
                HttpURLConnection getConnection = (HttpURLConnection) url2.openConnection();
                getConnection.setRequestMethod("GET");

                for (String headersKeys : headers.keySet()) {
                    getConnection.setRequestProperty(headersKeys, headers.get(headersKeys));
                }

                getConnection.setConnectTimeout(5000);
                getConnection.setReadTimeout(5000);

                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getConnection.getInputStream()))) {
                    String input;
                    while ((input = bufferedReader.readLine()) != null) {
                        stringBuilder.append(input);
                    }
                    System.out.println(stringBuilder);
                }
                getConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(stringBuilder);
    }


    @Override
    public String post(String url, Map<String, String> headers, Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL postUrl = new URL(url);
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
            postConnection.setRequestMethod("POST");

            for (String headersKeys : headers.keySet()) {
                postConnection.setRequestProperty(headersKeys, headers.get(headersKeys));
            }
            postConnection.setDoOutput(true);

            try (OutputStream outputStream = postConnection.getOutputStream()) {
                byte[] input = params.toString().getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }
            System.out.println(postConnection.getResponseCode());

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(postConnection.getInputStream()))) {
                String input;
                while ((input = reader.readLine()) != null) {
                    stringBuilder.append(input);
                }
                System.out.println(stringBuilder);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(stringBuilder);
    }
}
