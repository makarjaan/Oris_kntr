package ru.kpfu.itis304.util;

import java.util.Map;

public interface HttpClient {

    String get (String url, Map<String, String> headers, Map<String, String> data);
    String post(String url, Map<String, String> headers, Map<String, String> data);
    String put(String url, Map<String, String> headers, Map<String, String> data);
    String delete(String url, Map<String, String> headers, Map<String, String> data);
}