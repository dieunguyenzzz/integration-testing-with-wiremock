package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GatewayService {
    public String fetchWeatherData(String endpoint) throws IOException {
        return fetchDataFromExternalService(endpoint);
    }

    public String fetchStockData(String endpoint) throws IOException {
        return fetchDataFromExternalService(endpoint);
    }

    public String fetchNewsData(String endpoint) throws IOException {
        return fetchDataFromExternalService(endpoint);
    }

    private String fetchDataFromExternalService(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }
    }
}
