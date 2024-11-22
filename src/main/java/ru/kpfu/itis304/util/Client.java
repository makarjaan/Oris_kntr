package ru.kpfu.itis304.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Client implements HttpClient{

    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> params) {
        try {
            URL getUrl = new URL(getUrl(url, params));
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", headers.get("Content-Type"));
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            return readResponse(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String post(String url, Map<String, String> headers, Map<String, String> data) {
        try {
            URL postUrl = new URL(url);
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
            postConnection.setRequestMethod("POST");
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    postConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            postConnection.setDoOutput(true);

            String jsonInput = mapToJsonFile(data);

            try (OutputStream outputStream = postConnection.getOutputStream()) {
                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }
            System.out.println(postConnection.getResponseCode());
            return readResponse(postConnection);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String put(String url, Map<String, String> headers, Map<String, String> data) {
        try{
            URL putUrl = new URL(url);
            HttpURLConnection putConnection = (HttpURLConnection) putUrl.openConnection();
            putConnection.setRequestMethod("PUT");
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    putConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            putConnection.setDoOutput(true);

            String jsonInput = mapToJsonFile(data);

            try (OutputStream outputStream = putConnection.getOutputStream()) {
                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }
            System.out.println(putConnection.getResponseCode());


            return readResponse(putConnection);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String delete(String url, Map<String, String> headers, Map<String, String> data) {
        try{
            URL deleteURL = new URL(addParameters(url, data));
            HttpURLConnection deleteConnection = (HttpURLConnection) deleteURL.openConnection();
            deleteConnection.setRequestMethod("DELETE");
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    deleteConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            deleteConnection.setConnectTimeout(5000);
            deleteConnection.setReadTimeout(5000);

            System.out.println(deleteConnection.getResponseCode());

            return readResponse(deleteConnection);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String addParameters(String url, Map<String, String> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return url;
        }
        StringBuilder urlParams = new StringBuilder(url);
        urlParams.append("?");
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            urlParams.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        urlParams.deleteCharAt(urlParams.length() - 1);
        return urlParams.toString();
    }


    public static String mapToJsonFile(Map<String, String> map) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(map);
    }

    private String readResponse(HttpURLConnection connection) throws IOException {
        if (connection != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                return content.toString();
            }
        }
        return null;
    }

    public String getUrl(String url, Map<String, String> param) {
        if (param != null) {
            StringBuilder getUrl = new StringBuilder(url).append("?");
            param.forEach((key, value) -> getUrl.append(key).append("=").append(value).append("&"));
            getUrl.setLength(getUrl.length() - 1);
            return getUrl.toString();
        } else {
            return url;
        }
    }
}

