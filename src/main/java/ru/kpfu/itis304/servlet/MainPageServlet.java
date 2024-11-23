package ru.kpfu.itis304.servlet;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.kpfu.itis304.util.Client;
import ru.kpfu.itis304.util.HttpClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/main")
public class MainPageServlet extends HttpServlet {

    private static final String API_KEY = "bd5e378503939ddaee76f12ad7a97608";
    private static final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final HttpClient client = new Client();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        String city = req.getParameter("city");
        if (city == null || city.trim().isEmpty()) {
            resp.getWriter().write("City name is missing.");
            return;
        }

        Map<String, String> getHeaders = new HashMap<>();
        getHeaders.put("Content-Type", "application/json");
        getHeaders.put("Accept", "application/json");
        Map<String, String> params = new HashMap<>();
        params.put("q", city);
        params.put("appid", API_KEY);
        params.put("units", "metric");
        params.put("lang", "ru");

        try {
            String weatherData = client.get(url, getHeaders, params);

            JSONObject json = new JSONObject(weatherData);
            double temperature = Double.NaN;

            JSONObject mainObject = json.optJSONObject("main");
            if (mainObject != null) {
                temperature = mainObject.optDouble("temp", Double.NaN);
            }

            String responseText = String.format("Температура в городе - %s: %.1f°C", city, temperature);
            resp.getWriter().write(responseText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


